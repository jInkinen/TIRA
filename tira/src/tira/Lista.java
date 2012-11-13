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

    /**
     * Luo uuden Listan, joka käyttäytyy ArrayListin tavoin
     */
    public Lista() {   
        this.koko = 50;
        this.taulukko = new int[koko][2];
        this.pointer = 0;
    }
    
    /**
     * Lisää listan loppuun annetun alkion
     * @param uusi int[], joka kuvaa siirtoa
     */
    public void add(int[] uusi) {
        // Tarkista mahtuuko -> kasvatus järkevästi (koon tuplaus?)
        taulukko[pointer] = uusi;
        pointer++;
        
    }
    
    private void kasvata() {
        //TODO
    }
    
    /**
     * Tyhjentää listan
     */
    public void clear() {
        this.taulukko = new int[koko][2];
    }
    
    /**
     * 
     * @param index etsittävän alkion indeksi
     * @return alkio, joka on kyseisellä paikalla
     */
    public int[] get(int index) {
        return taulukko[index];
    }
    
    /**
     * 
     * @return listan tämänhetkinen pituus
     */
    public int length() {
        return pointer;
    }
}

/****
 * :::::TODO:::::
 * Lisää testit
 * remove(i)
 * 
 */