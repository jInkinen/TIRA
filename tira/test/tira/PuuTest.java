package tira;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juhainki
 */
public class PuuTest {

    /**
     * Testataan, että puun juuri asettuu oikein
     */
    @Test
    public void testJuurenLisays() {
        Siirto si = new Siirto(-1, -1, -1, -1, 0);
        Solmu s = new Solmu(null, si);
        Puu puu = new Puu();
        puu.setJuuri(s);

        assertEquals(puu.getJuuri(), s);
    }

    @Test
    public void testAB() {
        Puu puu = new Puu();

        Siirto si = new Siirto(-1, -1, -1, -1, -1);
        Solmu s = new Solmu(null, si);
        puu.setJuuri(null);

        for (int i = 0; i < 10; i++) {
            si = new Siirto(-1, -1, -1, -1, i);
            Solmu s2 = new Solmu(s, si);
            s.lisaaLapsi(s2);
            for (int j = 20; j > 10; j--) {
                si = new Siirto(-1, -1, -1, -1, j);
                Solmu s3 = new Solmu(s2, si);
                s2.lisaaLapsi(s3);
            }
        }
        
        Siirto min = new Siirto(-2, -2, -2, -2, Integer.MIN_VALUE);
        Siirto max = new Siirto(-3, -3, -3, -3, Integer.MAX_VALUE);
        Solmu sMin = new Solmu(null, min);
        Solmu sMax = new Solmu(null, max);
        
        Solmu ret = puu.alphabeta(false, 20, s, sMin, sMax);
        
        assertEquals(ret.vanhempi().vanhempi(), s);
    }
}
