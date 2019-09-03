// This tester program tests a graph by using it to search for plane flights.
import java.io.*;
import java.util.*;

// Reads airport data from a file and puts it into a graph.
public class TestGraphAirport {
    public static void main(String[] args) throws FileNotFoundException {
        SearchableGraph<String, String> graph = makeGraph();

        System.out.print("Start and end cities? ");
        Scanner console = new Scanner(System.in);
        String start = console.next();
        String end = console.next();

        fly(graph, start, end);
    }

    // Reads input file and builds airline flight graph.
    public static SearchableGraph<String, String> makeGraph() throws FileNotFoundException {
        SearchableGraph<String, String> graph = new SearchableGraph<String, String>();

        Scanner in = new Scanner(new File("airport.txt"));
        while (in.hasNextLine()) {
            String line = in.nextLine();
            Scanner lineScan = new Scanner(line);
            String type = lineScan.next();

            if (type.equals("vertex")) {
                String city = lineScan.next();

                graph.addVertex(city);

            } else if (type.equals("edge")) {
                String city1 = lineScan.next();
                String city2 = lineScan.next();
                String flight = lineScan.next();
                int miles = lineScan.nextInt();

                graph.addEdge(city1, city2, flight, miles);
            }
        }

        return graph;
    }

    // Searches for paths to fly from the given start city to the given end city.
    public static void fly(SearchableGraph<String, String> graph, String start, String end) {
        if (graph.reachable(start, end)) {
            System.out.println("Yes, we can fly you from " + start + " to " + end + ".");
            System.out.println();

            printPath("The route with fewest plane changes is ", graph, graph.shortestPath(start, end));
            printPath("The most direct mileage route is ", graph, graph.shortestPath(start, end));
        } else {
            System.out.println("Sorry, there's no path from " + start + " to " + end + ".");
        }
    }

    // Helper to print information about a given flight path.
    public static void printPath(String message, SearchableGraph<String, String> graph, List<String> path) {
        System.out.print(message);
        System.out.print(path);
        System.out.println(" with " + (path.size() - 1) + " stops.");
    }
}
