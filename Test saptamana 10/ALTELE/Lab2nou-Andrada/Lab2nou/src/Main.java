import container.Strategy;

public class Main {
    private static Strategy strategyFromString(String arg){
        return Strategy.valueOf(arg);
    }
    public static void main(String[] args) {

        System.out.println("Hello world!");
        ///TestRunner.runSort();
        TestRunner.runAll(strategyFromString(args[0]));
    }
}
