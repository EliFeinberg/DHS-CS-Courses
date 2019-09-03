
/**
 * FrontEndTester.java 
 *
 * @author:
 * Assignment #:
 * 
 * Brief Program Description:
 * 
 *
 */

public class FrontEndTester 
{
   public static void main(String[] args) throws InterruptedException
   {
       FrontEnd fe = new FrontEnd("Kevin Bacon");
       String[] arr = {"Kevin Bacon", "Javi\n\nStar Wars", "Eli\n\nThe Office", "Lily\n\nGame of Thrones", "Jonny\n\nMonsters Inc"};
       String[] arr2 = {"Kevin Bacon", "Brooke\n\nShark Boy and Lava Girl", "Reed\n\nA Million Ways To Die In The West", "Josh\n\nThe Hunger Games"};
       Thread.sleep(2000);
       fe.update(arr);
       Thread.sleep(2000);
       fe.update(arr2);
   }
}
