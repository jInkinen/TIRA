/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

/**
 *
 * @author juhainki
 */
public class Ruudut {
    private Ruutu[][] taulukko;

    public Ruudut(int kokox, int kokoy) {
        taulukko = new Ruutu[kokox][kokoy];
        
        for (int x = 0; x < kokox; x++) {
            for (int y = 0; y < kokoy; y++) {
                this.taulukko[x][y] = new Ruutu(x, y);
            }
        }
    }

    public Ruutu get(int x, int y) {
        return taulukko[x][y];
    }
    
    
}
