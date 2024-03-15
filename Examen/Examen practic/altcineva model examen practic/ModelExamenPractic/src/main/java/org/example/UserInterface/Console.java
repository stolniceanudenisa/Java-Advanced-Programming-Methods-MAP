package org.example.UserInterface;

import org.example.Domain.Student;
import org.example.Service.ServiceStudent;

import java.util.*;

public class Console {
    private final ServiceStudent service;
    Scanner kb = new Scanner(System.in);

    public Console(ServiceStudent service) {
        this.service = service;
    }

    public void runMenu() {
        boolean ok = true;
        while (ok) {
            System.out.println("1. Citire studenți.");
            System.out.println("2. Cautare student.");
            System.out.println("3. Filtreaza studenti dupa grupa.");
            System.out.println("4. Filtreaza studenti dupa grupa si medie.");
            System.out.println("5. Sortarea listei de studenti după nume și prenume, alfabetic, crescător.");
            System.out.println("6. Sortarea listei de studenti după grupă, crescător.");
            System.out.println("7. Sortarea listei de studenti după medie, descrescător.");

            System.out.print("Give option: ");
            String option = kb.next();

            if (Objects.equals(option, "1")) {
                showAll();
            } else if (Objects.equals(option, "2")) {
                findStudent();
            } else if (Objects.equals(option, "3")) {
                filterByGroup();
            } else if (Objects.equals(option, "4")) {
                filterByGroupGrade();
            } else if (Objects.equals(option, "5")) {
                sortStudentsA();
            } else if (Objects.equals(option, "6")) {
                sortStudentsB();
            } else if (Objects.equals(option, "7")) {
                sortStudentsC();
            } else if (Objects.equals(option, "x")) {
                ok = false;
            } else {
                System.out.println("Optiune gresita! Reincercati!");
            }
        }
    }

    private void sortStudentsC() {
        try {
            List<Student> students = this.service.findAll();
            students.sort(Comparator.comparing(Student::getMedia).reversed());
            for (Student s : students) {
//                System.out.println(s);
                System.out.println(s.getId() + ". " + s.getMedia());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void sortStudentsB() {
        try {
            List<Student> students = this.service.findAll();
            students.sort(Comparator.comparing(Student::getGrupa));
            for (Student s : students) {
//                System.out.println(s);
                System.out.println(s.getNume() + " " + s.getPrenume() + " " + s.getGrupa());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void sortStudentsA() {
        try {
            List<Student> students = this.service.findAll();
            students.sort(Comparator.comparing(Student::getNume)
                    .thenComparing(Student::getPrenume));
            for (Student s : students) {
//                System.out.println(s);
                System.out.println(s.getId() + ". " + s.getNume() + " " + s.getPrenume());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void filterByGroupGrade() {
        try {
            System.out.print("Precizati grupa: ");
            String group = kb.next();

            System.out.print("Precizati media: ");
            Double grade = kb.nextDouble();

            List<Student> students = this.service.filterByGroupGrade(group, grade);

            if (students.size() > 0)
                for (Student s : students) {
                    System.out.println(s);
                }
            else System.out.println("Nu exista studenti din grupa data si cu media respectiva!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void filterByGroup() {
        try {
            System.out.print("Precizati grupa: ");
            String group = kb.next();
            List<Student> students = this.service.filterByGroup(group);
            if (students.size() > 0)
                for (Student s : students) {
                    System.out.println(s);
                }
            else System.out.println("Nu exista studenti din grupa data!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void findStudent() {
        try {
            System.out.print("Precizati numele: ");
            String nume = kb.next();

            System.out.print("Precizati prenumele: ");
            String prenume = kb.next();

            Student student = this.service.findStudent(nume, prenume);
            System.out.println(student);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void showAll() {
        for (Student s : this.service.findAll()) {
            System.out.println(s);
        }
    }
}
