package dynammic_programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class min_sum_path_in_triangle {
    static void main() {
        ArrayList<List<Integer>> arr = new ArrayList<>();
        arr.add(List.of(1));
        arr.add(List.of(1, 2));
        arr.add(List.of(1, 2, 3));
        Solution s = new Solution();
        System.out.println(s.minimumTotal(arr));
    }

    static public class Solution {
        private int[][] dp;
        private List<List<Integer>> list;

        public int minimumTotal(List<List<Integer>> list) {
            dp = new int[list.size()][list.size()];
            this.list = list;
            for (int[] row : dp) Arrays.fill(row, -1);
            for (int col = 0; col < list.size(); col++) find(list.size() - 1, col);
            int min = Integer.MAX_VALUE;
            for (int num : dp[list.size() - 1]) if (min > num) min = num;
            return min;
        }

        private int find(int row, int col) {
            if (row < 0) return 0;
            if (row == 0) return list.getFirst().getFirst();
            if (col < 0 || col >= list.get(row).size()) return Integer.MAX_VALUE;
            if (dp[row][col] != -1) return dp[row][col];
            int topLeft = find(row - 1, col - 1);
            int topRight = find(row - 1, col);
            return dp[row][col] = Math.min(topLeft, topRight) + list.get(row).get(col);
        }
    }
}

