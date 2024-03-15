package UserInterface;

import Repository.IRepository;
import Repository.Repository;
import Service.IService;

import Domain.*;
import Service.Service;

import java.util.Objects;
import java.util.Scanner;

public class UserInterface {
    IService<BMI> bmiService;
    IService<BP> bpService;

    public UserInterface(IService<BMI> bmiService, IService<BP> bpService) {
        this.bmiService = bmiService;
        this.bpService = bpService;
    }

    void addFirst4Entities() {

        BMI bmi1 = new BMI("10-10-2020", 19.0F);
        BMI bmi2 = new BMI("12-11-2020", 30.0F);

        BP bp1 = new BP("10-10-2020", 101, 121);
        BP bp2 = new BP("12-11-2020", 99, 120);

        bmiService.addEntity(bmi1);
        bmiService.addEntity(bmi2);

        bpService.addEntity(bp1);
        bpService.addEntity(bp2);

    }


    private void addEntity() {
        System.out.println("Introduceti BMI sau BP");

        Scanner scanner = new Scanner(System.in);
        String readLine = scanner.nextLine();

        String BMI_BP = readLine;

        if (Objects.equals(BMI_BP, "BMI")) {
            System.out.println("Suntem pe BMI");

            System.out.println("Data: ");
            String data = scanner.nextLine();
            System.out.println("Valuare BMI: ");
            Float value = Float.parseFloat(scanner.nextLine());

            BMI bmi = new BMI(data, value);
            bmiService.addEntity(bmi);

        } else {
            System.out.println("Suntem pe BP");

            System.out.println("Data: ");
            String data = scanner.nextLine();
            System.out.println("Valuare systolic: ");
            int systolicValue = Integer.parseInt(scanner.nextLine());

            System.out.println("Valuare diastolic: ");
            int diastolicValue = Integer.parseInt(scanner.nextLine());

            BP bp = new BP(data, systolicValue, diastolicValue);
            bpService.addEntity(bp);
        }

    }

    private void personIsHealthy() {
        // FIXME Pentru o luna daca tastam "1" sau pentru ultimele 2 luni daca tastam orice alt nr din intervalul [2,12]

        System.out.println("Introduceti numarul unei luni 1-12:");
        Scanner scanner = new Scanner(System.in);
        String readLine = scanner.nextLine();

        int nrLuna = Integer.parseInt(readLine);

        int nrZile = 0;

        float BMI_Value = 0F;
        int BP_systolicValue = 0;
        int BP_diastolicValue = 0;

        for (BMI bmi : bmiService.getAllEntities()) {
            // Luam luna din obiectul BMI
            String[] dataParts = bmi.getDate().split("-");

            if (Integer.parseInt(dataParts[1]) == nrLuna || Integer.parseInt(dataParts[1]) == nrLuna - 1) {
                BMI_Value += bmi.getValue();
                nrZile++;
            }


        }

        System.out.println("BP entities");
        for (BP bp : bpService.getAllEntities()) {
            String[] dataParts = bp.getDate().split("-");

            if (Integer.parseInt(dataParts[1]) == nrLuna || Integer.parseInt(dataParts[1]) == nrLuna - 1) {
                BP_systolicValue += bp.systolicValue;
                BP_diastolicValue += bp.diastolicValue;
            }
        }

        if (nrZile != 0) {
            BMI_Value /= nrZile;//bmiService.getAllEntities().size();
            BP_systolicValue /= nrZile;//bpService.getAllEntities().size();
            BP_diastolicValue /= nrZile;//bpService.getAllEntities().size();

            if (BMI_Value >= 18.5 && BMI_Value <= 25 &&
                    (BP_systolicValue >= 100 && BP_systolicValue <= 130 &&
                            BP_diastolicValue >= 60 && BP_diastolicValue <= 80)) {
                System.out.println("Valori NORMALE ale analizelor");
            } else {
                System.out.println("Valori ANORMALE");
            }
        } else {
            System.out.println("Nu avem analize facute in aceasta luna peste acest pacient");
        }
    }


    private void getAllEntities() {
        System.out.println("BMI entities");
        for (BMI bmi : bmiService.getAllEntities())
            System.out.println(bmi.toString());

        System.out.println("BP entities");
        for (BP bp : bpService.getAllEntities())
            System.out.println(bp.toString());
    }

    private void showOptions() {
        System.out.println("1.Adaugare entitate: BMI sau BP");
        System.out.println("2.Afisare entitati");
        System.out.println("3.Persoana sanatoasa ? ");

    }

    public void ShowUserInterface() {
//        addFirst4Entities();
        int choice;

        while (true) {
            System.out.println("Choose between those options:");
            showOptions();

            Scanner scanner = new Scanner(System.in);
            String readLine = scanner.nextLine();
            choice = Integer.parseInt(readLine);

            switch (choice) {
                case 1:
                    addEntity();
                    break;
                case 2:
                    getAllEntities();
                    break;
                case 3:
                    personIsHealthy();
                    break;
            }
        }
    }
}
