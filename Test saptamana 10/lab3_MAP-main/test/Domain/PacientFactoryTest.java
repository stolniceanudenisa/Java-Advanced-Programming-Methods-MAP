package Domain;

import org.junit.jupiter.api.Test;

import java.util.Objects;

class PacientFactoryTest {

    @Test
    void creareEntitate() {
        PacientFactory pf = new PacientFactory();
        Pacient p = pf.creareEntitate("1,Ionescu,Razvan,52");
        assert p.getId() == 1;
        assert p.getVarsta() == 52;
        assert Objects.equals(p.getNume(), "Ionescu");
        assert Objects.equals(p.getPrenume(),"Razvan");

    }

    @Test
    void scriereEntitate() {
        PacientFactory pf = new PacientFactory();
        Pacient p = new Pacient(1,"Ionescu","Razvan",52);
        String st = pf.scriereEntitate(p);
        assert Objects.equals(st, "1,Ionescu,Razvan,52\n");
    }
}