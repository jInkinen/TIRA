package tira;

/**
 * Lauta toimii logiikan perusluokkana, joka säätelee mm. siirtojen laskemista,
 * tilanteen tallentamista ja heurestiikkaa.
 */
public class Lauta {

    public final int vakioMus = -1;
    public final int vakioVal = 1;
    public final int vakioTyh = 0;
    private final String merkit[] = {" ", "K", "Q", "T", "L", "S", "H"};
    private final int heurestiikka[] = {0, 20000, 90, 50, 30, 10, 30};
    private int kokox, kokoy;
    private Ruudut lauta;
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

        this.kokox = kokox;
        this.kokoy = kokoy;

        siirtoLaskin = new SiirtoLaskuri(this);

        lauta = new Ruudut(kokox, kokoy, vakioVal, vakioMus);
        lauta.alustaLauta();
    }

    /**
     * Konstruktori, joka sallii manuaalisesti asetetun tilanteen luomisen.
     *
     * @param kokox laudan leveys
     * @param kokoy laudan korkeus
     * @param ruudut Ruutu[][], jossa on kaikkien ruutujen tiedot
     * @param simuloiSiirto monekso vuoro menossa
     */
    public Lauta(int kokox, int kokoy, Ruudut ruudut) {

        this.kokox = kokox;
        this.kokoy = kokoy;

        siirtoLaskin = new SiirtoLaskuri(this);

        this.lauta = ruudut;
    }

    /**
     * @param x laudan sarake
     * @param y laudan rivi
     * @return merkki, joka kuvaa laudan sijainnissa olevaa nappulaa
     */
    private String getMerkki(int x, int y) {
        if (this.lauta.get(x, y).getVari() == vakioTyh) {
            return " ";
        } else if (this.lauta.get(x, y).getVari() == vakioMus) {
            return this.merkit[this.lauta.get(x, y).getNappula()].toLowerCase();
        } else {
            return this.merkit[this.lauta.get(x, y).getNappula()].toUpperCase();
        }
    }

    /**
     * Laskee laudan ruuduissa oleville nappuloille siirrot ja tallentaa ne
     * ruudun tietorakenteisiin.
     */
    public void laskeSiirrot(boolean valkoinen) {
        for (int x = 0; x < this.kokox; x++) {
            for (int y = 0; y < this.kokoy; y++) {
                int vari = this.lauta.get(x, y).getVari();
                int nappula = this.lauta.get(x, y).getNappula();
                this.lauta.get(x, y).tallennaSiirrot(siirtoLaskin.ruudunSiirrot(valkoinen, x, y, vari, nappula));
            }
        }
    }

    /**
     * Tulostaa laudan sisällön rivi riviltä.
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
     * @param x siirrettävän palikan alkuperäinen sijainti (sarake)
     * @param y siirrettävän palikan alkuperäinen sijainti (rivi)
     * @param uusix siirrettävän palikan uusi sijainti (sarake)
     * @param uusiy siirrettävän palikan uusi sijainti (rivi)
     * @return ovatko annettuissa ruuduissa olevat nappulat saman pelaajan
     */
    public boolean samanVarinen(int x, int y, int uusix, int uusiy) {
        int vari1 = lauta.get(x, y).getVari();
        int vari2 = lauta.get(uusix, uusiy).getVari();

        if (vari1 == vari2) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Kertoo onko laudan ruutu tyhjä
     *
     * @param x
     * @param y
     * @return onko tyhjä?
     */
    public boolean onkoTyhja(int x, int y) {
        return lauta.get(x, y).onkoTyhja();
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
     *
     * @param x1 nappula ykkösen x
     * @param y1 nappula ykkösen y
     * @param x2 nappula kakkosen x
     * @param y2 nappula kakkosen y
     * @return
     */
    public int nappulanArvo(int x, int y) {
        int nappula = this.lauta.get(x, y).getNappula();

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
                Lista apuri = this.lauta.get(x, y).getSiirrot();
                for (int i = 0; i < apuri.length(); i++) {
                    siirrot.add(apuri.get(i));
                }
            }
        }
        return siirrot;
    }

    /**
     *
     * @return kertoo käyttäjälle nykyisen pelilaudan ruudut
     */
    public Ruudut ruudut() {
        return this.lauta;
    }

    /**
     * Muuttaa laudan tilannetta suorittamalla annetun siirron.
     *
     * @param s toteutettavaksi annettava siirto
     */
    public void toteutaSiirto(Siirto s) {
        if (s == null) {
            throw new UnsupportedOperationException("siirto on null");
        }

        int origX = s.alkuperainenPaikka()[0];
        int origY = s.alkuperainenPaikka()[1];
        int uusiX = s.uusiPaikka()[0];
        int uusiY = s.uusiPaikka()[1];

        Ruutu orig = this.lauta.get(origX, origY);
        Ruutu uusi = this.lauta.get(uusiX, uusiY);

        //Annetaan uudelle ruudulle vanhan tiedot
        uusi.setVari(orig.getVari());
        uusi.setNappula(orig.getNappula());
        //Tyhjennetään alkuperäisen ruudun tiedot
        orig.tyhjaksi();
    }

    /**
     * Toteuttaa kaikki siirrot, jotka sille annetaan taulukon järjestyksessä.
     * Mahdollistaa pelitilanteeseen kiinnipääsemisen ilman tilanteen
     * säilömistä. Aikavaativuus O(n), mutta tilaa menee vähemmän, kuin jokaisen
     * pelitilanteen säilömiseen.
     *
     * @param siirrot Taulukko, jossa on kaikki toteutettavat siirrot
     * järjestyksessä
     * @param pituus kuinka monta siirtoa taulukosta toteutetaan
     */
    public void toteutaSiirrot(Siirto[] siirrot, int pituus) {
        for (int i = 0; i < pituus; i++) {
            this.toteutaSiirto(siirrot[i]);
        }
    }
}
