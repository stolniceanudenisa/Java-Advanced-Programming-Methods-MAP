import Repository.Repository;
import Ui.Menu;
import Service.Service;


public class Main {
    public static void main(String[] args) {
        Repository repository = new Repository();
        Service service = new Service(repository);
        Menu menu = new Menu(service);
        menu.run();

    }
}