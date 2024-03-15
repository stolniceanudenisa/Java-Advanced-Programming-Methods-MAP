package Domain;

public class Pacient extends Entity {
    private String name;
    private String prenume;
    private int age;

    public Pacient(int ID, String name, String prenume,int age) {
        super(ID);
        this.name = name;
        this.prenume = prenume;
        this.age = age;
    }

    public String getNume() {
        return name;
    }

    public int getVarsta() {
        return age;
    }


    public String getPrenume() {
        return prenume;
    }

    public void setNume(String nume) {
        this.name = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setVarsta(int varsta) {
        this.age = varsta;
    }

    public int getId () {
        return id;
    }


    @Override
    public String toString()
    {
        return "Id: " + id + ", Name: " + name + ", Age: " + age;
    }
}