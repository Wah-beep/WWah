public class MatrixGraph {
    private boolean[][] matrix;

    public MatrixGraph(int n) {
        matrix = new boolean[n][n];
    }

    public void addEdge(int i, int j) {
        matrix[i][j] = true;
        matrix[j][i] = true;
    }

    public long estimateMemoryBytes() {
        // boolean ≈ 1 byte (JVM-dependent, but good estimate)
        return (long) matrix.length * matrix.length;
    }
}