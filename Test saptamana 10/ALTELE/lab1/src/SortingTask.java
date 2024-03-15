import Domain.Task;

public class SortingTask extends Task {

    public SortingTask(Long id, String desc) {
        super(id, desc);
    }


    @Override
    public void execute() {
        System.out.println("Mesajul:" + message);
        System.out.println("Trimis de:" + from);
    }
}
