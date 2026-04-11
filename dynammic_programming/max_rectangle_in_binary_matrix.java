package dynammic_programming;

import java.util.Stack;

public class max_rectangle_in_binary_matrix {
    static void main() {
        int[][] matrix = {{1, 1, 1}, {0, 1, 1}, {1, 0, 0}};
        Solution solution = new Solution();
        System.out.println(solution.maximalRectangle(matrix));
    }

    static public class Solution {
        public int maximalRectangle(int[][] matrix) {
            int cols = matrix[0].length;
            int[] heights = new int[cols];
            int maxArea = 0;
            for (int[] row : matrix) {
                for (int j = 0; j < cols; j++) {
                    if (row[j] == 0) heights[j] = 0;
                    else heights[j]++;
                }
                maxArea = Math.max(maxArea, findMax(heights));
            }
            return maxArea;
        }

        private int findMax(int[] heights) {
            int maxArea = 0;
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < heights.length; i++) {
                while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                    int length = heights[stack.pop()];
                    int width = stack.isEmpty() ? i : i - stack.peek();
                    maxArea = Math.max(maxArea, width * length);
                }
                stack.push(i);
            }
            while (!stack.isEmpty()) {
                int length = heights[stack.pop()];
                int width = stack.isEmpty() ? heights.length - 1 : heights.length - 1 - stack.peek();
                maxArea = Math.max(maxArea, width * length);
            }
            return maxArea;
        }
    }
}
