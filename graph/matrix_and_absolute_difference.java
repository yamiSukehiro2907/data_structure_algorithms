package graph;

/*
Given a matrix C of integers, of dimension A x B.
For any given K, you are not allowed to travel between cells that have an absolute difference greater than K.
Return the minimum value of K such that it is possible to travel between any pair of cells in the grid through a path of adjacent cells.
NOTE:
Adjacent cells are those cells that share a side with the current cell.
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class matrix_and_absolute_difference {
    static void main() {

    }

    static public class Solution {
        private static final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        public int solve(int rows, int cols, int[][] matrix) {
            List<int[]> edges = new ArrayList<>();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    int fromCellNo = i * cols + j;
                    for (int[] dir : directions) {
                        int newX = i + dir[0];
                        int newY = j + dir[1];
                        if (newX >= 0 && newX < rows && newY >= 0 && newY < cols) {
                            int toCellNo = newX * cols + newY;
                            edges.add(new int[]{fromCellNo, toCellNo, Math.abs(matrix[newX][newY] - matrix[i][j])});
                        }
                    }
                }
            }
            Collections.sort(edges, (a, b) -> a[2] - b[2]);
            Union union = new Union(rows * cols);
            int count = 0;
            int minVal = Integer.MIN_VALUE;
            for (int[] edge : edges) {
                int fromCellNo = edge[0];
                int toCellNo = edge[1];
                int weight = edge[2];
                if (union.find(fromCellNo) != union.find(toCellNo)) {
                    union.union(fromCellNo, toCellNo);
                    minVal = Math.max(minVal, weight);
                    if (++count == rows * cols - 1) break;
                }
            }
            return minVal;
        }

        static class Union {
            private final int[] parent;
            private final int[] rank;

            public Union(int n) {
                parent = new int[n];
                rank = new int[n];
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                    rank[i] = 1;
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
                    if (rank[p1] < rank[p2]) parent[p1] = p2;
                    else if (rank[p1] > rank[p2]) parent[p2] = p1;
                    else {
                        parent[p1] = p2;
                        rank[p2]++;
                    }
                }
            }
        }
    }
}

