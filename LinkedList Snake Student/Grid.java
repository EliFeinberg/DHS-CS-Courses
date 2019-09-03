import java.util.Arrays;
import java.util.Random;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import java.io.*;
/**
 * The base of the game: the playing grid, powerUps, powerDowns, snake, and base of movement and rules
 *
 */
public class Grid  {
    private boolean isCovered[][];
    private final int width;
    private final int height;
    private int scores = 0;

    private Snake snake;
    private Node powerDown;
    private Node powerUp;

    private BufferedImage powerDownImage;
    private BufferedImage powerUpImage;
    
    private Direction snakeDirection = Direction.LEFT;
    public boolean isDirectionChanged = false;

    private AudioPlayer bite = new AudioPlayer("bite.wav");
    private AudioPlayer uhoh = new AudioPlayer("uhoh.wav");
    /**
     * Creates a new grid
     * @param width the width of the grid
     * @param height the height of the grid
     */
    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        isCovered = new boolean[width][height];
        try {
            powerDownImage = ImageIO.read(new File("powerDownImage.png"));
        }
        catch (IOException e){}
        try {
            powerUpImage = ImageIO.read(new File("powerUpImage.png"));
        }
        catch (IOException e){}
        initSnake();
        createPowerDown();
        createPowerUp();
    }

    /**
     * Creates a new snake with 15 nodes
     * @return the initial snake
     */
    private Snake initSnake() {   
        snake = new Snake();
        for (int i = 0; i < 15; i++) {
            snake.addTail(new Node(i + width / 2, height / 2));
            isCovered[i + width / 2][height / 2] = true;
        }
        return snake;
    }

    /**
     * Creates a powerDown and places it randomly on the grid
     * @return the node that is that good food
     */
    public Node createPowerDown() {
        int x;
        int y;
        do {
            x = new Random().nextInt(width);
            y = new Random().nextInt(height);
        } while (isCovered[x][y] == true);
        powerDown = new Node(x, y);
        return powerDown;
        
    }

    /**
     * Creates a power up and places it randomly on the grid
     * @return the node that is that bad food
     */
    public Node createPowerUp() {
        int x;
        int y;
        do {
            x = new Random().nextInt(width);
            y = new Random().nextInt(height);
        } while (isCovered[x][y] == true);
        powerUp = new Node(x, y);
        return powerUp;
    }

    /**
     * After every move, checks to see if the snake can keep playing
     * @return whether or not the snake can keep playing
     */
    public boolean nextRound() {  
        if (isMoveValid(snakeDirection)) {
            Node move = snake.move(snakeDirection);
            if (snake.isPowerDown(powerDown)) { 
                //if ate food, add the Node moved at tail
                snake.cutTail(move);
                bite.play();
                createPowerDown();
                scores--;
            } else 
            {
                isCovered[move.getX()][move.getY()] = false;
            }
            if (snake.isPowerUp(powerUp)) { 
                //if ate food, add the Node moved at tail
                snake.addTail(move);
                uhoh.play();
                createPowerUp();
                createPowerDown();
                scores++;
            } 
            else {
                isCovered[move.getX()][move.getY()] = false;
            }
            return true;
        }
        return false;
    }

    /**
     * Checks to see if the snake is down to one node
     * @return whether or not the snake is down to one node
     */
    public boolean isAtOne(){
        if(snake.getBody().size() == 1){
            return true;
        }
        return false;
    }

    /**
     * Checks to see if the snake's move is valid (if the snake lost, it is invalid)
     * Begins the move of the snake
     * @param Direction the direction the snake will move
     * @return whether or not the snake's move is valid
     */
    private boolean isMoveValid(Direction direction) {
        int headX = snake.getHead().getX();
        int headY = snake.getHead().getY();
        switch(direction) {
            case UP :
            headY--;
            break;
            case RIGHT :
            headX++;
            break;
            case DOWN :
            headY++;
            break;
            case LEFT :
            headX--;
            break;
        }
        if (headX < 0 || headX >= width || headY < 0 || headY >= height) return false;
        if (isCovered[headX][headY] == true) return false;
        isCovered[headX][headY] = true;
        return true;
    }

    /**
     * Changes the direction of the snake if it is an allowed switch
     * @param Direction the new direction to switch
     */
    public void changeDirection(Direction newDirection) {
        if (snakeDirection.compatibleWith(newDirection)) {
            snakeDirection = newDirection;
            isDirectionChanged = true;
        }
    }

    /**
     * Returns the snake
     * @return the snake
     */
    public Snake getSnake() { return snake; }
    
    /**
     * Returns the powerDown Node
     * @return the powerDown Node
     */
    public Node getPowerDown() { return powerDown; }
    
    /**
     * Returns the powerDown Image
     * @return the powerDown Image
     */
    public Image getPowerDownImage() { return powerDownImage; }
    
    /**
     * Returns the powerUp node
     * @return the powerUp node
     */
    public Node getPowerUp() { return powerUp; }
    
    /**
     * Returns the powerUp
     * @return the powerUpImage
     */
    public Image getPowerUpImage() { return powerUpImage; }
    
    /**
     * Returns the width of the board
     * @return the width of the board
     */
    public int getWidth() { return width; }
    
    /**
     * Returns the height of the board
     * @return the height of the board
     */
    public int getHeight() { return height; }
    
    /**
     * Returns the score (the size of the snake, used for calculating speed)
     * @return the score
     */
    public int getScore() { return scores; }
}