//Del B: Klasser
//Klasse: Node
public class Node{
    private int minne;
    private int prosessorAntall;
    
    public Node(int minne, int prosessorAntall){
        this.minne = minne;
        this.prosessorAntall = prosessorAntall;
    }

    public int minne(){
        return this.minne;
    }

    public int prosessorAntall(){
        return this.prosessorAntall;
    }

    public boolean nokMinne(int paakrevdMinne){
        if (paakrevdMinne <= this.minne) return true;
        else return false;
    }
}