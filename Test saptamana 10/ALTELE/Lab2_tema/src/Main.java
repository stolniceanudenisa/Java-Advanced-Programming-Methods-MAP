import Container.Strategy;
import Domain.MessageTask;
import Domain.Task;
import Runner.StrategyTaskRunner;
import Runner.TaskRunner;
import Sort.SortingStrategy;
import Sort.SortingTask;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        Task messageTask=new MessageTask(1L,"ughfs","Alex", "Andrei", LocalDateTime.now());
        Task messageTask1=new MessageTask(2L,"hgfg","Ale", "Andrei", LocalDateTime.now());
        Task messageTask2=new MessageTask(3L,"qefgrtbhn","Andreea", "Andrei", LocalDateTime.now());
        Task messageTask3=new MessageTask(4L,"ewfdg","Maria", "Andrei", LocalDateTime.now());
        Task messageTask4=new MessageTask(5L,"asdsfgt","Ioana", "Andrei", LocalDateTime.now());

        Task task1 = new SortingTask(1L, "da", new int[]{22,55,11,7,5}, SortingStrategy.MergeSort);
        task1.execute();

        Task task2 = new SortingTask(1L, "da", new int[]{22,55,11,7,5}, SortingStrategy.BubbleSort);
        task2.execute();

        TaskRunner taskRunner = new StrategyTaskRunner(Strategy.LIFO);
        taskRunner.addTask(messageTask);
        taskRunner.addTask(messageTask1);
        taskRunner.addTask(messageTask2);
        taskRunner.addTask(messageTask3);
        taskRunner.addTask(messageTask4);
        taskRunner.executeAll();

        TaskRunner taskRunner1 = new StrategyTaskRunner(Strategy.FIFO);
        taskRunner1.addTask(messageTask);
        taskRunner1.addTask(messageTask1);
        taskRunner1.addTask(messageTask2);
        taskRunner1.addTask(messageTask3);
        taskRunner1.addTask(messageTask4);
        taskRunner1.executeAll();
    }
}
