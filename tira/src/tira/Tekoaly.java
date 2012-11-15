/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

import java.util.PriorityQueue;

/**
 *
 * @author juhainki
 */
public class Tekoaly {
    private PriorityQueue<Lauta> jono;
    private Puu puu;
    private int puoli;
    
    public Tekoaly(int omaPuoli) {
        puu = new Puu();
        jono = new PriorityQueue<>();
        this.puoli = omaPuoli;
    }
    
    // Leveys ensin läpikäynti -> listätään puuhun -> kutsutaan
    public Siirto valitseSiirto(int syvyys, Lauta oikeaTilanne) {

        lisaaSiirrot(syvyys, oikeaTilanne);
        
        Siirto ret = puu.minimax(puoli);
        return null;
    }

    private void lisaaSiirrot(int syvyys, Lauta tilanne) {
        tilanne.laskeSiirrot();
        
//        System.out.println("SYV:" + syvyys + " - " + tilanne);
        if (syvyys == 0) {
            return;
        }
        // Lisätään nykyisen tilanteen siirrot jonoon
        for (int i = 0; i < tilanne.siirrot().length(); i++) {
            jono.add(tilanne.siirto(tilanne.siirrot().get(i)));
        }
        
        // Otetaan jonon eka, lisätään se puuhun ja kutsutaan rekursiivisesti funktiota
        Lauta lauta = jono.poll();
        
        puu.lisaaAliPuu(lauta.siirrot());

        
        lisaaSiirrot(syvyys - 1, lauta);
    }
}
