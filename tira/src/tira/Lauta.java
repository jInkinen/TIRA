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

    /**
     * Oletuskonstruktori, joka luo 6x6 laudan. Toistaiseksi suositeltu versio.
     */
    public Lauta() {
        this(6, 6);
    }

    /**
     * Parametrillinen konstruktori. Voi rikkoa ominaisuuksia, jos annetaan muita parametreja kuin 6, 6.
     * @param kokox laudan leveys
     * @param kokoy laudan korkeus
     */
    public Lauta(int kokox, int kokoy) {
        this.siirto = 0;
        this.kokox = kokox;
        this.kokoy = kokoy;

        alustaLauta();
    }

    /**
     * Luo pelilaudan käyttäen matriisia, johon luodaan Ruutu-olioita,
     * jotka ovat tietoisia ruutujen tiedoista.
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
     * aika: O(1), tila: O(1) Luo molemmille pelaajille sotilaat laudan haluttuun sarakkeesen.
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

    /**
     * Laskee laudan ruuduissa oleville nappuloille siirrot ja tallentaa ne ruudun tietorakenteisiin.
     */
    public void laskeSiirrot() {
        for (int x = 0; x < this.kokox; x++) {
            for (int y = 0; y < this.kokoy; y++) {
                if (!this.lauta[x][y].onkoSiirrotValmiina()) {
                    this.lauta[x][y].tallennaSiirrot(getSiirrot(x, y));
                }
            }
        }
    }

    /**
     * Laskee yksittäisen ruudun mahdolliset siirrot.
     * @param x Nappulan rivi
     * @param y Nappula sarake
     * @return Lista, jossa on kyseisen ruudun kaikki toteutettavat siirrot.
     */
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
                lista = lisaaLaillinenSiirto(x, y, x, y + 1, lista, true, false, true);
                lista = lisaaLaillinenSiirto(x, y, x + 1, y + 1, lista, false, true, true);
                lista = lisaaLaillinenSiirto(x, y, x - 1, y + 1, lista, false, true, true);
            } else {
                lista = lisaaLaillinenSiirto(x, y, x, y - 1, lista, true, false, true);
                lista = lisaaLaillinenSiirto(x, y, x + 1, y - 1, lista, false, true, true);
                lista = lisaaLaillinenSiirto(x, y, x - 1, y - 1, lista, false, true, true);
            }
        }
        // Torni
        if (nappula == 3) {
            lista = torninSiirrot(x, y, lista);
        }
        // Lähetti
        if (nappula == 4) {
            lista = lahetinSiirrot(x, y, lista);
        }
        // Kuningatar
        if (nappula == 2) {
            lista = torninSiirrot(x, y, lista);
            lista = lahetinSiirrot(x, y, lista);
        }
        // Kuningas
        if (nappula == 1) {
            lista = lisaaLaillinenSiirto(x, y, x, y + 1, lista, true, true, false);
            lista = lisaaLaillinenSiirto(x, y, x + 1, y + 1, lista, true, true, false);
            lista = lisaaLaillinenSiirto(x, y, x - 1, y + 1, lista, true, true, false);
            lista = lisaaLaillinenSiirto(x, y, x, y - 1, lista, true, true, false);
            lista = lisaaLaillinenSiirto(x, y, x + 1, y - 1, lista, true, true, false);
            lista = lisaaLaillinenSiirto(x, y, x - 1, y - 1, lista, true, true, false);
            lista = lisaaLaillinenSiirto(x, y, x - 1, y, lista, true, true, false);
            lista = lisaaLaillinenSiirto(x, y, x + 1, y, lista, true, true, false);
        }

        return lista;
    }

    /**
     * Laskee lähetille mahdolliset siirrot
     * @param x Nappulan rivi
     * @param y Nappulan sarake
     * @param lista lista, johon siirrot lisätään
     * @return lista, jossa on kaikki lailliset siirrot
     */
    private ArrayList lahetinSiirrot(int x, int y, ArrayList lista) {
        for (int siirronPituus = 1; siirronPituus < this.kokoy; siirronPituus++) { 
            int nykyinenX = x + siirronPituus;
            int nykyinenY = y + siirronPituus;
            
            if (onkoLaudanUlkopuolella(nykyinenX, nykyinenY)) {
                // Törmättiin laudan reunaan
                break;
            }
            
            lista = lisaaLaillinenSiirto(x, y, nykyinenX, nykyinenY, lista, true, true, true);
            
            if (this.lauta[nykyinenX][nykyinenY].getVari() != 0) {
                // Törmättiin nappulaan, ei etsitä kauemmaksi
                break;
            }
        }
        
        for (int siirronPituus = 1; siirronPituus < this.kokoy; siirronPituus++) { 
            int nykyinenX = x + siirronPituus;
            int nykyinenY = y - siirronPituus;
            
            if (onkoLaudanUlkopuolella(nykyinenX, nykyinenY)) {
                // Törmättiin laudan reunaan
                break;
            }
            
            lista = lisaaLaillinenSiirto(x, y, nykyinenX, nykyinenY, lista, true, true, true);
            
            if (this.lauta[nykyinenX][nykyinenY].getVari() != 0) {
                // Törmättiin nappulaan, ei etsitä kauemmaksi
                break;
            }
        }
        
        for (int siirronPituus = 1; siirronPituus < this.kokoy; siirronPituus++) { 
            int nykyinenX = x - siirronPituus;
            int nykyinenY = y - siirronPituus;
            
            if (onkoLaudanUlkopuolella(nykyinenX, nykyinenY)) {
                // Törmättiin laudan reunaan
                break;
            }
            
            lista = lisaaLaillinenSiirto(x, y, nykyinenX, nykyinenY, lista, true, true, true);
            
            if (this.lauta[nykyinenX][nykyinenY].getVari() != 0) {
                // Törmättiin nappulaan, ei etsitä kauemmaksi
                break;
            }
        }
        
        for (int siirronPituus = 1; siirronPituus < this.kokoy; siirronPituus++) { 
            int nykyinenX = x - siirronPituus;
            int nykyinenY = y + siirronPituus;
            
            if (onkoLaudanUlkopuolella(nykyinenX, nykyinenY)) {
                // Törmättiin laudan reunaan
                break;
            }
            
            lista = lisaaLaillinenSiirto(x, y, nykyinenX, nykyinenY, lista, true, true, true);
            
            if (this.lauta[nykyinenX][nykyinenY].getVari() != 0) {
                // Törmättiin nappulaan, ei etsitä kauemmaksi
                break;
            }
        }
        
        return lista;
    }

    /**
     * Laskee tornille mahdolliset siirrot
     * @param x Nappulan rivi
     * @param y Nappulan sarake
     * @param lista lista, johon siirrot lisätään
     * @return lista, jossa on kaikki lailliset siirrot
     */
    private ArrayList torninSiirrot(int x, int y, ArrayList lista) {
        for (int siirronPituus = 1; siirronPituus < this.kokoy; siirronPituus++) { 
            int nykyinenX = x;
            int nykyinenY = y + siirronPituus;
            
            if (onkoLaudanUlkopuolella(nykyinenX, nykyinenY)) {
                // Törmättiin laudan reunaan
                break;
            }
            
            lista = lisaaLaillinenSiirto(x, y, nykyinenX, nykyinenY, lista, true, true, true);
            
            if (this.lauta[nykyinenX][nykyinenY].getVari() != 0) {
                // Törmättiin nappulaan, ei etsitä kauemmaksi
                break;
            }
        }
        
        for (int siirronPituus = 1; siirronPituus < this.kokoy; siirronPituus++) { 
            int nykyinenX = x;
            int nykyinenY = y - siirronPituus;
            
            if (onkoLaudanUlkopuolella(nykyinenX, nykyinenY)) {
                // Törmättiin laudan reunaan
                break;
            }
            
            lista = lisaaLaillinenSiirto(x, y, nykyinenX, nykyinenY, lista, true, true, true);
            
            if (this.lauta[nykyinenX][nykyinenY].getVari() != 0) {
                // Törmättiin nappulaan, ei etsitä kauemmaksi
                break;
            }
        }
        
        for (int siirronPituus = 1; siirronPituus < this.kokoy; siirronPituus++) { 
            int nykyinenX = x + siirronPituus;
            int nykyinenY = y;
            
            if (onkoLaudanUlkopuolella(nykyinenX, nykyinenY)) {
                // Törmättiin laudan reunaan
                break;
            }
            
            lista = lisaaLaillinenSiirto(x, y, nykyinenX, nykyinenY, lista, true, true, true);
            
            if (this.lauta[nykyinenX][nykyinenY].getVari() != 0) {
                // Törmättiin nappulaan, ei etsitä kauemmaksi
                break;
            }
        }
        
        for (int siirronPituus = 1; siirronPituus < this.kokoy; siirronPituus++) { 
            int nykyinenX = x - siirronPituus;
            int nykyinenY = y;
            
            if (onkoLaudanUlkopuolella(nykyinenX, nykyinenY)) {
                // Törmättiin laudan reunaan
                break;
            }
            
            lista = lisaaLaillinenSiirto(x, y, nykyinenX, nykyinenY, lista, true, true, true);
            
            if (this.lauta[nykyinenX][nykyinenY].getVari() != 0) {
                // Törmättiin nappulaan, ei etsitä kauemmaksi
                break;
            }
        }
        
        return lista;
    }

    /**
     * Lisää listaan uuden siirron, jos se on laillinen. Muutoin palautetaan alkuperäinen lista.
     * @param x Nappulan rivi
     * @param y Nappulan sarake
     * @param uusix Uuden paikan rivi
     * @param uusiy Uuden paikan sarake
     * @param lista lista, jossa on jo lasketut siirrot
     * @param saaSiirtyaTyhjaan Saako nappula siirtyä tyhjään ruutuun laskettavalla siirrolla vai pitääkö ruudussa olla syötävä nappula? Käytetään sotilaan syöntisiirtoihin.
     * @param saakoSyoda Saako nappula syödä laskettavalla siirrolla. Normaalisti true, mutta sotilaan eteenpäin siirtyminen false.
     * @param saakoOllaVaarassa Normaalisti true, mutta kuninkaalla false. Varmistetaan, ettei kuninkaan liikkuminen altista sitä shakille.
     * @return lista, jossa on laskettu siirto ja aikaisemmat lasketut siirrot.
     */
    private ArrayList lisaaLaillinenSiirto(int x, int y, int uusix, int uusiy, ArrayList lista, boolean saaSiirtyaTyhjaan, boolean saakoSyoda, boolean saakoOllaVaarassa) {
        int siirto[] = {uusix, uusiy};
        // uusi tai vanha paikka on laudan ulkopuolella
        if (uusix >= this.kokox || uusiy >= this.kokoy
                || uusix < 0 || uusiy < 0) {
            return lista;
        }
        
        // TODO tarkastus, ettei siirto aseta kuningasta vaaraan.
        
        if (!saakoOllaVaarassa) {
            // TODO tarkastus siirrolle, jottei kuningas aseta itseään shakkiin.
            
            
        }

        // onko uusi paikka tyhjä?
        if (lauta[uusix][uusiy].onkoTyhja()) {
            // Siirrytään tyhjään ruutuun
            if (saaSiirtyaTyhjaan) {
                lista.add(siirto);
            }
            return lista;
        } else {
            // Tutkitaan voiko jo varattuun ruutuun siirtyä (syödäänkö)
            if (samanVarinen(x, y, uusix, uusiy)) {
                // Ruudussa saman värinen
                return lista;
            } else {
                // Syödään
                if (saakoSyoda) {
                    // Lisätään syöntisiirto vain jos syöminen on sallittua nappulalle tällä siirrolla
                    lista.add(siirto);
                }
                return lista;
            }
        }
    }

    /**
     * Tarkistaa onko ruutu laudalla.
     * @param nykyinenX Ruudun rivi
     * @param nykyinenY Ruudun sarake
     * @return onko ruutu laudan ulkopuolella
     */
    private boolean onkoLaudanUlkopuolella(int nykyinenX, int nykyinenY) {
        if (nykyinenX >= this.kokox || nykyinenX < 0) {
            return true;
        }
        if (nykyinenY >= this.kokoy || nykyinenY < 0) {
            return true;
        }
        return false;
    }
}
