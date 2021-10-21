public class Stabel<T> extends Lenkeliste<T> {

    public void leggPaa(T x){
        // Legger til noe sist i Lenkelisten (Stabelen)
        leggTil(x);
    }
    public T taAv(){
        // Metode som returner Lenkeliste, med siste element fjernet fra denne
        return fjern(stoerrelse()-1);
    }
}
