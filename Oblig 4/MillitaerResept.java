class MillitaerResept extends HvitResept{

  public MillitaerResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
    super(legemiddel, utskrivendeLege, pasient, reit);
  }

  // Militaer respter faar 100% rabatt, altsaa betaler de ingenting.
  @Override
  public int prisAaBetale() {
    return 0;
  }

  @Override
  public String toString() {
    // return "id: " + id + "\nLegemiddel (paafolgende linjer):\n" + legemiddel + "\nPasient id: " + pasient + "\nReit: " + reit + "\nFarge: " + farge() + "\nPris aa betale: " + prisAaBetale() + "\nUtskrivende lege: \n" + utskrivendeLege + "\n";
    return "MilitaerResept:\n ID: " + this.id + "\n Reit: " + reit + "\n Pris: " + prisAaBetale() + "\n Farge: " + farge() + "\n" + legemiddel + pasient + "   Utskrivende lege:\n" + utskrivendeLege + "\n";
  }
}
