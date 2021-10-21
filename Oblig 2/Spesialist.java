public class Spesialist extends Lege implements Godkjenningsfritak {
    private String kontrollID;

    public Spesialist(String navn, String kontrollID){
        super(navn);
        this.kontrollID = kontrollID;
    }

    public String hentKontrollID(){
        return this.kontrollID;
    }

    @Override
    public String toString(){
        return "\nSpesialistlege\nNavn: " + this.navn + "\nKontroll ID: " + this.kontrollID + "\n";
    }
}
