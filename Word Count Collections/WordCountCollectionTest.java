import java.util.*;
import java.io.*;
import java.lang.reflect.*;

/**
 * This WordCountCollectionTest object is the tester class for the 
 * Word Count Collection assignment
 * 
 * @author Tom Bredemeier
 * @version February 11, 2013
 */
public class WordCountCollectionTest
{
    private static WordCount word, test;
    private static WordCountCollection<WordCount> words;
    private static Scanner file;
    private static Calendar calendar;
    private static List<WordCount> list;
    private static long start, mid, stop;
    private static double addTime, rankTime, totalTime;
    private static boolean failed = false;
    private static boolean BSTFailed, BSAFailed, LLFailed;
    private static ArrayList<String> methodNames = new ArrayList<String>();
    private static String message;
    private static String methodName;
    private static Class<?> c;

    public static void main(String[] args)
    {
        testInterface();
        testCollection("BinarySearchArray");
        BSAFailed = failed;
        failed = false;
        testCollection("BinarySearchTree");     
        BSTFailed = failed;
        failed = false;
        testCollection("LinkedList");
        LLFailed = failed;
        if(BSAFailed || BSTFailed || LLFailed)
            System.out.println("Bummer, you have not finished the Word Count Collections assignment.  Try again.");
        else
            System.out.println("Boffo!  You have completed the Word Count Collections assignment");
    }

    private static void testCollection(String type)
    {
        collectionTest(type);
        if(failed)
            System.out.println(type + " class does not pass the test\n");
    }

    private static void testInterface()
    {
        // make sure that WordCountCollection<WordCount> is untouched
        try 
        {
            c = Class.forName("WordCountCollection");
            Member[] methods = c.getMethods();
            for (Member method : methods)
                methodNames.add(((Method)method).toGenericString());
        }
        catch(ClassNotFoundException e)
        {
            failure("Epic Failure: missing interface WordCountCollection");
            return;
        }

        methodName = "public abstract boolean WordCountCollection.isEmpty()";
        if(!methodNames.contains(methodName))
            failure("Missing interface method: " + methodName);

        methodName = "public abstract int WordCountCollection.size()";
        if(!methodNames.contains(methodName))
            failure("Missing interface method: " + methodName);

        methodName = "public abstract T WordCountCollection.fetch(T)";
        if(!methodNames.contains(methodName))
            failure("Missing interface method: " + methodName);

        methodName = "public abstract void WordCountCollection.add(T)";
        if(!methodNames.contains(methodName))
            failure("Missing interface method: " + methodName);

        methodName = "public abstract void WordCountCollection.clear()";
        if(!methodNames.contains(methodName))
            failure("Missing interface method: " + methodName);

        methodName = "public abstract java.util.List<T> WordCountCollection.createList()";
        if(!methodNames.contains(methodName))
            failure("Missing interface method: " + methodName);

        if(failed)
        {
            System.out.println("\nCorrupted interface WordCountCollection");
        }
    }

