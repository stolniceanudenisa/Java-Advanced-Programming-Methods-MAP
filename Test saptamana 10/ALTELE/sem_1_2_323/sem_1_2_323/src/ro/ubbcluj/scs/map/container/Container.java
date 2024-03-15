package ro.ubbcluj.scs.map.container;

import ro.ubbcluj.scs.map.domain.Task;

public interface Container {
    Task remove();
    void add(Task task);
    int size();
    boolean isEmpty();
}
