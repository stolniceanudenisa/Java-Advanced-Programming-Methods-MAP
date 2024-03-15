import Domain.BMI;
import Domain.BP;
import Repository.IRepository;
import Repository.Repository;
import Repository.TextFileRepository.BMIFileRepository;
import Repository.TextFileRepository.BPFileRepository;
import Service.IService;
import Service.Service;
import UserInterface.UserInterface;

public class Main {
    public static void main(String[] args) {

//        IRepository<BMI> repoBMI = new Repository<BMI>();
        IRepository<BMI> repoBMI = new BMIFileRepository<>("BMI.txt");
        IService<BMI> bmiService = new Service<>(repoBMI);

//        IRepository<BP> repoBP = new Repository<BP>();
        IRepository<BP> repoBP = new BPFileRepository<>("BP.txt");
        IService<BP> bpService = new Service<>(repoBP);

        UserInterface ui = new UserInterface(bmiService, bpService);

        ui.ShowUserInterface();
    }
}