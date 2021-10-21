class PResept extends HvitResept{

  public PResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient) {
    // Reit er alltid 3 for PResept, derfor tar jeg ikke det som argument.
    super(legemiddel, utskrivendeLege, pasient, 3);
  }

  @Override
  public int prisAaBetale() {
    int rabattertPris = legemiddel.hentPris() - 108;
    if (rabattertPris < 0) {
      return 0;
    }
    else {
      return rabattertPris;
    }
  }

  @Override
  public String toString() {
    // return "id: " + id + "HvitResept:\n" + legemiddel + "\nPasient id: " + pasient + "\nReit: " + reit + "\nFarge: " + farge() + "\nPris aa betale: " + prisAaBetale() + "\nUtskrivende lege: \n" + utskrivendeLege + "\n";
    return "PResept:\n ID: " + this.id + "\n Reit: " + reit + "\n Pris: " + prisAaBetale() + "\n Farge: " + farge() + "\n" + legemiddel + pasient + "   Utskrivende lege:\n  " + utskrivendeLege + "\n";
  }
}
