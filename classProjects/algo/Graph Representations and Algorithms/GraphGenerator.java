import java.util.*;

public class GraphGenerator {
    public static List<int[]> generateEdges(int n, double prob) {
        Random rand = new Random();
        List<int[]> edges = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (rand.nextDouble() < prob) {
                    edges.add(new int[]{i, j});
                }
            }
        }

        return edges;
    }
}