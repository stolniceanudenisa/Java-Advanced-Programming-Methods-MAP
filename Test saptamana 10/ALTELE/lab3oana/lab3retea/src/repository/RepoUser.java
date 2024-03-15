package repository;

import domain.*;

import java.util.*;

public class RepoUser implements IRepository<User>{

    List<User> users;

    public List<User> getUsers(){
        return users;
    }
    @Override
    public User findOne(long aLong) {
        for (User u : users) {
            long index = (long) u.getId();
            if (index == aLong)
                return u;
        }
        return null;
    }

    public RepoUser() {
        this.users = new ArrayList<>(); //users
    }

    @Override
    public void add(User entity) {
        this.users.add(entity);
    }

    @Override
    public void delete(long aLong) {
        User u = findOne(aLong);
        if(users.contains(u)){
            users.remove(u);
        }
    }
}
