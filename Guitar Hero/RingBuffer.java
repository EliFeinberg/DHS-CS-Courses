/**
 * This RingBuffer object . . .
 * 
 * @author  
 * @version 
 */
public class RingBuffer 
{
    double[] CAP;
    int front, rear;
    int size;
    public RingBuffer(int capacity)
    {
        CAP = new double[capacity];
        front = 0;
        rear = 0;
        size = 0;
    }

    public int size()
    {
        return size;
    }
    
    public boolean isEmpty()
    {
        return size == 0;
    }
    
    public boolean isFull()
    {
        return size == CAP.length;
    }
    
    public void add(double value)
    {
        size++;
        CAP[rear++] = value;
        if(rear + 1 > CAP.length){rear = 0;}
    }
    
    public double peek()
    {
        return CAP[front];
    }
    
    public double remove()
    {
        size--;
        double r = CAP[front++];
        if(front + 1 > CAP.length){front = 0;}
        return r;
        
    }
    
    // a simple test of the constructor and methods in RingBuffer
    public static void main(String[] args) 
    {
        int capacity = 100;
        RingBuffer buffer = new RingBuffer(capacity);  
        for (int i = 1; i <= capacity; i++) 
            buffer.add(i);
      
        double t = buffer.remove();
        buffer.add(t);
        System.out.println("Size after wrap-around is " + buffer.size());
        while (buffer.size() >= 2) 
        {
            double x = buffer.remove();
            double y = buffer.remove();
            buffer.add(x + y);
        }
        System.out.println(buffer.peek());
    }

}
