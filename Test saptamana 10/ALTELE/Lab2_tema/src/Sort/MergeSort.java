package Sort;

import Sort.AbstractSort;

public class MergeSort extends AbstractSort {
    public MergeSort() {
    }

    static void merge(int a[], int beg, int mid, int end)
    {
        int i, j, k;
        int n1 = mid - beg + 1;
        int n2 = end - mid;

        /* temporary Arrays */
        int[] LeftArray = new int[n1];
        int[] RightArray = new int[n2];

        /* copy data to temp arrays */
        for (i = 0; i < n1; i++)
            LeftArray[i] = a[beg + i];
        for (j = 0; j < n2; j++)
            RightArray[j] = a[mid + 1 + j];

        i = 0; /* initial index of first sub-array */
        j = 0; /* initial index of second sub-array */
        k = beg;  /* initial index of merged sub-array */

        while (i < n1 && j < n2)
        {
            if(LeftArray[i] <= RightArray[j])
            {
                a[k] = LeftArray[i];
                i++;
            }
            else
            {
                a[k] = RightArray[j];
                j++;
            }
            k++;
        }
        while (i<n1)
        {
            a[k] = LeftArray[i];
            i++;
            k++;
        }

        while (j<n2)
        {
            a[k] = RightArray[j];
            j++;
            k++;
        }
    }

    public void mergeSort(int[] array,int beg, int end) {
        if (beg < end)
        {
            int mid = (beg + end) / 2;
            mergeSort(array, beg, mid);
            mergeSort(array, mid + 1, end);
            merge(array, beg, mid, end);
        }
    }

    @Override
    public void sort(int[] array) {
        mergeSort(array,0,array.length-1);
    }
}
