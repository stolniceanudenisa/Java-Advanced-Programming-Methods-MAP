package Service;

import Domain.Pacient;
import Domain.Programare;
import Repository.DuplicateEntityException;
import Repository.IRepository2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class AppointmentService {
    IRepository2<Programare> programareRepository;

    public AppointmentService(IRepository2<Programare> programareRepository) {
        this.programareRepository = programareRepository;
    }

    public void addAppointment(int id, Pacient pacient, String data, String ora, String tip) throws DuplicateEntityException {
        this.programareRepository.add(new Programare(id, pacient, data, ora, tip));
        //repos_calling();
    }

    public void deleteAppointment(int id) {
        this.programareRepository.remove(id);
        //repos_calling();
    }

    public void updateAppointment(Programare programare) {
        this.programareRepository.update(programare);
       // repos_calling();
    }


    public Collection<Programare> getAll2() {
        return this.programareRepository.getAll2();
    }


    public Programare findById(int id) {
        return this.programareRepository.findById(id);
    }

    public Map<String, Long> getAppointmentCountPerMonth() {
        Collection<Programare> appointments = this.getAll2();

        return appointments.stream()
                .collect(Collectors.groupingBy(
                        appointment -> appointment.getData().split("-")[1], // Extract month from date
                        Collectors.counting() // Count number of appointments for each month
                ));
    }

    public Map<String, Long> calculateAppointmentsPerMonth() {
        Collection<Programare> appointments = this.getAll2();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return appointments.stream()
                .map(appointment -> LocalDate.parse(appointment.getData(), formatter))
                .collect(Collectors.groupingBy(
                        date -> date.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()),
                        Collectors.counting()
                ));
    }


    public Map<Pacient, Long> calculateDaysSinceLastAppointmentPerPatient() {

        Collection<Pacient> pacients = this.getAll2().stream()
                .map(Programare::getPacient)
                .distinct()
                .collect(Collectors.toList());

        Collection<Programare> appointments = this.getAll2();

        return pacients.stream()
                .collect(Collectors.toMap(
                        pacient -> pacient,
                        pacient -> {
                            try {
                                return appointments.stream()
                                        .filter(appointment -> appointment.getPacient().getId() == pacient.getId())
                                        .map(Programare::getData)
                                        .map(dateString -> {
                                            try {
                                                return LocalDate.parse(dateString);
                                            } catch (DateTimeParseException e) {
                                                // Handle invalid date format
                                                return null;
                                            }
                                        })
                                        .filter(Objects::nonNull) // Remove null dates using Objects::nonNull
                                        .max(LocalDate::compareTo)
                                        .map(lastAppointmentDate -> ChronoUnit.DAYS.between(lastAppointmentDate, LocalDate.now()))
                                        .orElse(Long.MAX_VALUE);
                            } catch (Exception e) {
                                // Handle other exceptions
                                return Long.MAX_VALUE;
                            }
                        }
                ));
    }


}

//    private void repos_calling() {
//        if (this.programareRepository instanceof FileRepository) {
//            ((FileRepository<Programare>) this.programareRepository).saveToFile();
//        }
//        if (this.programareRepository instanceof BinaryFileRepository<Programare>) {
//            ((BinaryFileRepository<Programare>) this.programareRepository).saveToFile();
//        }
//        if (this.programareRepository instanceof XmlFileRepository<Programare>) {
//            ((XmlFileRepository<Programare>) this.programareRepository).saveToXml();
//        }
//        if (this.programareRepository instanceof JsonFileRepository<Programare>) {
//            ((JsonFileRepository<Programare>) this.programareRepository).saveToFile();
//        }
//    }


//    public Iterable<Programare> filterAppointmentsByDate(String date) {
//        return this.programareRepository.getAll2().stream()
//                .filter(programare -> programare.getData().contains(date))
//                .toList();
//    }
//
//    public Iterable<Programare> filterAppointmentsByPacientName(String name) {
//        return this.programareRepository.getAll2().stream()
//                .filter(programare -> programare.getPacient().getNume().contains(name))
//                .toList();
//    }
//
//    public Iterable<Programare> filterAppointmentsByPacientFirstName(String firstName) {
//        return this.programareRepository.getAll2().stream()
//                .filter(programare -> programare.getPacient().getPrenume().contains(firstName))
//                .toList();
//    }
//
//    public Iterable<Programare> filterAppointmentsByPacientAge(Integer age) {
//        return this.programareRepository.getAll2().stream()
//                .filter(programare -> programare.getPacient().getVarsta().equals(age))
//                .toList();
//    }
//
//
//    public Iterable<Programare> filterAppointmentsByPacientFirstNameAndDate(String firstName, String date) {
//        return this.programareRepository.getAll2().stream()
//                .filter(programare -> programare.getPacient().getPrenume().contains(firstName) && programare.getData().contains(date))
//                .toList();
//    }