package container;

import static utils.Constants.INITIAL_TASK_SIZE;

import model.Task;

public class SuperContainer implements Container{
    protected Task[] tasks;
    protected int size;

    public SuperContainer(){
        size = 0;
        tasks = new Task[INITIAL_TASK_SIZE];

    }
    protected void resize() {
        Task[] newTasks = new Task[2 * size];
        System.arraycopy(tasks, 0, newTasks, 0, size);
        tasks = newTasks;
    }

    @Override
    public Task remove() {
        return null;
    }

    @Override
    public void add(Task task) {
        if (size == tasks.length) {
            resize();
        }
        tasks[size++] = task;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
