
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
public class SquareMatrix implements SquareMatrixInterface
{
    private int[][] Matrix;
    public SquareMatrix(int n)
    {
        Matrix = new int[n][n];
    }
    public SquareMatrix(int[][] n)
    {
        Matrix = n;
    }
    public int getSize()
    {
        return Matrix.length;
    }
    public int getValue(int r, int c)
    {
        return Matrix[r][c];
    }
    public void setValue(int r, int c, int val)
    {
        Matrix[r][c] = val;
    }
    public void fillValue(int value)
    {
        for(int i = 0; i < Matrix.length; i++)
        {
            for(int j = 0; j<Matrix[i].length; j++)
            {
                Matrix[i][j] = value;
            }
        }
    }
    public void makeZero()
    {
        for(int i = 0; i < Matrix.length; i++)
        {
            for(int j = 0; j<Matrix[i].length; j++)
            {
                Matrix[i][j] = 0;
            }
        }
    }
    public String toString()
    {
        String s = "";
        for(int i = 0; i < Matrix.length; i++)
        {
            for(int j = 0; j<Matrix[i].length; j++)
            {
                s+= Matrix[i][j] + "\t";
            }
            s+= "\n";
        }
        return s;
    }
    public SquareMatrix add(SquareMatrix A)
    {
        for(int i = 0; i < Matrix.length; i++)
        {
            for(int j = 0; j<Matrix[i].length; j++)
            {
                Matrix[i][j] += A.getValue(i,j);
            }
        }
        return new SquareMatrix(Matrix);
    }
    public SquareMatrix subtract(SquareMatrix A)
    {
        for(int i = 0; i < Matrix.length; i++)
        {
            for(int j = 0; j<Matrix[i].length; j++)
            {
                Matrix[i][j] -= A.getValue(i,j);
            }
        }
        return new SquareMatrix(Matrix);
    }
    public SquareMatrix copy()
    {
        int[][] N = new int[Matrix.length][Matrix[0].length];
        for(int i = 0; i < Matrix.length; i++)
        {
            for(int j = 0; j<Matrix[i].length; j++)
            {
                N[i][j] = Matrix[i][j];
            }
        }
        return new SquareMatrix(N);
    }
}
