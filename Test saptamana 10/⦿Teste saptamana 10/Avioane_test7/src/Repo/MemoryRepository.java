package Repo;


import Domain.FlightInstrument;

import exceptions.DuplicateEntityException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MemoryRepository<T extends FlightInstrument> implements IRepository<T> {
    protected List<T> entities = new ArrayList<>();

    @Override
    public void add(T entity) throws DuplicateEntityException {
        if (entity == null)
            throw new IllegalArgumentException("entity must be not null!");
        entities.add(entity);
    }

    public T findByCode(String code)
    {
        for (T entity : entities)
            if (entity.getCode().equals(code))
                return entity;
        return null;
    }

    @Override
    public Collection<T> getAll() {
        return new ArrayList<T>(entities);
    }


    @Override
    public Iterator<T> iterator() {
        return new ArrayList<T>(entities).iterator();
    }

}