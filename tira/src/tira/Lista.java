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
    private int koko;

    public Lista() {   
        this.koko = 50;
        this.taulukko = new int[koko][2];
    }
    
    public void add(int[] uusi) {
        // Tarkista mahtuuko -> kasvatus järkevästi (koon tuplaus?)
        //Lisäys
    }
    
    public void clear() {
        this.taulukko = new int[koko][2];
    }
}

/****
 * :::::TODO:::::
 * Lisää testit ja javadoc
 */