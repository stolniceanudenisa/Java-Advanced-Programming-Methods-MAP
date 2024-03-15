package Domain;

public class PacientFactory implements IEntityFactory<Pacient>{
    @Override
    public Pacient creareEntitate(String linie) {
        int ID = Integer.parseInt(linie.split(",")[0]);
        String nume = linie.split(",")[1];
        String prenume = linie.split(",")[2];
        int varsta = Integer.parseInt(linie.split(",")[3]);
        return new Pacient(ID,nume,prenume,varsta);
    }

    @Override
    public String scriereEntitate(Pacient ent) {
        return(ent.getId()+","+ent.getNume()+","+ent.getPrenume()+","+ent.getVarsta()+"\n");
    }


}
