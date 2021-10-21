public class Vanlig extends Legemiddel {
    public Vanlig(String navn, Integer pris, Double virkestoff){
        super(navn, pris, virkestoff);
    }
    public Integer prisAaBetale(){
        return this.pris;
    }
    @Override
    public String toString(){
        return "\nLegemiddel \nID: " + this.id + "\nNavn: " + this.navn + "\nPris: " + this.pris + "\nVirkestoff: " + this.virkestoff + "\n";
    }
}
