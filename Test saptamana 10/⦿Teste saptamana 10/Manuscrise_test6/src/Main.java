import Domain.Manuscript;
import Domain.Presentation;
import Repo.IRepository;
import Repo.MemoryRepository;
import Service.ManuscriptService;
import Service.PresentationService;
import Ui.Menu;
import exceptions.DuplicateEntityException;

public class Main {
    public static void main(String[] args) throws DuplicateEntityException {
        IRepository<Manuscript> manuscriptRepository = new MemoryRepository<>();
        IRepository<Presentation> presentationRepository = new MemoryRepository<>();
        ManuscriptService manuscriptService = new ManuscriptService(manuscriptRepository);
        PresentationService presentationService = new PresentationService(presentationRepository);
        Menu menu = new Menu(manuscriptService, presentationService);
        menu.run();
    }
}