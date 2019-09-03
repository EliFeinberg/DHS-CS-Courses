import java.util.List;
/**
 * This WordCountCollection interface represents a list of method
 * signatures that will be defined in all classes that implement 
 * this interface.
 * 
 * @author  Tom Bredemeier
 * @version February 11, 2013
 */
public interface WordCountCollection<T>
{
    // Returns the number of elements in this collection.
    public int size();
    
    // Returns true if this collection is empty; otherwise, returns false.
    public boolean isEmpty();

    // Adds element to this collection.
    // Precondition: element is not already in the collection
    public void add (T element);
    
    // Returns the element if this collection contains an element e such that
    // e.compareTo(element) == 0; otherwise, returns null.
    public T fetch(T element);

    // Removes all elements in this collection
    public void clear();
    
    // generates a list of the elements in the collection
    // Postcondion: list is independant (deep copy) of original collection
    public List<T> createList();
}
