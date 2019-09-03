PROJECT TITLE: Word Counting
PURPOSE OF PROJECT: This assignment counts and displays the frequency of 
individual words read from a text file, using WordCount objects for each 
word.
DATE: August 5, 2012
HOW TO START THIS PROJECT: Run the main method in the WordCounting class
AUTHOR: Tom Bredemeier
NOTES: The following private helper methods in WordCounting were created to 
stepwise refine the processes in the main method:

    void getFileList()
    void inputFromFile(File fileName)
    int totalWordCount()
    int uniqueWordCount()
    void removeCommonWords(int num)
    void displayWordFrequency(int threshold)
    
Furthermore, the equals, compareTo, and toString methods in WordCount include
an @Override annotation to insure that the proper parent method was overridden.
