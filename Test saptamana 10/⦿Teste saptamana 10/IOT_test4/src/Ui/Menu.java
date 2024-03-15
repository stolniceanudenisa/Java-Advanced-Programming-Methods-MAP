package Ui;

import Domain.Settings;
import Domain.SmokeSensor;
import Domain.TemperatureSensor;
import Service.SmokeService;
import Service.TemperatureService;
import exceptions.DuplicateEntityException;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

public class Menu {

    SmokeService smokeService;
    TemperatureService temperatureService;

    public Menu(SmokeService smokeService, TemperatureService temperatureService) {
        this.smokeService = smokeService;
        this.temperatureService = temperatureService;
    }

    public void add4Entities() throws DuplicateEntityException {
        smokeService.addSmokeSensor("mihai", 12.0, 1);
        smokeService.addSmokeSensor("andreea", 9, 2);
        smokeService.addSmokeSensor("raul", 21, 3);
        smokeService.addSmokeSensor("bogdan", 2, 4);

        temperatureService.addTemperatureSensor("ana", 1.0, 1);
        temperatureService.addTemperatureSensor("matei", 7, 2);
        temperatureService.addTemperatureSensor("mihai", 3.0, 3);
        temperatureService.addTemperatureSensor("roxana", 7, 4);

    }
    public void printMenu() {
        System.out.println();
        System.out.println("1. Add sensor");
        System.out.println("2. Add smoke sensor");
        System.out.println("3. Add temperature sensor");
        System.out.println("4. Print All Sensors Cheaper Than Value Sorted by producer");
        System.out.println("5. Save to file");
        System.out.println("6. Delete sensor");
        System.out.println("7. Show all from iot.txt");
        System.out.println("10. Print all sensors");
        System.out.println("11. Show all from txt Settings");
        System.out.println("0. Exit");
        System.out.println();
        System.out.println("Introduceti comanda: ");
    }


    public void run() throws DuplicateEntityException {
            Scanner scan = new Scanner(System.in);
           add4Entities();
            label:
            while (true) {
                printMenu();
                String cmd = scan.nextLine();
                switch (cmd) {
                    case "1":
                        handleAddSensor();
                        break;
                    case "2":
                        addSmoke();
                        break;
                    case "3":
                        addTemperature();
                        break;
                    case "4":
                        handlePrintAllSensorsCheaperThanValue();
                        break;
                    case "5":
                        saveToText();
                        break;
                    case "6":
                        deleteSensor();
                        break;
                    case "7":
                        handlePrintAllSensorsFromTxt();
                        break;
                    case "10":
                        handlePrintAllSensors();
                        break;
                    case "11":
                        showAllFromTxt();
                        break;
                    case "0":
                        break label;
                    default:
                        System.out.println("\u001B[31mCOMANDA INVALIDA!\u001B[0m");
                        break;
                }
            }
    }

