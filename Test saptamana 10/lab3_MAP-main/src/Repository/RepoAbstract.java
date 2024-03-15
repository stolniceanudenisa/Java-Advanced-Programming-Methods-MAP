package Repository;

import Domain.Entity;

import java.util.ArrayList;

public abstract class RepoAbstract<T extends Entity>{

    public final ArrayList<T> elems;

    public RepoAbstract(){
        this.elems = new ArrayList<T>();
    }

    public ArrayList<T> getAll(){
        return elems;
    }

    public T get(int i) throws RepoException {
        if(i<elems.size() && i >-1) return elems.get(i);
        throw new RepoException("index out of range ");
    }

    public  T getBYid(int id) throws RepoException {
        for(T ent : elems) if (ent.getId() == id) return ent;
        throw new RepoException("*** Nu exista un/o " + elems.get(0).getClass().getSimpleName() + " cu acel id ***");
    }

    public  Boolean searchid(int id){
        for(T ent : elems){
            if(ent.getId() == id) return true;
        }
        return false;
    }

    public int searchBYid(int id) throws RepoException {
        for(int i =0; i<elems.size(); i++){
            if(elems.get(i).getId() == id) return i;
        }
        throw new RepoException("*** Nu exista un/o " + elems.get(0).getClass().getSimpleName() + " cu acel id ***" );
    }

    public  int getSize() {
        return elems.size();
    }

    public abstract void add(T ent) throws RepoException;

    public abstract void delete(int id) throws RepoException;

    public abstract void update(T ent) throws RepoException;

}
