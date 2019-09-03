
/**
 * Priority.java  
 *
 * @author:
 * Assignment #:
 * 
 * Brief Program Description:
 * 
 *
 */
public class Priority
{
    public static int calc(int age, boolean insurance, int severity)
    {
        int priority = 5;
        int dis = Math.abs(age - 20);
        if(dis > 20){priority -= 2;}
        else if(dis < 10){priority +=1;}
        else{severity = (int)(severity*(1.2));}
        if(!insurance){priority -=3;}
        priority += severity;
        return priority;
    }
}
