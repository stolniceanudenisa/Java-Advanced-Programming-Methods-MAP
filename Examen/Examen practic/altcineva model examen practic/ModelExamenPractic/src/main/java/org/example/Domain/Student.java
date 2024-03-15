package org.example.Domain;

import java.util.Objects;

public class Student {
    private long id;
    private String nume;
    private String prenume;
    private String grupa;
    private Double media;

    public Student() {
    }

    public Student(long id, String nume, String prenume, String grupa, Double media) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.grupa = grupa;
        this.media = media;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
    public int hashCode() {
        return Objects.hash(nume, prenume, grupa, media);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || this.getClass() != obj.getClass())
            return false;
        Student student = (Student) obj;
        return (student.nume.equals(this.nume) && student.prenume.equals(this.prenume)
                && student.grupa.equals(this.grupa) && Objects.equals(student.media, this.media));
    }

    @Override
    public String toString() {
        return "Student{" +
                "nume='" + nume + "'\n" +
                "prenume='" + prenume + "'\n" +
                "grupa='" + grupa + "'\n" +
                "media='" + media + "'}";
    }
}
