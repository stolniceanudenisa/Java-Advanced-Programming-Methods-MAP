package org.example.domain;

public class Student extends Entity{
    String nume;
    String prenume;
    String grupa;
    Double media;

    public Student(Long id, String nume, String prenume, String grupa, Double media) {
        super(id);
        this.nume = nume;
        this.prenume = prenume;
        this.grupa = grupa;
        this.media = media;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getGrupa() {
        return grupa;
    }

    public void setGrupa(String grupa) {
        this.grupa = grupa;
    }

    public Double getMedia() {
        return media;
    }

    public void setMedia(Double media) {
        this.media = media;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +'\''+
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", grupa='" + grupa + '\'' +
                ", media=" + media +
                '}';
    }
}
