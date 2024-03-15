import Domain.Block;
import Domain.House;
import Repo.IRepository;
import Repo.MemoryRepository;
import Service.BlockService;
import Service.HouseService;
import Ui.Menu;
import exceptions.DuplicateEntityException;

public class Main {
    public static void main(String[] args) throws DuplicateEntityException {
        IRepository<Block> blockRepository = new MemoryRepository<>();
        IRepository<House> houseRepository = new MemoryRepository<>();

        BlockService blockService = new BlockService(blockRepository);
        HouseService houseService = new HouseService(houseRepository);

        Menu menu = new Menu(houseService, blockService);
        menu.run();
    }
}