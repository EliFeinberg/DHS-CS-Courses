public interface PriorityQueue<T extends Comparable<T>>
{
    // returns the logical size of the priority queue
    int size();    
    
    // tests if this priority queue is empty
    boolean empty();
    
    // adds an item to the priority queue
    T add(T element);
        
    // looks at the object at the front of this priority queue
    // without removing it from the priority queue
    T peek() throws PQUnderflowException;
    
    // removes the object at the front of this priority queue 
    // and returns that object as the value of this function
    T remove() throws PQUnderflowException;
    
    // removes all of the elements from this priority queue
    void clear();
}
