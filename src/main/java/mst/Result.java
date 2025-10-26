package mst;

import java.util.List;

public class Result {
    public List<Edge> mstEdges;
    public int totalCost;
    public long operations;
    public double timeMs;

    public Result(List<Edge> mstEdges, int totalCost, long operations, double timeMs) {
        this.mstEdges = mstEdges;
        this.totalCost = totalCost;
        this.operations = operations;
        this.timeMs = timeMs;
    }
}
