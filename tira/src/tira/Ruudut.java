
package tira;

/**
 * Ruudut on taulukkorakenne joka säilöö ruutu-olioita ja osaa alustaa ne uutta
 * peliä varten.
 */
public class Ruudut {

    private Ruutu[][] taulukko;
    private int kokox, kokoy;
    private int VALKO, MUSTA;

    public Ruudut(int kokox, int kokoy, int valk, int must) {
        this.VALKO = valk;
        this.MUSTA = must;
        this.kokox = kokox;
        this.kokoy = kokoy;
        taulukko = new Ruutu[kokox][kokoy];

        for (int x = 0; x < kokox; x++) {
            for (int y = 0; y < kokoy; y++) {
                this.taulukko[x][y] = new Ruutu(x, y);
            }
        }
    }

    public Ruutu get(int x, int y) {
        if (x < 0) {
            System.out.println("testi");
        }
        return taulukko[x][y];
    }

    /**
     * Luo pelilaudan käyttäen matriisia, johon luodaan Ruutu-olioita, jotka
     * ovat tietoisia ruutujen tiedoista.
     */
    public void alustaLauta() {
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
        this.get(sarake, 0).setVari(MUSTA);
        this.get(sarake, 0).setNappula(nappula);
        this.get(sarake, this.kokoy - 1).setVari(VALKO);
        this.get(sarake, this.kokoy - 1).setNappula(nappula);
    }

    /**
     * aika: O(1), tila: O(1) Luo molemmille pelaajille sotilaat laudan
     * haluttuun sarakkeesen.
     *
     * @param sarake laudan sarake
     * @param nappula nappulan tyyppi, merkit-taulukosta
     */
    private void uudetSotilaat(int sarake) {
        this.get(sarake, 1).setVari(MUSTA);
        this.get(sarake, 1).setNappula(5);
        this.get(sarake, this.kokoy - 2).setVari(VALKO);
        this.get(sarake, this.kokoy - 2).setNappula(5);
    }
}
