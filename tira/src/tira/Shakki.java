/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

/**
 *
 * @author juhainki
 */
public class Shakki {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        Lauta peliLauta = new Lauta();
        Tekoaly aly1 = new Tekoaly(1);
        Tekoaly aly2 = new Tekoaly(-1);
        int siirto = 0;
        
        while (true) {
            siirto++;
            Lauta dynaaminen = new Lauta(6, 6, peliLauta.ruudut(), siirto);
            Siirto s1 = aly1.valitseSiirto(10, dynaaminen);
            peliLauta.simuloiSiirto(s1);
            
            System.out.println(siirto + " Tekoälyn 1 valitsema siirto: " + s1);
            System.out.println(peliLauta.laudanTulostus());
            Thread.sleep(2000);
            
            
            
            
            siirto++;
            dynaaminen = new Lauta(6, 6, peliLauta.ruudut(), siirto);
            Siirto s2 = aly2.valitseSiirto(10, dynaaminen);
            peliLauta.simuloiSiirto(s2);
            
            System.out.println(siirto + " Tekoälyn 2 valitsema siirto: " + s2);
            System.out.println(peliLauta.laudanTulostus());
            Thread.sleep(2000);
        }
    }
}
