package Repo;

import Domain.User;

import java.util.ArrayList;
import java.util.List;

public class RepoUser implements IRepo<User> {
    List<User> users;

    public RepoUser() {
        this.users = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    @Override
    public User find_one(int id_to_find) {
        for(User user : users) {
            if(user.getId_entity() ==  id_to_find)
                return user;
        }
        return null;
    }

    @Override
    public void add(User entity) {
        this.users.add(entity);
    }

    @Override
    public void delete(int id_to_delete) {
        if(find_one(id_to_delete) != null) {
            users.remove(find_one(id_to_delete));
        }
    }
}
