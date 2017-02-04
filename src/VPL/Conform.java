/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VPL;

import PrologDB.DB;
import PrologDB.ErrorReport;
import PrologDB.Table;
import PrologDB.Tuple;
import java.util.function.Predicate;

/**
 *
 * @author don
 */
public class Conform {

    static private DB vpl;
    static private Table violetClass;
    static private Table violetInterface;
    static private Table violetAssociation;
    static private Table violetMiddleLabels;

    /**
     * @param args -- single path to vpl database which is to be tested for conformance
     */
    public static void main(String... args) {
        if (args.length != 1 || !args[0].endsWith(".vpl.pl")) {
            System.out.format("Usage: %s <file.vpl.pl>\n", Conform.class.getName());
            System.out.format("       <file.vpl.pl> will be checked for constraint violations\n");
            return;
        }
        try {
            // read database and get each table
            ErrorReport er = new ErrorReport();
            
            vpl = DB.readDataBase(args[0]);
            violetClass = vpl.getTable("violetClass");
            violetInterface = vpl.getTable("violetInterface");
            violetAssociation = vpl.getTable("violetAssociation");
            violetMiddleLabels = vpl.getTable("violetMiddleLabels");

            
            runConstraints(er);
            
            // report errors
            er.printReport(System.out);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    // By the way I felt like this was broken up enough and I kept all the actual
    // constraints as one lines instead of breaking them out into blocks because
    // I like the way the one like functional programming looks
    private static void runConstraints(ErrorReport er) {
        runNamingConstraints(er);
        runMiddleLabelConstraint(er);
        runAssociationConstraints(er);
    }
    
    /*** interface and class name constraints ***/
    private static void runNamingConstraints(ErrorReport er) {
        // Unique Names Rule: Classes and Interfaces have unique names constraint
        violetClass.stream().filter(x->violetClass.stream().filter(y -> y.is("name", x.get("name"))).count()>1).forEach(t->er.add(ciShareName("multiple classes",t)));
        violetInterface.stream().filter(x->violetInterface.stream().filter(y->y.is("name", x.get("name"))).count()>1).forEach(t->er.add(ciShareName("multiple interfaces",t)));
        violetClass.stream().filter(x->violetInterface.stream().filter(y->y.is("name", x.get("name"))).count()>0).forEach(t->er.add(ciShareName("classes and interfaces",t)));
            
        //  Null Names Rule: classes and interfaces cannot have null names
        violetClass.stream().filter(t->t.is("name","")).forEach(t->er.add(nullName("class",t)));
        violetInterface.stream().filter(t->t.is("name","")).forEach(t->er.add(nullName("interface",t)));
    }
    
    /*** middle label constraints ***/
    private static void runMiddleLabelConstraint(ErrorReport er) {
        // MiddleLabel Rule: each MiddleLabel tuple generates an error
            violetMiddleLabels.tuples().forEach(t->er.add(middleLabel(t)));  
    }
    
    
    /*** association constraints ***/
    
    private static void runAssociationConstraints(ErrorReport er) {
        // Black Diamond Rule: if a black diamond has a cardinality, it must be 1
        violetAssociation.stream().filter(t->t.is("arrow1","BLACK_DIAMOND") && roleHasCard(t.get("role1")) && !convertRole(t.get("role1")).equals("1")).forEach(t->er.add(blackDiamond(t)));
        violetAssociation.stream().filter(t->t.is("arrow2","BLACK_DIAMOND") && roleHasCard(t.get("role2")) && !convertRole(t.get("role2")).equals("1")).forEach(t->er.add(blackDiamond(t)));

        // Diamonds Rule: if a diamond has a cardinality, it must be 0..1
        violetAssociation.stream().filter(t->t.is("arrow1","DIAMOND") && !convertRole(t.get("role1")).equals("0..1")).forEach(t->er.add(diamond(t)));
        violetAssociation.stream().filter(t->t.is("arrow2","DIAMOND") && !convertRole(t.get("role2")).equals("0..1")).forEach(t->er.add(diamond(t)));

        // Triangle Rule: no Triangle association can have anything other than '' for its other arrow 
        violetAssociation.stream().filter(t->t.is("arrow1","TRIANGLE") && !t.is("arrow2","")).forEach(t->er.add(arrow(t)));
        violetAssociation.stream().filter(t->t.is("arrow2","TRIANGLE") && !t.is("arrow1","")).forEach(t->er.add(arrow(t)));

        //  No Labels Rule: inheritance associations cannot have non-empty roles
        violetAssociation.stream().filter(t->(t.is("arrow1","TRIANGLE") || t.is("arrow2","TRIANGLE")) && (!convertRole(t.get("role1")).equals("") || !convertRole(t.get("role2")).equals(""))).forEach(t->er.add(noRoles(t)));

        // Solid Association Rule: non-implements, non-extends association must be solid
        violetAssociation.stream().filter(t->!((t.is("arrow1","TRIANGLE") && !t.is("arrow2","TRIANGLE")) || (!t.is("arrow1","TRIANGLE") && t.is("arrow2","TRIANGLE")) || (t.is("arrow1","V") && !t.is("arrow2","V")) || (!t.is("arrow1","V") && t.is("arrow2","V"))) && !t.is("lineStyle","")).forEach(t->er.add(noDottedAssoc(t)));

        // Extends Constraint: extends relationships must be solid
        violetAssociation.stream().filter(t->t.is("type1",t.get("type2")) && ((t.is("arrow1","TRIANGLE") && !t.is("arrow2","TRIANGLE")) || (!t.is("arrow1","TRIANGLE") && t.is("arrow2","TRIANGLE"))) && !t.is("lineStyle","")).forEach(t->er.add(dotted(t)));

        // Implements Constraint1 -- implementation relationships must be dotted.
        violetAssociation.stream().filter(t->(t.is("arrow1","TRIANGLE") || t.is("arrow2","TRIANGLE")) && !t.is("lineStyle","DOTTED")).forEach(t->er.add(dotted(t)));

        // Implements Constraint2 -- only classes can implement interfaces.
        violetAssociation.stream().filter(t->(t.is("arrow1","TRIANGLE") && t.is("type1","interfacenode")) || (t.is("arrow2","TRIANGLE") && t.is("type2","interfacenode"))).forEach(t->er.add(impls(t)));

        // Self Inheritance Rule: no class or interface can inherit from itself
        violetAssociation.stream().filter(t->(t.is("arrow1","TRIANGLE") && t.is("cid1",t.get("cid2"))) || (t.is("arrow2","TRIANGLE") && t.is("cid1",t.get("cid2")))).forEach(t->er.add(impls(t)));
            
    }

    /****  error messages ***/
    private static String ciShareName(String variant, Tuple t) {
        String e  = variant + " share the same name: " + t.get("name");
        return e;
    }
    
    /** parameter variant has 1 of 2 values "class" or "interface" **/
    private static String nullName(String variant, Tuple t) {
        String e = variant + " with id=" + t.get("id") + " has null name";
        return e;
    }
    private static String middleLabel(Tuple t) {
        String e = String.format("association (%s - %s) has middle label %s",
                convert(t.get("cid1")), convert(t.get("cid2")), t.get("label"));
        return e;
    }

    private static String arrow(Tuple t) {
        String e = String.format("inheritance paired with non-null end (%s - %s)", convert(t.get("cid1")), convert(t.get("cid2")));
        return e;
    }

    private static String dotted(Tuple t) {
        String e;
        e = String.format("dotted inheritance cannot connect %s to %s", convert(t.get("cid1")), convert(t.get("cid2")));
        return e;
    }

    private static String noRoles(Tuple t) {
        String e = String.format("inheritance (%s - %s) should have no roles", convert(t.get("cid1")), convert(t.get("cid2")));
        return e;
    }

    private static String selfInherit(Tuple t) {
        String e = String.format("%s cannot inherit from itself", convert(t.get("cid1")));
        return e;
    }

    private static String noDottedAssoc(Tuple t) {
        String e = String.format("association (%s - %s) cannot be dotted", convert(t.get("cid1")), convert(t.get("cid2")));
        return e;
    }

    private static String impls(Tuple t) {
        String e;
        if (t.is("type1", "classnode")) {
            e = String.format("interface %s cannot implement class %s", t.get("cid2"), t.get("cid1"));
        } else {
            e = String.format("interface %s cannot implement class %s", t.get("cid1"), t.get("cid2"));
        }
        return e;
    }

    private static String blackDiamond(Tuple t) {
        String e = String.format("black diamond on association (%s - %s) does not have cardinality 1",
                convert(t.get("cid1")), convert(t.get("cid2")));
        return e;
    }
    
    private static String diamond(Tuple t) {
        String e = String.format("diamond on association (%s - %s) does not have cardinality 0..1",
                convert(t.get("cid1")), convert(t.get("cid2")));
        return e;
    }
    
    /** utilities needed for precise error reporting **/

    /** I give one below: converts a class or interface
     * id into the name of a class or interface
     * @param id -- string id of a class or interface
     * @return name of the corresponding class or interface
     */

    private static String convert(String id) {
        Predicate<Tuple> idtest = r -> r.is("id", id);
        String n = violetClass.getFirst(idtest).get("name");
        if (n == null) {
            n = violetInterface.getFirst(idtest).get("name");
        }
        // should not be null... which also would be an error
        // I'm not checking this but you might...
        return n;
    }
    
    /** Checks to see if the role has cardinality
     * @param role -- string that comes from the role column (either role1 or role2)
     * @return returns if the tuple has cardinality
     */
    private static boolean roleHasCard(String role) {
        String[] splitRole = role.split(" ");
        String card = splitRole[0];
        switch (card) {
            case "1":
                return true;
            case "0..1":
                return true;
            case "n":
                return true;
            case "m":
                return true;
            case "0..*":
                return true;
            case "1..*":
                return true;
            case "*":
                return true;
            default:
                return false;
        }
    }
    
    /** Converts a role into just the cardinality since
     * it can have both the rolename and the cardinality
     * id into the name of a class or interface
     * @param role -- string that comes from the role column (either role1 or role2)
     * @return returns the cardinality if t exits, if its just a role name 
     */
    private static String convertRole(String role) {
        String[] splitRole = role.split(" ");
        
        return splitRole[0];
    }
}
