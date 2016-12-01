package se350.logistics;

import java.util.HashMap;
import java.util.List;

public class Dijkstra {
    private Graph graph;
    private String origin;
    private Graph.Edge[] g;

    public Dijkstra(List<Graph.Edge> graphEdge) {
        Graph.Edge[] graphEdgeArray = new Graph.Edge[graphEdge.size()];
        for (int i = 0; i < graphEdge.size(); i++) {
            graphEdgeArray[i] =graphEdge.get(i);
        }
        this.graph = new Graph(graphEdgeArray);
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void printPath(String destination) {
        graph.dijkstra(origin);
        graph.printPath(destination);
    }

    public HashMap<Integer, List<String>> shortestPath(String destination) {
        graph.dijkstra(origin);
        return graph.shortestPath(destination);
    }
}
