
/**
 * This KeyedLinkedList object represents a Keyed List ADT implemented as
 * a LinkedList.
 * 
 * @author 
 * @version 
 */
public class KeyedLinkedList<K,V> implements KeyedLinkedListInterface<K,V>
{
    private KeyNode first;
    private int size;

    // a private inner class that represents a doubly 
    // linked-list node that contains both a key and a value
    private class KeyNode
    {
        K key;
        V value;
        KeyNode previous;
        KeyNode next;

        public KeyNode(K key, V value, KeyNode previous, KeyNode next)
        {
            this.key = key;
            this.value = value;
            this.previous = previous;
            this.next = next;
        }
    }
    public KeyedLinkedList(){first = null; size =0;}
    // returns the logical size of this list
    public int size()
    {
        return size;      // complete this method
    }

    // if key found, update value and return false (nothing added), 
    // otherwise grow list and return true
    public boolean put(K key, V value)
    {
        KeyNode cur = first;
        if(first==null)
        {
            first = (new KeyNode(key,value, null, null));
            size++;
            return true;
        }
        while(cur.key != key && cur.next != null)
        {
            if(cur.key.equals(key))
            {
                cur.value = value;
                return false;
            }
            cur = cur.next;

        }
        System.out.println("This is working i guess");
        size++;
        cur.next = (new KeyNode(key,value, cur, null));
        return true;// complete this method
    }

    // search for key, return associated value
    // if key not found, return null
    public V get(K key)
    {
        KeyNode cur = first;
        if(first == null){return null;}
        while(cur != null)
        {
            if(cur.key.equals(key))
            {
                return cur.value;
            }
            cur = cur.next;
        }

        return null;    // complete this method
    }

    // removes the element at the specified key location in this list
    // return true if remove was successful, false if key not found
    public boolean remove(K key)
    {
        KeyNode cur = first;
        if(first.key == key)
        {
            first = first.next;
            size--;
            return true;
        }
        while(!cur.equals(null) && !cur.key.equals(key))
        {
            cur = cur.next;
        }
        if(cur.key.equals(key))
        {
            
            if(!cur.next.equals(null)){
                cur.next.previous = cur.previous;
            }
            if(cur.previous != (null))
            {
                    cur.previous.next = cur.next;
            }
            else{cur.next.previous = null;}
            size--;
            return true;
        }
        else{return false;}
    }
}
