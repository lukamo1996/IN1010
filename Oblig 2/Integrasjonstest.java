//Minimal integrasjonstest - Oppgave Del D: Integrasjonstest
public class Integrasjonstest {
    public static void main(String args[]){
        //Legemidler
        Vanlig sumifilam = new Vanlig("Sumifilam", 125, 20.0);
        Vanedannende kodein = new Vanedannende("Paralgin Forte", 100, 10.0, 50);
        Narkotisk metamfetamin = new Narkotisk("Desoxyn", 500, 30.0, 25);

        //Leger
        Spesialist spesialistLege = new Spesialist("Ole Nordmann", "ABC123");
        Lege lege = new Lege("Per Nordmann");

        //Resepter
        Blaaresept blaaresept = new Blaaresept(sumifilam, spesialistLege, 1, 5);
        Hvitresept hvitresept = new Hvitresept(sumifilam, lege, 2, 2);
        PResept presept = new PResept(kodein, lege, 3);
        MResept mresept = new MResept(metamfetamin, spesialistLege, 4, 4);

        //Skriver ut all informasjon om hvert enkelt objekt
        System.out.print(sumifilam);
        System.out.print(kodein);
        System.out.print(metamfetamin);
        System.out.print(spesialistLege);
        System.out.print(lege);
        System.out.print(blaaresept);
        System.out.print(hvitresept);
        System.out.print(presept);
        System.out.print(mresept);
    }   
}