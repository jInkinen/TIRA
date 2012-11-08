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
     * Test of onkoSiirtoMahdollinen method, of class Lauta.
     */
    @Test
    public void testOnkoSiirtoMahdollinen() {
        System.out.println("onkoSiirtoMahdollinen");
        int x = 0;
        int y = 0;
        int uusix = 0;
        int uusiy = 0;
        Lauta instance = new Lauta();
        int expResult = 0;
        int result = instance.onkoSiirtoMahdollinen(x, y, uusix, uusiy);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of laudanTulostus method, of class Lauta.
     */
    @Test
    public void testLaudanTulostus() {
        System.out.println("laudanTulostus");
        Lauta instance = new Lauta();
        instance.laudanTulostus();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of laskeSiirrot method, of class Lauta.
     */
    @Test
    public void testLaskeSiirrot() {
        System.out.println("laskeSiirrot");
        Lauta instance = new Lauta();
        instance.laskeSiirrot();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
