import java.util.LinkedList;
/**
 * The snake controls including movement and eating
 *
 */
public class Snake {
    //Declare the LinkedList here
    LinkedList<Node> head; 
    private static int pos = 0;
    public Snake(){
        //Instantiate the LinkedList Here
        head = new LinkedList<Node>();
    }
    
    /**
     * Checks to see if the snake's head and the powerDown are in the same location
     * @param the power to check
     * @return Whether or not the snake's head and the powerDown are in the same location
     */
    public boolean isPowerDown(Node powerDown) {
        if(powerDown.getX() == head.get(0).getX() && powerDown.getY() == head.get(0).getY())
        {
            return true;
        }
        return false;
    }
    
    /**
     * Checks to see if the snake's head and the bad food are in the same location
     * @param the bad food to check
     * @return Whether or not the snake's head and the bad food are in the same location
     */
    public boolean isPowerUp(Node powerUp) {
        if(powerUp.getX() == head.get(0).getX() && powerUp.getY() == head.get(0).getY())
        {
            return true;
        }
        return false;
    }
    
    /**
     * Moves the snake by having each node "follow" the one before it
     * @param direction the direction to move
     * @return the last node, the next to move
     */
    public Node move(Direction direction) {
        //How can you get access to the head?
        Node mov = head.get(pos);
        switch(direction) {
            case UP :
                //How will you move UP?
                head.add(0,new Node(mov.getX(), mov.getY()-1));
                head.remove(head.size()-1);
                break;
            case RIGHT :
                //How will you move RIGHT?
                head.add(0,new Node(mov.getX()+1, mov.getY()));
                head.remove(head.size()-1);
                break;
            case DOWN :
                //How will you move DOWN?
                head.add(0,new Node(mov.getX(), mov.getY()+1));
                head.remove(head.size()-1);
                break;
            case LEFT :
                //How will you move LEFT?
                head.add(0,new Node(mov.getX()-1, mov.getY()));
                head.remove(head.size()-1);
                break;
        }
        return head.get(head.size()-1);
    }
    
    /**
     * Returns the head of the snake
     * @return the head of the snake
     */
    public Node getHead() {
        return head.get(0);
    }
    
    /**
     * Adds a node to the tail
     * @param Node the node to add to the snake
     * @return the node added
     */
    public Node addTail(Node node) {
        //Complete this method
        head.add(node);
        return node;
    }
    
    /**
     * Cuts a node from the tail
     * @param Node the node "removed" (thought it's more for return purposes)
     * @return the node removed
     */
    public Node cutTail(Node node) {
        //Complete this method
        return head.remove(head.size() - 1);
    }
    
    /**
     * Returns the body of the snake
     * @return the nodes of the body of the snake in a LinkedList
     */
    public LinkedList<Node> getBody() {
        //Complete this method
        LinkedList<Node> body = (LinkedList<Node>)head.clone();
        body.remove(0);
        body.remove(body.size()-1);
        return body;
    }
}