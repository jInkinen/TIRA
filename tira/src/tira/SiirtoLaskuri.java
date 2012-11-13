/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

/**
 *
 * @author juhainki
 */
public class SiirtoLaskuri {

    private Lauta lauta;

    public SiirtoLaskuri(Lauta omistaja) {
        this.lauta = omistaja;
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
            System.out.println("TYHJÄ: " + x + y);
        }
        
        if (lauta.valkoisenVuoro()) {
            if (vari != 1) {
                return lista;
            }
        } else {
            if (vari != -1) {
                return lista;
            }
        }
        // Sotilas
        if (nappula == 5) {
            lista = sotilaanSiirrot(x, y, lista, vari);
        }
        // Lähetti
        if (nappula == 4) {
            lista = lahetinSiirrot(x, y, lista, vari);
        }
        // Torni
        if (nappula == 3) {
            lista = torninSiirrot(x, y, lista, vari);
        }
        // Kuningatar
        if (nappula == 2) {
            lista = torninSiirrot(x, y, lista, vari);
            lista = lahetinSiirrot(x, y, lista, vari);
        }
        // Kuningas
        if (nappula == 1) {
            lista = kuninkaanSiirrot(x, y, lista, vari);
        }

        return lista;
    }

    private Lista sotilaanSiirrot(int x, int y, Lista lista, int vari) {
        if (vari == -1) {
            lista = lisaaLaillinenSiirto(x, y, x, y + 1, lista, true, false, true, vari);
            lista = lisaaLaillinenSiirto(x, y, x + 1, y + 1, lista, false, true, true, vari);
            lista = lisaaLaillinenSiirto(x, y, x - 1, y + 1, lista, false, true, true, vari);
        } else {
            lista = lisaaLaillinenSiirto(x, y, x, y - 1, lista, true, false, true, vari);
            lista = lisaaLaillinenSiirto(x, y, x + 1, y - 1, lista, false, true, true, vari);
            lista = lisaaLaillinenSiirto(x, y, x - 1, y - 1, lista, false, true, true, vari);
        }
        return lista;
    }

    private Lista kuninkaanSiirrot(int x, int y, Lista lista, int vari) {
        lista = lisaaLaillinenSiirto(x, y, x, y + 1, lista, true, true, false, vari);
        lista = lisaaLaillinenSiirto(x, y, x + 1, y + 1, lista, true, true, false, vari);
        lista = lisaaLaillinenSiirto(x, y, x - 1, y + 1, lista, true, true, false, vari);
        lista = lisaaLaillinenSiirto(x, y, x, y - 1, lista, true, true, false, vari);
        lista = lisaaLaillinenSiirto(x, y, x + 1, y - 1, lista, true, true, false, vari);
        lista = lisaaLaillinenSiirto(x, y, x - 1, y - 1, lista, true, true, false, vari);
        lista = lisaaLaillinenSiirto(x, y, x - 1, y, lista, true, true, false, vari);
        lista = lisaaLaillinenSiirto(x, y, x + 1, y, lista, true, true, false, vari);
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
        //for (int siirronPituus = 1; siirronPituus < this.kokoy; siirronPituus++) {
        int siirronPituus = 1;
        while (true) {
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


            if (lauta.onkoLaudanUlkopuolella(nykyinenX, nykyinenY)) {
                // Törmättiin laudan reunaan
                break;
            }

            lista = lisaaLaillinenSiirto(x, y, nykyinenX, nykyinenY, lista, true, true, true, vari);

            if (vari != 0) {
                // Törmättiin nappulaan, ei etsitä kauemmaksi
                break;
            }

            siirronPituus++;
        }
        return lista;
    }

    private Lista torni(int x, int y, Lista lista, int vari, boolean xakseli, boolean plus) {
        int nykyinenX, nykyinenY;

        int siirronPituus = 1;
        while (true) {
            if (xakseli) {
                if (plus) {
                    nykyinenX = x + siirronPituus;
                    nykyinenY = y;
                } else {
                    nykyinenX = x - siirronPituus;
                    nykyinenY = y;
                }
            } else {
                if (plus) {
                    nykyinenX = x;
                    nykyinenY = y + siirronPituus;
                } else {
                    nykyinenX = x;
                    nykyinenY = y - siirronPituus;
                }
            }


            if (lauta.onkoLaudanUlkopuolella(nykyinenX, nykyinenY)) {
                // Törmättiin laudan reunaan
                break;
            }

            lista = lisaaLaillinenSiirto(x, y, nykyinenX, nykyinenY, lista, true, true, true, vari);

            if (vari != 0) {
                // Törmättiin nappulaan, ei etsitä kauemmaksi
                break;
            }
            siirronPituus++;
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
        lista = torni(x, y, lista, vari, true, true);
        lista = torni(x, y, lista, vari, true, false);
        lista = torni(x, y, lista, vari, false, true);
        lista = torni(x, y, lista, vari, false, false);

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
    private Lista lisaaLaillinenSiirto(int x, int y, int uusix, int uusiy, Lista lista, boolean saaSiirtyaTyhjaan, boolean saakoSyoda, boolean saakoOllaVaarassa, int vari) {
        Siirto uusiSiirto = new Siirto(x, y, uusix, uusiy, 0);
        // uusi paikka on laudan ulkopuolella
        if (lauta.onkoLaudanUlkopuolella(uusix, uusiy)) {
            return lista;
        }
        if (lauta.onkoLaudanUlkopuolella(x, y)) {
            throw new UnsupportedOperationException("Siirrettävä nappula ei voi olla laudan ulkopuolella");
        }

        // TODO tarkastus, ettei siirto aseta kuningasta vaaraan.
        // Pohdintaa: onko järkevämpää hoitaa erittäin suurella min-max arvolla?

        if (!saakoOllaVaarassa) {
            // TODO tarkastus siirrolle, jottei kuningas aseta itseään shakkiin.
            // Pohdintaa: varmistaako minmax tämän?
        }

        // onko uusi paikka tyhjä?
        if (lauta.onkoTyhja(uusix, uusiy)) {
            // Siirrytään tyhjään ruutuun
            if (saaSiirtyaTyhjaan) {
                lista.add(uusiSiirto);
            }
            return lista;
        } else {
            // Tutkitaan voiko jo varattuun ruutuun siirtyä (syödäänkö)
            if (lauta.samanVarinen(x, y, uusix, uusiy)) {
                // Ruudussa saman värinen
                return lista;
            } else {
                // Syödään
                if (saakoSyoda) {
                    // Lisätään syöntisiirto vain jos syöminen on sallittua nappulalle tällä siirrolla
                    uusiSiirto.setArvo(vari * lauta.nappulanArvo(uusix, uusiy));
                    lista.add(uusiSiirto);
                }
                return lista;
            }
        }
    }
}
