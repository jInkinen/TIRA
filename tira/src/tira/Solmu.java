/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

/**
 *
 * @author juhainki
 */
class Solmu {
    private Lista siirrot;
    private Solmu[] lapset;

    public Solmu(Lista l, int lastenMaara){
       this.siirrot = l;
       this.lapset = new Solmu[lastenMaara];
    }

    public Solmu getLapsi(int i){
        return this.lapset[i];
    }

    public int lastenMaara(){
        return lapset.length;
    }

    public void setLapsi(int i, Solmu s){
        this.lapset[i] = s;
    }

    
}
