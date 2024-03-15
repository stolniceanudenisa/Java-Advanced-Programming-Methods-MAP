package org.example.domain;

public class SoftwareFactory implements IEntityFactory<SoftwareInstrument> {
    @Override
    public SoftwareInstrument createEntity(String line) {

        String[] parts = line.split(",");
        String code = parts[0];
        boolean maintenance = Boolean.parseBoolean(parts[1]);
        int versiune_software = Integer.parseInt(parts[2]);
        return new SoftwareInstrument(code, maintenance, versiune_software);


//        Long id = Long.parseLong(line.split(",")[0]);
//        String nume = line.split(",")[1];
//        String prenume = line.split(",")[2];
//        int age = Integer.parseInt(line.split(",")[3]);

//        sau asa
//        String[] parts = line.split(",");
//        Long id = Long.parseLong(parts[0]);
//        String nume = parts[1];
//        String prenume = parts[2];
//        int varsta = Integer.parseInt(parts[3]);

//        return new Pacient(id, nume, prenume, age);
    }



    @Override
    public String createString(SoftwareInstrument entity) {
        return entity.getCode() + "," + entity.getMaintenance() + "," + entity.getVersiuneSoftware() + "," + entity.getPrice();

    }
}