package runner;

import java.time.LocalDateTime;
import utils.Constants;

public class PrinterTaskRunner extends AbstractTaskRunner {
    public PrinterTaskRunner(TaskRunner runner){
        super(runner);
    }
    @Override
    public void executeOneTask() {
        this.runner.executeOneTask();
        this.decoratorExecuteOneTask();
    }
    private void decoratorExecuteOneTask(){
        System.out.println("Task executat la: " + LocalDateTime.now().format(Constants.DATE_TIME_FORMATTER));
    }
}
