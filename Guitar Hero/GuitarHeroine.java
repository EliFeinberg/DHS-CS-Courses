import java.util.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
/**
 * This GuitarHeroine object . . .
 * https://www.hooktheory.com/theorytab/view/toby-fox/megalovania?node=b6.5
 * @author  
 * @version 
 */
public class GuitarHeroine
{
    public static void main(String[] args)
    {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        ArrayList<GuitarString> Order= new ArrayList<GuitarString>();
        //ArrayList<Integer> plucked = new ArrayList<Integer>();
        for(int i = 0; i < keyboard.length(); i++)
        {
            GuitarString note = new GuitarString(440*Math.pow(1.05956, i - 24));
            Order.add(note);
        }
        // Create two guitar strings, for concert A and C
        double CONCERT_A = 110.0;
        double CONCERT_C = CONCERT_A * Math.pow(2, 3.0/12.0);
        GuitarString stringA = new GuitarString(CONCERT_A);
        GuitarString stringC = new GuitarString(CONCERT_C);
        Thread m = new Thread(){
                public Robot r;
                ArrayList<Character> notes;
                int EIG = 250;
                int SIX = 125;
                int QUEA = 500;
                public void intro(){
                    try{
                        while(!notes.isEmpty())
                        {
                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX);
                            r.keyRelease(notes.remove(0));
                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX);
                            r.keyRelease(notes.remove(0));
                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX);
                            r.keyRelease(notes.remove(0));
                            Thread.sleep(SIX);

                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX);
                            r.keyRelease(notes.remove(0));
                            Thread.sleep(EIG);                            
                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX);
                            r.keyRelease(notes.remove(0));

