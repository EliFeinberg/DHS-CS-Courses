public interface HashTableInterface<K,V>
{   
    // returns the number of keys in this hashtable
    public int size();

    // clears this hashtable so that it contains no keys
    public void clear();

    // returns a hash index for a given key
    public int hashIndex(K key);

    // maps the specified key to the specified value in this hashtable
    public boolean put(K key, V value);

    // returns the value to which the specified key is mapped, 
    // or null, if this hashtable does not contain the key
    public V get(K key);

    // removes the key (and its corresponding value) from this hashtable
    public boolean remove(K key);
}
