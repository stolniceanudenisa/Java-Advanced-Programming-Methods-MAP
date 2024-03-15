package Ui;

import Domain.Document;
import Domain.Manuscript;
import Domain.Presentation;
import Service.ManuscriptService;
import Service.PresentationService;
import exceptions.DuplicateEntityException;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Menu {

    ManuscriptService manuscriptService;
    PresentationService presentationService;

    public Menu(ManuscriptService manuscriptService, PresentationService presentationService) {
        this.manuscriptService = manuscriptService;
        this.presentationService = presentationService;
    }


    public void printMenu() {
        System.out.println();
        System.out.println("1. Add document");
        System.out.println("2. Afisare nu conforme si crescator dupa autor");
        System.out.println("3. Scriere in fisier text, datele obiectului, in ordine descrescatoare dupa numele autorului");
        System.out.println("10. Print all documents");
        System.out.println("0. Exit");
        System.out.println();
        System.out.println("Introduceti comanda: ");
    }


    public void run() throws DuplicateEntityException {
        Scanner scan = new Scanner(System.in);
        addEntities();
        label:
        while (true) {
            printMenu();
            String cmd = scan.nextLine();
            switch (cmd) {
                case "1":
                   addDocument();
                    break;
                case "2":
                    afisareNeconforme();
                    break;
                case "3":
                    saveToFile();
                    break;
                case "10":
                    handlePrintAllDocuments();
                    break;
                case "0":
                    break label;
                default:
                    System.out.println("\u001B[31mCOMANDA INVALIDA!\u001B[0m");
                    break;
            }
        }
    }

    private void saveToFile() {
        Manuscript[] bmis = manuscriptService.saveToText();
        Presentation[] bps = presentationService.saveToText();


        for (Manuscript bmi : bmis) {
            System.out.println(bmi.toString());
        }

        for (Presentation bp : bps) {
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
        writeInFile(fileName, List.of(bmis), List.of(bps));
    }


    private void writeInFile(String fileName, List<Manuscript> entitatBMI, List<Presentation> entitatiBP) {
        List<Document> allDocuments = new ArrayList<>();
        allDocuments.addAll(entitatBMI);
        allDocuments.addAll(entitatiBP);

        Collections.sort(allDocuments, Comparator.comparing(Document::getAuthor).reversed());

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("manuscripts.txt")))
        {
            for (Document data : allDocuments) {
                bw.write(data.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void afisareNeconforme() {
        manuscriptService.isNeconform();
        presentationService.isNeconform();

    }

    private void handlePrintAllDocuments() {
        System.out.println();
        System.out.println("Manuscripts: ");
        System.out.println();
        for (Document doc : manuscriptService.getAll())
            System.out.println(doc);
        System.out.println();
        System.out.println("Presentations: ");
        System.out.println();
        for (Presentation presentation : presentationService.getAll())
            System.out.println(presentation);
        System.out.println();

    }

    private void addDocument() {
        System.out.println("Manuscript / presentation? (m/p)");
        Scanner scan = new Scanner(System.in);
        String cmd = scan.nextLine();

        switch (cmd) {
            case "m":
                addManuscript();
                break;
            case "p":
                addPresentation();
                break;
            default:
                System.out.println("\u001B[31mCOMANDA INVALIDA!\u001B[0m");
                break;
        }

    }

    private void addPresentation() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Autor: ");
        String autor = scan.nextLine();

        System.out.println("Nr slides: ");
        int nrslides = scan.nextInt();

        scan.nextLine();
        System.out.println("Text: ");
        String text  = scan.nextLine();

        try {
            presentationService.addPresentation(autor, nrslides, text);
            System.out.println("\u001B[32mPrezentare adaugata!\u001B[0m");
        } catch (Exception e) {
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
        }
    }

    private void addManuscript() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Autor: ");
        String autor = scan.nextLine();

        System.out.println("Nr words: ");
        int nrwords = scan.nextInt();

        System.out.println("Nr pages: ");
        int nrpages  = scan.nextInt();

        try {
            manuscriptService.addManuscript(autor, nrwords, nrpages);
            System.out.println("\u001B[32mManuscript adaugat!\u001B[0m");
        } catch (Exception e) {
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
        }

    }

    private void addEntities() throws DuplicateEntityException {
        manuscriptService.addManuscript("Dostoievski", 3000, 4);
        manuscriptService.addManuscript("Tolstoi", 1000, 25);

        presentationService.addPresentation("Ana", 1000, "piersica");
        presentationService.addPresentation("Andrei", 700, "mar");
    }

}