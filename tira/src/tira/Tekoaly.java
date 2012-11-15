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
    private int debugKutsut;
    private Lauta[] peliHistoria;
    
    public Tekoaly(int puoli, Lauta peli) {
        this.peliHistoria = new Lauta[100];
        this.debugKutsut = 0;
        this.puoli = puoli;
        this.peli = peli;


        juuriPuu = new PuuSolmu(50);
    }

    public Siirto valitseSiirto(int syvyys) {
        if (syvyys < 1) {
            throw new UnsupportedOperationException("syvyys liian pieni");
        }

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
        this.peliHistoria[syvyys] = peliTilanne;
        if (peliTilanne.equals(peliHistoria[syvyys])) {
            peliTilanne = peliHistoria[syvyys];
            System.out.println(syvyys + " asdasd");
        }
        if (syvyys == 0) {
            
            return;
        }
        this.debugKutsut++;
        System.out.println("kutsuja: " + this.debugKutsut + " syvyys: " + syvyys);
        // Ei mennä rajattua syvyyttä pidemmälle
        

        peliTilanne.laskeSiirrot();

        // Lisätään tämän kerran siirrot puuhun

        Lista siirrot = peliTilanne.siirrot();
//        System.out.println(vanhempi.siirrotString());
        // Käydään läpi kaikki laudan laskemat siirrot
        for (int i = 0; i < siirrot.length(); i++) {


            // Luodaan uusi Lauta-olio, jottei tuohota alkuperäistä

//            System.out.println("uudelle laudalle syötettävät tiedot:");
//            Ruutu[][] ruudut = peliTilanne.ruudut();
//            for (Ruutu[] r : ruudut) {
//                for (Ruutu ruutu : r) {
//                    System.out.println(ruutu);
//                }
//            }
            Lauta newLauta = new Lauta(6, 6, peliTilanne.ruudut(), peliTilanne.monesSiirto() + 1);

            Siirto uusiSiirto = siirrot.get(i);

            newLauta = newLauta.siirto(uusiSiirto);


            PuuSolmu uusiPuuSolmu = new PuuSolmu(50);
            vanhempi.liitaPuuPuuhun(uusiPuuSolmu);

//            System.out.println("Nyt tehdään rekursiivinen kutsu: \n" + newLauta.laudanTulostus());
            siirrotPuuhun(newLauta, uusiPuuSolmu, syvyys - 1);


            vanhempi.lisaaLasketutSiirrot(siirrot);
        }
    }
}
