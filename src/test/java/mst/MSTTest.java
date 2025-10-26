package mst;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.google.gson.*;

import java.nio.file.*;
import java.util.*;

public class MSTTest {

    @Test
    public void testSampleGraphsProduceSameTotal() throws Exception {
        JsonObject input = Utils.readJson("resources/input.json");
        JsonArray graphs = input.getAsJsonArray("graphs");
        for (JsonElement ge : graphs) {
            JsonObject gobj = ge.getAsJsonObject();
            Graph g = Utils.graphFromJson(gobj);
            PrimAlgorithm prim = new PrimAlgorithm();
            Result rp = prim.run(g, g.vertices.iterator().next());
            KruskalAlgorithm kr = new KruskalAlgorithm();
            Result rk = kr.run(g);
            assertEquals(rp.totalCost, rk.totalCost, "Total cost must match for graph id " + gobj.get("id"));
            assertEquals(g.V()-1, rp.mstEdges.size());
            assertEquals(g.V()-1, rk.mstEdges.size());
        }
    }
}
