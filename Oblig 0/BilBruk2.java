//Oppgave 2
import java.util.Scanner;
import java.util.Arrays;

public class BilBruk2 {
    public static void main(String[] args){
        //Start scanner
        Scanner Scanner = new Scanner(System.in);
        System.out.println("Hei, hva skal bilnummeret ditt være?");

        //Motta første input
        String bilNummer = Scanner.nextLine();

        System.out.println(Arrays.toString(bilNummer.split("")));
        Bil2 Bil2 = new Bil2(bilNummer);
        Bil2.printJegErEnBil();

        //Lukk scanner
        Scanner.close();
    }
}