/** Represents information stored in each edge. */
public class EdgeInfo<E> {
    /** The edge itself. */
    public E e;
    
    /** The edge's weight; 1 in an unweighted graph. */
    public int weight;
    
    /** Constructs information about the given edge with the default edge weight. */
    public EdgeInfo(E e) {
    	this(e, AbstractGraph.DEFAULT_EDGE_WEIGHT);
    }
    
    /** Constructs information about the given edge with the given weight. */
    public EdgeInfo(E e, int weight) {
        if (e == null) {
            throw new NullPointerException("Argument must not be null");
        }    
        if (weight < 0) {
            throw new IllegalArgumentException("negative weight");
        }   
        this.e = e;
        this.weight = weight;
    }
    
    /** Returns a string representation of the information about this edge. */
    public String toString() {
        return "(" + this.e + ":" + this.weight + ")";
    }
}
