/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

/**
 *
 * @author juhainki
 */
public class Solmu {
    private Solmu vanh;
    private Solmu[] lapset;
    private int pointer, koko, omaArvo, lastenMax, lastenMin, maxIndeksi, minIndeksi;
    private Siirto siirto;

    public Solmu(Solmu vanhempi, Siirto siirto) {
        this.vanh = vanhempi;
        this.siirto = siirto;
        
        this.omaArvo = this.siirto.arvo();
        if (vanhempi != null) {
            omaArvo =+ vanhempi.getOmaArvo();
        }
        
        
        this.pointer = -1;
        this.koko = 20;
        this.lapset = new Solmu[koko];
        
        lastenMin = Integer.MAX_VALUE;
        lastenMax = Integer.MIN_VALUE;
        maxIndeksi = -1;
        minIndeksi = -1;
    }
    
    public void lisaaLapsi(Solmu s) {
        pointer++;
        if (pointer == koko) {
            kasvataLapsiVarastoa();
        }
        //tallennetaan suurimman/pienimmän lapsen sijainti
        if (maxIndeksi < 0 || minIndeksi < 0) {
            maxIndeksi = pointer;
            minIndeksi = pointer;
        } else {
            if (s.getOmaArvo() > lapset[maxIndeksi].getOmaArvo()) {
                maxIndeksi = pointer;
                lastenMax = s.getOmaArvo();
            }
            if (s.getOmaArvo() < lapset[minIndeksi].getOmaArvo()) {
                minIndeksi = pointer;
                lastenMin = s.getOmaArvo();
            }
        }
        this.lapset[pointer] = s;
    }
    
    /**
     * Kasvattaa taulukon kokoa tarvittaessa tuplaamalla sen
     */
        private void kasvataLapsiVarastoa() {
        Solmu[] uudetLapset = new Solmu[this.koko*2];
        for (int i = 0; i < pointer - 1; i++) {
            uudetLapset[i] = this.lapset[i];
        }
        this.lapset = uudetLapset;
    }

    public int getLastenMax() {
        return lastenMax;
    }

    public void setLastenMax(int lastenMax) {
        this.lastenMax = lastenMax;
    }

    public int getLastenMin() {
        return lastenMin;
    }

    public void setLastenMin(int lastenMin) {
        this.lastenMin = lastenMin;
    }

    public int getOmaArvo() {
        return omaArvo;
    }

    public void setOmaArvo(int omaArvo) {
        this.omaArvo = omaArvo;
    }

    public Solmu getVanhempi() {
        return vanh;
    }

    public Solmu[] getLapset() {
        return lapset;
    }
    
    public Siirto getSiirto() {
        return siirto;
    }
    
    public int length() {
        return pointer;
    }
    
    
    /**
     * minimax-funktion apufunktio.
     */
    public Siirto max(Siirto vanhempi, int syvyys) {
        if (syvyys <= 1 || eiLapsia()) {
            return this.siirto;
        }
        
        Siirto s = new Siirto(-1, -1, -1, -1, Integer.MAX_VALUE);
        
        for (int i = 0; i < pointer; i++) {
            s = valitseParempi(true, s, min(lapset[this.minIndeksi].getSiirto(), syvyys - 1));
        }
        return s;
    }

    /**
     * minimax-funktion apufunktio.
     */
    public Siirto min(Siirto vanhempi, int syvyys) {
        if (syvyys < 1 || eiLapsia()) {
            return this.siirto;
        }
        
        Siirto s = new Siirto(-1, -1, -1, -1, Integer.MIN_VALUE);
        
        for (int i = 0; i < pointer; i++) {
            s = valitseParempi(false, s, max(lapset[this.maxIndeksi].getSiirto(), syvyys - 1));
        }

        return s;
    }

    private boolean eiLapsia() {
        if (pointer < 0) {
            return true;
        }
        return false;
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
