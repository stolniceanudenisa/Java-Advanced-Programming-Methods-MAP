package Repository;

import Domain.Pacient;
import org.junit.jupiter.api.Test;

import java.util.Objects;

class MemoryRepoTest {

    @Test
    void add() {
        MemoryRepo<Pacient> repo = new MemoryRepo<>();
        try {
            Pacient p = new Pacient(1,"todorut","mihai",22);
            repo.add(p);
            assert Objects.equals(repo.get(0),p);
        }catch (RepoException e){assert false;}
    }

    @Test
    void delete() {
        MemoryRepo<Pacient> repo = new MemoryRepo<>();
        try {
            Pacient p = new Pacient(1,"todorut","mihai",22);
            repo.add(p);
            assert Objects.equals(repo.get(0),p);
            repo.delete(1);
            assert repo.getAll().isEmpty();
        }catch (RepoException e){assert false;}
    }

    @Test
    void update() {
        MemoryRepo<Pacient> repo = new MemoryRepo<>();
        try {
            Pacient p = new Pacient(1,"todorut","mihai",22);
            repo.add(p);
            assert Objects.equals(repo.get(0),p);
            Pacient p2 = new Pacient(1,"poputa","eduard",51);
            repo.update(p2);
            assert Objects.equals(repo.get(0),p2);
        }catch (RepoException e){assert false;}
    }
}