package Repository;

import Domain.TrainingMachine;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    List<TrainingMachine> trainingMachineList = new ArrayList<>();

    public void add(TrainingMachine trainingMachine)
    {
        trainingMachineList.add(trainingMachine);
    }

    public List<TrainingMachine> getAllEntities() {
        return trainingMachineList;
    }

}