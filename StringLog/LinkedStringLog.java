
/**
 * This LinkedStringLog object represents a StringLog ADT implemented as
 * a LinkedList using the EnhancedStringLogInterface.
 * 
 * @author 
 * @version 
 */
public class LinkedStringLog implements EnhancedStringLogInterface
{
    private LLStringNode log;
    private String name;
    private int size;

    public LinkedStringLog(String name)
    {
        log = null;
        this.name = name;
        size = 0;
    }

    // returns the name of this StringLog
    public String getName()
    {
        return name;
    }

    // returns the logical size of this StringLog
    public int size()
    {
        return size;
    }

    // returns true if this list contains no elements
    public boolean isEmpty()
    {
        if(size == 0)
        {
            return true;
        }
        return false;
    }

    // returns true if this list is completely full
    public boolean isFull()
    {
        return false;
    }

    // appends the specified element to the end of this list
    public void add(String element)
    {
        if(log == null)
        {
            log = new LLStringNode(element);
            size++;
            return;
        }
        LLStringNode Check  = log;
        while(Check.getLink() != null)
        {
            Check = Check.getLink();
        }
        Check.setLink(new LLStringNode(element));
        size++;
    }

    // returns the element at the specified position in this list
    public String get(int index)
    {
        LLStringNode node = log;
        for(int i = 0; i < index; i++)
        {
            node = node.getLink();
        }
        return node.getInfo();
    }

    // returns the index of the first occurance of the specified element
    // in this list, or -1 if this list does not contain the element
    public int indexOf(String element)
    {
        int defaultt = -1;
        int index = 0;
        LLStringNode node = log;
        while(node != null)
        {
            if(node.getInfo().equals(element))
            {
                defaultt = index;
                break;
            }
            index++;
            node = node.getLink();
        }
        return defaultt;
    }

    // returns true if this list contains the specified element
    public boolean contains(String element)
    {
        LLStringNode node = log;
        while(node != null)
        {
            if(node.getInfo().equals(element))
            {
                return true;
            }
            node = node.getLink();
        }
        return false;
    }

    // returns a formatted string representation of this StringLog
    public String toString()
    {
        String result = "Log: " + name + "\n";
        LLStringNode curNode = log;
        int count = 0;

        while (curNode != null)
        {
            count++;
            result += count + ". " + curNode.getInfo() + "\n";
            curNode = curNode.getLink();
        }
        return result;
    }

    // replaces the element at the specified position in this list
    // with the specified element
    public String set(int index, String element)
    {
        LLStringNode node = log;
        String returned = "";
        for(int i = 0; i < index; i++)
        {
            node = node.getLink();
        }
        returned = node.getInfo();
        node.setInfo(element);
        return returned;
    }

    // inserts the specified element at the specified position in this list
    public void add(int index, String element)
    {
        LLStringNode node = log;
        LLStringNode news = new LLStringNode(element);
        for(int i = 0; i < index-1; i++)
        {
            node = node.getLink();
        }
        news.setLink(node.getLink());
        node.setLink(news);
        size++;
    }

    // removes the element at the specified position in this list
    public String remove(int index)
    {

        String s = "";
        if(index > size){return s;}
        if(index == 0)
        {
            s = log.getInfo();
            size--;
            log = log.getLink();
            return s;
        }
        LLStringNode node = log;
        for(int i = 0; i < index-1; i++)
        {
            node = node.getLink();
        }
        s = node.getLink().getInfo();
        node.setLink(node.getLink().getLink());
        size--;
        return s;
    }

    // removes the first occurance of the specified element from this
    // list, if it is present
    public boolean remove(String element)
    {
        LLStringNode node = log;
        if(log.getInfo().equals(element))
        {
            size--;
            log = log.getLink();
            return true;
        }
        while(!node.getLink().getInfo().equals(element))
        {
            if(node.getLink() == null)
            {
                return false;
            }
            node = node.getLink();
            if(node.getLink() == null)
            {
                return false;
            }
        }
        node.setLink(node.getLink().getLink());
        size--;
        return true;
    }

    // removes all of the elements from this list
    public void clear()
    {
        log = null;
        size = 0;
    }
}
