public interface KeyedLinkedListInterface<K,V>
{
    // returns the logical size of this list
    public int size();

    // search for key, return associated value
    public V get(K key);

    // if key found, update value and return false (nothing added), 
    // otherwise grow table and return true
    public boolean put(K key, V value);

    // removes the element at the specified key location in this list
    public boolean remove(K key);
    // removes all of the elements from this list
}
