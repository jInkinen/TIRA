/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juhainki
 */
public class LautaTest {
    
    public LautaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of siirto method, of class Lauta.
     */
    @Test
    public void testSiirtojenLaskemisenAikaVaativuus() {
        long start = System.nanoTime();
        int kerrat = 10000;
        System.out.println("Lasketaan siirrot " + kerrat + " kertaa");
        Lauta instance = new Lauta();
        
        for (int i = 0; i < kerrat; i++) {
            instance.laskeSiirrot();
        }
        
        long end = System.nanoTime();
        double muunnos = (end - start) / 1000000000.0;
        System.out.println("Kesto: " + muunnos + "s");
        
        
        // 1 000   : 0,1s
        // 10 000  : 1s
        // 100 000 : 12s
        // 500 000 : 45s
    }
}
