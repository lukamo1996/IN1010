//Del B: Klasser
//Klasse: Rack
public class Rack{
    private Node[] noder;
    private int antallPlasser;

    public Rack(int antallPlasser){
        this.antallPlasser = antallPlasser;
        this.noder = new Node[this.antallPlasser];
    }

    public Node[] noder(){
        return this.noder;
    }

    public int antallPlasser(){
        return this.antallPlasser;
    }

    public int antProsessorer(){
        int antallProsessorer = 0;
        for(Node node : this.noder){
            if(node != null){
                antallProsessorer = antallProsessorer + node.prosessorAntall();
            }
        }
        return antallProsessorer;
    }

    public int noderMedNokMinne(int paakrevdMinne){
        int antallNoder = 0;
        for(Node node : this.noder){
            if(node != null && node.nokMinne(paakrevdMinne)){
                antallNoder = antallNoder + 1;
            }
        }
        return antallNoder;
    }
    
    public boolean addNode(Node node){
        for(int i = 0; i < this.noder.length; i++){
            if(this.noder[i] == null){
                this.noder[i] = node;
                return true;
            }
        }
        return false;
    }
}