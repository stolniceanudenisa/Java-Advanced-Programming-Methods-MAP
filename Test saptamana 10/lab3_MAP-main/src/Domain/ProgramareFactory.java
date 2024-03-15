package Domain;

public class ProgramareFactory implements IEntityFactory<Programare>{
    @Override
    public Programare creareEntitate(String linie) {
        int Id_pr = Integer.parseInt(linie.split(",")[0]);
        int ID = Integer.parseInt(linie.split(",")[1]);
        String nume = linie.split(",")[2];
        String prenume = linie.split(",")[3];
        int varsta = Integer.parseInt(linie.split(",")[4]);
        Pacient pacient = new Pacient(ID,nume,prenume,varsta);
        String scop = linie.split(",")[5];
        String data = linie.split(",")[6];
        String ora = linie.split(",")[7];
        return new Programare(Id_pr,pacient,scop,data,ora);
    }

    @Override
    public String scriereEntitate(Programare ent) {
        return (ent.getId()+","+ent.getPacient().getId()+","+ent.getPacient().getNume()+","+ent.getPacient().getPrenume()+","+ent.getPacient().getVarsta()+","+ent.getScop()+","+ent.getData()+","+ent.getOra())+"\n";
    }
}
