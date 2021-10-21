public class MResept extends Hvitresept{
    protected Integer rabatt = 0;
    protected String rabattTekst = "100%";

    public MResept(Legemiddel legemiddel, Lege utskrivendeLege, Integer pasientId, Integer reit){
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }
    @Override
    public Integer prisAaBetale(){
        Integer nyPris = this.hentLegemiddel().hentPris() * this.rabatt;
        return nyPris < 0 ? 0 : nyPris;
    }
    @Override
    public String toString(){
        return "\nMilitaerresept:\nID: " + this.hentId() + "\nPasientID: " + this.hentPasientId()+ "\nReit: " + this.hentReit() + "\nLegemiddel: " + this.hentLegemiddel().hentNavn() + "\nUtskrivende Lege: " + this.hentLege().hentNavn() + "\nRabatt: " + this.rabattTekst + "\nType resept: " + this.farge() + "\n";
    }
    
}