package org.example.ui;

import org.example.domain.Excursie;
import org.example.service.ExcursieService;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Console {

    static ExcursieService service = ExcursieService.getInstance();

    public static List<Excursie> list = new ArrayList<>();

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
        System.out.println("1. Afisare excursii");
        System.out.println("2. Cautare excursie");
        System.out.println("3. Filtrare categorie");
        System.out.println("4. Filtrare categorie si pret");
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
                case "1" -> afisare();
                case "2" -> cautareCategorie();
                case "3" -> filtrareCategorie();
                case "4" -> filtrareCategoriePret();
                case "5" -> sortA();
                case "6" -> sortB();
                case "7" -> sortC();
                case "x" -> System.exit(0);
            }
        }
    }

    private void afisare() {
        // list = service.getAll();
        list.forEach(System.out::println);
    }


    private void cautareCategorie() {
        String excursie = "Roma Colosseum";
        String[] arr = excursie.split(" ");
        String oras = arr[0];
        String atractie = arr[1];

        List<Excursie> lista = list.stream()
                .filter(x -> Objects.equals(x.getOras(), oras))
                .filter(y-> Objects.equals(y.getAtractie(), atractie))
                .collect(Collectors.toList());
        if(lista.isEmpty()){
            System.out.println("Nu exista!");
        }
        else{
            lista.forEach(System.out::println);
        }

    }

    private void filtrareCategorie() {
        String categorie = "istorie";
        List<Excursie> lista = list.stream()
                .filter(x-> Objects.equals(x.getCategorie(), categorie))
                .collect(Collectors.toList());
        if(lista.isEmpty()){
            System.out.println("Nu exista!");
        }
        else{
            lista.forEach(System.out::println);
        }
    }

    private void filtrareCategoriePret() {
        String categorie = "istorie";
        List<Excursie> lista = list.stream()
                .filter(x-> Objects.equals(x.getCategorie(), categorie))
                .filter(y-> y.getPret() > 2500)
                .collect(Collectors.toList());
        if(lista.isEmpty()){
            System.out.println("Nu exista!");
        }
        else{
            lista.forEach(System.out::println);
        }
    }


    private void sortA() {
        List<Excursie> lista = list.stream()
                .sorted(Comparator.comparing(Excursie::getOras).thenComparing(Excursie::getAtractie))
                .collect(Collectors.toList());
        lista.stream().map(excursie -> excursie.getId() + " " + excursie.getAtractie() + " " + excursie.getOras()).forEach(System.out::println);

    }

    private void sortB() {
        List<Excursie> lista = list.stream()
                .sorted(Comparator.comparing(Excursie::getCategorie))
                .collect(Collectors.toList());
        lista.stream().map(excursie -> excursie.getOras() + " " + excursie.getAtractie() + " " + excursie.getCategorie()).forEach(System.out::println);
    }

    private void sortC() {
        List<Excursie> lista = list.stream()
                .sorted(Comparator.comparing(Excursie::getPret).reversed())
                .collect(Collectors.toList());
        lista.stream().map(excursie -> excursie.getId() + " " + excursie.getPret()).forEach(System.out::println);

    }




}
