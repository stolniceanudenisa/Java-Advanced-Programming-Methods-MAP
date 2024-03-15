package Ui;

import Domain.Block;
import Domain.Building;
import Domain.House;
import Service.BlockService;
import Service.HouseService;
import exceptions.DuplicateEntityException;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Menu {

    HouseService houseService;
    BlockService blockService;

    public Menu(HouseService houseService, BlockService blockService) {
        this.houseService = houseService;
        this.blockService = blockService;
    }

    public void addEntities () throws DuplicateEntityException {
        houseService.add(1900, true);
        houseService.add(2005, false);
        houseService.add(1934, false);
        houseService.add(1645, true);
        houseService.add(2012, true);

        blockService.add(2010, 100, 100);
        blockService.add(2002, 50, 25);
        blockService.add(1945, 70, 25);
        blockService.add(1967, 100, 100);
    }
    public void printMenu() {
        System.out.println();
        System.out.println("1. Add cladire");
        System.out.println("2. Afisare cladiri care trebuie restaurate in ord cresc a anului constructiei");
        System.out.println("3. Save to file cladiri care trebuie restaurate + cladiri care trebuie demolate ord cresc an constructie");
        System.out.println("4. Delete building");
        System.out.println("10. Print all");
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
                    addCladire();
                    break;
                case "2":
                    afisCladiriRestaurate();
                    break;
                case "3":
                    saveToFile();
                case "4":
                    deleteBuilding();
                    break;
                case "10":
                    handlePrintAll();
                    break;
                case "0":
                    break label;
                default:
                    System.out.println("\u001B[31mCOMANDA INVALIDA!\u001B[0m");
                    break;
            }
        }
    }

    private void deleteBuilding() {
        System.out.println("Ce tip? Block sau House? (b/h)");
        Scanner scan = new Scanner(System.in);
        String cmd = scan.nextLine();

        switch (cmd) {
            case "b":
                deleteBlock();
                break;
            case "h":
                deleteHouse();
                break;
            default:
                System.out.println("\u001B[31mCOMANDA INVALIDA!\u001B[0m");
                break;
        }

//        Block[] blocks = blockService.getBlocks();
//        House[] houses= houseService.getHouses();
//
//        List<Building> buildings = new ArrayList<>();
//        buildings.addAll(Arrays.asList(blocks));
//        buildings.addAll(Arrays.asList(houses));
//
//
//
//        for (int i = 0; i < buildings.size(); i++) {
//            System.out.println(i + ": " + buildings.get(i).toString());
//        }
//        System.out.println("Select the building to delete by its index:");
//        //make an object with that index
//
//
//
//        Scanner scanner = new Scanner(System.in);
//        int indexToDelete = scanner.nextInt();
//        Building buildingToDelete = buildings.get(indexToDelete);
//        System.out.println("You have selected: " + buildingToDelete.toString());
//
//        // de vazut de ce tip este cladirea
//        // de chemat din service functie de stergere pentru tipul respectiv
//
//
//
//
//
//        if (indexToDelete >= 0 && indexToDelete < buildings.size()) {
//            buildings.remove(indexToDelete);
//            System.out.println("Building deleted successfully.");
//        } else {
//            System.out.println("Invalid index. No building deleted.");
//        }

    }

    private void deleteHouse() {

        Scanner scan = new Scanner(System.in);
        System.out.println("Id: ");
        Integer id = scan.nextInt();

        try {
            houseService.remove(id);
            System.out.println("\u001B[32mHouse sters!\u001B[0m");
        } catch (Exception e) {
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
        }
    }

    private void deleteBlock() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Id: ");
        Integer id = scan.nextInt();

        try {
            blockService.remove(id);
            System.out.println("\u001B[32mBlock sters!\u001B[0m");
        } catch (Exception e) {
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
        }

    }

    private void handlePrintAll() {
        System.out.println();
        System.out.println("Blocks:");
        for (var bmi : blockService.getAll()) {
            System.out.println(bmi.toString());
        }
        System.out.println();
        System.out.println("Houses:");
        for (var bp : houseService.getAll()) {
            System.out.println(bp);
        }
        System.out.println();

    }

    private void saveToFile() {
        Block[] blocks = blockService.afisBlocuriRestaurate();
        Block[] blocks2 = blockService.saveToTextDemolate();
        House[] houses= houseService.afisCaseRestaurate();
        House[] houses2= houseService.saveToTextDemolate();

        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream("C:\\Users\\40766\\OneDrive\\Desktop\\Cladiri_test5\\settings.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileName = properties.getProperty("nume_fisier");
        String locatie_fileName = properties.getProperty("locatie_fisier");
        writeInFile(fileName, List.of(blocks), List.of(houses)); //restaurate
        writeInFile2(fileName, List.of(blocks2), List.of(houses2)); //demolate

    }

    private void writeInFile(String fileName, List<Block> blocks, List<House> houses) {
        List<Building> buildings = new ArrayList<>();
        buildings.addAll(blocks);
        buildings.addAll(houses);

        Collections.sort(buildings, Comparator.comparing(Building::getConstructionYear));

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("restaurate.txt")))
        {
            for (Building data : buildings) {
                bw.write(data.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void writeInFile2(String fileName, List<Block> blocks2, List<House> houses2) {

        List<Building> buildings = new ArrayList<>();
        buildings.addAll(blocks2);
        buildings.addAll(houses2);

        Collections.sort(buildings, Comparator.comparing(Building::getConstructionYear));
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("demolate.txt")))
        {
            for (Building data : buildings) {
                bw.write(data.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void afisCladiriRestaurate() {
        System.out.println();
        Block[] blocks = blockService.afisBlocuriRestaurate();
        for (var block : blocks) {
            System.out.println(block.toString());
        }
        System.out.println();
        House[] houses= houseService.afisCaseRestaurate();
        for (var house : houses) {
            System.out.println(house.toString());
        }
        System.out.println();

    }

    private void addCladire() {
        System.out.println("Ce tip? Block sau House? (b/h)");
        Scanner scan = new Scanner(System.in);
        String cmd = scan.nextLine();

        switch (cmd) {
            case "b":
                addBlock();
                break;
            case "h":
                addHouse();
                break;
            default:
                System.out.println("\u001B[31mCOMANDA INVALIDA!\u001B[0m");
                break;
        }

    }

    private void addHouse() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Construction year: ");
        Integer year = scan.nextInt();

        System.out.println("Is historical? (true/false)");
        Boolean isHistorical = scan.nextBoolean();

        try {
            houseService.add(year, isHistorical);
            System.out.println("\u001B[32mHouse adaugat!\u001B[0m");
        } catch (Exception e) {
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
        }
    }

    private void addBlock() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Construction year: ");
        Integer year = scan.nextInt();

        System.out.println("Total apartments: ");
        Integer totalapartments = scan.nextInt();

        System.out.println("Occupied apartments: ");
        Integer occupied = scan.nextInt();

        try {
            blockService.add(year, totalapartments, occupied);
            System.out.println("\u001B[32mBlock adaugat!\u001B[0m");
        } catch (Exception e) {
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
        }
    }


}