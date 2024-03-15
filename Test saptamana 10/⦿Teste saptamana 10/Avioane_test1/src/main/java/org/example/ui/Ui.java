package org.example.ui;

import org.example.domain.HardwareInstrument;
import org.example.domain.SoftwareInstrument;
import org.example.service.HardwareInstrumentService;
import org.example.service.SoftwareInstrumentService;

import java.util.*;


public class Ui {

    SoftwareInstrumentService softwareInstrumentService;
    HardwareInstrumentService hardwareInstrumentService;

    public Ui(SoftwareInstrumentService softwareInstrumentService, HardwareInstrumentService hardwareInstrumentService) {
        this.softwareInstrumentService = softwareInstrumentService;
        this.hardwareInstrumentService = hardwareInstrumentService;
    }

    public void printMenu() {
        System.out.println('\n');
        System.out.println("MENU");
        System.out.println("1. Add Software Instrument");
        System.out.println("2. Add Hardware Instrument");
        System.out.println("3. Show all instruments cheaper than a given value");
        System.out.println("4. Show all instruments cheaper than a given value sorted");
        System.out.println("5. Add Software Instrument with maintenance");


        System.out.println("6. Update Software Instrument");
        System.out.println("7. Delete Hardware Instrument");

        System.out.println("10. Show all software instruments");
        System.out.println("11. Show all hardware instruments");
        System.out.println("12. Show both all software and hardware instruments");
        System.out.println("0. Exit");
        System.out.println("Choose an option:");
    }

    public void run() {
        Scanner scan = new Scanner(System.in);
        label:
        while (true) {
            printMenu();

            String cmd = scan.nextLine();
            switch (cmd) {
                case "1":
                    addSoftwareInstrument();
                    break;
                case "2":
                    addHardwareInstrument();
                    break;
                case "3":
                    afisareInstrumenteMaiIeftineDecat();
                    break;
                case "4":
                    afisareInstrumenteMaiIeftineDecatSortat(); // NU MERGE SORTARA
                    break;
                    // DE FACUT SI FUNCTIE DE LE BAGA PE TOATE GRAMADA SI SORTEAZA DUPA COD
                case "5":
                    addSoftwareInstrumentMent();
                    break;

                case "10":
                    printSoftwareInstruments();
                    break;
                case "11":
                    printHardwareInstruments();
                    break;
                case "12":
                    printSoftwareHardwareInstruments();
                    break;
                case "0":
                    break label;
                default:
                    System.out.println("\u001B[31mCOMANDA INVALIDA!\u001B[0m");
                    break;
            }
        }
    }

