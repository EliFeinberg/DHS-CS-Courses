import java.util.*;
import java.io.*;

public class KevinBacon {
    static ArrayList<String> arr;
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Welcome to the six degrees of Kevin Bacon.\nIf you tell me"
            + " an actor's name, I'll connect them to Keving Bacon\n through tbe movies" + 
            " they've appeared in. I bet your actor has a Kevin Bacon number " + 
            " of less than six");

        System.out.println("\nActor's name: (or ALL for everyone)?"); 
        Scanner sc = new Scanner(System.in);
        String actor = sc.nextLine();
        SearchableGraph<String, String> graph = buildGraph();
        try
        {

            findPath(actor, graph);

        }
        catch(Exception e)
        {
            System.out.println("I'm sorry. " + actor + " is not on the file.");
        }
        FrontEnd fe = new FrontEnd("Kevin Bacon");
        fe.update(format());
    }

    public static String[] format()
    {
        String[] s = new String[arr.size()];
        for(int i = 0; i <arr.size(); i++)
        {
            s[i] = arr.get(i);
        }
        return s;
    }

    public static SearchableGraph<String, String> buildGraph() throws FileNotFoundException {
        Scanner input = new Scanner(new File("movies.txt"));
        SearchableGraph<String, String> graph = new SearchableGraph<String, String>();

        while (input.hasNextLine()) {
            Scanner line = new Scanner(input.nextLine()).useDelimiter(";");

            String movie = line.next();

            // get all of the actors in the movie
            List<String> actors = new ArrayList<String>();
            while (line.hasNext()) {
                String actor = line.next();
                graph.addVertex(actor);
                actors.add(actor);
            }

            // connect all of the actors
            for (int i = 0; i < actors.size(); i++) {
                for (int j = 1; j < actors.size(); j++) {
                    graph.addEdge(actors.get(i), actors.get(j), movie);
                }
            }

        }

        return graph;
    }

    public static void findPath(String actor, SearchableGraph graph) throws Exception
    {
        if(actor.equals("ALL"))
        {
            printAll(graph);
        }
        else
        {
            List path = graph.shortestPath("Kevin Bacon", actor);
            //System.out.println(path.toString());
            printConnection(path, graph);
            arr.add("Kevin Bacon " + "(" + actor + "'s\nBacon number is " + (path.size()-1) + ")");
            //System.out.println(actor + "'s Bacon number is " + (path.size()-1) + ".");
        }

    }
    public static void printConnection(List<String> path, SearchableGraph<String, String> graph)
    {
        arr = new ArrayList<String>();

        for(int i = path.size()-1; i > 0; i--)
        {
            String castMate = path.get(i);
            String castMate2 = path.get(i-1);
            String movie = graph.edge(castMate, castMate2);
            arr.add(castMate + " was in " + movie + " with " + castMate2);
            //System.out.println(castMate + "\nwas in " + movie + "\nwith " + castMate2 + ".");
        }

        //"Eli\n\nThe Office"
    }

    public static void printAll(SearchableGraph<String, String> graph)
    {
        Set vertices = graph.getVertices();
        Iterator it = vertices.iterator();
        while(it.hasNext())
        {
            String actor = (String)it.next();
            System.out.println();
            List path = graph.shortestPath("Kevin Bacon", actor);
            printConnection(path, graph);
        }
    }

}