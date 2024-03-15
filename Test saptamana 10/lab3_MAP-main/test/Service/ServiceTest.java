package Service;

import Domain.Pacient;
import Domain.Programare;
import Repository.MemoryRepo;
import Repository.RepoAbstract;
import Repository.RepoException;
import org.junit.jupiter.api.Test;

import java.util.Objects;



class ServiceTest {

    @Test
    void add() {
        RepoAbstract<Programare> rep = new MemoryRepo<>();
        Validator<Programare> val = new ValidatorProgramare<>();
        Service<Programare> ser = new Service<Programare>(rep,val);
        Pacient p = new Pacient(1,"Ionescu","Razvan",52);
        Programare pg = new Programare(1,p,"masea","22/02","13:31");
        Programare pg2 = new Programare(2,p,"masea","22/02","13:31");
        Programare pg3 = new Programare(3,p,"masea","21/02","13:31");
        try {
            ser.add(pg);
            assert Objects.equals(ser.getRep().get(0), pg);
            ser.add(pg3);
            ser.add(pg2);
        } catch (RepoException e) {
            assert true;
        }
    }

    @Test
    void update() {
        RepoAbstract<Pacient> rep = new MemoryRepo<>();
        Validator<Pacient> val = new ValidatorPacient<>();
        Service<Pacient> ser = new Service<Pacient>(rep,val);
        Pacient p = new Pacient(1,"Ionescu","Razvan",52);
        try {
            ser.add(p);
            assert Objects.equals(ser.getRep().get(0), p);
            Pacient p2 = new Pacient(1,"Morinelo","Tantar",42);
            Pacient p3 = new Pacient(2,"Morinelo","Raul",22);
            ser.update(p2);
            ser.update(p3);
            assert Objects.equals(ser.getRep().get(0),p2);
        } catch (RepoException e) {
            assert true;
        }
    }

    @Test
    void delete() {
        RepoAbstract<Pacient> rep = new MemoryRepo<>();
        Validator<Pacient> val = new ValidatorPacient<>();
        Service<Pacient> ser = new Service<Pacient>(rep,val);
        Pacient p = new Pacient(1,"Ionescu","Razvan",52);
        try {
            ser.add(p);
            assert Objects.equals(ser.getRep().get(0), p);
            assert ser.getRep().getSize() == 1;
            ser.delete(1);
            assert ser.getRep().getSize() == 0;
            ser.delete(2);
        } catch (RepoException e) {
            assert true;
        }
    }

}