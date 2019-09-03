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
public class Heap<T extends Comparable<T>> implements PriorityQueue<T>
{
    ArrayList<T> R;
    // returns the logical size of the priority queue
    public Heap()
    {
        R = new ArrayList<T>();
    }

    public int size()
    {
        return R.size();
    }

    public void swap(int index, int index2)
    {
        T temp = R.get(index);
        R.set(index, R.get(index2));
        R.set(index2, temp);
    }
    // tests if this priority queue is empty
    public boolean empty()
    {
        return R.isEmpty();
    }

    // adds an item to the priority queue
    public T add(T element){
        //if(R.size() == 0){R.add(element);return element;}
        R.add(element);
        complete(element, R.size()-1);
        return element;
    }

    private void complete(T element, int index)
    {
        int parentDex = (index-1)/2;
        if(element.compareTo(R.get(parentDex)) > 0)
        {
            swap(index, parentDex);
            complete(element, parentDex);
        }
    }   
    // looks at the object at the front of this priority queue
    // without removing it from the priority queue
    public T peek() throws PQUnderflowException
    {
        if(R.size() == 0){throw new PQUnderflowException();}
        return R.get(0);
    }

    // removes the object at the front of this priority queue 
    // and returns that object as the value of this function
    public T remove() throws PQUnderflowException
    {
        if(R.size() == 0){throw new PQUnderflowException();}
        T rem = R.get(0);
        int index = R.size()-1;
        swap(0, index);
        R.remove(index);
        removeHelp(0);
        return rem;
    }

    public void removeHelp(int index)
    {
        int child1 = (index + 1)*2 -1;
        int child2 = (index + 1)*2;

        if(child2 < R.size() && child1 < R.size()){
            if(R.get(index).compareTo(R.get(child1)) < 0 && R.get(child1).compareTo(R.get(child2))>0)
            {
                swap(index,child1);
                removeHelp(child1);
                return;
            }
            else if(R.get(index).compareTo(R.get(child2)) < 0)
            {
                swap(index,child2);
                removeHelp(child2);
                return;
            }
        }
    }
    // removes all of the elements from this priority queue
    public void clear(){R.clear();}
}
