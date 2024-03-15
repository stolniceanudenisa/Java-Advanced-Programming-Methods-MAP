package org.example.domain;

public class Excursie extends Entity {

    String oras;
    String atractie;
    String categorie;
    Double pret;

    public Excursie(Long id, String oras, String atractie, String categorie, Double pret) {
        super(id);
        this.oras = oras;
        this.atractie = atractie;
        this.categorie = categorie;
        this.pret = pret;
    }

    public String getOras() {
        return oras;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    public String getAtractie() {
        return atractie;
    }

    public void setAtractie(String atractie) {
        this.atractie = atractie;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Double getPret() {
        return pret;
    }

    public void setPret(Double pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "Excursie{" +
                "id=" + id +
                ", oras='" + oras + '\'' +
                ", atractie='" + atractie + '\'' +
                ", categorie='" + categorie + '\'' +
                ", pret=" + pret +
                '}';
    }
}
