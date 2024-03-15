package Service;

import Domain.SmokeSensor;
import exceptions.DuplicateEntityException;
import repository.IRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SmokeService {

    IRepository<SmokeSensor> smokeRepository;

    public SmokeService(IRepository<SmokeSensor> smokeRepository) {
        this.smokeRepository = smokeRepository;
    }

    public void addSmokeSensor(String producer, double last_recording, int length) throws DuplicateEntityException {
        SmokeSensor smokeSensor = new SmokeSensor(producer, last_recording, length);
        smokeRepository.add(smokeSensor);
    }

    public SmokeSensor findByProducer(String producer) {
        return smokeRepository.findByCode(producer);
    }

    public Iterable<SmokeSensor> getAll() {
        return smokeRepository.getAll();
    }

    public SmokeSensor[] getCheaperSensors(double price) {
        List<SmokeSensor> cheaperSensors = smokeRepository.getAll().stream()
                .filter(smokeSensor -> smokeSensor.getPrice() < price)
                .filter(SmokeSensor::sendAlert)
                .sorted(Comparator.comparing(SmokeSensor::getProducer))
                .collect(Collectors.toList());
       // .toList();

        return cheaperSensors.toArray(new SmokeSensor[0]);
    }


    public SmokeSensor[] saveToText(String producer) {
        List<SmokeSensor> entitati = (List<SmokeSensor>) smokeRepository.getAll();
        List<SmokeSensor> entitatiFiltrateSiSortate = entitati.stream()
                .filter(smokeSensor -> smokeSensor.getProducer().equals(producer))
                .sorted((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()))
                .collect(Collectors.toList());

        return entitatiFiltrateSiSortate.toArray(new SmokeSensor[0]);
//
//        for (SmokeSensor smokeSensor : entitatiFiltrateSiSortate)
//            System.out.println(smokeSensor);
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

    public void deleteSmokeSensor(String producer) {
        smokeRepository.remove(producer);
    }


//    private void writeInFile(String fileName, List<SmokeSensor> entitatiFiltrateSiSortate) {
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
//            for (SmokeSensor smokeSensor : entitatiFiltrateSiSortate) {
//                String linie = smokeSensor.toString();
//                bw.write(linie);
//                bw.newLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}