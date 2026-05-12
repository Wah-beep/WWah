import java.util.*;

public class ListGraph {
    private List<List<Integer>> adj;

    public ListGraph(int n) {
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int i, int j) {
        adj.get(i).add(j);
        adj.get(j).add(i);
    }

    public long estimateMemoryBytes() {
        long totalEdges = 0;
        for (List<Integer> list : adj) {
            totalEdges += list.size();
        }

        // each edge stored twice (undirected)
        // estimate: 4 bytes per int
        return totalEdges * 4;
    }
}