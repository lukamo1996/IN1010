import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

//Legesystem
public class Legesystem {
    Lenkeliste<Pasient> pasienter;
    Lenkeliste<Legemiddel> legemidler;
    SortertLenkeliste<Lege> leger;
    Lenkeliste<Resept> resepter;

    //Konstruktoer
    public Legesystem(){
        this.leger = new SortertLenkeliste<Lege>();
        this.legemidler = new Lenkeliste<Legemiddel>();
        this.resepter = new Lenkeliste<Resept>();
        this.pasienter = new Stabel<Pasient>();
    }

    //Les inn fra fil. Txt-fil blir konvertert til bytebuffer -> String Array vha. regex -> fjerner foerste tomme element. Resten er vanlig parsing
    public void lesInnFraFil(String filnavn) throws IOException, FileNotFoundException, UlovligUtskrift{
        Integer index = 0;
        try{
            byte[] allTekstBuffer = Files.readAllBytes(Paths.get(filnavn));
            String[] allTekstString = new String(allTekstBuffer, StandardCharsets.US_ASCII).split("(#.*)");
            allTekstString = Arrays.copyOfRange(allTekstString, 1, allTekstString.length);

            for (String s : allTekstString){
                s = s.trim();
                if(index == 0) this.leggTilPasient(s);
                else if(index == 1) this.leggTilLegemiddel(s);
                else if(index == 2) this.leggTilLege(s);
                else if(index == 3) this.leggTilResept(s);
                index++;
            }
        }
        catch(Exception e){
          System.out.println(e);
        }
    }

    //Legg til pasient
    public void leggTilPasient(String string){
        String[] splittaStreng = string.split("\n");
        for(String s : splittaStreng){
            String[] ss = s.split(",");
            String navn = ss[0].trim();
            String foedselsnummer = ss[1].trim();
            this.pasienter.leggTil(new Pasient(navn, foedselsnummer));
        }
    }

    //Legg til legemiddel
    public void leggTilLegemiddel(String string){
        String[] splittaStreng = string.split("\n");
        for(String s : splittaStreng){
            Integer index = null;
            String[] ss = s.split(",");

            //Vi maa finne forekomsten av typen som skal legges til, og deretter parse pga en saann liten "luring" i myeinndata.txt filen. Resten er som vanlig
            for(Integer i = 0; i < ss.length; i++){
                if(ss[i].equals("vanedannende") || ss[i].equals("narkotisk") || ss[i].equals("vanlig")){
                    index = i;
                    break;
                }
            }

            String type = ss[index].trim();
            String navn = ss[0].trim();
            Integer pris = Double.valueOf(ss[index + 1].trim()).intValue();
            Double virkestoff = Double.parseDouble(ss[index + 2].trim());
            Integer styrke = null;

            if(index + 3 < ss.length){
              styrke = Integer.parseInt(ss[index + 3].trim());
            }

            if(type.equals("narkotisk")) {
                Narkotisk narkotisk = new Narkotisk(navn, pris, virkestoff);
                this.legemidler.leggTil(narkotisk);
                narkotisk.settStyrke(styrke);
            }
            else if(type.equals("vanedannende")){
                Vanedannende vanedannende = new Vanedannende(navn, pris, virkestoff);
                this.legemidler.leggTil(vanedannende);
                vanedannende.settStyrke(styrke);
            }
            else if(type.equals("vanlig")){
                Vanlig vanlig = new Vanlig(navn, pris, virkestoff);
                this.legemidler.leggTil(vanlig);
            }
        }
    }

    //Legg til Lege
    public void leggTilLege(String string){
        String[] splittaStreng = string.split("\n");
        for(String s : splittaStreng){
            String[] ss = s.split(",");
            String navn = ss[0].trim();
            String kontrollId = ss[1].trim();
            if(kontrollId.equals("0")) this.leger.leggTil(new Lege(navn));
            else this.leger.leggTil(new Spesialist(navn, kontrollId));
        }
    }

