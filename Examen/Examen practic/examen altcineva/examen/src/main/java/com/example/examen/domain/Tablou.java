package com.example.examen.domain;

public class Tablou extends Entity {
    String titlu;
    String pictor;
    String tematica;
    Double celebritate;

    public Tablou(Long id, String titlu, String pictor, String tematica, Double celebritate) {
        super(id);
        this.titlu = titlu;
        this.pictor = pictor;
        this.tematica = tematica;
        this.celebritate = celebritate;
    }

    public Tablou(Tablou tablou) {
        this.id = tablou.id;
        this.titlu = tablou.titlu;
        this.pictor = tablou.pictor;
        this.tematica = tablou.tematica;
        this.celebritate = tablou.celebritate;
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

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public Double getCelebritate() {
        return celebritate;
    }

    public void setCelebritate(Double celebritate) {
        this.celebritate = celebritate;
    }

    @Override
    public String toString() {
        return "Tablou{" +
                "id=" + id + '\'' +
                ", titlu='" + titlu + '\'' +
                ", pictor='" + pictor + '\'' +
                ", tematica='" + tematica + '\'' +
                ", celebritate=" + celebritate +
                '}';
    }
}
