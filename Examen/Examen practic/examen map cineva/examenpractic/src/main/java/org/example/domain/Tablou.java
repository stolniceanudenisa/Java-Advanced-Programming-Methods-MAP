package org.example.domain;

public class Tablou  extends Entity{
    String titlu;
    String pictor;

    String tematica;

    Double celebritate;

    @Override
    public String toString() {
        return "Tablou{" +
                "titlu='" + titlu + '\'' +
                ", pictor='" + pictor + '\'' +
                ", tematica='" + tematica + '\'' +
                ", celebritate=" + celebritate +
                '}';
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public Tablou(String titlu, String pictor, String tematica, Double celebritate) {
        this.titlu = titlu;
        this.pictor = pictor;
        this.tematica = tematica;
        this.celebritate = celebritate;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getPictor() {
        return pictor;
    }

    public void setPictor(String pictor) {
        this.pictor = pictor;
    }

    public Double getCelebritate() {
        return celebritate;
    }

    public void setCelebritate(Double celebritate) {
        this.celebritate = celebritate;
    }


}