    //Legg til Resept
    public void leggTilResept(String string) throws UlovligUtskrift{
        String[] splittaStreng = string.split("\n");
        for(String s : splittaStreng){
            String[] ss = s.split(",");
            Integer legemiddelID = Integer.parseInt(ss[0].trim());
            String legeNavn = ss[1].trim();
            Integer pasientId = Integer.parseInt(ss[2].trim());
            String type = ss[3].trim();
            Integer reit = ss.length > 4 ? Integer.parseInt(ss[4].trim()) : null;

            //Finn Ting
            Legemiddel legemiddel = this.finnLegemiddel(legemiddelID);
            Lege utskrivendeLege = this.finnLege(legeNavn);
            Pasient pasient = this.finnPasient(pasientId);

            if(type.equals("hvit")){
              try{
                Resept nyResept = new HvitResept(legemiddel, utskrivendeLege, pasient, reit);
                utskrivendeLege.skrivHvitResept(legemiddel, pasient, reit);
                pasient.leggTilResept(nyResept);
                this.resepter.leggTil(nyResept);
              }
              catch(UlovligUtskrift e){
                System.out.println(e);
              }
              catch(NullPointerException e){
                if(pasient == null) System.out.println(e + " --> " + "Pasient med pasientID " + pasientId + " finnes ikke.");
                if(legemiddel == null) System.out.println(e + " --> " + "Legemiddel med ID " + legemiddelID + " finnes ikke.");
                if(utskrivendeLege == null) System.out.println(e + " --> " + "Lege med navn " + legeNavn + " finnes ikke.");
              }
            }
            else if(type.equals("blaa")){
              try{
                Resept nyResept = new BlaaResept(legemiddel, utskrivendeLege, pasient, reit);
                utskrivendeLege.skrivBlaaResept(legemiddel, pasient, reit);
                pasient.leggTilResept(nyResept);
                this.resepter.leggTil(nyResept);
              }
              catch(UlovligUtskrift e){
                System.out.println(e);
              }
              catch(NullPointerException e){
                if(pasient == null) System.out.println(e + " --> " + "Pasient med pasientID " + pasientId + " finnes ikke.");
                if(legemiddel == null) System.out.println(e + " --> " + "Legemiddel med ID " + legemiddelID + " finnes ikke.");
                if(utskrivendeLege == null) System.out.println(e + " --> " + "Lege med navn " + legeNavn + " finnes ikke.");
              }
            }
            else if(type.equals("millitaer")){
              try{
                Resept nyResept = new MillitaerResept(legemiddel, utskrivendeLege, pasient, reit);
                utskrivendeLege.skrivMillitaerResept(legemiddel, pasient, reit);
                pasient.leggTilResept(nyResept);
                this.resepter.leggTil(nyResept);
              }
              catch(UlovligUtskrift e){
                System.out.println(e);
              }
              catch(NullPointerException e){
                if(pasient == null) System.out.println(e + " --> " + "Pasient med pasientID " + pasientId + " finnes ikke.");
                if(legemiddel == null) System.out.println(e + " --> " + "Legemiddel med ID " + legemiddelID + " finnes ikke.");
                if(utskrivendeLege == null) System.out.println(e + " --> " + "Lege med navn " + legeNavn + " finnes ikke.");
              }
            }
            else if(type.equals("p")){
              try{
                Resept nyResept = new PResept(legemiddel, utskrivendeLege, pasient);
                utskrivendeLege.skrivPResept(legemiddel, pasient);
                pasient.leggTilResept(nyResept);
                this.resepter.leggTil(nyResept);
              }
              catch(UlovligUtskrift e){
                System.out.println(e);
              }
              catch(NullPointerException e){
                if(pasient == null) System.out.println(e + " --> " + "Pasient med pasientID " + pasientId + " finnes ikke.");
                if(legemiddel == null) System.out.println(e + " --> " + "Legemiddel med ID " + legemiddelID + " finnes ikke.");
                if(utskrivendeLege == null) System.out.println(e + " --> " + "Lege med navn " + legeNavn + " finnes ikke.");
              }
            }
      }
    }

    //Finn legemiddel som samsvarer med oppgitt id
    public Legemiddel finnLegemiddel(Integer id){
        for(Legemiddel legemiddel : this.legemidler){
            if(legemiddel.hentId() == id) return legemiddel;
        }
        return null;
    }

