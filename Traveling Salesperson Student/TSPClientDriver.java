import java.io.*;
import java.util.*;
import java.awt.*;

public class TSPClientDriver 
{
    static Calendar calendar;
    
    public static void main(String[] args) throws FileNotFoundException
    {   
        // Make a new Tour
        Tour tour = new Tour();
        
        //Open file for reading
        Scanner input = new Scanner(new File("dataFiles","tsp1000.txt"));
        
        int w = input.nextInt(); //first two lines are width/height maxes
        int h = input.nextInt(); //might want to use for Graphics scaling

        // get start time
        long start = calendar.getInstance().getTimeInMillis();

        // add points to the tour from the file.
        while(input.hasNext())
        {
            //tour.add(new Point(input.nextDouble(), input.nextDouble()));
            //tour.insertNearest(new Point(input.nextDouble(), input.nextDouble()));
            tour.insertSmallest(new Point(input.nextDouble(), input.nextDouble()));
        }
        
        // get stop time
        long stop = calendar.getInstance().getTimeInMillis();
        // calculate elapsed time
        double elapsedTime = (double) (stop - start) / 1000;
        
        
        //Print stuff out about it:
        System.out.println("Created Tour with " + tour.size() + " points.");
        System.out.println("Tour has distance " + tour.distance());
        System.out.println("Here is the tour in order of points visited");
        tour.print();
        
        // graph the points
        DrawingPanel panel = new DrawingPanel(w,h);
        Graphics g = panel.getGraphics();
        tour.draw(g);
        g.setColor(Color.RED);
        g.drawString(String.format("Tour with %,d",tour.size()) + " points",10,20);
        g.drawString(String.format("Tour distance: %,.2f",tour.distance()), 10, 40);
        g.drawString(String.format("Elapsed time: %4.2f seconds", elapsedTime), 10,60);

    }
}