import Domain.BMI;
import Domain.BP;
import Domain.Glicemie;
import Repository.IRepository;
import Repository.MemoryRepository;
import Service.BMIService;
import Service.BPService;
import Service.GlicemieService;
import Ui.Menu;
import exceptions.DuplicateEntityException;

public class Main {
    public static void main(String[] args) throws DuplicateEntityException {
        IRepository<BMI> bmiRepository = new MemoryRepository<>();
        IRepository<BP> bpRepository = new MemoryRepository<>();
        IRepository<Glicemie> glicemieRepository = new MemoryRepository<>();

        BMIService bmiService = new BMIService(bmiRepository);
        BPService bpService = new BPService(bpRepository);
        GlicemieService glicemieService = new GlicemieService(glicemieRepository);
        Menu menu = new Menu(bmiService,bpService,glicemieService);
        menu.run();

    }
}