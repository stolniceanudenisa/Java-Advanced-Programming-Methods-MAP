package Ui;

import Domain.FlightInstrument;
import Domain.HardwareInstrument;
import Domain.SoftwareInstrument;
import Service.HardwareService;
import Service.SoftwareService;
import exceptions.DuplicateEntityException;

import java.io.*;
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
        System.out.println("1. -");
        System.out.println("2. Afisare instrumente mai ieftine decat un pret dat sort cresc dupa cod");
        System.out.println("3. Delete instrument");
        System.out.println("4. Save to file all instruments ord cresc pret");
        System.out.println("5. Save to file bin");
        System.out.println("6. Read from file bin");
        System.out.println("10. Print all");
        System.out.println("0. Exit");
        System.out.println();
        System.out.println("Introduceti comanda: ");
    }

    public void run() throws DuplicateEntityException {
        Scanner scan = new Scanner(System.in);
        //addEntities();
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
                    deleteInstrument();
                    break;
                case "4":
                    saveToFile();
                    break;
                case "5":
                    saveToFileBin();
                    break;
                case "6":
                    readFromFileBin();
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

    private void saveToFileBin() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("instruments.bin"))) {
            List<HardwareInstrument> hardwareList = hardwareService.getAllHardware();
            List<SoftwareInstrument> softwareList = softwareService.getAllSoftware();

            List<Object> entitiesToSave = new ArrayList<>();
            entitiesToSave.addAll(hardwareList);
            entitiesToSave.addAll(softwareList);

            outputStream.writeObject(entitiesToSave);

            System.out.println("Entities saved to instruments.bin");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DuplicateEntityException e) {
            throw new RuntimeException(e);
        }
    }

public void readFromFileBin() {
    try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("instruments.bin"))) {
        ArrayList<Object> entities = (ArrayList<Object>) objectInputStream.readObject();

        System.out.println();
        for (Object entity : entities) {
            if (entity instanceof HardwareInstrument) {
                HardwareInstrument hardwareInstrument = (HardwareInstrument) entity;

                System.out.println( hardwareInstrument.toString());

            } else if (entity instanceof SoftwareInstrument) {

                SoftwareInstrument softwareInstrument = (SoftwareInstrument) entity;
                System.out.println( softwareInstrument.toString());

            }
        }
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }
}

    private void deleteInstrument() {
        System.out.println("Ce tip? Hardware sau software? (h/s)");
        Scanner scan = new Scanner(System.in);
        String cmd = scan.nextLine();

        switch (cmd) {
            case "h":
                deleteHardware();
                break;
            case "s":
                deleteSoftware();
                break;
            default:
                System.out.println("\u001B[31mCOMANDA INVALIDA!\u001B[0m");
                break;
        }

    }

    private void deleteSoftware() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Code: ");
        String code = scan.nextLine();

        try {
            softwareService.remove(code);

        } catch (Exception e) {
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
        }
    }

    private void deleteHardware() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Code: ");
        String code = scan.nextLine();

        try {
            hardwareService.remove(code);

        } catch (Exception e) {
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
        }

    }

    private void handlePrintAll() {
        for (var h : hardwareService.getAll()) {
            System.out.println(h);
        }
        for (var s : softwareService.getAll()) {
            System.out.println(s.toString());
        }
    }

    private void saveToFile() {

        SoftwareInstrument[] softwareInstruments = softwareService.getAllSoftware2();
        for (var s : softwareInstruments) {
            System.out.println(s);
        }

        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream("C:\\Users\\40766\\OneDrive\\Desktop\\test\\settings.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileName = properties.getProperty("nume_fisier");
        String locatie_fileName = properties.getProperty("locatie_fisier");
        writeInFile(fileName, List.of(softwareInstruments));
    }

    private void writeInFile(String fileName,List<SoftwareInstrument> softwareInstruments) {
        List<FlightInstrument> instruments = new ArrayList<>();
        instruments.addAll(softwareInstruments);

        Collections.sort(instruments, Comparator.comparing(FlightInstrument::getPrice));

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("software.txt")))
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

        System.out.println();
        System.out.println("Hardware instruments:");
        for (var h : hardwareInstruments) {
            System.out.println(h);
        }
        System.out.println();
        System.out.println("Software instruments:");
        for (var s : softwareInstruments) {
            System.out.println(s);
        }
        System.out.println();
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
            saveToFileBin();
            System.out.println("\u001B[32mHardware adaugat!\u001B[0m");
        } catch (Exception e) {
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
        }
    }

    private void addSoftware() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Code: ");
        String code = scan.nextLine();

        System.out.println("Maintenance? (true/false)");
        Boolean maintenance = scan.nextBoolean();

        System.out.println("Version: ");
        Integer version = scan.nextInt();

        try {
            softwareService.add(code, maintenance, version);
            saveToFileBin();
            System.out.println("\u001B[32mSoftware adaugat!\u001B[0m");
        } catch (Exception e) {
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
        }

    }


}