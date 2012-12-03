
package tira;

/**
 * Solmu on puun käyttämä apuväline, jonne tiedot varsinaisesti säilötään.
 */
public class Solmu {
    private Solmu vanh;
    private Solmu[] lapset;
    private int pointer, koko, omaArvo, lastenMax, lastenMin, maxIndeksi, minIndeksi;
    private Siirto siirto;

    /**
     * @param vanhempi Solmun vanhempi
     * @param siirto solmun siirto
     */
    public Solmu(Solmu vanhempi, Siirto siirto) {
        this.vanh = vanhempi;
        this.siirto = siirto;
        
//        asetaOmaArvo();
        asetaOletusArvot();
    }
    
//    private void asetaOmaArvo() {
//        if (this.siirto != null) {
//            this.omaArvo = this.siirto.arvo();
//        } else {
//            this.omaArvo = Integer.MAX_VALUE;
//        }
//        
//        if (this.vanh != null) {
//            omaArvo =+ this.vanh.getOmaArvo();
//        }
//    }
    
    //Asettaa oliolle tietyt enneltamääritetyt oletusarvot
    private void asetaOletusArvot() {
        this.pointer = -1;
        this.koko = 20;
        this.lapset = new Solmu[koko];
        
        lastenMin = Integer.MAX_VALUE;
        lastenMax = Integer.MIN_VALUE;
        maxIndeksi = -1;
        minIndeksi = -1;
    }
    
    
    
    /**
     * Lisää solmulle toisen solmun lapseksi
     * @param s lisättävä solmu
     */
    public void lisaaLapsi(Solmu s) {
        pointer++;
        if (pointer == koko) {
            kasvataLapsiVarastoa();
        }
        
        aariLastenSijainti(s);
        
        this.lapset[pointer] = s;
    }
    
    //tallennetaan suurimman/pienimmän lapsen sijainti
    private void aariLastenSijainti(Solmu s) {
        // ensimmäisellä lisäyskerralla asetetaan ensimmäinen alkio min/max arvoiksi
        if (maxIndeksi < 0 || minIndeksi < 0) {
            maxIndeksi = pointer;
            minIndeksi = pointer;
        // muilla kerroilla vertaillaan  ja päivitetään arvot tarvittaessa
        } else {
            if (this.lapset[maxIndeksi] == null || lapset[minIndeksi] == null) {
                maxIndeksi = -1;
                minIndeksi = -1;
                return;
            }
            if (s.getSiirto().arvo() > this.lapset[maxIndeksi].getSiirto().arvo()) {
                maxIndeksi = pointer;
                lastenMax = s.getSiirto().arvo();
            }
            if (s.getSiirto().arvo() < lapset[minIndeksi].getSiirto().arvo()) {
                minIndeksi = pointer;
                lastenMin = s.getSiirto().arvo();
            }
        }
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

    /**
     * @return suurrimman lapsen arvo
     */
    public int getLastenMax() {
        return lastenMax;
    }

    /**
     * @return pienimmän lapsen arvo
     */
    public int getLastenMin() {
        return lastenMin;
    }

    /**
     * @return Solmun oma arvo
     */
//    public int getOmaArvo() {
////        asetaOmaArvo();
//        return omaArvo;
//    }

//    public void setOmaArvo(int omaArvo) {
//        this.omaArvo = omaArvo;
//    }

    /**
     * 
     * @return solmun vanhempi
     */
    public Solmu getVanhempi() {
        return vanh;
    }

    /**
     * 
     * @return lista solmun lapsista
     */
    public Solmu[] getLapset() {
        return lapset;
    }
    
    /**
     * 
     * @return solmun siirto
     */
    public Siirto getSiirto() {
        return siirto;
    }
    
    /**
     * getLapset palauttama lista saattaa sisältää tyhjiä alkioita.
     * Siksi on syytä käyttää tätä metodia oikea pituuden selvittämiseen.
     * @return lasten määrä
     */
    public int lastenMaara() {
        return pointer;
    }

    /**
     * 
     * @return jos solmulla ei ole lapsia -> true, muutoin false
     */
    public boolean eiLapsia() {
        if (pointer < 0) {
            return true;
        }
        return false;
    }
    
    /**
     * 
     * @return solmun suurin lapsi
     */
    public Solmu getMaxLapsi() {
        return lapset[this.maxIndeksi];
    }
    
    /**
     * 
     * @return solmun pienin lapsi
     */
    public Solmu getMinLapsi() {
        return lapset[this.minIndeksi];
    }

    /**
     * 
     * @return solmun vanhempi
     */
    public Solmu vanhempi() {
        return this.vanh;
    }
}
