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
    private int kokox, kokoy;
    private boolean mustanVuoro;
    private int lauta[][];
    
    
    // TODO: refaktoroi: ryhmittele merkit (M/V/-)
    private String merkit[] = {" ", "k", "q", "t", "l", "s", "K", "Q", "T", "L", "S"};

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
        this.lauta = new int[kokox][kokoy];
        alustaNappulat();
    }
    
    private void alustaNappulat() {
        for (int x = 0; x < this.kokox; x++) {
            // SOTILAAT
            // Mustan sotilaat
            this.lauta[x][1] = 10;
            // Valk.Sotilaat
            this.lauta[x][this.kokoy-2] = 5;
            
            // Erikoisnappulat
            if (x == 0) {
                // A-sarakkeen tornit
                this.lauta[x][0] = 8;
                this.lauta[x][this.kokoy-1] = 3;
            }
            if (x == 1) {
                // B-sarakkeen lähetit
                this.lauta[x][0] = 9;
                this.lauta[x][this.kokoy-1] = 4;
            }
            if (x == 2) {
                // C-sarakkeen kuninkaat
                this.lauta[x][0] = 6;
                this.lauta[x][this.kokoy-1] = 1;
            }
            if (x == 3) {
                // D-sarakkeen kuningattaret
                this.lauta[x][0] = 7;
                this.lauta[x][this.kokoy-1] = 2;
            }
            if (x == 4) {
                // E-sarakkeen lähetit
                this.lauta[x][0] = 9;
                this.lauta[x][this.kokoy-1] = 4;
            }
            if (x == 5) {
                // F-sarakkeen tornit
                this.lauta[x][0] = 8;
                this.lauta[x][this.kokoy-1] = 3;
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
        // uusi paikka on laudan ulkopuolella
        if (uusix >= this.kokox || uusiy >= this.kokoy ||
                uusix < 0 || uusiy < 0) {
            return -1;
        }
        // alkuperäisessä paikassa ei ole nappulaa
        if (lauta[x][y] == 0) {
            return -1;
        }
        // uusi paikka varattu
        if (lauta[uusix][uusiy] > 0) {
            // valkoinen
            if (lauta[x][y] <= 5) {
                // paikalla musta: syödään
                if (lauta[uusix][uusiy] > 5) {
                    siirto(x, y, uusix, uusiy);
                    return 1;
                } else { // paikalla oma nappula
                    return -1;
                }
            }
            // musta
            if (lauta[x][y] > 5) {
                // paikalla valkoinen: syödään
                if (lauta[uusix][uusiy] <= 5) {
                    siirto(x, y, uusix, uusiy);
                    return 1;
                } else { // paikalla oma nappula
                    return -1;
                }
            }      
            return -1;
        }
        // paikka tyhjä: siirrytään
        siirto(x, y, uusix, uusiy);
        return 0;
    }
    
    private void siirto(int x, int y, int uusix, int uusiy) {
        lauta[uusix][uusiy] = lauta[x][y];
        lauta[x][y] = 0;
    }

    /*
     * Tulostaa laudan tilanteen
     */
    public void laudanTulostus() {
        for (int y = 0; y < this.kokox; y++) {
            for (int x = 0; x < this.kokoy; x++) {
                System.out.print("|" + this.merkit[this.lauta[x][y]]);
            }
            System.out.println("|");
        }
        System.out.println("");
    }
    
    
}
