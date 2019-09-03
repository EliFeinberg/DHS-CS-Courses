
/**
 * Write a description of class LinkedStack here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LinkedStack<T> implements StackInterface<T>
{
    int size;
    LLNode<T> root;
    LLNode<T> end;
    public LinkedStack()
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

    // looks at the object at the top of this stack
    // without removing it from the stack
    public T peek() throws StackUnderflowException
    {
        if(size == 0)
            throw new StackUnderflowException();
        else if(size == 1)
        {
            return root.getInfo();
        }
        LLNode<T> node = root;
        while(node.getLink() != null)
        {
            node = node.getLink();

        }
        return node.getInfo();
    }

    // removes the object at the top of this stack 
    // and returns that object as the value of this function
    public T pop() throws StackUnderflowException
    {
        if(size == 0)
            throw new StackUnderflowException();
        else if(size == 1)
        {
            size--;
            T s = root.getInfo();
            root = null;
            return s;
        }
        LLNode<T> node = root;
        while(node.getLink().getLink() != null)
        {
            node = node.getLink();
        }
        T s = node.getLink().getInfo();
        node.setLink(null);
        size--;
        return s;
    }

    // pushes an item onto the top of this stack
    public T push(T item)
    {
        if(root == null)
        {
            root = new LLNode(item);
            size++;
            return root.getInfo();
        }
        LLNode Check  = root;
        while(Check.getLink() != null)
        {
            Check = Check.getLink();
        }
        Check.setLink(new LLNode(item));
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
    // returns the 1 based position where an object is on this stack
    // note: when the method ends, the stack is the same as it was at the start
    public int search(Object o)
    {
        int defaultt = -1;
        int index = 0;
        LLNode node = root;
        while(node != null)
        {
            Object c = node.getInfo();
            if(c.equals(o))
            {
                defaultt = size - index;
                break;
            }
            index++;
            node = node.getLink();
        }
        return defaultt;
    }
}
