package graph;

/*
Problem Description
You are the Prime Minister of a country and once you went for a world tour.
After 5 years, when you returned to your country, you were shocked to see the condition of the roads between the cities.
So, you plan to repair them, but you cannot afford to spend a lot of money.
The country can be represented as a (N+1) x (M+1) grid, where Country(i, j) is a city.
The cost of repairing a road between (i, j) and (i + 1, j) is A[i]. The cost of repairing a road between (i, j) and (i, j + 1) is B[j].
Return the minimum cost of repairing the roads such that all cities can be visited from city indexed (0, 0).
As the cost can be large, return the cost modulo 109+7.
*/

import java.util.Arrays;

public class damaged_roads {
    static void main() {

    }

    static public class Solution {
        private static final long MOD = (long) 1e9 + 7;

        public int solve(int[] verticalCost, int[] horizontalCost) {
            int[][] vCost = new int[verticalCost.length][3];
            for (int i = 0; i < verticalCost.length; i++) vCost[i] = new int[]{i, i + 1, verticalCost[i]};
            int[][] hCost = new int[horizontalCost.length][3];
            for (int j = 0; j < horizontalCost.length; j++) hCost[j] = new int[]{j, j + 1, horizontalCost[j]};
            Arrays.sort(vCost, (a, b) -> a[2] - b[2]);
            Arrays.sort(hCost, (a, b) -> a[2] - b[2]);
            int i = 0, j = 0;
            int rowComponents = verticalCost.length + 1, colComponents = horizontalCost.length + 1;
            Union rowDSU = new Union(rowComponents);
            Union colDSU = new Union(colComponents);
            long totalCost = 0;
            while (i < hCost.length || j < vCost.length) {
                int[] hEdgeCost = new int[]{-1, -1, Integer.MAX_VALUE};
                int[] vEdgeCost = new int[]{-1, -1, Integer.MAX_VALUE};
                if (i < hCost.length) hEdgeCost = hCost[i];
                if (j < vCost.length) vEdgeCost = vCost[j];
                if (hEdgeCost[2] <= vEdgeCost[2]) {
                    if (colDSU.find(hEdgeCost[0]) != colDSU.find(hEdgeCost[1])) {
                        colDSU.union(hEdgeCost[0], hEdgeCost[1]);
                        totalCost = (totalCost + ((long) hEdgeCost[2] * rowComponents)) % MOD;
                        colComponents--;
                    }
                    i++;
                } else {
                    if (rowDSU.find(vEdgeCost[0]) != rowDSU.find(vEdgeCost[1])) {
                        rowDSU.union(vEdgeCost[0], vEdgeCost[1]);
                        totalCost = (totalCost + ((long) vEdgeCost[2] * colComponents)) % MOD;
                        rowComponents--;
                    }
                    j++;
                }
            }
            return (int) totalCost;
        }

        static class Union {
            int[] parent;
            int[] rank;

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

            public void union(int node1, int node2) {
                int p1 = find(node1);
                int p2 = find(node2);
                if (p1 != p2) {
                    if (rank[p1] > rank[p2]) parent[p2] = p1;
                    else if (rank[p1] < rank[p2]) parent[p1] = p2;
                    else {
                        parent[p1] = p2;
                        rank[p2]++;
                    }
                }
            }
        }
    }
}

