
/**
 * ArrayQueue.java  
 *
 * @author:
 * Assignment #:
 * 
 * Brief Program Description:
 * 
 *
 */
public class ArrayQueue<T> implements QueueInterface<T>
{
    int size;
    int start;
    T[] Queue;
    public ArrayQueue()
    {
        Queue = (T[])new Object[4];
        size = 0;
        start = 0;
    }
    // returns the logical size of the queue
    public int size()
    {
        return size;
    }
    // tests if this queue is empty
    public boolean empty()
    {
        return size == 0;
    }
    // adds an item onto the rear of this queue
    public T add(T item)
    {
        if(size >= Queue.length)
        {
            T[] nQueue = (T[])new Object[Queue.length * 2];
            for(int i = 0; i < Queue.length; i++)
            {
                nQueue[i] = Queue[i];
            }
            Queue = nQueue;
        }
        if(start > 0)
        {
            start--;
            Queue[start] = item;
            size++;
            return Queue[start];
        }
        Queue[size] = item;
        size++;
        return Queue[size - 1];
    }

    public T peek() throws QueueUnderflowException
    {
        if(size == 0)
            throw new QueueUnderflowException();
        return Queue[start];
    }

    public T remove() throws QueueUnderflowException
    {
        if(size == 0)
            throw new QueueUnderflowException();
        size--;
        T temp = Queue[start];
        start++;
        return temp;
    }

    public void clear()
    {
        Queue = (T[])new Object[4];
        size = 0;
        start = 0;
        //System.gc();
    }
}
