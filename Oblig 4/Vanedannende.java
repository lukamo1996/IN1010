class Vanedannende extends Legemiddel {
  protected Integer styrke;

  public Vanedannende(String navn, int pris, double virkestoff, int styrke) {
    super(navn, pris, virkestoff);
    this.styrke = styrke;
  }

  public Vanedannende(String navn, int pris, double virkestoff) {
    super(navn, pris, virkestoff);
  }

  public int hentVanedannendeStyrke() {
    return styrke;
  }

  public void settStyrke(Integer styrke){
    this.styrke = styrke;
  }

  @Override
  public String toString() {
    return "   Vanedannende legemiddel: \n      Navn: " + navn + "\n      Pris: " + pris + "\n      Virkestoff: " + virkestoff + "\n      Vanedannende styrke: " + styrke + "\n";
  }
}
