public interface QueueInterface<T>
{
    // returns the logical size of the queue
    int size();    
    
    // tests if this queue is empty
    boolean empty();
    
    // adds an item onto the rear of this queue
    T add(T item);
        
    // looks at the object at the front of this queue
    // without removing it from the queue
    T peek() throws QueueUnderflowException;
    
    // removes the object at the front of this queue 
    // and returns that object as the value of this function
    T remove() throws QueueUnderflowException;
    
    // removes all of the elements from this stack
    void clear();
}
