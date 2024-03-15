package Service;

import Domain.Entity;
import Repository.RepoAbstract;

public abstract class Validator<T extends Entity> {
    public abstract boolean valid_id(RepoAbstract<T> rep, T ent);
}
