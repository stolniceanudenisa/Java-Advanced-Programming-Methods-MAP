import Domain.FlightInstrument;
import Repository.FlightRepository;
import Service.FlightService;

import UI.UI;

public class Main {
    public static void main(String[] args) {


        FlightRepository flightRepository = new FlightRepository();
//        FlightRepository<FlightInstrument> repoBMI = new BMIFileRepository<>("BMI.txt");
        FlightService flightService = new FlightService(flightRepository);

        UI ui = new UI(flightService);

        ui.ShowUserInterface();
    }
}