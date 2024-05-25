import java.util.HashMap;
import java.util.Map;

public class WeightedGraph<V> {
    private Map<V, Vertex<V>> vertices = new HashMap<>();
    private boolean directed;

    public WeightedGraph(boolean directed) {
        this.directed = directed;
    }

    public void addEdge(V source, V destination, double weight) {
        Vertex<V> sourceVertex = vertices.computeIfAbsent(source, Vertex::new);
        Vertex<V> destinationVertex = vertices.computeIfAbsent(destination, Vertex::new);
        sourceVertex.setAdjacentVertex(destinationVertex, weight);
        if (!directed) {
            destinationVertex.setAdjacentVertex(sourceVertex, weight);
        }
    }

    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }

    public Map<V, Vertex<V>> getVertices() {
        return vertices;
    }
}