public class Lege implements Comparable<Lege>{

  // Instansvariabler
  protected String navn;
  protected Lenkeliste<Resept> utskrevedeResepter = new Lenkeliste<>();

  // Konstruktør
  public Lege(String navn) {
    this.navn = navn;
  }

  public String hentNavn() {
    return navn;
  }

  @Override
  public String toString() {
    return "   Lege\n         Navn: " + navn;
  }

  // Comparable interface implemented
  public int compareTo(Lege other) {
    return navn.compareTo(other.hentNavn());
  }

  // Hent ut utskrevedeResepter
  public Lenkeliste<Resept> hentUtskrevedeResepter () {
    return utskrevedeResepter;
  }

  // (D3) Metoder for aa opprette instanser av de fire reseptklassene.
  public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
    // Tester for ulovlig utskrift
    testForNarkotiskLegemiddel(legemiddel);
    // Oppretter ny hvit resept
    HvitResept nyResept = new HvitResept(legemiddel, this, pasient, reit);
    // Legger resepten til i legens liste over utskrevede resepter
    utskrevedeResepter.leggTil(nyResept);
    return nyResept;
  }
  public MillitaerResept skrivMillitaerResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
    // Tester for ulovlig utskrift
    testForNarkotiskLegemiddel(legemiddel);
    // Oppretter ny millitaer resept
    MillitaerResept nyResept = new MillitaerResept(legemiddel, this, pasient, reit);
    // Legger resepten til i legens liste over utskrevede resepter
    utskrevedeResepter.leggTil(nyResept);
    return nyResept;
  }
  public PResept skrivPResept (Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
    // Tester for ulovlig utskrift
    testForNarkotiskLegemiddel(legemiddel);
    // Oppretter ny presept
    PResept nyResept = new PResept(legemiddel, this, pasient);
    // Legger resepten til i legens liste over utskrevede resepter
    utskrevedeResepter.leggTil(nyResept);
    return nyResept;
  }
  public BlaaResept skrivBlaaResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
    // Tester for ulovlig utskrift
      testForNarkotiskLegemiddel(legemiddel);
      // Oppretter ny presept
      BlaaResept nyResept = new BlaaResept(legemiddel, this, pasient, reit);
      // Legger resepten til i legens liste over utskrevede resepter
      utskrevedeResepter.leggTil(nyResept);
      return nyResept;
  }

  // Sjekk for om Lege skriver ut noe han/hun ikke har lov til.
  private void testForNarkotiskLegemiddel(Legemiddel legemiddel) throws UlovligUtskrift {
    // ?????Litt usikker her.... må sjekke om legen er en vanlig lege eller om han er spesialist...
      if (legemiddel instanceof Narkotisk && this instanceof Spesialist == false) {
        throw new UlovligUtskrift(this, legemiddel);
        // System.out.println(this.hentNavn() + " prøver å skrive ut noe ulovlig, ring politiet. ");
      }
  }
}
