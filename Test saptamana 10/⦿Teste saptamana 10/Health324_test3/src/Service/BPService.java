package Service;

import Domain.BMI;
import Domain.BP;
import Repository.IRepository;
import exceptions.DuplicateEntityException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BPService {
    IRepository<BP> repository;

    public BPService(IRepository<BP> repository) {
        this.repository = repository;
    }

    public void addBP(String date,Integer systolic, Integer diastolic ) throws DuplicateEntityException {

        BP bp = new BP(date, systolic, diastolic);
        repository.add(bp);
    }

    public BP findByDate(String date)
    {
        return repository.findByDate(date);
    }

    public Iterable<BP> getAll() {
        return repository.getAll();
    }

    public BP[] saveToText() {
        List<BP> entitati = (List<BP>) repository.getAll();
        List<BP> entitatiFiltrateSiSortate = entitati.stream()
                .filter(bp -> bp.getSystolic() > 130 || bp.getSystolic() < 100 || bp.getDiastolic() > 80 || bp.getDiastolic() < 60)
                .collect(Collectors.toList());
        return entitatiFiltrateSiSortate.toArray(new BP[entitatiFiltrateSiSortate.size()]);
    }


    public boolean checkHealthy(int month) {
        if(month<=12 && month>=1) {
            boolean bmiHealthy = true;
            boolean notExistent = true;
            List<BP> bmis = (List<BP>) repository.getAll();
            for (BP bmi : bmis){
                String[] dateParts = bmi.getDate().split("-");
                if(Integer.parseInt(dateParts[1]) == month ){//|| Integer.parseInt(dateParts[1]) == month - 1
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

    public BP[] saveToText2(String date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate currentDate = LocalDate.parse(date, dateFormatter);
        List<BP> entitati = (List<BP>) repository.getAll();
        List<BP> entitatiFiltrateSiSortate = entitati.stream()
                .filter(data -> LocalDate.parse(data.getDate(), dateFormatter).isAfter(currentDate))
                .collect(Collectors.toList());
        return entitatiFiltrateSiSortate.toArray(new BP[entitatiFiltrateSiSortate.size()]);
    }
}