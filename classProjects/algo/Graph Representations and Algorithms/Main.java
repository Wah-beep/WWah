import java.util.*;

public class Main {
    public static void main(String[] args) {
        
        //Graph g = new Graph(); // Adjacency List
        GraphAM g = new GraphAM(100); // Adjacency Matrix
        
        Runtime runtime = Runtime.getRuntime();

        // ---------------- Graph Build ----------------
        runtime.gc();
        long memBeforeBuild = runtime.totalMemory() - runtime.freeMemory();
        long startBuild = System.nanoTime();

        try {
            g.loadFromCSV("test.csv");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        long endBuild = System.nanoTime();
        long memAfterBuild = runtime.totalMemory() - runtime.freeMemory();

        System.out.println("Graph build time: " + (endBuild - startBuild) / 1e6 + " ms");
        System.out.println("Memory used (build): " + (memAfterBuild - memBeforeBuild) / 1024 + " KB");

        // ---------------- BFS ----------------
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter source: ");
        String source = scanner.nextLine();

        System.out.print("Enter destination: ");
        String dest = scanner.nextLine();

        runtime.gc();
        long memBeforeBFS = runtime.totalMemory() - runtime.freeMemory();
        long startBFS = System.nanoTime();

        List<String> path = g.bfsPath(source, dest);

        long endBFS = System.nanoTime();
        long memAfterBFS = runtime.totalMemory() - runtime.freeMemory();

        System.out.println("BFS time: " + (endBFS - startBFS) / 1e6 + " ms");
        System.out.println("Memory used (BFS): " + (memAfterBFS - memBeforeBFS) / 1024 + " KB");

        if (path.isEmpty()) {
            System.out.println("No path found.");
        } else {
            System.out.println("Path: " + path);
        }

        // ---------------- Prim ----------------
        runtime.gc();
        long memBeforePrim = runtime.totalMemory() - runtime.freeMemory();
        long startPrim = System.nanoTime();

        g.primMST(source);

        long endPrim = System.nanoTime();
        long memAfterPrim = runtime.totalMemory() - runtime.freeMemory();

        System.out.println("Prim time: " + (endPrim - startPrim) / 1e6 + " ms");
        System.out.println("Memory used (Prim): " + (memAfterPrim - memBeforePrim) / 1024 + " KB");

        scanner.close();

        /*
        // Dense Graph Contrast
        int n = 500;
        double prob = 0.7;

        var edges = GraphGenerator.generateEdges(n, prob);

        MatrixGraph mg = new MatrixGraph(n);
        ListGraph lg = new ListGraph(n);

        for (int[] e : edges) {
            mg.addEdge(e[0], e[1]);
            lg.addEdge(e[0], e[1]);
        }

        System.out.println("Edges: " + edges.size());

        System.out.println("Matrix memory (approx bytes): " + mg.estimateMemoryBytes());
        System.out.println("List memory (approx bytes): " + lg.estimateMemoryBytes());
        */
    }
}