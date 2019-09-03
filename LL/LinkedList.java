import java.util.*;
public class LinkedList<E>
{
    Node<E> root;
    int size;
    public E get(){
        return root.value;
    }

    public E get(int pos){
        if(pos > size-1){return null;}
        Node<E> n = root;
        for(int i = 0; i<pos; i++)
        {
            n = n.next;
        }
        return n.value;
    }

    public void add(E val)
    {
        if(root == null)
        {
            root = new Node<E>();
            root.value = val;
            size++;
            System.out.println("CHECK");
            return;
        }
        Node<E> n = root;
        while(n.next != null)
        {
            n = n.next;
        }
        n.next = new Node<E>();
        n.next.value = val;
        size++;
    }

    public void add(E val, int pos)
    {
        Node<E> n = root;
        for(int i = 0; i< pos; i++)
        {
            n = n.next;
        }
        Node<E> ne = new Node<E>();
        ne.value = val;
        ne.next = n.next;
        n.next = ne;
        size++;
    }

    public boolean remove(){
        if(size <= 0){return false;}
        Node<E> n = root;
        while(n.next.next != null)
        {
            n = n.next;
        }
        n.next = null;
        size--;
        return true;
    } //removes the node from the end of the list 

    public boolean remove(int pos){
        Node<E> n = root;
        if(pos > size)
        {
            return false;
        }
        for(int i = 0; i<pos-1; i++)
        {
            n = n.next;
        }
        n.next = n.next.next;
        size--;
        return true;
    }

    public boolean remove(E val){
        Node<E> n = root;
        while(!n.value.equals(val))
        {
            if(n.next == null)
            {
                return false;
            }
            n = n.next;
        }
        n.next = n.next.next;
        return true;
    }

    public boolean contains(E val){
        Node<E> n = root;
        while(!n.value.equals(val))
        {
            if(n.next == null)
            {
                return false;
            }
            n = n.next;
        }
        return true;
    }

    public int size()
    {
        return size;
    }

    public String toString(){
        String s = "";
        int count = 0;
        Node<E> n = root;
        s+= root.value + "\t" + count +"\n";
        count++;
        while(n.next != null)
        {
            s+= n.next.value + "\t" + count +"\n";
            n = n.next;
            count++;
        }
        return s;
    }
}
