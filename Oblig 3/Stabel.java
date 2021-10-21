//Del C: Stabel
public class Stabel<T> extends Lenkeliste<T> {
    //Konstruktør
    public Stabel(){
        super();
    }
    
    //Legg til et element bakerst - LastInFirstOut
    public void leggPaa(T x){
        this.leggTil(this.stoerrelse(), x);
    }

    //Fjern det siste elementet bakerst - LastInFirstOut
    public T taAv(){
        return this.fjern(this.stoerrelse() - 1);
    }
}