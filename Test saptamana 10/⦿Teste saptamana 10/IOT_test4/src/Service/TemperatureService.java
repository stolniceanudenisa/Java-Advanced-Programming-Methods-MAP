package Service;

import Domain.TemperatureSensor;
import exceptions.DuplicateEntityException;
import repository.IRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TemperatureService {

    IRepository<TemperatureSensor> temperatureRepository;

    public TemperatureService(IRepository<TemperatureSensor> temperatureRepository) {
        this.temperatureRepository = temperatureRepository;
    }

    public void addTemperatureSensor(String producer, double last_recording, int diameter) throws DuplicateEntityException {
        TemperatureSensor temperatureSensor = new TemperatureSensor(producer, last_recording, diameter);
        temperatureRepository.add(temperatureSensor);
    }

    public TemperatureSensor findByProducer(String producer) {
        return temperatureRepository.findByCode(producer);
    }

    public Iterable<TemperatureSensor> getAll() {
        return temperatureRepository.getAll();
    }

    public TemperatureSensor[] getCheaperSensors(double price) {
        List<TemperatureSensor> cheaperSensors = temperatureRepository.getAll().stream()
                .filter(temperatureSensor -> temperatureSensor.getPrice() < price)
                .filter(TemperatureSensor::sendAlert)
                .sorted(Comparator.comparing(TemperatureSensor::getProducer))
                .collect(Collectors.toList());

        return cheaperSensors.toArray(new TemperatureSensor[0]);
    }

    public TemperatureSensor[] saveToText(String producer) {
        List<TemperatureSensor> entitati = (List<TemperatureSensor>) temperatureRepository.getAll();
        List<TemperatureSensor> entitatiFiltrateSiSortate = entitati.stream()
                .filter(smokeSensor -> smokeSensor.getProducer().equals(producer))
                .sorted((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()))
                .collect(Collectors.toList());

        return entitatiFiltrateSiSortate.toArray(new TemperatureSensor[0]);

//        for (TemperatureSensor temperatureSensor : entitatiFiltrateSiSortate)
//            System.out.println(temperatureSensor);
//
//        Properties properties = new Properties();
//
//        try (FileInputStream fileInputStream = new FileInputStream("C:\\Users\\40766\\OneDrive\\Desktop\\IOT_test4\\settings.properties")) {
//            properties.load(fileInputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        String fileName = properties.getProperty("nume_fisier");
//        String locatie_fileName = properties.getProperty("locatie_fisier");

        //writeInFile(fileName, entitatiFiltrateSiSortate);
        //return new SmokeSensor[0];

    }

    public void deleteTemperatureSensor(String producer) {
        temperatureRepository.remove(producer);
    }

//    private void writeInFile(String fileName, List<TemperatureSensor> entitatiFiltrateSiSortate) {
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
//            for (TemperatureSensor temperatureSensor : entitatiFiltrateSiSortate) {
//                String linie = temperatureSensor.toString();
//                bw.write(linie);
//                bw.newLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}