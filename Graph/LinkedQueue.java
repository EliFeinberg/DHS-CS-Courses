
/**
 * Write a description of class LinkedQueue here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LinkedQueue<T> implements QueueInterface<T>
{
    int size;
    LLNode<T> root;
    LLNode<T> end;
    public LinkedQueue()
    {
        root = null;
        end = null;
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

    public T peek() throws QueueUnderflowException
    {
        if(size == 0)
            throw new QueueUnderflowException();
        else
        {
            return root.getInfo();
        }
    }

    public T remove() throws QueueUnderflowException
    {
        if(size == 0)
            throw new QueueUnderflowException();
        else if(size == 1)
        {
            size--;
            T s = root.getInfo();
            root = null;
            return s;
        }
        else
        {
            size--;
            T s = root.getInfo();
            root = root.getLink();
            return s;
        }
    }

    public T add(T item)
    {
        if(root == null)
        {
            root = new LLNode(item);
            size++;
            return root.getInfo();
        }
        LLNode Check  = new LLNode(item);
        while(Check.getLink() != null)
        {
            Check = Check.getLink();
        }
        Check.setLink(root);
        root = Check;
        size++;
        return item;
    }

    // removes all of the elements from this stack
    public void clear()
    {
        root = null;
        end = null;
        size = 0;
    }
}
