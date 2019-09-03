import java.util.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.JToolBar;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
/*
 * Using the JFreeChart API to graph functions on the XY plane.
 * Graphs two series a function and its approximation using its Macluarian Series
 * (Taylor Series centered at 0)
 */
public class XYSeriesDemo extends ApplicationFrame {
    final static XYSeriesDemo demo = new XYSeriesDemo("Taylor Approximations of Functions");
    final XYSeries series;
    final XYSeries seriesT;
    Function Tay;
    static ArrayList<Coordinate> Deriv, Func;
    static double START = 0.1;
    static double END = 5;
    static int derivative = 3;
    final static double Incr = 0.1;
    static boolean Set;
    static Krack x;
    JComboBox sims;
    /**
     * Creates series and seriesT which map the function on the XY-plane.
     * 
     * @sims Allows you to choose a function
     * @Start Choose starting value to graph
     * @end Choose ending value to graph
     * @deriv Choose amount of derivatives to use in the approximation of functions
     * @Run Runs the simulation
     */
    public XYSeriesDemo(final String title) {

        super(title);
        series = new XYSeries("Approximate Function");
        seriesT = new XYSeries("Actual Function");
        Tay = new Function();

        final XYSeriesCollection data = new XYSeriesCollection(series);
        data.addSeries(seriesT);
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "\t",
                "X", 
                "Y", 
                data,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
            );

        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500*2, 270*2));
        String[] y = {"Sine", "Cosine", "e^x"};
        JComboBox sims = new JComboBox(y);
        JSpinner Start = new JSpinner();
        JSpinner end = new JSpinner();
        JSpinner deriv = new JSpinner();
        JButton Run = new JButton("Run");
        Start.setName("Start");
        Start.setValue(0);
        end.setName("End");
        end.setValue(5);
        deriv.setValue(3);

        Run.addActionListener( new ActionListener()
            {
                /**
                 * Clears the former function from the graph and
                 * pulls the current start, end, and derivative
                 * Then runs the simulation.
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    demo.series.clear();
                    demo.seriesT.clear();
                    x = new Krack();
                    x.set(sims.getSelectedIndex());
                    START = (int)(Start.getValue());
                    END = (int)(end.getValue());
                    derivative = (int)deriv.getValue();
                    x.start();
                }
            }
        );

        sims.getSelectedIndex();
        chartPanel.add(sims);
        chartPanel.add(new JLabel("Start:"));
        chartPanel.add(Start);

        chartPanel.add(new JLabel("End:"));
        chartPanel.add(end);

        chartPanel.add(new JLabel("Derivative:"));
        chartPanel.add(deriv);
        chartPanel.add(Run);
        setContentPane(chartPanel);

    }

    /**
     * Adds to approximated functions
     */
    public void adds(double x, double y)
    {
        series.add(x, y);
    }

    /**
     * Adds to actual function
     */
    public void addT(double x, double y)
    {
        seriesT.add(x, y);
    }

    /**
     * retuns function for approximation
     */
    public Function getFunction()
    {
        return Tay;
    }

    /**
     * Sets simulation to approximate sine by clearing all data currently added then adding
     * polynomials to a list based on the basic sine power series. Then uses the Calc function
     * to setup and approximate a function between the interval start and end. After a seperate
     * Function Func uses java's prebuilt math library to plot the function.
     */
    public static void setSine()
    {
        Func.clear();
        Deriv.clear();
        demo.repaint();
        demo.Tay.Func.clear();

        Func = new ArrayList<Coordinate>();
        for(int i = 0; i< derivative; i++)
        {
            double coef = Math.pow(-1.0, i);
            coef /= Factorial(2*i+1);
            demo.Tay.add(coef, 2*i+1);
        }
        Deriv = Calc(START, END, Incr, demo);
        for(double i = START; i < END; i += Incr)
        {
            Func.add(new Coordinate(i,Math.sin(i) ));
        }
        Set = true;
    }
    /**
     * Sets simulation to approximate sine by clearing all data currently added then adding
     * polynomials to a list based on the basic e^x power series. Then uses the Calc function
     * to setup and approximate a function between the interval start and end. After a seperate
     * Function Func uses java's prebuilt math library to plot the function.
     */
    public static void setex()
    {
        Func.clear();
        Deriv.clear();
        demo.repaint();
        demo.Tay.Func.clear();
        System.out.println(Math.E);
        Func = new ArrayList<Coordinate>();
        for(int i = 0; i< derivative; i++)
        {
            double coef = Factorial(i);
            demo.Tay.add(1/coef, i);
        }
        Deriv = Calc(START, END, Incr, demo);
        for(double i = START; i < END; i += Incr)
        {
            Func.add(new Coordinate(i,Math.pow(Math.E, i) ));
        }
        Set = true;
    }
    /**
     * Sets simulation to approximate sine by clearing all data currently added then adding
     * polynomials to a list based on the basic cosine power series. Then uses the Calc function
     * to setup and approximate a function between the interval start and end. After a seperate
     * Function Func uses java's prebuilt math library to plot the function.
     */
    public static void setCosine()
    {
        Func.clear();
        Deriv.clear();
        demo.repaint();
        demo.Tay.Func.clear();

        Func = new ArrayList<Coordinate>();
        for(int i = 0; i< derivative; i++)
        {
            double coef = Math.pow(-1.0, i);
            coef /= Factorial(2*i);
            demo.Tay.add(coef, 2*i);
        }
        Deriv = Calc(START, END, Incr, demo);
        for(double i = START; i < END; i += Incr)
        {
            Func.add(new Coordinate(i,Math.cos(i) ));
        }
        Set = true;
    }

    /**
     * Starting point for the demonstration application.
     */
    public static void main(final String[] args) {
        Set = false;
        x = new Krack();
        //x.start();
    }
    
    public void Start(int SIM)
    {
        x = new Krack();
        x.set(SIM);
        //x.start();
    }
    /**
     * Iteratively caculates x factorial (x!)
     */
    public static int Factorial(int x)
    {
        int sum = 1;
        for(int i = x; i>0; i--){sum *= i;}
        return sum;
    }
    /**
     * Calculates the approximation function
     */
    public static ArrayList<Coordinate> Calc(double 
    start, double end
    , double increment,XYSeriesDemo demo)
    {
        ArrayList<Coordinate> Deriv = new ArrayList<Coordinate>();
        for(double i = start; i < end; i += increment)
        {
            Deriv.add(new Coordinate(i,demo.getFunction().approximate(i) ));
        }
        return Deriv;
    }
    /*
     * Creates a seperate thread to run the simulation on
     */
    static class Krack extends Thread
    {
        int SIM = 0;
        public Krack()
        {
            demo.pack();
            RefineryUtilities.centerFrameOnScreen(demo);
            Deriv = Calc(START, END, Incr, demo);
            Func = new ArrayList<Coordinate>();
            demo.setVisible(true);
            demo.repaint();
        }
        /*
         * Picks the function used for simulation
         */
        public void run()
        {

            switch(SIM)
            {
                case 0:
                setSine();
                break;
                case 1:
                setCosine();
                break;
                case 2:
                setex();
                break;
            }
            runn();

        }
        /*
         * Adds value to the xy-plot between Start and end
         */
        public void runn(){
            for(int i =0; i < Deriv.size(); i++)
            {
                demo.adds(Deriv.get(i).x, Deriv.get(i).y);
                demo.addT(Func.get(i).x, Func.get(i).y);
                try{Thread.sleep(100);}catch(Exception e){}

                demo.repaint(); 
            }
        }

        public void set(int SIM){this.SIM = SIM;}
    }
}
class Coordinate{
    double x,y;
    public Coordinate(double x, double y){this.x=x; this.y=y;}

    public double getX(){return x;}

    public double getY(){return y;}
}
