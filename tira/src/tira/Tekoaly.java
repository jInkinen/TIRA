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

    private int puoli;
    
    public Tekoaly(int omaPuoli) {

        jono = new PriorityQueue<>();
        this.puoli = omaPuoli;
    }
    
    // Leveys ensin läpikäynti -> listätään puuhun -> kutsutaan
    /**
     * TODO
     * 
     * Toteuttaa tekoälyn siirron valikoinnin.
     * Algoritmi käy ensin läpi kaikki annetun pelitilanteen mahdolliset siirrot ja lisää ne jonoon.
     * Jonosta data lisätään puuhun, jonka jälkeen suoritetaan rekursiivinen kutsu. Lopulta rekursio
     * purkautuu, kun syvyys saavutetaan ja kaikki alkiot käydään läpi leveyssuuntaisesti.
     * 
     * @param syvyys kuinka syvälle siirtoja haetaan
     * @param oikeaTilanne mikä on ylemmän rekursiotason alkuperäinen tilanne
     * @return 
     */
    public Siirto valitseSiirto(int syvyys, Lauta oikeaTilanne) {

        lisaaSiirrot(syvyys, oikeaTilanne);
        
        // TODO: tallenna puuhun ja suorita haku
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
        
        // TODO: lisää puuhun
        
        lisaaSiirrot(syvyys - 1, lauta);
    }
}
