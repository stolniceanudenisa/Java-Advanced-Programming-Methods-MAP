package org.example.service;

import org.example.domain.*;
import org.example.repository.*;

import java.util.*;

public class TablouService {
    TablouDatabaseRepository repo = TablouDatabaseRepository.getInstance();


    private static TablouService instance = null;
    private TablouService() {
    }
    public static TablouService getInstance() {
        if(instance == null) {
            instance = new TablouService();
        }
        return instance;
    }

    public void add(Tablou u) {
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

    public void update(Tablou c) {
        if(repo.getOne(c.getId()) == null){
            throw new RuntimeException("Id-ul nu exista!");}
        repo.update(c);
    }

    public Iterable<Tablou> getAll() {
        return repo.getAll();
    }

    public ArrayList<Tablou> getAll2() {
        return repo.getAll2();
    }

    public Tablou getById(long id) {
        return repo.getOne(id);
    }

    public Long lastid(){
        Long id = 0L;
        for(Tablou u: repo.getAll()){
            if(u.getId() > id)
                id = u.getId();
        }
        return id + 1;
    }
}
