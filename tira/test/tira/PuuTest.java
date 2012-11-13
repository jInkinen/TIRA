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
public class PuuTest {
    
    public PuuTest() {
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
     * Test of add method, of class Puu.
     */
    @Test
    public void testEkaLisaysMeneeJuureksi() {
        Solmu solmu = new Solmu();
        Puu instance = new Puu();
        instance.add(solmu);
        
        assertEquals(instance.getJuuri(), solmu);
    }
    
    public void testTokaLisaysEiMeneJuureksi() {
        Solmu solmu = new Solmu();
        Solmu solmu2 = new Solmu();
        Puu instance = new Puu();
        instance.add(solmu);
        instance.add(solmu2);
        
        assertSame(instance.getJuuri(), solmu2);
    }
}
