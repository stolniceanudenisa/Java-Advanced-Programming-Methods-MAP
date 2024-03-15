package repository;

import domain.*;
import java.util.ArrayList;

public class Repository<T extends HealthData> {
    private final ArrayList<T> data;

    public Repository() {
        this.data = new ArrayList<>();
    }

    public ArrayList<T> getAll() {
        return data;
    }

    public void add(T entity) {
        data.add(entity);
    }
    public int size() {
        return data.size();
    }

}
