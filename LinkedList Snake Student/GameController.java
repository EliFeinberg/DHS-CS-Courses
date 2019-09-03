import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * The class that controls the snake and checks for a win/loss
 *
 */
public class GameController implements Runnable, KeyListener{

    private final Grid grid;
    private final GameView gameView;

    private boolean running;
    /**
     * Creates a new GameController
     * @param Grid the grid used by the game
     * @param GameView the GameView to put the game onto
     */
    public GameController(Grid grid, GameView gameView) {
        this.grid = grid;
        this.gameView = gameView;
        this.running = true;
    }
    /**
     * Keeps the game running as long as there isn't a reason to stop the game
     */
    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(150 + grid.getScore() / 5 * 30);                     //DEFAULT_MOVE_INTERVAL
            } catch (InterruptedException e) {
                break;
            }
            grid.isDirectionChanged = false;
            if (grid.isAtOne()){
                gameView.showWinMessage();
                running = false;
            }
            if (grid.nextRound()) {
                gameView.draw();
            }
            else {
                gameView.showGameOverMessage();
                running = false;
            }
        }
        }
    /**
      * Checks to see if a key is pressed and changes the direction if a valid arrow is
      * @param KeyEvent any time a key is pressed
      */
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (grid.isDirectionChanged == false) {
            switch (keyCode) {
                case KeyEvent.VK_UP :
                    grid.changeDirection(Direction.UP);
                    break;
                case KeyEvent.VK_RIGHT :
                    grid.changeDirection(Direction.RIGHT);
                    break;
                case KeyEvent.VK_DOWN :
                    grid.changeDirection(Direction.DOWN);
                    break;
                case KeyEvent.VK_LEFT :
                    grid.changeDirection(Direction.LEFT);
                    break;
                case KeyEvent.VK_SPACE :
                    break;
            }
        }
    }
    /**
     * A method in order to implement KeyListener
     */
    @Override
    public void keyReleased(KeyEvent e) {}
    /**
     * A method in order to implement KeyListener
     */
    @Override
    public void keyTyped(KeyEvent e) {}
}