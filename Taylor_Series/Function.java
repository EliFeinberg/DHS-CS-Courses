import java.util.*;
/**
 * Function.java  
 *
 * @author:
 * Assignment #:
 * 
 * Brief Program Description:
 * Creates a list of Terms repersenting a polynominal
 */
public class Function
{
    ArrayList<Term> Func;
    public Function()
    {
        Func = new ArrayList<Term>();
    }
    public void add(double Coefficinet, int powers)
    {
        Term n = new Term();
        n.coefficient = Coefficinet;
        n.power = powers;
        Func.add(n);
    }
    /**
     * Runs through the list of polynomials substituting using X 
     */
    public double approximate(double X)
    {
        double n = 0;
        for(int i = 0; i< Func.size(); i++)
        {
            double s = (Math.pow(X,Func.get(i).power));
            s *= Func.get(i).coefficient;
            n += s;
        }
        return n;
    }
}
