package Repository;

import Domain.HealthData;

import java.util.ArrayList;
import java.util.List;

public class Repository<T extends HealthData> implements IRepository<T>{

    public List<T> entitati = new ArrayList<>();
    @Override
    public void addEntity(T entitate) {
        entitati.add(entitate);
    }

    @Override
    public List<T> getAllEntities() {
        return entitati;
    }
}
