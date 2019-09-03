/*************************************************************************
 *       Demonstrates hashCodes of various objects
 *
 *************************************************************************/


import java.util.*;

public class HashCodeDemo
{
   public static void main(String[] args)
   {
      Integer obj1 = new Integer(2016);
      System.out.println("hashCode for an integer " + obj1.hashCode());

      String obj2 = new String("2016");
      System.out.println("\nhashCode for a string " + obj2.hashCode());

      StringBuffer obj3 = new StringBuffer("2016");
      System.out.println("\nhashCode for a string buffer " + obj3.hashCode());

      ArrayList<Integer> obj4 = new ArrayList<Integer>();
      obj4.add(new Integer(2016));
      System.out.println("\nhashCode for an arraylist " + obj4.hashCode());

      Iterator obj5 = obj4.iterator();
      System.out.println("\nhashCode for an iterator " + obj5.hashCode());

      HashCodeDemo obj6 = new HashCodeDemo();
      System.out.println("\nhashCode for HashCodeDemo " + obj6.hashCode());

      String obj7 = new String("19999999999999999");
      System.out.println("\nhashCode can be negative " + obj7.hashCode());
   }
}
