package Repository;

import Domain.Entity;

import java.util.Collection;

public interface IRepository2<T extends Entity> extends Iterable<T> {

      void add(T entity) throws DuplicateEntityException;

      T findById(int id);

      void remove(int id);

      void update(T entity);

      Collection<T> getAll2();


}