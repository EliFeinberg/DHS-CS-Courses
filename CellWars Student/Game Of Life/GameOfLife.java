import java.util.*;
/**
 * The model for John Conway's Game of Life.
 *
 * @author Rick Mercer
 *
 * This class has all needed methods as stubs. Comments explain each method.
 *
 */
public class GameOfLife {
    private boolean[][] grid;
    /**
     * Write the constructor so it takes two integer arguments to represent the
     * number of rows and columns in the game of life. The constructor creates a
     * society with no cells but space to store rows*cols cells.
     *
     * @param rows
     *            The height of the grid that shows the cells.
     * @param cols
     *            The width of the grid that shows the cells.
     */
    public GameOfLife(int rows, int cols) {
        grid = new boolean[rows][cols];
        for(int i = 0; i < rows; i++)
            for(int j = 0; j < cols; j++)
                grid[i][j] = false;
    }

    /**
     * Return the number of rows, which can be indexed from 0..numberOfRows()-1.
     *
     * @return The height of the society.
     */
    public int numberOfRows() {
        return grid.length;
    }

    /**
     * The number of columns, which can be indexed from 0..numberOfColumns()-1.
     *
     * @return The height of the society.
     */
    public int numberOfColumns() {
        return grid[0].length;
    }

    /**
     * Place a new cell in the society. Precondition: row and col are in range.
     *
     * @param row
     *            The row to grow the cell.
     * @param col
     *            The column to grow the cell.
     */
    public void growCellAt(int row, int col) {
        grid[row][col] = true;
    }

    /**
     * 5) Return true if there is a cell at the given row and column. Return
     * false if there is none at the specified location.
     *
     * @param row
     *            The row to check.
     * @param col
     *            The column to check.
     * @return True if there is a cell at the given row or false if none
     */
    public boolean cellAt(int row, int col) {
        return grid[row][col];
    }

    /**
     * Return one big string of cells to represent the current state of the
     * society of cells (see output below where '.' represents an empty space
     * and 'O' is a live cell. There is no need to test toString. Simply use it
     * to visually inspect if needed. Here is one sample output from toString:
     *
     * GameOfLife society = new GameOfLife(4, 14); society.growCellAt(1, 2);
     * society.growCellAt(2, 3); society.growCellAt(3, 4);
     * System.out.println(society.toString());
     *
     * Output .............. ..O........... ...O.......... ....O.........
     *
     * @return A textual representation of this society of cells.
     */
    @Override
    public String toString() {
        String str = "";
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++)
                if(!grid[i][j])
                    str += ".";
                else
                    str += "0";
            str += "\n";
        }
        return str;
    }

    /**
     * Count the neighbors around the given location. Use wraparound. A cell in
     * row 0 has neighbors in the last row if a cell is in the same column, or
     * the column to the left or right. In this example, cell 0,5 has two
     * neighbors in the last row, cell 2,8 has four neighbors, cell 2,0 has four
     * neighbors, cell 1,0 has three neighbors. The cell at 3,8 has 3 neighbors.
     * The potential location for a cell at 4,8 would have three neighbors.
     *
     * .....O..O
     * O........
     * O.......O
     * O.......O
     * ....O.O..
     *
     *
     * The return values should always be in the range of 0 through 8.
     *
     * @return The number of neighbors around any cell using wrap around.
     */
    public int neighborCount(int row, int col) {
        int count = 0;
        for(int i = row - 1; i <= row + 1; i++) {
            for(int j = col - 1; j <= col + 1; j++) {
                if(i != row || j != col) {
                    int newRow;
                    int newCol;
                    if(i == -1)
                        newRow = grid.length - 1;
                    else if(i == grid.length)
                        newRow = 0;
                    else
                        newRow = i;
                    if(j == -1)
                        newCol = grid[0].length - 1;
                    else if(j == grid[0].length)
                        newCol = 0;
                    else
                        newCol = j;
                    if(grid[newRow][newCol]) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * Update the state to represent the next society. Typically, some cells
     * will die off while others are born.
     */
    public void update() {
        boolean[][] newGrid = new boolean[grid.length][grid[0].length];
        for(int i = 0; i < grid.length; i++)
            for(int j = 0; j < grid[0].length; j++) {
                int neighbors = neighborCount(i, j);
                if(grid[i][j]) {
                    if(neighbors == 2 || neighbors == 3)
                        newGrid[i][j] = true;
                }
                else {
                    if(neighbors == 3)
                        newGrid[i][j] = true;
                }
            }
        grid = newGrid;
    }
}