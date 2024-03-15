package org.example.ui;

import org.example.domain.*;
import org.example.service.*;

import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.stream.*;

public class Console {
    static TablouService service = TablouService.getInstance();

    public static List<Tablou> list = new ArrayList<>();

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
        System.out.println("1. Afisare tablou");
        System.out.println("2. Cautare tablou");
        System.out.println("3. Afisarerea tablourilor care au tematica natura");
        System.out.println("4. Afisarerea tablourilor care au tematica natura si celebritatea > 8");
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
                case "1" -> afisareTablouri();
                case "2" -> cautareTablouNuferi();
                case "3" -> filtrareNatura();
                case "4" -> filtrareNaturaCelebritate();
                case "5" -> sortA();
                case "6" -> sortB();
                case "7" -> sortC();
                case "x" -> System.exit(0);
            }
        }
    }

    private void sortC() {
        List<Tablou> lista = list.stream()
                .sorted(Comparator.comparing(Tablou::getCelebritate).reversed())
                .collect(Collectors.toList());
        lista.forEach(produs -> {
            System.out.println(produs.getId() + " " + produs.getCelebritate());
        });
    }

    /*
    private void sortB() {
        List<Tablou> lista = list.stream()
                .sorted(Comparator.comparing(Tablou::getTematica))
                .collect(Collectors.toList());
        lista.forEach(tablou -> {
            System.out.println(tablou.getTitlu() + " " + tablou.getPictor() + " " + tablou.getTematica());
        });

    }

     */


    private void sortB() {
        List<Tablou> lista = list.stream()
                .sorted(Comparator.comparing(Tablou::getTematica))
                .collect(Collectors.toList());
        lista.stream().map(tablou -> tablou.getTitlu() + " " + tablou.getPictor() + " " + tablou.getTematica()).forEach(System.out::println);

    }


    private void sortA() {
        List<Tablou> lista = list.stream()
                .sorted(Comparator.comparing(Tablou::getPictor).thenComparing(Tablou::getTitlu))
                .collect(Collectors.toList());
        lista.forEach(tablou -> {
            System.out.println(tablou.getId() + " " + tablou.getPictor() + " " + tablou.getTitlu());
        });

    }

    private void filtrareNaturaCelebritate() {
        String tematica = "natura";
        List<Tablou> lista = list.stream()
                .filter(x -> Objects.equals(x.getTematica(), tematica))
                .filter(y-> y.getCelebritate() > 8)
                .collect(Collectors.toList());
        if(lista.isEmpty()){
            System.out.println("nu exista");
        }
        else{
            lista.forEach(System.out::println);
        }
    }

    private void filtrareNatura() {
        String tematica = "natura";
        List<Tablou> lista = list.stream()
                .filter(x -> Objects.equals(x.getTematica(), tematica))
                .collect(Collectors.toList());
        if(lista.isEmpty()){
            System.out.println("nu exista");
        }
        else{
            lista.forEach(System.out::println);
        }
    }

    private void cautareTablouNuferi() {
        String tablou= "Nuferi,Claude Monet";
        String[] arr = tablou.split(",");
        String titlu = arr[0];
        String nume = arr[1];

        List<Tablou> lista = list.stream()
                .filter(x -> Objects.equals(x.getTitlu(), titlu))
                .filter(y-> Objects.equals(y.getPictor(), nume))
                .collect(Collectors.toList());
        if(lista.isEmpty()){
            System.out.println("nu exista");
        }
        else{
            lista.forEach(System.out::println);
        }

    }

    private void afisareTablouri() {
        list = service.getAll2();
        list.forEach(System.out::println);
    }
}
