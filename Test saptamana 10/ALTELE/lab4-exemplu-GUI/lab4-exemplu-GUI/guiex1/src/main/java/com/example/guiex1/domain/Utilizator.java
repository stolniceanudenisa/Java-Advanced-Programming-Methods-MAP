package com.example.guiex1.domain;

import java.util.List;
import java.util.Objects;

public class Utilizator extends Entity<Long>{
    private String first_name;
    private String last_name;
    private List<Utilizator> friends;

    public Utilizator(){}
    public Utilizator(String firstName, String lastName) {
        this.first_name = firstName;
        this.last_name = lastName;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public List<Utilizator> getFriends() {
        return friends;
    }

    @Override
    public String toString() {
        return "Utilizator{" +
                "firstName='" + first_name + '\'' +
                ", lastName='" + last_name + '\'' +
                ", friends=" + friends +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utilizator)) return false;
        Utilizator that = (Utilizator) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirst_name(), getLast_name(), getFriends());
    }
}