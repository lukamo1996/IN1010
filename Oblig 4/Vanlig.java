class Vanlig extends Legemiddel {
  // Denne klassen har ingen spesielle variabler/egenskaper

  // Konstruktor
  public Vanlig(String navn, int pris, double virkestoff) {
    super(navn, pris, virkestoff);
  }

  @Override
  public String toString() {
    return "   Vanlig legemiddel\n      Navn: " + navn + "\n      Pris: " + pris + "\n      Virkestoff: " + virkestoff + "\n";
  }
}
