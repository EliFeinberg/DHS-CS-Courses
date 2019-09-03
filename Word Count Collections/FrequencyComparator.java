import java.util.Comparator;
/**
 * This FrequencyComparator object allows for a collection
 * of WordCount objects to be sorted by frequency (i.e. word count)
 * rather than alphabetically (as done by the compareTo method)
 * 
 * @author  Mr. Bredemeier
 * @version February 11, 2013
 */
public class FrequencyComparator implements Comparator<WordCount>
{
    public int compare(WordCount wc1, WordCount wc2)
    {
        return wc2.getCount() - wc1.getCount();
    }
}
