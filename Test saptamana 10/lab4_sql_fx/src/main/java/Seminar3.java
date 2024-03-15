import Domain.validator.pacient.PacientValidator;
import Repository.AppointmentDbRepository;
import Repository.DuplicateEntityException;
import Repository.PatientsDbRepository;
import Service.AppointmentService;
import Service.PacientService;
import UI.Console;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Seminar3 {
    public static void main(String[] args) throws DuplicateEntityException, IOException {

        Properties properties = new Properties();


            FileInputStream input = new FileInputStream("settings.properties");
            properties.load(input);

            String repositoryType = properties.getProperty("Repository");
            String patientsFile = properties.getProperty("Pacients");
            String appointmentsFile = properties.getProperty("Appointments");


//        IRepository2<Pacient> pacientRepo;
//        IRepository2<Programare> appointmentRepo;

            if ("database".equalsIgnoreCase(repositoryType)) {
//            pacientRepo = new PatientsDbRepository();
//            appointmentRepo = new AppointmentDbRepository();

                PatientsDbRepository patientDBRepo = new PatientsDbRepository();
                AppointmentDbRepository appointmentDBRepo = new AppointmentDbRepository();
                PacientService patientService = new PacientService(patientDBRepo, new PacientValidator());
                AppointmentService appointmentService = new AppointmentService(appointmentDBRepo);
                Console console = new Console(patientService, appointmentService);

                console.run();

                patientDBRepo.closeConnection();
                appointmentDBRepo.closeConnection();
            }

//        else if ("file".equalsIgnoreCase(repoType)) {
//            repository = new FileRepository<>(); // Instantiate File Repository (for example)
//        } else if ("binary".equalsIgnoreCase(repoType)) {
//            repository = new BinaryRepository<>(); // Instantiate Binary Repository (for example)
//        } else if ("json".equalsIgnoreCase(repoType)) {
//            repository = new JsonRepository<>(); // Instantiate JSON Repository (for example)
//        } else if ("xml".equalsIgnoreCase(repoType)) {
//            repository = new XmlRepository<>(); // Instantiate XML Repository (for example)
//        }
//
            else {
                throw new IllegalArgumentException("Invalid repository type specified in settings.properties");
            }

//        PacientService patientService = new PacientService(pacientRepo, new PacientValidator());
//        AppointmentService appointmentService = new AppointmentService(appointmentRepo);
//        Console console = new Console(patientService, appointmentService);
//
//
//
//        PatientsDbRepository patientDBRepo = new PatientsDbRepository();
//       AppointmentDbRepository appointmentDBRepo = new AppointmentDbRepository();
//
//        //IRepository<Patient> patientRepository = new MemoryRepository<>();
////        IEntityFactory<Patient> patientFactory = new PatientFactory();
////        IRepository<Patient> patientRepository = new FileRepository<>("patients.txt",patientFactory);
//
//
//        PacientService patientService = new PacientService(patientDBRepo, new PacientValidator());
//        AppointmentService appointmentService = new AppointmentService(appointmentDBRepo);
//        Console console = new Console(patientService, appointmentService);
//
//        console.run();
//
//        patientDBRepo.closeConnection();
//        appointmentDBRepo.closeConnection();
            //}
//        catch (FileNotFoundException e) {
//            System.err.println("Error: " + e.getMessage());
//        } catch (IOException e) {
//            System.err.println("Error: " + e.getMessage());
//        } catch (DuplicateEntityException e) {
//            throw new RuntimeException(e);
//        } catch (RuntimeException e) {
//            System.err.println("Error: " + e.getMessage());
//        }
            // }

    }
}