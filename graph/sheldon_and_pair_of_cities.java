package graph;

/*
Sheldon lives in a country with A cities (numbered from 1 to A) and B bidirectional roads.
Roads are denoted by integer array D, E and F of size M, where D[i] and E[i] denotes the cities and F[i] denotes the distance between the cities.
Now he has many lectures to give in the city and is running short of time, so he asked you C queries, for each query i,
find the shortest distance between city G[i] and H[i].
If the two cities are not connected then the distance between them is assumed to be -1.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class sheldon_and_pair_of_cities {
    static void main() {

    }

    static public class Solution {
        public int[] solve(int cityCount, int roadCount, int queryCount, int[] from, int[] to, int[] time, int[] queryFrom, int[] queryTo) {
            int[][] edges = new int[roadCount][3];
            for (int i = 0; i < roadCount; i++) edges[i] = new int[]{from[i] - 1, to[i] - 1, time[i]};
            Graph graph = new Graph(cityCount, edges);
            int[] distances = new int[queryCount];
            for (int i = 0; i < queryCount; i++) distances[i] = graph.shortestPath(queryFrom[i] - 1, queryTo[i] - 1);
            return distances;
        }

        static class Graph {
            int vertices;
            List<List<int[]>> adj;
            int[][] distances;

            Graph(int vertices, int[][] edges) {
                this.vertices = vertices;
                this.adj = new ArrayList<>(vertices);
                for (int i = 0; i < vertices; i++) this.adj.add(new ArrayList<>());
                this.distances = new int[vertices][vertices];
                for (int[] row : distances) Arrays.fill(row, Integer.MAX_VALUE);
                addEdges(edges);
            }

            private void addEdges(int[][] edges) {
                for (int[] edge : edges) {
                    this.adj.get(edge[0]).add(new int[]{edge[1], edge[2]});
                    this.adj.get(edge[1]).add(new int[]{edge[0], edge[2]});
                }
            }

            public int shortestPath(int src, int target) {
                if (distances[src][src] != Integer.MAX_VALUE)
                    return distances[src][target] == Integer.MAX_VALUE ? -1 : distances[src][target];
                distances[src][src] = 0;
                PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
                pq.offer(new int[]{src, 0});
                while (!pq.isEmpty()) {
                    int[] curr = pq.poll();
                    for (int[] next : adj.get(curr[0])) {
                        int newDist = distances[src][curr[0]] + next[1];
                        if (distances[src][next[0]] > newDist) {
                            distances[src][next[0]] = newDist;
                            pq.offer(new int[]{next[0], newDist});
                        }
                    }
                }
                return distances[src][target] == Integer.MAX_VALUE ? -1 : distances[src][target];
            }
        }
    }
}