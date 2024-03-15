package Repo;


import Domain.Document;

import exceptions.DuplicateEntityException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MemoryRepository<T extends Document> implements IRepository<T> {
    protected List<T> entities = new ArrayList<>();

    @Override
    public void add(T entity) throws DuplicateEntityException {
        if (entity == null)
            throw new IllegalArgumentException("entity must be not null!");

        entities.add(entity);
    }

    public T findByAuthor(String author)
    {
        for (T entity : entities)
            if (entity.getAuthor().equals(author))
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