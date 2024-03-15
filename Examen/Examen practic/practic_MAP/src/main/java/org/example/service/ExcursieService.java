package org.example.service;

import org.example.domain.Excursie;
import org.example.repository.ExcursieDbRepo;

import java.util.List;

public class ExcursieService {

    ExcursieDbRepo repo = ExcursieDbRepo.getInstance();

    private static ExcursieService instance = null;

    private ExcursieService() {
    }

    public static ExcursieService getInstance() {
        if(instance == null) {
            instance = new ExcursieService();
        }
        return instance;
    }

    public void add(Excursie e) {
        System.out.println(e);
        repo.save(e);
    }

    public void delete(Long l) {
        if(repo.getOne(l) == null)
            throw new RuntimeException("Id-ul nu exista!");
        repo.delete(l);
    }


    public List<Excursie> getAll() {
        return repo.getAll();
    }


    public Excursie getById(long id) {
        return repo.getOne(id);
    }


}
