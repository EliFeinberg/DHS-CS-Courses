public class HashTable<Value, Key>
{
    final int SIZE = 30;
    HashEntry[] sys;
    public HashTable()
    {
        sys = new HashEntry[SIZE];
        for(int i = 0; i < SIZE; i++)
        {
            sys[i] = null;
        }
    }

    public void put(Key stuff, Value value)
    {
        int pos = stuff.hashCode()%SIZE;
        while(sys[pos] != null){pos++; pos %= SIZE;}
        sys[pos] = new HashEntry(value,stuff);
    }
    
    public Value get(Key hashCode)
    {
        int pos = hashCode.hashCode()%SIZE;
        while(sys[pos].key.hashCode() != hashCode.hashCode()){pos++; pos %= SIZE;}
        return (Value)(sys[pos].value);
    }
}
