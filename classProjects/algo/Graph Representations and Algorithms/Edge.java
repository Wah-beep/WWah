class Edge {
    
    // For Adjacency Matrix
    int from;
    int to;
    double weight;
    
    public Edge(int from, int to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
    

    /*
    // For Adjacency List
    Vertex from;
    Vertex to;
    double weight;

    public Edge(Vertex from, Vertex to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
    */

}