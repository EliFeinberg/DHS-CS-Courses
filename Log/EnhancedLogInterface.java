public interface EnhancedLogInterface<T>
{
    // returns the name of this StringLog
    String getName();

    // returns the logical size of this StringLog
    int size();    
    
    // returns true if this list contains no elements
    boolean isEmpty();
    
    // returns true if this list is completely full
    boolean isFull();

    // appends the specified element to the end of this list
    void add(T element);
  
    // returns the element at the specified position in this list
    T get(int index);
    
    // returns the index of the first occurrence of the specified element
    // in this list, or -1 if this list does not contain the element
    int indexOf(T element);
    
    // returns true if this list contains the specified element
    boolean contains(T element);
    
    // returns a formatted string representation of this StringLog
    String toString();
    
    // replaces the element at the specified position in this list
    // with the specified element, and returns the previous element
    T set(int index, T element);
    
    // inserts the specified element at the specified position in this list
    void add(int index, T element);
    
    // removes the element at the specified position in this list, and 
    // returns the removed element
    T remove(int index);
    
    // removes the first occurrence of the specified element from this
    // list, if it is present
    boolean remove(T element);
    
    // removes all of the elements from this list
    void clear();    
}
