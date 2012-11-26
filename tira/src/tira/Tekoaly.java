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
    private boolean valkoinen;
    
    public Tekoaly(boolean omaPuoli) {
        this.puu = new Puu();
        this.valkoinen = omaPuoli;
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
        oikeaTilanne.laskeSiirrot();
        
        Lista l = oikeaTilanne.siirrot();
        Solmu apuSolmu = new Solmu(null, l.get(0));
        Solmu apuri2 = new Solmu(apuSolmu, l.getMax());
        puu.setJuuri(apuSolmu);
        for (int i = 0; i < l.length(); i++) {
            Solmu uusiS = new Solmu(apuri2, l.get(i));
            puu.getJuuri().lisaaLapsi(uusiS);
        }
        
        for (int i2 = 0; i2 < puu.getJuuri().lastenMaara(); i2++) {
            siirronLapset(puu.getJuuri().getLapset()[i2], oikeaTilanne, syvyys);
        }
        
        Siirto min = new Siirto(-1, -1, -1, -1, Integer.MIN_VALUE);
        Siirto max = new Siirto(-1, -1, -1, -1, Integer.MAX_VALUE);
        Solmu sMin = new Solmu(null, min);
        Solmu sMax = new Solmu(null, max);
        
        return puu.alphabeta(valkoinen, syvyys - 1, puu.getJuuri(), sMin, sMax).getSiirto();
    }

    private Siirto siirronLapset(Solmu s, Lauta tilanne, int syvyys) {
//        System.out.println(syvyys);
        if (syvyys == 0) {
            return s.getSiirto();
        }
        // toteuta simuloiSiirto
//        System.out.println(s.getSiirto());
        tilanne.simuloiSiirto(s.getSiirto());
        // laske uudet siirrot
        tilanne.laskeSiirrot();
//        System.out.println(tilanne.laudanTulostus());
        
        // lisää uudet lapset puuhun
        
        for (int i = 0; i < tilanne.siirrot().length(); i++) {
            Solmu uusiSolmu = new Solmu(s, tilanne.siirrot().get(i));
            s.lisaaLapsi(uusiSolmu);
            return siirronLapset(uusiSolmu, tilanne, syvyys - 1);
        }
        // kutsu metodia rekursiivisesti
        return s.getSiirto();
        // palauta simuloiSiirto
    }
}
