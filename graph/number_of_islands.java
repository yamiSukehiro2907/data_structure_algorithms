package graph;

/*
Problem Description
Given a matrix of integers A of size N x M consisting of 0 and 1. A group of connected 1's forms an island.
From a cell (i, j) such that A[i][j] = 1 you can visit any cell that shares a corner with (i, j) and value in that cell is 1.
More formally, from any cell (i, j) if A[i][j] = 1 you can visit:
(i-1, j) if (i-1, j) is inside the matrix and A[i-1][j] = 1.
(i, j-1) if (i, j-1) is inside the matrix and A[i][j-1] = 1.
(i+1, j) if (i+1, j) is inside the matrix and A[i+1][j] = 1.
(i, j+1) if (i, j+1) is inside the matrix and A[i][j+1] = 1.
(i-1, j-1) if (i-1, j-1) is inside the matrix and A[i-1][j-1] = 1.
(i+1, j+1) if (i+1, j+1) is inside the matrix and A[i+1][j+1] = 1.
(i-1, j+1) if (i-1, j+1) is inside the matrix and A[i-1][j+1] = 1.
(i+1, j-1) if (i+1, j-1) is inside the matrix and A[i+1][j-1] = 1.
Return the number of islands.

NOTE: Rows are numbered from top to bottom and columns are numbered from left to right.
*/

public class number_of_islands {
    static void main() {

    }

    static public class Solution {
        private final static int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        private int[][] grid;

        public int solve(int[][] A) {
            int count = 0;
            this.grid = A;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 1) {
                        dfs(i, j);
                        count++;
                    }
                }
            }
            return count;
        }

        private void dfs(int row, int col) {
            grid[row][col] = 0;
            for (int[] dir : directions) {
                int new_row = row + dir[0];
                int new_col = col + dir[1];
                if (new_row >= 0 && new_row < grid.length && new_col >= 0 && new_col < grid[0].length && grid[new_row][new_col] == 1)
                    dfs(new_row, new_col);
            }
        }
    }
}