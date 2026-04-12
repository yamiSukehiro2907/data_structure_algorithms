package graph;

/*
Given an directed graph having A nodes. A matrix B of size M x 2 is given which represents the M edges such that
there is a edge directed from node B[i][0] to node B[i][1].
Find whether the graph contains a cycle or not, return 1 if cycle is present else return 0.
NOTE:
The cycle must contain atleast two nodes.
There are no self-loops in the graph.
There are no multiple edges between two nodes.
The graph may or may not be connected.
Nodes are numbered from 1 to A.
Your solution will run on multiple test cases. If you are using global variables make sure to clear them.
 */

import java.util.ArrayList;
import java.util.List;

public class cycle_in_directed_graph {
    static void main() {

    }

    static public class Solution {
        private List<List<Integer>> graph;
        private int[] visited;

        public int solve(int totalNodes, int[][] edges) {
            this.graph = new ArrayList<>();
            for (int i = 0; i < totalNodes; i++) graph.add(new ArrayList<>());
            for (int[] edge : edges) graph.get(edge[0] - 1).add(edge[1] - 1);
            visited = new int[totalNodes];
            for (int i = 0; i < totalNodes; i++) if (visited[i] == 0 && cycle(i)) return 1;
            return 0;
        }

        private boolean cycle(int src) {
            visited[src] = 1;
            for (int next : graph.get(src)) {
                if (visited[next] == 1) return true;
                if (cycle(next)) return true;
            }
            visited[src] = 2;
            return false;
        }
    }
}


