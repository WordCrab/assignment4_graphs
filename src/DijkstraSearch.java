import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private Map<V, Double> distTo = new HashMap<>();
    private Map<V, V> edgeTo = new HashMap<>();
    private PriorityQueue<Vertex<V>> pq;
    private V source;

    public DijkstraSearch(WeightedGraph<V> graph, V source) {
        this.source = source;
        distTo.put(source, 0.0);

        Comparator<Vertex<V>> comparator = Comparator.comparingDouble(v -> distTo.getOrDefault(v.getData(), Double.MAX_VALUE));
        pq = new PriorityQueue<>(comparator);
        pq.add(graph.getVertex(source));

        while (!pq.isEmpty()) {
            Vertex<V> v = pq.poll();
            for (Map.Entry<Vertex<V>, Double> entry : v.getAdjacentVertices().entrySet()) {
                relax(v, entry.getKey(), entry.getValue());
            }
        }
    }

    private void relax(Vertex<V> v, Vertex<V> w, double weight) {
        double newDist = distTo.getOrDefault(v.getData(), Double.MAX_VALUE) + weight;
        if (newDist < distTo.getOrDefault(w.getData(), Double.MAX_VALUE)) {
            distTo.put(w.getData(), newDist);
            edgeTo.put(w.getData(), v.getData());
            pq.remove(w);
            pq.add(w);
        }
    }

    @Override
    public boolean hasPathTo(V vertex) {
        return distTo.containsKey(vertex);
    }

    @Override
    public List<V> pathTo(V vertex) {
        if (!hasPathTo(vertex)) return null;
        LinkedList<V> path = new LinkedList<>();
        for (V x = vertex; x != null && !x.equals(source); x = edgeTo.get(x)) {
            path.addFirst(x);
        }
        path.addFirst(source);
        return path;
    }
}