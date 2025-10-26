package mst;

import com.google.gson.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Utils {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static List<JsonObject> readGraphsFromFile(String path) throws IOException {
        String s = new String(Files.readAllBytes(Paths.get(path)));
        JsonObject root = JsonParser.parseString(s).getAsJsonObject();
        List<JsonObject> list = new ArrayList<>();
        for (JsonElement e : root.getAsJsonArray("graphs")) list.add(e.getAsJsonObject());
        return list;
    }

    public static JsonObject readJson(String path) throws IOException {
        String s = new String(Files.readAllBytes(Paths.get(path)));
        return JsonParser.parseString(s).getAsJsonObject();
    }

    public static Graph graphFromJson(JsonObject jobj) {
        Graph g = new Graph();
        for (JsonElement ve : jobj.getAsJsonArray("nodes")) {
            g.addVertex(ve.getAsString());
        }
        for (JsonElement ee : jobj.getAsJsonArray("edges")) {
            JsonObject e = ee.getAsJsonObject();
            String from = e.get("from").getAsString();
            String to = e.get("to").getAsString();
            int w = e.get("weight").getAsInt();
            g.addEdge(from, to, w);
        }
        return g;
    }

    public static void writeOutput(String path, JsonObject output) throws IOException {
        try (Writer w = Files.newBufferedWriter(Paths.get(path))) {
            w.write(gson.toJson(output));
        }
    }
}
