abstract class Resept {
    private static Integer UID_TELLER = 1;

    protected Integer id;
    protected Integer pasientId;
    protected Integer reit;
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Integer pasientId, Integer reit){
        this.id = UID_TELLER++;
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasientId = pasientId;
        this.reit = reit;
    }

    public Integer hentId(){
        return this.id;
    }

    public Legemiddel hentLegemiddel(){
        return this.legemiddel;
    }

    public Lege hentLege(){
        return this.utskrivendeLege;
    }

    public Integer hentPasientId(){
        return this.pasientId;
    }

    public Integer hentReit(){
        return this.reit;
    }

    public Boolean bruk(){
        if(this.reit > 0){
            reit--;
            return true;
        }
        return false;
    }
    
    abstract public String farge();
    abstract public Integer prisAaBetale();
    
}