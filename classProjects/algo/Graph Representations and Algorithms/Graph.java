import java.io.*;
import java.util.*;

// If this is showing errors, fixes in Edge.java
public class Graph {
    private Map<String, Vertex> vertices;

    public Graph() {
        vertices = new HashMap<>();
    }
    
    // Add a vertex
    public void addVertex(String id) {
        vertices.putIfAbsent(id, new Vertex(id));
    }

    // Add edge (directed, weighted)
    public void addEdge(String id1, String id2, double weight) {
        addVertex(id1);
        addVertex(id2);

        Vertex v1 = vertices.get(id1);
        Vertex v2 = vertices.get(id2);

        v1.addNeighbor(v2, weight);
        v2.addNeighbor(v1, weight);
        // Remove the comments for undirected graph
        //v2.addNeightbor(v1, weight);
    }

    // Get adjacency list
    public List<String> adjacent(String id) {
        Vertex v = vertices.get(id);
        if (v == null) return Collections.emptyList();

        List<String> result = new ArrayList<>();
        for (Vertex neighbor : v.getNeighbors().keySet()) {
            result.add(neighbor.getId());
        }
        return result;
    }

    // Check if edge exists
    public boolean hasEdge(String id1, String id2) {
        Vertex v1 = vertices.get(id1);
        Vertex v2 = vertices.get(id2);

        if (v1 == null || v2 == null) return false;

        return v1.getNeighbors().containsKey(v2);
    }

    // Load from tab-separated file
    public void loadFromCSV(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;

        // Skip header
        br.readLine();

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");

            String userId = "U" + parts[0];
            String movieId = "M" + parts[1];
            double rating = Double.parseDouble(parts[2]);

            addEdge(userId, movieId, rating);

            // If you want undirected bipartite graph:
            // addEdge(userId, movieId, rating);
        }

        br.close();
    }

    public List<String> bfsPath(String startId, String endId) {
        if (!vertices.containsKey(startId) || !vertices.containsKey(endId)) {
            return Collections.emptyList();
        }

        Map<Vertex, Boolean> visited = new HashMap<>();
        Map<Vertex, Vertex> parent = new HashMap<>();
        Map<Vertex, Integer> distance = new HashMap<>();

        Queue<Vertex> queue = new LinkedList<>();

        Vertex start = vertices.get(startId);
        Vertex end = vertices.get(endId);

        queue.add(start);
        visited.put(start, true);
        distance.put(start, 0);

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();

            if (current == end) break;

            for (Vertex neighbor : current.getNeighbors().keySet()) {
                if (!visited.containsKey(neighbor)) {
                    visited.put(neighbor, true);
                    parent.put(neighbor, current);
                    distance.put(neighbor, distance.get(current) + 1);
                    queue.add(neighbor);
                }
            }
        }

        if (!visited.containsKey(end)) {
            return Collections.emptyList();
        }

        // reconstruct path
        List<String> path = new ArrayList<>();
        for (Vertex at = end; at != null; at = parent.get(at)) {
            path.add(at.getId());
        }

        Collections.reverse(path);

        System.out.println("Distance: " + distance.get(end));
        return path;
    }

    public void primMST(String startId) {
        if (!vertices.containsKey(startId)) {
            System.out.println("Start vertex not found.");
            return;
        }

        Set<Vertex> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingDouble(e -> e.weight));

        Vertex start = vertices.get(startId);
        visited.add(start);

        // add edges from start
        for (Map.Entry<Vertex, Double> entry : start.getNeighbors().entrySet()) {
            pq.add(new Edge(start, entry.getKey(), entry.getValue()));
        }

        double totalWeight = 0;
        int edgesUsed = 0;

        while (!pq.isEmpty() && edgesUsed < vertices.size() - 1) {
            Edge edge = pq.poll();

            if (visited.contains(edge.to)) continue;

            // accept edge
            visited.add(edge.to);
            totalWeight += edge.weight;
            edgesUsed++;

            // expand
            for (Map.Entry<Vertex, Double> entry : edge.to.getNeighbors().entrySet()) {
                if (!visited.contains(entry.getKey())) {
                    pq.add(new Edge(edge.to, entry.getKey(), entry.getValue()));
                }
            }
        }

        System.out.println("MST total weight: " + totalWeight);
        System.out.println("Edges in MST: " + edgesUsed);

        if (edgesUsed == vertices.size() - 1) {
            System.out.println("Valid MST: YES");
        } else {
            System.out.println("Invalid MST: graph disconnected");
        }
    }
}
