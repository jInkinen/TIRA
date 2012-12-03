package tira;

/**
 * Puu tuntee oman juurensa. Juuri on Solmu joka tuntee lapsensa ja vanhempansa.
 */
public class Puu {

    private Solmu juuri;

    public Puu() {
        juuri = null;
    }

    public Puu(Solmu s) {
        juuri = s;
    }

    /**
     * Asettaa uuden juuren puulle
     *
     * @param s uusi juurisolmu
     */
    public void setJuuri(Solmu s) {
        this.juuri = s;
    }

    /**
     *
     * @return puun juurisolmu
     */
    public Solmu getJuuri() {
        return juuri;
    }

    /**
     *
     * @return Puun tulostusasu testausta varten
     */
    public String tulostus() {
        if (juuri == null) {
            return "Puu on tyhjä. (Juuri on null).";
        }
        String ret = juuri.lastenMaara() + " " + juuri.getSiirto().arvo() + "\n";

        ret = ret + lastenString(juuri, 1);

        return ret;
    }

    //Apuväline tulostusasun luomiseen
    private String lastenString(Solmu s, int syvyys) {
        String ret = "#" + syvyys + "|v|";
        for (int i = 0; i <= s.lastenMaara(); i++) {
            ret = ret + s.getLapset()[i].getSiirto().arvo() + "<A ";
            ret = ret + lastenString(s.getLapset()[i], syvyys + 1);
        }
        return ret + "|^|\n";
    }

    /**
     * Alphabeta-algoritmi, jonka tehtävä on laskea optimaalinen siirto parametrien mukaan.
     *
     * @param valkoinen true, jos max-pelaaja. false, jos min-pelaaja
     * @param syvyys kuinka monta tasoa alaspäin mennään puussa. Käytetään
     * rekursiokutsussa arvolla syvyys-1. Lisäksi alkuperäisessä kutsussa
     * käytetään määrittämään etsinnän kokonaissyvyys.
     * @param solmu solmu, joka toimii juurena kyseisellä iteraatiolla
     * @param a alpha-arvon sisältävä solmu
     * @param b beta-arvon sisältävä solmu
     * @return solmu, joka on paras
     */
    public Solmu alphabeta(boolean valkoinen, int syvyys, Solmu solmu, Solmu a, Solmu b) {
        if (valkoinen) {
            return maxArvo(solmu, a, b, syvyys);
        } else {
            return minArvo(solmu, a, b, syvyys);
        }
    }


    private Solmu maxArvo(Solmu solmu, Solmu a, Solmu b, int syvyys) {
        if (lopetus(syvyys, solmu)) {
            return solmu;
        }
        
        Siirto min = new Siirto(-4, -4, -4, -4, Integer.MIN_VALUE);
        Solmu v = new Solmu(null, min);
        
        for (int i = 0; i < solmu.lastenMaara(); i++) {
            v = max(v, minArvo(solmu.getLapset()[i], a, b, syvyys - 1));
            if (v.getSiirto().arvo() >= b.getSiirto().arvo()) {
                return v;
            }
            a = max(a, v);
        }
        return v;
        
    }

    private Solmu minArvo(Solmu solmu, Solmu a, Solmu b, int syvyys) {
        if (lopetus(syvyys, solmu)) {
            return solmu;
        }
        
        Siirto min = new Siirto(-5, -5, -5, -5, Integer.MAX_VALUE);
        Solmu v = new Solmu(null, min);
        
        for (int i = 0; i < solmu.lastenMaara(); i++) {
            v = min(v, maxArvo(solmu.getLapset()[i], a, b, syvyys - 1));
            if (v.getSiirto().arvo() <= a.getSiirto().arvo()) {
                return v;
            }
            b = min(b, v);
        }
        return v;
    }
    
    /**
     * @param syvyys hetkellinen syvyys, jonka suuruus tarkistetaan
     * @param s solmu, jota tarkastellaan
     * @return true, jos ei voida jatkaa, muutoin false.
     */
    private boolean lopetus(int syvyys, Solmu s) {
        if (s == null) {
            return true;
        }
        if (syvyys == 0 || s.eiLapsia()) {
            return true;
        }
        return false;
    }
    
    /**
     * vertailee kahta solmua
     * @return suurempi solmu
     */
    private Solmu max(Solmu a, Solmu b) {
        if (b == null) {
            return a;
        }
        if (a.getSiirto().arvo() > b.getSiirto().arvo()) {
            return a;
        } else {
            return b;
        }
    }

    /**
     * vertailee kahta solmua
     * @return pienempi solmu
     */
    private Solmu min(Solmu a, Solmu b) {
        if (b == null) {
            return a;
        }
        if (a.getSiirto().arvo() < b.getSiirto().arvo()) {
            return a;
        } else {
            return b;
        }
    }
}