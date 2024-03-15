package container;

import model.Task;

public class QueueContainer extends SuperContainer {

    public QueueContainer() {
        super();
    }

    @Override
    public Task remove() {
        Task rez;
        if (!isEmpty()) {
            rez = tasks[0];
            for (int i = 0; i < tasks.length - 1; i++) {
                tasks[i] = tasks[i + 1];
            }
            size--;
            return rez;
        }
        return null;
    }
}
