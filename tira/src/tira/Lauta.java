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

    private final String merkit[] = {" ", "K", "Q", "T", "L", "S"};
    private final int vakioMus = -1;
    private final int vakioVal = 1;
    private final int vakioTyh = 0;
    private int kokox, kokoy, siirto;
    private Ruutu lauta[][];
    private Siirrot siirtoLaskin;

    /**
     * Oletuskonstruktori, joka luo 6x6 laudan. Toistaiseksi suositeltu versio.
     */
    public Lauta() {
        this(6, 6);
    }

    /**
     * Parametrillinen konstruktori. Voi rikkoa ominaisuuksia, jos annetaan
     * muita parametreja kuin 6, 6.
     *
     * @param kokox laudan leveys
     * @param kokoy laudan korkeus
     */
    public Lauta(int kokox, int kokoy) {
        this.siirto = 0;
        this.kokox = kokox;
        this.kokoy = kokoy;

        siirtoLaskin = new Siirrot(this);

        alustaLauta();
    }

    /**
     * Luo pelilaudan käyttäen matriisia, johon luodaan Ruutu-olioita, jotka
     * ovat tietoisia ruutujen tiedoista.
     */
    private void alustaLauta() {
        this.lauta = new Ruutu[kokox][kokoy];

        for (int x = 0; x < kokox; x++) {
            for (int y = 0; y < kokoy; y++) {
                this.lauta[x][y] = new Ruutu(x, y);
            }
        }
        alustaNappulat();
        laskeSiirrot();
    }

    /**
     * Asettaa nappulat pelilaudan oikeisiin paikkoihin.
     */
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
     * aika: O(1), tila: O(1) Luo molemmille pelaajille sotilaat laudan
     * haluttuun sarakkeesen.
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

    public void siirto(int x, int y, int uusix, int uusiy) {
        // Ei siirretä tyhjää nappulaa
        if (lauta[x][y].onkoTyhja()) {
            throw new UnsupportedOperationException("Eihän olematonta nappulaa voi siirtää!");
        }
        siirto++;
        this.lauta[x][y].ruutuunVaikutettu();
        this.lauta[uusix][uusiy].ruutuunVaikutettu();
        // TODO: Lisää systeemi, joka käy kaikki nappulat, joiden siirtomahdollisuuksiin vaikutettiin

        lauta[uusix][uusiy].setVari(lauta[x][y].getVari());
        lauta[uusix][uusiy].setNappula(lauta[x][y].getNappula());
        this.lauta[x][y].tyhjaksi();
        laskeSiirrot();
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

    /**
     * Laskee laudan ruuduissa oleville nappuloille siirrot ja tallentaa ne
     * ruudun tietorakenteisiin.
     */
    public void laskeSiirrot() {
        for (int x = 0; x < this.kokox; x++) {
            for (int y = 0; y < this.kokoy; y++) {
                if (!this.lauta[x][y].onkoSiirrotValmiina()) {
                    this.lauta[x][y].tallennaSiirrot(siirtoLaskin.ruudunSiirrot(x, y, this.lauta[x][y].getVari(), this.lauta[x][y].getNappula()));
                }
            }
        }
    }

    /**
     * aika: O(nm), tila: O(1), n=laudan leveys, m=laudan korkeus Tulostaa
     * laudan sisällön rivi riviltä.
     */
    public String laudanTulostus() {
        String ret = "";
        for (int y = 0; y < this.kokoy; y++) {
            for (int x = 0; x < this.kokox; x++) {
                ret = ret + "|" + getMerkki(x, y);
            }
            ret = ret + "|\n";
        }
        return ret;
    }

    public String tulostaSiirtojenMaara() {
        String ret = "";
        for (int y = 0; y < this.kokoy; y++) {
            for (int x = 0; x < this.kokox; x++) {
                ret = ret + "|" + this.lauta[x][y].siirtojenMaara();
            }
            ret = ret + "|\n";
        }
        return ret;
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
    public boolean samanVarinen(int x, int y, int uusix, int uusiy) {
        int vari1 = lauta[x][y].getVari();
        int vari2 = lauta[uusix][uusiy].getVari();

        if (vari1 == vari2) {
            return true;
        } else {
            return false;
        }
    }

    public boolean onkoTyhja(int x, int y) {
        return lauta[x][y].onkoTyhja();
    }
    
        /**
     * Tarkistaa onko ruutu laudalla.
     *
     * @param nykyinenX Ruudun rivi
     * @param nykyinenY Ruudun sarake
     * @return onko ruutu laudan ulkopuolella
     */
    public boolean onkoLaudanUlkopuolella(int nykyinenX, int nykyinenY) {
        if (nykyinenX >= this.kokox || nykyinenX < 0) {
            return true;
        }
        if (nykyinenY >= this.kokoy || nykyinenY < 0) {
            return true;
        }
        return false;
    }
}
