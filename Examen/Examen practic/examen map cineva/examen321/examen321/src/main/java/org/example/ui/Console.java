package org.example.ui;

import org.example.domain.Album;
import org.example.service.Service;

import java.util.Scanner;

public class Console {

    private final Service service;

    public Console(Service service) {
        this.service = service;
    }

    void printMenu(){


        System.out.println("1. Add album");
        System.out.println("2. Delete album");
        System.out.println("3. Update album");
        System.out.println("4. Search album 'Thriller'");
        System.out.println("5. Filter rock");
        System.out.println("6. Filter rock and price higher than 40");
        System.out.println("7. Sort by artist and name");
        System.out.println("8. Sort by genre descending");
        System.out.println("9. Sort by price");
        System.out.println("10. Show all");
        System.out.println("0. Exit");

    }
    public void run() {
        Scanner scan = new Scanner(System.in);
        label:
        while (true) {
            printMenu();
            String cmd = scan.nextLine();
            switch (cmd) {
                case "1":
                    addAlbum();
                    break;
                case "2":
                    deleteAlbum();
                    break;
                case "3":
                    updateAlbum();
                    break;
                case "4":
                    searchThriller();
                    break;
                case "5":
                    filterRock();
                    break;
                case "6":
                    filterRockAndPrice();
                    break;
                case "7":
                    sortArtistName();
                    break;
                case "8":
                    sortGenre();
                    break;
                case "9":
                    sortPriceDesc();
                    break;
                case "10":
                    printAlbums();
                    break;
                case "0":
                    break label;
                default:
                    System.out.println("COMANDA INVALIDA!");
                    break;
            }
        }
    }

    private void showAll() {
        System.out.println(service.findAllAlbums());
    }

    private void searchThriller() {
        System.out.println(service.searchThriller());
    }

    private void sortGenre() {
        service.sortByGenre().forEach(s-> System.out.println(s.getNume()+" "+s.getArtist() +" "+s.getGen()));
    }

    private void sortPriceDesc() {
        service.sortByPrice().forEach(s-> System.out.println(s.getId()+" "+s.getPret()));

    }

    private void sortArtistName() {
        service.sortByArtistAndName().forEach(s-> System.out.println(s.getId()+" "+s.getNume()+" "+s.getArtist()));

    }

    private void filterRockAndPrice() {
        System.out.println(service.filterRockAndPrice());
    }

    private void filterRock() {
        System.out.println(service.filterRock());
    }

    private void updateAlbum() {
        printAlbums();
        Scanner scan = new Scanner(System.in);
        System.out.println("Id Album you want to update");
        String val = scan.nextLine();
        Long id1 = Long.parseLong(val);
        System.out.println("Nume: ");
        String nume = scan.nextLine();
        System.out.println("Artist: ");
        String artist = scan.nextLine();
        System.out.println("Gen: ");
        String gen = scan.nextLine();
        System.out.println("Pret: ");
        Double pret = scan.nextDouble();
        Album album = new Album(nume,artist,gen,pret);
        album.setId(id1);
        try{
            service.update(album);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid arguments!");
        }
    }

    private void deleteAlbum() {
        printAlbums();
        Scanner scan = new Scanner(System.in);
        System.out.println("Id: ");
        String val = scan.nextLine();
        try{
            Long id = Long.parseLong(val);
            service.delete(id);
        }
        catch (IllegalArgumentException e) {
            System.out.println("The id can not contain letters or symbols and can not be empty!!");
        }
    }
    void printAlbums() {
        for (Album u : service.findAllAlbums()) {
            System.out.println("Id: " + u.getId() + "; nume: " + u.getNume() + "; artist: " + u.getArtist() + "; gen: "
                    + u.getGen() + "; pret: " + u.getPret());
        }
    }

    private void addAlbum() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Nume: ");
        String nume = scan.nextLine();
        System.out.println("Artist: ");
        String artist = scan.nextLine();
        System.out.println("Gen: ");
        String gen = scan.nextLine();
        System.out.println("Pret: ");
        Double pret = scan.nextDouble();
        try{
            Album album = new Album(nume,artist,gen,pret);
            service.add(album);}
        catch (IllegalArgumentException e) {
                System.out.println("Invalid arguments!");
            }

    }

}
