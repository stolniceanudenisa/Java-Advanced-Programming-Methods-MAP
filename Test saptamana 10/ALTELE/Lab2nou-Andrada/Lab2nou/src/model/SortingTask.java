package model;

import model.enums.SortStrategy;

public class SortingTask extends Task {

    SortStrategy sortStrategy;
    AbstractSorter abstractSorter;
    int[] arr;

    public SortingTask(String _taskId, String _description, SortStrategy sortStrategy, int[] arr) {
        super(_taskId, _description);
        this.sortStrategy = sortStrategy;
        this.arr = arr;
        if(sortStrategy == SortStrategy.BUBBLE_SORT)
            abstractSorter = new BubbleSorter();
        else
            abstractSorter = new QuickSorter();
    }

    @Override
    public void execute() {
        abstractSorter.sort(arr);
        for (int el : arr) {
            System.out.println(el);
        }
        System.out.println();
    }
}
