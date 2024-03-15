package com.example.examen.domain;

public class Entity {
    protected Long id;

    /**
     * Default constructor
     */
    public Entity() {
    }

    /**
     * Constructor with parameters
     * @param id int - Entity id
     */
    public Entity(Long id) {
        this.id = id;
    }

    /**
     * Id getter
     * @return int - Entity id
     */
    public Long getId() {
        return id;
    }

    /**
     * Id setter
     * @param id int - new Entity id
     */
    public void setId(Long id) {
        this.id = id;
    }
}