    private void showAllFromTxt() {
        Settings settings = Settings.getInstance();
        String fileName = settings.getFileName();
        String location = settings.getLocation();

        //try (Scanner scanner = new Scanner(new FileInputStream(location + "\\" + fileName))) {
        try (Scanner scanner = new Scanner(new FileInputStream("C:\\Users\\40766\\OneDrive\\Desktop\\IOT_test4\\settings.properties"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



        printAllSensorsFromTxt("iot.txt");
    }

    private void handlePrintAllSensorsFromTxt() {
        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream("C:\\Users\\40766\\OneDrive\\Desktop\\IOT_test4\\settings.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileName = properties.getProperty("nume_fisier");
        String locatie_fileName = properties.getProperty("locatie_fisier");

        printAllSensorsFromTxt(fileName);

    }

    private void printAllSensorsFromTxt(String fileName) {

        try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
            while (scanner.hasNextLine()) {
                String linie = scanner.nextLine();
                System.out.println(linie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteSensor() {
        System.out.println("Delete a temperature sensor or smoke sensor?  (t/s)");
        Scanner scan = new Scanner(System.in);
        String cmd = scan.nextLine();
        switch (cmd) {
            case "t":
                Scanner scan2 = new Scanner(System.in);
                System.out.println("Producer: ");
                String producer = scan2.nextLine();
                deleteTemperature(producer);

                break;
            case "s":
                Scanner scan3 = new Scanner(System.in);
                System.out.println("Producer: ");
                String producer2 = scan3.nextLine();
                deleteSmoke(producer2);
                break;
            default:
                System.out.println("\u001B[31mCOMANDA INVALIDA!\u001B[0m");
                break;
        }
    }

    private void deleteSmoke(String producer) {
        smokeService.deleteSmokeSensor(producer);
    }

    private void deleteTemperature(String producer) {
        temperatureService.deleteTemperatureSensor(producer);
    }


    private void saveToText() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Producer: ");
        String producer = scan.nextLine();

        SmokeSensor[] cheaperSmokeSensors = smokeService.saveToText(producer);
        TemperatureSensor[] cheaperTemperatureSensors = temperatureService.saveToText(producer);

        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream("C:\\Users\\40766\\OneDrive\\Desktop\\IOT_test4\\settings.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileName = properties.getProperty("nume_fisier");
        String locatie_fileName = properties.getProperty("locatie_fisier");
        writeInFile(fileName, List.of(cheaperSmokeSensors), List.of(cheaperTemperatureSensors));
    }

    private void writeInFile(String fileName, List<SmokeSensor> entitatiSmoke, List<TemperatureSensor> entitatiTemperature) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName)))
        {
            for (SmokeSensor smokeSensor : entitatiSmoke) {
                String linie = smokeSensor.toString();
                bw.write(linie);
                bw.newLine();
            }
            for (TemperatureSensor temperatureSensor : entitatiTemperature) {
                String linie = temperatureSensor.toString();
                bw.write(linie);
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handlePrintAllSensorsCheaperThanValue() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Price: ");
        int price = scan.nextInt();

        SmokeSensor[] cheaperSmokeSensors = smokeService.getCheaperSensors(price);
        TemperatureSensor[] cheaperTemperatureSensors = temperatureService.getCheaperSensors(price);

        System.out.println();
        System.out.println("Smoke Sensors Cheaper than " + price + ":");
        System.out.println();
        for (SmokeSensor smokeSensor : cheaperSmokeSensors) {
            System.out.println(smokeSensor.getProducer() + " - Price: " + smokeSensor.getPrice());
            System.out.println();


            System.out.println("Temperature Sensors Cheaper than " + price + ":");
            System.out.println();
            for (TemperatureSensor temperatureSensor : cheaperTemperatureSensors) {
                System.out.println(temperatureSensor.getProducer() + " - Price: " + temperatureSensor.getPrice());
            }

            System.out.println();
        }
    }

    private void handleAddSensor() {
        System.out.println("Temperature sensor or smoke sensor?  (t/s)");
        Scanner scan = new Scanner(System.in);
        String cmd = scan.nextLine();

        switch (cmd) {
            case "t":
                addTemperature();
                break;
            case "s":
                addSmoke();
                break;
            default:
                System.out.println("\u001B[31mCOMANDA INVALIDA!\u001B[0m");
                break;
        }
    }
    private void addSmoke() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Producer: ");
        String producer = scan.nextLine();
        System.out.println("Last recording: ");
        double recording = scan.nextDouble();
        System.out.println("Diameter: ");
        int diameter = scan.nextInt();
        try {
            smokeService.addSmokeSensor(producer, recording, diameter);
            System.out.println("\u001B[32mSenzor adaugat!\u001B[0m");
        } catch (Exception e) {
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
        }

    }

    private void addTemperature() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Producer: ");
        String producer = scan.nextLine();
        System.out.println("Last recording: ");
        double recording = scan.nextDouble();
        System.out.println("Length: ");
        int length = scan.nextInt();
        try {
            temperatureService.addTemperatureSensor(producer, recording, length);
            System.out.println("\u001B[32mSenzor adaugat!\u001B[0m");
        } catch (Exception e) {
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
        }

    }


    private void handlePrintAllSensors() {
//        System.out.println("Smoke sensors:");
//        smokeService.getAll().forEach(System.out::println);
//        System.out.println();
//        System.out.println("Temperature sensors:");
//        System.out.println();
//        temperatureService.getAll().forEach(System.out::println);
//        System.out.println();

        //sau
        System.out.println();
        System.out.println("Smoke sensors:");
        for (SmokeSensor smokeSensor : smokeService.getAll())
            System.out.println(smokeSensor.toString());
        System.out.println();
        System.out.println("Temperature sensors:");
        for (TemperatureSensor temperatureSensor : temperatureService.getAll())
            System.out.println(temperatureSensor.toString());
        System.out.println();
    }
}