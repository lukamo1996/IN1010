class Narkotisk extends Legemiddel {
  protected Integer styrke;

  public Narkotisk(String navn, int pris, Double virkestoff, int styrke) {
    super(navn, pris, virkestoff);
    this.styrke = styrke;
  }

  public Narkotisk(String navn, int pris, Double virkestoff) {
    super(navn, pris, virkestoff);
  }

  public void settStyrke(Integer styrke){
    this.styrke = styrke;
  }

  public int hentNarkotiskStyrke() {
    return styrke;
  }

  @Override
  public String toString() {
    return "   Narkotisk legemiddel\n      Navn: " + navn + "\n      Pris: " + pris + "\n      Virkestoff: " + virkestoff + "\n      Narkotisk styrke: " + styrke + "\n";
  }

}
