package Repository;

import Domain.Pacient;
import org.junit.jupiter.api.Test;

import java.util.Objects;



class BinaryFileRepoTest {

    @Test
    void add() {
        BinaryFileRepo<Pacient> rep = new BinaryFileRepo<>("Test.bin");
        Pacient p = new Pacient(1,"Ionescu","Razvan",52);
        try {
            rep.delete(1);
            rep.add(p);
            assert rep.get(0).getId() == 1;
            assert rep.get(0).getVarsta() == 52;
            assert Objects.equals(rep.get(0).getNume(), "Ionescu");
            assert Objects.equals(rep.get(0).getPrenume(),"Razvan");
            rep.delete(1);
        } catch (RepoException e) {assert false;}
    }

    @Test
    void delete() {
        BinaryFileRepo<Pacient> rep = new BinaryFileRepo<>("Test.bin");
        Pacient p = new Pacient(1,"Ionescu","Razvan",52);
        try {
            rep.add(p);
            assert Objects.equals(rep.getBYid(1), p);
            rep.delete(1);
            assert rep.getSize() == 0;
        } catch (RepoException e) {assert false;}
    }

    @Test
    void update() {
        BinaryFileRepo<Pacient> rep = new BinaryFileRepo<>("Test.bin");
        Pacient p = new Pacient(1,"Ionescu","Razvan",52);
        Pacient p2 = new Pacient(1,"Melean","Tantar",52);
        try {
            rep.add(p);
            assert Objects.equals(rep.getBYid(1), p);
            rep.update(p2);
            assert Objects.equals(rep.getBYid(1), p2);
        } catch (RepoException e) {assert false;}
    }
}