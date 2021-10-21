public class Hvitresept extends Resept {
    public Hvitresept(Legemiddel legemiddel, Lege utskrivendeLege, Integer pasientId, Integer reit){
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }
    //Jeg valgte å overloade konstruktøren her, men jeg syns egentlig det også kan være litt risky
    //Jeg tenkte at man også kunne satt reit = 3 i super inni konstruktøren i PResept, men var usikker på tolkningen av oppgaveteksten
    public Hvitresept(Legemiddel legemiddel, Lege utskrivendeLege, Integer pasientId){
        super(legemiddel, utskrivendeLege, pasientId, 3);
    }
    public String farge(){
        return "hvit";
    }
    public Integer prisAaBetale(){
        return this.hentLegemiddel().hentPris();
    }
    @Override
    public String toString(){
        return "\nHvitresept:\nID: " + this.hentId() + "\nPasientID: " + this.hentPasientId()+ "\nReit: " + this.hentReit() + "\nLegemiddel: " + this.hentLegemiddel().hentNavn() + "\nUtskrivende Lege: " + this.hentLege().hentNavn() + "\nType resept: " + this.farge() + "\n";
    }
}