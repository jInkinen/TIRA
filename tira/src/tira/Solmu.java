/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

import java.util.PriorityQueue;

/**
 *
 * @author juhainki
 */
public class Solmu {
    private PriorityQueue<Siirto> jono;

    public Solmu() {
        jono = new PriorityQueue<>();
    }
    
    public void add(Siirto s) {
        jono.add(s);
    }
    
    public Siirto getEka() {
        return jono.peek();
    }

    public Solmu[] lapset() {
        return (Solmu[]) jono.toArray();
    }

    boolean eiLapsia() {
        if (jono.size() == 0) {
            return true;
        }
        return false;
    }

    
}
