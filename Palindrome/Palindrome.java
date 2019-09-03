import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
/**
 * Palindrome.java  
 *
 * @author:
 * Assignment #:
 * 
 * Brief Program Description:
 * 
 *
 */ 
public class Palindrome
{
    public static boolean isPalindrome(String str) {
        char[] charArray = str.toCharArray();
        Queue q = new LinkedList();
        Stack s = new Stack();
        for(char ch : charArray) {
            if(Character.isLetter(ch)) {
                q.add(Character.toLowerCase(ch));
                s.add(Character.toLowerCase(ch));
            }
        }
        boolean palindrome = true;
        while(!s.isEmpty())
            if(q.remove() != s.pop())
                return false;
        return palindrome;
    }
}
