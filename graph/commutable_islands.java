package graph;

/*
There are A islands and there are M bridges connecting them. Each bridge has some cost attached to it.
We need to find bridges with minimal cost such that all islands are connected.
It is guaranteed that input data will contain at least one possible scenario in which all islands are connected with each other.
 */

import java.util.Arrays;

public class commutable_islands {
    static void main() {

    }

    static public class Solution {
        public int solve(int vertices, int[][] edges) {
            Union union = new Union(vertices);
            Arrays.sort(edges, (a, b) -> a[2] - b[2]);
            int count = 0;
            int totalCost = 0;
            for (int[] edge : edges) {
                int from = edge[0] - 1;
                int to = edge[1] - 1;
                if (union.find(from) != union.find(to)) {
                    union.union(from, to);
                    totalCost += edge[2];
                    if (++count == vertices - 1) break;
                }
            }
            return totalCost;
        }

        static class Union {
            private final int[] parent;
            private final int[] rank;

            Union(int n) {
                this.parent = new int[n];
                this.rank = new int[n];
                for (int i = 0; i < n; i++) {
                    this.parent[i] = i;
                    this.rank[i] = 1;
                }
            }

            public int find(int node) {
                if (parent[node] != node) parent[node] = find(parent[node]);
                return parent[node];
            }

            private void union(int node1, int node2) {
                int p1 = find(node1);
                int p2 = find(node2);
                if (p1 != p2) {
                    if (rank[p1] < rank[p2]) parent[p1] = parent[p2];
                    else if (rank[p1] > rank[p2]) parent[p2] = parent[p1];
                    else {
                        parent[p1] = parent[p2];
                        rank[p2]++;
                    }
                }
            }
        }
    }
}

