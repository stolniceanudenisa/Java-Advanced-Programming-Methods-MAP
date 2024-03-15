package org.example.service;

import org.example.domain.Tablou;
import org.example.repository.TablouDatabaseRepository;

import java.util.ArrayList;
import java.util.List;

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
    }


    public List<Tablou> getAll() {
        return repo.getAll();
    }


    public Tablou getById(long id) {
        return repo.getOne(id);
    }

    /*
    public Long lastid(){
        Long id = 0L;
        for(Tablou u: repo.getAll()){
            if(u.getId() > id)
                id = u.getId();
        }
        return id + 1;
    }
    */
}
