package Repository;

import Domain.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MemoryRepository<T extends Entity> implements IRepository2<T>{
    protected List<T> entities = new ArrayList<>();

    @Override
    public void add(T entity) throws DuplicateEntityException {
        if (entity == null)
            throw new IllegalArgumentException("Entity can't be null");
        if (findById(entity.getId()) != null)
            throw new DuplicateEntityException("Entity with the same id already exists!");
        entities.add(entity);
    }

    @Override
    public void remove(int id) {
        for (T entity : entities)
        {
            if (entity.getId() == id)
            {
                entities.remove(entity);
                return;
            }
        }
        //entities.removeIf(entity -> entity.getId().equals(id));
    }

    @Override
    public T findById(int id) {
        for (T entity : entities)
        {
            if (entity.getId() == id)
            {
                return entity;
            }
        }
        return null;
    }


    @Override
    public void update(T entity) {
        if (entity == null)
            throw new IllegalArgumentException("entity must not be null!");

        for (int i = 0; i < entities.size(); i++) {
            T currentEntity = entities.get(i);
            if (currentEntity.getId() == (entity.getId())) {
                entities.set(i, entity);
                return;
            }
        }
        throw new RuntimeException("Pacientul cu ID: " + entity.getId() + " nu a fost gasit!");
    }

    @Override
    public Collection<T> getAll2() {
        return new ArrayList<T>(entities);
    }

    @Override
    public Iterator<T> iterator() {
        return  new ArrayList<T>(entities).iterator();
    }
}