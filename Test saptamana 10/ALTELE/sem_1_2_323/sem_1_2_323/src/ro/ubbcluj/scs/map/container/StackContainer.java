package ro.ubbcluj.scs.map.container;

import ro.ubbcluj.scs.map.domain.Task;

import java.util.ArrayList;
import java.util.List;

public class StackContainer implements Container {

    List<Task> elems;

    public StackContainer() {
        this.elems= new ArrayList<>();
    }

    @Override
    public Task remove() {
        int pos=elems.size()-1;
        return elems.remove(pos);
    }

    @Override
    public void add(Task task) {
        elems.add(task);

    }

    @Override
    public int size() {
        return elems.size();
    }

    @Override
    public boolean isEmpty() {
        return elems.isEmpty();
    }
}
