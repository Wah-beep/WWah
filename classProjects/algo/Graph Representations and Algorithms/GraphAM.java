import java.io.*;
import java.util.*;

// If this is showing errors, fixes in Edge.java
public class GraphAM {
    private Map<String, Integer> idToIndex;
    private List<String> indexToId;
    private double[][] matrix;
    private int size;
    private int capacity;

    public GraphAM(int initialCapacity) {
        this.capacity = initialCapacity;
        this.size = 0;

        idToIndex = new HashMap<>();
        indexToId = new ArrayList<>();
        matrix = new double[capacity][capacity];
    }

    // Resize matrix if needed
    private void resize() {
        capacity *= 2;
        double[][] newMatrix = new double[capacity][capacity];

        for (int i = 0; i < size; i++) {
            System.arraycopy(matrix[i], 0, newMatrix[i], 0, size);
        }

        matrix = newMatrix;
    }

    // Add vertex
    public void addVertex(String id) {
        if (idToIndex.containsKey(id)) return;

        if (size == capacity) {
            resize();
        }

        idToIndex.put(id, size);
        indexToId.add(id);
        size++;
    }

    // Add edge (directed, weighted)
    public void addEdge(String id1, String id2, double weight) {
        addVertex(id1);
        addVertex(id2);

        int i = idToIndex.get(id1);
        int j = idToIndex.get(id2);

        matrix[i][j] = weight;
        matrix[j][i] = weight;
    }

    // Get adjacent vertices
    public List<String> adjacent(String id) {
        List<String> result = new ArrayList<>();

        Integer i = idToIndex.get(id);
        if (i == null) return result;

        for (int j = 0; j < size; j++) {
            if (matrix[i][j] != 0) {
                result.add(indexToId.get(j));
            }
        }

        return result;
    }

    // Check if edge exists
    public boolean hasEdge(String id1, String id2) {
        Integer i = idToIndex.get(id1);
        Integer j = idToIndex.get(id2);

        if (i == null || j == null) return false;

        return matrix[i][j] != 0;
    }

    // Load from tab-separated edge list file
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
        }

        br.close();
    }

    public List<String> bfsPath(String startId, String endId) {
        if (!idToIndex.containsKey(startId) || !idToIndex.containsKey(endId)) {
            return Collections.emptyList();
        }

        int n = indexToId.size();

        boolean[] visited = new boolean[n];
        int[] parent = new int[n];
        int[] distance = new int[n];

        Arrays.fill(parent, -1);

        Queue<Integer> queue = new LinkedList<>();

        int start = idToIndex.get(startId);
        int end = idToIndex.get(endId);

        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            // If reached destination, stop early
            if (current == end) break;

            // 🔁 Get neighbors (works for adjacency list version)
            for (String neighborId : adjacent(indexToId.get(current))) {
                int neighbor = idToIndex.get(neighborId);

                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    parent[neighbor] = current;
                    distance[neighbor] = distance[current] + 1;
                    queue.add(neighbor);
                }
            }
        }

        // No path found
        if (!visited[end]) {
            return Collections.emptyList();
        }

        // Reconstruct path
        List<String> path = new ArrayList<>();
        for (int at = end; at != -1; at = parent[at]) {
            path.add(indexToId.get(at));
        }

        Collections.reverse(path);

        System.out.println("Distance: " + distance[end]);
        return path;
    }

    public void primMST(String startId) {
        if (!idToIndex.containsKey(startId)) {
            System.out.println("Start vertex not found.");
            return;
        }

        int n = size;

        boolean[] visited = new boolean[n];
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingDouble(e -> e.weight));

        int start = idToIndex.get(startId);

        visited[start] = true;

        // add all edges from start
        for (int j = 0; j < n; j++) {
            if (matrix[start][j] != 0) {
                pq.add(new Edge(start, j, matrix[start][j]));
            }
        }

        double totalWeight = 0;
        int edgesUsed = 0;

        while (!pq.isEmpty() && edgesUsed < n - 1) {
            Edge edge = pq.poll();

            if (visited[edge.to]) continue;

            // accept edge
            visited[edge.to] = true;
            totalWeight += edge.weight;
            edgesUsed++;

            // expand from new node
            int u = edge.to;

            for (int v = 0; v < n; v++) {
                if (!visited[v] && matrix[u][v] != 0) {
                    pq.add(new Edge(u, v, matrix[u][v]));
                }
            }
        }

        System.out.println("MST total weight: " + totalWeight);
        System.out.println("Edges in MST: " + edgesUsed);

        // verification
        if (edgesUsed == n - 1) {
            System.out.println("Valid MST: YES (V - 1 edges)");
        } else {
            System.out.println("Invalid MST: graph may be disconnected");
        }
    }
}