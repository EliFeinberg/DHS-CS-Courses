import java.util.*;
/**
 * Heap.java  
 *
 * @author:
 * Assignment #:
 * 
 * Brief Program Description:
 * 
 *
 */
public class Heap<V extends Comparable<V>> 
{
    ArrayList pq;
    public Heap()
    {
        pq = new ArrayList<V>();
    }

    // returns the logical size of the priority queue
    public int size()
    {
        return pq.size();
    }

    // tests if this priority queue is empty
    public boolean empty()
    {
        return pq.size() == 0;
    }

    // adds an item to the priority queue
    public V add(V element)
    {
        pq.add(element);
        confirmPos(element, pq.size()-1);
        return element;
    }

    public void confirmPos(V element, int index)
    {
        int parentIndex = (index-1)/2;
        if(element.compareTo((V)pq.get(parentIndex)) > 0) //then they switch 
        {
            V temp = (V)pq.get(index);
            pq.set(index, pq.get(parentIndex));
            pq.set(parentIndex, temp);
            confirmPos(element, parentIndex);
        }
    }

    // looks at the object at the front of this priority queue
    // without removing it from the priority queue
    public V peek() 
    {
        if(pq.size() == 0)
        {
            //throw new PQUnderflowVxception();
            return null;
        }
        else
        {
            return (V)pq.get(0);
        }
    }

    // removes the object at the front of this priority queue 
    // and returns that object as the value of this function
    public V remove() 
    {
        if(pq.size() == 0)
        {
            //throw new PQUnderflowVxception();
            return null;
        }
        else
        {
            V toReturn = (V)pq.get(0);
            if(pq.size() == 1)
            {
                pq.remove(0);
            }
            else
            {
                pq.set(0, pq.remove(pq.size()-1)); //sets root to be the most recent leaf added 
                removePos(0);
            }
            return toReturn;
        }

    }

    public void removePos(int parentIndex) //has to move up the most recent leaf so it remains complete
    {
        int child1 = (parentIndex*2)+1;
        int child2 = (parentIndex*2)+2;
        if(child1 < pq.size() && child2 < pq.size()) 
        {
            int comp1 = ((V)pq.get(child1)).compareTo((V)pq.get(parentIndex));
            int comp2 = ((V)pq.get(child2)).compareTo((V)pq.get(parentIndex));
            if(comp1 > 0 || comp2 > 0) 
            {
                if(((V)pq.get(child1)).compareTo((V)pq.get(child2)) > 0)
                {//this means that the parent and comp1 must switch      
                    swap(parentIndex, child1);
                    removePos(child1);
                }
                else
                {//this means that the parent and comp2 must switch 
                    swap(parentIndex, child2);
                    removePos(child2);
                }
            }
        }
        else if(child1 < pq.size())
        {
            int comp1 = ((V)pq.get(child1)).compareTo((V)pq.get(parentIndex));
            if(comp1 > 0)
            {
                swap(parentIndex, child1);
                removePos(child1);
            }
        }
    }

    public void swap(int one, int two)
    {
        V temp = (V)pq.get(one);
        pq.set(one, pq.get(two));
        pq.set(two, temp);
    }

    // removes all of the elements from this priority queue
    public void clear()
    {
        pq = new ArrayList<V>();
    }

    public String toString()
    {
        String str = "";
        for(int i = 0; i < pq.size(); i++)
        {
            str += "\n " + i + ": " + pq.get(i);
        }
        return str;
    }

}
