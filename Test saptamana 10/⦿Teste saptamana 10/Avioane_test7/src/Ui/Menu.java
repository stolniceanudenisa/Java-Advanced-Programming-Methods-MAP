package Ui;

import Domain.FlightInstrument;
import Domain.HardwareInstrument;
import Domain.SoftwareInstrument;
import Service.HardwareService;
import Service.SoftwareService;
import exceptions.DuplicateEntityException;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Menu {

    SoftwareService softwareService;
    HardwareService hardwareService;

    public Menu(SoftwareService softwareService, HardwareService hardwareService) {
        this.softwareService = softwareService;
        this.hardwareService = hardwareService;
    }

    public void addEntities () throws DuplicateEntityException {
        hardwareService.add("116", true, "altitudine");
        hardwareService.add("112", false, "directie");
        hardwareService.add("115", true, "stare_motor");
        hardwareService.add("113", false, "directie");

        softwareService.add("226", true, 8);
        softwareService.add("220", false, 5);
        softwareService.add("223", true, 13);
        softwareService.add("222", false, 54);
    }
    public void printMenu() {
        System.out.println();
        System.out.println("1. Add instrument");
        System.out.println("2. Afisare instrumente mai ieftine decat un pret dat sort cresc dupa cod");
        System.out.println("3. Save to file instrumente care au mentenanta");
        System.out.println("10. Print all");
        System.out.println("0. Exit");
        System.out.println();
        System.out.println("Introduceti comanda: ");
    }

    public void run() throws DuplicateEntityException {
        Scanner scan = new Scanner(System.in);
        addEntities();
        label:
        while (true) {
            printMenu();
            String cmd = scan.nextLine();
            switch (cmd) {
                case "1":
                    addInstrument();
                    break;
                case "2":
                    afisInstrumenteMaiIeftine();
                    break;
                case "3":
                    saveToFile();
                    break;
                case "10":
                    handlePrintAll();
                    break;
                case "0":
                    break label;
                default:
                    System.out.println("\u001B[31mCOMANDA INVALIDA!\u001B[0m");
                    break;
            }
        }
    }

    private void handlePrintAll() {
        System.out.println();
        System.out.println("Software:");
        for (var s : softwareService.getAll()) {
            System.out.println(s.toString());
        }
        System.out.println();
        System.out.println("Hardware:");
        for (var h : hardwareService.getAll()) {
            System.out.println(h);
        }
        System.out.println();
    }

    private void saveToFile() {

        HardwareInstrument[] hardwareInstruments = hardwareService.getHardwareMaintenance();
        SoftwareInstrument[] softwareInstruments = softwareService.getSoftwareMaintenance();


        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream("C:\\Users\\40766\\OneDrive\\Desktop\\Avioane_test7\\settings.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileName = properties.getProperty("nume_fisier");
        String locatie_fileName = properties.getProperty("locatie_fisier");
        writeInFile(fileName, List.of(hardwareInstruments), List.of(softwareInstruments));
    }

    private void writeInFile(String fileName, List<HardwareInstrument> hardwareInstruments, List<SoftwareInstrument> softwareInstruments) {
        List<FlightInstrument> instruments = new ArrayList<>();
        instruments.addAll(hardwareInstruments);
        instruments.addAll(softwareInstruments);

        Collections.sort(instruments, Comparator.comparing(FlightInstrument::getPrice));

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("avioane.txt")))
        {
            for (FlightInstrument data : instruments) {
                bw.write(data.toString());
                bw.newLine();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void afisInstrumenteMaiIeftine() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Pret: ");
        double pret = scan.nextDouble();

        HardwareInstrument[] hardwareInstruments = hardwareService.getHardwareInstrumentsCheaperThan(pret);
        SoftwareInstrument[] softwareInstruments = softwareService.getSoftwareInstrumentsCheaperThan(pret);

        System.out.println("Hardware instruments:");
        for (var h : hardwareInstruments) {
            System.out.println(h);
        }

        System.out.println("Software instruments:");
        for (var s : softwareInstruments) {
            System.out.println(s);
        }


    }

    private void addInstrument() {
        System.out.println("Ce tip? Software sau Hardware? (s/h)");
        Scanner scan = new Scanner(System.in);
        String cmd = scan.nextLine();

        switch (cmd) {
            case "s":
                addSoftware();
                break;
            case "h":
                addHardware();
                break;
            default:
                System.out.println("\u001B[31mCOMANDA INVALIDA!\u001B[0m");
                break;
        }
    }

    private void addHardware() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Code year: ");
        String code = scan.nextLine();
        scan.nextLine();

        System.out.println("Maintenance? (true/false)");
        Boolean maintenance = scan.nextBoolean();

        System.out.println("Measurement type: ");
        String measurementType = scan.nextLine();
        scan.nextLine();

        try {
            hardwareService.add(code, maintenance, measurementType);
            System.out.println("\u001B[32mHardware adaugat!\u001B[0m");
        } catch (Exception e) {
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
        }
    }

    private void addSoftware() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Code year: ");
        String code = scan.nextLine();

        System.out.println("Maintenance? (true/false)");
        Boolean maintenance = scan.nextBoolean();

        System.out.println("Version: ");
        Integer version = scan.nextInt();

        try {
            softwareService.add(code, maintenance, version);
            System.out.println("\u001B[32mSoftware adaugat!\u001B[0m");
        } catch (Exception e) {
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
        }

    }


}