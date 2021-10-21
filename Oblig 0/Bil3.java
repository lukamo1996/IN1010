//Oppgave 3
public class Bil3 extends Bil2{
    //Konstruktør
    Bil3(String bilNummer){
        super(bilNummer);
    }

    //Hent bilnummer
    public String hentNummer(){
        return this.bilNummer;
    }
}