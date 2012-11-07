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
        
        peliLauta.laskeSiirrot();
        
        /*peliLauta.onkoSiirtoMahdollinen(0, 1, 0, 2);
        peliLauta.laudanTulostus();
        
        peliLauta.onkoSiirtoMahdollinen(1, 4, 1, 3);
        peliLauta.laudanTulostus();
        
        peliLauta.onkoSiirtoMahdollinen(0, 2, 1, 3);
        peliLauta.laudanTulostus();
        
        peliLauta.onkoSiirtoMahdollinen(2, 4, 1, 3);
        peliLauta.laudanTulostus();*/
    }
}
