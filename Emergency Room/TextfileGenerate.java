import java.util.*;
import java.io.*;
/**
 * Write a description of class TextfileGenerate here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TextfileGenerate
{
    public static void main() throws Exception
    {
        Scanner patientList = new Scanner(new File("PATIENTS.txt"));

        while(patientList.hasNextLine()){
            String name = patientList.nextLine();
            System.out.println(name);
            System.out.println((int)(Math.random()*100));
        }
    } 
}

