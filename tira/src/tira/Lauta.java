/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

/**
 *
 * @author juhainki
 */
public class Lauta implements Comparable{

    private final String merkit[] = {" ", "K", "Q", "T", "L", "S", "H"};
    private final int heurestiikka[] = {0, 2000, 9, 5, 3, 1, 3};
    private final int vakioMus = -1;
    private final int vakioVal = 1;
    private final int vakioTyh = 0;
    private int kokox, kokoy, siirto;
    private Ruutu[][] lauta;
    private SiirtoLaskuri siirtoLaskin;

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

        siirtoLaskin = new SiirtoLaskuri(this);

        alustaLauta();
    }
    
    /**
     * Konstruktori, joka sallii manuaalisesti asetetun tilanteen luomisen.
     * @param kokox laudan leveys
     * @param kokoy laudan korkeus
     * @param ruudut Ruutu[][], jossa on kaikkien ruutujen tiedot
     * @param simuloiSiirto monekso vuoro menossa
     */
    public Lauta(int kokox, int kokoy, Ruutu[][] ruudut, int siirto) {
        this.siirto = siirto;
        this.kokox = kokox;
        this.kokoy = kokoy;

        siirtoLaskin = new SiirtoLaskuri(this);
        
        this.lauta = ruudut;
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

    /**
     * Simuloi siirron suorittamisen
     * @param s Siirto, joka simuloidaan
     * @return Palautetaan pelilauta, jossa simuloiSiirto on toteutettu
     */
    public Lauta simuloiSiirto(Siirto s) {
        // luodaan kopio laudasta
        Lauta ret = new Lauta(this.kokox, this.kokoy, this.ruudut(), this.siirto);
        //Toteutetaan simuloiSiirto
        ret.toteutaSiirto(s);
        
        
        return ret;
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

    /**
     * Kertoo onko laudan ruutu tyhjä
     * @param x
     * @param y
     * @return onko tyhjä?
     */
    public boolean onkoTyhja(int x, int y) {
//        System.out.println("----- ONKO TYHJÄ??? " + x + "," + y + " "+ lauta[x][y] + " > " + lauta[x][y].onkoTyhja());
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

    /**
     * Laskee heurestiikan mukaisen arvon siirrolle
     * @param x1 nappula ykkösen x
     * @param y1 nappula ykkösen y
     * @param x2 nappula kakkosen x
     * @param y2 nappula kakkosen y
     * @return 
     */
    public int nappulanArvo(int x, int y) {
        int nappula = this.lauta[x][y].getNappula();
        
        return this.heurestiikka[nappula];
    }

    /**
     * 
     * @return Kertoo pelilaudan kaikki mahdolliset siirrot
     */
    public Lista siirrot() {
        Lista siirrot = new Lista();
        
        for (int x = 0; x < this.kokox; x++) {
            for (int y = 0; y < this.kokoy; y++) {
                Lista apuri = this.lauta[x][y].getSiirrot();
                for (int i = 0; i < apuri.length(); i++) {
                    siirrot.add(apuri.get(i));
                }
            }
        }
        return siirrot;
    }

    /**
     * 
     * @return Kertoo onko nyt alkoisen vuoro
     */
    public boolean valkoisenVuoro() {
        if (this.siirto % 2 == 0) {
            return true;
        }
        return false;
    }
    
    /**
     * 
     * @return kertoo käyttäjälle nykyisen pelilaudan ruudut
     */
    public Ruutu[][] ruudut() {
        return this.lauta;
    }
    
    /**
     * Kertoo monesko siirtovuoro pelisssä on.
     * @return 
     */
    public int monesSiirto() {
        return this.siirto;
    }

    /**
     * Muuttaa laudan tilannetta suorittamalla annetun siirron.
     * @param s toteutettavaksi annettava siirto
     */
    public void toteutaSiirto(Siirto s) {
        int origX = s.alkuperainenPaikka()[0];
        int origY = s.alkuperainenPaikka()[1];
        int uusiX = s.uusiPaikka()[0];
        int uusiY = s.uusiPaikka()[1];
        
        Ruutu orig = this.lauta[origX][origY];
        Ruutu uusi = this.lauta[uusiX][uusiY];
        
        //Annetaan uudelle ruudulle vanhan tiedot
        uusi.setVari(orig.getVari());
        uusi.setNappula(orig.getNappula());
        //Tyhjennetään alkuperäisen ruudun tiedot
        orig.tyhjaksi();
    }

    /**
     * comparablen toteutus vaatii compareTo:n olemassaolon.
     * Jotta oliota voi laittaa jonoon, javan jono-toteutus vaatii, että olio on comparable
     * @param o verrattava olio
     * @return vertailuku
     */
    @Override
    public int compareTo(Object o) {
        return -1;
    }
    
}
