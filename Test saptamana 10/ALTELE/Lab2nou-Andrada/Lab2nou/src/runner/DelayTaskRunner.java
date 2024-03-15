package runner;

public class DelayTaskRunner extends AbstractTaskRunner {
    public DelayTaskRunner(TaskRunner runner){super(runner);}

    @Override
    public void executeOneTask(){
        try{
            Thread.sleep(6000);
            this.runner.executeOneTask();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
