import java.util.*;
/**
 * Write a description of class LinkedList here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LinkedList<T extends Comparable<T>> implements WordCountCollection<T>
{
    LLNode<T> root;
    int size;
    // Returns the number of elements in this collection.
    public LinkedList(){root = null; size = 0;}

    public int size(){return size;}

    // Returns true if this collection is empty; otherwise, returns false.
    public boolean isEmpty(){return root == null || size == 0;}

    // Adds element to this collection.
    // Precondition: element is not already in the collection
    public void add (T element)
    {
        if(root == null)
        {
            root = (new LLNode<T>(element));
            size++;
            return;
        }
        else
        {
            LLNode<T> nod = root;
            while(nod.getLink() != null)
            {
                nod = nod.getLink();
            }
            nod.setLink(new LLNode<T>(element));
            size++;
        }
    }

    // Returns the element if this collection contains an element e such that
    // e.compareTo(element) == 0; otherwise, returns null.
    public T fetch(T element)
    {
        if(root == null)
        {
            return null;
        }
        LLNode<T> nod = root;
        while(nod != null)
        {
            if(nod.getInfo().compareTo(element) == 0)
            {
                return nod.getInfo();
            }
            nod = nod.getLink();
        }
        return null;
    }

    // Removes all elements in this collection
    public void clear(){size = 0; root = null;}

    // generates a list of the elements in the collection
    // Postcondion: list is independant (deep copy) of original collection
    public List<T> createList()
    {
        List<T> x = new ArrayList<T>();
        LLNode<T> nod = root;
        while(nod != null)
        {
                x.add(nod.getInfo());
                nod = nod.getLink();
        }
        return x;
    }
}
