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

    private int x1, x2, y1, y2, arvo;

    public Siirto(int x1, int x2, int y1, int y2, int arvo) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.arvo = arvo;
    }

    @Override
    public int compareTo(Object o) {
        return arvo;
    }

    public int arvo() {
        return arvo;
    }
}
