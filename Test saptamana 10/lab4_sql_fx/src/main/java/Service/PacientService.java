package Service;

import Domain.Pacient;
import Domain.Programare;
import Domain.validator.pacient.PacientValidator;
import Domain.validator.pacient.ValidationException;
import Repository.DuplicateEntityException;
import Repository.IRepository2;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PacientService {
    IRepository2<Pacient> pacientRepository;
    private final PacientValidator pacientValidator;


    public PacientService(IRepository2<Pacient> pacientRepository, PacientValidator pacientValidator) {
        this.pacientRepository = pacientRepository;
        this.pacientValidator = pacientValidator;

    }

    public void addPacient(int id, String nume, String prenume, Integer varsta) throws DuplicateEntityException {
        try {
            Pacient newPacient = new Pacient(id, nume, prenume, varsta);

            this.pacientValidator.validate(newPacient);
            this.pacientRepository.add(new Pacient(id, nume, prenume, varsta));

//            if (this.pacientRepository instanceof FileRepository) {
//                ((FileRepository<Pacient>) this.pacientRepository).saveToFile();
//            }
//            if (this.pacientRepository instanceof BinaryFileRepository<Pacient>) {
//                ((BinaryFileRepository<Pacient>) this.pacientRepository).saveToFile();
//            }
//            if (this.pacientRepository instanceof XmlFileRepository<Pacient>) {
//                ((XmlFileRepository<Pacient>) this.pacientRepository).saveToXml();
//            }
//            if (this.pacientRepository instanceof JsonFileRepository<Pacient>) {
//                ((JsonFileRepository<Pacient>) this.pacientRepository).saveToFile();
//            }

        } catch (ValidationException | DuplicateEntityException e) {
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
        }
    }

    public void updatePacient(Pacient pacient) {
        try {
            pacientValidator.validate(pacient);
            this.pacientRepository.update(pacient);

           // repos_calling();

            System.out.println("\u001B[32mPacient actualizat!\u001B[0m");
        } catch (IllegalArgumentException e) {
            System.out.println("\u001B[31mInvalid arguments!\u001B[0m");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public Collection<Pacient> getAll2() {
        return this.pacientRepository.getAll2();
    }


    public List<Pacient> filterPacientsByName(String name) {
        return this.pacientRepository.getAll2().stream()
                .filter(pacient -> pacient.getNume().contains(name))
                .toList();
    }


    public void deletePacient(int id) {
        this.pacientRepository.remove(id);
        //repos_calling();
    }


    public Pacient findById(int pacientId) {
        return this.pacientRepository.findById(pacientId);
    }

    public Map<Pacient, Long> getAppointmentCountPerPacient(AppointmentService appointmentService) {
        Collection<Pacient> pacients = this.getAll2();
        Collection<Programare> appointments = appointmentService.getAll2();

        return pacients.stream()
                .collect(Collectors.toMap(
                        pacient -> pacient,
                        pacient -> appointments.stream()
                                .filter(appointment -> appointment.getPacient().getId() == pacient.getId())
                                .count()
                ));
    }

}






//    private void repos_calling() {
//        if (this.pacientRepository instanceof FileRepository) {
//            ((FileRepository<Pacient>) this.pacientRepository).saveToFile();
//        }
//        if (this.pacientRepository instanceof BinaryFileRepository) {
//            ((BinaryFileRepository<Pacient>) this.pacientRepository).saveToFile();
//        }
//        if (this.pacientRepository instanceof XmlFileRepository<Pacient>) {
//            ((XmlFileRepository<Pacient>) this.pacientRepository).saveToXml();
//        }
//        if (this.pacientRepository instanceof JsonFileRepository<Pacient>) {
//            ((JsonFileRepository<Pacient>) this.pacientRepository).saveToFile();
//        }
//    }

//    public void addPacient1(Pacient pacient) throws DuplicateEntityException {
//        this.pacientRepository.add(pacient);
//    }