package org.example.service;

import org.example.domain.*;
import org.example.repository.*;

import java.util.*;

public class ProduseService {
    ProdusDatabaseRepository repo = ProdusDatabaseRepository.getInstance();


    private static ProduseService instance = null;
    private ProduseService() {
    }
    public static ProduseService getInstance() {
        if(instance == null) {
            instance = new ProduseService();
        }
        return instance;
    }

    public void add(Produs u) {
        System.out.println(u);
        repo.save(u);
    }

    public void delete(Long l) {
        if(repo.getOne(l) == null)
            throw new RuntimeException("Id-ul nu exista!");
        repo.delete(l);
        //for(Friendship friendship: repoFriendship.getAll()){
        //    if(Objects.equals(l, friendship.getUser1()) || Objects.equals(l, friendship.getUser2())){
        //        repoFriendship.delete(friendship.getId());
        //    }
        //}
    }

    public void update(Produs c) {
        if(repo.getOne(c.getId()) == null){
            throw new RuntimeException("Id-ul nu exista!");}
        repo.update(c);
    }

    public Iterable<Produs> getAll() {
        return repo.getAll();
    }

    public ArrayList<Produs> getAll2() {
        return repo.getAll2();
    }

    public Produs getById(long id) {
        return repo.getOne(id);
    }

    public Long lastid(){
        Long id = 0L;
        for(Produs u: repo.getAll()){
            if(u.getId() > id)
                id = u.getId();
        }
        return id + 1;
    }
}
