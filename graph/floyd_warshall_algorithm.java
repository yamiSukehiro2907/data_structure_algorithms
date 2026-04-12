package graph;

/*
Given a matrix of integers A of size N x N, where A[i][j] represents the weight of directed edge from i to j (i ---> j).
If i == j, A[i][j] = 0, and if there is no directed edge from vertex i to vertex j, A[i][j] = -1.
Return a matrix B of size N x N where B[i][j] = shortest path from vertex i to vertex j.
If there is no possible path from vertex i to vertex j , B[i][j] = -1
Note: Rows are numbered from top to bottom and columns are numbered from left to right.
*/

public class floyd_warshall_algorithm {
    static void main() {

    }

    static public class Solution {
        public int[][] solve(int[][] dist) {
            int totalNodes = dist.length;
            for (int[] row : dist) {
                for (int i = 0; i < row.length; i++) {
                    if (row[i] == -1) row[i] = Integer.MAX_VALUE;
                }
            }
            for (int node = 0; node < totalNodes; node++) {
                for (int from = 0; from < totalNodes; from++) {
                    for (int to = 0; to < totalNodes; to++) {
                        if (dist[from][node] != Integer.MAX_VALUE && dist[node][to] != Integer.MAX_VALUE) {
                            dist[from][to] = Math.min(dist[from][to], dist[from][node] + dist[node][to]);
                        }
                    }
                }
            }
            for (int[] row : dist) {
                for (int i = 0; i < row.length; i++) {
                    if (row[i] == Integer.MAX_VALUE) row[i] = -1;
                }
            }
            return dist;
        }
    }
}