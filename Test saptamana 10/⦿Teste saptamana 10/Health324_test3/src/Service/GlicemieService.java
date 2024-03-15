package Service;

import Domain.BMI;
import Domain.Glicemie;
import Repository.IRepository;
import exceptions.DuplicateEntityException;

import java.util.List;
import java.util.stream.Collectors;

public class GlicemieService {
    IRepository<Glicemie> repository;

    public GlicemieService(IRepository<Glicemie> repository) {
        this.repository = repository;
    }

    public void addGlicemie(String date, float value ) throws DuplicateEntityException {

        Glicemie glicemie = new Glicemie(date, value);
        repository.add(glicemie);
    }

    public Glicemie findByDate(String date)
    {
        return repository.findByDate(date);
    }

    public Iterable<Glicemie> getAll() {
        return repository.getAll();
    }

    public Glicemie[] saveToText() {
        List<Glicemie> entitati = (List<Glicemie>) repository.getAll();
        List<Glicemie> entitatiFiltrateSiSortate = entitati.stream()
                .filter(glicemie -> glicemie.getValue() > 100 || glicemie.getValue() < 70)
                .collect(Collectors.toList());
        return entitatiFiltrateSiSortate.toArray(new Glicemie[entitatiFiltrateSiSortate.size()]);
    }
}