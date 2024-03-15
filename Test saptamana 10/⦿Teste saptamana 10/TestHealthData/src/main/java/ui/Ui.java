package ui;

import domain.BMI;
import domain.BP;
import service.Service;

import java.util.Scanner;
import java.util.List;
public class Ui {
    private Scanner scanner;
    private Service service;

    public Ui(Service service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Show all bmi");
            System.out.println("2. Show All bp");
            System.out.println("3. add bmi ");
            System.out.println("4. add bp");
            System.out.println("5. healthy?");
            System.out.println("6. saveDataToFile");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    showAllBmi();
                    break;
                case 2:
                    showAllBp();
                    break;
                case 3:
                    addBmi();
                    break;
                case 4:
                    addBp();
                    break;
                case 5:
                    healthy();
                    break;
                case 6:
                    saveData();
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private void saveData() {
        System.out.println("Enter date");
        String date = scanner.next();
        service.saveToFile(date);
    }

    private void healthy() {
        System.out.println("Enter month");
        int month = scanner.nextInt();
        System.out.println(service.healthy(month));
    }

    private void addBmi() {
        System.out.print("Enter date: ");
        String date = scanner.next();
        System.out.print("Enter value ");
        float value = scanner.nextFloat();

        service.addBmi(date,value);
    }
    private void addBp() {
        System.out.print("Enter date: ");
        String date = scanner.next();
        System.out.print("Enter systolic value ");
        int systolic = scanner.nextInt();
        System.out.print("Enter diastolc value ");
        int diastolic = scanner.nextInt();

        service.addBp(date,systolic,diastolic);
    }

    private void showAllBp() {
        List<BP> bps = service.getAllBp();
        for (BP  bp : bps) {
            System.out.println(bp.toString());
        }
    }

    private void showAllBmi() {
        List<BMI> bmis = service.getAllBmi();
        for (BMI  bmi : bmis) {
            System.out.println(bmi.toString());
        }
    }

}