    // Overloader finnLegemiddel, slik at den alternativt kan ta en string.
    public Legemiddel finnLegemiddel(String navn) {
      for (Legemiddel legemiddel : this.legemidler){
        if(legemiddel.hentNavn().equals(navn)) {
          return legemiddel;
        }
      }
      return null;
    }

    //Finn lege som samsvarer meg legenavn
    public Lege finnLege(String legeNavn){
        for(Lege lege : this.leger){
            if(lege.hentNavn().equals(legeNavn)) {
                return lege;
            }
        }
        return null;
    }

    //Finn pasient som samsvarer med oppgitt id
    public Pasient finnPasient(Integer id){
        for(Pasient pasient : this.pasienter){
            if(pasient.hentId().equals(id)) {
                return pasient;
            }
        }
        return null;
    }

    // Overloader finnPasient slik at den alternativt kan ta inn en string
    public Pasient finnPasient(String navn) throws NumberFormatException {
      for (Pasient pasient : this.pasienter){
        if(pasient.hentNavn().equals(navn)) {
          return pasient;
        }
      }
      return null;
    }

    public Resept finnResept(String id) throws NumberFormatException{
      try{
        Integer reseptID = Integer.parseInt(id);
        for (Resept resept : this.resepter){
          if(resept.hentId().equals(reseptID)) {
            return resept;
          }
        }
        return null;
      }
      catch(NumberFormatException e){
        System.out.println("Dette er ikke en id");
        return null;
      }
    }

    public Resept finnReseptForPasientMedID(Pasient pasient, Integer valgtResept) throws NumberFormatException {
      Pasient funnetPasient = finnPasient(pasient.hentId());
      for(Resept resept : funnetPasient.hentResepter()){
        if(resept.hentId().equals(valgtResept)){
          return resept;
        }
      }
      return null;
    }

    public Resept finnReseptForPasientMedInt(Pasient pasient, Integer valgtResept) throws NumberFormatException {
      Pasient funnetPasient = finnPasient(pasient.hentId());
      Stabel<Resept> pasientensResepter = funnetPasient.hentResepter();
      for(Integer i = 0; i < pasientensResepter.stoerrelse(); i++){
        if(i.equals(valgtResept)){
          return pasientensResepter.hent(i);
        }
      }
      return null;
    }
    // Oppgave E2
    public void kommandoLoop() {
        //Man kan ikke bruke private inni en metode, bare final, saa jeg bare fjerna det (luka) ingenting = local variabel
        boolean fortsett = true;
        String brukerInput;
        Scanner scanner = new Scanner(System.in);

        while (fortsett) {
            // Skriver ut hvilke alternativer brukeren har
            kommandoLoopBeskjed();

            brukerInput = scanner.nextLine();
            // Sjekker om brukeren vil avslutte programmet.
            if (brukerInput.equals("q")) {
              System.out.println("Avslutter program...");
              fortsett = false;
            }
            else if (brukerInput.equals("o")) {
                // Skriv inn funksjon fra oppgave E3
                skrivUtAlleElementer();
            }
            else if (brukerInput.equals("l")) {
                // Skriv inn funksjon fra oppgave E4
                leggTilISystem();
            }
            else if (brukerInput.equals("b")) {
                // Skriv inn funksjon fra oppgave E5
                brukEnResept();
            }
            else if (brukerInput.equals("s")) {
                // Skriv inn funksjon fra oppgave E6
                visStatistikk();
            }
            else if (brukerInput.equals("w")) {
                // Skriv inn funksjon fra oppgave E7
                System.out.println("Vennligst oppgi navnet paa filen du oenser aa skrive til:");
                String filnavn = scanner.nextLine();
                skrivTilFil(filnavn);
            }
            else {
                System.out.println("hmm.. Det der forstod jeg ikke helt. Vennligst brukt en av kommandoene over!");
            }
        }
        scanner.close();
    }
    private void kommandoLoopBeskjed() {
      System.out.println("\n\nVennligst velg mellom folgende kommandoer: ");
      System.out.println("  --> Skriv ut fullstendig oversikt:       o" );
      System.out.println("  --> Opprette/legge til nye elementer:    l" );
      System.out.println("  --> Bruke en resept:                     b" );
      System.out.println("  --> Skrive ut statistikk:                s" );
      System.out.println("  --> Skrive all data til fil:             w" );
      System.out.println("  --> Avslutte programmet:                 q" );
      System.out.print("Kommando: ");
    }

