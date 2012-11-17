package tira;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

       
/**
 *
 * @author juhainki
 * 
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
    
    public void setJuuri(Solmu s) {
        this.juuri = s;
    }
    
    public Solmu getJuuri() {
        return juuri;
    }
    
    public String tulostus() {
        if (juuri == null) {
            return "Puu on tyhjä. (Juuri on null).";
        }
        return "Tulostusta ei toteutettu vielä";
        
    }
    


   

    /**
     * minimax hakee optimaalisen siirron annettujen parametrien mukaisesti
     * @param valkoisenVuoro onko valkoisen vuoro?
     * @param syvyys Kuinka syvä puu on käytössä?
     * @return paras siirto
     */
    public Siirto minimax(boolean valkoisenVuoro, int syvyys) {
        Siirto s = new Siirto(-1, -1, -1, -1, Integer.MIN_VALUE);
        if (valkoisenVuoro) {
            s = juuri.max(s, syvyys);
        } else {
            s = juuri.min(s, syvyys);
        }
        return s;
    }

    



}
