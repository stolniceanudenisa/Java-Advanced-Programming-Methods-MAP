package org.example.repository;


import org.example.domain.FlightInstrument;
import org.example.exceptions.DuplicateEntityException;

import java.util.Collection;

public interface IRepository<T extends FlightInstrument> extends Iterable<T> {

    public void add(T entity) throws DuplicateEntityException;

    public T findByCode(String code);

    public Collection<T> getAll();





//
//    public void remove(Long id);
//
//    public void update(T entity);

//    public Iterable<T> getAll();




}