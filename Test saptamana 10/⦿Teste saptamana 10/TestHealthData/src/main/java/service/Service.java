package service;

import domain.BMI;
import domain.BP;
import repository.Repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Service {
    private Repository<BMI> bmiRepository;
    private Repository<BP> bpRepository;

    public Service(Repository<BMI> bmiRepository,  Repository<BP> bpRepository){
        this.bmiRepository = bmiRepository;
        this.bpRepository = bpRepository;
    }
    public void addBmi(String date, float value){
        BMI bmi = new BMI(date,value);
        bmiRepository.add(bmi);
    }

    public List<BMI> getAllBmi() {
        return bmiRepository.getAll();
    }
    public void addBp(String date, int systolicValue , int diastolicValue){
        BP bp = new BP(date,systolicValue, diastolicValue);
        bpRepository.add(bp);
    }

    public List<BP> getAllBp() {
        return bpRepository.getAll();
    }

    public boolean healthy(int month){
        if(month<=12 && month>=1) {
            boolean bmiHealthy = true;
            boolean notExistent = true;
            List<BMI> bmis = bmiRepository.getAll();
            for (BMI bmi : bmis){
                String[] dateParts = bmi.getDate().split("-");
                if(Integer.parseInt(dateParts[1]) == month || Integer.parseInt(dateParts[1]) == month - 1){
                    notExistent = false;
                    if(bmi.isNormalValue() == false)
                        bmiHealthy = false;
                }
            }
            boolean bpHealthy = true;
            List<BP> bps = bpRepository.getAll();
            for (BP bp : bps) {
                String[] dataParts = bp.getDate().split("-");
                if (Integer.parseInt(dataParts[1]) == month || Integer.parseInt(dataParts[1]) == month - 1) {
                    notExistent = false;
                    if (!bp.isNormalValue())
                        bpHealthy = false;
                }
            }
            if(notExistent)
                return false;
            if (bmiHealthy == true && bpHealthy == true)
                return true;
            return false;
        }
        else return false;
    }

public void saveToFile(String date) {
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    ArrayList<BMI> bmis = bmiRepository.getAll();
    ArrayList<BMI> newBmis = new ArrayList<>();

    LocalDate targetDate = LocalDate.parse(date, dateFormatter);

    for (BMI bmi : bmis) {
        LocalDate bmiDate = LocalDate.parse(bmi.getDate(), dateFormatter);
        if (bmiDate.isAfter(targetDate)) {
            newBmis.add(bmi);
        }
    }
    Collections.sort(newBmis, Comparator.comparing(BMI::getDate));

    ArrayList<BP> bps = bpRepository.getAll();
    ArrayList<BP> newBps = new ArrayList<>();

    for (BP bp : bps) {
        LocalDate bpDate = LocalDate.parse(bp.getDate(), dateFormatter);
        if (bpDate.isAfter(targetDate)) {
            newBps.add(bp);
        }
    }
    Collections.sort(newBps, Comparator.comparing(BP::getDate));

    try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/files/data.txt"))) {
        for (BMI bmi : newBmis) {
            writer.write(bmi.toString());
            writer.newLine();
        }

        for (BP bp : newBps) {
            writer.write(bp.toString());
            writer.newLine();
        }
    } catch (IOException e) {}
}
}
