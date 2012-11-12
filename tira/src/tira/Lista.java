/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

/**
 *
 * @author juhainki
 */
public class Lista {
    
    private int[][] taulukko;
    private int koko, pointer;

    public Lista() {   
        this.koko = 50;
        this.taulukko = new int[koko][2];
        this.pointer = 0;
    }
    
    public void add(int[] uusi) {
        // Tarkista mahtuuko -> kasvatus järkevästi (koon tuplaus?)
        taulukko[pointer] = uusi;
        pointer++;
        
    }
    
    private void kasvata() {
        //TODO
    }
    
    public void clear() {
        this.taulukko = new int[koko][2];
    }
    
    public int[] get(int index) {
        return taulukko[index];
    }
    
    public int length() {
        return pointer;
    }
}

/****
 * :::::TODO:::::
 * Lisää testit ja javadoc
 * Toteuta add()
 * Lisää getti
 */