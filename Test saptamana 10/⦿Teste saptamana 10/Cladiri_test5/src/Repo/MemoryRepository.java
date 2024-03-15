package Repo;


import Domain.Building;
import exceptions.DuplicateEntityException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MemoryRepository<T extends Building > implements IRepository<T> {
    protected List<T> entities = new ArrayList<>();

    @Override
    public void add(T entity) throws DuplicateEntityException {
        if (entity == null)
            throw new IllegalArgumentException("entity must be not null!");
        entities.add(entity);
    }

    public T findByConstructionYear(Integer constructionYear)
    {
        for (T entity : entities)
            if (entity.getConstructionYear().equals(constructionYear))
                return entity;
        return null;
    }

    @Override
    public Collection<T> getAll() {
        return new ArrayList<T>(entities);
    }

    @Override
    public void remove(Integer id) {
        T entityToRemove = null;

        for (T entity : entities) {
            if (entity.getId() == id) {
                entityToRemove = entity;
                break;
            }
        }
        if (entityToRemove != null) {
            entities.remove(entityToRemove);
            System.out.println("Building with ID " + id + " removed successfully.");
        } else {
            System.out.println("Building with ID " + id + " not found.");
        }
    }



    @Override
    public Iterator<T> iterator() {
        return new ArrayList<T>(entities).iterator();
    }


//    @Override
//    public void remove(Long id) {
////        for (T entity : entities)
////            if (entity.getId().equals(id))
////                entities.remove(entity);
//        entities.removeIf(entity -> entity.getId().equals(id));
//    }


//    @Override
//    public void update(T entity) {
//        if (entity == null)
//            throw new IllegalArgumentException("entity must not be null!");
//
//        for (int i = 0; i < entities.size(); i++) {
//            T currentEntity = entities.get(i);
//            if (currentEntity.getId().equals(entity.getId())) {
//                entities.set(i, entity);
//                return;
//            }
//        }
//        throw new RuntimeException("Pacientul cu ID: " + entity.getId() + " nu a fost gasit!");
//    }
//
//    @Override
//    public Iterable<T> getAll1() {
//        return null;
//    }
//
//    @Override
//    public Collection<T> getAll2() {
//        return new ArrayList<T>(entities);
//    }
//
//
//    @Override
//    public Iterator<T> iterator() {
//        return new ArrayList<T>(entities).iterator();
//    }

}