public class Blaaresept extends Resept{
    protected Double rabatt = 0.25;
    protected String rabattTekst = "75%";

    public Blaaresept(Legemiddel legemiddel, Lege utskrivendeLege, Integer pasientId, Integer reit){
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }
    public String farge(){
        return "blaa";
    }
    public Integer prisAaBetale(){
        return (int) (this.hentLegemiddel().hentPris() * this.rabatt);
    }    
    @Override
    public String toString(){
        return "\nBlaaresept:\nID: " + this.hentId() + "\nPasientID: " + this.hentPasientId()+ "\nReit: " + this.hentReit() + "\nLegemiddel: " + this.hentLegemiddel().hentNavn() + "\nUtskrivende Lege: " + this.hentLege().hentNavn() + "\nRabatt: " + this.rabattTekst + "\nType resept: " + this.farge() + "\n";
    }
}