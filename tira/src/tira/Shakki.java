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
        Tekoaly aly1 = new Tekoaly(1);
        Tekoaly aly2 = new Tekoaly(-1);
        int siirto = 0;
//        while (true) {
            siirto++;
            Lauta dynaaminen = new Lauta(6, 6, peliLauta.ruudut(), siirto);
            Siirto s1 = aly1.valitseSiirto(10, dynaaminen);
            System.out.println("Tekoälyn valitsema siirto: " + s1);
            peliLauta.simuloiSiirto(s1);
            
            dynaaminen = new Lauta(6, 6, peliLauta.ruudut(), siirto);
            Siirto s2 = aly2.valitseSiirto(10, dynaaminen);
            System.out.println("Tekoälyn valitsema siirto: " + s2);
            peliLauta.simuloiSiirto(s2);
            
            System.out.println("OIKEA TILANNE:");
            System.out.println(peliLauta.laudanTulostus());
//        }
        
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
