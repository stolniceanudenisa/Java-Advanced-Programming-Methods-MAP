package Service;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import Domain.CardioMachine;
import Domain.StrenghtMachine;
import Domain.TrainingMachine;
import Repository.Repository;

public class Service {

    Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public void add(TrainingMachine trainingMachine) {
        repository.add(trainingMachine);
    }

    public List<TrainingMachine> getAllEntities() {
        return repository.getAllEntities();
    }

    public TrainingMachine[] showMachinesCheaperThanAndResitanceMotion(int price1, int number2) {
        return repository.getAllEntities().stream()
                .filter(trainingMachine -> {
                    if (trainingMachine instanceof CardioMachine) {
                        CardioMachine cardioMachine = (CardioMachine) trainingMachine;
                        return cardioMachine.getPrice() < price1 && cardioMachine.getResistance_level() > number2;
                    } else if (trainingMachine instanceof StrenghtMachine) {
                        StrenghtMachine strenghtMachine = (StrenghtMachine) trainingMachine;
                        return strenghtMachine.getPrice() < price1 && strenghtMachine.getMotion_level() > number2;
                    }
                    return false;
                })
                .sorted(Comparator.comparingInt(TrainingMachine::getSerialNumber).reversed())
                .toArray(TrainingMachine[]::new);
    }

    public Collection<CardioMachine> getAllCardioMachines() {
        return repository.getAllEntities().stream()
                .filter(trainingMachine -> trainingMachine instanceof CardioMachine)
                .map(trainingMachine -> (CardioMachine) trainingMachine)
                .toList();
    }

    public Collection<StrenghtMachine> getAllStrenghtMachines() {
        return repository.getAllEntities().stream()
                .filter(trainingMachine -> trainingMachine instanceof StrenghtMachine)
                .map(trainingMachine -> (StrenghtMachine) trainingMachine)
                .toList();
    }

    public void savetotext() {
        List<TrainingMachine> entitati = repository.getAllEntities();

        List<TrainingMachine> entitatiFiltrateSiSortate = entitati.stream()
                .filter(TrainingMachine::isMaintenance)
                .sorted((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()))
                .collect(Collectors.toList());

        for (TrainingMachine trainingMachine : entitatiFiltrateSiSortate)
            System.out.println(trainingMachine);

        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream("C:\\Users\\40766\\OneDrive\\Desktop\\Fitness_test2\\settings.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileName = properties.getProperty("nume_fisier");
        String locatie_fileName = properties.getProperty("locatie_fisier");

        writeInFile(fileName, entitatiFiltrateSiSortate);
    }


    private void writeInFile(String fileName, List<TrainingMachine> entitatiFiltrateSiSortate) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (TrainingMachine trainingMachine : entitatiFiltrateSiSortate) {
                String linie = trainingMachine.toString();
                bw.write(linie);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}