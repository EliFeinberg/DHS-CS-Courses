import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.awt.Color;
import java.util.ArrayList;

import java.io.IOException;
/**
 * Write a description of class Tour here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tour{

    private static int Side = 45;
    private static JButton[][] Board = new JButton[Side][Side];
    private static JFrame f;

    private final static int[][] moves = {{1,-2},{2,-1},{2,1},{1,2},{-1,2},
            {-2,1},{-2,-1},{-1,-2}};
    private static int[][] grid = new int[Side][Side];
    private static int total = Side*Side;
    private static ArrayList<int[]> MOVESMADE = new ArrayList<int[]>(total);
    private static ArrayList<ArrayList<int[]>> POSSIBLEMOVESMADE = new ArrayList<ArrayList<int[]>>(total);
    public static void main()
    {
        for(int i = 0; i < grid.length; i++)
        {
            for(int k = 0; k<grid[i].length; k++)
            {
                grid[i][k] = -1;
            }
        }
        f =new JFrame();
        f.setLayout(new GridLayout(Side,Side));
        for(int i = 0; i<Side; i++)
        {
            for(int j = 0; j<Side ; j++)
            {
                Board[i][j] = new JButton();
                if((j+1)% 2 == 0 && (i+1)%2 ==1 ){Board[i][j].setBackground(Color.BLACK);}
                else if((j+1)% 2 == 1 && (i+1)%2 == 0 ){Board[i][j].setBackground(Color.BLACK);}
                f.add(Board[i][j]);
            }
        }
        f.setSize(800,800);
        f.setVisible(true);
        solve(0,0,1);
        //printGrid();
    }

    public static void solve(int X, int Y, int move)
    {
        if(move > total)
            return;
        else
        {
            try{Thread.sleep(2);}
            catch(Exception e){}
            Board[Y][X].setBackground(Color.BLUE);

            grid[Y][X] = move;
            ArrayList<int[]> Possible = new ArrayList<int[]>();
            for(int i = 0; i < 8; i++)
            {
                int Temp = FutureMovNum(X + moves[i][0], Y + moves[i][1], move);
                if(Temp > 0 && isValid(X + moves[i][0], Y + moves[i][1]))
                {
                    Possible.add(new int[]{i,Temp});
                }
                
            }
            if(Possible.size() == 0 && move < total)
            {
            }
            else if(Possible.size() > 0){ 
                int indexed = NewMov(Possible);

                //System.out.println(move);
                //System.out.println(X +" " + Y);
                //System.out.println(Possible.size());
                Possible.clear();
                solve(X + moves[indexed][0], Y + moves[indexed][1], ++move);
            }
        }
    }

    public static int NewMov(ArrayList<int[]> Possible)
    {
        int minInd = 0;
        for(int i = 0; i < Possible.size(); i++)
        {
            if(Possible.get(minInd)[1] > Possible.get(i)[1])
            {
                minInd = i;
            }
            else if(Possible.get(minInd)[1] == Possible.get(i)[1] && Possible.get(minInd)[0] > Possible.get(i)[0])
            {
                minInd = i;
            } 
        }
        return Possible.get(minInd)[0];
    }

    public static boolean isValid(int X, int Y)
    {
        if(X >= 0 && X < Side && Y >= 0 && Y < Side && grid[Y][X] == -1)
            return true;
        return false;
    }

    public static int FutureMovNum(int X, int Y,int move)
    {
        if(move == total - 1)
        {
            return 1;
        }
        int numMov = 0;
        for (int k = 0; k <= 7; k++)
        {
            if(isValid(X + moves[k][0],Y + moves[k][1]))
            {
                numMov++;
            }
        }
        return numMov;
    }

    public static void printGrid()
    {
        for(int i = 0; i < grid.length; i++)
        {
            for(int k = 0; k<grid[i].length; k++)
            {
                System.out.print(grid[i][k] + "\t");
            }
            System.out.println();
        }
    }
}
