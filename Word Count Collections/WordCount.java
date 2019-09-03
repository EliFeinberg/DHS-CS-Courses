/**
 * This WordCount object represents a counter of unique words
 * 
 * @author  Mr. Bredemeier
 * @version February 11, 2013
 */
public class WordCount implements Comparable<WordCount>
{
    private String word;
    private int count;
    
    public WordCount(String word)
    {
        this.word = word;
        this.count = 1;
    }
    
    public String getWord()
    {
        return word;
    }
    
    public int getCount()
    {
        return count;
    }
    
    public void increment()
    {
        count++;
    }
    
    public String toString()
    {
        return word + " " + count;
    }

//  public boolean equals(Object object)
    public boolean equals(WordCount other)
    {
//        WordCount other = (WordCount) object;
        return word.equals(other.getWord());
    }
    
    @Override
    public int compareTo(WordCount other)
    {
        return word.compareTo(other.getWord());
    }

}
