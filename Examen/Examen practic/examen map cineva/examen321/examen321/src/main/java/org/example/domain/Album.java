package org.example.domain;

import java.util.Objects;

public class Album extends Entity<Long>{

    private String nume;
    private String artist;
    private String gen;
    private Double pret;

    public Album(String nume, String artist, String gen, Double pret) {
        this.nume = nume;
        this.artist = artist;
        this.gen = gen;
        this.pret = pret;
    }

    public Album() {
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public Double getPret() {
        return pret;
    }

    public void setPret(Double pret) {
        this.pret = pret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Album album)) return false;
        return Objects.equals(getNume(), album.getNume()) && Objects.equals(getArtist(), album.getArtist()) && Objects.equals(getGen(), album.getGen()) && Objects.equals(getPret(), album.getPret());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNume(), getArtist(), getGen(), getPret());
    }

    @Override
    public String toString() {
        return "Album{" +
                "nume='" + nume + '\'' +
                ", artist='" + artist + '\'' +
                ", gen='" + gen + '\'' +
                ", pret=" + pret +
                '}';
    }
}
