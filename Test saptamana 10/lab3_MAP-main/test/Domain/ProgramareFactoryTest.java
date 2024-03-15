package Domain;

import org.junit.jupiter.api.Test;

import java.util.Objects;

class ProgramareFactoryTest {

    @Test
    void creareEntitate() {
        ProgramareFactory pgf = new ProgramareFactory();
        Programare pg = pgf.creareEntitate("1,1,Ionescu,Razvan,52,masea,12/11,13:10");
        assert pg.getId() == 1;
        assert pg.getPacient().getId() == 1;
        assert pg.getPacient().getVarsta() == 52;
        assert Objects.equals(pg.getPacient().getNume(), "Ionescu");
        assert Objects.equals(pg.getPacient().getPrenume(), "Razvan");
        assert Objects.equals(pg.getOra(), "13:10");
        assert Objects.equals(pg.getData(), "12/11");
        assert Objects.equals(pg.getScop(), "masea");
    }

    @Test
    void scriereEntitate() {
        ProgramareFactory pgf = new ProgramareFactory();
        Pacient p = new Pacient(1,"Ionescu","Razvan",52);
        Programare pg = new Programare(1,p,"masea","12/11","13:10");
        assert Objects.equals(pgf.scriereEntitate(pg), "1,1,Ionescu,Razvan,52,masea,12/11,13:10\n");
    }
}