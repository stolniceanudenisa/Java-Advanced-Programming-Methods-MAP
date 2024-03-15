package org.example.ui;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Console {





    private static Console instance = null;

    private Console() {
    }


    public static Console getInstance() {
        if (instance == null) {
            instance = new Console();
        }
        list = service.getAll();
        return instance;
    }


    private void printMenu() {
        System.out.println("\n");
        System.out.println("1. ");
        System.out.println("2. ");
        System.out.println("3. ");
        System.out.println("4. ");
        System.out.println("5. Sortare a");
        System.out.println("6. Sortare b");
        System.out.println("7. Sortare c");
        System.out.println("x. EXIT");
        System.out.println("Select your choice: ");
    }

    public void menu() {
        AtomicReference<String> choice = new AtomicReference<>("0");
        while (!Objects.equals(choice.get(), "x")) {
            printMenu();
            Scanner scanner = new Scanner(System.in);
            choice.set(scanner.nextLine());
            System.out.println("\n");
            switch (choice.get()) {
                //case "1" -> afisare();
               // case "2" -> ;
               // case "3" -> ;
               // case "4" -> ;
               // case "5" -> sortA();
               // case "6" -> sortA();
               // case "7" -> sortA();
               case "x" -> System.exit(0);
            }
        }
    }

    private void afisare() {
        // list = service.getAll();
        list.forEach(System.out::println);
    }

    private void sortA() {


    }

    private void sortB() {


    }

    private void sortC() {

    }




}
