
/**
 * Write a description of class ArrayStack here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ArrayStack<T> implements StackInterface<T>
{
    int size;
    T[] Stack;
    public ArrayStack()
    {
        Stack = (T[])new Object[4];
        size = 0;
    }
    // returns the logical size of the stack
    public int size()
    {
        return size;
    }

    // tests if this stack is empty
    public boolean empty()
    {
        return size == 0;
    }

    // looks at the object at the top of this stack
    // without removing it from the stack
    public T peek() throws StackUnderflowException
    {
        if(size == 0)
            throw new StackUnderflowException();
        return Stack[size-1];
    }

    // removes the object at the top of this stack 
    // and returns that object as the value of this function
    public T pop() throws StackUnderflowException
    {
        if(size == 0)
            throw new StackUnderflowException();
        size--;
        return Stack[size];
    }

    // pushes an item onto the top of this stack
    public T push(T item)
    {
        if(size >= Stack.length)
        {
            T[] nStack = (T[])new Object[Stack.length * 2];
            for(int i = 0; i < Stack.length; i++)
            {
                nStack[i] = Stack[i];
            }
            Stack = nStack;
        }

        Stack[size] = item;
        size++;
        return Stack[size-1];
    }

    // removes all of the elements from this stack
    public void clear()
    {
        Stack = (T[])new Object[4];
        size = 0;
        System.gc();
    }
    // returns the 1 based position where an object is on this stack
    // note: when the method ends, the stack is the same as it was at the start
    public int search(Object o)
    {
        int pos = -1;
        for(int i = 0; i < size; i++)
        {
            Object c = Stack[i];
            if(c.equals(o))
            {
                pos = size - i;
            }
        }
        return pos;
    }
}
