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

    int kokox = 6;
    int kokoy = 6;
    Ruutu[][] ruudut = new Ruutu[kokox][kokoy];

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
     * Test of simuloiSiirto method, of class Lauta.
     */
    @Test
    public void testSimppeliSiirtojenLaskemisenAikaVaativuus() {
        long start = System.nanoTime();
        int kerrat = 1;
        //System.out.println("Lasketaan siirrot " + kerrat + " kertaa");
        Lauta instance = new Lauta();

        for (int i = 0; i < kerrat; i++) {
            instance.laskeSiirrot();
        }

        long end = System.nanoTime();
        double muunnos = (end - start) / 1000000000.0;
        //System.out.println("Kesto: " + muunnos + "s");


        // 1 000   : 0,1s
        // 10 000  : 1s
        // 100 000 : 12s
        // 500 000 : 45s
    }

    @Test
    public void lautaAlustuuOikein() {
        Lauta l = new Lauta();
        assertEquals("|t|l|k|q|l|t|\n"
                + "|s|s|s|s|s|s|\n"
                + "| | | | | | |\n"
                + "| | | | | | |\n"
                + "|S|S|S|S|S|S|\n"
                + "|T|L|K|Q|L|T|\n", l.laudanTulostus());
    }

    @Test
    public void lautaAlustuuManuaalisesti() {
        Ruudut r = new Ruudut(6, 6, 1, -1);
        r.alustaLauta();
        Lauta l = new Lauta(kokox, kokoy, r, 0);

        assertEquals("|t|l|k|q|l|t|\n"
                + "|s|s|s|s|s|s|\n"
                + "| | | | | | |\n"
                + "| | | | | | |\n"
                + "|S|S|S|S|S|S|\n"
                + "|T|L|K|Q|L|T|\n", l.laudanTulostus());


    }
}