    // Oppgave E3
    public void skrivUtAlleElementer() {
      System.out.println("\n\n\n\n\n-------- Skriver fullstendig systemoversikt --------\n\n");
      // Skriver ut pasienter
      System.out.println("Pasienter som er registrert i systemet:");
      for (Pasient pasient : pasienter) {
        System.out.println("-> " + pasient);
      }
      System.out.println("\nLegemidlene som er registrert i systemet:");
      for (Legemiddel legemiddel : legemidler) {
        System.out.println("-> " + legemiddel);
      }
      System.out.println("\nReseptene som er registrert i systemet:");
      for (Resept resept : this.resepter) {
        System.out.println("-> " + resept);
      }
      System.out.println("\nLegene som er registrert i systemet:");
      for (Lege lege : leger) {
        System.out.println("-> " + lege);
      }
    }

    // Oppgave E4
    public void leggTilISystem() {
      // Spoer brukeren hva han/hun oensker aa legge til.
      System.out.println("\n\nHvilken av foelgende oensker du aa legge til?");
      System.out.println("  --> Lege:        l");
      System.out.println("  --> Pasient:     p");
      System.out.println("  --> Resept:      r");
      System.out.println("  --> Legemiddel:  lm");

      Scanner scanner = new Scanner(System.in);
      String brukerInput = scanner.nextLine();

      // Legge til ny lege
      if (brukerInput.equals("l")) {
        String navn = "";
        String spesialistInput = "";

        System.out.println("Du har valgt aa legge til en ny lege:");
        System.out.println("Vennligst oppgi foelgende:");
        System.out.println("Navn:");
        navn = scanner.nextLine();
        System.out.println("Er denne legen en spesialist? [y/n]");
        spesialistInput = scanner.nextLine();

        if (spesialistInput.equals("y")) {
          // Oppretter spesialist
          // Maa be om kontrollId
          String kontrollId = "";
          System.out.println("Vennligst oppgi kontroll id for denne spesialist legen:");
          kontrollId = scanner.nextLine();
          Spesialist nySpesialist = new Spesialist(navn, kontrollId);
          leger.leggTil(nySpesialist);
          System.out.println("Spesialist lege lagt til.");
          return;
        }
        else if (spesialistInput.equals("n")) {
          // Oppretter en vanlig lege.
          Lege lege = new Lege(navn);
          leger.leggTil(lege);
          System.out.println("Lege lagt til.");
          return;
        }
        else {
          System.out.println("Ugyldig input, forsoek igjen.");
          return;
        }


      } // Legge til ny pasient
      else if (brukerInput.equals("p")) {
        String navn = "";
        String foedselsnummer = "";
        boolean gyldigInput = true;
        System.out.println("Du har valgt aa legge til en ny pasient.");
        System.out.println("Vennligst oppgi foelgende:");

        // Ber om navn (og sjekker at vi har fatt en string)
        System.out.println("Navn:");
        if (scanner.hasNext()) {
          navn = scanner.nextLine();
        } else {gyldigInput = false;}

        // Ber om foedselsnummer (og sjekker at vi har fatt en string)
        System.out.println("Foedselsnummer:");
        if (scanner.hasNext()) {
          foedselsnummer = scanner.nextLine();
        } else {
          gyldigInput = false;
        }

        // Dersom input er gyldig opprettes en ny pasient og legges til i oversikten over pasienter
        if (gyldigInput) {
          Pasient nyPasient = new Pasient(navn, foedselsnummer);
          pasienter.leggTil(nyPasient);
          System.out.println("Ny pasient ble opprettet.");
        } else {
          System.out.println("Ugyldig input. Vennligst Forsoek paa nytt.");
        }


      } // Legge til ny resept
      else if (brukerInput.equals("r")) {
        String typeResept;
        String legemiddelNavn;
        String utskrivendeLegeNavn;
        String pasientNavn;
        int reit;

        System.out.println("Du har valgt aa legge til en ny resept.");
        System.out.println("Oppgi typen resept du oensker aa legge til:");
        System.out.println("  --> Hvit resept:           h");
        System.out.println("  --> Blaa resept:           b");
        System.out.println("  --> PResept:               p");
        System.out.println("  --> Millitaer resept:      m");
        typeResept = scanner.nextLine().toLowerCase();

        // Tar inn info om resept fra bruker
        System.out.println("Oppgi navn paa legemiddel: ");
        skrivUtListeAvLegemidlerTilBruker();
        legemiddelNavn = scanner.nextLine().trim();
        System.out.println("Oppgi navn paa pasient:");
        skrivUtListeAvPasienterTilBruker();
        pasientNavn = scanner.nextLine().trim();
        System.out.println("Oppgi navn paa utskrivende lege: ");
        skrivUtListeAvLegerTilBruker();
        utskrivendeLegeNavn = scanner.nextLine().trim();
        System.out.println("Oppgi reit for denne resepten: ");

        try {
          reit = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
          System.out.println("Foelgende error oppstod ved lesing av reit input: " + e);
          return;
        }

        // Sjekk for gyldig lege.
        Lege lege = finnLege(utskrivendeLegeNavn);
        if (lege == null) {
          System.out.println("Ugyldig lege. Forsoek igjen.");
          // Hopper ut av metoden dersom lege ikke finnes i systemet.
          return;
        }

        // Sjekker at legemiddel finnes
        Legemiddel legemiddel = finnLegemiddel(legemiddelNavn);
        if (legemiddel == null) {
          System.out.println("Ugyldig legemiddel. Forsoek igjen.");
          // Hopper ut av metoden dersom lege ikke finnes i systemet.
          return;
        }

        // Sjekker at pasient finnes
        Pasient pasient = finnPasient(pasientNavn);
        if (pasient == null) {
          System.out.println("Ugyldig pasient. Forsoek igjen.");
          // Hopper ut av metoden dersom pasient ikke finnes i systemet.
          return;
        }

        if (typeResept.equals("h")) {
          // Forsok aa opprette hvit resept
          try {
            lege.skrivHvitResept(legemiddel, pasient, reit);
          } catch (UlovligUtskrift e) {
            System.out.println(e);
          }
        }
        else if (typeResept.equals("b")) {
          // Forsok aa opprette blaa resept
          try {
            lege.skrivBlaaResept(legemiddel, pasient, reit);
          } catch (UlovligUtskrift e) {
            System.out.println(e);
          }
        }
        else if (typeResept.equals("p")) {
          // Forsok aa opprette p resept
          try {
            lege.skrivPResept(legemiddel, pasient);
          } catch (UlovligUtskrift e) {
            System.out.println(e);
          }
        }
        else if (typeResept.equals("m")) {
          // Forsok aa opprette m resept
          try {
            lege.skrivMillitaerResept(legemiddel, pasient, reit);
          } catch (UlovligUtskrift e) {
            System.out.println(e);
          }
        } else {
          System.out.println("Ugyldig resept valgt. Forsoek paa nytt");
        }

      } // Legge til nytt legemiddel
      else if (brukerInput.equals("lm")) {
        String valgtTypeLegemiddel = "";
        String navn = "";
        int pris;
        double virkestoff;
        int styrke;
        System.out.println("Du har valgt aa legge til et nytt legemiddel.");
        System.out.println("Oppgi typen legemiddel du oensker aa legge til:");
        System.out.println("  --> Vanlig:           v\n  --> Narkotisk:        n\n  --> Vanedannende:     vd");
        valgtTypeLegemiddel = scanner.nextLine();
        // Spoer ogsaa om de andre attributtene som er felles for alle legemidler
        System.out.println("Oppgi navn paa legemiddelet: ");
        navn = scanner.nextLine();
        try {
          System.out.println("Oppgi pris:");
          pris = Integer.parseInt(scanner.nextLine());
          System.out.println("Oppgi virkestoff:");
          virkestoff = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
          System.out.println("Foelgende error oppstod ved lesing av pris eller virkestoff input: " + e);
          return;
        }
        if (valgtTypeLegemiddel.equals("v")) {
          // Oppretter et vanlig legemiddel
          Vanlig nyttLegemiddel = new Vanlig(navn, pris, virkestoff);
          legemidler.leggTil(nyttLegemiddel);
          return;
        }
        else if (valgtTypeLegemiddel.equals("n")) {
          System.out.println("Oppgi styrke:");
          try {
            styrke = Integer.parseInt(scanner.nextLine());
          } catch (NumberFormatException e ) {
            System.out.println("Foelgende error oppstod ved lesing av styrke input: " + e);
            return;
          }
          Narkotisk nyttLegemiddel = new Narkotisk(navn, pris, virkestoff, styrke);
          legemidler.leggTil(nyttLegemiddel);
          return;
        }
        else if (valgtTypeLegemiddel.equals("vd")) {
          System.out.println("Oppgi styrke:");
          try {
            styrke = Integer.parseInt(scanner.nextLine());
          } catch (NumberFormatException e ) {
            System.out.println("Foelgende error oppstod ved lesing av styrke input: " + e);
            return;
          }
          Vanedannende nyttLegemiddel = new Vanedannende(navn, pris, virkestoff, styrke);
          legemidler.leggTil(nyttLegemiddel);
          return;
        } else {
          System.out.println("Vennligst oppgi gyldig legemiddel type.");
        }
      }
      else {
        System.out.println("Vennligst oppgi gyldig kommando.");
      }
    }

