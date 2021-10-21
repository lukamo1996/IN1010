public class UlovligUtskrift extends Exception {
  UlovligUtskrift(Lege l, Legemiddel lm) {
    super("Legen " + l.hentNavn() + " har ikke lov til og skrive ut " + lm.hentNavn());
  }
}
