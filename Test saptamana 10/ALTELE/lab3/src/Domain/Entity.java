package Domain;

public class Entity {
    protected int id_entity;

    public int getId_entity() {
        return id_entity;
    }

    public void setId_entity(int id_entity) {
        this.id_entity = id_entity;
    }

    public Entity(int id_entity) {

        this.id_entity = id_entity;
    }
}
