/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

import java.util.ArrayList;

/**
 *
 * @author juhainki
 */
public class Lauta {

    private final String merkit[] = {" ", "K", "Q", "T", "L", "S"};
    private final int vakioMus = -1;
    private final int vakioVal = 1;
    private final int vakioTyh = 0;
    private int kokox, kokoy, siirto;
    private Ruutu lauta[][];

//    private boolean mustanVuoro;
    public Lauta() {
        this(6, 6);
    }

    /**
     * @param kokox laudan leveys
     * @param kokoy laudan korkeus
     */
    public Lauta(int kokox, int kokoy) {
        this.siirto = 0;
        this.kokox = kokox;
        this.kokoy = kokoy;

        alustaLauta();
    }

    private void alustaLauta() {
        this.lauta = new Ruutu[kokox][kokoy];

        for (int x = 0; x < kokox; x++) {
            for (int y = 0; y < kokoy; y++) {
                this.lauta[x][y] = new Ruutu(x, y);
            }
        }
        alustaNappulat();
    }

    private void alustaNappulat() {
        // A-sarake (tornit)
        uudetErikoisNappulat(0, 3);

        // B-sarake (lähetit)
        uudetErikoisNappulat(1, 4);

        // C-sarake (kuninkaat)
        uudetErikoisNappulat(2, 1);

        // D-sarake (kuningattaret)
        uudetErikoisNappulat(3, 2);

        // E-sarake (lähetit)
        uudetErikoisNappulat(4, 4);

        // F-sarake (tornit)
        uudetErikoisNappulat(5, 3);

        // SOTILAAT
        for (int x = 0; x < this.kokox; x++) {
            uudetSotilaat(x);
        }
    }

    /**
     * aika: O(1), tila: O(1) Luo molemmille pelaajille anettujen parametrien
     * mukaan halutut erikoisnappulat.
     *
     * @param sarake laudan sarake
     * @param nappula nappulan tyyppi, merkit-taulukosta
     */
    private void uudetErikoisNappulat(int sarake, int nappula) {
        this.lauta[sarake][0].setVari(vakioMus);
        this.lauta[sarake][0].setNappula(nappula);
        this.lauta[sarake][this.kokoy - 1].setVari(vakioVal);
        this.lauta[sarake][this.kokoy - 1].setNappula(nappula);
    }

    /**
     * aika: O(1), tila: O(1) Luo molemmille pelaajille anettujen parametrien
     * mukaan halutut erikoisnappulat.
     *
     * @param sarake laudan sarake
     * @param nappula nappulan tyyppi, merkit-taulukosta
     */
    private void uudetSotilaat(int sarake) {
        this.lauta[sarake][1].setVari(vakioMus);
        this.lauta[sarake][1].setNappula(5);
        this.lauta[sarake][this.kokoy - 2].setVari(vakioVal);
        this.lauta[sarake][this.kokoy - 2].setNappula(5);
    }

    /**
     *
     * @param x siirrettävän palikan alkuperäinen sijainti (sarake)
     * @param y siirrettävän palikan alkuperäinen sijainti (rivi)
     * @param uusix siirrettävän palikan uusi sijainti (sarake)
     * @param uusiy siirrettävän palikan uusi sijainti (rivi)
     * @return -1: laiton siirto, 0: siirto tyhjään ruutuun, 1: syönti
     */
    public int onkoSiirtoMahdollinen(int x, int y, int uusix, int uusiy) {
        // uusi tai vanha paikka on laudan ulkopuolella
        if (uusix >= this.kokox || uusiy >= this.kokoy
                || uusix < 0 || uusiy < 0) {
            return -1;

        }
        // alkuperäisessä paikassa ei ole nappulaa
        if (lauta[x][y].onkoTyhja()) {
            return -1;
        } else {
            // onko uusi paikka tyhjä?
            if (lauta[uusix][uusiy].onkoTyhja()) {
                // Siirrytään tyhjään ruutuun
                return siirto(x, y, uusix, uusiy);
            } else {
                // Tutkitaan voiko jo varattuun ruutuun siirtyä (syödäänkö)
                if (samanVarinen(x, y, uusix, uusiy)) {
                    return -1;
                } else {
                    return siirto(x, y, uusix, uusiy);
                }
            }
        }
    }

