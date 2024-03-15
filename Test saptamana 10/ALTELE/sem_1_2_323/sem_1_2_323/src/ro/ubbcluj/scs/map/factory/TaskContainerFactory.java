package ro.ubbcluj.scs.map.factory;

import ro.ubbcluj.scs.map.container.Container;
import ro.ubbcluj.scs.map.container.QueueContainer;
import ro.ubbcluj.scs.map.container.StackContainer;

import java.util.Queue;

public class TaskContainerFactory implements Factory{

    private static TaskContainerFactory instance =null;
    public final static TaskContainerFactory INSTANCE =new TaskContainerFactory();
    private TaskContainerFactory() {

    }

    public static Factory getInstance()
    {
        if (instance ==null)
            instance = new TaskContainerFactory();
        return instance;
    }

    @Override
    public Container createContainer(Strategy strategy) {
        if (strategy==Strategy.LIFO)
            return new StackContainer();
        if (strategy==Strategy.FIFO)
            return new QueueContainer();
        else return null;
    }
}
