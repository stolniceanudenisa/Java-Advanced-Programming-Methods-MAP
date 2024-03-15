package Container;

import Domain.Task;

import static Utils.Util.initial_task_size;

public abstract class SuperClassContainer implements Container{
    Task[] elems;
    int size;
    public SuperClassContainer(){
        size = 0;
        elems = new Task[initial_task_size];
    }
    @Override
    public Task remove() {
        return null;
    }

    public void resize(){
        Task[] newTasks = new Task[2*size];
        System.arraycopy(elems,0,newTasks,0,size);
        elems = newTasks;
    }

    @Override
    public void add(Task task) {
        if(elems.length == size())
            resize();
        elems[size++]=task;
    }



    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }
}
