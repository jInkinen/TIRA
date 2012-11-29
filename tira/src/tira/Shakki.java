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
     * 
     * main-luokka testausta ja käyttöä varten.
     */
    public static void main(String[] args) throws InterruptedException {

        Lauta peliLauta = new Lauta();
        Lauta tilanne;

        Tekoaly aly1 = new Tekoaly(true);
        Tekoaly aly2 = new Tekoaly(false);
        int siirto = 0;
        
        Siirto[] siirrot = new Siirto[1000];

        while (true) {
            //VALKOINEN
            tilanne = new Lauta();
            tilanne.toteutaSiirrot(siirrot, siirto);
            
            siirrot[siirto] = aly1.valitseSiirto(5, tilanne, siirto);
            siirto++;
            
            System.out.println(siirto + " Tekoälyn 1 valitsema siirto: " + siirrot[siirto - 1]);
            peliLauta.toteutaSiirto(siirrot[siirto - 1]);

            System.out.println(peliLauta.laudanTulostus());
            Thread.sleep(1000);

            //MUSTA
            tilanne = new Lauta();
            tilanne.toteutaSiirrot(siirrot, siirto);
            
            siirrot[siirto] = aly2.valitseSiirto(5, tilanne, siirto);
            siirto++;
            
            System.out.println(siirto + " Tekoälyn 2 valitsema siirto: " + siirrot[siirto - 1]);
            peliLauta.toteutaSiirto(siirrot[siirto - 1]);

            System.out.println(peliLauta.laudanTulostus());
            Thread.sleep(1000);
        }
    }
}
