public class Narkotisk extends Legemiddel {
    private Integer styrke;
    
    public Narkotisk(String navn, Integer pris, Double virkestoff, Integer styrke){
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }

    public Integer hentNarkotiskStyrke(){
        return this.styrke;
    }

    @Override
    public String toString(){
        return "\nNarkotisk Legemiddel: \nID: " + this.id + "\nNavn: " + this.navn + "\nPris: " + this.pris + "\nVirkestoff: " + this.virkestoff + "\nStyrke: " + this.styrke + "\n";
    }
}
