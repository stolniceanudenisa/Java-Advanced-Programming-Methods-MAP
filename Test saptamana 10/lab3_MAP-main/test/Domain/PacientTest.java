package Domain;

import org.junit.jupiter.api.Test;

import java.util.Objects;

class PacientTest {

    @Test
    void getVarsta() {
        Pacient p = new Pacient(1,"todorut","mihai",22);
        assert p.getVarsta() == 22;
    }

    @Test
    void getNume() {
        Pacient p = new Pacient(1,"todorut","mihai",22);
        assert Objects.equals(p.getNume(),"todorut");
    }

    @Test
    void getPrenume() {
        Pacient p = new Pacient(1,"todorut","mihai",22);
        assert Objects.equals(p.getPrenume(),"mihai");
    }

    @Test
    void setNume() {
        Pacient p = new Pacient(1,"todorut","mihai",22);
        p.setNume("popa");
        assert Objects.equals(p.getNume(),"popa");
    }

    @Test
    void setPrenume() {
        Pacient p = new Pacient(1,"todorut","mihai",22);
        p.setPrenume("edy");
        assert Objects.equals(p.getPrenume(),"edy");
    }

    @Test
    void setVarsta() {
        Pacient p = new Pacient(1,"todorut","mihai",22);
        p.setVarsta(31);
        assert p.getVarsta() == 31;
    }

    @Test
    void testToString() {
        Pacient p = new Pacient(1,"todorut","mihai",22);
        assert Objects.equals(p.toString(),"\nPacientul cu id-ul 1: todorut mihai de 22 ani.");

    }
}