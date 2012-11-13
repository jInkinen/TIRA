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
    public static void main(String[] args) {
        Lauta peliLauta;
        peliLauta = new Lauta(6, 6);
        peliLauta.laudanTulostus();
        
        peliLauta.tulostaSiirtojenMaara();
        
        peliLauta.siirto(5, 1, 5, 2);
        peliLauta.laudanTulostus();
        
        peliLauta.tulostaSiirtojenMaara();
        
    }
}
