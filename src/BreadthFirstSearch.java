import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {
    private Map<V, Boolean> marked = new HashMap<>();
    private Map<V, V> edgeTo = new HashMap<>();
    private V source;

    public BreadthFirstSearch(WeightedGraph<V> graph, V source) {
        this.source = source;
        bfs(graph, source);
    }

    private void bfs(WeightedGraph<V> graph, V source) {
        Queue<V> queue = new LinkedList<>();
        queue.add(source);
        marked.put(source, true);

        while (!queue.isEmpty()) {
            V v = queue.poll();
            for (Vertex<V> w : graph.getVertex(v).getAdjacentVertices().keySet()) {
                if (!marked.getOrDefault(w.getData(), false)) {
                    queue.add(w.getData());
                    marked.put(w.getData(), true);
                    edgeTo.put(w.getData(), v);
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(V vertex) {
        return marked.getOrDefault(vertex, false);
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