public class PResept extends Hvitresept{
    protected Integer rabatt = 108;
    protected String rabattTekst = "108NOK";

    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, Integer pasientId){
        super(legemiddel, utskrivendeLege, pasientId);
    }
    @Override
    public Integer prisAaBetale(){
        Integer nyPris = this.hentLegemiddel().hentPris() - this.rabatt;
        return nyPris < 0 ? 0 : nyPris;
    }
    @Override
    public String toString(){
        return "\nP-Resept:\nID: " + this.hentId() + "\nPasientID: " + this.hentPasientId()+ "\nReit: " + this.hentReit() + "\nLegemiddel: " + this.hentLegemiddel().hentNavn() + "\nUtskrivende Lege: " + this.hentLege().hentNavn() + "\nRabatt: " + this.rabattTekst + "\nType resept: " + this.farge() + "\n";
    }
}