    private void addSoftwareInstrumentMent() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Cod: ");
        String cod = scan.nextLine();
        System.out.println("Maintenance: ");
        boolean maintenance = scan.nextBoolean();
        System.out.println("Versiune software: ");
        int versiune_software = scan.nextInt();
        try {
            softwareInstrumentService.addSoftwareInstrumentMentenanta(cod, maintenance, versiune_software);
            System.out.println("\u001B[32mInstrument adaugat!\u001B[0m");
        } catch (Exception e) {
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
        }

    }

    private void afisareInstrumenteMaiIeftineDecat() {
        //afisare toate elemente zbor mai ieftine decat o valoare citita la tastatura, ordonate dupa cod crescator
        Scanner scan = new Scanner(System.in);
        System.out.println("Introduceti valoarea: ");
        double valoare = scan.nextDouble();
        System.out.println();
        System.out.println("Software Instruments:");
        System.out.println();
        Collection<SoftwareInstrument> softwareInstruments = softwareInstrumentService.getAll();
        for (SoftwareInstrument softwareInstrument : softwareInstruments) {
            if (softwareInstrument.getPrice() < valoare)
                System.out.println(softwareInstrument.toString());
        }
        System.out.println();
        Collection<HardwareInstrument> hardwareInstruments = hardwareInstrumentService.getAll();
        System.out.println("Hardware Instruments:");
        System.out.println();
        for (HardwareInstrument hardwareInstrument : hardwareInstruments) {
            if (hardwareInstrument.getPrice() < valoare)
                System.out.println(hardwareInstrument.toString());
        }
    }



    private void afisareInstrumenteMaiIeftineDecatSortat() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Introduceti valoarea: ");
        double valoare = scan.nextDouble();
        System.out.println();

        System.out.println("Software Instruments:");
        Collection<SoftwareInstrument> softwareInstruments = softwareInstrumentService.getAll();
        List<SoftwareInstrument> softwareList = new ArrayList<>(softwareInstruments);

        // Filtrare și sortare după cod pentru softwareInstruments
        softwareList.stream()
                .filter(softwareInstrument -> softwareInstrument.getPrice() < valoare)
                .sorted(Comparator.comparing(SoftwareInstrument::getCode))
                .forEach(softwareInstrument -> System.out.println(softwareInstrument.toString()));

        System.out.println();

        System.out.println("Hardware Instruments:");
        Collection<HardwareInstrument> hardwareInstruments = hardwareInstrumentService.getAll();
        List<HardwareInstrument> hardwareList = new ArrayList<>(hardwareInstruments);

        // Filtrare și sortare după cod pentru hardwareInstruments
        hardwareList.stream()
                .filter(hardwareInstrument -> hardwareInstrument.getPrice() < valoare)
                .sorted(Comparator.comparing(HardwareInstrument::getCode))
                .forEach(hardwareInstrument -> System.out.println(hardwareInstrument.toString()));
    }


    private void printSoftwareHardwareInstruments() {
        System.out.println("All Instruments:");
        System.out.println();
        System.out.println("Hardware Instruments:");
        System.out.println();
        Collection<HardwareInstrument> hardwareInstruments = hardwareInstrumentService.getAll();
        for (HardwareInstrument hardwareInstrument : hardwareInstruments) {
            System.out.println(hardwareInstrument.toString());
        }
        System.out.println();
        System.out.println("Software Instruments:");
        System.out.println();
        Collection<SoftwareInstrument> softwareInstruments = softwareInstrumentService.getAll();
        for (SoftwareInstrument softwareInstrument : softwareInstruments) {
            System.out.println(softwareInstrument.toString());
        }
    }

    private void printHardwareInstruments() {
        System.out.println("Hardware Instruments:");
        Collection<HardwareInstrument> hardwareInstruments = hardwareInstrumentService.getAll();
        for (HardwareInstrument hardwareInstrument : hardwareInstruments) {
            System.out.println(hardwareInstrument.toString());
        }
    }

    private void printSoftwareInstruments() {
        System.out.println("Software Instruments:");
        Collection<SoftwareInstrument> softwareInstruments = softwareInstrumentService.getAll();
        for (SoftwareInstrument softwareInstrument : softwareInstruments) {
            System.out.println(softwareInstrument.toString());
        }
    }

    private void addHardwareInstrument() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Cod: ");
        String cod = scan.nextLine();
        System.out.println("Tip de masuratoare: ");
        String tip_de_masuratoare = scan.nextLine();
        System.out.println("Maintenance: ");
        boolean maintenance = scan.nextBoolean();

        try {
            hardwareInstrumentService.addHardwareInstrument(cod, maintenance, tip_de_masuratoare);
            System.out.println("\u001B[32mInstrument adaugat!\u001B[0m");
        } catch (Exception e) {
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
        }

    }

    private void addSoftwareInstrument() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Cod: ");
        String cod = scan.nextLine();
        System.out.println("Maintenance: ");
        boolean maintenance = scan.nextBoolean();
        System.out.println("Versiune software: ");
        int versiune_software = scan.nextInt();
        try {
            softwareInstrumentService.addSoftwareInstrument(cod, maintenance, versiune_software);
            System.out.println("\u001B[32mInstrument adaugat!\u001B[0m");
        } catch (Exception e) {
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
        }

    }


}