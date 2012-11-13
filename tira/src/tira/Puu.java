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

    private Solmu juuri;

    public Puu() {
        this.juuri = null;
    }

    public void add(Solmu solmu) {
        if (juuri == null) {
            juuri = solmu;
            return;
        }

        liitaPuuhun(solmu);
    }

    private void liitaPuuhun(Solmu solmu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Solmu getJuuri() {
        return juuri;
    }

    public Siirto minimax(boolean valkoisenVuoro, int syvyys) {
        Siirto s;
        if (valkoisenVuoro) {
            s = max(juuri, syvyys);
        } else {
            s = min(juuri, syvyys);
        }
        return s;
    }

    public Siirto max(Solmu solmu, int syvyys) {
        if (syvyys < 1 || solmu.eiLapsia()) {
            return solmu.getParas();
        }
        
        Siirto s = new Siirto(-1, -1, -1, -1, Integer.MIN_VALUE);
        
        for (Solmu lapsi : solmu.lapset()) {
            s = valitseParempi(true, s, min(lapsi, syvyys - 1));
        }
        return s;
    }

    public Siirto min(Solmu solmu, int syvyys) {
        if (syvyys < 1 || solmu.eiLapsia()) {
            return solmu.getParas();
        }
        Siirto s = new Siirto(-1, -1, -1, -1, Integer.MAX_VALUE);
        for (Solmu lapsi : solmu.lapset()) {
            s = valitseParempi(false, s, min(lapsi, syvyys - 1));
        }
        return s;
    }

    /**
     * 
     * @param min true, jos etsitään pienempää
     * @param s1
     * @param s2
     * @return 
     */
    private Siirto valitseParempi(boolean min, Siirto s1, Siirto s2) {
        if (min) {
            if (s1.arvo() >= s2.arvo()) {
                return s2;
            } else {
                return s1;
            }
        } else {
            if (s1.arvo() < s2.arvo()) {
                return s2;
            } else {
                return s1;
            }
        }
    }
}
