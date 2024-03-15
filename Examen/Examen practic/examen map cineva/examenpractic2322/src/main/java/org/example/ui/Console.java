package org.example.ui;

import org.example.domain.*;
import org.example.service.*;

import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.stream.*;

public class Console {
    static ProduseService service = ProduseService.getInstance();

    public static List<Produs> list = new ArrayList<>();

    private static Console instance = null;

    private Console() {
    }

    public static Console getInstance() {
        if (instance == null) {
            instance = new Console();
        }
        list = service.getAll2();
        return instance;
    }

    private void printMenu() {
        System.out.println("\n");
        System.out.println("1. Afisare produs");
        System.out.println("2. Cautare produs");
        System.out.println("3. Afisarerea produselor care au categoria x");
        System.out.println("4. Afisarerea produselor care au categoria x si pretul < y");
        System.out.println("5. Sortare a");
        System.out.println("6. Sortare b");
        System.out.println("7. Sortare c");
        System.out.println("8. Adauga un produs");
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
                case "1" -> afisareProduse();
                case "2" -> cautareProdus();
                case "3" -> filtrareCatgorie();
                case "4" -> filtrareCatgoriePret();
                case "5" -> sortA();
                case "6" -> sortB();
                case "7" -> sortC();
                case "8" -> adaugareProdus();

                case "x" -> System.exit(0);
            }
        }
    }

    private void adaugareProdus() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Da nume: ");
        String nume = scanner.nextLine();
        System.out.println("Da categorie: ");
        String categorie = scanner.nextLine();
        System.out.println("Da descriere: ");
        String desc = scanner.nextLine();
        System.out.println("Da pret: ");
        Double pret = scanner.nextDouble();
        Produs produs = new Produs(nume,categorie,desc,pret);
        service.add(produs);
    }

    private void sortC() {
        List<Produs> lista = list.stream()
                .sorted(Comparator.comparing(Produs::getPret))
                .collect(Collectors.toList());
        lista.forEach(produs -> {
            System.out.println(produs.getId() + " " + produs.getPret());
        });
    }

    private void sortB() {
        List<Produs> lista = list.stream()
                .sorted(Comparator.comparing(Produs::getDescriere).reversed())
                .collect(Collectors.toList());
        lista.forEach(carte -> {
            System.out.println(carte.getNume() + " " + carte.getCategorie() + " " + carte.getDescriere());
        });
    }

    private void sortA() {
        List<Produs> lista = list.stream()
                .sorted(Comparator.comparing(Produs::getCategorie).thenComparing(Produs::getNume).reversed())
                .collect(Collectors.toList());
        lista.forEach(produs -> {
            System.out.println(produs.getId() + " " + produs.getNume() + " " + produs.getCategorie());
        });
    }

    private void filtrareCatgoriePret() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Da categorie: ");
        String categorie = scanner.nextLine();
        System.out.println("Da pret: ");
        Double pret = scanner.nextDouble();
        List<Produs> lista = list.stream()
                .filter(x -> Objects.equals(x.getCategorie(), categorie))
                .filter(y-> y.getPret() < pret)
                .collect(Collectors.toList());
        if(lista.isEmpty()){
            System.out.println("nu exista");
        }
        else{
            lista.forEach(System.out::println);
        }
    }

    private void filtrareCatgorie() {
        System.out.println("Da categorie: ");
        Scanner scanner = new Scanner(System.in);
        String categorie = scanner.nextLine();
        List<Produs> lista = list.stream()
                .filter(x -> Objects.equals(x.getCategorie(), categorie))
                .collect(Collectors.toList());
        if(lista.isEmpty()){
            System.out.println("nu exista");
        }
        else{
            lista.forEach(System.out::println);
        }
    }

    private void afisareProduse() {
        list.forEach(System.out::println);
    }

    private void cautareProdus() {
        Scanner scanner = new Scanner(System.in);
        String nume = scanner.nextLine();
        boolean ok = false;
        for (Produs produs : service.getAll2()) {
            if (Objects.equals(produs.getNume(), nume)) {
                System.out.println(produs);
                ok = true;
                break;
            }
        }
        if (!ok) {
            System.out.println("nu exista produsul dat");
        }
    }
}
