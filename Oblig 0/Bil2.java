//Oppgave 2
public class Bil2 extends Bil1{
    //Initialiser attributt
    String bilNummer;

    //Klassekonstruktør
	Bil2(String bilNummer) {
        this.bilNummer = bilNummer;
	}

	//Metode fra parent overrida
    public void printJegErEnBil(){
        System.out.println("Dette er bilnummeret mitt: " + this.bilNummer);
    }
}