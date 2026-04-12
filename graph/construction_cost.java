package graph;

/*
Flipkart has ‘A’ local distribution centers located across a large metropolitan city.
Each distribution center needs to be interconnected through roads to facilitate efficient movement of goods.
The cost of constructing a road between any two distribution centers is represented by the weight of the edge connecting them.
Given a graph with ‘A’ nodes representing the distribution centers and C weighted edges representing the possible roads between them,
your task is to find the minimum total cost of constructing roads such that every distribution center can be reached from the first distribution center.
Cost Calculation:
The cost of constructing the roads is the sum of the weights of the edges selected for the construction.
NOTE: Return the answer modulo 10^9+7 as the answer can be large.
 */

import java.util.Arrays;

public class construction_cost {
    static void main() {

    }

    static public class Solution {
        private final static long MOD = (long) 1e9 + 7;

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
                    totalCost = (int) (((long) totalCost + edge[2]) % MOD);
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

