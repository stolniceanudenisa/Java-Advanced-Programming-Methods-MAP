package org.example.ui;

import org.example.domain.Student;
import org.example.service.StudentService;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Console {
    static StudentService service = StudentService.getInstance();

    public static List<Student> list = new ArrayList<>();
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
        System.out.println("1. Afisare studenti");
        System.out.println("2. Cautare student");
        System.out.println("3. Filtrare grupa 222");
        System.out.println("4. Filtrare grupa 222 si media >5");
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
                case "2" -> cautareStudent();
                case "3" -> filtrareGrupa();
                case "4" -> filtrareGrupaMedie();
               // case "5" -> sortA();
               // case "6" -> sortA();
               // case "7" -> sortA();
               case "x" -> System.exit(0);
            }
        }
    }

    private void afisare() {
        list = service.getAll();
        list.forEach(System.out::println);
    }

    private void cautareStudent(){
        String student = "Pop Ion";
        String[] arr = student.split(" ");
        String nume = arr[0];
        String prenume = arr[1];
        for(Student s: service.getAll()) {
            if(Objects.equals(nume, s.getNume()) && Objects.equals(prenume, s.getPrenume()))
                System.out.println(s);
        }

    }

    private void filtrareGrupa() {
        String grupa = "222";
        List<Student> lista = list.stream()
                .filter(x -> Objects.equals(x.getGrupa(), grupa))
                .collect(Collectors.toList());
        if(lista.isEmpty()){
            System.out.println("Nu exista!");
        }
        else{
            lista.forEach(System.out::println);
        }
    }


    private void filtrareGrupaMedie() {
        String grupa = "222";
        List<Student> lista = list.stream()
                .filter(x -> Objects.equals(x.getGrupa(), grupa))
                .filter(y -> y.getMedia() > 5)
                .collect(Collectors.toList());
        if(lista.isEmpty()){
            System.out.println("Nu exista!");
        }
        else{
            lista.forEach(System.out::println);
        }
    }

    private void sortA() {
        List<Student> lista = list.stream()
                .sorted(Comparator.comparing(Student::getNume).thenComparing(Student::getPrenume))
                .collect(Collectors.toList());
        lista.forEach(student -> {
            System.out.println(student.getId() + " " +student.getNume() + " " + student.getPrenume());
        });



    }

    private void sortB() {
        List<Student> lista = list.stream()
                .sorted(Comparator.comparing(Student::getGrupa))
                .collect(Collectors.toList());
        lista.forEach(student -> {
            System.out.println(student.getNume() + " " + student.getPrenume() + " " + student.getGrupa());
        });

    }

    private void sortC() {
        List<Student> lista = list.stream()
                .sorted(Comparator.comparing(Student::getMedia).reversed())
                .collect(Collectors.toList());
        lista.forEach(student -> {
            System.out.println(student.getId() + " " + student.getMedia());
        });

    }




}
