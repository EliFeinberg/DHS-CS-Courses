import java.util.*;
/**
 * TestThirteenException.java 
 *
 * @author:
 * Assignment #:
 * 
 * Brief Program Description:
 * 
 *
 */

public class TestThirteenException 
{
    public static void main() throws ThirteenException
    {
        while(true){
            Scanner s = new Scanner(System.in);
            String st = s.nextLine();
            try{
            if(st.length() != 13)
            {
                System.out.println(st);
            }
            else{
                throw new ThirteenException();
                
            }
        }
        catch(ThirteenException e)
        {
            System.out.println(e);
        }
        }
    }
}
