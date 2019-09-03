import java.util.*;
/**
 * WeightedGraph.java  
 *
 * @author:
 * Assignment #:
 * 
 * Brief Program Description:
 * 
 *
 */
public class WeightedGraph<T> implements WeightedGraphInterface<T>
{
    public static final int NULL_EDGE = 0;
    public static final int DEFCAP = 50;
    private int numVer, MaxVer;
    private T[] vert;
    private int[][] edge;
    private boolean[] mark;
    public WeightedGraph()
    {
        numVer = 0;
        MaxVer = DEFCAP;
        vert = (T[])(new Object[DEFCAP]);
        mark = new boolean[DEFCAP];
        edge = new int[DEFCAP][DEFCAP];
    }

    public WeightedGraph(int MaxVertices)
    {
        numVer = 0;
        MaxVer = MaxVertices;
        vert = (T[])(new Object[MaxVer]);
        mark = new boolean[MaxVer];
        edge = new int[MaxVer][MaxVer];
    }
    // tests if graph is empty
    public boolean empty(){return numVer == 0;}

    // tests if graph is full
    public boolean full(){return numVer == MaxVer;}

    // Precondition: Vertex is not already in graph
    // Precondition: Vertex is not null
    // adds vertex to graph
    public void addVertex(T vertex)
    {
        vert[numVer] = vertex;
        for(int i =0; i < numVer; i++)
        {
            edge[numVer][i] = 0;
            edge[i][numVer] = 0;
        }
        numVer++;
    }

    // returns true if graph contains vertex
    public boolean hasVertex(T vertex)
    {
        for(int i = 0; i < numVer; i++)
        {
            if(vertex.equals(vert[i]))
            {
                return true;
            }
        }
        return false;
    }

    private int IndexIs(T vertex)
    {
        int index = 0;
        while(!vertex.equals(vert[index]) && index < numVer)
        {
            index++;
        }
        return index;
    }
    // adds an edge with the specified weight from fromVertex to toVertex
    public void setEdge(T fromVertex, T toVertex,int weight)
    {
        int row, column;
        row = IndexIs(fromVertex);
        column = IndexIs(toVertex);
        edge[row][column] = weight;
    }

    // if edge from fromVertex to toVertex exists, return the weight of
    // the edge; otherwise, returns a special "null-edge" value.
    public int weightIs(T fromVertex, T toVertex)
    {
        int row, column;
        row = IndexIs(fromVertex);
        column = IndexIs(toVertex);
        return edge[row][column];
    }

    // returns a queue of the vertices that are adjacent to the vertex
    public QueueInterface<T> getAdjacentVertices(T vertex)
    {
        QueueInterface<T> adj = new LinkedQueue<T>();
        int fromIndex;
        int toIndex;
        fromIndex = IndexIs(vertex);
        for(toIndex = 0; toIndex<numVer; toIndex++)
        {
            if(edge[fromIndex][toIndex] != NULL_EDGE)
                adj.add(vert[toIndex]);
        }
        return adj;
    }

    // sets marks for all vertices to false
    public void clearMarks()
    {
        for(int i =0; i< numVer; i++)
        {
            mark[i] = false;
        }
    }

    // sets mark for vertex to true
    public void markVertex(T vertex)
    {
        int index = IndexIs(vertex);
        mark[index] = true;
    }

    // returns true if vertex is marked
    public boolean isMarked(T vertex)
    {
        return mark[IndexIs(vertex)];
    }

    // returns an unmarked vertex if any exist, otherwise returns null
    public T getUnmarked()
    {
        int i;
        boolean ex = false;
        for(i = 0; i< numVer; i++)
        {
            if(!mark[i]){ex = true; break;}
        }
        if(ex)
        {
            return vert[i];
        }
        else{return null;}
    } 

    // return a String representation of this graph
    public String toString()
    {
        return "Yeet work";
    }
}
