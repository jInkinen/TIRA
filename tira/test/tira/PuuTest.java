
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
    
}
