package Domain;

import java.io.Serializable;

public class Pacient extends Entity  implements Serializable {
    private String nume;
    private String prenume;
    private int varsta;

    public Pacient(int id, String nume, String prenume, int v){
        super(id);
        this.nume = nume;
        this.prenume = prenume;
        this.varsta = v;
    }

    public int getVarsta() {
        return varsta;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    @Override
    public String toString() {
        return ("\nPacientul cu id-ul " + this.id +": " + this.nume + " " + this.prenume+ " de "+ this.varsta + " ani.");
    }
}
