abstract class Resept {

  protected static int idCount = 1;
  protected Integer id;
  protected Legemiddel legemiddel;
  protected Lege utskrivendeLege;
  protected Pasient pasient;
  protected int reit;

  // Kostruktor for alle subklasser bortsett fra PResept
  public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
    this.legemiddel = legemiddel;
    this.utskrivendeLege = utskrivendeLege;
    this.pasient = pasient;
    this.reit = reit;
    this.id = idCount++;
  }

  public Integer hentId() {
    return id;
  }

  public Legemiddel hentLegemiddel() {
    return legemiddel;
  }

  public Lege hentLege() {
    return utskrivendeLege;
  }

  public Pasient hentPasient() {
    return pasient;
  }

  public int hentReit() {
    return reit;
  }

  public boolean bruk() {
    if (reit > 0) {
      reit--;
      return true;
    }
    else {
      return false;
    }
  }

  // Dette er abstract metoder som maa defineres i subklassene.
  abstract public String farge();
  abstract public int prisAaBetale();
}
