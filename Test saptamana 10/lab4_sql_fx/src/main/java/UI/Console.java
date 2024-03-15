package UI;

import Domain.Pacient;
import Domain.Programare;
import Repository.DuplicateEntityException;
import Service.AppointmentService;
import Service.PacientService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Console {
    PacientService patientService;
    AppointmentService appointmentService;
    Scanner scanner = new Scanner(System.in);

    public Console(PacientService patientService, AppointmentService appointmentService)
    {
        this.patientService = patientService;
        this.appointmentService = appointmentService;
    }

    private void printMenu()
    {
        System.out.println();
        System.out.println("1. Add pacient");
        System.out.println("2. Add appointment");

        System.out.println("3. Delete pacient");
        System.out.println("4. Update pacient");

        System.out.println("5. Delete appointment");
        System.out.println("6. Update appointment");
        System.out.println("7. Numarul de programari pentru fiecare pacient in parte");
        System.out.println("8. Numarul total de programari pentru fiecare luna a anului");
        System.out.println("9. Numarul de zile trecute de la ultima programare a fiecarui pacient");
        System.out.println("91. Cele mai aglomerate luni ale anului");


        System.out.println("10. Show all pacients");
        System.out.println("11. Show all appointments");
        System.out.println("0. Exit");
        System.out.println();
        System.out.println("Introduceti comanda: ");
    }

    public void run() throws DuplicateEntityException {
        Scanner scan = new Scanner(System.in);
        label:
        while (true) {
            printMenu();
            String cmd = scan.nextLine();
            switch (cmd) {
                case "1":
                    addPacient();
                    break;
                case "2":
                    addAppointment();
                    break;
                case "3":
                    deletePacient();
                    break;
                case "4":
                    updatePacient();
                    break;
                case "5":
                    deleteAppointment();
                    break;
                case "6":
                    updateAppointment();
                    break;

                case "7":
                    nrProgramariPacient();
                    break;

                case "8":
                   nrProgramariLuna();
                    break;

                case "9":
                    //nrZileTrecute();
                    showDaysSinceLastAppointment();
                    break;

                case "91":
                   luniAglomerate();
                    break;

                case "10":
                    printAllPacients();
                    break;
                case "11":
                    printAllAppointments();
                    break;
                case "0":
                    break label;
                default:
                    System.out.println("\u001B[31mCOMANDA INVALIDA!\u001B[0m");
                    break;
            }
        }
    }

    private void luniAglomerate() {
//        Collection<Programare> appointments = appointmentService.getAll2();
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//        Map<String, Long> appointmentsPerMonth = appointments.stream()
//                .map(appointment -> LocalDate.parse(appointment.getData(), formatter))
//                .collect(Collectors.groupingBy(date -> date.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()),
//                        Collectors.counting()));
//
//        appointmentsPerMonth.entrySet().stream()
//                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
//                .forEach(entry -> System.out.println("Month: " + entry.getKey() + ", Appointments: " + entry.getValue()));
//
        Map<String, Long> appointmentsPerMonth = appointmentService.calculateAppointmentsPerMonth();

        appointmentsPerMonth.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(entry -> System.out.println("Month: " + entry.getKey() + ", Appointments: " + entry.getValue()));


    }


    private void showDaysSinceLastAppointment() {
        Map<Pacient, Long> daysSinceLastAppointmentPerPatient = appointmentService.calculateDaysSinceLastAppointmentPerPatient();
        Collection<Programare> appointments = appointmentService.getAll2(); // Obținerea tuturor programărilor

        daysSinceLastAppointmentPerPatient.entrySet().stream()
                .sorted(Map.Entry.<Pacient, Long>comparingByValue().reversed())
                .forEach(entry -> {
                    Pacient pacient = entry.getKey();
                    Long daysSinceLastAppointment = entry.getValue();
                    System.out.println("Pacient: " + pacient.getNume() + " " + pacient.getPrenume() + ", ID: " + pacient.getId() +
                            ", Last Appointment Date: " + getLastAppointmentDate(pacient, appointments) +
                            ", Days Since Last Appointment: " + daysSinceLastAppointment);
                });
    }


    private void nrZileTrecute() {
    Collection<Pacient> pacients = patientService.getAll2();
    Collection<Programare> appointments = appointmentService.getAll2();

    Map<Pacient, Long> daysSinceLastAppointmentPerPatient = pacients.stream()
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
                                    .filter(date -> date != null) // Remove null dates
                                    .max(LocalDate::compareTo)
                                    .map(lastAppointmentDate -> ChronoUnit.DAYS.between(lastAppointmentDate, LocalDate.now()))
                                    .orElse(Long.MAX_VALUE);
                        } catch (Exception e) {
                            return Long.MAX_VALUE;
                        }
                    }
            ));

    daysSinceLastAppointmentPerPatient.entrySet().stream()
            .sorted(Map.Entry.<Pacient, Long>comparingByValue()
                    .reversed())
            .forEach(entry -> {
                Pacient pacient = entry.getKey();
                Long daysSinceLastAppointment = entry.getValue();
                System.out.println("Pacient: " + pacient.getNume() + " " + pacient.getPrenume() + ", ID: " + pacient.getId() +
                        ", Last Appointment Date: " + getLastAppointmentDate(pacient, appointments) +
                        ", Days Since Last Appointment: " + daysSinceLastAppointment);
            });
}


    private LocalDate getLastAppointmentDate(Pacient pacient, Collection<Programare> appointments) {
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
                .filter(date -> date != null) // Remove null dates
                .max(LocalDate::compareTo)
                .orElse(null);
    }


    private void nrProgramariLuna() {
//        Collection<Programare> appointments = appointmentService.getAll2();
//
//        Map<String, Integer> appointmentsPerMonth = new HashMap<>();
//
//        // Count the number of appointments for each month
//        for (Programare appointment : appointments) {
//            String[] dateParts = appointment.getData().split("-");
//            String month = dateParts[1];
//
//            appointmentsPerMonth.put(month, appointmentsPerMonth.getOrDefault(month, 0) + 1);
//        }
//
//        // Sort the map by the number of appointments in descending order
//        List<Map.Entry<String, Integer>> sortedAppointments = new ArrayList<>(appointmentsPerMonth.entrySet());
//        sortedAppointments.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
//
//        // Display the total number of appointments for each month
//        for (Map.Entry<String, Integer> entry : sortedAppointments) {
//            String month = entry.getKey();
//            int appointmentCount = entry.getValue();
//            System.out.println("Month: " + month + ", Total Appointments: " + appointmentCount);
//        }

        // metoda 2
//        Collection<Programare> appointments = appointmentService.getAll2();
//
//        Map<String, Long> appointmentsPerMonth = appointments.stream()
//                .collect(Collectors.groupingBy(
//                        appointment -> appointment.getData().split("-")[1], // Extract month from date
//                        Collectors.counting() // Count number of appointments for each month
//                ));
//
//        // Sort the map by the number of appointments in descending order
//        appointmentsPerMonth.entrySet().stream()
//                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
//                .forEach(entry -> System.out.println("Month: " + entry.getKey() + ", Total Appointments: " + entry.getValue()));

        Map<String, Long> appointmentsPerMonth = appointmentService.getAppointmentCountPerMonth();

        appointmentsPerMonth.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(entry -> System.out.println("Month: " + entry.getKey() + ", Total Appointments: " + entry.getValue()));


    }

    private void nrProgramariPacient() {
//        Collection<Pacient> pacients = patientService.getAll2();
//
//        Map<Pacient, Integer> appointmentsPerPatient = new HashMap<>();
//
//        // Count the number of appointments for each patient
//        for (Pacient pacient : pacients) {
//            int count = 0;
//            Collection<Programare> appointments = appointmentService.getAll2();
//            for (Programare appointment : appointments) {
//                if (appointment.getPacient().getId()==(pacient.getId())) {
//                    count++;
//                }
//            }
//            appointmentsPerPatient.put(pacient, count);
//        }
//
//
//        List<Map.Entry<Pacient, Integer>> sortedAppointments = new ArrayList<>(appointmentsPerPatient.entrySet());
//        sortedAppointments.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
//
//        for (Map.Entry<Pacient, Integer> entry : sortedAppointments) {
//            Pacient pacient = entry.getKey();
//            int appointmentCount = entry.getValue();
//            System.out.println("Pacient: " + pacient.getNume() + " " + pacient.getPrenume() + ", ID: " + pacient.getId() + ", Number of Appointments: " + appointmentCount);
//        }

        //metoda 2
//        Collection<Pacient> pacients = patientService.getAll2();
//        Collection<Programare> appointments = appointmentService.getAll2();
//
//        Map<Pacient, Long> appointmentsPerPatient = pacients.stream()
//                .collect(Collectors.toMap(
//                        pacient -> pacient,
//                        pacient -> appointments.stream()
//                                .filter(appointment -> appointment.getPacient().getId() == pacient.getId())
//                                .count()
//                ));
//
//        appointmentsPerPatient.entrySet().stream()
//                .sorted(Map.Entry.<Pacient, Long>comparingByValue(Comparator.reverseOrder()))
//                .forEach(entry -> {
//                    Pacient pacient = entry.getKey();
//                    Long appointmentCount = entry.getValue();
//                    System.out.println("Pacient: " + pacient.getNume() + " " + pacient.getPrenume() + ", ID: " + pacient.getId() + ", Number of Appointments: " + appointmentCount);
//                });

        // metoda 3
        Map<Pacient, Long> appointmentsPerPatient = patientService.getAppointmentCountPerPacient(appointmentService);

        appointmentsPerPatient.entrySet().stream()
                .sorted(Map.Entry.<Pacient, Long>comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> {
                    Pacient pacient = entry.getKey();
                    Long appointmentCount = entry.getValue();
                    System.out.println("Pacient: " + pacient.getNume() + " " + pacient.getPrenume() + ", ID: " + pacient.getId() + ", Number of Appointments: " + appointmentCount);
                });

    }



    private void updateAppointment() {
        Scanner scan = new Scanner(System.in);
        try {
            System.out.println("Id-ul programarii care se va actualiza: ");
            int appId = scan.nextInt();

            System.out.println("Id-ul pacientului asociat programarii: ");
            int pacientId = scan.nextInt();

            Optional<Pacient> pacient = Optional.ofNullable(patientService.findById(pacientId));

            if (pacient.isPresent()) {
                System.out.println("Data noua: ");
                String data = scan.nextLine();
                System.out.println("Ora noua: ");
                String ora = scan.nextLine();
                System.out.println("Scopul programarii nou: ");
                String scopul = scan.nextLine();

                Programare updatedAppointment = new Programare(appId, pacient.get(), data, ora, scopul);
                appointmentService.updateAppointment(updatedAppointment);
            } else {
                System.out.println("Pacientul nu exista.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Input format is incorrect. Please enter a valid numeric ID.");
        }
    }

    private void updatePacient() {

        Scanner scan = new Scanner(System.in);
        System.out.println("Id-ul pacientului care se va actualiza: ");
        int id = scan.nextInt();
        System.out.println("Nume nou: ");
        String newNume = scan.nextLine();
        System.out.println("Prenume nou: ");
        String newPrenume = scan.nextLine();
        int newVarsta;
        try {
            System.out.println("Varsta noua: ");
            String varstaInput = scan.nextLine();
            newVarsta = Integer.parseInt(varstaInput);
        } catch (NumberFormatException e) {
            System.out.println("\u001B[31mVarsta trebuie sa fie un numar!\u001B[0m");
            return;
        }

        Pacient updatedPacient = new Pacient(id, newNume, newPrenume, newVarsta);
       // updatedPacient.setId(id);

        patientService.updatePacient(updatedPacient);
    }

    private void deleteAppointment() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Id-ul programarii care se va sterge: ");

        try {
            int id = scan.nextInt();
            appointmentService.deleteAppointment(id);
            System.out.println("\u001B[32mProgramare stearsa!\u001B[0m");
        } catch (NumberFormatException e) {
            System.out.println("\u001B[31mId must be an integer!\u001B[0m");
        } catch (IllegalArgumentException e) {
            System.out.println("\u001B[31mInvalid arguments!\u001B[0m");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

    }

    private void deletePacient() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Id-ul pacientului care se va sterge: ");

        try {
            int id = scan.nextInt();
            patientService.deletePacient(id);
            System.out.println("\u001B[32mPacient sters!\u001B[0m");
        } catch (NumberFormatException e) {
            System.out.println("\u001B[31mId must be an integer!\u001B[0m");
        } catch (IllegalArgumentException e) {
            System.out.println("\u001B[31mInvalid arguments!\u001B[0m");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

    }


    private void printAllAppointments() {
        System.out.println();
        Collection<Programare> appointments = appointmentService.getAll2();
        for (Programare p: appointments)
        {
            System.out.println(p.toString());
        }
        System.out.println();
    }

    private void printAllPacients() {
        System.out.println();
        Collection<Pacient> patients = patientService.getAll2();
        for (Pacient p: patients)
        {
            System.out.println(p.toString());
        }
        System.out.println();
    }

    private void addAppointment() {
        try {
            System.out.println();
            System.out.println("Id: ");
            int id = scanner.nextInt();

            System.out.println("Id pacient: ");
            int idPacient = scanner.nextInt();

            System.out.println("Data: ");
            String data = scanner.next();

            System.out.println("Ora: ");
            String ora = scanner.next();

            System.out.println("Tip: ");
            String tip = scanner.next();

            appointmentService.addAppointment(id, patientService.findById(idPacient), data, ora, tip);
        }
        catch (DuplicateEntityException ex)
        {
            System.out.println(ex.getMessage());
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    private void addPacient() {
        try {
            System.out.println();
            System.out.println("Id: ");
            int id = scanner.nextInt();

            System.out.println("Nume: ");
            String name = scanner.next();

            System.out.println("Prenume: ");
            String prenume = scanner.next();

            System.out.println("Varsta: ");
            int age = scanner.nextInt();

            patientService.addPacient(id, name, prenume, age);
        }
        catch (DuplicateEntityException ex)
        {
            System.out.println(ex.getMessage());
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

}