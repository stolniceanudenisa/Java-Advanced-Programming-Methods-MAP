package UI;

import Domain.FlightInstrument;
import Domain.HardwareInstrument;
import Domain.SoftwareInstrument;
import Service.FlightService;

import java.util.*;
import java.util.logging.Filter;

public class UI {
    FlightService flightService;

    public UI(FlightService flightService) {
        this.flightService = flightService;
    }

    void addFirst4Entities() {


        SoftwareInstrument softwareInstrument1 = new SoftwareInstrument("1", false, 1);
        SoftwareInstrument softwareInstrument2 = new SoftwareInstrument("2", true, 2);

        HardwareInstrument hardwareInstrument1 = new HardwareInstrument("1", false, "Altitudine");
        HardwareInstrument hardwareInstrument2 = new HardwareInstrument("2", true, "Starea motorului");


        flightService.add(softwareInstrument1);
        flightService.add(softwareInstrument2);
        flightService.add(hardwareInstrument1);
        flightService.add(hardwareInstrument2);
    }

    private void getAllEntities() {
        for (FlightInstrument flightInstrument : flightService.getAllEntities())
            System.out.println(flightInstrument.toString());
    }

    private void addEntity() {
        System.out.println("Introduceti Flight sau Software sau Hardware");

        Scanner scanner = new Scanner(System.in);
        String readLine = scanner.nextLine();

        String BMI_BP = readLine;

        if (Objects.equals(BMI_BP, "Flight")) {
            System.out.println("Suntem pe Flight");

            System.out.println("String <=> code: ");
            String code = scanner.nextLine();
            System.out.println("Boolean <=> value: ");
            boolean value = Boolean.parseBoolean(scanner.nextLine());

            FlightInstrument flightInstrument;
            flightInstrument = new FlightInstrument(code, value);
            flightService.add(flightInstrument);

        } else if (Objects.equals(BMI_BP, "Software")) {
            System.out.println("Suntem pe Software");

            System.out.println("String <=> code: ");
            String code = scanner.nextLine();
            System.out.println("Boolean <=> value: ");
            boolean value = Boolean.parseBoolean(scanner.nextLine());

            System.out.println("Integer <=> version: ");
            int version = Integer.parseInt(scanner.nextLine());

            FlightInstrument flightInstrument = new SoftwareInstrument(code, value, version);
            flightService.add(flightInstrument);
        } else if (Objects.equals(BMI_BP, "Hardware")) {
            System.out.println("Suntem pe Hardware");

            System.out.println("String <=> code: ");
            String code = scanner.nextLine();
            System.out.println("Boolean <=> value: ");
            boolean value = Boolean.parseBoolean(scanner.nextLine());

            System.out.println("Integer <=> version: ");
            String measurementType = scanner.nextLine();

            FlightInstrument flightInstrument = new HardwareInstrument(code, value, measurementType);
            flightService.add(flightInstrument);
        } else {
            System.out.println("Alegere care nu e BMI sau BP");
        }
    }

    private void ex4() {
//        List<FlightInstrument> flightInstrumentList = new ArrayList<>();
//
//        System.out.println("Introduceti o valoare tip INTEGER");
//
//        Scanner scanner = new Scanner(System.in);
//        String readLine = scanner.nextLine();
//
//        int value = Integer.parseInt(readLine);
//
//        for(FlightInstrument flightInstrument: flightService.getAllEntities()){
//            System.out.println(flightInstrument.getPrice());
//            if (flightInstrument.getPrice() < value)
//                flightInstrumentList.add(flightInstrument);
//        }
//
//            // TODO SORTAREA DUPA CODE
//        flightInstrumentList.sort(new Comparator<FlightInstrument>() {
//            @Override
//            public int compare(FlightInstrument o1, FlightInstrument o2) {
//                return o1.getCode().compareTo(o2.getCode());
//            }
//        });
//
//        for (FlightInstrument flightInstrument: flightInstrumentList)
//            System.out.println(flightInstrument);
        System.out.println("Introduceti o valoare tip DOUBLE");

        Scanner scanner = new Scanner(System.in);
        String readLine = scanner.nextLine();

        double Price = Double.parseDouble(readLine);

        flightService.afisareObiecteSortateDupaCod(Price);
    }

    public void Ex5() {
        flightService.Ex5();
    }

    private void showOptions() {
        System.out.println("1. Ex 3");
        System.out.println("2. Ex 2");
        System.out.println("3. Ex 4");
        System.out.println("4. Ex 5");
    }

    public void ShowUserInterface() {
        addFirst4Entities();
        int choice;

        while (true) {
            System.out.println("Choose between those options:");
            showOptions();

            Scanner scanner = new Scanner(System.in);
            String readLine = scanner.nextLine();
            choice = Integer.parseInt(readLine);

            switch (choice) {
                case 1:
                    getAllEntities();
                    break;
                case 2:
                    addEntity();
                    break;
                case 3:
                    ex4();
                    break;
                case 4:
                    Ex5();
                    break;
            }
        }
    }
}
