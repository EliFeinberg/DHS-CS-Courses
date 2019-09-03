import java.util.ArrayList;
/**
 * This SortUtilities object is the parent class of Sorts 
 * and ExemplarSorts.  The purpose is to contain methods common
 * to both classes.
 * 
 * @author  Tom Bredemeier
 * @version April 10, 2013
 */
public abstract class SortUtilities
{
    protected String[] array;
    protected int swaps;
    protected ArrayList<String[]> results;

    public SortUtilities(String[] a)
    {
        array = a;
        results = new ArrayList<String[]>();
    }

    public abstract void bubbleSort();

    public abstract void selectionSort();

    public abstract void insertionSort();

    public abstract void mergeSort();

    public abstract void quickSort();

    public abstract void heapSort();

    public void swap(int a, int b)
    {
        String temp = array[a];
        array[a] = array[b];
        array[b] = temp;
        storeArray(a, b);
    }

    public void storeArray(int a, int b)
    {
        String[] output = new String[array.length + 2];
        output[0] = a + "";
        output[1] = b + "";
        for(int i = 0; i < array.length; i++)
            output[i + 2] = array[i];
        results.add(output);
    }

    public ArrayList<String[]> getResults()
    {
        return results;
    }

    // Precondition: current root position is "empty"
    // inserts element into the tree and ensures shape and order properties
    public void reheapDown(String item, int root, int lastIndex)
    {
        int hole = root;    // current index of hole
        int newhole;        // index where the hole should move to

        newhole = newHole(hole, lastIndex, item);       // find next hole
        while(newhole != hole)
        {
            swap(hole, newhole);                        // move element up
            hole = newhole;                             // move hole down
            newhole = newHole(hole, lastIndex, item); // find next hole
        }
        array[hole] = item;                             // fill in the final hole
    }

    // if either child of hole is larger than element, return the index
    // of the larger child; otherwise, return the index of the hole.
    public int newHole(int hole, int lastIndex, String item)
    {
        int left = (hole * 2) + 1;
        int right = (hole * 2) + 2;

        if(left > lastIndex)            // hole has no children
            return hole;
        else if(left == lastIndex)      // hole has left child only
            if(item.compareTo(array[left]) < 0) // item < left child
                return left;
            else                        // item >= left child
                return hole;
        else                        // hole has two children
        if(array[left].compareTo(array[right]) < 0)   // left child < right child
            if(array[right].compareTo(item) <= 0)         // right child <= item
                return hole;
            else                    // item < right child
                return right;
        else                    // left child >= right child
        if(array[left].compareTo(item) <= 0)          // left child <= item
            return hole;
        else                    // item < left child
            return left;
    }

}
