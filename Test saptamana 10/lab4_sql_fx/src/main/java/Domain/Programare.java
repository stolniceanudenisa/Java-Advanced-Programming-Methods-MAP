package Domain;


import java.io.Serializable;
import java.util.Objects;

public class Programare extends Entity implements Serializable {
    private Pacient pacient;
    private String data;
    private String ora;
    private String scopulProgramarii;



    public Programare(int id, Pacient pacient, String data, String ora, String scopulProgramarii) {
        super(id);
        this.pacient = pacient;
        this.data = data;
        this.ora = ora;
        this.scopulProgramarii = scopulProgramarii;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public String getScopulProgramarii() {
        return scopulProgramarii;
    }

    public void setScopulProgramarii(String scopulProgramarii) {
        this.scopulProgramarii = scopulProgramarii;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Programare programare = (Programare) o;
        return Objects.equals(getId(), programare.getId()) &&
                Objects.equals(getPacient(), programare.getPacient()) &&
                Objects.equals(getData(), programare.getData()) &&
                Objects.equals(getOra(), programare.getOra()) &&
                Objects.equals(getScopulProgramarii(), programare.getScopulProgramarii());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPacient(), getData(), getOra(), getScopulProgramarii());
    }

    @Override
    public String toString() {
        return "Programare: " +
                "pacient: " + pacient +
                ", data: '" + data + '\'' +
                ", ora: '" + ora + '\'' +
                ", scopulProgramarii: '" + scopulProgramarii ;
    }
}