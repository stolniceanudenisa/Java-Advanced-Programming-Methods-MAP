package Container;

import Domain.Task;



public class QueueContainer extends SuperClassContainer{
    public QueueContainer(){
        super();
    }

    @Override
    public Task remove() {
        Task newTask;
        if(!isEmpty()) {
            newTask = elems[0];
            for (int i = 0; i < elems.length - 1; i++) {
                elems[i] = elems[i + 1];
            }
            size--;
            return newTask;
        }
        return null;
    }
}
