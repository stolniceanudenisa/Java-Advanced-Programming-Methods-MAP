import Domain.Settings;
import Domain.SmokeSensor;
import Domain.TemperatureSensor;
import Service.SmokeService;
import Service.TemperatureService;
import Ui.Menu;
import exceptions.DuplicateEntityException;
import repository.IRepository;
import repository.MemoryRepository;

public class Main {
    public static void main(String[] args) throws DuplicateEntityException {
     Settings settings = Settings.getInstance();
//     String fileName = settings.getFileName();
//      String location = settings.getLocation();

      IRepository<SmokeSensor> smokeRepository = new MemoryRepository<>();
    IRepository<TemperatureSensor> temperatureRepository = new MemoryRepository<>();
    TemperatureService temperatureService = new TemperatureService(temperatureRepository);
    SmokeService smokeService = new SmokeService(smokeRepository);
    Menu menu = new Menu(smokeService, temperatureService);
    menu.run();

    }
}