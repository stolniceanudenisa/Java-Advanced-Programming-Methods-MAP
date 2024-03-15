package Ui;

import Domain.BMI;
import Domain.BP;
import Domain.Glicemie;
import Domain.HealthData;
import Service.BMIService;
import Service.BPService;
import Service.GlicemieService;
import exceptions.DuplicateEntityException;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Menu {
    BMIService bmiService;
    BPService bpService;

    GlicemieService glicemieService;

    public Menu(BMIService bmiService, BPService bpService, GlicemieService glicemieService) {
        this.bmiService = bmiService;
        this.bpService = bpService;
        this.glicemieService = glicemieService;
    }
    public void add6Entities() throws DuplicateEntityException {
        bmiService.addBMI("2020-01-01", 21);
        bmiService.addBMI("2020-02-02", 40);

        bpService.addBP("2020-01-01", 120, 70);
        bpService.addBP("2020-02-02", 150, 100);


        glicemieService.addGlicemie("2020-01-01", 70);
        glicemieService.addGlicemie("1980-02-02", 125);
    }
    public void printMenu() {
        System.out.println();
        System.out.println("1. Add Health data");
        System.out.println("2. Is person healthy");
        System.out.println("3. Save to file");
        System.out.println("4. Is person healthy MONTH");
        System.out.println("5. Save to file newer than date");
        System.out.println("10. Print all");
        System.out.println("0. Exit");
        System.out.println();
        System.out.println("Introduceti comanda: ");
    }

    public void run() throws DuplicateEntityException {
        Scanner scan = new Scanner(System.in);
        add6Entities();
        label:
        while (true) {
            printMenu();
            String cmd = scan.nextLine();
            switch (cmd) {
                case "1":
                    addHealth();
                    break;
                case "2":
                    personHealthy();
                    break;
                case "3":
                    saveToFile324();
                    break;
                case "4":
                    personHealthyInMonth();
                    break;
                case "5":
                    saveToFileNewerThanDate();
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

    private void saveToFileNewerThanDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter date");
        String date = scanner.nextLine();

        BMI[] bmis = bmiService.saveToText2(date);
        BP[] bps = bpService.saveToText2(date);


        for (BMI bmi : bmis) {
            System.out.println(bmi.toString());
        }

        for (BP bp : bps) {
            System.out.println(bp.toString());
        }

        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream("C:\\Users\\40766\\OneDrive\\Desktop\\Health324_test3\\settings.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileName = properties.getProperty("nume_fisier");
        String locatie_fileName = properties.getProperty("locatie_fisier");
        writeInFile2(fileName, List.of(bmis), List.of(bps) );

    }

    private void writeInFile2(String fileName, List<BMI> bmis, List<BP> bps) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("health3.txt")))
        {
            for (BMI bmi : bmis) {
                String linie = bmi.toString();
                bw.write(linie);
                bw.newLine();
            }
            for (BP bp : bps) {
                String linie = bp.toString();
                bw.write(linie);
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void personHealthyInMonth() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter month");
        int month = scanner.nextInt();
        bpService.checkHealthy(month);
        bmiService.checkHealthy(month);
        System.out.println(bpService.checkHealthy(month));
        System.out.println(bmiService.checkHealthy(month));

    }


    private void handlePrintAll() {
        System.out.println();
        System.out.println("BMI:");
        for (var bmi : bmiService.getAll()) {
            System.out.println(bmi.toString());
        }
        System.out.println();
        System.out.println("BP:");
        for (var bp : bpService.getAll()) {
            System.out.println(bp);
        }
        System.out.println();
        System.out.println("Glicemie:");
        for (var glicemie : glicemieService.getAll()) {
            System.out.println(glicemie);
        }
        System.out.println();
    }

    private void saveToFile324() {
        BMI[] bmis = bmiService.saveToText();
        BP[] bps = bpService.saveToText();
        Glicemie[] glicemies = glicemieService.saveToText();

        for (BMI bmi : bmis) {
            System.out.println(bmi.toString());
        }

        for (BP bp : bps) {
            System.out.println(bp.toString());
        }

        for (Glicemie glicemie : glicemies) {
            System.out.println(glicemie.toString());
        }

//        List<HealthData> allHealthData = new ArrayList<>();
//
//        Collections.addAll(allHealthData, bmis);
//        Collections.addAll(allHealthData, bps);
//        Collections.addAll(allHealthData, glicemies);
//        Collections.sort(allHealthData, (data1, data2) -> data2.getDate().compareTo(data1.getDate()));
        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream("C:\\Users\\40766\\OneDrive\\Desktop\\Health324_test3\\settings.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileName = properties.getProperty("nume_fisier");
        String locatie_fileName = properties.getProperty("locatie_fisier");
        //writeInFile(fileName, List.of(bmis), List.of(bps), List.of(glicemies));
        writeInFile(fileName, List.of(bmis), List.of(bps), List.of(glicemies));
    }

    private void writeInFile(String fileName, List<BMI> entitatBMI, List<BP> entitatiBP, List<Glicemie> entitatiGlicemie) {
        List<HealthData> allHealthData = new ArrayList<>();
        allHealthData.addAll(entitatBMI);
        allHealthData.addAll(entitatiBP);
        allHealthData.addAll(entitatiGlicemie);

        // Sort allHealthData by date
        Collections.sort(allHealthData, Comparator.comparing(HealthData::getDate));

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("health.txt")))
        {
//            for (BMI bmi : entitatBMI) {
//                String linie = bmi.toString();
//                bw.write(linie);
//                bw.newLine();
//            }
//            for (BP bp : entitatiBP) {
//                String linie = bp.toString();
//                bw.write(linie);
//                bw.newLine();
//            }
//            for (Glicemie glicemie : entitatiGlicemie) {
//                String linie = glicemie.toString();
//                bw.write(linie);
//                bw.newLine();
//            }
            for (HealthData data : allHealthData) {
                bw.write(data.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void personHealthy() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<String> monthsToCheck = List.of("2020-01-01", "2020-02-02");
        checkHealthForMonths(monthsToCheck, bmiService, bpService, glicemieService, dateFormatter);
    }

    private void checkHealthForMonths(List<String> monthsToCheck, BMIService bmiService, BPService bpService,
                                      GlicemieService glicemieService, DateTimeFormatter dateFormatter) {
        List<String> healthyMonths = new ArrayList<>();
        List<String> unhealthyMonths = new ArrayList<>();

        for (String date : monthsToCheck) {
            checkHealthForMonth(date, bmiService, bpService, glicemieService, dateFormatter, healthyMonths, unhealthyMonths);
        }

        for (String month : monthsToCheck) {
            String status = healthyMonths.contains(month) ? "healthy" : "not healthy";
            System.out.println("The person from month " + month.substring(0, 7) + " is " + status);
        }
    }


    private void checkHealthForMonth(String date, BMIService bmiService, BPService bpService,
                                     GlicemieService glicemieService, DateTimeFormatter dateFormatter,
                                     List<String> healthyMonths, List<String> unhealthyMonths) {
        LocalDate currentDate = LocalDate.parse(date, dateFormatter);
        boolean healthy = true;

        for (var bmi : bmiService.getAll()) {
            LocalDate bmiDate = LocalDate.parse(bmi.getDate(), dateFormatter);
            if (!bmi.isNormalValue() || !isSameMonth(currentDate, bmiDate)) {
                healthy = false;
                break;
            }
        }

        for (var bp : bpService.getAll()) {
            LocalDate bpDate = LocalDate.parse(bp.getDate(), dateFormatter);
            if (!bp.isNormalValue() || !isSameMonth(currentDate, bpDate)) {
                healthy = false;
                break;
            }
        }

        for (var glicemie : glicemieService.getAll()) {
            LocalDate glicemieDate = LocalDate.parse(glicemie.getDate(), dateFormatter);
            if (!glicemie.isNormalValue() || !isSameMonth(currentDate, glicemieDate)) {
                healthy = false;
                break;
            }
        }

        if (healthy) {
            healthyMonths.add(currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM")));
        } else {
            unhealthyMonths.add(currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM")));
        }
    }

    private boolean isSameMonth(LocalDate date1, LocalDate date2) {
        return date1.getYear() == date2.getYear() && date1.getMonth() == date2.getMonth();
    }
    private void addHealth() {
        System.out.println("Ce tip? BMI sau BP? (bmi/bp)");
        Scanner scan = new Scanner(System.in);
        String cmd = scan.nextLine();

        switch (cmd) {
            case "bmi":
                addBMI();
                break;
            case "bp":
                addBP();
                break;
            default:
                System.out.println("\u001B[31mCOMANDA INVALIDA!\u001B[0m");
                break;
        }
    }

    private void addBP() {
        Scanner scan = new Scanner(System.in);
        System.out.println("DATA: ");
        String data = scan.nextLine();

        System.out.println("Systolic: ");
        Integer sist = scan.nextInt();

        System.out.println("Diastolic: ");
        Integer diastolic = scan.nextInt();
        try {
            bpService.addBP(data, sist, diastolic);
            System.out.println("\u001B[32mSenzor adaugat!\u001B[0m");
        } catch (Exception e) {
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
        }
    }

    private void addBMI() {
        Scanner scan = new Scanner(System.in);
        System.out.println("DATA: ");
        String data = scan.nextLine();

        System.out.println("Value: ");
        Float value = scan.nextFloat();
        try {
            bmiService.addBMI(data, value);
            System.out.println("\u001B[32mSenzor adaugat!\u001B[0m");
        } catch (Exception e) {
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
        }
    }
}