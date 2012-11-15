package tira;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

       
/**
 *
 * @author juhainki
 */
public class PuuSolmu {

    private Lista solmunSiirrot;
    private PuuSolmu[] alipuu;
    private int pointer, koko;

    /**
     * Luo uuden puun.
     * @param koko Mahdollisten alipuiden määrä. Ylivuoto katoaa ja lisäyksiä ei liitetä puuhun, kun lista täyttyy.
     */
    public PuuSolmu(int koko) {
        this.solmunSiirrot = new Lista();
        this.koko = koko;
        this.pointer = 0;
        this.alipuu = new PuuSolmu[koko];
    }
    
    /**
     * Lisää puulle uuden alipuun.
     * @param puu 
     */
    public void liitaPuuPuuhun(PuuSolmu puu) {
        if (pointer < koko) {
            alipuu[pointer] = puu;
            pointer++;
        } else {
            throw new UnsupportedOperationException("Puu yli rajansa");
        }
    }

    public Lista getSiirrot() {
        return solmunSiirrot;
    }
    
    private boolean eiLapsia() {
        if (pointer <= 0) {
            return true;
        }
        return false;
    }
    
    private PuuSolmu[] lapset() {
        return alipuu;
    }

    public Siirto minimax(boolean valkoisenVuoro, int syvyys) {
        Siirto s;
        if (valkoisenVuoro) {
            s = max(this, syvyys);
        } else {
            s = min(this, syvyys);
        }
        return s;
    }

    public Siirto max(PuuSolmu solmu, int syvyys) {
        if (syvyys <= 1 || solmu.eiLapsia()) {
            System.out.println("PS:max():::" + solmu.getSiirrot().getMax());
            return solmu.getSiirrot().getMax();
        }
        
        Siirto s = new Siirto(-1, -1, -1, -1, Integer.MIN_VALUE);
        
        for (int i = 0; i < solmu.pointer; i++) {
            s = valitseParempi(true, s, min(solmu.alipuu[i], syvyys - 1));
        }
        return s;
    }

    public Siirto min(PuuSolmu solmu, int syvyys) {
        if (syvyys < 1 || solmu.eiLapsia()) {
            return solmu.getSiirrot().getMin();
        }
        Siirto s = new Siirto(-1, -1, -1, -1, Integer.MAX_VALUE);
        for (PuuSolmu lapsi : solmu.lapset()) {
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

    public void lisaaLasketutSiirrot(Lista siirrot) {
        for (int i = 0; i < siirrot.length(); i++) {
            this.solmunSiirrot.add(siirrot.get(i));
        }
    }

    public String siirrotString() {
        String ret = "";
        for (int i = 0; i < this.solmunSiirrot.length(); i++) {
            ret = ret + solmunSiirrot.get(i) + " | ";
            
        }
        return ret;
    }
}
