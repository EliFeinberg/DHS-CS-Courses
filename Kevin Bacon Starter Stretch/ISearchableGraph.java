import java.util.List;

public interface ISearchableGraph<V, E> extends IGraph<V, E> {
    // graph/path-related methods
    //    public List<V> minimumWeightPath(V v1, V v2);
    public boolean reachable(V v1, V v2);

    public List<V> shortestPath(V v1, V v2);
}
