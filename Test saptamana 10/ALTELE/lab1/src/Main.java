import Domain.MessageTask;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        //Domain.Task task = new Domain.Task(12L, "Sem Map");
        //System.out.println(task.getId() + " " + task.getDesc());
        //System.out.println(task); //sau task.tostring()
        LocalDateTime myObj = LocalDateTime.now();
        MessageTask mess = new MessageTask(12L, "Sem Map", "Oana", "Ale", "Buna", myObj);
        mess.execute();
    }
}
