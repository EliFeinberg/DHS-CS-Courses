import java.util.*;
/**
 * PostfixEvaluator.java  
 *
 * @author:
 * Assignment #:
 * 
 * Brief Program Description:
 * 
 *
 */
public class PostfixEvaluator
{

    public static int evaluate(String s) throws PostfixException
    {
        Stack<Integer> stuff = new Stack<Integer>();
        while(s.length() > 0)
        {
            if(s.substring(0,1).equals(" ")){
                s = s.substring(1);
                continue;
            }
            else if(s.substring(0,1).equals("+"))
            {
                if(stuff.size() < 2)
                {
                    throw new PostfixException("Malformed");
                }
                s = s.substring(1);
                int t = stuff.pop();
                stuff.push(stuff.pop() + t);
                continue;
            }
            else if(s.substring(0,1).equals("-"))
            {
                if(stuff.size() < 2)
                {
                    throw new PostfixException("Malformed");
                }
                s = s.substring(1);
                int t = stuff.pop();
                stuff.push(stuff.pop() - t);
                continue;
            }
            else if(s.substring(0,1).equals("*"))
            {
                if(stuff.size() < 2)
                {
                    throw new PostfixException("Malformed");
                }
                s = s.substring(1);
                int t = stuff.pop();
                stuff.push(stuff.pop() * t);
                continue;
            }
            else if(s.substring(0,1).equals("/"))
            {
                if(stuff.size() < 2)
                {
                    throw new PostfixException("Malformed");
                }
                s = s.substring(1);
                int t = stuff.pop();
                stuff.push(stuff.pop() / t);
                continue;
            }
            try
            {
                int n = 0;
                String k = s;
                for(int i = 0; i < s.length(); i++)
                {
                    if(k.substring(0,1).equals(" ")){;break;}
                    //System.out.println(k);
                    k = k.substring(1);
                    n++;
                }
                stuff.push(Integer.parseInt(s.substring(0,n)));
                s = s.substring(n);
                continue;
            }
            catch(NumberFormatException e)
            {
                System.out.println(e);
            }
            s = s.substring(1);
        }
        if(stuff.size() != 1){throw new PostfixException("Malformed");}
        System.out.println(stuff.peek());
        return stuff.pop();
    }
}
