public class TestLegemiddel{

    //Testing metoder
        //HentId Test
        public static String testLegemiddelID(Legemiddel legemiddel, Integer forventetLegemiddelID){
            Boolean resultat = legemiddel.hentId().equals(forventetLegemiddelID);
            return resultat ? "testLegemiddelID: Forventet resultat --> " + forventetLegemiddelID + " er det samme som " + legemiddel.hentId() : "testLegemiddelID: Feil resultat fordi " + forventetLegemiddelID + " er ikke det samme som " + legemiddel.hentId();
        }
        //HentNavn Test
        public static String testLegemiddelNavn(Legemiddel legemiddel, String forventetNavn){
            Boolean resultat = legemiddel.hentNavn().equals(forventetNavn);
            return resultat ? "testLegemiddelnavn: Forventet resultat --> " + forventetNavn + " er det samme som " + legemiddel.hentNavn() : "testLegemiddelnavn: Feil resultat fordi " + forventetNavn + " er ikke det samme som " + legemiddel.hentNavn();
        }
        //hentPris Test
        public static String testLegemiddelPris(Legemiddel legemiddel, Integer forventetPris){
            Boolean resultat = legemiddel.hentPris().equals(forventetPris);
            return resultat ? "testLegemiddelPris: Forventet resultat --> " + forventetPris + " er det samme som " + legemiddel.hentPris() : "testLegemiddelPris: Feil resultat fordi " + forventetPris + " er ikke det samme som " + legemiddel.hentPris();
        }
        //hentVirkestoff Test
        public static String testLegemiddelVirkestoff(Legemiddel legemiddel, Double forventetVirkestoff){
            Boolean resultat = legemiddel.hentVirkestoff().equals(forventetVirkestoff);
            return resultat ? "testLegemiddelVirkestoff: Forventet resultat --> " + forventetVirkestoff + " er det samme som " + legemiddel.hentVirkestoff() : "testLegemiddelVirkestoff: Feil resultat fordi " + forventetVirkestoff + " er ikke det samme som " + legemiddel.hentVirkestoff();
        }
        //settNyPris Test
        public static String testSettNyPris(Legemiddel legemiddel, Integer nyPris){
            Integer forventetPris = nyPris;
            legemiddel.settNyPris(nyPris);
            Boolean resultat = forventetPris.equals(legemiddel.hentPris());
            return resultat ? "testSettNyPris: Forventet resultat --> " + forventetPris + " er det samme som " + legemiddel.hentPris() : "testSettNyPris: Feil resultat fordi " + forventetPris + " er ikke det samme som " + legemiddel.hentPris();
        }
    
    //Main metode
    public static void main(String args[]){
        //Legemidler
        Vanlig sumifilam = new Vanlig("Sumifilam", 125, 20.0);
        Vanedannende kodein = new Vanedannende("Paralgin Forte", 100, 10.0, 50);
        Narkotisk metamfetamin = new Narkotisk("Desoxyn", 500, 30.0, 25);

        //Start tester
        System.out.println("Testing starter...\n");

        //Vanlig Legemiddel Test
        System.out.println(testLegemiddelID(sumifilam, 1));
        System.out.println(testLegemiddelNavn(sumifilam, "Sumifilam"));
        System.out.println(testLegemiddelPris(sumifilam, 125));
        System.out.println(testLegemiddelVirkestoff(sumifilam, 20.0));
        System.out.println(testSettNyPris(sumifilam, 202));
        System.out.println(sumifilam.toString());

        //Vanedannende Legemiddel Test
        System.out.println(testLegemiddelID(kodein, 2));
        System.out.println(testLegemiddelNavn(kodein, "Paralgin Forte"));
        System.out.println(testLegemiddelPris(kodein, 100));
        System.out.println(testLegemiddelVirkestoff(kodein, 10.0));
        System.out.println(testSettNyPris(kodein, 202));
        System.out.println(kodein.hentVanedannendeStyrke().equals(50) ? "testVanedannendeStyrke: Forventet resultat --> " + kodein.hentVanedannendeStyrke() + " er det samme som " + 50 : "Vanedannende styrke er feil");
        System.out.println(kodein.toString());

        //Narkotisk Legemiddel Test
        System.out.println(testLegemiddelID(metamfetamin, 3));
        System.out.println(testLegemiddelNavn(metamfetamin, "Desoxyn"));
        System.out.println(testLegemiddelPris(metamfetamin, 500));
        System.out.println(testLegemiddelVirkestoff(metamfetamin, 30.0));
        System.out.println(testSettNyPris(metamfetamin, 202));
        System.out.println(metamfetamin.hentNarkotiskStyrke().equals(25) ? "testNarkotiskStyrke: Forventet resultat --> " + metamfetamin.hentNarkotiskStyrke() + " er det samme som " + 25 : "Narkotisk styrke er feil");
        System.out.println(metamfetamin.toString());
    }
}