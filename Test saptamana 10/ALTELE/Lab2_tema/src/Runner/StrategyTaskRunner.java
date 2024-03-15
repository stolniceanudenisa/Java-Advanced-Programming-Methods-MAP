package Runner;

import Container.Container;
import Container.Strategy;
import Domain.Task;
import Factory.TaskContainerFactory;

public class StrategyTaskRunner implements TaskRunner{
    Container container;

    public StrategyTaskRunner(Strategy strategy) {
        container= TaskContainerFactory.getInstance().createContainer(strategy);
    }

    @Override
    public void executeOneTask() {
        if(hasTask())
        {
            container.remove().execute();
        }
    }

    @Override
    public void executeAll() {
        while (hasTask())
            executeOneTask();
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
