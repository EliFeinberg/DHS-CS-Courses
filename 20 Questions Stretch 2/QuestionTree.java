import java.util.*; 
import java.io.*; 

public class QuestionTree {
    private QuestionNode root;
    private Scanner console; 
    public QuestionTree() {
        root = new QuestionNode("computer"); 
        console = new Scanner(System.in); 
    }

    public void read(Scanner input) {
        root = readHelper(input); 
    }

    private QuestionNode readHelper(Scanner input) {
        String type = input.nextLine();
        String info = input.nextLine();
        QuestionNode root = new QuestionNode(info);  

        if (type.contains("Q:")) {
            root.yes = readHelper(input);
            root.no = readHelper(input);   
        }
        return root; 
    }

    public void write(PrintStream output) {
        writeTree(root, output);
    }

    private void writeTree(QuestionNode root, PrintStream output) {
        if (root.isAnswerNode()) {
            output.println("A:"); 
            output.println(root.info);
        } else {
            output.println("Q:");
            output.println(root.info);
            writeTree(root.yes, output);
            writeTree(root.no, output); 
        }   
    }

    public void askQuestions() {
        root = askQuestion(root); 
    } 

    private QuestionNode askQuestion(QuestionNode current) {
        if (current.isAnswerNode()) {
            if (yesTo("Is the thing you're thinking of " + current.info +"?")) {System.out.println("Of course it was!");} 
            else 
            {
                wrongAnswer(current);
            }

        } 
        else 
        {
            if (yesTo(current.info)) {
                current.yes = askQuestion(current.yes);
            } else {
                current.no = askQuestion(current.no); 
            }   
        } 
        return current;
    }

    public void wrongAnswer(QuestionNode finals)
    {
        System.out.print("What is the name of your object? ");
        QuestionNode answer = new QuestionNode(console.nextLine());
        System.out.println("Please give me a y/n question that");
        System.out.println("distinguishes between your object from this thing");
        String question = console.nextLine(); 
        if (yesTo("And what is the answer for your object?")) {
            finals = new QuestionNode(question, answer, finals); 
        } else {
            finals = new QuestionNode(question, finals, answer); 
        }
    }

    public boolean yesTo(String prompt) {
        System.out.print(prompt + " (y/n)? ");
        String response = console.nextLine().trim().toLowerCase();
        while (!response.equals("y") && !response.equals("n")) {
            System.out.println("Please answer y or n.");
            System.out.print(prompt + " (y/n)? ");
            response = console.nextLine().trim().toLowerCase();
        }
        return response.equals("y");
    }   
}



