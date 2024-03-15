package Service;

import Domain.Entity;
import Repository.RepoAbstract;
import Repository.RepoException;


public class Service<T extends Entity> {

    private final RepoAbstract<T> rep;
    private final Validator<T> valid;

    public Service(RepoAbstract<T> rep, Validator<T> valid) {
        this.rep = rep;
        this.valid = valid;
    }

    public void add(T ent) throws RepoException {
        if (valid.valid_id(rep,ent))rep.add(ent);
        else throw new RepoException("*** exista deja un/o " + ent.getClass().getSimpleName() + " cu acele caracterisitce unice ***\n");
    }

    public void update(T ent) throws RepoException {
        if(rep.searchid(ent.getId())) rep.update(ent);
        else throw new RepoException("*** nu exista niciun/nici o " + ent.getClass().getSimpleName() + " cu acel ID! ***\n");
    }

    public void delete(int ID) throws RepoException {
        if(rep.searchid(ID)) rep.delete(ID);
        else throw new RepoException("*** nu exista ce doriti sa stergeti ***\n");
    }
    public RepoAbstract<T> getRep() {
        return rep;
    }
}
