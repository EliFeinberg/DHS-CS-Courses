
/**
 * Test.java 
 *
 * @author:
 * Assignment #:
 * 
 * Brief Program Description:
 * 
 *
 */

public class Test 
{
   public static void main(String[] args)
   {
       HashTable people = new HashTable();
       people.put("Weeb", 1);
       people.put("Javi", 0);
       people.put("Caden", 10);
       System.out.println(people.get("Caden"));
   }
}
