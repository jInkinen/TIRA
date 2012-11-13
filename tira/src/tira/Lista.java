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
    
    private Siirto[] taulukko;
    private int koko, pointer;

    /**
     * Luo uuden Listan, joka käyttäytyy ArrayListin tavoin
     */
    public Lista() {   
        this.koko = 50;
        this.taulukko = new Siirto[this.koko];
        this.pointer = 0;
    }
    
    /**
     * Lisää listan loppuun annetun alkion
     * @param uusi int[], joka kuvaa siirtoa
     */
    public void add(Siirto uusi) {
        if (pointer == koko - 1) {
            kasvata();
        }
        taulukko[pointer] = uusi;
        pointer++;
        
    }
    
    private void kasvata() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Tyhjentää listan
     */
    public void clear() {
        this.taulukko = new Siirto[this.koko];
    }
    
    /**
     * 
     * @param index etsittävän alkion indeksi
     * @return alkio, joka on kyseisellä paikalla
     */
    public Siirto get(int index) {
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