    // Hjelpemetode som skriver ut navn paa leger.
    private void skrivUtListeAvLegerTilBruker() {
      for (Lege l : leger) {System.out.println(l.hentNavn());}
    }
    // Hjelpemetode som skriver ut navn paa pasienter
    private void skrivUtListeAvPasienterTilBruker() {
      for (Pasient p : pasienter) {
        System.out.println(p.hentId() + ". " + p.hentNavn());
      }
    }
    // Hjelpemetode som skriver ut navn paa legemideler
    private void skrivUtListeAvLegemidlerTilBruker() {
      for (Legemiddel lm : legemidler) {
        System.out.println(lm.hentNavn());
      }
    }

    //Skriver ut resepter for pasient
    public void skrivUtResepterForPasient(Pasient pasient){
      Pasient funnetPasient = finnPasient(pasient.hentId());
      Stabel<Resept> pasientResepter = funnetPasient.hentResepter();
      Resept currentResept = null;
      for(Integer i = 0; i < pasientResepter.stoerrelse(); i++){
        currentResept = pasientResepter.hent(i);
        System.out.println(i + ": " + currentResept.hentLegemiddel().hentNavn() + " (" + currentResept.hentReit() + ")");
      }
    }

    public Boolean brukResept(Resept resept){
      return resept.bruk();
    }

