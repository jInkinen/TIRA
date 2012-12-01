
package tira;

/**
 * main-luokka testausta ja käyttöä varten.
 */
public class Shakki {

    /**
     * @param args the command line arguments
     * 
     * 
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
            siirto++;
            siirrot[siirto - 1] = aly1.valitseSiirto(5, siirto - 1, siirrot);
            
            
            System.out.println("Siirto " + siirto + " Tekoälyn 1 valitsema siirto: " + siirrot[siirto - 1]);
//            for (int i = 0; i < siirto; i++) {
//                System.out.println(i + " " + siirrot[i]);
//            }
            peliLauta.toteutaSiirto(siirrot[siirto - 1]);

            System.out.println(peliLauta.laudanTulostus());
            Thread.sleep(100);

            //MUSTA
            siirto++;
            siirrot[siirto - 1] = aly2.valitseSiirto(5, siirto - 1, siirrot);
            
            
            System.out.println("Siirto " + siirto + " Tekoälyn 2 valitsema siirto: " + siirrot[siirto - 1]);
            peliLauta.toteutaSiirto(siirrot[siirto - 1]);

            System.out.println(peliLauta.laudanTulostus());
            Thread.sleep(100);
        }
    }
}
