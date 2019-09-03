public class NodeTest 
{
    public static void main(String[] args)
    {
        Node root = new Node();
        root.value = 3.14;
        System.out.println(root.value);
        Node a = new Node(); //we could also give Node a constructor 
        //and pass in the value 
        a.value = 5.6;
        Node b = new Node();
        b.value = 2.22;
        Node c = new Node();
        c.value = 8.987;

        //Create LinkedList by making pointer (reference) to next value
        root.next = a;
        a.next = b;
        //add C to the end 
        root.next.next.next = c; //root.next gets A, root.next.next = B, B's 
        //next is C 
        //In order to access C now, you have to print (root.next.next.next.value)

        displayNodes(root);
        System.out.println("\n");
        displayNodes(root);
    }

    public static void displayNodes(Node node)
    {
        while(node != null)
        {
            System.out.println("Node: " + node.value);
            node = node.next;
        }
    }
}
