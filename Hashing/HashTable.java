/**
 * This HashTable object represents a hash table ADT implemented as an array of doubly linked lists
 * 
 * @author  
 * @version 
 */
public class HashTable<K,V> implements HashTableInterface<K,V>
{
    private int size;
    private int capacity;
    private KeyedLinkedList<K,V> [] table;

    @SuppressWarnings("unchecked")
    public HashTable(int capacity)
    {
        table = (KeyedLinkedList<K,V>[]) new KeyedLinkedList[capacity];
        // set the remaining instance variables
        // instantiate a new KeyedLinkedList<K,V> list in every element of table
    }

    // returns the number of keys in this hashtable
    public int size()
    {
        return -1;  // complete this method
    }

    // clears this hashtable so that it contains no keys
    @SuppressWarnings("unchecked")
    public void clear()
    {
        // complete this method
        // it is essentially identical to the constructor
    }

    // returns the hashtable index for a given key
    public int hashIndex(K key)
    {
        // the line below insures a positive integer by eliminating the sign bit
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    // if key found, update value and return false (nothing added), 
    // otherwise grow table and return true
    public boolean put(K key, V value)
    {
        KeyedLinkedList<K,V> list = table[hashIndex(key)];
        return false;   // complete this method
    }

    // search for key, return associated value
    // if key not found, return null
    public V get(K key)
    {
        return null;    // complete this method
    }

    // removes the element at the specified key location in this table
    // return true if remove was successful, false if key not found
    public boolean remove(K key)
    {
        return false;   // complete this method
    }
}
