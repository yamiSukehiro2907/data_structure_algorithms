package graph;

import java.util.LinkedList;
import java.util.Queue;

/*
Given a matrix of integers A of size N x M consisting of 0 or 1.
For each cell of the matrix find the distance of nearest 1 in the matrix.
Distance between two cells (x1, y1) and (x2, y2) is defined as |x1 - x2| + |y1 - y2|.
Find and return a matrix B of size N x M which defines for each cell in A distance of nearest 1 in the matrix A.
NOTE: There is atleast one 1 is present in the matrix.
*/
public class distance_of_nearest_cell {
    static void main() {

    }

    static public class Solution {
        private static final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        public int[][] solve(int[][] matrix) {
            int rows = matrix.length, cols = matrix[0].length;
            Queue<int[]> queue = new LinkedList<>();
            int[][] dist = new int[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (matrix[i][j] == 1) {
                        dist[i][j] = 0;
                        queue.offer(new int[]{i, j});
                    } else dist[i][j] = Integer.MAX_VALUE;
                }
            }
            while (!queue.isEmpty()) {
                int[] node = queue.poll();
                for (int[] dir : directions) {
                    int newX = dir[0] + node[0];
                    int newY = dir[1] + node[1];
                    if (newX >= 0 && newX < rows && newY >= 0 && newY < cols) {
                        if (dist[newX][newY] > dist[node[0]][node[1]] + 1) {
                            dist[newX][newY] = dist[node[0]][node[1]] + 1;
                            queue.offer(new int[]{newX, newY});
                        }
                    }
                }
            }
            return dist;
        }
    }
}


