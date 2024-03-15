package Repository;

import Domain.Entity;


public class MemoryRepo<T extends Entity> extends  RepoAbstract<T> {


    @Override
    public void add(T p) throws RepoException {
        elems.add(p);
    }

    @Override
    public void delete(int ID) throws RepoException {
        T ent = super.getBYid(ID);
        elems.remove(ent);

    }

    @Override
    public void update(T ent) throws RepoException {
        int i = searchBYid(ent.getId());
        elems.set(i, ent);
    }


}
