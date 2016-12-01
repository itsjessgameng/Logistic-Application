package se350.logistics;

import java.util.*;

class Graph {

    private final Map<String, Vertex> graph; // mapping of vertex names to Vertex objects, built from a set of Edges

    /** One edge of the graph (only used by Graph constructor) */
    public static class Edge {
        public final String v1, v2;
        public final int dist;
        public Edge(String v1, String v2, int dist) {
            this.v1 = v1;
            this.v2 = v2;
            this.dist = dist;
        }
    }

    /** One vertex of the graph, complete with mappings to neighbouring vertices */
    public static class Vertex implements Comparable<Vertex>{
        public final String name;
        public int dist = Integer.MAX_VALUE; // MAX_VALUE assumed to be infinity
        public Vertex previous = null;
        public final Map<Vertex, Integer> neighbours = new HashMap<>();

        private static List<String> pathList = new ArrayList<>();

        public Vertex(String name)
        {
            this.name = name;
        }

        public List<String> getPathList(){
            return pathList;
        }

        private HashMap<Integer,List<String>> printPath()
        {
            HashMap<Integer, List<String>> distanceOfPaths = new HashMap<>();

            if (this == this.previous)
            {
                pathList.add(this.name);

                System.out.printf("\t\u2022 %s", this.name);
            }
            else if (this.previous == null)
            {
                System.out.printf("%s(unreached)", this.name);
            }
            else
            {
                this.previous.printPath();
               pathList.add(this.name);
                System.out.printf(" \u2192 %s", this.name);
            }
            distanceOfPaths.put(dist, pathList);
            return distanceOfPaths;
        }

        private HashMap<Integer,List<String>> printShortestPath()
        {
            HashMap<Integer, List<String>> distanceOfPaths = new HashMap<>();
            if (this == this.previous)
            {
                pathList.add(this.name);
            }
            else if (this.previous == null)
            {
                System.out.printf("%s(unreached)", this.name);
            }
            else
            {
                this.previous.printShortestPath();
                pathList.add(this.name);
            }
            distanceOfPaths.put(dist, pathList);
            return distanceOfPaths;
        }

        public int compareTo(Vertex other)
        {
            if (dist == other.dist)
                return name.compareTo(other.name);

            return Integer.compare(dist, other.dist);
        }

        @Override public String toString()
        {
            return "(" + name + ", " + dist + ")";
        }
    }

    /** Builds a graph from a set of edges */
    public Graph(Edge[] edges) {
        graph = new HashMap<>(edges.length);

        //one pass to find all vertices
        for (Edge e : edges) {
            if (!graph.containsKey(e.v1)) graph.put(e.v1, new Vertex(e.v1));
            if (!graph.containsKey(e.v2)) graph.put(e.v2, new Vertex(e.v2));
        }

        //another pass to set neighbouring vertices
        for (Edge e : edges) {
            graph.get(e.v1).neighbours.put(graph.get(e.v2), e.dist);
            graph.get(e.v2).neighbours.put(graph.get(e.v1), e.dist); // also do this for an undirected graph
        }
    }

    /** Runs dijkstra using a specified source vertex */
    public void dijkstra(String startName) {
        if (!graph.containsKey(startName)) {
            System.err.printf("Graph doesn't contain start vertex \"%s\"\n", startName);
            return;
        }
        final Vertex source = graph.get(startName);
        NavigableSet<Vertex> q = new TreeSet<>();

        // set-up vertices
        for (Vertex v : graph.values()) {
            v.previous = v == source ? source : null;
            v.dist = v == source ? 0 : Integer.MAX_VALUE;
            q.add(v);
        }

        dijkstra(q);
    }

    /** Implementation of dijkstra's algorithm using a binary heap. */
    private void dijkstra(final NavigableSet<Vertex> q) {
        Vertex u, v;
        while (!q.isEmpty()) {

            u = q.pollFirst(); // vertex with shortest distance (first iteration will return source)
            if (u.dist == Integer.MAX_VALUE) break;

            //look at distances to each neighbour
            for (Map.Entry<Vertex, Integer> a : u.neighbours.entrySet()) {
                v = a.getKey(); //the neighbour in this iteration

                final int alternateDist = u.dist + a.getValue();
                if (alternateDist < v.dist) { // shorter path to neighbour found
                    q.remove(v);
                    v.dist = alternateDist;
                    v.previous = u;
                    q.add(v);
                }
            }
        }
    }

    /** Prints a path from the source to the specified vertex */
    public void printPath(String endName) {
        if (!graph.containsKey(endName)) {
            System.err.printf("Graph doesn't contain end vertex \"%s\"\n", endName);
            return;
        }

        HashMap<Integer, List<String>> distanceofPaths = graph.get(endName).printPath();
        int distance = 0;
        List<String> listOfFacility = new ArrayList<>();

        for(Map.Entry<Integer, List<String>> entry: distanceofPaths.entrySet()){
            distance = entry.getKey();
            listOfFacility = entry.getValue();
        }

        System.out.println(" = " + distance + " mi");
        double distanceInDays = (double)distance / (FacilityConnections.fullDayInHours * FacilityConnections.averageSpeed);
        System.out.printf("\t\u2022 %d mi / (%d hours per day * %d mph) = %.2f days\n",distance, FacilityConnections.fullDayInHours,
                FacilityConnections.averageSpeed, distanceInDays);
        
    }
    /** Prints the path from the source to every vertex (output order is not guaranteed) */
    public void printAllPaths() {
        for (Vertex v : graph.values()) {
            v.printPath();
            System.out.println();
        }
    }

    public HashMap<Integer, List<String>> shortestPath(String endName){
        HashMap<Integer, List<String>> distanceofPaths = graph.get(endName).printShortestPath();
        return distanceofPaths;
    }

}
