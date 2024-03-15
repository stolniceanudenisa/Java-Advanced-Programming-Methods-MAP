package Domain;

import Service.PacientService;

public class ProgramareFactory implements IEntityFactory<Programare> {
    private final PacientService pacientService;

    public ProgramareFactory(PacientService pacientService) {
        this.pacientService = pacientService;
    }

    @Override
    public Programare createEntity(String line) {
        String[] parts = line.split(",");
        int appointmentId = Integer.parseInt(parts[0]);
        int pacientId = Integer.parseInt(parts[1]);
        String date = parts[2];
        String time = parts[3];
        String appointmentType = parts[4];

        try {
            Pacient pacient = pacientService.findById(pacientId);
            return new Programare(appointmentId, pacient, date, time, appointmentType);
        } catch (Exception e) {
            // Handle exceptions or return null if necessary
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String createString(Programare entity) {
        return entity.getId() + "," + entity.getPacient().getId() + "," + entity.getData() + "," +
                entity.getOra() + "," + entity.getScopulProgramarii();
    }

    @Override
    public String createString(Pacient entity) {
        return null;
    }
}