
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
/**
 * BOATYMCBOATFACE.java  
 *
 * @author:
 * Assignment #:
 * 
 * Brief Program Description:
 * 
 *
 */
public class BOATYMCBOATFACE extends CellAI
{
    public int[][] grid, tempGrid;
    private ArrayList<CellAI> currentAIs = new ArrayList<CellAI>();
    static boolean First = true;
    public static Location last;
    public String getAIName(){
        last = new Location(0,0);
        return "BoatyMcBoatFace";

    }

    //if a position holds -1, then it is dead, >= 0 means alive, value is id of CelLAI
    public Location select(Grid grids)
    {

        return failSafe(grids, getID());
    }

    private Location failSafe(Grid grid, int ID)
    {
        int priority = 0;
        Location re = new Location(0,0);
        for(int r = 0; r < grid.getRows(); r++) {
            for(int c = 0; c < grid.getCols(); c++) {
                Location U = new Location (r,c);
                 if(boxKiller(grid, r, c, getID()))
                {
                    //System.out.println("Killed");
                    return U;
                }
                else if(LineKiller(grid, r, c, getID()))
                {
                    //System.out.println("snipped");
                    return U;
                }
                  
                if(checkstillLife(grid, r, c, getID()))
                {
                    //System.out.println("Yeet");
                    return U;
                }
                  
                
                else if(!(U.getRow() - last.getRow() <= 5 && U.getCol() - last.getCol() <= 5)){
                    if(grid.getCell(r,c) == -1 && neighborCount(r,c,grid,ID) == 1 || neighborCount(r,c,grid,ID) == 2) {
                        //if(priority < 3){
                            //System.out.println("Defend");
                            last = U;
                            re = U;
                            //priority = 3;
                        //}

                    }
                    int kill = neighborCountE(r,c,grid,ID);
                    if(grid.getCell(r,c) == -1 &&  kill >= 3)
                    {
                        //if(priority < kill){
                            //System.out.println("Explode");
                            last = U;
                            re = U;
                            //priority = kill;
                        //}
                    }
                    else if(grid.getCell(r,c) != ID && neighborCountE(r,c,grid,ID) == 2)
                    {
                        //if(priority < 2){
                            //System.out.println("Attack");
                            last = U;
                            re = U;
                            //priority = 2;
                        //}
                    }
                }
            }
        }
        return re;
    }

    public int neighborCount(int row, int col, Grid grid, int ID) {
        int count = 0;
        for(int i = row - 1; i <= row + 1; i++) {
            for(int j = col - 1; j <= col + 1; j++) {
                if(i > 0 && j >0 && i < grid.getRows() && j < grid.getCols() && grid.getCell(i,j) == ID)
                {
                    count++;
                }
            }
        }
        return count;
    }

    public int neighborCountE(int row, int col, Grid grid, int ID) {
        int count = 0;
        for(int i = row - 1; i <= row + 1; i++) {
            for(int j = col - 1; j <= col + 1; j++) {
                if(i > 0 && j >0 && i < grid.getRows() && j < grid.getCols() && grid.getCell(i,j) != ID  && grid.getCell(i,j) != -1)
                {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean boxKiller(Grid grid, int r, int c, int ID)
    {
        if(grid.getCell(r,c) == -1 &&  r+ 1 < grid.getRows() && c + 2 < grid.getCols())
        {
            if((grid.getCell(r,c+1) != ID && grid.getCell(r,c+1) != -1) && (grid.getCell(r,c+2) != ID && grid.getCell(r,c+2) != -1) && (grid.getCell(r+1,c+1) != ID && grid.getCell(r+1,c+1) != -1) && (grid.getCell(r+1,c+2) != ID && grid.getCell(r+1,c+2) != -1))
                return true;
        }
        if(grid.getCell(r,c) == -1 &&  r+ 1 < grid.getRows() && c - 2 > 0)
        {
            if((grid.getCell(r,c-1) != ID && grid.getCell(r,c-1) != -1) && (grid.getCell(r,c-2) != ID && grid.getCell(r,c-2) != -1) && (grid.getCell(r+1,c-1) != ID && grid.getCell(r+1,c-1) != -1) && (grid.getCell(r+1,c-2) != ID && grid.getCell(r+1,c-2) != -1))
                return true;
        }
        return false;
    }
        
    public boolean LineKiller(Grid grid, int r, int c, int ID)
    {
        if(grid.getCell(r,c) != -1 && grid.getCell(r,c) != ID &&  r+ 1 < grid.getRows() && r-1 > 0)
        {
            if((grid.getCell(r-1,c) != -1 && grid.getCell(r-1,c) != ID) && (grid.getCell(r+1,c) != -1 && grid.getCell(r+1,c) != ID))
                return true;
        }
        if(grid.getCell(r,c) != -1 && grid.getCell(r,c) != ID &&  c+ 1 < grid.getCols() && c-1 > 0)
        {
            if((grid.getCell(r,c-1) != -1 && grid.getCell(r,c-1) != ID) && (grid.getCell(r,c+1) != -1 && grid.getCell(r,c+1) != ID))
                return true;
        }
        return false;
    }
    
    public boolean checkstillLife(Grid grid, int r, int c, int ID)
    {
        
        if( r+ 3 < grid.getRows() && c + 2 < grid.getCols() && c - 1 >0 &&grid.getCell(r,c) == -1 && grid.getCell(r+1, c) == ID && grid.getCell(r+1, c+1) == ID && grid.getCell(r+2,c-1) == ID && grid.getCell(r+2, c+2) == ID && grid.getCell(r+3,c) == ID && grid.getCell(r+3, c+1) == ID)
            return true;
        else if( r+ 2 < grid.getRows() && c + 3 < grid.getCols() && r - 1 >0 &&grid.getCell(r,c) == -1 && grid.getCell(r, c+1) == ID && grid.getCell(r+1, c+1) == ID && grid.getCell(r-1,c+2) == ID && grid.getCell(r+2, c+2) == ID && grid.getCell(r,c+3) == ID && grid.getCell(r+1, c+3) == ID)
            return true;
        return false;
    }
}
