package com.example.examen.service;

import com.example.examen.domain.Tablou;
import com.example.examen.repository.RepositoryDB;
import javafx.scene.control.Tab;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Service {
    List<Tablou> entities;
    private static Service instance = null;
    private Service() {
        entities = RepositoryDB.getInstance().read();
    }

    public static Service getInstance() {
        if(instance == null) {
            instance = new Service();
        }
        return instance;
    }

    public Long idGenerator() {
        Long maxim = 0L;
        if(RepositoryDB.getInstance().read().size() == 0) {
            return 1L;
        }
        else {
            for(Tablou e : entities) {
                if(e.getId() > maxim) {
                    maxim = e.getId();
                }
            }
        }
        return maxim + 1;
    }

    public void create(Tablou entity) {
        RepositoryDB.getInstance().create(entity);
    }

    public List<Tablou> read() {
        return RepositoryDB.getInstance().read();
    }

    public List<Tablou> searchByTitlu(String text) {
        List<Tablou> newEntities = entities
                .stream()
                .filter(t -> Objects.equals(t.getTitlu() + " " + t.getPictor(), text))
                .collect(Collectors.toList());
        return newEntities;
    }

    public List<Tablou> filterByTematica(String tematica) {
        List<Tablou> newEntities = entities
                .stream()
                .filter(t -> Objects.equals(t.getTematica(), tematica))
                .collect(Collectors.toList());
        return newEntities;
    }

    public List<Tablou> filterByTematicaAndCelebritate(String tematica, Double celebritate) {
        List<Tablou> newEntities = entities
                .stream()
                .filter(t-> Objects.equals(t.getTematica(), tematica) && t.getCelebritate() > celebritate)
                .collect(Collectors.toList());
        return newEntities;
    }

    public List<Tablou> sortByPictorAndTitlu() {
        List<Tablou> newEntities = entities
                .stream()
                .sorted(Comparator.comparing(Tablou::getPictor).thenComparing(Tablou::getTitlu))
                .collect(Collectors.toList());
        return newEntities;
    }

    public List<Tablou> sortByTematica() {
        List<Tablou> newEntities = entities
                .stream()
                .sorted(Comparator.comparing(Tablou::getTematica))
                .collect(Collectors.toList());
        return newEntities;
    }

    public List<Tablou> sortByCelebritateDescending() {
        List<Tablou> newEntities = entities
                .stream()
                .sorted(Comparator.comparing(Tablou::getCelebritate).reversed())
                .collect(Collectors.toList());
        return newEntities;
    }
}
