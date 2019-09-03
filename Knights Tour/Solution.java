//Knight's Tour
import java.io.*;
import java.util.*;
public class Solution {
    // X and Y corresponding move position clock wise starting at index of 0
    static int x[] =  { 2, 1, -1, -2, -2, -1, 1, 2 };
    static int y[] = { 1, 2, 2, 1, -1, -2, -2, -1  };
    //static int[] moveT[]= {{2,1},{1,2},{-1,2},{-2,1},{-2,-1},{-1,-2},{1,-2},{2,-1}};
    static int N;
    public static void main(String args[]) {
        N = 8; //dimension of the chess board
        solveKnightTour(N);
    }

    public static void solveKnightTour(int N) {
        int solution[][] = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                solution[i][j] = -1;
            }
        }
        //solution[0][0] = 0;

        solve(0, 0, 1, solution);
        printSolution(solution);

    }

    static void solve(int X, int Y, int moves, int sol[][]) {
        ArrayList<Integer> MovAmount = new ArrayList<Integer>(8);
        int next_X = 0, next_Y = 0;
        sol[X][Y] = moves;
        if (moves == (N * N)+1)
            System.out.println("DONE");
        else{

            for (int k = 0; k <= 7; k++) {
                next_X = X + x[k];
                next_Y = Y + y[k];
                if (isValid(next_X, next_Y, sol)) {
                    
                    MovAmount.add(FutureMovNum(next_X,next_Y, sol));
                }
            }
            int minInd = 0;
            for (int k = 1; k < MovAmount.size(); k++)
            {
                if(MovAmount.get(minInd) > MovAmount.get(k))
                    minInd = k;
            }
            moves++;
            solve(next_X, next_Y, moves, sol);
        }
    }

    static int FutureMovNum(int X, int Y, int sol[][])
    {
        int numMov = 0;
        for (int k = 0; k <= 7; k++)
        {
            if(isValid( X + x[k], Y + y[k], sol))
            {
                numMov++;
            }
        }
        return numMov;
    }

    static boolean isValid(int x, int y, int sol[][]) {
        if (x >= 0 && x < N && y >= 0 && y < N && sol[x][y] == -1)
            return true;

        return false;
    }

    public static void printSolution(int[][] sol) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(sol[i][j] + "\t");
            }

            System.out.println();
        }
    }
}