                            Thread.sleep(SIX);
                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX);
                            r.keyRelease(notes.remove(0));
                            Thread.sleep(SIX);
                            r.keyPress(notes.get(0));
                            Thread.sleep(EIG);
                            r.keyRelease(notes.remove(0));
                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX);
                            r.keyRelease(notes.remove(0));
                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX);
                            r.keyRelease(notes.remove(0));
                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX);
                            r.keyRelease(notes.remove(0));
                        }
                        }catch(Exception e){}}
                public void verse()
                {
                    try{
                        while(!notes.isEmpty())
                        {
                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX);
                            r.keyRelease(notes.remove(0));
                                Thread.sleep(SIX);
                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX);
                            r.keyRelease(notes.remove(0));
                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX);
                            r.keyRelease(notes.remove(0));
                                Thread.sleep(SIX);
                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX);
                            r.keyRelease(notes.remove(0));
                                Thread.sleep(SIX);
                            r.keyPress(notes.get(0));
                            Thread.sleep(EIG);
                            r.keyRelease(notes.remove(0));
                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX);
                            r.keyRelease(notes.remove(0));
                                Thread.sleep(SIX);
                            r.keyPress(notes.get(0));
                            Thread.sleep(QUEA);
                            r.keyRelease(notes.remove(0));
                                Thread.sleep(SIX);
                                
                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX);
                            r.keyRelease(notes.remove(0));
                                Thread.sleep(SIX);
                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX);
                            r.keyRelease(notes.remove(0));
                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX);
                            r.keyRelease(notes.remove(0));
                                Thread.sleep(SIX);
                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX);
                            r.keyRelease(notes.remove(0));
                                Thread.sleep(SIX);
                            r.keyPress(notes.get(0));
                            Thread.sleep(EIG);
                            r.keyRelease(notes.remove(0));
                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX);
                            r.keyRelease(notes.remove(0));
                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX);
                            r.keyRelease(notes.remove(0));
                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX);
                            r.keyRelease(notes.remove(0));
                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX);
                            r.keyRelease(notes.remove(0));
                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX);
                            r.keyRelease(notes.remove(0));
                                Thread.sleep(EIG);
                                
                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX);
                            r.keyRelease(notes.remove(0));
                                Thread.sleep(SIX);
                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX);
                            r.keyRelease(notes.remove(0));
                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX);
                            r.keyRelease(notes.remove(0));
                                Thread.sleep(SIX);
                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX);
                            r.keyRelease(notes.remove(0));
                                Thread.sleep(SIX);
                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX);
                            r.keyRelease(notes.remove(0));
                                Thread.sleep(SIX);
                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX);
                            r.keyRelease(notes.remove(0));
                                Thread.sleep(SIX);
                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX);
                            r.keyRelease(notes.remove(0));
                                Thread.sleep(SIX);
                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX*3);
                            r.keyRelease(notes.remove(0)); 
                            
                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX);
                            r.keyRelease(notes.remove(0));
                            
                                Thread.sleep(SIX);
                            
                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX);
                            r.keyRelease(notes.remove(0));
                            
                                Thread.sleep(SIX);
                            
                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX);
                            r.keyRelease(notes.remove(0));
                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX);
                            r.keyRelease(notes.remove(0));
                            r.keyPress(notes.get(0));
                            Thread.sleep(SIX);
                            r.keyRelease(notes.remove(0));
                            r.keyPress(notes.get(0));
                            Thread.sleep(QUEA*2);
                            r.keyRelease(notes.remove(0));
                        }
                        }catch(Exception e){}
                }
        public void run()
        {
            String s = "[[mvfcx[xc ppmvfcx[xc oomvfcx[xc 99mvfcx[xc [[mvfcx[xc ppmvfcx[xc oomvfcx[xc 99mvfcx[xc";
            //String s = "i p z v b z p b n z p n d [ i d z p i p z p i u i i";
            //String d = "8 u 7 y o p p";
            //String f = "w q q";
            s = s.replaceAll("\\s+","").toUpperCase();
            //d = d.replaceAll("\\s+","").toUpperCase();
            //f = f.replaceAll("\\s+","").toUpperCase();
            System.out.println(s);
            notes = new ArrayList<Character>();
            for(int i = 0; i<s.length(); i++)
            {
                notes.add(s.charAt(i));
            }
            int start = 0;
            int start2 = 0;
            int start3 = 0;
            int var = 30;
            int inter = 140;

            try{
                r = new Robot();
                Thread.sleep(1000);
                intro();
            
                s = "xxxxx[[xxxcfcx[xcxxxcfvnvmmmvmnvvvvvccvvvvcvmvcmvcxncxz9p[xn";
                s = s.replaceAll("\\s+","").toUpperCase();
                for(int i = 0; i<s.length(); i++)
            {
                notes.add(s.charAt(i));
            }
                verse();
                // while(true){
                // if(start == 4 ||  start == 8 || start == 12 ||start ==16 || start ==23 || start ==24 || start == 25)
                // {
                // //r.keyPress(d.charAt(start2));
                // }
                // if(start ==23 || start ==24 || start == 25)
                // {
                // //r.keyPress(f.charAt(start3));
                // }
                // r.keyPress(s.charAt(start));
                // Thread.sleep(inter+(int)(var*Math.random()));
                // r.keyRelease(s.charAt(start));
                // if(start == 4 ||  start == 8 || start == 13 ||start ==17 || start ==24 || start ==25 || start == 26)
                // {
                // //r.keyRelease(d.charAt(start2));
                // start2++;
                // }
                // if(start ==23 || start ==24 || start == 25)
                // {
                // //r.keyRelease(f.charAt(start3));
                // start3++;
                // }
                // Thread.sleep(inter+(int)(var*Math.random()));
                // start++;
                // }

            }
            catch(Exception e){}}

    };
    m.start();
    // the main input loop
    while (true) 
    {

        // check if the user has typed a key, and, if so, process it
        if (StdDraw.hasNextKeyTyped()) 
        {

            char key = StdDraw.nextKeyTyped();
            // pluck the corresponding string
            for(int i = 0; i < keyboard.length(); i++)
            {
                if(key == keyboard.charAt(i)){Order.get(i).pluck();}
            }
        }

        // compute the superposition of the samples
        double sample = 0.0;
        for(int i = 0; i < keyboard.length(); i++)
        {
            sample += Order.get(i).sample();
        }
        //System.out.println(sample);
        // send the result to standard audio
        StdAudio.play(sample);

        // advance the simulation of each guitar string by one step
        for(int i = 0; i < Order.size(); i++){Order.get(i).tic();}
        //Order.get(3).tic();
        //Order.get(4).tic();
    }
}
}