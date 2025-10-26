package mst;

import java.util.*;

public class Graph {
    public Set<String> vertices = new LinkedHashSet<>();
    public List<Edge> edges = new ArrayList<>();
    public Map<String,List<Edge>> adj = new HashMap<>();

    public void addVertex(String v) {
        vertices.add(v);
        adj.putIfAbsent(v, new ArrayList<>());
    }

    public void addEdge(String u, String v, int w) {
        addVertex(u); addVertex(v);
        Edge e = new Edge(u,v,w);
        edges.add(e);
        adj.get(u).add(e);
        adj.get(v).add(e);
    }

    public int V() { return vertices.size(); }
    public int E() { return edges.size(); }
}
