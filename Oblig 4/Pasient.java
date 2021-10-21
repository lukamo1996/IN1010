public class Pasient {
    private String navn;
    private String foedselsnummer;
    private int ID;
    private Stabel<Resept> utskrevneResepter = new Stabel<>();
    public static int UID = 1;

    public Pasient(String navn, String foedselsnummer){
        this.navn = navn;
        this.foedselsnummer = foedselsnummer;
        this.ID = UID++;
    }

    public Integer hentId(){
        return this.ID;
    }

    public String hentNavn(){
        return this.navn;
    }

    public String hentFoedselsnummer() {
      return foedselsnummer;
    }

    public void leggTilResept(Resept resept){
        this.utskrevneResepter.leggPaa(resept);
    }

    public Stabel<Resept> hentResepter(){
      return utskrevneResepter;
    }

    @Override
    public String toString(){
        return "   Pasient\n      ID: " + this.ID + "\n      Navn: " + this.navn + "\n      Foedselsnummer: " + this.foedselsnummer + "\n";
    }
}
