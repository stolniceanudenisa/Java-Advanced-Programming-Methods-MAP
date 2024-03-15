package org.example.domain;


import org.example.exceptions.DuplicateEntityException;

public interface IEntityFactory<T extends FlightInstrument> {
    public T createEntity(String line) throws DuplicateEntityException;

    String createString(T entity);
}