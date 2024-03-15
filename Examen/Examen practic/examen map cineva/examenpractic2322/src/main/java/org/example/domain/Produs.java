package org.example.domain;

public class Produs extends Entity {
    String nume;

    @Override
    public String toString() {
        return "Produs{" +
                "nume='" + nume + '\'' +
                ", categorie='" + categorie + '\'' +
                ", descriere='" + descriere + '\'' +
                ", pret=" + pret +
                '}';
    }

    String categorie;

    String descriere;

    Double pret;

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public Double getPret() {
        return pret;
    }

    public void setPret(Double pret) {
        this.pret = pret;
    }

    public Produs(String nume, String categorie, String descriere, Double pret) {
        this.nume = nume;
        this.categorie = categorie;
        this.descriere = descriere;
        this.pret = pret;
    }
}
