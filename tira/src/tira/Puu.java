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
        String ret = juuri.lastenMaara() + " " + juuri.getOmaArvo() + "\n";
        
        ret = ret + lastenString(juuri, 1);
        
        return ret;
    }
    
    //Apuväline tulostusasun luomiseen
    private String lastenString(Solmu s, int syvyys) {
        String ret = "#" + syvyys + "|v|";
        for (int i = 0; i <= s.lastenMaara(); i++) {
            ret = ret + s.getLapset()[i].getOmaArvo() + "<A ";
            ret = ret + lastenString(s.getLapset()[i], syvyys + 1);
        }
        return ret + "|^|\n";
    }
 
    /**
     * Alphabeta-algoritmi, joka laskee optimaalisen siirron parametrien mukaan.
     * @param valkoinen true, jos max-pelaaja. false, jos min-pelaaja
     * @param syvyys kuinka monta tasoa alaspäin mennään puussa. Käytetään rekursiokutsussa arvolla syvyys-1. Lisäksi alkuperäisessä kutsussa käytetään määrittämään etsinnän kokonaissyvyys.
     * @param solmu solmu, joka toimii juurena kyseisellä iteraatiolla
     * @param a alpha-arvon sisältävä solmu
     * @param b beta-arvon sisältävä solmu
     * @return solmu, joka on paras
     */
    public Solmu alphabeta(boolean valkoinen, int syvyys, Solmu solmu, Solmu a, Solmu b) {
        if (syvyys <= 0 || solmu.eiLapsia()) {
            return solmu;
        }
        if (valkoinen) {
            for (int i = 0; i < solmu.lastenMaara(); i++) {
                a = max(a, alphabeta(!valkoinen, syvyys - 1, solmu, a, b));
                if (b.getOmaArvo() <= a.getOmaArvo()) {
                    break;
                }
            }
            return a;
        } else {
            for (int i = 0; i < solmu.lastenMaara(); i++) {
                b = min(b, alphabeta(!valkoinen, syvyys - 1, solmu, a, b));
                if (b.getOmaArvo() <= a.getOmaArvo()) {
                    break;
                }
            }
            return b;
        }
    }

    private Solmu max(Solmu a, Solmu ab) {
        if (a.getOmaArvo() > ab.getOmaArvo()) {
            return a;
        } else {
            return ab;
        }
    }
        

    private Solmu min(Solmu b, Solmu ab) {
        if (b.getOmaArvo() < ab.getOmaArvo()) {
            return b;
        } else {
            return ab;
        }
    }
}