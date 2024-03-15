package repository;


import Domain.Sensor;
import exceptions.DuplicateEntityException;

import java.util.Collection;

public interface IRepository<T extends Sensor> extends Iterable<T> {

    public void add(T entity) throws DuplicateEntityException;

    public T findByCode(String code);

    public Collection<T> getAll();

    public void remove(String producer);





//
//    public void remove(Long id);
//
//    public void update(T entity);

//    public Iterable<T> getAll();




}