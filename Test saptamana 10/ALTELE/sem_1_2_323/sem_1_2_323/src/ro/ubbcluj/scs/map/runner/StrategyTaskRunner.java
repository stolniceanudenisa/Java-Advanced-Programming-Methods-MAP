package ro.ubbcluj.scs.map.runner;

import ro.ubbcluj.scs.map.container.Container;
import ro.ubbcluj.scs.map.domain.Task;
import ro.ubbcluj.scs.map.factory.Strategy;
import ro.ubbcluj.scs.map.factory.TaskContainerFactory;

public class StrategyTaskRunner implements TaskRunner {

    private Container container;

    public StrategyTaskRunner(Strategy strategy) {
        container= TaskContainerFactory.getInstance().createContainer(strategy);
    }

    @Override
    public void executeOneTask() {
        if (!container.isEmpty())
            container.remove().execute();
    }

    @Override
    public void executeAll() {
        while (hasTask())
        {
            executeOneTask();
        }
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
