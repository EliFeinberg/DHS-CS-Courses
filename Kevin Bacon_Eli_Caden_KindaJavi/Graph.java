import java.util.Collection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

/**
 * Graph.java  
 *
 * @author:
 * Assignment #:
 * 
 * Brief Program Description:
 * 
 *
 */
public class Graph<V, E> extends AbstractGraph<V, E>
{      
    // vertex-related methods

    public void addVertex(V v)
    {
        //this.adjacencyMap = new HashMap<V, Map<V, EdgeInfo<E>>>();
        if(v == null)
        {
            throw new NullPointerException();
        }
        if(!containsVertex(v))
        {
            // Map newMap = new HashMap<V, VertexInfo<V>>();
            // adjacencyMap.put(v, newMap);
            adjacencyMap.put(v, new HashMap<V, EdgeInfo<E>>());
            vertexInfo.put(v, new VertexInfo(v));
        }
    }

    public boolean containsVertex(V v)
    {
        // Map newMap = adjacencyMap.get(v);
        // if(newMap == null)
        // {
        // return false;
        // }
        // return true;
        Collection<V> vertices = adjacencyMap.keySet();
        return vertices.contains(v);
    }

    public Collection<V> neighbors(V v)
    {
        if(v == null)
        {
            throw new NullPointerException();
        }
        if(containsVertex(v) == false)
        {
            throw new IllegalArgumentException();
        }
        Map fromVertex = adjacencyMap.get(v);
        Collection toReturn = fromVertex.values();
        Iterator it = fromVertex.keySet().iterator();
        ArrayList<V> list = new ArrayList<V>();
        while(it.hasNext())
        {
            V key = (V)it.next();
            list.add(key);
        }
        System.out.println("Neighbors of " + v.toString() + list.toString());
        return list;
    }

    // edge-related methods
    public void addEdge(V v1, V v2, E e)
    {
        if(v1 == null || v2 == null || e == null)
        {
            throw new NullPointerException();
        }
        if(containsVertex(v1) == false || containsVertex(v2) == false)
        {
            throw new IllegalArgumentException();
        }
        Map fromVertex = adjacencyMap.get(v1);
        Map toVertex = adjacencyMap.get(v2);
        EdgeInfo<E> edge = new EdgeInfo(e, 1);
        fromVertex.put(v2, edge);
        toVertex.put(v1, edge);
        edgeList.add(e);
    }

    public void addEdge(V v1, V v2, E e, int weight)
    {
        if(v1 == null || v2 == null || e == null)
        {
            throw new NullPointerException();
        }
        if(containsVertex(v1) == false || containsVertex(v2) == false || weight < 0)
        {
            throw new IllegalArgumentException();
        }
        Map fromVertex = adjacencyMap.get(v1);
        Map toVertex = adjacencyMap.get(v2);
        EdgeInfo<E> edge = new EdgeInfo(e, weight);
        fromVertex.put(v2, edge);
        toVertex.put(v1, edge);
        edgeList.add(e);
    }

    public boolean containsEdge(V v1, V v2)
    {
        //Map fromVertex = adjacencyMap.get(v1);
        //return fromVertex.get(v2) != null; 
        if(v1 == null || v2 == null)
        {
            throw new NullPointerException();
        }
        if(!containsVertex(v1) || !containsVertex(v2))
        {
            throw new IllegalArgumentException();
        }
        return neighbors(v1).contains(v2);
    }

    public E edge(V v1, V v2)
    {
        if(v1 == null || v2 == null)
        {
            throw new NullPointerException();
        }
        if(containsVertex(v1) == false || containsVertex(v2) == false)
        {
            throw new IllegalArgumentException();
        }
        //Map fromVertex = adjacencyMap.get(v1);
        //return (E)fromVertex.get(v2);
        return adjacencyMap.get(v1).get(v2).e;
    }

    public int edgeWeight(V v1, V v2)
    {
        if(v1 == null || v2 == null)
        {
            throw new NullPointerException();
        }
        if(containsVertex(v1) == false || containsVertex(v2) == false)
        {
            throw new IllegalArgumentException();
        }
        //int weight = 0;
        Map<V, EdgeInfo<E>> fromVertex = adjacencyMap.get(v1);
        EdgeInfo<E> edge = fromVertex.get(v2);
        if(edge == null)
        {
            return -1;
        }
        int weight = edge.weight;
        //weight = edge.weight;
        return weight;
    }

    public int indexOf(V vertex)
    {
        int index = 0;
        Iterator it = adjacencyMap.keySet().iterator();
        while(it.hasNext())
        {
            V key = (V)it.next();
            if(key.equals(vertex))
            {
                return index;
            }
            index++;
        }
        return -1;
    }
}
