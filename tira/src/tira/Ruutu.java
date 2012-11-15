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
    private Lista omatSiirrot;
    private boolean siirrotLaskettu;

    /**
     * Luo uuden Ruutu-olion, joka tietää omat koordinaattinsa.
     *
     * @param x Ruudun sijainti x-akselin suhteen
     * @param y Ruudun sijainti y-akselin suhteen
     */
    Ruutu(int x, int y) {
        this.x = x;
        this.y = y;
        this.tyhja = 0;

        nappula = 0;
        vari = 0;

        omatSiirrot = new Lista();
        siirrotLaskettu = false;
    }

    /**
     *
     * @return Kertoo ruudun värin. 0, jos tyhjä
     */
    public int getVari() {
        return this.vari;
    }

    /**
     * Asettaa ruudulle värin.
     *
     * @param vari uusi väri (1, 0, -1)
     */
    public void setVari(int vari) {
        this.vari = vari;
    }

    /**
     *
     * @return Kertoo ruudussa olevan nappulan tyypin.
     */
    public int getNappula() {
        return this.nappula;
    }

    /**
     * Tallentaa nappulan tyypin
     *
     * @param nappula nappulan tyyppi
     */
    public void setNappula(int nappula) {
        this.nappula = nappula;
    }

    /**
     * Tyhjentää ruudun tiedot.
     */
    public void tyhjaksi() {
        this.vari = this.tyhja;
        this.nappula = 0;
    }

    /**
     *
     * @return Onko ruutu tyhjä
     */
    public boolean onkoTyhja() {
        if (this.vari == this.tyhja) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Tyhjentää ruudun muistissa olevat siirrot, jos ruutuun on vaikutettu.
     */
    public void ruutuunVaikutettu() {
        this.omatSiirrot.clear();
        this.siirrotLaskettu = false;
    }

    /**
     *
     * @return onko ruudun siirrot jo laskettu
     */
    public boolean onkoSiirrotValmiina() {
        return this.siirrotLaskettu;
    }

    /**
     * Tallentaa ruudulle uudet siirrot
     *
     * @param siirrot lista, jossa on ruudun mahdolliset siirrot
     */
    public void tallennaSiirrot(Lista siirrot) {
        this.omatSiirrot = siirrot;
        //this.siirrotLaskettu = true;
        
        // TODO kehitä systeemi, jolla varmistetaan että siirrot lasketaan
        // vaikka booleania käytetään
    }

    public Lista getSiirrot() {
        return omatSiirrot;
    }
    
    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ") V:" + this.vari + " N:" + this.nappula;
    }
}
