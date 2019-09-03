import java.util.*;
import java.io.*;
/**
 * Write a description of class WordCountListFile here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class WordCountListFile 
{
    public static void save(WordCountList list, String filename) throws IOException
    {
        
        FileOutputStream f = new FileOutputStream(filename); 
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(list);
        o.close();
    }
    
    public static WordCountList load(String filename) throws Exception
    {
        FileInputStream f = new FileInputStream(filename);
        ObjectInputStream o = new ObjectInputStream(f);
        return (WordCountList)(o.readObject());
    }
}
