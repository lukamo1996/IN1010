public class TestResepter {
    //Testing metoder
    //Farge Test
    public static String testFarge(Resept resept, String forventetFarge){
        if(forventetFarge.equals(resept.farge())){
            return "FargeTest: Forventet resultat --> " + forventetFarge + " er det samme som " + resept.farge();
        }
        else{
            return "FargeTest: Den fargen var ikke forventet fordi " + resept.farge() + " er ikke det samme som " + forventetFarge;
        }
    }
    //PrisÅbetale Test
    public static String testPrisAaBetale(Resept resept, Integer forventetPris){
        if(forventetPris.equals(resept.prisAaBetale())){
            return "PrisAaBetaleTest: Forventet resultat --> " + forventetPris + " er det samme som " + resept.prisAaBetale();
        }
        else{
            return "PrisAaBetaleTest: Den prisen var ikke som forventet fordi " + forventetPris + " er ikke det samme som " + resept.prisAaBetale();
        }
    }
    public static void main(String[] args){
        //Legemidler opprettes
        Vanlig sumifilam = new Vanlig("Sumifilam", 100, 20.0);
        Vanedannende kodein = new Vanedannende("Kodein", 100, 20.0, 10);
        Narkotisk metamfetamin = new Narkotisk("Metamfetamin", 100, 20.0, 20);

        //Starter testing
        System.out.println("Testing starter...\n");

        //Blaaresept Testing
        Blaaresept blaaresept = new Blaaresept(sumifilam, new Lege("Luka"), 1, 2);
        System.out.println(testFarge(blaaresept, "blaa"));
        System.out.println(testPrisAaBetale(blaaresept, 25));
        System.out.println(blaaresept.toString());
        System.out.println();

        //P-Resept Testing - Hvit resept
        PResept presept = new PResept(kodein, new Lege("Luka"), 1);
        System.out.println(testFarge(presept, "hvit"));
        System.out.println(testPrisAaBetale(presept, 0));
        System.out.println(presept.toString());
        System.out.println();

        //P-Resept Testing - Hvit resept
        MResept mresept = new MResept(metamfetamin, new Lege("Luka"), 1, 1);
        System.out.println(testFarge(mresept, "hvit"));
        System.out.println(testPrisAaBetale(mresept, 0));
        System.out.println(mresept.toString());
        System.out.println();

    }
}