import java.io.*;
/**
 * Write a description of class WordCount here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class WordCount implements Comparable<WordCount>, Serializable
{
    private int count;
    private String word;

    /**
     * Constructor for objects of class WordCount
     */
    public WordCount(String Word)
    {
        word = Word;
        count = 1;
    }
    
    public String getWord(){return word;}
    
    public int getCount(){return count;}
    
    public void increment(){count++;}
    
    public String toString()
    {
        return word + " " + count;
    }
    
    public boolean equals(WordCount other)
    {
        return getWord() == other.getWord();
    }
    
    public int compareTo(WordCount other)
    {
        return other.getCount() - getCount();
    }
}
