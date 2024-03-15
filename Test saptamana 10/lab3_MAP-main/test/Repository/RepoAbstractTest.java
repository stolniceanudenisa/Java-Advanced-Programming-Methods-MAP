package Repository;

import Domain.Pacient;
import org.junit.jupiter.api.Test;

import java.util.Objects;

class RepoAbstractTest {

    @Test
    void getAll() {
        RepoAbstract<Pacient> repo = new MemoryRepo<>();
        assert repo.getAll().isEmpty();

        try {
            Pacient p = new Pacient(1,"todorut","mihai",22);
            repo.add(p);
            assert repo.getAll().size() == 1;
            assert Objects.equals(repo.getAll().get(0),p);
            repo.add(p);
        }catch (RepoException e){assert true;}


    }

    @Test
    void get() {
        RepoAbstract<Pacient> repo = new MemoryRepo<>();

        try {
            Pacient p = new Pacient(1,"todorut","mihai",22);
            repo.add(p);
            assert Objects.equals(repo.get(0),p);
            repo.get(2);
        }catch (RepoException e){assert true;}
    }

    @Test
    void getBYid() {
        RepoAbstract<Pacient> repo = new MemoryRepo<>();

        try {
            Pacient p = new Pacient(1,"todorut","mihai",22);
            repo.add(p);
            assert Objects.equals(repo.getBYid(1),p);
            repo.getBYid(3);
        }catch (RepoException e){assert true;}
    }

    @Test
    void searchid() {
        RepoAbstract<Pacient> repo = new MemoryRepo<>();

        try {
            Pacient p = new Pacient(1,"todorut","mihai",22);
            repo.add(p);
            assert repo.searchid(1);
            assert !repo.searchid(2);
        }catch (RepoException e){assert true;}
    }

    @Test
    void searchBYid() {
        RepoAbstract<Pacient> repo = new MemoryRepo<>();

        try {
            Pacient p = new Pacient(1,"todorut","mihai",22);
            repo.add(p);
            assert Objects.equals(repo.searchBYid(1),0);
            repo.searchBYid(3);
        }catch (RepoException e){assert true;}
    }

    @Test
    void getSize() {
        RepoAbstract<Pacient> repo = new MemoryRepo<>();
        assert repo.getSize() == 0;
        try {
            Pacient p = new Pacient(1,"todorut","mihai",22);
            repo.add(p);
            assert repo.getSize() == 1;
        }catch (RepoException e){assert true;}
    }
}