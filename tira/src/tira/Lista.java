/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

/**
 *
 * @author juhainki
 * 
 * Listä on ArrayListia muistuttava tietorakenne, joka muistaa myös oman maksimin
 * ja miniminsä, sekä niiden sijainnit.
 */
public class Lista {
    
    private Siirto[] taulukko;
    private int koko, pointer, maxArvo, minArvo, minIndex, maxIndex;

    /**
     * Luo uuden Listan, joka käyttäytyy ArrayListin tavoin
     */
    public Lista() {   
        this.koko = 50;
        this.taulukko = new Siirto[this.koko];
        this.pointer = 0;
        
        maxArvo = Integer.MIN_VALUE;
        minArvo = Integer.MAX_VALUE;
        minIndex = 0;
        maxIndex = 0;
    }
    
    /**
     * Lisää listan loppuun annetun alkion
     * @param uusi Siirto-olio, joka kuvaa tallennettavaa siirtoa
     */
    public void add(Siirto uusi) {
        if (pointer == koko - 1) {
            kasvata();
        }
        taulukko[pointer] = uusi;
        
        if (uusi.arvo() < minArvo) {
            this.minArvo = uusi.arvo();
            this.minIndex = pointer;
        }
        if (uusi.arvo() > maxArvo) {
            this.maxArvo = uusi.arvo();
            this.maxIndex = pointer;
        }
//        System.out.println("Lista.add(): " + uusi.arvo() + ", max: " + this.maxArvo + "@" + this.maxIndex);
//        System.out.println("Lista.add(): " + uusi.arvo() + ", min: " + this.minArvo + "@" + this.minIndex);
        pointer++;
    }
    
    /**
     * Kasvattaa taulukon kokoa tarvittaessa tuplaamalla sen
     */
    private void kasvata() {
        Siirto[] uusiTaulu = new Siirto[this.koko*2];
        for (int i = 0; i < pointer; i++) {
            uusiTaulu[i] = this.taulukko[i];
        }
        this.taulukko = uusiTaulu;
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

    /**
     * 
     * @return listan suurin alkio
     */
    public Siirto getMax() {
        return this.taulukko[this.maxIndex];
    }

    /**
     * 
     * @return listan pienin alkio
     */
    public Siirto getMin() {
        return this.taulukko[this.minIndex];
    }
}