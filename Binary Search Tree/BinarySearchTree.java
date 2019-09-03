import java.util.*;

/**
 * This BinarySearchTree object defines a reference based binary search tree
 * 
 * @author  
 * @version 
 */

public class BinarySearchTree<T extends Comparable<T>> implements BSTInterface<T>
{
    protected BinaryNode<T> root;      // reference to the root of this BST

    // Creates an empty Binary Search Tree object
    public BinarySearchTree()
    {
    }

    // Returns true if this BST is empty; otherwise, returns false.
    public boolean isEmpty()
    {
        return size()==0;
    }

    // Returns the number of elements in this BST.
    public int size()
    {
        BinaryNode node = root;
        return size(node);
    }

    private int size(BinaryNode node){
        if(node==null){
            return 0;
        }else return size(node.getLeft())+size(node.getRight())+1;
    }
    // Adds element to this BST. The tree retains its BST property.
    public void add (T element)
    {
        BinaryNode node = root;
        if(isEmpty()){
            root = new BinaryNode(element);
        }else{
            while(true){
                if(node.getInfo().compareTo(element)>=0){
                    if(node.getLeft()==null){
                        node.setLeft(new BinaryNode(element));
                        break;
                    }else node = node.getLeft();
                }else{
                    if(node.getRight()==null){
                        node.setRight(new BinaryNode(element));
                        break;
                    }
                    else node = node.getRight();
                }
            }
        }
    }

    public boolean contains (T element)
    {
        BinaryNode node = root;
        while(true){
            if(node.getInfo().compareTo(element)==0)
                return true;
            else if(node.getInfo().compareTo(element)>0){
                if(node.getLeft()==null){
                    return false;
                }else node = node.getLeft();
            }else if(node.getInfo().compareTo(element)<0){
                if(node.getRight()==null){
                    return false;
                }
                else node = node.getRight();
            }
        }
    }

    public ArrayList<BinaryNode> traverse(T element)
    {
        BinaryNode pre = root;
        BinaryNode node = root;
        ArrayList<BinaryNode> list = new ArrayList<>();
        while(true){
            if(node.getInfo().compareTo(element)==0){
                list.add(pre);
                list.add(node);
                return list;
            }else if(node.getInfo().compareTo(element)>0){
                if(node.getLeft()==null){
                    return null;
                }else{
                    pre=node;
                    node = node.getLeft();
                }
            }else if(node.getInfo().compareTo(element)<0){
                if(node.getRight()==null){
                    return null;
                }
                else{
                    node = node.getRight();
                    pre=node;
                }
            }
        }
    }

    // Returns a graphical representation of the tree
    public String toString()
    {
        return toString(root, 0);
    }

    private String toString(BinaryNode<T> tree, int level)
    {
        String str = "";
        if (tree != null)
        {
            str += toString(tree.getRight(), level + 1);
            for (int i = 1; i <= level; ++i)
                str = str + "| ";
            str += tree.getInfo().toString() + "\n";
            str += toString(tree.getLeft(), level + 1);
        }
        return str;
    }

    // Returns a list of elements from a preorder traversal
    public List<T> preorderTraverse()
    {
        BinaryNode node =root;
        ArrayList<T> list = new ArrayList<>();
        trav(0,list, node);
        return list;
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
    // Returns a list of elements from an inorder traversal
    public ArrayList<T> inorderTraverse()
    {
        BinaryNode node =root;
        ArrayList<T> list = new ArrayList<>();
        trav(1,list, node);
        return list;
    }

    // Returns a list of elements from a postorder traversal
    public List<T> postorderTraverse()
    {
        BinaryNode node =root;
        ArrayList<T> list = new ArrayList<>();
        trav(2,list, node);
        return list;
    }
    // Removes an element e from this BST such that e.compareTo(element) == 0
    public void remove (T element)
    {
        ArrayList<BinaryNode> nodes = traverse(element);
        BinaryNode pre = nodes.get(0);
        BinaryNode node = nodes.get(1);

        if(node.getLeft()==null && node.getRight()==null && !node.equals(root)){
            if(node.getInfo().compareTo(pre.getLeft().getInfo())==0){
                pre.setLeft(null);
            }else pre.setRight(null);
        }else if(pre.getLeft()!=null && node.getInfo().compareTo(pre.getLeft().getInfo())<0){
            if(node.getLeft()==null && node.getRight()!=null){
                pre.setRight(node.getRight());
            }else if(node.getLeft()!=null && node.getRight()==null){
                pre.setLeft(node.getLeft());
            }
        }else if(pre.getLeft()!=null && node.getInfo().compareTo(pre.getLeft().getInfo())>=0){
            if(node.getLeft()==null && node.getRight()!=null){
                pre.setLeft(node.getRight());
            }else if(node.getLeft()!=null && node.getRight()==null){
                pre.setRight(node.getLeft());
            }
        }else{
            if(node.equals(root)){
                if(size()==1)
                    root=null;
                else if(root.getLeft()==null)
                    root = root.getRight();
                else if(root.getRight()==null)
                    root=root.getLeft();
            }else {
                pre = node;
                BinaryNode div = pre;
                node = node.getLeft();
                while(node.getRight()!=null){
                    pre=node;
                    node = node.getRight();
                }
                div.setInfo(node.getInfo());
                pre.setRight(null);
            }
        }     
    }

    // Restructures this BST to be optimally balanced
    public ArrayList<BinaryNode> inorder(){
        BinaryNode node =root;
        ArrayList<BinaryNode> list = new ArrayList<>();
        traverse(list, node);
        return list;
    }

    public void traverse(ArrayList<BinaryNode> list ,BinaryNode node){
        if(node!=null){
            traverse(list,node.getLeft());
            list.add(node);
            traverse(list,node.getRight());
        }
    }

    public void balance()
    {
        ArrayList<BinaryNode> list = inorder();
        root = getMiddle(list);
        split(list,root);
    }

    private void split(ArrayList<BinaryNode> list, BinaryNode node){
        if(list.size()>1){
            if(list.size()%2==0){
                ArrayList<BinaryNode> left = new ArrayList<>();
                ArrayList<BinaryNode> right = new ArrayList<>();
                for(int i =0;i<list.size()/2+1;i++){
                    list.get(i).setRight(null);
                    list.get(i).setLeft(null);
                    left.add(list.get(i));
                }
                for(int i=list.size()/2+2;i<list.size();i++){
                    list.get(i).setRight(null);
                    list.get(i).setLeft(null);
                    right.add(list.get(i));
                }
                node.setLeft(getMiddle(left));
                node.setRight(getMiddle(right));
                split(left, node.getLeft());
                split(right,node.getRight());
            }else{
                ArrayList<BinaryNode> left = new ArrayList<>();
                ArrayList<BinaryNode> right = new ArrayList<>();
                for(int i =0;i<list.size()/2;i++){
                    list.get(i).setRight(null);
                    list.get(i).setLeft(null);
                    left.add(list.get(i));
                }
                for(int i=list.size()/2+1;i<list.size();i++){
                    list.get(i).setRight(null);
                    list.get(i).setLeft(null);
                    right.add(list.get(i));
                }
                node.setLeft(getMiddle(left));
                node.setRight(getMiddle(right));
                node=getMiddle(list);
                split(left, node.getLeft());
                split(right,node.getRight());

            }
        }
    }

    private BinaryNode getMiddle(ArrayList<BinaryNode> list){
        if(list.size()%2==0)
            return list.get(list.size()/2+1);
        return list.get(list.size()/2);
    }
}