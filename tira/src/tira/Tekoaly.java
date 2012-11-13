/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

/**
 *
 * @author juhainki
 */
public class Tekoaly {
    private Puu puu;
    private int puoli;
    private Lauta peli;

    public Tekoaly(int puoli, Lauta peli) {
        this.puoli = puoli;
        this.peli = peli;
        
        puu = new Puu();
        

    }
    
    public Siirto valitseSiirto(int syvyys) {
        Siirto ret;
        if (puoli > 0) {
            ret = puu.minimax(true, syvyys);
        } else {
            ret = puu.minimax(false, syvyys);
        }
        return ret;
    }
}
