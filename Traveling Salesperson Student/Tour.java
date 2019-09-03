import java.util.*;
import java.awt.Graphics;

/**
 * This class is a specialized Linked List of Points that represents a
 * Tour of locations attempting to solve the Traveling Salesperson Problem
 * 
 * @author
 * @version
 */

public class Tour implements TourInterface
{
    // instance variables
    LinkedList<ListNode> root;
    // constructor
    public Tour()
    {
        root = new LinkedList<ListNode>();
    }

    //return the number of points (nodes) in the list   
    public int size()
    {
        return root.size();
    }

    // append Point p to the end of the list
    public void add(Point p)
    {
        root.add(new ListNode(p));
    } 

    // print every node in the list 
    public void print()
    {   
        for(int i = 0; i<root.size(); i++)
        {
            System.out.println(root.get(i).data);
        }
    }

    // draw the tour using the given graphics context
    public void draw(Graphics g)
    {
        // int x = 0,y = 0;
        // for(int i =0; i<root.size(); i++)
        // {
        // if(i > 0)
        // {
        // g.drawLine(x, y, (int)root.get(i).data.getX(),(int)root.get(i).data.getY());
        // }
        // g.fillOval((int)root.get(i).data.getX(),(int)root.get(i).data.getY(), 2,2);
        // x = (int)root.get(i).data.getX();
        // y = (int)root.get(i).data.getY();
        // }
        // g.drawLine(x,y,(int)root.get(0).data.getX(), (int)root.get(0).data.getY());
        int x = 0,y = 0;
        for(int i =root.size()-1; i>=0; i--)
        {
            if(i < root.size()-1)
            {
                g.drawLine(x, y, (int)root.get(i).data.getX(),(int)root.get(i).data.getY());
            }
            g.fillOval((int)root.get(i).data.getX(),(int)root.get(i).data.getY(), 2,2);
            x = (int)root.get(i).data.getX();
            y = (int)root.get(i).data.getY();
        }
        g.drawLine(x,y,(int)root.get(root.size()-1).data.getX(), (int)root.get(root.size()-1).data.getY());
    }

    //calculate the distance of the Tour, but summing up the distance between adjacent points
    //NOTE p.distance(p2) gives the distance where p and p2 are of type Point
    public double distance()
    {
        double sum = 0;
        for(int i = 0; i < root.size()-1; i++)
        {
            sum += root.get(i).data.distance(root.get(i+1).data);
        }
        sum += root.get(root.size()-1).data.distance(root.get(0).data);
        return sum;
    }

    // add Point p to the list according to the NearestNeighbor heuristic
    public void insertNearest(Point p)
    {   
        int NEAR = 0;
        double DIST = 0.0;
        for(int i = 0; i<root.size(); i++)
        {
            if(p.distance(root.get(i).data) < DIST || DIST == 0.0)
            {
                NEAR = i;
                DIST = p.distance(root.get(i).data);
            }
        }
        root.add(NEAR, new ListNode(p));
    }

    // add Point p to the list according to the InsertSmallest heuristic
    public void insertSmallest(Point p)
    { 
        if(root.size() < 2 ){
            root.add(new ListNode(p));
            return;
        }
        CS Pone = new CS();
        CS Ptwo = new CS();
        ListNode node = new ListNode(p);
        int NEAR = 0;
        double MDIST = distance();
        Thread one = new Thread()
            {
                public void start()
                {

                    double DIST = 0.0 ;
                    double nDIST = 0.0 ;

                    for(int i = 0;i<root.size()/2;i++){
                        if(i == 0)
                        {
                            nDIST= MDIST - root.get(root.size()-1).data.distance(root.get(i).data);
                            //root.add(i,node);
                            nDIST += root.get(root.size()-1).data.distance(node.data) + root.get(i).data.distance(node.data);
                        }
                        else if(i == root.size() - 1)
                        {
                            nDIST= MDIST - root.get(root.size()-1).data.distance(root.get(i).data);
                            //root.add(i,node);
                            nDIST += root.get(i-1).data.distance(node.data) + node.data.distance(root.get(0).data);
                        }
                        else{
                            nDIST= MDIST - root.get(i-1).data.distance(root.get(i).data);
                            //root.add(i,node);
                            nDIST += root.get(i-1).data.distance(node.data) + root.get(i).data.distance(node.data);
                        }
                        if(nDIST<DIST || DIST == 0.0){
                            Pone.pos=i;
                            DIST=nDIST;
                            Pone.dist = nDIST;
                        }
                        //root.remove(i);
                        //System.out.println("This 1");
                    }

                }
            };
        Thread two = new Thread()
            {
                public void start()
                {

                    double DIST = 0.0 ;
                    double nDIST = 0.0 ;

                    //double MDIST = distance();

                    for(int i = root.size()/2+1;i<root.size();i++){
                        if(i == 0)
                        {
                            nDIST= MDIST - root.get(root.size()-1).data.distance(root.get(i).data);
                            //root.add(i,node);
                            nDIST += root.get(root.size()-1).data.distance(node.data) + root.get(i).data.distance(node.data);
                        }
                        else if(i == root.size() - 1)
                        {
                            nDIST= MDIST - root.get(root.size()-1).data.distance(root.get(i).data);
                            //root.add(i,node);
                            nDIST += root.get(i-1).data.distance(node.data) + node.data.distance(root.get(0).data);
                        }
                        else{
                            nDIST= MDIST - root.get(i-1).data.distance(root.get(i).data);
                            //root.add(i,node);
                            nDIST += root.get(i-1).data.distance(node.data) + root.get(i).data.distance(node.data);
                        }
                        if(nDIST<DIST || DIST == 0.0){
                            Ptwo.pos=i;
                            DIST=nDIST;
                            Ptwo.dist = nDIST;
                        }
                        //root.remove(i);
                        //System.out.println("This 2");
                    }

                }
            };
        one.start();
        two.start();
        
        if(Ptwo.dist < Pone.dist)
        {
            NEAR = Ptwo.pos;
        }
        else{NEAR = Pone.pos;}
        root.add(NEAR,node);
    }

    // This is a private inner class, which is a separate class within a class.
    private class ListNode
    {
        private Point data;
        private ListNode next;
        public ListNode(Point p, ListNode n)
        {
            this.data = p;
            this.next = n;
        }

        public ListNode(Point p)
        {
            this(p, null);
        }        
    }

}