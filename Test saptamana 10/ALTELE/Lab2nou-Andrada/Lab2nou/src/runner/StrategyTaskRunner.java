package runner;

import model.Task;
import container.Container;
import container.Strategy;
import factory.TaskContainerFactory;

public class StrategyTaskRunner implements TaskRunner{
    private Container container;
    private TaskContainerFactory taskContainerFactory = TaskContainerFactory.getInstance();

    public StrategyTaskRunner(Strategy strategy){
        container = taskContainerFactory.createContainer(strategy);
    }

    @Override
    public void executeOneTask() {
        if (!container.isEmpty())
            container.remove().execute();
    }

    @Override
    public void executeAll() {
        while (!container.isEmpty())
            container.remove().execute();
    }

    @Override
    public void addTask(Task t) {
        container.add(t);
    }

    @Override
    public boolean hasTask() {
        return !container.isEmpty();
    }
}
