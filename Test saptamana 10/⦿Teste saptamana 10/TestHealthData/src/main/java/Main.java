import domain.*;
import repository.*;
import service.*;
import ui.*;

public class Main {
    public static void main(String[] args) {
        Repository<BMI> bmiRepository = new Repository<BMI>();
        Repository<BP> bpRepository = new Repository<BP>();

        Service service = new Service(bmiRepository,bpRepository);

        service.addBp("28-11-2023",125,65);
        service.addBp("28-09-2023",50,100);
        service.addBmi("12-11-2023",19.04F);
        service.addBmi("11-05-2023",80.04F);

        Ui ui = new Ui(service);
        ui.run();
    }
}
