package Repo;


import Domain.FlightInstrument;

import exceptions.DuplicateEntityException;

import java.util.Collection;

public interface IRepository<T extends FlightInstrument> extends Iterable<T> {

    public void add(T entity) throws DuplicateEntityException;

    public T findByCode(String code);

    public Collection<T> getAll();

}