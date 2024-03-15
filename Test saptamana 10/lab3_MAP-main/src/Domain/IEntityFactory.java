package Domain;

public interface IEntityFactory<T extends Entity> {
    T creareEntitate(String linie);
    String scriereEntitate(T ent);
}
