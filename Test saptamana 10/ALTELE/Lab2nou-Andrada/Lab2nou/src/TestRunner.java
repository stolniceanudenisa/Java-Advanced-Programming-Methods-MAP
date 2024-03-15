import model.MessageTask;
import model.SortingTask;
import model.enums.SortStrategy;
import container.Strategy;
import java.time.LocalDateTime;

import runner.DelayTaskRunner;
import runner.PrinterTaskRunner;
import runner.StrategyTaskRunner;

public class TestRunner {
    private static MessageTask[] createMessageTask(){
        MessageTask messageTask1 = new
                MessageTask("1","description","message ","from","to", LocalDateTime.now());
        MessageTask messageTask2 = new
                MessageTask("2","description","mesage ","from","to", LocalDateTime.now());
        MessageTask messageTask3 = new
                MessageTask("3","description","massage ","from","to", LocalDateTime.now());
        return new MessageTask[]{messageTask1,messageTask2,messageTask3};
    }

    public static void runStrategy(Strategy strategy){
        MessageTask[] messageTasks = createMessageTask();
        StrategyTaskRunner runner = new StrategyTaskRunner(strategy);
        runner.addTask(messageTasks[0]);
        runner.addTask(messageTasks[1]);
        runner.addTask(messageTasks[2]);
        runner.executeAll();
    }

    public static void runPrinter(Strategy strategy){ //13
        MessageTask[] lista = createMessageTask();
        StrategyTaskRunner strategyTaskRunner = new StrategyTaskRunner(strategy);
        PrinterTaskRunner printerTaskRunner = new PrinterTaskRunner(strategyTaskRunner);
        for (MessageTask task:lista){
            printerTaskRunner.addTask(task);
        }
        printerTaskRunner.executeAll();
    }
    public static void run(){
        MessageTask[] list = createMessageTask();
        for (MessageTask messageTask : list)
            messageTask.execute();
    }
    public static void runSort(){
        SortingTask sortingTask1 = new SortingTask("1","2", SortStrategy.BUBBLE_SORT, new int[]{3, 8, 2, 5, 3, 5});
        SortingTask sortingTask2 = new SortingTask("2","2", SortStrategy.QUICK_SORT, new int[]{3, 8, 2, 5, 3, 5});
        sortingTask1.execute();
        sortingTask2.execute();

    }

    public static void runAll(Strategy strategy){
        MessageTask[] messageTasks = createMessageTask();
        StrategyTaskRunner strategyTaskRunner = new StrategyTaskRunner(strategy);
        StrategyTaskRunner strategyTaskRunner2 = new StrategyTaskRunner(strategy);
        StrategyTaskRunner strategyTaskRunner3 = new StrategyTaskRunner(strategy);

        PrinterTaskRunner printerTaskRunner = new PrinterTaskRunner(strategyTaskRunner2);
        DelayTaskRunner delayTaskRunner = new DelayTaskRunner(new PrinterTaskRunner(new PrinterTaskRunner(strategyTaskRunner3)));
        for(MessageTask task: messageTasks){
            strategyTaskRunner.addTask(task);
            printerTaskRunner.addTask(task);
            delayTaskRunner.addTask(task);
        }
        strategyTaskRunner.executeAll();
        printerTaskRunner.executeAll();
        delayTaskRunner.executeAll();
    }
}
