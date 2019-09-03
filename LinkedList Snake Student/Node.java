/**
 * Blueprint for a node of the snake
 *
 */
public class Node {
    private final int x;
    private final int y;
    /**
     * Creates a new node
     * @param x the x position of the node
     * @param y the y position of the node
     */
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the x position of the node 
     * @return the x position of the node 
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y position of the node 
     * @return the y position of the node 
     */
    public int getY() {
        return y;
    }
}