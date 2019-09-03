public interface StackInterface<T>
{
    // returns the logical size of the stack
    int size();    
    
    // tests if this stack is empty
    boolean empty();
    
    // looks at the object at the top of this stack
    // without removing it from the stack
    T peek() throws StackUnderflowException;
    
    // removes the object at the top of this stack 
    // and returns that object as the value of this function
    T pop() throws StackUnderflowException;
    
    // pushes an item onto the top of this stack
    T push(T item);
        
    // removes all of the elements from this stack
    void clear();
    
    // returns the 1 based position where an object is on this stack
    // note: when the method ends, the stack is the same as it was at the start
    int search(Object o);

}
