//Del B: Lenkeliste
public class Lenkeliste<T> implements Liste<T> {
    //Lenkeliste variabler
    protected Integer stoerrelse = 0;
    protected Node head;

    //Konstruktør
    public Lenkeliste(){
        super();
    }

    //Node privat klasse som kun kan lages her inne
    private class Node {
        private T data;
        private Node next;
    
        private Node(T data){
            this.data = data;
        }
        @Override
        public String toString(){
            return this.data.toString();
        }
    }

    //Iterator privat klasse
    public class LenkelisteIterator implements Iterator<T>{
        private Node current = this.head;

        @Override
        public boolean hasNext() {
            if(this.current != null && this.current.next != null){
                return true;
            }
            else if(this.current != null && this.current.next == null){
                return false;
            }
            else if(this.current == null){
                return false;
            }
        }
    
        @Override
        public T next() {
            this.current = this.current.next;
        }
    
        @Override
        public void remove() {
            //Frivillig.
        }
    }

    //Lenkeliste iterator konstruktør
    public Iterator iterator(){
        return this.LenkelisteIterator();
    }

    //Legger til en ny-node bakerst i listen vha finnSisteGyldigeNode-metode
    @Override
    public void leggTil(T x){
        if(this.head == null) this.head = new Node(x);
        else finnSisteGyldigeNode().next = new Node(x);
        this.inkrement();
    }

    //Fjern det første(fremste/headen) elementet i listen
    @Override
    public T fjern() {
        Node tempNode = this.head;
        if(this.head == null) throw new UgyldigListeIndeks(-1);
        else if(this.head.next == null){
            this.head = null;
            this.dekrement();
            return tempNode.data;
        }
        else{
            this.head = this.head.next;
            this.dekrement();
            return tempNode.data;
        }
    }

    //Finner siste Node som er gyldig
    public Node finnSisteGyldigeNode(){
        Node current = this.head;
        while(current.next != null) current = current.next;
        return current;
    }

    //Øk størrelsen med 1
    public void inkrement(){
        this.stoerrelse++;
    }

    //Reduser størrelsen med 1
    public void dekrement(){
        if(this.stoerrelse > 0) this.stoerrelse--;
    }

    //Finn størrelse på den lenkede listen
    @Override
    public int stoerrelse(){
        return this.stoerrelse;
    }

    //Legger til en ny-node på angitt posisjon
    @Override
    public void leggTil(int pos, T x){
        //Variabler
        Node nyNode = new Node(x);
        Node current = this.head;
        Node forrige = null;

        //Hvis posisjonen ikke finnes i listen
        if(pos < 0 || pos > this.stoerrelse()) throw new UgyldigListeIndeks(-1);

        //Vi søker etter ønsket posisjon og legger den til
        for(Integer i = 0; i <= pos; i++){
            if(i == pos){
                //Hvis det er noe før current
                if(forrige != null){
                    forrige.next = nyNode;
                    nyNode.next = current;
                    this.inkrement();
                    break;
                }
                else{
                    //Hvis det ikke er noe før current
                    this.head = nyNode;
                    nyNode.next = current;
                    this.inkrement();
                    break;
                }
            }
            forrige = current;
            current = current.next;
        }
    }

    //Erstatter et element på ønsket posisjon
    @Override
    public void sett(int pos, T x) {
        //Variabler
        Node nyNode = new Node(x);
        Node current = this.head;
        Node forrige = null;

        //Hvis posisjonen ikke finnes i listen
        if(pos < 0 || pos >= this.stoerrelse()) throw new UgyldigListeIndeks(-1);

        //Vi søker etter ønsket posisjon og legger den til
        for(Integer i = 0; i <= pos; i++){
            if(i == pos){
                if(this.stoerrelse() == 1) this.head = nyNode;
                else if(this.stoerrelse() > 1){
                    if(forrige != null) forrige.next = nyNode;
                    else head = nyNode;
                    nyNode.next = current.next == null ? null : current.next;
                    break;
                }
            }
            forrige = current;
            current = current.next;
        }
    }

    //Henter et element i listen uten å endre den
    @Override
    public T hent(int pos) {
        //Vi setter current til å være head først
        Node current = this.head;

        //Hvis posisjon større enn liste eller mindre enn null eller listen er tom så throw
        if(pos < 0 || pos >= this.stoerrelse()) throw new UgyldigListeIndeks(-1);

        //Vi søker etter noden med ønsket indeks
        for(Integer i = 0; i <= pos; i++){
            if(i == pos) return current.data;
            current = current.next;
        }
        return null;
    }

    //Fjerning av element på ønsket posisjon
    @Override
    public T fjern(int pos){
        //Variabler
        Node current = this.head;
        Node forrige = null;

        //Hvis posisjonen ikke finnes i listen
        if(pos < 0 || pos >= this.stoerrelse()) throw new UgyldigListeIndeks(-1);

        //Vi søker etter elementet å fjerne
        for(Integer i = 0; i <= pos; i++){
            if(i == pos){
                this.dekrement();
                if(forrige != null){
                    forrige.next = current.next;
                    return current.data;
                }
                else{
                    this.head = current.next;
                    return current.data;
                }
            }
            forrige = current;
            current = current.next;
        }
        return null;
    }

    //Skriver ut innholdet i listen i dette formatet: Node-klassen ---> Klassenavnet inni data ---> Innholdet i data
    @Override
    public String toString(){
        String results = "";
        Node current = this.head;
        while(current != null){
            results = results + "\n" + current.getClass() + " ---> " + current.data.getClass() + " ---> " + current;
            current = current.next;
        }
        return results;
    }
}