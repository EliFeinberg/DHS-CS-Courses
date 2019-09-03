import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractGraph<V, E> implements IGraph<V, E>  {
	
	/** Default weight of an edge in an undirected graph (1). */
    public static final int DEFAULT_EDGE_WEIGHT = 1;
    
    /** Maximum distance between two vertices.  Treat this as "infinity". */
    public static final int MAX_DISTANCE = Integer.MAX_VALUE;
    
    // data fields
    protected final Map<V, Map<V, EdgeInfo<E>>> adjacencyMap;  	// [vertices] -> [edge]
    protected final Map<V, VertexInfo<V>> vertexInfo;  			// [vertex] -> [info]
    protected final List<E> edgeList;               			// list of all edges in graph
    
    /** 
     * Constructs a new empty undirected graph.
     */
    public AbstractGraph() {
        this.adjacencyMap = new HashMap<V, Map<V, EdgeInfo<E>>>();
        this.vertexInfo = new HashMap<V, VertexInfo<V>>();
        this.edgeList = new ArrayList<E>();
    }
    
    /** 
     * Returns this graph's collection of edges.
     * The collection should be considered read-only.
     * If this graph contains no edges, returns an empty collection.
     */
    public final Collection<E> edges() {
        return Collections.unmodifiableList(this.edgeList);
    }    
    
    /** 
     * Returns a string representation of this graph and its vertices. 
     */
    public String toString() {
        String result = "Graph {\n";
        for (V v : this.vertices()) {
            result += "  vertex: " + this.vertexInfo.get(v) + " -> " + this.neighbors(v) + "\n";
        }
        result += "  edges: " + this.edges() + "\n";
        result += "}";
        return result;
    }
    
    /** 
     * Returns a collection of the vertices in this graph.
     * The collection should be considered read-only.
     * If this graph contains no vertices, returns an empty collection.
     */
    public final Collection<V> vertices() {
        return Collections.unmodifiableSet(this.adjacencyMap.keySet());
    }    
    
    /** 
     * Tests the given arguments for null.
     * @param args The arguments to examine.
     * @throws NullPointerException If any argument is null.
     */
    protected void checkForNull(Object arg) {
        if (arg == null) {
            throw new NullPointerException("Argument must not be null");
        }
    }
    
    /** 
     * Tests the given vertices for null and for membership in the graph.
     * @param v The vertex to examine.
     * @throws IllegalArgumentException If any vertex is not part of this graph.
     * @throws NullPointerException If any vertex is null.
     */
    protected void checkVertex(V v) {
        this.checkForNull(v);
        if (!this.containsVertex(v)) {
            throw new IllegalArgumentException("Vertex not found in graph: " + v);
        }
    }
    
    /** 
     * Tests the given vertices for null and for membership in the graph.
     * @param v1 First vertex to examine.
     * @param v2 Second vertex to examine.
     * @throws IllegalArgumentException If any vertex is not part of this graph.
     * @throws NullPointerException If any vertex is null.
     */
    protected void checkVertices(V v1, V v2) {
        this.checkForNull(v1);
        this.checkForNull(v2);
        if (!this.containsVertex(v1)) {
            throw new IllegalArgumentException("Vertex not found in graph: " + v1);
        }
        if (!this.containsVertex(v2)) {
            throw new IllegalArgumentException("Vertex not found in graph: " + v2);
        }        
    } 
    
    /**
     * Resets all distance / previous / visited markings from vertex info
     * objects in this graph.
     */
    protected void clearVertexInfo() {
        for (VertexInfo<V> info : this.vertexInfo.values()) {
            info.clear();
        }
    }    
}
