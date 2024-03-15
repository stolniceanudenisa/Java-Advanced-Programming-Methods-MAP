package UI;

import Domain.Pacient;
import Domain.Programare;
import Repository.RepoException;
import Service.Service;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Consola {

    private final Service<Pacient> serPAcient;
    private final Service<Programare> serProgramare;
    private static final Scanner read = new Scanner(System.in);

    public Consola(Service<Pacient> p1 , Service<Programare> p2){
        this.serPAcient = p1;
        this.serProgramare = p2;

    }


/*
    public void instante_initiale() {
        try {
            Random random = new Random();
            String[] Nume = {"Marian", "Ionescu", "Vintila", "Popa", "Stefanescu"};
            String[] Prenume = {"Eusebiu", "Eustache", "Razvan", "Rares", "Rozeta"};
            for (int i = 0; i < 5; i++) {
                String nume = Nume[random.nextInt(Nume.length)];
                String prenume = Prenume[random.nextInt(Prenume.length)];
                int varsta = random.nextInt(89);
                serPAcient.add(new Pacient(i + 1, nume, prenume, varsta));
            }
        }catch (RepoException e ){
            System.out.println();
        }
    }
*/



    public void adaugaPacient(){
            try {
                System.out.println("dati id-ul, numele, prenumele si varsta Pacientului.\n");
                int ID = read.nextInt();
                String nume = read.next();
                String prenume = read.next();
                int varsta = read.nextInt();
                Pacient p = new Pacient(ID,nume,prenume,varsta);
                serPAcient.add(p);
            }
            catch(RepoException e){
                System.out.println("\033[31m"+e.getMessage());
            }catch (InputMismatchException e){
                System.out.println("\033[31m"+"eroare, ati  introdus un tip de data gresit.");
                read.next();
            }
    }


    public void updatePacient(){
        try{
            System.out.println("dati id-ul Pacientului pe care doriti sa-l modificati\n");
            int ID = read.nextInt();
            System.out.println("dati noile date ale pacientului (nume prenume varsta)\n");
            String nume = read.next();
            String prenume = read.next();
            int varsta = read.nextInt();
            Pacient p = new Pacient(ID,nume,prenume,varsta);
            serPAcient.update(p);

        } catch(RepoException e){
            System.out.println("\033[31m"+e.getMessage());
        }catch (InputMismatchException e){
            System.out.println("\033[31m"+"eroare, ati  introdus un tip de data gresit.");
            read.next();
        }
    }

    public void deletePacient(){
        try{
            System.out.println("dati id-ul Pacientului pe care doriti sa-l stergeti\n");
            int ID = read.nextInt();
            serPAcient.delete(ID);
        } catch(RepoException e){
            System.out.println("\033[31m"+e.getMessage());
        }catch (InputMismatchException e){
            System.out.println("\033[31m"+"eroare, ati  introdus un tip de data gresit.");
            read.next();
        }
    }


    public void afiseazaPacienti(){
        try {
            if(serPAcient.getRep().getSize() == 0){
                System.out.println("lista de pacienti este goala. ");
            }
            else {
                for (int i = 0; i < serPAcient.getRep().getSize(); i++) {
                    System.out.println(serPAcient.getRep().get(i));
                }
            }
        }
        catch(RepoException e){
            System.out.println("\033[31m"+e.getMessage());
        }
    }

    public void adaugaProgramare(){
        try {
            System.out.println("dati id-ul programarii, id-ul pacientului, data, ora si scopul programarii.\n");
            int IDprogra = read.nextInt();
            int IDpacient = read.nextInt();
            String data = read.next();
            String ora = read.next();
            read.useDelimiter("\\n");
            String scopul = read.next();
            Pacient pacient = serPAcient.getRep().getBYid(IDpacient);
            Programare p = new Programare(IDprogra,pacient,scopul,data,ora);
            serProgramare.add(p);
        }
        catch(RepoException e){
            System.out.println("\033[31m"+e.getMessage());
        }catch (InputMismatchException e){
            System.out.println("\033[31m"+"eroare, ati  introdus un tip de data gresit.");
            read.next();
        }
    }

    public void deleteProgramare(){
        try{
            System.out.println("dati id-ul Programarii pe care doriti sa o stergeti\n");
            int ID = read.nextInt();
            serProgramare.delete(ID);
        } catch(RepoException e){
            System.out.println("\033[31m"+e.getMessage());
        }catch (InputMismatchException e){
            System.out.println("\033[31m"+"eroare, ati  introdus un tip de data gresit.");
            read.next();
        }
    }

    public void updateProgramare(){
        try{
            System.out.println("dati id-ul Programarii pe care doriti sa o modificati\n");
            int ID = read.nextInt();
            System.out.println("dati noile date ale programarii (data, ora, scop)\n");
            String data = read.next();
            String ora = read.next();
            read.useDelimiter("\\n");
            String scopul = read.next();
            Pacient pacient = serProgramare.getRep().getBYid(ID).getPacient();
            Programare p = new Programare(ID,pacient,scopul,data,ora);
            serProgramare.update(p);

        } catch(RepoException e){
            System.out.println("\033[31m"+e.getMessage());
        }catch (InputMismatchException e){
            System.out.println("\033[31m"+"eroare, ati  introdus un tip de data gresit.");
            read.next();
        }
    }

    public void afiseazaProgramari(){
        try {
            if(serProgramare.getRep().getSize() ==0){
                System.out.println("lista de programari este goala. ");
            }
            else {
                for (int i = 0; i < serProgramare.getRep().getSize(); i++) {
                    System.out.println(serProgramare.getRep().get(i));
                }
            }
        }
        catch(RepoException e){
            System.out.println("\033[31m"+e.getMessage());
        }
    }





    public void  printMeniu(){
        System.out.println("""
                \u001B[0m
                ----------------------------
                cu ce meniu doriti sa lucrati?""");
        System.out.println("1. Pacenti.");
        System.out.println("2. Programare.");
        System.out.println("x. iesire din aplicatie.");
        System.out.println("----------------------------");

    }

    public void printMeniuPacient(){
        System.out.println("\u001B[0m"+"\n----------------------------");
        System.out.println("1. adauga un pacient.");
        System.out.println("2. modificarea unui pacient.");
        System.out.println("3. stergerea unui pacient.");
        System.out.println("a. afiseaza lista de pacienti.");
        System.out.println("x. iesire din meniul de pacienti.");
        System.out.println("----------------------------");
    }


    public void meniuPacient(){
        String b;
        labelPacient:
        while(true){
            printMeniuPacient();
            System.out.println("dati optiunea: ");
            b = read.next();
            switch (b){
                case "1":
                    adaugaPacient();
                    break;
                case "2":
                    updatePacient();
                    break;
                case "3":
                    deletePacient();
                    break;
                case "a":
                    afiseazaPacienti();
                    break;
                case "x":
                    break labelPacient;
                case null:
                default:
                    System.out.println("optiune gresita, reincercati. ");
                    break;
            }
        }
    }

    public void printMeniuProgramare(){
        System.out.println("\u001B[0m"+"\n----------------------------");
        System.out.println("1. adauga o programare.");
        System.out.println("2. modificarea unei programari.");
        System.out.println("3. stergerea unei programari.");
        System.out.println("a. afiseaza lista de programari.");
        System.out.println("x. iesire din meniul de programari.");
        System.out.println("----------------------------");
    }


    public void meniuProgramare(){
        String b;
        labelProgramare:
        while(true){
            read.useDelimiter("[\\n ]");
            printMeniuProgramare();
            System.out.println("dati optiunea: ");
            b = read.next();
            switch (b){
                case "1":
                    adaugaProgramare();
                    break;
                case "2":
                    updateProgramare();
                    break;
                case "3":
                    deleteProgramare();
                    break;
                case "a":
                    afiseazaProgramari();
                    break;
                case "x":
                    break labelProgramare;
                case null:
                default:
                    System.out.println("optiune gresita, reincercati. ");
                    break;
            }
        }
    }

    public void meniu(){
        String a;
        //instante_initiale();
        label:
        while (true){
            printMeniu();
            System.out.println("dati optiunea: ");
            a = read.next();
            switch (a) {
                case "1":
                    meniuPacient();
                    break;
                case "2":
                    meniuProgramare();
                    break;
                case "x":
                    break label;
                case null:
                default:
                    System.out.println("optiune gresita, reincercati. ");
                    break;
            }

        }

    }




}
