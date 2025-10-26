package mst;

import java.util.*;

public class KruskalAlgorithm {
    public long operations = 0;

    static class DSU {
        Map<String,String> parent = new HashMap<>();
        Map<String,Integer> rank = new HashMap<>();
        long ops = 0;

        public void makeSet(Collection<String> vs) {
            for (String v: vs) { parent.put(v,v); rank.put(v,0); ops++; }
        }

        public String find(String x) {
            ops++;
            if (!parent.get(x).equals(x)) parent.put(x, find(parent.get(x)));
            return parent.get(x);
        }

        public boolean union(String a, String b) {
            ops++;
            String ra = find(a), rb = find(b);
            if (ra.equals(rb)) return false;
            if (rank.get(ra) < rank.get(rb)) parent.put(ra, rb);
            else if (rank.get(rb) < rank.get(ra)) parent.put(rb, ra);
            else { parent.put(rb, ra); rank.put(ra, rank.get(ra)+1); }
            return true;
        }
    }

    public Result run(Graph g) {
        operations = 0;
        List<Edge> edges = new ArrayList<>(g.edges);
        edges.sort(Comparator.naturalOrder());
        operations += edges.size();

        DSU dsu = new DSU();
        dsu.makeSet(g.vertices);
        operations += dsu.ops;

        List<Edge> mst = new ArrayList<>();
        long startTime = System.nanoTime();
        for (Edge e : edges) {
            operations++;
            if (dsu.union(e.from, e.to)) {
                mst.add(e); operations++;
                if (mst.size() == g.V()-1) break;
            }
        }
        long endTime = System.nanoTime();
        double timeMs = (endTime - startTime) / 1_000_000.0;
        long totalOps = operations + dsu.ops;
        int total = mst.stream().mapToInt(x->x.weight).sum();
        return new Result(mst, total, totalOps, timeMs);
    }
}
