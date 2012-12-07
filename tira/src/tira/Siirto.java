
package tira;

/**
 * Siirto-luokka luo olioita, jotka esittävät yksittäisiä siirtoja.
 * Siirrolla on lähtöpiste, loppupiste ja arvo.
 */
public class Siirto implements Comparable {

    private int arvo;
    private int[] paikka1, paikka2;

    public Siirto(int x1, int y1, int x2, int y2, int arvo) {
        paikka1 = new int[2];
        paikka2 = new int[2];

        paikka1[0] = x1;
        paikka2[0] = x2;
        paikka1[1] = y1;
        paikka2[1] = y2;
        this.arvo = arvo;
    }

    /**
     * osa Comparable:n toteutusta
     * @param o verrattava olio
     * @return vertailuarvo
     */
    @Override
    public int compareTo(Object o) {
        return arvo;
    }

    /**
     * 
     * @return siirron arvo, joka on laskettu sille heurestiikan mukaan
     */
    public int arvo() {
        return arvo;
    }
    
    /**
     * 
     * @return kertoo siirron lähtöpisteen
     */
    public int[] alkuperainenPaikka() {
        return paikka1;
    }
    
    /**
     * 
     * @return kertoo siirron päätepisteen
     */
    public int[] uusiPaikka() {
        return paikka2;
    }
    
    /**
     * Asettaa siirrolle uuden vertailuarvon
     * @param arvo heurestiikan mukainen vertailuarvo
     */
    public void setArvo(int arvo) {
        this.arvo = arvo;
    }
    
    /**
     * 
     * @return Muotoiltu tulostusasu luokalle
     */
    @Override
    public String toString() {
        return "(" + paikka1[0] + "," + paikka1[1] + ") -> (" + paikka2[0] + "," + paikka2[1] + ") [" + arvo + "]";
    }
}