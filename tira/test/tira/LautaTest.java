
package tira;

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

    /**
     * Testaa siirtojen laskemisen aikavaativuutta
     */
    @Test
    public void testSimppeliSiirtojenLaskemisenAikaVaativuus() {
        long start = System.nanoTime();
        int kerrat = 1;
        //System.out.println("Lasketaan siirrot " + kerrat + " kertaa");
        Lauta instance = new Lauta();

        for (int i = 0; i < kerrat; i++) {
            instance.laskeSiirrot(true);
            instance.laskeSiirrot(false);
        }

        long end = System.nanoTime();
        double muunnos = (end - start) / 1000000000.0;
        //System.out.println("Kesto: " + muunnos + "s");


        // 1 000   : 0,1s
        // 10 000  : 1s
        // 100 000 : 12s
        // 500 000 : 45s
    }

    /**
     * Varmistaa, että lauta sisältää oikean tilanteen, kun se luodaan
     */
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

    /**
     * Varmistaa, että laudan voi luoda myös manuaalisesti
     */
    @Test
    public void lautaAlustuuManuaalisesti() {
        Ruudut r = new Ruudut(6, 6, 1, -1);
        r.alustaLauta();
        Lauta l = new Lauta(kokox, kokoy, r);

        assertEquals("|t|l|k|q|l|t|\n"
                + "|s|s|s|s|s|s|\n"
                + "| | | | | | |\n"
                + "| | | | | | |\n"
                + "|S|S|S|S|S|S|\n"
                + "|T|L|K|Q|L|T|\n", l.laudanTulostus());


    }
}
