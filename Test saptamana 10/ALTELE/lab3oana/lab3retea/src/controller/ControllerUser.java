package controller;

import domain.*;
import domain.validators.*;
import repository.*;

import java.util.*;

public class ControllerUser {
    RepoUser repo;
    UserValidator userValidator;
    RepoFriendship repoFriendship;

    public ControllerUser(RepoUser repo, UserValidator userValidator, RepoFriendship repoFriendship) {
        this.repo = repo;
        this.userValidator = userValidator;
        this.repoFriendship = repoFriendship;
    }

    public void add(User u) {
        if(repo.findOne(u.getId()) != null)
            throw new RuntimeException("Id-ul exista deja!");
        userValidator.validate(u);
        repo.add(u);
    }

    public void delete(Long l) {
        if(repo.findOne(l) == null)
            throw new RuntimeException("Id-ul nu exista!");
        repo.delete(l);
        for(Friendship friendship: repoFriendship.getFriendships()){
            if(l == friendship.getUser1().getId() || l == friendship.getUser2().getId()){
                repoFriendship.delete(friendship.getId());
            }
        }
    }

    public List<User> getAll() {
        return repo.getUsers();
    }

    public User getById(long id) {
        return repo.findOne(id);
    }

}
