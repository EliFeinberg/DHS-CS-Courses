import javax.swing.JFrame;
import java.awt.*;
/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Main
{
    static int angle = 0;
    static JFrame f = new JFrame()
        {
            public void paint(Graphics g)
            {
                Koch(3, 100, g);
                
            }
        };
    static int X = 500, Y = 500;
    /**
     * Constructor for objects of class Main
     */
    public static void main()
    {
        f.setSize(1000, 1000);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //f.repaint();
    }

    public static void Koch(int level, int length, Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        if(level < 1)
        {
            g2d.drawLine(X,Y, (int)(X + length*Math.cos(angle*Math.PI/180)),(int)(Y + length*Math.sin(angle*Math.PI/180)));
            X += length*Math.cos(angle*Math.PI/180);
            Y += length*Math.sin(angle*Math.PI/180);
        }
        else
        {
            Koch(level-1, length/3, g);
            angle -= 60;
            Koch(level-1, length/3, g);
            angle += 120;
            Koch(level-1, length/3, g);
            angle -= 60;
            Koch(level-1, length/3, g);

        }
    }
}
