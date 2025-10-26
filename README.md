# MST Assignment — Console Java Implementation

Includes:
- Prim's and Kruskal's algorithms implemented in Java (console-based).
- JSON input/output using Gson.
- JUnit tests.


## 📘 Overview
This project implements and compares **Prim’s** and **Kruskal’s** algorithms to find the **Minimum Spanning Tree (MST)** of weighted undirected graphs.  
It simulates a **city transportation optimization problem**, where each vertex represents a district and each edge represents a potential road with a construction cost.

The project reads multiple graphs from a JSON file, computes MSTs using both algorithms, records performance metrics, and outputs results to `output.json`.


## Project structure
- `src/main/java/mst` — Java sources.
- `src/test/java/mst` — JUnit tests.
- `resources/input.json` — sample input (your graphs).
- `resources/output.json` — sample output (will be produced by program).
- `pom.xml` — Maven configuration (Gson + JUnit).





## Notes on measurements
- Execution time measured with `System.nanoTime()` and saved as milliseconds (double).
- Operation counters are approximations (incremented at key algorithmic events such as queue operations, unions, finds, and comparisons).

## Files included
See repository structure above.


# MST Assignment — Small, Medium & Large Graphs Version

This project includes three graph datasets for testing:
- **Small graph** — 5 vertices, simple test.
- **Medium graph** — 12 vertices, medium density.
- **Large graph** — 27 vertices, scalability test.

All are merged into `resources/input.json` and used by `mst.Main`.

## Run Instructions
```bash
mvn compile
mvn exec:java -Dexec.mainClass="mst.Main" -Dexec.args="resources/input.json resources/output.json"
```
or run directly in your IDE.

Results (Prim vs Kruskal) will be saved in `resources/output.json`.



---

## ⚙️ Features
✅ Reads graph data from JSON (`resources/input.json`)  
✅ Implements both **Prim’s** and **Kruskal’s** algorithms  
✅ Calculates:
- MST edges
- Total MST cost
- Number of operations
- Execution time (ms)  
  ✅ Compares results between algorithms  
  ✅ Includes **automated JUnit tests** for correctness and performance  
  ✅ Datasets: **Small**, **Medium**, and **Large** graphs  



mst_assignment/
│
├── pom.xml # Maven configuration (Gson + JUnit)
├── README.md # This file
│
├── resources/
│ ├── input.json # Input graphs (small, medium, large)
│ └── output.json # Output results written by program
│
├── src/
│ ├── main/java/mst/
│ │ ├── Graph.java # Graph data structure
│ │ ├── Edge.java # Edge representation
│ │ ├── PrimAlgorithm.java # Prim’s MST implementation
│ │ ├── KruskalAlgorithm.java # Kruskal’s MST implementation
│ │ ├── Result.java # Holds algorithm results
│ │ ├── Utils.java # JSON and helper utilities
│ │ └── Main.java # Entry point
│ │
│ └── test/java/mst/
│ └── MSTTest.java # Automated JUnit tests