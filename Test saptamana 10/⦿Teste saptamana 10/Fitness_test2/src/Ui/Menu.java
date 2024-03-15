package Ui;

import Domain.CardioMachine;
import Domain.StrenghtMachine;
import Domain.TrainingMachine;
import Service.Service;

import java.util.Scanner;

public class Menu {

    Service service;

    public Menu(Service service) {
        this.service = service;
    }

    public void printMenu() {
        System.out.println('\n');
        System.out.println("MENU");
        System.out.println("1. Add Machine");
        System.out.println("2. Get all machines");
        System.out.println("3. Masini mai ieftine decat un pret dat si cu rezistenta mai mare decat un numar dat");
        System.out.println("4. Save to file");
        System.out.println("0. Exit");
        System.out.println("Choose an option:");
    }

    public void addFirst4Entities() {

        CardioMachine cardioMachine1 = new CardioMachine(1111, false, 5);
        CardioMachine cardioMachine2 = new CardioMachine(2222, true, 40);
        StrenghtMachine strenghtMachine1 = new StrenghtMachine(8888, false, 4,"arms");
        StrenghtMachine strenghtMachine2 = new StrenghtMachine(999, true, 40,"core");

        service.add(cardioMachine1);
        service.add(cardioMachine2);
        service.add(strenghtMachine1);
        service.add(strenghtMachine2);

    }

    public void run() {
        Scanner scan = new Scanner(System.in);  addFirst4Entities();
        label:
        while (true) {

            printMenu();

            String cmd = scan.nextLine();
            switch (cmd) {
                case "1":
                    addMachine();
                    break;
                case "2":
                    printMachines();
                    break;
                case "3":
                    showMachinesCheaperThanAndResitance();
                    break;

                case "4":
                    saveToText();
                    break;



                case "0":
                    break label;
                default:
                    System.out.println("\u001B[31mCOMANDA INVALIDA!\u001B[0m");
                    break;
            }
        }
    }

    //salvam machines care au mententata true, sortate descrescator dupa pret
    private void saveToText() {
        service.savetotext();
    }

    private void showMachinesCheaperThanAndResitance() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Price 1: ");
        int price1 = scan.nextInt();
        System.out.println("Number 2: ");
        int number2 = scan.nextInt();

        TrainingMachine[] result = service.showMachinesCheaperThanAndResitanceMotion(price1, number2);

        System.out.println("Filtered and sorted machines:");
        for (TrainingMachine machine : result) {
            System.out.println(machine.toString());
        }
    }
    private void printMachines() {
        for (TrainingMachine trainingMachine : service.getAllEntities())
            System.out.println(trainingMachine.toString());
    }

    private void addMachine() {
        System.out.println("Cardio Machine sau Strenght Machine? (c/s)");
        Scanner scan = new Scanner(System.in);
        String cmd = scan.nextLine();
        switch (cmd) {
            case "c":
                addCardioMachine();
                break;
            case "s":
                addStrenghtMachine();
                break;
            default:
                System.out.println("\u001B[31mCOMANDA INVALIDA!\u001B[0m");
                break;
        }
    }

    private void addStrenghtMachine() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Serial Number: ");
        int serialNumber = scan.nextInt();
        scan.nextLine();

        System.out.println("Maintenance: ");
        boolean maintenance = scan.nextBoolean();
        scan.nextLine();

        System.out.println("Muscle Group: ");
        String muscleGroup = scan.nextLine();

        System.out.println("Resistance Level: ");
        int resistanceLevel = scan.nextInt();
        StrenghtMachine strenghtMachine = new StrenghtMachine(serialNumber, maintenance, resistanceLevel, muscleGroup);
        service.add(strenghtMachine);
    }

    private void addCardioMachine() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Serial Number: ");
        int serialNumber = scan.nextInt();
        System.out.println("Maintenance: ");
        boolean maintenance = scan.nextBoolean();
        System.out.println("Resistance Level: ");
        int resistanceLevel = scan.nextInt();

        CardioMachine cardioMachine = new CardioMachine(serialNumber, maintenance, resistanceLevel);
        service.add(cardioMachine);
    }

}