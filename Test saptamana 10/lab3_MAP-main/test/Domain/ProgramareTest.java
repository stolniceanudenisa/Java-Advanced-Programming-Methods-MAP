package Domain;

import org.junit.jupiter.api.Test;

import java.util.Objects;

class ProgramareTest {

    @Test
    void getPacient() {
        Pacient p = new Pacient(1,"todorut","mihai",22);
        Programare prog = new Programare(1,p,"masea","22/01","12:31");
        assert Objects.equals(prog.getPacient(),p);
    }

    @Test
    void getData() {
        Pacient p = new Pacient(1,"todorut","mihai",22);
        Programare prog = new Programare(1,p,"masea","22/01","12:31");
        assert Objects.equals(prog.getData(),"22/01");
    }

    @Test
    void getOra() {
        Pacient p = new Pacient(1,"todorut","mihai",22);
        Programare prog = new Programare(1,p,"masea","22/01","12:31");
        assert Objects.equals(prog.getOra(),"12:31");
    }

    @Test
    void getScop() {
        Pacient p = new Pacient(1,"todorut","mihai",22);
        Programare prog = new Programare(1,p,"masea","22/01","12:31");
        assert Objects.equals(prog.getScop(),"masea");
    }

    @Test
    void setData() {
        Pacient p = new Pacient(1,"todorut","mihai",22);
        Programare prog = new Programare(1,p,"masea","22/01","12:31");
        prog.setData("28/02");
        assert Objects.equals(prog.getData(),"28/02");
    }

    @Test
    void setOra() {
        Pacient p = new Pacient(1,"todorut","mihai",22);
        Programare prog = new Programare(1,p,"masea","22/01","12:31");
        prog.setOra("11:11");
        assert Objects.equals(prog.getOra(),"11:11");
    }

    @Test
    void setPacient() {
        Pacient p = new Pacient(1,"todorut","mihai",22);
        Programare prog = new Programare(1,p,"masea","22/01","12:31");
        Pacient p2 = new Pacient(2,"poputa","eduard",51);
        prog.setPacient(p2);
        assert Objects.equals(prog.getPacient(),p2);
    }

    @Test
    void setScop() {
        Pacient p = new Pacient(1,"todorut","mihai",22);
        Programare prog = new Programare(1,p,"masea","22/01","12:31");
        prog.setScop("minte");
        assert Objects.equals(prog.getScop(),"minte");
    }

    @Test
    void testToString() {
        Pacient p = new Pacient(1,"todorut","mihai",22);
        Programare prog = new Programare(1,p,"masea","22/01","12:31");
        assert Objects.equals(prog.toString(),"1 22/01 la ora: 12:31 \nPacientul cu id-ul 1: todorut mihai de 22 ani.\n avand ca scop: masea");
    }
}