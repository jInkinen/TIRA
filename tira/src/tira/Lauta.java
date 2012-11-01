/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

/**
 *
 * @author juhainki
 */
public class Lauta {
    private final int vakioMus = -1;
    private final int vakioVal = 1;
    private final int vakioTyh = 0;
    
    private int kokox, kokoy;
    private boolean mustanVuoro;
    private int lauta[][][];

    private final String merkit[] = {" ", "K", "Q", "T", "L", "S"};

    public Lauta() {
        this(6, 6);
    }

    /**
     * @param kokox laudan leveys
     * @param kokoy laudan korkeus
     */
    public Lauta(int kokox, int kokoy) {
        this.kokox = kokox;
        this.kokoy = kokoy;

        mustanVuoro = false;

        alustaLauta();
    }

    private void alustaLauta() {
        this.lauta = new int[kokox][kokoy][2];
        alustaNappulat();
    }

    private void alustaNappulat() {
        // A-sarake (tornit)
        uudetErikoisNappulat(0, 3);

        // B-sarake (lähetit)
        this.lauta[1][0][0] = vakioMus;
        this.lauta[1][0][1] = 4;
        this.lauta[1][5][0] = vakioVal;
        this.lauta[1][5][1] = 4;

        // C-sarake (kuninkaat)
        this.lauta[2][0][0] = vakioMus;
        this.lauta[2][0][1] = 1;
        this.lauta[2][5][0] = vakioVal;
        this.lauta[2][5][1] = 1;

        // D-sarake (kuningattaret)
        this.lauta[3][0][0] = vakioMus;
        this.lauta[3][0][1] = 2;
        this.lauta[3][5][0] = vakioVal;
        this.lauta[3][5][1] = 2;

        // E-sarake (lähetit)
        this.lauta[4][0][0] = vakioMus;
        this.lauta[4][0][1] = 4;
        this.lauta[4][5][0] = vakioVal;
        this.lauta[4][5][1] = 4;

        // F-sarake (tornit)
        this.lauta[5][0][0] = vakioMus;
        this.lauta[5][0][1] = 3;
        this.lauta[5][5][0] = vakioVal;
        this.lauta[5][5][1] = 3;


        for (int x = 0; x < this.kokox; x++) {
            // SOTILAAT
            // Mustan sotilaat
            this.lauta[x][1][0] = vakioMus;
            this.lauta[x][1][1] = 5;
            // Valk.Sotilaat
            this.lauta[x][4][0] = vakioVal;
            this.lauta[x][4][1] = 5;

            // Asetetaan nollat tyhjille ruuduille
            for (int y = 2; y < 4; y++) {
                this.lauta[x][y][0] = vakioTyh;
            }
        }
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
        
        if (lauta[x][y][0] == vakioTyh) { // alkuperäisessä paikassa ei ole nappulaa
            return -1;
        } else { // alkuperäisessä paikassa on nappula
            if (lauta[x][y][0] == vakioVal) { //valkoinen
                if (lauta[uusix][uusiy][0] == vakioMus) {
                    siirto(x, y, uusix, uusiy);
                    return 1;
                } else if (lauta[uusix][uusiy][0] == vakioVal) {
                    return -1;
                } else {
                    siirto(x, y, uusix, uusiy);
                    return 0;
                }
            } else if (lauta[x][y][0] == vakioMus) { //musta
                if (lauta[uusix][uusiy][0] == vakioVal) {
                    siirto(x, y, uusix, uusiy);
                    return 1;
                } else if (lauta[uusix][uusiy][0] == vakioMus) {
                    return -1;
                } else {
                    siirto(x, y, uusix, uusiy);
                    return 0;
                }
            } else {
                return -1;
            }
            
        }
    }

    /**
     * aika: O(1), tila: O(1)
     * Siirtää nappulan paikasta toiseen
     * @param x siirrettävän palikan alkuperäinen sijainti (sarake)
     * @param y siirrettävän palikan alkuperäinen sijainti (rivi)
     * @param uusix siirrettävän palikan uusi sijainti (sarake)
     * @param uusiy siirrettävän palikan uusi sijainti (rivi)
     */
    private void siirto(int x, int y, int uusix, int uusiy) {
        lauta[uusix][uusiy][0] = lauta[x][y][0];
        lauta[uusix][uusiy][1] = lauta[x][y][1];
//        System.out.println(lauta[x][y][0] + " + " + lauta[x][y][1]);
        lauta[x][y][0] = vakioTyh;
    }

    /**
     * aika: O(nm), tila: O(1), n=laudan leveys, m=laudan korkeus
     * Tulostaa laudan sisällön rivi riviltä.
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
     * @param x laudan sarake
     * @param y laudan rivi
     * @return merkki, joka kuvaa laudan sijainnissa olevaa nappulaa
     */
    private String getMerkki(int x, int y) {
        if (this.lauta[x][y][0] == vakioTyh) {
            return " ";
        } else if (this.lauta[x][y][0] == vakioMus){
            return this.merkit[this.lauta[x][y][1]].toLowerCase();
        } else {
            return this.merkit[this.lauta[x][y][1]].toUpperCase();
        }
    }

    /**
     * aika: O(1), tila: O(1)
     * Luo molemmille pelaajille anettujen
     * parametrien mukaan halutut erikoisnappulat.
     * @param sarake laudan sarake
     * @param nappula nappulan tyyppi, merkit-taulukosta
     */
    private void uudetErikoisNappulat(int sarake, int nappula) {
        this.lauta[sarake][0][0] = vakioMus;
        this.lauta[sarake][0][1] = nappula;
        this.lauta[sarake][5][0] = vakioVal;
        this.lauta[sarake][5][1] = nappula;
    }
}
