/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

/**
 *
 * @author juhainki
 * 
 * Solmu on puun käyttämä apuväline, jonne tiedot varsinaisesti säilötään.
 */
public class Solmu {
    private Solmu vanh;
    private Solmu[] lapset;
    private int pointer, koko, omaArvo, lastenMax, lastenMin, maxIndeksi, minIndeksi;
    private Siirto siirto;

    public Solmu(Solmu vanhempi, Siirto siirto) {
        this.vanh = vanhempi;
        this.siirto = siirto;
        
        if (this.siirto != null) {
            this.omaArvo = this.siirto.arvo();
        } else {
            this.omaArvo = Integer.MAX_VALUE;
        }
        
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
    
    public int lastenMaara() {
        return pointer;
    }

    public boolean eiLapsia() {
        if (pointer < 0) {
            return true;
        }
        return false;
    }   
}
