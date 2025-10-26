package mst;

import java.util.*;

public class PrimAlgorithm {
    public long operations = 0;

    public Result run(Graph g, String start) {
        operations = 0;
        Set<String> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        List<Edge> mst = new ArrayList<>();
        long startTime = System.nanoTime();

        visited.add(start);
        operations++; // initial add
        for (Edge e : g.adj.getOrDefault(start, Collections.emptyList())) {
            pq.add(e); operations++;
        }

        while (!pq.isEmpty() && mst.size() < g.V()-1) {
            Edge e = pq.poll(); operations++;
            String u = e.from, v = e.to;
            String next = visited.contains(u) ? v : u;
            if (visited.contains(next)) { operations++; continue; } // both visited
            mst.add(e); operations++;
            visited.add(next); operations++;
            for (Edge ne : g.adj.getOrDefault(next, Collections.emptyList())) {
                String other = ne.other(next);
                if (!visited.contains(other)) { pq.add(ne); operations++; }
            }
        }

        long endTime = System.nanoTime();
        double timeMs = (endTime - startTime) / 1_000_000.0;
        int total = mst.stream().mapToInt(x->x.weight).sum();
        return new Result(mst, total, operations, timeMs);
    }
}