    /**
     * aika: O(1), tila: O(1)
     *
     * @param x siirrettävän palikan alkuperäinen sijainti (sarake)
     * @param y siirrettävän palikan alkuperäinen sijainti (rivi)
     * @param uusix siirrettävän palikan uusi sijainti (sarake)
     * @param uusiy siirrettävän palikan uusi sijainti (rivi)
     * @return ovatko annettuissa ruuduissa olevat nappulat saman pelaajan
     */
    private boolean samanVarinen(int x, int y, int uusix, int uusiy) {
        int vari1 = lauta[x][y].getVari();
        int vari2 = lauta[uusix][uusiy].getVari();

        if (vari1 == vari2) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * aika: O(1), tila: O(1) Siirtää nappulan paikasta toiseen
     *
     * @param x siirrettävän palikan alkuperäinen sijainti (sarake)
     * @param y siirrettävän palikan alkuperäinen sijainti (rivi)
     * @param uusix siirrettävän palikan uusi sijainti (sarake)
     * @param uusiy siirrettävän palikan uusi sijainti (rivi)
     */
    private int siirto(int x, int y, int uusix, int uusiy) {
        int vanhanRuudunArvo = this.lauta[uusix][uusiy].getNappula() + 1;

        siirto++;
        System.out.println("Siirto " + siirto + " (" + getMerkki(x, y) + (x + 1)
                + "" + (y + 1) + " -> " + (uusix + 1) + (uusiy + 1) + ")");

        lauta[uusix][uusiy].setVari(lauta[x][y].getVari());
        lauta[uusix][uusiy].setNappula(lauta[x][y].getNappula());
        lauta[x][y].tyhjaksi();


        return vanhanRuudunArvo;
    }

    /**
     * aika: O(nm), tila: O(1), n=laudan leveys, m=laudan korkeus Tulostaa
     * laudan sisällön rivi riviltä.
     */
    public void laudanTulostus() {
        for (int y = 0; y < this.kokoy; y++) {
            for (int x = 0; x < this.kokox; x++) {
                System.out.print("|" + getMerkki(x, y));
            }
            System.out.println("|");
        }
        System.out.println("");
    }

    /**
     * aika: O(1), tila: O(1)
     *
     * @param x laudan sarake
     * @param y laudan rivi
     * @return merkki, joka kuvaa laudan sijainnissa olevaa nappulaa
     */
    private String getMerkki(int x, int y) {
        if (this.lauta[x][y].getVari() == vakioTyh) {
            return " ";
        } else if (this.lauta[x][y].getVari() == vakioMus) {
            return this.merkit[this.lauta[x][y].getNappula()].toLowerCase();
        } else {
            return this.merkit[this.lauta[x][y].getNappula()].toUpperCase();
        }
    }

    public void laskeSiirrot() {
        for (int x = 0; x < this.kokox; x++) {
            for (int y = 0; y < this.kokoy; y++) {
                if (!this.lauta[x][y].onkoSiirrotValmiina()) {
                    this.lauta[x][y].tallennaSiirrot(getSiirrot(x, y));
                }
            }
        }
    }

    private ArrayList getSiirrot(int x, int y) {
        ArrayList lista = new ArrayList();

        int vari = this.lauta[x][y].getVari();
        int nappula = this.lauta[x][y].getNappula();

        if (vari == 0) {
            return null;
        }
        // Sotilas
        if (nappula == 5) {
            if (vari == -1) {
                lista = onkoSiirtoLaillinen(x, y, x, y + 1, lista);
                lista = onkoSiirtoLaillinen(x, y, x + 1, y + 1, lista);
                lista = onkoSiirtoLaillinen(x, y, x - 1, y + 1, lista);
                System.out.println(lista.size());
            } else {
                lista = onkoSiirtoLaillinen(x, y, x, y - 1, lista);
                lista = onkoSiirtoLaillinen(x, y, x + 1, y - 1, lista);
                lista = onkoSiirtoLaillinen(x, y, x - 1, y - 1, lista);
            }
        }
        // Torni
        if (nappula == 3) {
        }
        // Lähetti
        if (nappula == 4) {
        }
        // Kuningatar
        if (nappula == 2) {
        }
        // Kuningas
        if (nappula == 1) {
        }

        return lista;
    }

    private ArrayList onkoSiirtoLaillinen(int x, int y, int uusix, int uusiy, ArrayList lista) {
        int siirto[] = {uusix, uusiy};
        // uusi tai vanha paikka on laudan ulkopuolella
        if (uusix >= this.kokox || uusiy >= this.kokoy
                || uusix < 0 || uusiy < 0) {
            return lista;
        }

        // onko uusi paikka tyhjä?
        if (lauta[uusix][uusiy].onkoTyhja()) {
            // Siirrytään tyhjään ruutuun
            lista.add(siirto);
            return lista;
        } else {
            // Tutkitaan voiko jo varattuun ruutuun siirtyä (syödäänkö)
            if (samanVarinen(x, y, uusix, uusiy)) {
                return lista;
            } else {
                lista.add(siirto);
                return lista;
            }
        }
    }
}
