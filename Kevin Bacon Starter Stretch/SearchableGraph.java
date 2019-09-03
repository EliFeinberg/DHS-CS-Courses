import java.util.*;
public class SearchableGraph<V, E> extends Graph<V, E> implements ISearchableGraph<V, E>
{
    public boolean reachable(V v1, V v2)
    {
        if(v1 == null || v2 == null)
        {
            throw new NullPointerException();
        }
        if(containsVertex(v1) == false || containsVertex(v2) == false)
        {
            throw new IllegalArgumentException();
        }
        boolean foundPath = false;
        PriorityQueue<V> queue = new PriorityQueue<V>();
        queue.add(v1);
        while(!foundPath)
        {
            if(queue.size() == 0)
            {
                System.out.println("Empty queue");
                return false;
            }
            V curVertex = queue.remove();  
            //System.out.println("Cur vertex: " + curVertex);
            //System.out.println("Versus: " + v2);
            vertexInfo.get(curVertex).visited = true;
            if(curVertex.equals(v2))
            {
                foundPath = true;
            }
            else
            {
                Collection<V> adjVertices = neighbors(v1);
                V[] arrVersion = (V[])adjVertices.toArray();
                int adjSize = arrVersion.length;
                System.out.println("Number of connections: " + adjSize);
                ArrayList<V> listVersion = new ArrayList<V>();
                for(int i = 0; i < adjSize; i++)
                {
                    listVersion.add(arrVersion[i]);
                }

                for(int i = 0; i < adjSize; i++)
                {
                    V toAdd = listVersion.remove(0); //always removes the first element 
                    if(vertexInfo.get(toAdd).visited == false)
                    {
                        //System.out.println("ADDING" + toAdd);
                        queue.add(toAdd);
                    }
                }
            }
        }
        return foundPath;
    }

    public List<V> shortestPath2(V v1, V v2)
    {      
        PriorityQueue<V> vertices = new PriorityQueue<V>();
        ArrayList<V> correct = new ArrayList<V>();
        boolean found = false;
        V curVertex;
        vertices.add(v1);
        while(vertices.size() > 0)
        {
            System.out.println("Vertices size: " + vertices.size());
            curVertex = vertices.remove();
            if(curVertex.equals(v2))
            {
                //System.out.println("Adding: " + v2);
                correct.add(v2);
                while(vertexInfo.get(v2).previous != null) {
                    v2 = vertexInfo.get(v2).previous;
                    //System.out.println("Adding: " + v2);
                    correct.add(0, v2  );
                }
            }

            Set keySet = adjacencyMap.get(curVertex).keySet();
            Iterator it = keySet.iterator();
            while(it.hasNext()) {
                V vert = (V)it.next();
                if(!vertexInfo.get(vert).visited) {
                    vertexInfo.get(vert).previous = curVertex;
                    vertices.add(vert);
                }
            }
        }
        System.out.println(correct.toString());
        return correct;
    }   

    public List<V> shortestPath(V v1, V v2)
    {
        Set keySet = adjacencyMap.keySet();
        Iterator iterator = keySet.iterator();
        clearMarks();
        ArrayList<V> correct = new ArrayList<V>();
        if(v1 == null || v2 == null)
            throw new NullPointerException();
        else
        if(!(containsVertex(v1) && containsVertex(v2)))
            throw new IllegalArgumentException();
        else {

            PriorityQueue<V> vertices = new PriorityQueue<V>();
            vertices.add(v1);
            while(vertices.size() > 0) //Check that q is not empty
            {
                V vertex = vertices.remove(); //dequeue next element from q
                vertexInfo.get(vertex).visited = true;
                if(vertex.equals(v2)) {
                    correct.add(v2);
                    //System.out.println("Adding: " + v2);
                    while(vertexInfo.get(v2).previous != null) {
                        v2 = vertexInfo.get(v2).previous;
                        //System.out.println("Adding: " + v2);
                        correct.add(0, v2  );
                    }
                }
                keySet = adjacencyMap.get(vertex).keySet();
                iterator = keySet.iterator();
                while(iterator.hasNext()) {
                    V v = (V)iterator.next();
                    if(!vertexInfo.get(v).visited) {
                        vertexInfo.get(v).previous = vertex;
                        vertices.add(v);
                    }
                }
            }
        }   
        return correct;
    }

    public void clearMarks()
    {

        Iterator iterator = adjacencyMap.keySet().iterator();
        while(iterator.hasNext())
            vertexInfo.get((V)(iterator.next())).visited = false;

    }

    public Set<V> getVertices()
    {
        return adjacencyMap.keySet();
    }

}
