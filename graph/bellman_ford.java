package graph;

/*
You are given a directed weighted graph G consisting of A vertices.
There are E edges of the graph given by the 2D array B where the i-th edge is from B[i][0] to B[i][1] and has a weight B[i][2].
Find the shortest distance from the vertex C to D. Return 109 if there exists a negative weight cycle in the graph or there exist no path from C to D.
*/

import java.util.Arrays;

public class bellman_ford {
    static void main() {

    }

    static public class Solution {
        private static final int INF = (int) 1e9;

        public int solve(int vertices, int[][] edges, int from, int to) {
            int[] dist = new int[vertices];
            Arrays.fill(dist, INF);
            dist[from - 1] = 0;
            boolean[] negativeNodes = new boolean[vertices];
            for (int i = 0; i < vertices; i++) {
                for (int[] edge : edges) {
                    int src = edge[0] - 1, dest = edge[1] - 1, weight = edge[2];
                    if ((dist[src] != INF) && dist[dest] > dist[src] + weight) {
                        dist[dest] = dist[src] + weight;
                        if (i == vertices - 1) negativeNodes[dest] = true;
                    }
                }
            }
            for (int[] edge : edges) {
                int src = edge[0] - 1, dest = edge[1] - 1;
                if (negativeNodes[src]) negativeNodes[dest] = true;
            }
            if (negativeNodes[to - 1]) dist[to - 1] = INF;
            return dist[to - 1];
        }
    }
}

