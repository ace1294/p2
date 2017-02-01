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
        if (args.length != 1 && !args[0].endsWith(".vpl.pl")) {
            System.out.format("Usage: %s <file.vpl.pl>\n", Conform.class.getName());
            System.out.format("       <file.vpl.pl> will be checked for constraint violations\n");
            return;
        }
        try {
            // read database and get each table
            ErrorReport er = new ErrorReport();
            
            vpl = DB.readDataBase(args[0]);
            violetClass = vpl.getTable("violetClass");
            
            violetClass.tuples().forEach((t) -> {
                t.print(System.out);
            });
            
            violetInterface = vpl.getTable("violetInterface");
            violetAssociation = vpl.getTable("violetAssociation");
            violetMiddleLabels = vpl.getTable("violetMiddleLabels");

            /** following are rules/constraints to check on vpl database **/
            /** each rule (constraint) has its own static error method below **/
            
            // MiddleLabel Rule: each MiddleLabel tuple generates an error
            violetMiddleLabels.tuples().forEach((t) -> {
                middleLabel(t);
            });
            
            // Unique Names Rule: Classes and Interfaces have unique names constraint
            
            violetClass.stream().filter(x->violetClass.stream().filter(y->y.is("name", x.get("name"))).count()>1).forEach(t->ciShareName("",t));
            
            //  Null Names Rule: classes and interfaces cannot have null names
            
            violetClass.stream().filter(t->t.isNull("name")).forEach(t->nullName("",t));
            
            // Black Diamond Rule: if a black diamond has a cardinality, it must be 1
            
            // Diamonds Rule: if a diamond has a cardinality, it must be 0..1
            
            // Triangle Rule: no Triangle association can have anything other than '' for its other arrow 

            //  Non-Empty Rule: inheritance associations cannot have non-empty roles

            // Dotted Inheritance Rule: dotted lines exist only between classes and interfaces

            // Interface cannot implement Class Rule: implements is drawn from class to interface

            // Self Inheritance Rule: no class or interface can inherit from itself

            // Dotted Association Rule: non-inheritance association cannot be dotted
            
            // report errors
            er.printReport(System.out);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
}
