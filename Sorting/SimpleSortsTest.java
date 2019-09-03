import java.util.ArrayList;
/**
 * This SimpleSortsTest object tests the simple sorts found in the Sorts class 
 * by selecting a random word and then checking the results of Sorts against an Exemplar.
 * 
 * @author  Tom Bredemeier
 * @version April 10, 2013
 */
public class SimpleSortsTest extends SortsTest
{
    public static void main(String[] args)
    {
        int rand = (int)(Math.random() * 12);

        if(!failed)
        {
            System.out.print("Bubble Sort\t");
            restart(rand);
            exemplar.bubbleSort();
            comparison = exemplar.getResults();
            sorts.bubbleSort();
            results = sorts.getResults();
            comparison = exemplar.getResults();
            displayResults();

            if(!failed)
                System.out.println("\nCongratulations! Your bubbleSort method is correct");
            else
                System.out.println("\nBummer.  Your bubbleSort method is incorrect.  Try again.");
        }

        if(!failed)
        {
            System.out.print("\nSelection Sort\t");
            restart(rand);
            exemplar.selectionSort();
            comparison = exemplar.getResults();
            sorts.selectionSort();
            results = sorts.getResults();
            comparison = exemplar.getResults();
            displayResults();

            if(!failed)
                System.out.println("\nCongratulations! Your selectionSort method is correct");
            else
                System.out.println("\nBummer.  Your selectionSort method is incorrect. Try again.");
        }

        if(!failed)
        {
            System.out.print("\nInsertion Sort\t");
            restart(rand);
            exemplar.insertionSort();
            comparison = exemplar.getResults();
            sorts.insertionSort();
            results = sorts.getResults();
            comparison = exemplar.getResults();
            displayResults();

            if(!failed)
                System.out.println("\nCongratulations! Your insertionSort method is correct");
            else
                System.out.println("\nBummer.  Your insertionSort method is incorrect. Try again.");
        }

        if(!failed)
            System.out.println("\nFantastical!  You have finished the Simple Sorts assignment");
    }    
}
