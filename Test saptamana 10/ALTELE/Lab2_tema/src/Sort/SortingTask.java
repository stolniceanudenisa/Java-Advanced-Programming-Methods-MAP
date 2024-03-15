package Sort;

import Domain.Task;



public class SortingTask extends Task {
    int[] array;
    AbstractSort abstractSorter;
    SortingStrategy strategy;

    public SortingTask(Long id, String desc, int[] array, SortingStrategy strategy) {
        super(id, desc);
        this.array = array;
        this.strategy = strategy;

    }
    @Override
    public void execute(){
        if(strategy == SortingStrategy.MergeSort) {
            abstractSorter = new MergeSort();
            abstractSorter.sort(array);
        }
        if(strategy == SortingStrategy.BubbleSort) {
            abstractSorter = new BubbleSort();
            abstractSorter.sort(array);
        }
        for(int el:array){
            System.out.println(el);
        }
        System.out.println();
    }
}
