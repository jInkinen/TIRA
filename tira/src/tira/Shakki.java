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
        peliLauta = new Lauta();
        Tekoaly aly = new Tekoaly(1);
        
        Siirto s = aly.valitseSiirto(10, peliLauta);
        System.out.println("Tekoälyn valitsema siirto: " + s);

        System.out.println(peliLauta.laudanTulostus());
        
        /*Lauta peliLauta;
        peliLauta = new Lauta(6, 6);
        System.out.println(peliLauta.laudanTulostus());
        
        System.out.println(peliLauta.tulostaSiirtojenMaara());
        
        peliLauta.simuloiSiirto(5, 1, 5, 2);
        peliLauta.simuloiSiirto(4, 4, 4, 3);
        System.out.println(peliLauta.laudanTulostus());
        
        System.out.println(peliLauta.tulostaSiirtojenMaara());
        System.out.println(peliLauta.tulostaSiirtojenArvo());*/
    }
}
