import javax.swing.*;
import java.awt.*;
/**
 * LinkedList Sname
 */
public class SnakeApp implements Runnable {

    private final int DEFAULT_GRID_WIDTH = 30;
    private final int DEFAULT_GRID_HEIGHT = 30;
    private GameView gameView;
    private GameController gameController;
    
    /**
     * Runs the game by setting up the basic window
     */
    public void run() {
        JFrame window = new JFrame("Snake Game");               //creat game window

        Container contentPane = window.getContentPane();
        // use Grid initialize the gamaView
        Grid grid = new Grid(DEFAULT_GRID_WIDTH, DEFAULT_GRID_HEIGHT);
        gameView = new GameView(grid);
        JOptionPane.showMessageDialog(null, "Eat the power ups with the arrow keys on your keyboard but avoid the power downs.\nHit yourself or the wall, and you lose!", "Instructions", JOptionPane.INFORMATION_MESSAGE);
        gameView.init();
        
        // set JPanel's size
        gameView.getCanvas().setPreferredSize(new Dimension(DEFAULT_GRID_WIDTH * gameView.DEFAULT_NODE_SIZE, 
                DEFAULT_GRID_HEIGHT * gameView.DEFAULT_NODE_SIZE));
        // add JPanel to windows
        contentPane.add(gameView.getCanvas(), BorderLayout.CENTER);

        // draw grid and snake
        window.pack();
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        gameController = new GameController(grid, gameView);
        window.addKeyListener(gameController);

        // start the thread
        new Thread(gameController).start();

    }
    /**
     * Calls run()
     */
    public static void main(String[] args) {
        SnakeApp snakeApp = new SnakeApp();
        snakeApp.run();
    }
}