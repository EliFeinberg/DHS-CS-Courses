/** Represents information stored in each vertex. */
public class VertexInfo<V>  {
    /** The vertex itself. */
    public V v;
    
    /** A reference to a 'previous' vertex.  Useful for path searching. */
    public V previous;

    /** A mark for whether this vertex has been visited.  Useful for path searching. */
    public boolean visited;

    /** A mark for the minimum distance to reach this vertex.  Useful for Dijkstra's algorithm. */
    public int distance;
    
    /** Constructs information for the given vertex. */
    public VertexInfo(V v) {
        if (v == null) {
            throw new NullPointerException("Argument must not be null");
        }
        this.v = v;
        this.clear();
    }
    
    /** Resets the previous, visited, and distance data fields. */
    public void clear() {
        this.previous = null;
        this.visited = false;
        this.distance = AbstractGraph.MAX_DISTANCE;
    }
    
    /** Returns a string representation of the information about this vertex. */
    public String toString() {
        String result = "(" + this.v;
        if (this.previous != null) {
            result += ",previous=" + this.previous;
        }
        if (this.distance < AbstractGraph.MAX_DISTANCE) {
            result += ",distance=" + this.distance;
        }
        if (this.visited) {
            result += ",visited";
        }
        return result + ")";
    }
}
