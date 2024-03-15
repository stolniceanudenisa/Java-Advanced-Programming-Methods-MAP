package Repository;

import Domain.HealthData;

import java.util.List;

public interface IRepository<T extends HealthData> {
    void addEntity(T entitate);

    List<T> getAllEntities();
}
