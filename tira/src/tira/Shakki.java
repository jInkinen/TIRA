
package tira;

/**
 * main-luokka testausta ja käyttöä varten.
 */
public class Shakki {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {

        Lauta tilanne;

        Tekoaly aly1 = new Tekoaly(true);
        Tekoaly aly2 = new Tekoaly(false);
        int siirto = 0;
        
        Siirto[] siirrot = new Siirto[1000];

        while (true) {
            //VALKOINEN
            long start = System.nanoTime();
            tilanne = new Lauta();
            tilanne.toteutaSiirrot(siirrot, siirto);
            
            siirto++;
            siirrot[siirto - 1] = aly1.valitseSiirto(6, siirto - 1, siirrot);
            
            System.out.println("Siirto " + siirto + ": Tekoälyn 1 valitsema siirto: " + siirrot[siirto - 1]);
            tilanne.toteutaSiirto(siirrot[siirto - 1]);

            long end = System.nanoTime();
            double muunnos = (end - start) / 1000000000.0;
            System.out.println("Kesto: " + muunnos + "s");
            
            System.out.println(tilanne.laudanTulostus());
            Thread.sleep(100);

            //MUSTA
            tilanne = new Lauta();
            tilanne.toteutaSiirrot(siirrot, siirto);
            
            siirto++;
            siirrot[siirto - 1] = aly2.valitseSiirto(6, siirto - 1, siirrot);
            
            
            System.out.println("Siirto " + siirto + ": Tekoälyn 2 valitsema siirto: " + siirrot[siirto - 1]);
            tilanne.toteutaSiirto(siirrot[siirto - 1]);

            System.out.println(tilanne.laudanTulostus());
            Thread.sleep(100);
        }
    }
}

//syvyyden vaikutuksia solmujen määrään
//syvyys 7: niin suuri, etten jaksanut odottaa tuloksia...
//syvyys 6: 3 066 078
//syvyys 5: 178 490 <-- suositeltu syvyys
//syvyys 4: 11 177
//syvyys 3: 776
//syvyys 2: 63
//syvyys 1: 6