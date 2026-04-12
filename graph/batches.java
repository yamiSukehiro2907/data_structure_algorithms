package graph;

/*
A students applied for admission in IB Academy.
An array of integers B is given representing the strengths of A people i.e. B[i] represents the strength of ith student.
Among the A students some of them knew each other.
A matrix C of size M x 2 is given which represents relations where ith relations depicts that C[i][0] and C[i][1] knew each other.
All students who know each other are placed in one batch.
Strength of a batch is equal to sum of the strength of all the students in it.
Now the number of batches are formed are very much, it is impossible for IB to handle them. So IB set criteria for selection:
All those batches having strength at least D are selected.
Find the number of batches selected.
NOTE: If student x and student y know each other, student y and z know each other then student x and student z will also know each other.
*/

import java.util.HashMap;
import java.util.Map;

public class batches {
    static void main() {

    }

    static public class Solution {
        public int solve(int totalPeople, int[] strengths, int[][] edges, int strengthThreshold) {
            Union union = new Union(totalPeople);
            for (int[] edge : edges) union.union(edge[0] - 1, edge[1] - 1);
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < totalPeople; i++) {
                int parent = union.find(i);
                if (!map.containsKey(parent)) map.put(parent, 0);
                map.put(parent, map.get(parent) + strengths[i]);
            }
            int count = 0;
            for (int value : map.values()) if (value >= strengthThreshold) count++;
            return count;
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

            public void union(int node1, int node2) {
                int p1 = find(node1), p2 = find(node2);
                if (p1 != p2) {
                    if (rank[p1] < rank[p2]) parent[p1] = p2;
                    else if (rank[p1] > rank[p2]) parent[p2] = p1;
                    else {
                        rank[p1]++;
                        parent[p2] = p1;
                    }
                }
            }
        }
    }
}


