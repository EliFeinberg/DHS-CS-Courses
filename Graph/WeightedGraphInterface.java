public interface WeightedGraphInterface<T>
{
    // tests if graph is empty
    boolean empty();
    
    // tests if graph is full
    boolean full();
    
    // Precondition: Vertex is not already in graph
    // Precondition: Vertex is not null
    // adds vertex to graph
    void addVertex(T vertex);
        
    // returns true if graph contains vertex
    boolean hasVertex(T vertex);
    
    // adds an edge with the specified weight from fromVertex to toVertex
    void setEdge(T fromVertex, T toVertex,int weight);
    
    // if edge from fromVertex to toVertex exists, return the weight of
    // the edge; otherwise, returns a special "null-edge" value.
    int weightIs(T fromVertex, T toVertex);
    
    // returns a queue of the vertices that are adjacent to the vertex
    QueueInterface<T> getAdjacentVertices(T vertex);
    
    // sets marks for all vertices to false
    void clearMarks();
    
    // sets mark for vertex to true
    void markVertex(T vertex);
    
    // returns true if vertex is marked
    boolean isMarked(T vertex);
    
    // returns an unmarked vertex if any exist, otherwise returns null
    T getUnmarked();  
    
    // return a String representation of this graph
    String toString();
}
