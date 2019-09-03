import javax.swing.*;
import java.awt.*;
/**
 * The graphics aspect of the game that displays messages and draws the game
 */
public class GameView {
    //use Graphics API draw pics
    private JPanel canvas;
    private final Grid grid;
    public final int DEFAULT_NODE_SIZE = 15;
    private AudioPlayer music = new AudioPlayer("music.wav");
    /**
     * Makes a new GameView
     * @param the Grid of the game to use
     */
    public GameView(Grid grid) {
        this.grid = grid;
    }

    /**
     * Initializes the music and graphics
     */
    public void init() {
        music.play();
        canvas = new JPanel()
        {
            @Override
            public void paintComponent(Graphics graphics) {
                drawGridBackground(graphics);
                drawSnake(graphics, grid.getSnake());
                drawFood(graphics, grid.getPowerDown());
                drawBadFood(graphics, grid.getPowerUp());
            }
        } ;
    }

    /**
     * Draws the canvas (get it? Canvas?)
     */
    public void draw() {
        canvas.repaint();
    }

    /**
     * Returns the canvas
     * @return the canvas
     */
    public JPanel getCanvas() {
        return canvas;
    }

    /**
     * Draws the snake
     * @param Graphics the graphics instance to draw the snake
     * @param Snake the snake itself
     */
    public void drawSnake(Graphics graphics, Snake snake) {
        for (Node node : snake.getBody()) {
            drawSquare(graphics, node, Color.MAGENTA);
        }
    }

    /**
     * Draws the good food
     * @param Graphics the graphics instance to draw the food
     * @param Node the node on which to put the image
     */
    public void drawFood (Graphics graphics, Node squareArea) {
        //drawCircle(graphics, squareArea, Color.GREEN) - was used when food was a node
        int size = DEFAULT_NODE_SIZE;  
        graphics.drawImage(grid.getPowerDownImage(), squareArea.getX() * size, squareArea.getY() * size, size - 1, size - 1, null);
    }

    /**
     * Draws the bad food
     * @param Graphics the graphics instance to draw the food
     * @param Node the node on which to put the image
     */
    public void drawBadFood(Graphics graphics, Node squareArea) {
        int size = DEFAULT_NODE_SIZE;  
        graphics.drawImage(grid.getPowerUpImage(), squareArea.getX() * size, squareArea.getY() * size, size - 1, size - 1, null);
    }

    /**
     * Draws the grid background
     * @param Graphics the graphics instance to make the background
     */
    public void drawGridBackground(Graphics graphics) {
        for (int i = 0; i < grid.getWidth(); i++) {
            for (int j = 0; j < grid.getHeight(); j++) {
                drawSquare(graphics, new Node(i, j), Color.WHITE);
            }
        }
    }

    /**
     * Fills a node with a color
     * @param Graphics the graphics instance to fill the square
     * @param Node the node which needs to be filled
     * @param Color the color to fill the square
     */
    private void drawSquare(Graphics graphics, Node squareArea, Color color) {
        graphics.setColor(color);
        int size = DEFAULT_NODE_SIZE;                                 
        graphics.fillRect(squareArea.getX() * size, squareArea.getY() * size, size - 1, size - 1);
    }

    /**
     * Displays a game over message when the user loses and offers a play again
     */
    public void showGameOverMessage() {
        JOptionPane.showMessageDialog(null, "You Lose!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        
        if(JOptionPane.showConfirmDialog(null,"Would you like to play again?","Play Again?",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            SnakeApp snakeApp = new SnakeApp();
            music.stop();
            snakeApp.run();
        }
        else{
            System.exit(0);
        }
    }

    /**
     * Displays a winning message when the user wins and offers a play again
     */
    public void showWinMessage() {
        JOptionPane.showMessageDialog(null, "You Win!", "WINNER", JOptionPane.INFORMATION_MESSAGE);
        int n = JOptionPane.showConfirmDialog(null,"Would you like to play again?","Play Again?",JOptionPane.YES_NO_OPTION);
        if(JOptionPane.showConfirmDialog(null,"Would you like to play again?","Play Again?",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            SnakeApp snakeApp = new SnakeApp();
            music.stop();
            snakeApp.run();
        }
        else{
            System.exit(0);
        }
    }
}