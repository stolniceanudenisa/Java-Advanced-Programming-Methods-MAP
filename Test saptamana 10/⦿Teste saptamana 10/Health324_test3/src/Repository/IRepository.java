package Repository;


import Domain.HealthData;

import exceptions.DuplicateEntityException;

import java.util.Collection;

public interface IRepository<T extends HealthData> extends Iterable<T> {

    public void add(T entity) throws DuplicateEntityException;

    public T findByDate(String date);

    public Collection<T> getAll();

}