    private static void collectionTest(String type)
    {
        //********** Generic Word Count Collection ADT Test **************************************
        System.out.println("Now testing your " + type + " class:");
        try 
        {
            if(type.equals("BinarySearchTree"))
                words = new BinarySearchTree<WordCount>();
            else if(type.equals("BinarySearchArray"))
                words = new BinarySearchArray<WordCount>();
            else
                words = new LinkedList<WordCount>();
            c = Class.forName(type);
        }
        catch(ClassNotFoundException e)
        {
            failure("Epic Failure: missing " + type + " class");
            return;
        }
        catch(NoClassDefFoundError e)
        {
            failure("Epic Failure: missing " + type + " class");
            return;
        }
        catch (NoSuchMethodError e)
        {
            failure("Failed: missing constructor " + type + "()");
            return;
        }        

        //make sure that ArrayStack implements StackInterface
        if(!(words instanceof WordCountCollection))
        {
            failure("Failed: " + type + " does not implement WordCountCollection");
            return;
        }

        // Instantiate a WordCount object
        try
        {
            word = new WordCount("Beanwhip");
        }
        catch (NoClassDefFoundError e)
        {
            failure("Epic Failure: missing WordCount class");
        }

        // check size method
        if(!failed)
        {   
            try
            {
                if(words.size() != 0)
                    failure("Failed: size method is incorrect");
            }
            catch (NoSuchMethodError e)
            {
                failure("Failed: missing size method");
            }
        }

        // check add method
        if(!failed)
        {   
            try
            {
                word = new WordCount("BEANWHIP");
                words.add(word);
            }
            catch (NoSuchMethodError e)
            {
                failure("Failed: missing add(WordCount word) method");
            }
        }

        // check fetch method
        if(!failed)
        {   
            try
            {
                test = words.fetch(word);
                if(test == null)
                    failure("Failed: add and/or fetch method is incorrect");
            }
            catch (NoSuchMethodError e)
            {
                failure("Failed: missing fetch(WordCount word) method");
            }
            if(!failed && !test.equals(word))
            {
                failure("Failed: add and/or fetch method is incorrect");
            }
        }

        if(!failed)
        {
            if(words.size() != 1)
                failure("Failed: size method is incorrect");
        }

        if(!failed)
        {
            processWord("TRY");
            processWord("SOME");
            processWord("MORE");
            processWord("TRY");
            processWord("SOME");
            processWord("TRY");
            processWord("TRY");
            processWord("MORE");
            processWord("SOME");
            test = words.fetch(word);
            if(word != null && (test == null || !test.equals(word)))
            {
                failure("Failed: add and/or fetch method is incorrect");
            }
        }

        if(!failed)
        {
            if(! (words.fetch(new WordCount("TRY"))    != null &&
                words.fetch(new WordCount("SOME"))     != null &&
                words.fetch(new WordCount("MORE"))     != null &&
                words.fetch(new WordCount("BEANWHIP")) != null))
                failure("Failed: add and/or fetch method is incorrect");
        }

        if(!failed)
        {
            if(! (words.fetch(new WordCount("TRY")).getCount()      == 4 &&
                words.fetch(new WordCount("SOME")).getCount()     == 3 &&
                words.fetch(new WordCount("MORE")).getCount()     == 2 &&
                words.fetch(new WordCount("BEANWHIP")).getCount() == 1))
                failure("Failed: add and/or fetch method is incorrect");
        }

        if(!failed)
        {
            if(words.size() != 4)
                failure("Failed: size method is incorrect");
        }

        // check creatList method
        if(!failed)
        {   
            try
            {
                list = words.createList();
                Collections.sort(list, new FrequencyComparator());
                if(list.size() != 4)
                    failure("Failed: createList method is incorrect");
            }
            catch (NoSuchMethodError e)
            {
                failure("Failed: missing createList method");
            }
        }
        
        if(!failed)
        {
            if(! (list.get(0).getWord().equals("TRY"))  &&
                 (list.get(1).getWord().equals("SOME")) &&
                 (list.get(2).getWord().equals("MORE")) &&
                 (list.get(3).getWord().equals("BEANWHIP")))
                failure("Failed: createList method is incorrect");
        }

        if(!failed)
        {
            if(! (list.get(0).getWord().equals("TRY"))  &&
                 (list.get(1).getWord().equals("SOME")) &&
                 (list.get(2).getWord().equals("MORE")) &&
                 (list.get(3).getWord().equals("BEANWHIP")))
                failure("Failed: createList method is incorrect");
        }
        // check clear method
        if(!failed)
        {   
            try
            {
                words.clear();
                if(words.size() != 0)
                    failure("Failed: size and/or clear method is incorrect");
            }
            catch (NoSuchMethodError e)
            {
                failure("Failed: missing clear method");
            }
        }

        if(!failed)
        {
            System.out.println("Now processing the 559,815 words in Les Miserables");

            try
            {
                file = new Scanner(new File("LesMiserables.txt"));
            }
            catch (Exception e)
            {
                failure("Failure: missing LesMiserables.txt file");
            }
        }

        if(!failed)
        {   
            start = calendar.getInstance().getTimeInMillis();

            while(file.hasNext())
            {
                String string = file.next().toUpperCase().replaceAll("[^A-Z]","");
                if(string.length() > 0)    // ignore any empty strings
                {
                    processWord(string);
                }
            }
            mid = calendar.getInstance().getTimeInMillis();
            addTime = (double) (mid - start) / 1000;

            list = words.createList();
            Collections.sort(list, new FrequencyComparator());

            stop = calendar.getInstance().getTimeInMillis();
            rankTime = (double) (stop - mid) / 1000;
            totalTime = (double) (stop - start) / 1000;
        }

        if(!failed)
        {
            if(words.size() != 25289)
            {
                System.out.println("There are 25,289 unique words in Les Miserables");
                System.out.println("Your code says it's " + words.size());
                failure("Failed: " + type + " class is incorrect");
            }
        }

        if(!failed)
        {
            word = words.fetch(new WordCount("THE"));
            if(!  (word.getWord().equals("THE") &&
                word.getCount() == 40365))
            {
                System.out.println("The word 'THE' appears 40,365 times in Les Miserables");
                System.out.println("Your code says it's " + word);
                failure("Failed: " + type + " class is incorrect");
            }
        }

        if(!failed)
        {
            word = words.fetch(new WordCount("YOU"));
            if(!  (word.getWord().equals("YOU") &&
                word.getCount() == 3580))
            {
                System.out.println("The word 'YOU' appears 3,580 times in Les Miserables");
                System.out.println("Your code says it's " + word);
                failure("Failed: " + type + " class is incorrect");
            }
        }

        if(!failed)
        {
            word = words.fetch(new WordCount("THOU"));
            if(!  (word.getWord().equals("THOU") &&
                word.getCount() == 112))
            {
                System.out.println("The word 'THOU' appears 112 times in Les Miserables");
                System.out.println("Your code says it's " + word);
                failure("Failed: " + type + " class is incorrect");
            }
        }

        if(!failed)
        {
            word = words.fetch(new WordCount("MUCH"));
            if(!  (word.getWord().equals("MUCH") &&
                word.getCount() == 324))
            {
                System.out.println("The word 'MUCH' appears 324 times in Les Miserables");
                System.out.println("Your code says it's " + word);
                failure("Failed: " + type + " class is incorrect");
            }
        }

        if(!failed)
        {
            word = words.fetch(new WordCount("LIFE"));
            if(!  (word.getWord().equals("LIFE") &&
                word.getCount() == 417))
            {
                System.out.println("The word 'LIFE' appears 417 times in Les Miserables");
                System.out.println("Your code says it's " + word);
                failure("Failed: " + type + " class is incorrect");
            }
        }

        if(!failed)
        {
            word = list.get(10);
            if(!  (word.getWord().equals("HIS") &&
                word.getCount() == 6433))
            {
                System.out.println("The word 'HIS' appears 6,433 times in Les Miserables");
                System.out.println("Your code says it's " + word);
                failure("Failed: " + type + " class is incorrect");
            }
        }

        if(!failed)
        {
            word = list.get(100);
            if(!  (word.getWord().equals("FATHER") &&
                word.getCount() == 559))
            {
                System.out.println("The word 'FATHER' appears 559 times in Les Miserables");
                System.out.println("Your code says it's " + word);
                failure("Failed: " + type + " class is incorrect");
            }
        }

        if(!failed)
        {
            word = list.get(375);
            if(!  (word.getWord().equals("TURN") &&
                word.getCount() == 144))
            {
                System.out.println("The word 'TURN' appears 144 times in Les Miserables");
                System.out.println("Your code says it's " + word);
                failure("Failed: " + type + " class is incorrect");
            }
        }

        if(!failed)
        {
            word = list.get(475);
            if(!  (word.getWord().equals("IMPOSSIBLE") &&
                word.getCount() == 116))
            {
                System.out.println("The word 'IMPOSSIBLE' appears 116 times in Les Miserables");
                System.out.println("Your code says it's " + word);
                failure("Failed: " + type + " class is incorrect");
            }
        }

        if(!failed)
        {
            word = list.get(595);
            if(!  (word.getWord().equals("IDEAS") &&
                word.getCount() == 94))
            {
                System.out.println("The word 'IDEAS' appears 94 times in Les Miserables");
                System.out.println("Your code says it's " + word);
                failure("Failed: " + type + " class is incorrect");
            }
        }

        if(!failed)
        {
            System.out.println("\nList add time:  " + addTime + "  " + Math.round(addTime / totalTime * 100) + "%");
            System.out.println("List rank time: " + rankTime + "  " + Math.round(rankTime / totalTime * 100) + "%");
            System.out.println("Total time:     " + totalTime + "  100%");
            System.out.println("Congratulations!  Your " + type + " class is correct\n");
        }
    }

    private static void failure(String str)
    {
        System.out.println("*** " + str);
        failed = true;
    }

    private static void processWord(String string)
    {
        WordCount nextWord = new WordCount(string);
        word = words.fetch(nextWord);
        if(word != null)
            word.increment();
        else
            words.add(nextWord);   
    }

}

