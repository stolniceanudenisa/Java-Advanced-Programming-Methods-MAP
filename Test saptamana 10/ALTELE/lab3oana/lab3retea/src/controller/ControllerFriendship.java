package controller;

import domain.*;
import domain.validators.*;
import repository.*;

import java.util.*;

public class ControllerFriendship {
    RepoFriendship repo;
    FriendshipValidator friendshipValidator;

    ControllerUser controllerUser;

    public ControllerFriendship(RepoFriendship repo, FriendshipValidator friendshipValidator, ControllerUser controllerUser) {
        this.repo = repo;
        this.friendshipValidator = friendshipValidator;
        this.controllerUser = controllerUser;
    }

    public void add(Friendship f) {
        User u1 = f.getUser1();
        User u2 = f.getUser2();
        if(repo.findOne(f.getId()) != null)
            throw new RuntimeException("Id-ul exista deja!");
        if(controllerUser.getById(u1.getId()) == null || controllerUser.getById(u2.getId()) == null)
            throw new RuntimeException("User 1 sau user 2 nu exista!!");
        if(controllerUser.getById(u1.getId()) == controllerUser.getById(u2.getId()))
            throw new RuntimeException("User 1 sau user 2 nu pot fi la fel!!");

        for(Friendship friendship: repo.getFriendships()){
            if(friendship.getUser1() == f.getUser1() && friendship.getUser2() == f.getUser2()){
                throw new RuntimeException("Exista deja o prietenie intre cei 2 useri!");
            }
            if(friendship.getUser1() == f.getUser2() && friendship.getUser2() == f.getUser1()){
                throw new RuntimeException("Exista deja o prietenie intre cei 2 useri!");
            }
        }
        friendshipValidator.validate(f);
        repo.add(f);
    }

    public void delete(Long l){
        if(repo.findOne(l) == null)
            throw new RuntimeException("Id-ul nu exista!");
        repo.delete(l);
    }

    public List<Friendship> getAll(){
        return repo.getFriendships();
    }
}
