/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

/**
 *
 * @author juhainki
 */
public class Ruutu {

    private final int x, y, tyhja;
    private int nappula, vari;
//    private int siirrot[][];

    Ruutu(int x, int y) {
        this.x = x;
        this.y = y;
        this.tyhja = 0;
        
        nappula = 0;
        vari = 0;
    }

    public int getVari() {
        return this.vari;
    }

    public void setVari(int vari) {
        this.vari = vari;
    }

    public int getNappula() {
        return this.nappula;
    }

    public void setNappula(int nappula) {
        this.nappula = nappula;
    }

    void tyhjaksi() {
        this.vari = this.tyhja;
        this.nappula = 0;
    }

    boolean onkoTyhja() {
        if (this.vari == this.tyhja) {
            return true;
        } else {
            return false;
        }
    }
    
}
