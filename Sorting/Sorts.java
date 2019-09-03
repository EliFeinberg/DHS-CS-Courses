import java.util.ArrayList;
/**
 * This Sorts object represents a class that perform
 * Bubble, Selection, Insertion, Merge, Quick, and Heap sorts
 * 
 * @author  
 * @version 
 */
public class Sorts extends SortUtilities
{
    public Sorts(String[] array)
    {
        super(array);
    }

    // The array to be sorted is a String array called 'array'

    // Whenever you need to swap two elements in 'array', call the 'swap(int a, int b)' method 
    // where a and b are the indices of the elements in 'array' that need to be swapped.  Every
    // time 'swap' is called, a snapshot of the array is taken, which is later used to 
    // compare against an exemplar (test example) to see if all of the swaps are correct.

    // All of your sorting methods should utilize 'in-place' sorting, meaning that elements
    // are always swapped, rather than being copied out to a temporary variable, and then copied 
    // back later.

    // Feel free to write any private helper methods you wish to use

    public void bubbleSort()    // ascending, bubbling up from beginning to end
    {
        boolean swap;
        do {
            swap = false;
            for(int i = 0; i < array.length - 1; i++) {
                if(array[i].compareTo(array[i + 1]) > 0) {
                    swap(i, i + 1);
                    swap = true;
                }
            }
        } while(swap);
    }

    public void selectionSort() // ascending, selecting the maximum values
    {
        int end = array.length - 1;
        int max;
        while(end > 0) {
            max = 0;
            for(int i = 1; i <= end; i++)
                if(array[i].compareTo(array[max]) > 0)
                    max = i;
            if(max != end)
                swap(max, end);
            end -= 1;
        }
    }

    public void insertionSort() // ascending, inserting values on the front end
    {
        int j;
        for(int i = 1; i < array.length; i++) {
            j = i;
            while(j != 0 && array[j].compareTo(array[j - 1]) < 0) {
                swap(j - 1, j);
                j--;
            }
        }
    }

    public void mergeSort() // ascending, working from front to back
    {
        mergeSort(0, array.length - 1);
    }

    private void mergeSort(int first, int last) {
        if(first < last) {
            int middle = (first + last) / 2;
            mergeSort(first, middle);
            mergeSort(middle + 1, last);
            merge(first, last);
        }
    }

    private void merge(int first, int last) {
        int j;
        for(int i = first + 1; i <= last; i++) {
            j = i;
            while(j != first && array[j].compareTo(array[j - 1]) < 0) {
                swap(j, j - 1);
                j--;
            }
        }
    }

    public void quickSort() // ascending, working from front to back
    {
        quickSort(0, array.length - 1);  
    }

    private void quickSort(int first, int last) {
        if(first < last) {
            int splitPoint;
            splitPoint = split(first, last);
            quickSort(first, splitPoint - 1);
            quickSort(splitPoint + 1, last);
        }
    }

    private int split(int first, int last) {
        String splitVal = array[first];
        int saveF = first;
        boolean onCorrectSide;
        first++;
        do {
            onCorrectSide = true;
            while(onCorrectSide)
                if(array[first].compareTo(splitVal) > 0)
                    onCorrectSide = false;
                else {
                    first++;
                    onCorrectSide = (first <= last);
                }
            onCorrectSide = (first <= last);
            while(onCorrectSide)
                if(array[last].compareTo(splitVal) < 0)
                    onCorrectSide = false;
                else {
                    last--;
                    onCorrectSide = (first <= last);
                }
            if(first < last) {
                swap(first, last);
                first++;
                last--;
            }
        } while(first <= last);
        if(array[saveF].compareTo(array[last]) != 0)
            swap(saveF, last);
        return last;
    }

    public void heapSort()  // ascending
    {
        for(int i = array.length / 2 - 1; i >= 0; i--)
            reheapDown(array[i], i, array.length - 1);
        for(int i = array.length - 1; i >= 1; i--) {
            swap(0, i);
            if(i < 5) {
                boolean sorted = true;
                String prior = array[0];
                for(int j = 1; j <= i; j++) {
                    if(array[j].compareTo(prior) < 0)
                        sorted = false;
                    prior = array[j];
                }
                if(sorted)
                    return;
            }
            reheapDown(array[0], 0, i - 1);
        }
    }
}
