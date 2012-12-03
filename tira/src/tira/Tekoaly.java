
package tira;


/**
 * Tekoäly toteuttaa pelin tekoälylliset ominaisuudet hyödyntäen mm. puu-luokasta
 * löytyviä välineitä.
 */
public class Tekoaly {
    private int debugKutsut, debugLehdet, debugLisayksia;
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
    public Siirto valitseSiirto(int syvyys, int siirto, Siirto[] siirrot) {
        this.debugLisayksia = 0;
        //Lisätään puulle juureksi jokin siirto
        Siirto dummy = new Siirto(-9, -9, -9, -9, 0);
        Solmu apuSolmu = new Solmu(null, dummy);
        puu.setJuuri(apuSolmu);
        
        //Rakennetaan puu alphabetaa varten
        rakennaPuu(puu.getJuuri(), syvyys, siirrot, siirto);
        System.out.println("Puuhun lisätty siirtoja yhteensä " + this.debugLisayksia);
//        System.out.println(puu.tulostus());
        //Luodaan vertailua varten apuarvot (jotka rikkovat pelin jos ne jostain syystä tulevat valituksi)
        Siirto min = new Siirto(-2, -2, -2, -2, Integer.MIN_VALUE);
        Siirto max = new Siirto(-3, -3, -3, -3, Integer.MAX_VALUE);
        Solmu sMin = new Solmu(null, min);
        Solmu sMax = new Solmu(null, max);

        //Palautetaan alphabetan avulla paras siirto
        Solmu tulos = puu.alphabeta(valkoinenko, syvyys, puu.getJuuri(), sMin, sMax);
        tulos = tulos.vanhempi().vanhempi().vanhempi().vanhempi();
        return tulos.getSiirto();
    }

    /**
     * Rakentaa puun alphabetassa käytettäväksi
     * @param s luotavan tason vanhempi
     * @param tilanne pelitilanne
     * @param syvyys syvyys, jota hyödynnetään rekursion katkaisussa
     */
    private void rakennaPuu(Solmu s, int syvyys, Siirto[] siirrot, int siirto) {
//        System.out.println(siirto + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        this.debugKutsut++;
        //luodaan tilanne, joka vastaa tällä hetkellä laudalla vallitsevaa tilannetta
        Lauta tilanne = new Lauta();
        tilanne.toteutaSiirrot(siirrot, siirto);
//        System.out.println(this.debugKutsut + "\n" + tilanne.laudanTulostus());
        
        //varmistetaan että annetulle tilanteelle on laskettu siirrot
        tilanne.laskeSiirrot(valkoinenko);
        
        // Ei edetä annettua syvyyttä pidemmälle
        if (syvyys <= 0) {
            this.debugLehdet++;
//            System.out.println(this.debugKutsut + " / " + this.debugLehdet);
            return;
        }
    
        //käydään läpi kaikki tilanteesta seuraavat siirrot ja lisätään
        //ne puuhun nykyisen tilanteen lapseksi
        for (int i = 0; i < tilanne.siirrot().length(); i++) {
            //Luodaan uusi solmu
            Solmu uusiSolmu = new Solmu(s, tilanne.siirrot().get(i));
            //lisätään uusi solmu lapseksi
            s.lisaaLapsi(uusiSolmu);
            this.debugLisayksia++;
            
            siirrot[siirto] = s.getLapset()[i].getSiirto();
//            System.out.println(siirto + "-- " + siirrot[siirto]);
            rakennaPuu(s.getLapset()[i], syvyys - 1, siirrot, siirto + 1);
        }
    }
}
