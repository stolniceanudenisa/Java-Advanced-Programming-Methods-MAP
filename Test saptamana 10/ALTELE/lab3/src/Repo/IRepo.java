package Repo;

import Domain.Entity;

public interface IRepo <E extends Entity> {
    E find_one(int id);
    public void add(E entity);
    public void delete(int id);
}
