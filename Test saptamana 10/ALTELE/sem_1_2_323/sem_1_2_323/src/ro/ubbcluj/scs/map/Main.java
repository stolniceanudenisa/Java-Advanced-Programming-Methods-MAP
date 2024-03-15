package ro.ubbcluj.scs.map;

import ro.ubbcluj.scs.map.domain.MessageTask;
import ro.ubbcluj.scs.map.factory.Factory;
import ro.ubbcluj.scs.map.factory.Strategy;
import ro.ubbcluj.scs.map.factory.TaskContainerFactory;
import ro.ubbcluj.scs.map.runner.StrategyTaskRunner;
import ro.ubbcluj.scs.map.runner.TaskRunner;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
	// write your code here
//        Task task=new Task(12L, "studiez");
//        System.out.println(task);
//        System.out.println(task.getId());
//        System.out.println(task.getDesc());
        MessageTask messageTask=new MessageTask(1l,"De viata","Andy", "Ioana", LocalDateTime.now());
        MessageTask messageTask1=new MessageTask(2l,"De viata","Andy", "Ioana", LocalDateTime.now());
        MessageTask messageTask2=new MessageTask(3l,"De viata","Andy", "Ioana", LocalDateTime.now());
        MessageTask messageTask3=new MessageTask(4l,"De viata","Andy", "Ioana", LocalDateTime.now());
        MessageTask messageTask4=new MessageTask(5l,"De viata","Andy", "Ioana", LocalDateTime.now());

        TaskRunner runner=new StrategyTaskRunner(Strategy.LIFO);
        runner.addTask(messageTask);
        runner.addTask(messageTask1);
        runner.addTask(messageTask2);
        runner.addTask(messageTask3);
        runner.addTask(messageTask4);
        runner.executeAll();

//        Factory factory= TaskContainerFactory.getInstance();
//        factory.createContainer(Strategy.LIFO);
//
//        Factory factory2= TaskContainerFactory.getInstance();
//        System.out.println("ok");

      //  final Factory factory5=TaskContainerFactory.getInstance();

    }
}
