package Domain;

public class PatientFactory implements IEntityFactory<Pacient>{
    @Override
    public Pacient createEntity(String line) {
        int id = Integer.parseInt(line.split(",")[0]);
        String name = line.split(",")[1];
        String prenume = line.split(",")[2];
        int age = Integer.parseInt(line.split(",")[3]);

        return new Pacient(id, name,prenume, age);
    }

    @Override
    public String createString(Programare entity) {
        return null;
    }

    @Override
    public String createString(Pacient entity) {
        return entity.getId() + "," + entity.getNume() + "," + entity.getPrenume() + "," + entity.getVarsta();
    }
}