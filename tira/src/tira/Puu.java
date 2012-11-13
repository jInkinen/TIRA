/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

/**
 *
 * @author juhainki
 */
public class Puu {

    private Solmu nykyinenTila;

    public Puu() {
        this.nykyinenTila = null;
    }

    public void add(Solmu solmu) {
        if (nykyinenTila == null) {
            nykyinenTila = solmu;
            return;
        }

        liitaPuuhun(solmu);
    }

    private void liitaPuuhun(Solmu solmu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Solmu getJuuri() {
        return nykyinenTila;
    }

    
    public int max(Solmu solmu, int syvyys) {
        if (syvyys < 1 || solmu.eiLapsia()) {
            return solmu.getEka().arvo();
        }
        int luku = Integer.MIN_VALUE;
        for (Solmu lapsi : solmu.lapset()) {
            luku = Math.max(luku, min(lapsi, syvyys - 1));
        }
        return luku;
    }

    public int min(Solmu solmu, int syvyys) {
        if (syvyys < 1 || solmu.eiLapsia()) {
            return solmu.getEka().arvo();
        }
        int luku = Integer.MAX_VALUE;
        for (Solmu lapsi : solmu.lapset()) {
            luku = Math.min(luku, max(lapsi, syvyys - 1));
        }
        return luku;
    }
}