    //Oppgave E5
    public void brukEnResept() throws NumberFormatException {
      Scanner scanner = new Scanner(System.in);
      System.out.println("\nHvilken pasient vil du se resepter for?");
      skrivUtListeAvPasienterTilBruker();
      System.out.print("Kommando: ");
      String valgtPasient = scanner.nextLine().trim();
      Pasient funnetPasient = null;

      //Foerst sjekker vi om strengen som passes inn er et navn eller en ID
      try{
        Integer.parseInt(valgtPasient.substring(0, 1));
        funnetPasient = finnPasient(Integer.parseInt(valgtPasient));
      }
      catch(NumberFormatException e){
        funnetPasient = finnPasient(valgtPasient);
      }
      if(funnetPasient == null){
        System.out.println("Den pasienten finnes ikke, proev noe annet");
        return;
      }
      else if(funnetPasient.hentResepter().stoerrelse() == 0){
        System.out.println("Denne pasienten har ingen resepter.");
        return;
      }

      System.out.println("\nValgt pasient: " + funnetPasient.hentNavn() + " (" + "fnr " + funnetPasient.hentFoedselsnummer() + ")");
      System.out.println("Hvilken resept vil du bruke?");
      skrivUtResepterForPasient(funnetPasient);
      System.out.print("Kommando: ");
      String valgtResept = scanner.nextLine().trim();

      //Skriver inn id paa resepten
      try{
        Integer resept = Integer.parseInt(valgtResept.substring(0, 1));
        Resept onsketResept = finnReseptForPasientMedInt(funnetPasient, Integer.parseInt(valgtResept));
        if(onsketResept == null){
          System.out.println(funnetPasient.hentNavn() + " har ikke denne resepten");
          return;
        }
        //Her bruker vi resepten
        if(onsketResept.bruk()){
          System.out.println("Resepten har blitt brukt");
          System.out.println("Brukte resept paa " + onsketResept.hentLegemiddel().hentNavn() + "Antall gjenvaerende reit: " + onsketResept.hentReit());
        }
        else{
          System.out.println("Kunne ikke bruke resept paa Paracet (ingen gjenvaerende reit)");
        }
        return;
      }
      catch(NumberFormatException e){
        System.out.println("En resept identifiseres med et tall og ikke bokstaver, vær vennlig og tast inn et tall. Mr. Cocky Bastard.");
        return;
      }
    }

