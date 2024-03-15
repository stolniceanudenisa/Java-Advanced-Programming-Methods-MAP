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
        for (T existingEntity : entities) {
            if (existingEntity.getCode().equals(entity.getCode())) {
                throw new DuplicateEntityException("Entity with the same CODE already exists!");
            }
        }
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
    public void remove(String code) {
        T entityToRemove = null;

        for (T entity : entities) {
            if (entity.getCode() .equals(code)) {
                entityToRemove = entity;
                break;
            }
        }
        if (entityToRemove != null) {
            entities.remove(entityToRemove);
            System.out.println("Instrumentul cu codul " + code + " a fost sters.");

        } else {
            System.out.println("Instrumentul cu codul " + code + " nu exista.");
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayList<T>(entities).iterator();
    }

}