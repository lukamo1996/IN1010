class BlaaResept extends Resept {

  public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
    super(legemiddel, utskrivendeLege, pasient, reit);
  }

  public String farge() {
    return "blaa";
  }

  public int prisAaBetale() {
    Integer orginalPris = legemiddel.hentPris();
    return orginalPris / 4; // Siden pris er en int vil dette rundes av til nearmeste hele krone.
  }

  @Override
  public String toString() {
    // return "BlaaResept:\n" + "   ID: " + id + "\n" + legemiddel + pasient + "      Reit: " + reit + "\n      Farge: " + farge() + "\n      Pris aa betale: " + prisAaBetale() + "\n      Utskrivende lege: \n" + utskrivendeLege + "\n";
    return "BlaaResept:\n ID: " + this.id + "\n Reit: " + reit + "\n Pris: " + prisAaBetale() + "\n Farge: " + farge() + "\n" + legemiddel + pasient + "   Utskrivende lege:\n" + utskrivendeLege + "\n";

  }
}
