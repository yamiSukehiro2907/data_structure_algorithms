package graph;

/*
A country consist of N cities connected by N - 1 roads.
King of that country want to construct maximum number of roads such that the new country formed remains bipartite country.
Bipartite country is a country, whose cities can be partitioned into 2 sets in such a way, that for each road (u, v) that belongs to the country,
u and v belong to different sets. Also, there should be no multiple roads between two cities and no self loops.
Return the maximum number of roads king can construct. Since the answer could be large return answer % 109 + 7.
NOTE: All cities can be visited from any city.
*/

import java.util.*;

public class construct_roads {
    static void main() {
        int nodes = 5;
        int[][] edges = {
                {1, 3},
                {1, 4},
                {3, 2},
                {3, 5},
        };
        Solution s = new Solution();
        System.out.println(s.solve(nodes, edges));
    }

    static public class Solution {
        private static final long MOD = (long) 1e9 + 7;

        public int solve(int vertices, int[][] edges) {
            List<List<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < vertices; i++) adj.add(new ArrayList<>());
            for (int[] edge : edges) {
                adj.get(edge[0] - 1).add(edge[1] - 1);
                adj.get(edge[1] - 1).add(edge[0] - 1);
            }
            int[] colors = new int[vertices];
            Arrays.fill(colors, -1);
            List<Integer> setA = new ArrayList<>();
            List<Integer> setB = new ArrayList<>();
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < vertices; i++) {
                if (colors[i] == -1) {
                    queue.add(i);
                    colors[i] = 0;
                    while (!queue.isEmpty()) {
                        int node = queue.poll();
                        if (colors[node] == 0) setA.add(node);
                        else setB.add(node);
                        for (int next : adj.get(node)) {
                            if (colors[next] == -1) {
                                int color = 1 - colors[node];
                                colors[next] = color;
                                queue.offer(next);
                            }
                        }
                    }
                }
            }
            long totalEdgesPossible = (long) setA.size() * setB.size();
            long toBeConstructed = totalEdgesPossible - edges.length;
            return (int) (Math.max(toBeConstructed, 0) % MOD);
        }
    }
}

