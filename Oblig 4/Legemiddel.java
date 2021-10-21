abstract class Legemiddel {
  /* Oppretter pekere for variabler som er felles
   for alle legemidler. */

  // Denne variabelen holder styr paa hvor mange legemidler som er
  // opprettet. Hver gang et nytt legemiddel opprettes okes denne
  // med 1.
  protected static int idCount = 1;

  protected String navn;
  protected int id;
  protected int pris;
  protected double virkestoff;

  // Konstruktor
  public Legemiddel(String navn, int pris, double virkestoff) {
    this.id = idCount++;
    this.navn = navn;
    this.pris = pris;
    this.virkestoff = virkestoff;
  }
  public int hentId() {
    return id;
  }
  public String hentNavn() {
    return navn;
  }
  public int hentPris() {
    return pris;
  }
  public double hentVirkestoff() {
    return virkestoff;
  }
  public void settNyPris(int nyPris) {
    pris = nyPris;
  }
}
