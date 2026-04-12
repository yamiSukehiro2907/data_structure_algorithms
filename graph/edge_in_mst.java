package graph;

/*
Given a undirected weighted graph with A nodes labeled from 1 to A with M edges given in a form of 2D-matrix B of size M * 3
where B[i][0] and B[i][1] denotes the two nodes connected by an edge of weight B[i][2].
For each edge check whether it belongs to any of the possible minimum spanning tree or not , return 1 if it belongs else return 0.
Return an one-dimensional binary array of size M denoting answer for each edge.
NOTE:
The graph may be disconnected in that case consider mst for each component.
No self-loops and no multiple edges present.
Answers in output array must be in order with the input array B output[i] must denote the answer of edge B[i][0] to B[i][1].
*/

import java.util.Arrays;

public class edge_in_mst {
    static void main() {

    }

    static public class Solution {
        public int[] solve(int vertices, int[][] edges) {
            Union union = new Union(vertices);
            Integer[] indexes = new Integer[edges.length];
            int[] ans = new int[edges.length];
            for (int i = 0; i < edges.length; i++) indexes[i] = i;
            Arrays.sort(indexes, (a, b) -> edges[a][2] - edges[b][2]);
            int i = 0;
            while (i < edges.length) {
                int currentEdgeIndex = indexes[i];
                int[] edge = edges[currentEdgeIndex];
                int j = i;
                while (j < edges.length && edges[indexes[j]][2] == edge[2]) j++;
                for (int k = i; k < j; k++) {
                    int[] tempEdge = edges[indexes[k]];
                    int from = tempEdge[0] - 1, to = tempEdge[1] - 1;
                    if (union.find(from) != union.find(to)) ans[indexes[k]] = 1;
                }
                for (int k = i; k < j; k++) {
                    int[] tempEdge = edges[indexes[k]];
                    int from = tempEdge[0] - 1, to = tempEdge[1] - 1;
                    if (ans[indexes[k]] == 1) union.union(from, to);
                }
                i = j;
            }
            return ans;
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


