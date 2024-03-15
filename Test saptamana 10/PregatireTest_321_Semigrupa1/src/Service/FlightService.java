package Service;

import Domain.FlightInstrument;
import Repository.FlightRepository;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class FlightService {
    FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public void add(FlightInstrument flightInstrument) {
        flightRepository.add(flightInstrument);
    }

    public List<FlightInstrument> getAllEntities() {
        return flightRepository.getAllEntities();
    }


    public void afisareObiecteSortateDupaCod(double Price) {
        List<FlightInstrument> entitati = flightRepository.getAllEntities();

        System.out.println("Dupa filtrare si sortare");

        entitati.stream().filter(p1 -> p1.getPrice() < Price).sorted((p1, p2) -> (p1.getCode()).compareTo(p2.getCode()))
                .forEach(flightInstrument -> System.out.println(flightInstrument));
    }

    public void Ex5() {
        List<FlightInstrument> entitati = flightRepository.getAllEntities();

        List<FlightInstrument> entitatiFiltrateSiSortate = entitati.stream()
                .filter(p1 -> p1.isMaintenance())
                .sorted((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()))
                .toList();

        for (FlightInstrument flightInstrument : entitatiFiltrateSiSortate)
            System.out.println(flightInstrument);

        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream("C:\\Users\\tonyc\\Downloads\\Anul2\\MAP\\Laborator\\PregatireTest_321_Semigrupa1\\settings.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileName = properties.getProperty("nume_fisier");
        String locatie_fileName = properties.getProperty("locatie_fisier");

        writeInFile(fileName, entitatiFiltrateSiSortate);
    }

    private void writeInFile(String fileName, List<FlightInstrument> entitati) {
        // FIXME try with resources
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (FlightInstrument flightInstrument : entitati) {
                //FIXME NU i ok asa, trebuie fac cu gett-eri
                String linie = flightInstrument.toString();

                bw.write(linie);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
