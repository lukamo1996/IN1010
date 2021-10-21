//Oppgave 3
public class Person {
    //Initialiser attributter
    Bil3 bilenDeres;

    //Konstruktør
    Person(Bil3 bilenDeres){
        this.bilenDeres = bilenDeres;
    }

    //Metoder
    public void skrivUtBilnummer(){
        System.out.println("Dette er bilnummeret ditt: " + bilenDeres.hentNummer());
    }
}
