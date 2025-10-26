package mst;

public class Edge implements Comparable<Edge> {
    public String from;
    public String to;
    public int weight;

    public Edge(String from, String to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public String other(String v) {
        return from.equals(v) ? to : from;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }

    @Override
    public String toString() {
        return String.format("{\"from\": \"%s\", \"to\": \"%s\", \"weight\": %d}", from, to, weight);
    }
}
