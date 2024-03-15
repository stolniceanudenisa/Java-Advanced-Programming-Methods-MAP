package repository;

import domain.*;

public interface IRepository<E extends Entity> {

    E findOne(long id);

    public void add(E entity);

    public void delete(long id);
}
