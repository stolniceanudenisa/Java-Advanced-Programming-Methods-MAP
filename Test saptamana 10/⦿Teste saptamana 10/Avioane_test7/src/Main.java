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
        IRepository<SoftwareInstrument> softwareRepository = new MemoryRepository<>();
        IRepository<HardwareInstrument> hardwareRepository = new MemoryRepository<>();

        SoftwareService softwareService = new SoftwareService(softwareRepository);
        HardwareService hardwareService = new HardwareService(hardwareRepository);

        Menu menu = new Menu(softwareService, hardwareService);
        menu.run();
    }
}