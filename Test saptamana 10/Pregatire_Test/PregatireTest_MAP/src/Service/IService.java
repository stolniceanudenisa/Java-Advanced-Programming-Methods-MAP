package Service;

import Domain.HealthData;

import java.util.List;

public interface IService<T extends HealthData> {
    void addEntity(T entitate);

    List<T> getAllEntities();

}
