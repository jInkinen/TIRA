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
    private PuuSolmu juuriPuu;
    private int puoli;
    private Lauta peli;

    public Tekoaly(int puoli, Lauta peli) {
        this.puoli = puoli;
        this.peli = peli;
        
        
        juuriPuu = new PuuSolmu(4);
    }
    
    public Siirto valitseSiirto(int syvyys) {
        siirrotPuuhun(peli, juuriPuu, syvyys);
        System.out.println("Siirrot lisätty puuhun. Suoritetaan minimax.");
        Siirto ret;
        if (puoli > 0) {
            ret = juuriPuu.minimax(true, syvyys);
        } else {
            ret = juuriPuu.minimax(false, syvyys);
        }
        peli.toteutaSiirto(ret);
        return ret;
    }

    private void siirrotPuuhun(Lauta peliTilanne, PuuSolmu vanhempi, int syvyys) {
        // Ei mennä rajattua syvyyttä pidemmälle
        if (syvyys == 0) {
            return;
        }
        
        peliTilanne.laskeSiirrot();
        
        // Lisätään tämän kerran siirrot puuhun
        Lista siirrot = peliTilanne.siirrot();
        vanhempi.lisaaLasketutSiirrot(siirrot);
        
        System.out.println(vanhempi.siirrotString());
        // Käydään läpi kaikki laudan laskemat siirrot
        for (int i = 0; i < siirrot.length(); i++) {

            
            // Luodaan uusi Lauta-olio, jottei tuohota alkuperäistä
            Lauta newLauta = new Lauta(6, 6, peliTilanne.ruudut(), peliTilanne.monesSiirto() + 1);
            
            Siirto uusiSiirto = siirrot.get(i);
            
            newLauta = newLauta.siirto(uusiSiirto);

            PuuSolmu uusiPuuSolmu = new PuuSolmu(50);
            vanhempi.liitaPuuPuuhun(uusiPuuSolmu);
            
            siirrotPuuhun(newLauta, uusiPuuSolmu, syvyys - 1);
        }
    }
}
