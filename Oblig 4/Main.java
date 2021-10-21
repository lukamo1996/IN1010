import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main (String[] args) throws FileNotFoundException, IOException, UlovligUtskrift {
        Legesystem legesystem = new Legesystem();
        legesystem.lesInnFraFil("myeinndata.txt");

        /*
        for(Pasient pasient : legesystem.pasienter){
            System.out.println("________START________");
            System.out.println(pasient.hentNavn());
            System.out.println(pasient.hentResepter());
            System.out.println("________END__________");
        }
        */

        // Tester skrivUtAlleElementer() metoden
        // legesystem.skrivUtAlleElementer();

        // Tester kommando linje funksjonalitetb
        legesystem.kommandoLoop();

        // legesystem.skrivTilFil("TESTUTSKRIFT.txt");

    }
}
