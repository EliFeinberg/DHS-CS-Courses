/**
 * This GuitarString object . . .
 * 
 * @author  
 * @version 
 */
public class GuitarString 
{
    RingBuffer BUF;
    int ticy;
    public GuitarString(double frequency) 
    {
        BUF= new RingBuffer((int)(44100/frequency));
        for(int i = 0; i < (int)(44100/frequency); i++){BUF.add(0);}
    }

    public GuitarString(double[] array) 
    {
        BUF = new RingBuffer(array.length);
        for(int i = 0; i < array.length; i++){BUF.add(array[i]);}
    }

    public void pluck() 
    {
        int s = BUF.size();
        for(int i = 0; i < s; i++)
        {
            BUF.remove();
        }
        for(int i = 0; i < s; i++)
        {
            BUF.add(Math.random()-0.5);
        }
    }

    // advance the simulation one time step
    public void tic() 
    {
        BUF.add((BUF.remove()+BUF.peek())/1.999*0.994);
        ticy++;
    }

    // return the current sample
    public double sample() 
    {
        return BUF.peek();
    }

    // return number of times tic was called
    public int time() 
    {
        return ticy;
    }

    public static void main(String[] args) 
    {
        double[] samples = { .2, .4, .5, .3, -.2, .4, .3, .0, -.1, -.3 };  
        GuitarString testString = new GuitarString(samples);
        for (int i = 0; i < 25; i++) 
        {
            int t = testString.time();
            double sample = testString.sample();
            System.out.printf("%6d %8.4f\n", t, sample);
            testString.tic();
        }
    }
}
