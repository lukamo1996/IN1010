abstract class Legemiddel{
    private static Integer UID_TELLER = 1;

    protected Integer id;
    protected String navn;
    protected Integer pris;
    protected Double virkestoff;

    public Legemiddel(String navn, Integer pris, Double virkestoff){
        this.id = UID_TELLER++;
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
    }

    public Integer hentId(){
        return this.id;
    }

    public String hentNavn(){
        return this.navn;
    }

    public Integer hentPris(){
        return this.pris;
    }

    public Double hentVirkestoff(){
        return this.virkestoff;
    }

    public void settNyPris(Integer pris){
        this.pris = pris;
    }
}