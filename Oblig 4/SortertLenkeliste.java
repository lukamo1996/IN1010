public class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T>{
    //Konstruktoer
    public SortertLenkeliste(){
        super();
    }

    //Sortert innleggelse. Hvis tom liste => legg til fremst. Hvis ikke loop til du finner et stoerre element og legg til. Hvis ikke => legg til paa slutten.
    @Override
    public void leggTil(T x) {
        if(this.stoerrelse == 0) super.leggTil(x);
        else{
            for(Integer i = 0; i < this.stoerrelse(); i++){
                if(this.hent(i).compareTo(x) > 0){
                    super.leggTil(i, x);
                    return;
                }
            }
            super.leggTil(x);
        }
    }

    //Fjerner et element fra sortertListe fra bakerst (aka. stoerste element)
    @Override
    public T fjern(){
        return super.fjern(this.stoerrelse() - 1);
    }

    //Overrida metode
    @Override
    public void sett(int pos, T x){
        throw new UnsupportedOperationException();
    }

    //Overrida metode
    @Override
    public void leggTil(int pos, T x) {
        throw new UnsupportedOperationException();
    }
}
