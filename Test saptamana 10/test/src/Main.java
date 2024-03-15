import Domain.HardwareInstrument;
import Domain.SoftwareInstrument;
import Repo.IRepository;
import Repo.MemoryRepository;
import Service.HardwareService;
import Service.SoftwareService;
import Ui.Menu;
import exceptions.DuplicateEntityException;

public class Main {
    public static void main(String[] args) throws DuplicateEntityException {
        IRepository<HardwareInstrument> hardwareRepository = new MemoryRepository<>();
        IRepository<SoftwareInstrument> softwareRepository = new MemoryRepository<>();

        HardwareService hardwareService = new HardwareService(hardwareRepository);
        SoftwareService softwareService = new SoftwareService(softwareRepository);

        Menu menu = new Menu(softwareService, hardwareService);
        menu.run();
    }
}