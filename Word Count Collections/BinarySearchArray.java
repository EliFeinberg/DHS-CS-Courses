import java.util.*;
/**
 * Write a description of class BinarySearchArray here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BinarySearchArray<T extends Comparable<T>> implements WordCountCollection<T>
{
    int size;
    T[] arr;

    public BinarySearchArray()
    {
        arr = (T[]) new Comparable[5];
        size = 0;
    }
    // Returns the number of elements in this collection.
    public int size(){return size;}

    // Returns true if this collection is empty; otherwise, returns false.
    public boolean isEmpty(){return size == 0;}

    // Adds element to this collection.
    // Precondition: element is not already in the collection
    public void add (T element)
    {
        if(size == 0){
            arr[0] = element;
            size++;
        }
        else
        {
            if(size == arr.length)
            {
                T[] n = (T[]) new Comparable[size*2];
                for(int i = 0; i< size; i++)
                {
                    n[i] = arr[i];
                }
                arr = n;
            }
            int j = Math.abs(Arrays.binarySearch(arr, 0, size, element)+1);
            System.arraycopy(arr, j, arr, j+1, size-j);
            arr[j] = element;
            size++;
        }
    }

    // Returns the element if this collection contains an element e such that
    // e.compareTo(element) == 0; otherwise, returns null.
    public T fetch(T element)
    {
        int j = Arrays.binarySearch(arr, 0, size, element);
        if(j < 0){return null;}
        return arr[j];
    }

    // Removes all elements in this collection
    public void clear(){size = 0;  arr = (T[]) new Comparable[5];}

    // generates a list of the elements in the collection
    // Postcondion: list is independant (deep copy) of original collection
    public List<T> createList()
    {
        List<T> x = new ArrayList<T>();
        for(int i = 0; i<size; i++)
        {
            x.add(arr[i]);
        }
        return x;
    }
}
