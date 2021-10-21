class HvitResept extends Resept{
  public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
    super(legemiddel, utskrivendeLege, pasient, reit);
  }

  @Override
  public String farge() {
    return "hvit";
  }

  @Override
  public int prisAaBetale() {
    return legemiddel.hentPris();
  }

  @Override
  public String toString() {
    return "HvitResept:\n ID: " + this.id + "\n Reit: " + reit + "\n Pris: " + prisAaBetale() + "\n Farge: " + farge() + "\n" + legemiddel + pasient + "   Utskrivende lege:\n" + utskrivendeLege + "\n";
  }

}
