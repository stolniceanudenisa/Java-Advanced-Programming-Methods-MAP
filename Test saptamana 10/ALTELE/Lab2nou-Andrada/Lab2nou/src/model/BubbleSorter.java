package model;

public class BubbleSorter extends AbstractSorter {

    @Override
    public void sort(int[] arr) {
        boolean ok = true;
        while (ok) {
            ok = false;
            for (int i = 0; i < arr.length - 1; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[i] > arr[j]) {
                        ok = true;
                        int aux = arr[i];
                        arr[i] = arr[j];
                        arr[j] = aux;
                    }
                }
            }
        }
    }
}
