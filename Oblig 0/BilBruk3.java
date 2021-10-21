//Oppgave 3
import java.util.Scanner;

public class BilBruk3 {
    public static void main(String[] args){
        //Start scanner
        Scanner Scanner = new Scanner(System.in);

        //Motta første input
        System.out.println("Hei, hva skal bilnummeret ditt være?");
        String bilNummer = Scanner.nextLine();

        //Lukk scanner
        Scanner.close();

        //Lag et Bil-objekt
        Bil3 bil3 = new Bil3(bilNummer);

        //Lag person-objekt
        Person person = new Person(bil3);

        //Skriv ut bilnummeret til personen
        person.skrivUtBilnummer();
    }
}