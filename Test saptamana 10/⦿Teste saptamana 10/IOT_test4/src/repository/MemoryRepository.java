package repository;


import Domain.Sensor;
import exceptions.DuplicateEntityException;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MemoryRepository<T extends Sensor> implements IRepository<T> {
    protected List<T> entities = new ArrayList<>();

    @Override
    public void add(T entity) throws DuplicateEntityException {
        if (entity == null)
            throw new IllegalArgumentException("entity must be not null!");
        for (T existingEntity : entities) {
            if (existingEntity.getProducer().equals(entity.getProducer())) {
                throw new DuplicateEntityException("Entity with the same PRODUCER already exists!");
            }
        }
        entities.add(entity);
    }

    public T findByCode(String producer)
    {
        for (T entity : entities)
            if (entity.getProducer().equals(producer))
                return entity;
        return null;
    }

    @Override
    public Collection<T> getAll() {
        return new ArrayList<T>(entities);
    }

    @Override
    public void remove(String producer) {
        entities.removeIf(entity -> entity.getProducer().equals(producer));
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayList<T>(entities).iterator();
    }


//    @Override
//    public T findById(Long id) {
//        for (T entity : entities)
//            if (entity.getId().equals(id))
//                return entity;
//        return null;
//    }
//
//    public T findById2(int id) {
//        for (T entity : entities)
//            if (entity.getId().equals(id))
//                return entity;
//        return null;
//    }
//
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