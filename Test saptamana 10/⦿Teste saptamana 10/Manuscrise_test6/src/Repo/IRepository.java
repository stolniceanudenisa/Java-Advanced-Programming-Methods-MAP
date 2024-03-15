package Repo;


import Domain.Document;

import exceptions.DuplicateEntityException;

import javax.print.Doc;
import java.util.Collection;

public interface IRepository<T extends Document> extends Iterable<T> {

    public void add(T entity) throws DuplicateEntityException;

    public T findByAuthor(String author);

    public Collection<T> getAll();


}