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
        laskeSiirrot(peli, juuriPuu, syvyys);
        
        Siirto ret;
        if (puoli > 0) {
            ret = juuriPuu.minimax(true, syvyys);
        } else {
            ret = juuriPuu.minimax(false, syvyys);
        }
        return ret;
    }

    private void laskeSiirrot(Lauta peliTilanne, PuuSolmu vanhempi, int syvyys) {
        if (syvyys == 0) {
            return;
        }
        Lista siirrot = peliTilanne.siirrot();
        vanhempi.lisaaLasketutSiirrot(siirrot);
        
        // Käydään läpi kaikki laudan laskemat siirrot
        for (int i = 0; i < siirrot.length(); i++) {
            Siirto uusiSiirto = siirrot.get(i);
            Lauta uusiLauta = peliTilanne.siirto(uusiSiirto);
            PuuSolmu uusiPuuSolmu = new PuuSolmu(50);
            vanhempi.liitaPuuPuuhun(uusiPuuSolmu);
            // Rekursiivinen kutsu
            laskeSiirrot(uusiLauta, uusiPuuSolmu, syvyys - 1);
        }
        System.out.println("Siirrot laskettu");
    }
}
