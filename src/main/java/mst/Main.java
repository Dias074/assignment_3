package mst;

import com.google.gson.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String inputPath = args.length>0 ? args[0] : "resources/input.json";
        String outputPath = args.length>1 ? args[1] : "resources/output.json";
        JsonObject input = Utils.readJson(inputPath);
        JsonArray graphs = input.getAsJsonArray("graphs");
        JsonArray results = new JsonArray();

        for (JsonElement ge : graphs) {
            JsonObject gobj = ge.getAsJsonObject();
            Graph g = Utils.graphFromJson(gobj);
            int vertices = g.V();
            int edges = g.E();

            PrimAlgorithm prim = new PrimAlgorithm();
            String start = g.vertices.iterator().next();
            Result rPrim = prim.run(g, start);

            KruskalAlgorithm kruskal = new KruskalAlgorithm();
            Result rKruskal = kruskal.run(g);

            JsonObject res = new JsonObject();
            res.addProperty("graph_id", gobj.has("id") ? gobj.get("id").getAsInt() : -1);
            JsonObject inStats = new JsonObject();
            inStats.addProperty("vertices", vertices);
            inStats.addProperty("edges", edges);
            res.add("input_stats", inStats);

            JsonObject primObj = new JsonObject();
            primObj.add("mst_edges", edgesToJsonArray(rPrim.mstEdges));
            primObj.addProperty("total_cost", rPrim.totalCost);
            primObj.addProperty("operations_count", rPrim.operations);
            primObj.addProperty("execution_time_ms", rPrim.timeMs);

            JsonObject krObj = new JsonObject();
            krObj.add("mst_edges", edgesToJsonArray(rKruskal.mstEdges));
            krObj.addProperty("total_cost", rKruskal.totalCost);
            krObj.addProperty("operations_count", rKruskal.operations);
            krObj.addProperty("execution_time_ms", rKruskal.timeMs);

            res.add("prim", primObj);
            res.add("kruskal", krObj);
            results.add(res);
        }

        JsonObject out = new JsonObject();
        out.add("results", results);
        Utils.writeOutput(outputPath, out);
        System.out.println("Output written to: " + outputPath);
    }

    private static JsonArray edgesToJsonArray(List<Edge> edges) {
        JsonArray arr = new JsonArray();
        for (Edge e : edges) {
            JsonObject o = new JsonObject();
            o.addProperty("from", e.from);
            o.addProperty("to", e.to);
            o.addProperty("weight", e.weight);
            arr.add(o);
        }
        return arr;
    }
}
