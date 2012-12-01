
package tira;


/**
 * Tekoäly toteuttaa pelin tekoälylliset ominaisuudet hyödyntäen mm. puu-luokasta
 * löytyviä välineitä.
 */
public class Tekoaly {
    
    private Puu puu;
    private boolean valkoinenko;
    
    public Tekoaly(boolean omaPuoli) {
        this.puu = new Puu();
        this.valkoinenko = omaPuoli;
    }
    
    /**
     * Toteuttaa tekoälyn siirron valikoinnin.
     * Algoritmi käy ensin läpi kaikki annetun pelitilanteen mahdolliset siirrot ja lisää ne jonoon.
     * Jonosta data lisätään puuhun, jonka jälkeen suoritetaan rekursiivinen kutsu. Lopulta rekursio
     * purkautuu, kun syvyys saavutetaan ja kaikki alkiot käydään läpi leveyssuuntaisesti.
     * 
     * @param syvyys kuinka syvälle siirtoja haetaan
     * @param oikeaTilanne mikä on ylemmän rekursiotason alkuperäinen tilanne
     * @return 
     */
    public Siirto valitseSiirto(int syvyys, Lauta oikeaTilanne, int siirto) {
        oikeaTilanne.laskeSiirrot(valkoinenko);

        //Lisätään puulle juureksi jokin siirto
        Solmu apuSolmu = new Solmu(null, oikeaTilanne.siirrot().get(0));
        puu.setJuuri(apuSolmu);
        
        //Rakennetaan puu alphabetaa varten
        rakennaPuu(puu.getJuuri(), oikeaTilanne, syvyys);
        
        //Luodaan vertailua varten apuarvot, jotka rikkovat pelin jos ne tulevat valituksi
        Siirto min = new Siirto(-2, -2, -2, -2, Integer.MIN_VALUE);
        Siirto max = new Siirto(-3, -3, -3, -3, Integer.MAX_VALUE);
        Solmu sMin = new Solmu(null, min);
        Solmu sMax = new Solmu(null, max);

        //Palautetaan alphabetan avulla paras siirto
        return puu.alphabeta(valkoinenko, syvyys, puu.getJuuri(), sMin, sMax).getSiirto();
    }

    /**
     * Rakentaa puun alphabetassa käytettäväksi
     * @param s luotavan tason vanhempi
     * @param tilanne pelitilanne
     * @param syvyys syvyys, jota hyödynnetään rekursion katkaisussa
     */
    private void rakennaPuu(Solmu s, Lauta tilanne, int syvyys) {
        //varmistetaan että annetulle tilanteelle on laskettu siirrot
        tilanne.laskeSiirrot(valkoinenko);
        
        // Ei edetä annettua syvyyttä pidemmälle
        if (syvyys <= 0) {
            return;
        }
    
        //käydään läpi kaikki tilanteesta seuraavat siirrot ja lisätään
        //ne puuhun nykyisen tilanteen lapseksi
        for (int i = 0; i < tilanne.siirrot().length(); i++) {
            //Luodaan uusi solmu
            Solmu uusiSolmu = new Solmu(s, tilanne.siirrot().get(i));
            //lisätään uusi solmu lapseksi
            s.lisaaLapsi(uusiSolmu);
        }
        
        for (int i = 0; i < s.lastenMaara(); i++) {
            tilanne.toteutaSiirto(s.getLapset()[i].getSiirto());
            rakennaPuu(s.getLapset()[i], tilanne, syvyys - 1);

        }
    }
}
