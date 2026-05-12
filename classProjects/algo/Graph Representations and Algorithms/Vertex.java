import java.util.HashMap;
import java.util.Map;

public class Vertex {
    private String id;
    /**
     * For unweight graph
     * Set<Vertex> neighbors;
     * And removes weight everywhere
     */
    private Map<Vertex, Double> neighbors;

    public Vertex(String id) {
        this.id = id;
        this.neighbors = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public Map<Vertex, Double> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(Vertex v, double weight) {
        neighbors.put(v, weight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex)) return false;
        Vertex v = (Vertex) o;
        return id.equals(v.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
