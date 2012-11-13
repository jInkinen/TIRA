/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

/**
 *
 * @author juhainki
 */
public class Siirrot {

    private int kokoy, kokox;
    private Lauta omistaja;

    public Siirrot(int kokox, int kokoy, Lauta omistaja) {
        this.kokoy = kokoy;
        this.kokox = kokox;
        
        this.omistaja = omistaja;
    }

    /**
     * Laskee yksittäisen ruudun mahdolliset siirrot.
     *
     * @param x Nappulan rivi
     * @param y Nappula sarake
     * @return Lista, jossa on kyseisen ruudun kaikki toteutettavat siirrot.
     */
    public Lista ruudunSiirrot(int x, int y, int vari, int nappula) {
        Lista lista = new Lista();

        if (vari == 0) {
            return lista;
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
            lista = torninSiirrot(x, y, lista, vari);
        }
        // Lähetti
        if (nappula == 4) {
            lista = lahetinSiirrot(x, y, lista, vari);
        }
        // Kuningatar
        if (nappula == 2) {
            lista = torninSiirrot(x, y, lista, vari);
            lista = lahetinSiirrot(x, y, lista, vari);
        }
        // Kuningas
        if (nappula == 1) {
            lista = kuninkaanSiirrot(x, y, lista);
        }

        return lista;
    }

    private Lista kuninkaanSiirrot(int x, int y, Lista lista) {
        lista = lisaaLaillinenSiirto(x, y, x, y + 1, lista, true, true, false);
        lista = lisaaLaillinenSiirto(x, y, x + 1, y + 1, lista, true, true, false);
        lista = lisaaLaillinenSiirto(x, y, x - 1, y + 1, lista, true, true, false);
        lista = lisaaLaillinenSiirto(x, y, x, y - 1, lista, true, true, false);
        lista = lisaaLaillinenSiirto(x, y, x + 1, y - 1, lista, true, true, false);
        lista = lisaaLaillinenSiirto(x, y, x - 1, y - 1, lista, true, true, false);
        lista = lisaaLaillinenSiirto(x, y, x - 1, y, lista, true, true, false);
        lista = lisaaLaillinenSiirto(x, y, x + 1, y, lista, true, true, false);
        return lista;
    }

    /**
     * Laskee lähetille mahdolliset siirrot
     *
     * @param x Nappulan rivi
     * @param y Nappulan sarake
     * @param lista lista, johon siirrot lisätään
     * @return lista, jossa on kaikki lailliset siirrot
     */
    private Lista lahetinSiirrot(int x, int y, Lista lista, int vari) {
        lista = lahetti(x, y, lista, vari, true, true);
        lista = lahetti(x, y, lista, vari, true, false);
        lista = lahetti(x, y, lista, vari, false, true);
        lista = lahetti(x, y, lista, vari, false, false);

        return lista;
    }

    private Lista lahetti(int x, int y, Lista lista, int vari, boolean xplus, boolean yplus) {
        for (int siirronPituus = 1; siirronPituus < this.kokoy; siirronPituus++) {
            int nykyinenX, nykyinenY;

            if (xplus) {
                nykyinenX = x + siirronPituus;
            } else {
                nykyinenX = x - siirronPituus;
            }

            if (yplus) {
                nykyinenY = y + siirronPituus;
            } else {
                nykyinenY = y - siirronPituus;
            }


            if (onkoLaudanUlkopuolella(nykyinenX, nykyinenY)) {
                // Törmättiin laudan reunaan
                break;
            }

            lista = lisaaLaillinenSiirto(x, y, nykyinenX, nykyinenY, lista, true, true, true);

            if (vari != 0) {
                // Törmättiin nappulaan, ei etsitä kauemmaksi
                break;
            }
        }
        return lista;
    }

    /**
     * Laskee tornille mahdolliset siirrot
     *
     * @param x Nappulan rivi
     * @param y Nappulan sarake
     * @param lista lista, johon siirrot lisätään
     * @return lista, jossa on kaikki lailliset siirrot
     */
    private Lista torninSiirrot(int x, int y, Lista lista, int vari) {
        for (int siirronPituus = 1; siirronPituus < this.kokoy; siirronPituus++) {
            int nykyinenX = x;
            int nykyinenY = y + siirronPituus;

            if (onkoLaudanUlkopuolella(nykyinenX, nykyinenY)) {
                // Törmättiin laudan reunaan
                break;
            }

            lista = lisaaLaillinenSiirto(x, y, nykyinenX, nykyinenY, lista, true, true, true);

            if (vari != 0) {
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

            if (vari != 0) {
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

            if (vari != 0) {
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

            if (vari != 0) {
                // Törmättiin nappulaan, ei etsitä kauemmaksi
                break;
            }
        }

        return lista;
    }

    /**
     * Lisää listaan uuden siirron, jos se on laillinen. Muutoin palautetaan
     * alkuperäinen lista.
     *
     * @param x Nappulan rivi
     * @param y Nappulan sarake
     * @param uusix Uuden paikan rivi
     * @param uusiy Uuden paikan sarake
     * @param lista lista, jossa on jo lasketut siirrot
     * @param saaSiirtyaTyhjaan Saako nappula siirtyä tyhjään ruutuun
     * laskettavalla siirrolla vai pitääkö ruudussa olla syötävä nappula?
     * Käytetään sotilaan syöntisiirtoihin.
     * @param saakoSyoda Saako nappula syödä laskettavalla siirrolla.
     * Normaalisti true, mutta sotilaan eteenpäin siirtyminen false.
     * @param saakoOllaVaarassa Normaalisti true, mutta kuninkaalla false.
     * Varmistetaan, ettei kuninkaan liikkuminen altista sitä shakille.
     * @return lista, jossa on laskettu siirto ja aikaisemmat lasketut siirrot.
     */
    private Lista lisaaLaillinenSiirto(int x, int y, int uusix, int uusiy, Lista lista, boolean saaSiirtyaTyhjaan, boolean saakoSyoda, boolean saakoOllaVaarassa) {
        int uusiSiirto[] = {uusix, uusiy};
        // uusi tai vanha paikka on laudan ulkopuolella
        if (uusix >= this.kokox || uusiy >= this.kokoy
                || uusix < 0 || uusiy < 0) {
            return lista;
        }

        // TODO tarkastus, ettei siirto aseta kuningasta vaaraan.
        // Pohdintaa: onko järkevämpää hoitaa erittäin suurella min-max arvolla?

        if (!saakoOllaVaarassa) {
            // TODO tarkastus siirrolle, jottei kuningas aseta itseään shakkiin.
            // Pohdintaa: varmistaako minmax tämän?
        }

        // onko uusi paikka tyhjä?
        if (omistaja.onkoTyhja(uusix, uusiy)) {
            // Siirrytään tyhjään ruutuun
            if (saaSiirtyaTyhjaan) {
                lista.add(uusiSiirto);
            }
            return lista;
        } else {
            // Tutkitaan voiko jo varattuun ruutuun siirtyä (syödäänkö)
            if (omistaja.samanVarinen(x, y, uusix, uusiy)) {
                // Ruudussa saman värinen
                return lista;
            } else {
                // Syödään
                if (saakoSyoda) {
                    // Lisätään syöntisiirto vain jos syöminen on sallittua nappulalle tällä siirrolla
                    lista.add(uusiSiirto);
                }
                return lista;
            }
        }
    }



    /**
     * Tarkistaa onko ruutu laudalla.
     *
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
