/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;



/**
 *
 * @author juhainki
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

    @Override
    public int compareTo(Object o) {
        return arvo;
    }

    public int arvo() {
        return arvo;
    }
    
    public int[] alkuperainenPaikka() {
        return paikka1;
    }
    
    public int[] uusiPaikka() {
        return paikka2;
    }
    
    public void setArvo(int arvo) {
        this.arvo = arvo;
    }
    
    @Override
    public String toString() {
        return "(" + paikka1[0] + "," + paikka1[1] + ") -> (" + paikka2[0] + "," + paikka2[1] + ")";
    }
}