    public Integer totaltAntallVanedannendeLegemidler(){
      Integer antall = 0;
      for(Resept resept : this.resepter){
        if(resept.hentLegemiddel() instanceof Vanedannende){
          antall = antall + 1;
        }
      }
      return antall;
    }

    public Integer totaltAntallNarkotiskeLegemidler(){
      Integer antall = 0;
      for(Resept resept : this.resepter){
        if(resept.hentLegemiddel() instanceof Narkotisk){
          antall++;
        }
      }
      return antall;
    }

    //Returnerer antallet leger i sortert rekkefoelge og antallet narkotiske legemidler de har utskrevet
    public TreeMap<String, Integer> totaltAntallLegerUtskrevetNarkotiske(){
      TreeMap<String, Integer> sortertLegeListe = new TreeMap<String, Integer>();
      for(Lege lege : this.leger){
        for(Resept resept : lege.hentUtskrevedeResepter()){
          if(resept.hentLegemiddel() instanceof Narkotisk){
            if(sortertLegeListe.get(lege.hentNavn()) == null){
              sortertLegeListe.put(lege.hentNavn(), 1);
            }
            else{
              sortertLegeListe.put(lege.hentNavn(), sortertLegeListe.get(lege.hentNavn()) + 1);
            }
          }
        }
      }
      return sortertLegeListe;
    }

    public TreeMap<String, Integer> totaltAntallLegerUtskrevetVanedannende(){
      TreeMap<String, Integer> sortertLegeListe = new TreeMap<String, Integer>();
      for(Lege lege : this.leger){
        for(Resept resept : lege.hentUtskrevedeResepter()){
          if(resept.hentLegemiddel() instanceof Vanedannende){
            if(sortertLegeListe.get(lege.hentNavn()) == null){
              sortertLegeListe.put(lege.hentNavn(), 1);
            }
            else{
              sortertLegeListe.put(lege.hentNavn(), sortertLegeListe.get(lege.hentNavn()) + 1);
            }
          }
        }
      }
      return sortertLegeListe;
    }

    //Oppgave E6
    public void visStatistikk(){
      Integer totaltVanedannende = totaltAntallVanedannendeLegemidler();
      Integer totaltNarkotiske = totaltAntallNarkotiskeLegemidler();
      TreeMap<String, Integer> alleLegerSomHarUtskrevetNarkotiske = totaltAntallLegerUtskrevetNarkotiske();
      TreeMap<String, Integer> alleLegerSomHarUtskrevetVanedannende = totaltAntallLegerUtskrevetVanedannende();

      System.out.println("--> Total statistikk:");
      System.out.println("Det har blitt utskrevet " + totaltVanedannende + " vanedannende legemidler");
      System.out.println("Det har blitt utskrevet " + totaltNarkotiske + " narkotiske legemidler\n");
      System.out.println("--> Liste over alle legene og antall ganger de har utskrevet et narkotisk legemiddel:");
      System.out.println(alleLegerSomHarUtskrevetNarkotiske);
      System.out.println("--> Liste over alle legene og antall ganger de har utskrevet et vanedannende legemiddel:");
      System.out.println(alleLegerSomHarUtskrevetVanedannende);
      // for(Map.Entry<String, Integer> entry : alleLegerSomHarUtskrevetNarkotiske.entrySet()){
      //   System.out.println(entry.getKey() + " har skrevet ut " + entry.getValue() + " narkotiske legemidler.");
      // }

    }

