package dynammic_programming;

import java.util.Arrays;

public class longest_palindromic_sequence {
    static void main() {

    }

    static class Solution {
        private int[][] dp;
        private char[] str;

        public int longestPalSubseq(String s) {
            this.str = s.toCharArray();
            this.dp = new int[s.length()][s.length()];
            for (int[] row : dp) Arrays.fill(row, -1);
            return find(0, str.length - 1);
        }

        private int find(int left, int right) {
            if (left > right) return 0;
            if (left == right) return 1;
            if (dp[left][right] != -1) return dp[left][right];
            int max = Integer.MIN_VALUE;
            if (str[left] == str[right]) max = 2 + find(left + 1, right - 1);
            max = Math.max(max, find(left + 1, right));
            max = Math.max(max, find(left, right - 1));
            return dp[left][right] = max;
        }
    }
}