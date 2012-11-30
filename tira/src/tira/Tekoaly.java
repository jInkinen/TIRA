
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
        
        //Räkennetaan puu alphabetaa varten
        rakennaPuu(puu.getJuuri(), oikeaTilanne, syvyys);
        
        //Luodaan vertailua varten apuarvot, jotka rikkovat pelin jos ne tulevat valituksi
        Siirto min = new Siirto(-2, -2, -2, -2, Integer.MIN_VALUE);
        Siirto max = new Siirto(-3, -3, -3, -3, Integer.MAX_VALUE);
        Solmu sMin = new Solmu(null, min);
        Solmu sMax = new Solmu(null, max);
        
        //Palautetaan alphabetan avulla paras siirto
        return puu.alphabeta(valkoinenko, syvyys - 1, puu.getJuuri(), sMin, sMax).getSiirto();
    }

    /**
     * Rakentaa puun alphabetassa käytettäväksi
     * @param s Puun juurisolmu
     * @param tilanne
     * @param syvyys
     * @return 
     */
    private Siirto rakennaPuu(Solmu s, Lauta tilanne, int syvyys) {
        // Ei edetä annettua syvyyttä pidemmälle
        if (syvyys <= 0) {
            return s.getSiirto();
        }

        System.out.println(puu.tulostus());
        //ensimmäisellä kutstukerralla luo     
        for (int i = 0; i < tilanne.siirrot().length(); i++) {
            System.out.println(tilanne.siirrot().get(i));
            Solmu uusiSolmu = new Solmu(s, tilanne.siirrot().get(i));
            s.lisaaLapsi(uusiSolmu);
            return rakennaPuu(uusiSolmu, tilanne, syvyys - 1);
        }
        
        tilanne.simuloiSiirto(s.getSiirto());

        tilanne.laskeSiirrot(valkoinenko);
        
        
        // kutsu metodia rekursiivisesti
        return s.getSiirto();
    }
}
