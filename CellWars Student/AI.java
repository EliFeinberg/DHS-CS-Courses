import java.util.*;

/**
 * Write a description of class Terminator here.
 *
 * @author (Javier Espinosa)
 * @version (a version number or a date)
 */
public class AI extends CellAI{
    public String getAIName(){
        return "FreshmanBedtimeAI";

    }

    //if a position holds -1, then it is dead, >= 0 means alive, value is id of CelLA
    public Location select(Grid grid){
        int[][] temp = new int[grid.getRows()][grid.getCols()];
        Internal n1 = new Internal(grid);
        n1.update();
        int[] cells = n1.cells();
        for(int i =0;i<cells.length;i++){
            if(cells[i]!=-1){
                if(i!=getID()){
                    if(cells[i]==4){
                        return n1.kill4();
                    }
                }
            }
        }

        for(int r =0;r<grid.getRows();r++){
            for(int c =0;c<grid.getCols();c++){
                Internal n = new Internal(grid);
                if(n.grid[r][c]==-1)
                    n.grid[r][c]=getID();
                else n.grid[r][c]=-1;
                n.update();
                temp[r][c]=n.score();
            }
        }
        int[] loc={0,0,temp[0][0]};
        for(int r =0;r<temp.length;r++){
            for(int c =0;c<temp[0].length;c++){
                if(loc[2]<temp[r][c]){
                    loc[0]=r;
                    loc[1]=c;
                    loc[2]=temp[r][c];
                }
            }
        }

        return new Location(loc[0],loc[1]);
    }

    class Internal{
        int[][] grid;
        public Internal(Grid g){
            this.grid = new int[g.getRows()][g.getCols()];
            for(int r =0;r<grid.length;r++){
                for(int c =0;c<grid.length;c++){
                    this.grid[r][c]=g.getCell(r,c);
                }
            }
        }

        public Location kill4(){
            int r =0;
            int c =0;
            boolean run = true;
            while(r<grid.length && run){
                while(c<grid[0].length && run){
                    if(grid[r][c]!=-1 && grid[r][c]!=getID()){
                        run=false;
                    }else {
                        r++;
                        c++;
                    }
                }
            }
            for(int row = r-1; row<=r+2;row++){
                for(int col = c-1; row<=c+2;col++){
                    if(row>=0 && col>=0 && row<grid.length && col<grid.length && grid[row][col]==-1)
                        return new Location(row,col);
                }
            }
            return null;
        }

        public int[] cells(){
            int[] group= new int[4];
            for(int i =0;i<group.length;i++){
                group[i]=0;
            }
            for(int r =0;r<grid.length;r++){
                for(int c =0;c<grid.length;c++){
                    if(grid[r][c]!=-1)
                        group[grid[r][c]]+=1;
                }
            }
            return group;
        }

        public int score(){
            int[] group= cells();
            int hold =0;
            for(int i =0;i<group.length;i++){
                if(i!=getID() && group[i]!=-1){
                    hold-=group[i];
                }else hold+=group[i];
            }
            return hold;
        }

        public void update() {
            int[][] tempGrid = new int[grid.length][grid[0].length];
            for(int i = 0; i < grid.length; i++) {
                for(int j = 0; j < grid[i].length; j++) {
                    int count = GridFunctions.getNeighbors(i, j, new Grid(grid));
                    if(grid[i][j] != -1) {
                        if(count > 3 || count < 2) 
                            tempGrid[i][j] = -1;
                        else 
                            tempGrid[i][j] = GridFunctions.mostCommonNeighbor(i, j, new Grid(grid));
                    } else {
                        if(count != 3) 
                            tempGrid[i][j] = -1;
                        else tempGrid[i][j] = GridFunctions.mostCommonNeighbor(i, j, new Grid(grid));
                    }
                }
            }
            grid= tempGrid;
        }
    }
}
