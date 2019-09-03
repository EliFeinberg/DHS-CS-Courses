public interface EnhancedStringLogInterface
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
    void add(String element);
  
    // returns the element at the specified position in this list
    String get(int index);
    
    // returns the index of the first occurrence of the specified element
    // in this list, or -1 if this list does not contain the element
    int indexOf(String element);
    
    // returns true if this list contains the specified element
    boolean contains(String element);
    
    // returns a formatted string representation of this StringLog
    String toString();
    
    // replaces the element at the specified position in this list
    // with the specified element, and returns the previous element
    String set(int index, String element);
    
    // inserts the specified element at the specified position in this list
    void add(int index, String element);
    
    // removes the element at the specified position in this list, and 
    // returns the removed element
    String remove(int index);
    
    // removes the first occurrence of the specified element from this
    // list, if it is present
    boolean remove(String element);
    
    // removes all of the elements from this list
    void clear();    
}
