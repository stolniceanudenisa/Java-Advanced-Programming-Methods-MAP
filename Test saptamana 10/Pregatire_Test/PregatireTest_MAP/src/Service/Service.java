package Service;

import Domain.HealthData;
import Repository.IRepository;

import java.util.List;

public class Service<T extends HealthData> implements IService<T> {

    private final IRepository<T> repoGeneric;

    public Service(IRepository<T> repoGeneric) {
        this.repoGeneric = repoGeneric;
    }

    @Override
    public void addEntity(T entitate) {
        repoGeneric.addEntity(entitate);
    }

    @Override
    public List<T> getAllEntities() {
        return repoGeneric.getAllEntities();
    }
}
