package org.example.repository;

import org.example.domain.Student;

import java.util.List;

public interface Repository< E extends Student> {

    /**
     * @param id -the id of the entity to be returned
     *           id must not be null
     * @return the entity with the specified id
     * or null - if there is no entity with the given id
     * @throws IllegalArgumentException if id is null.
     */
    E getOne(Long id);

    /**
     * @return all entities
     */
    List<E> getAll();

    /**
     * @param entity - the entity that mus be validated
     * @throws IllegalArgumentException and ValidationException if the entity isn't a good one
     */
    void verifyEntity(E entity);

    /**
     * @param entity entity must be not null
     * @return null- if the given entity is saved
     * otherwise returns the entity (id already exists)
     * @throws IllegalArgumentException if the given entity is null.     *
     */
    void save(E entity);

    /**
     * @return the number of existing entities in this repo
     */
    int size();

    /**
     * removes the entity with the specified id
     *
     * @param id id must be not null
     * @return the removed entity or null if there is no entity with the given id
     * @throws IllegalArgumentException if the given id is null.
     */
    E delete(Long id);

    /**
     * @param entity entity must not be null
     * @return null - if the entity is updated,
     * otherwise  returns the entity  - (e.g id does not exist).
     * @throws IllegalArgumentException if the given entity is null.
     */
    E update(E oldEntity, E newEntity);


}
