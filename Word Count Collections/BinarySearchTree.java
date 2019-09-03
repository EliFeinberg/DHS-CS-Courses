import java.util.*;
/**
 * Write a description of class BinarySearchTree here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BinarySearchTree<T extends Comparable<T>> implements WordCountCollection<T>
{
    BinaryNode<T> root;
    int size;
    public BinarySearchTree()
    {
        root = null;
        size = 0;
    }

    public int size(){return size;}

    // Returns true if this collection is empty; otherwise, returns false.
    public boolean isEmpty(){return root == null || size == 0;}

    // Adds element to this collection.
    // Precondition: element is not already in the collection
    public void add (T element)
    {
        BinaryNode<T> nod = root;
        if(root == null){root = new BinaryNode(element);size++;}
        else
        {
            while(true){
                if(nod.getInfo().compareTo(element) < 0)
                {
                    if(nod.getLeft() == null){nod.setLeft(new BinaryNode<T>(element)); size++;return;}
                    else{nod = nod.getLeft();}
                }
                if(nod.getInfo().compareTo(element) > 0)
                {
                    if(nod.getRight() == null){nod.setRight(new BinaryNode<T>(element)); size++;return;}
                    else{nod = nod.getRight();}
                }
            }
        }
    }

    // Returns the element if this collection contains an element e such that
    // e.compareTo(element) == 0; otherwise, returns null.
    public T fetch(T element)
    {
        BinaryNode<T> nod = root;
        if(root == null){return null;}
        else
        {
            while(true){
                if(nod.getInfo().compareTo(element) == 0){return nod.getInfo();}
                else if(nod.getInfo().compareTo(element) < 0)
                {
                    if(nod.getLeft() == null){return null;}
                    nod = nod.getLeft();
                }
                else if(nod.getInfo().compareTo(element) > 0)
                {
                    if(nod.getRight() == null){return null;}
                    nod = nod.getRight();
                }
            }
        }
    }
    
   private void trav(int type, ArrayList<T> list,BinaryNode node){
        if(node!=null){
            if(type==0){
                list.add((T)node.getInfo());
                trav(0, list, node.getLeft());
                trav(0, list, node.getRight());
            }else if(type==1){

                trav(1, list,node.getLeft());
                list.add((T)node.getInfo());
                trav(1, list,node.getRight());
            }else if(type==2){
                trav(2, list,node.getLeft());
                trav(2, list,node.getRight());
                list.add((T)node.getInfo());
            }
        }
    }
    // Removes all elements in this collection

    public void clear(){size = 0; root = null;}
    // generates a list of the elements in the collection
    // Postcondion: list is independant (deep copy) of original collection
    public List<T> createList()
    {
        BinaryNode node =root;
        ArrayList<T> list = new ArrayList<>();
        trav(1,list, node);
        return list;
    }
}
