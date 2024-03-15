package Domain;

public interface IEntityFactory<T extends Entity> {
    T createEntity(String line);

    String createString(Programare entity);

    String createString(Pacient entity);
}