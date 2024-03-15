package Repository;

import Domain.Pacient;
import Domain.Programare;
import Domain.ProgramareFactory;
import org.junit.jupiter.api.Test;

import java.util.Objects;

class TextFileRepoTest {


    @Test
    void add() {
        ProgramareFactory pg = new ProgramareFactory();
        TextFileRepo<Programare> rep = new TextFileRepo<>("Test.txt",pg);
        Pacient p = new Pacient(1,"Ionescu","Razvan",52);
        Programare prg = new Programare(1,p,"masea","12/11","13:10");
        try {
            rep.delete(1);
            rep.add(prg);
            assert rep.get(0).getId() == 1;
            assert rep.get(0).getPacient().getId() == 1;
            assert rep.get(0).getPacient().getVarsta() == 52;
            assert Objects.equals(rep.get(0).getPacient().getNume(), "Ionescu");
            assert Objects.equals(rep.get(0).getPacient().getPrenume(), "Razvan");
            assert Objects.equals(rep.get(0).getOra(), "13:10");
            assert Objects.equals(rep.get(0).getData(), "12/11");
            assert Objects.equals(rep.get(0).getScop(), "masea");
            rep.delete(1);
        } catch (RepoException e) {
            assert false;
        }
    }

    @Test
    void delete() {
        ProgramareFactory pg = new ProgramareFactory();
        TextFileRepo<Programare> rep = new TextFileRepo<>("Test.txt",pg);
        Pacient p = new Pacient(1,"Ionescu","Razvan",52);
        Programare prg = new Programare(1,p,"masea","12/11","13:10");
        try {
            rep.add(prg);
            assert rep.getSize() == 1;
            rep.delete(1);
            assert rep.getSize() == 0;
        } catch (RepoException e) {
            assert false;
        }

    }

    @Test
    void update() {
        ProgramareFactory pg = new ProgramareFactory();
        TextFileRepo<Programare> rep = new TextFileRepo<>("Test.txt",pg);
        Pacient p = new Pacient(1,"Ionescu","Razvan",52);
        Programare prg = new Programare(1,p,"masea","12/11","13:10");
        try {
            rep.add(prg);
            assert rep.get(0).getId() == 1;
            assert rep.get(0).getPacient().getId() == 1;
            assert rep.get(0).getPacient().getVarsta() == 52;
            assert Objects.equals(rep.get(0).getPacient().getNume(), "Ionescu");
            assert Objects.equals(rep.get(0).getPacient().getPrenume(), "Razvan");
            assert Objects.equals(rep.get(0).getOra(), "13:10");
            assert Objects.equals(rep.get(0).getData(), "12/11");
            assert Objects.equals(rep.get(0).getScop(), "masea");

            Pacient p2 = new Pacient(1,"Maximilian","Tantar",31);
            Programare prg2 = new Programare(1,p2,"ochi","22/04","13:05");
            rep.update(prg2);
            assert rep.get(0).getId() == 1;
            assert rep.get(0).getPacient().getId() == 1;
            assert rep.get(0).getPacient().getVarsta() == 31;
            assert Objects.equals(rep.get(0).getPacient().getNume(), "Maximilian");
            assert Objects.equals(rep.get(0).getPacient().getPrenume(), "Tantar");
            assert Objects.equals(rep.get(0).getOra(), "13:05");
            assert Objects.equals(rep.get(0).getData(), "22/04");
            assert Objects.equals(rep.get(0).getScop(), "ochi");

            assert Objects.equals(rep.get(0),prg2);

        } catch (RepoException e) {
            assert false;
        }

    }

}