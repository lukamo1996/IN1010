//Del B: Klasser. og Del C: Antall prosessorer og minnekrav
//Klasse: Dataklynge

//Importer nødvendig moduler
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//Vi tar i bruk ArrayList siden vi ikke på forhånd vet hvor mange racks dataklyngen skal ha
public class Dataklynge{
    private ArrayList<Rack> racks = new ArrayList<Rack>();
    private int antallPlasser;
    private int lineCounter = 0;
    
    //Konstruktør. 
    public Dataklynge(String filnavn){
        try {
            final File fil = new File(filnavn);
            final Scanner scanner = new Scanner(fil);

            while(scanner.hasNextLine()){
                if(this.lineCounter == 0){
                    this.antallPlasser = Integer.parseInt(scanner.nextLine());
                }
                else{
                    String[] specs = scanner.nextLine().split(" ");
                    int antallNoder = Integer.parseInt(specs[0]);
                    int minnePlass = Integer.parseInt(specs[1]);
                    int prosessorer = Integer.parseInt(specs[2]);
                    this.addToRackX(antallNoder, new Node(minnePlass, prosessorer));
                }        
                this.lineCounter++;
            }
            scanner.close();
        }
        catch (FileNotFoundException error) {
            System.out.println(error.getMessage());
        }
    }

    //Returnerer antall racks i dataklyngen
    public int antallRack(){
        return this.racks.size();
    }

    //Returnerer antall prosessorer i dataklyngen.
    public int antProsessorer(){
        int antallProsessorer = 0;
        for(Rack rack : this.racks){
            antallProsessorer = antallProsessorer + rack.antProsessorer();
        }
        return antallProsessorer;
    }

    //Returnerer antallet noder med nok minne basert på paakrevdMinne
    public int noderMedNokMinne(int paakrevdMinne){
        int antallNoder = 0;
        for(Rack rack : this.racks){
           antallNoder = antallNoder + rack.noderMedNokMinne(paakrevdMinne);
        }
        return antallNoder;
    }

    //Lag Rack og erstatt posisjon 0 med ønsket Node fordi den er tom. Legg til Rack i this.racks.
    public void lagNyRackOgLeggTil(Node node){
        Rack nyttRack = new Rack(this.antallPlasser);
        nyttRack.noder()[0] = node;
        this.racks.add(nyttRack);
    }

    //Legg til ønsket node x-ganger
    public void addToRackX(int x, Node node){
        for(int i = 0; i < x; i++){
            addToRack(node);
        }
    }

    //Legg til ønsket Node i rack. Hvis rack tom? => lagNyRackOgLeggTil. Ikke tom? => finn null i racksen og legg til.
    public void addToRack(Node node){
        if(this.racks.size() == 0){
            lagNyRackOgLeggTil(node);
        }
        else{
            boolean lagtTil = false;
            for(Rack rack : this.racks){
                lagtTil = rack.addNode(node);
            }
            if(!lagtTil) lagNyRackOgLeggTil(node);
        }
    }
}