package Service;

import Domain.BMI;
import Repository.IRepository;
import exceptions.DuplicateEntityException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BMIService {
    IRepository<BMI> repository;

    public BMIService(IRepository<BMI> repository) {
        this.repository = repository;
    }

    public void addBMI(String date, float value ) throws DuplicateEntityException {

        BMI bmi = new BMI(date, value);
        repository.add(bmi);
    }

    public BMI findByDate(String date)
    {
        return repository.findByDate(date);
    }

    public Iterable<BMI> getAll() {
        return repository.getAll();
    }

    public BMI[] saveToText() {
        List<BMI> entitati = (List<BMI>) repository.getAll();
        List<BMI> entitatiFiltrateSiSortate = entitati.stream()
                .filter(bmi -> bmi.getValue() > 25 || bmi.getValue() < 18.5)
                .collect(Collectors.toList());
        return entitatiFiltrateSiSortate.toArray(new BMI[entitatiFiltrateSiSortate.size()]);
    }


    public BMI[] saveToText2(String date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate currentDate = LocalDate.parse(date, dateFormatter);

        List<BMI> entitati = (List<BMI>) repository.getAll();
        List<BMI> entitatiFiltrateSiSortate = entitati.stream()
                .filter(data -> LocalDate.parse(data.getDate(), dateFormatter).isAfter(currentDate))
                .collect(Collectors.toList());
        return entitatiFiltrateSiSortate.toArray(new BMI[entitatiFiltrateSiSortate.size()]);
    }

    public boolean checkHealthy(int month) {
        if(month<=12 && month>=1) {
            boolean bmiHealthy = true;
            boolean notExistent = true;
            List<BMI> bmis = (List<BMI>) repository.getAll();
            for (BMI bmi : bmis){
                String[] dateParts = bmi.getDate().split("-");
                if(Integer.parseInt(dateParts[1]) == month ){ //|| Integer.parseInt(dateParts[1]) == month - 1
                    notExistent = false;
                    if(bmi.isNormalValue() == false)
                        bmiHealthy = false;
                }
            }
            if(notExistent)
                return false;
            if (bmiHealthy == true)
                return true;
            return false;
        }
        else return false;
    }


}