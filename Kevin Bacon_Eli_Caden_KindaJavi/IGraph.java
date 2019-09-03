import java.util.*;

public interface IGraph<V, E> {
    // vertex-related methods
    public void addVertex(V v);
    public boolean containsVertex(V v);
    public Collection<V> neighbors(V v);
    public Collection<V> vertices();

    // edge-related methods
    public void addEdge(V v1, V v2, E e);
    public void addEdge(V v1, V v2, E e, int weight);
    public boolean containsEdge(V v1, V v2);
    public E edge(V v1, V v2);
    public Collection<E> edges();
    public int edgeWeight(V v1, V v2);
}
