public class Vanedannende extends Legemiddel {
    private Integer styrke;
    
    public Vanedannende(String navn, Integer pris, Double virkestoff, Integer styrke){
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }

    public Integer hentVanedannendeStyrke(){
        return this.styrke;
    }

    @Override
    public String toString(){
        String resultat = "\nVanedannende Legemiddel\nID: " + this.id + "\nNavn: " + this.navn + "\nPris: " + this.pris + "\nVirkestoff: " + this.virkestoff + "\nStyrke: " + this.styrke + "\n";
        return resultat;
    }
}