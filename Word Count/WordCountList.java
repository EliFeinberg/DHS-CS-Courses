import java.util.*;
import java.io.*;
/**
 * Write a description of class WordCountList here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class WordCountList implements Serializable
{
    // instance variables - replace the example below with your own
    private ArrayList<WordCount> list;

    /**
     * Constructor for objects of class WordCountList
     */
    public WordCountList()
    {
        list = new ArrayList<WordCount>();
    }

    public int size(){return list.size();}

    public void add(String word)
    {
        rank();
        for(int i = 0; i< list.size(); i++){
            if(word.equals(list.get(i).getWord())){list.get(i).increment();return;}
        }
        list.add(new WordCount(word));
    }

    public WordCount get(int index){return list.get(index);}

    public void rank()
    {
        for(int i = 0; i< list.size()-1; i++){
            if(list.get(i).compareTo(list.get(i+1)) > 0){list.add(i, list.remove(i+1));} 

        }
    }
}
