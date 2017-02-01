/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VPL;

import org.junit.Test;

/**
 *
 * @author don
 */
public class ConformTest {
    
    public ConformTest() {
    }

    @Test
    public void CarRental() {
        RegTest.Utility.redirectStdOut("out.txt");
        Conform.main("TestData/CarRental.vpl.pl");
        RegTest.Utility.validate("out.txt","Correct/CarRental.txt",false);
    }
    
    @Test
    public void Comp() {
        RegTest.Utility.redirectStdOut("out.txt");
        Conform.main("TestData/Comp.vpl.pl");
        RegTest.Utility.validate("out.txt","Correct/Comp.txt",false);
    }
    
    @Test
    public void DecayProduct() {
        RegTest.Utility.redirectStdOut("out.txt");
        Conform.main("TestData/DecayProduct.vpl.pl");
        RegTest.Utility.validate("out.txt","Correct/DecayProduct.txt",false);
    }
    
    @Test
    public void diagram1() {
        RegTest.Utility.redirectStdOut("out.txt");
        Conform.main("TestData/diagram1.vpl.pl");
        RegTest.Utility.validate("out.txt","Correct/diagram1.txt",false);
    }
    
    @Test
    public void squirrel() {
        RegTest.Utility.redirectStdOut("out.txt");
        Conform.main("TestData/squirrel.vpl.pl");
        RegTest.Utility.validate("out.txt","Correct/squirrel.txt",false);
    }
 
    @Test
    public void straight() {
        RegTest.Utility.redirectStdOut("out.txt");
        Conform.main("TestData/straight.vpl.pl");
        RegTest.Utility.validate("out.txt","Correct/straight.txt",false);
    }
}
