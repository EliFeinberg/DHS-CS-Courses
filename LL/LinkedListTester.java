
/**
 * LinkedListTester.java  
 *
 * @author:
 * Assignment #:
 * 
 * Brief Program Description:
 * 
 *
 */
public class LinkedListTester
{
    public static void main()
    {
        LinkedList<Double> test = new LinkedList<Double>();
        test.add(0.1);
        test.add(0.2);
        test.add(0.3);
        test.add(0.4);
        test.add(0.5);
        System.out.println(test.toString());
        System.out.println(test.contains(0.2));
        System.out.println(test.get(4));
        test.remove();
        System.out.println(test.toString());
    }
}
