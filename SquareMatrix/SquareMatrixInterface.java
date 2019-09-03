
/**
 * SquareMatrix.java  
 *
 * @author:
 * Assignment #:
 * 
 * Brief Program Description:
 * 
 *
 */
public interface SquareMatrixInterface
{
    int getValue(int r, int c);
    int getSize();
    void setValue(int r, int c, int val);
    void fillValue(int value);
    void makeZero();
    String toString();
    public abstract SquareMatrix add(SquareMatrix A);
    public abstract SquareMatrix subtract(SquareMatrix A);
    public abstract SquareMatrix copy();
}
