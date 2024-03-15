package Repo;


import Domain.Building;

import exceptions.DuplicateEntityException;

import java.util.Collection;

public interface IRepository<T extends Building> extends Iterable<T> {

    public void add(T entity) throws DuplicateEntityException;

    public T findByConstructionYear(Integer constructionYear);

    public Collection<T> getAll();


    public void remove(Integer id);
}