    //Oppgave E7
    // Skriver all informasjon i systemet til en fil.
    public void skrivTilFil(String filnavn) {
      // Oppretter ny fil med oppgitt filnavn hvis det ikke finnes fra foer
      try {
        File file = new File(filnavn);
        if (file.createNewFile()) {
          System.out.println("File created.");
        }
        else {
            System.out.println("File already exists.");
        }
      } catch (IOException e) {
        System.out.println("Noe gikk galt ved filopprettelse: " + e);
        return;
      }

      // Skriver til fil
      try {
        FileWriter skriver = new FileWriter(filnavn);
        // Skriver ut pasient data
        skriver.write("# Pasienter (navn, fnr)");
        for (Pasient p : pasienter) {
          skriver.write("\n" + p.hentNavn() + "," + p.hentFoedselsnummer());
        }
        // Skriver ut legemiddel data
        skriver.write("\n# Legemidler (navn,type,pris,virkestoff,[styrke])");
        for (Legemiddel lm : legemidler) {
          // Sjekk om legemiddel er vanlig eller ikke (for vanlige legemidler kan vi ikke skrive ut styrke)
          if (lm instanceof Vanlig) {
            skriver.write("\n" + lm.hentNavn() + "," + "vanlig" + "," + lm.hentPris() + "," + lm.hentVirkestoff());
          } else if (lm instanceof Narkotisk) {
            Narkotisk lmNarkotisk = (Narkotisk) lm;
            skriver.write("\n" + lmNarkotisk.hentNavn() + "," + "narkotisk" + "," + lmNarkotisk.hentPris() + "," + lmNarkotisk.hentVirkestoff() + "," + lmNarkotisk.hentNarkotiskStyrke());
          } else if (lm instanceof Vanedannende) {
            Vanedannende lmVanedannende = (Vanedannende) lm;
            skriver.write("\n" + lmVanedannende.hentNavn() + "," + "vanedannende" + "," + lmVanedannende.hentPris() + "," + lmVanedannende.hentVirkestoff() + "," + lmVanedannende.hentVanedannendeStyrke());
          }
        }
        // Skriver ut lege data
        skriver.write("\n# Leger (navn,kontrollid / 0 hvis vanlig lege)");
        for (Lege lege : leger) {
          if (lege instanceof Spesialist) {
            Spesialist spesialist = (Spesialist) lege;
            skriver.write("\n" + spesialist.hentNavn() + "," + spesialist.hentKontrollID());
          } else {
            skriver.write("\n" + lege.hentNavn() + "," + "0");
          }
        }

        // Skriver ut resept data
        skriver.write("\n# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])");
        for (Resept r : resepter) {
          // Her er det avgjoerende at vi sjekker Presept og MillitaerResept foerst fordi de er subklasser av Hvit resept og derfor ville instanceof hvit ogsaa bli sant.
          if (r instanceof PResept) {
            skriver.write("\n" + r.hentLegemiddel().hentId() + "," + r.hentLege().hentNavn() + "," + r.hentPasient().hentId() + "," + "p");
          } else if (r instanceof MillitaerResept) {
            skriver.write("\n" + r.hentLegemiddel().hentId() + "," + r.hentLege().hentNavn() + "," + r.hentPasient().hentId() + "," + "millitaer" + "," + r.hentReit());
          } else if (r instanceof HvitResept) {
            skriver.write("\n" + r.hentLegemiddel().hentId() + "," + r.hentLege().hentNavn() + "," + r.hentPasient().hentId() + "," + "hvit" + "," + r.hentReit());
          } else if (r instanceof BlaaResept) {
            skriver.write("\n" + r.hentLegemiddel().hentId() + "," + r.hentLege().hentNavn() + "," + r.hentPasient().hentId() + "," + "blaa" + "," + r.hentReit());
          }
        }

        skriver.close();
        System.out.println("Skrevet til fil.");
      } catch (IOException e) {
        System.out.println("Noe gikk galt ved skriving til fil: " + e);
      }
    }
}
