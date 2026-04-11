package dynammic_programming;

import java.util.Arrays;

public class ways_to_decode {
    static void main() {
        String str = "108";
        Solution sol = new Solution();
        System.out.println(sol.numDecodings(str));
    }

    static public class Solution {
        private char[] arr;
        private int[] dp;
        private static final int MOD = 1_000_000_007;

        public int numDecodings(String str) {
            this.arr = str.toCharArray();
            this.dp = new int[this.arr.length];
            Arrays.fill(dp, -1);
            return find(0);
        }

        private int find(int index) {
            if (index >= arr.length) return 1;
            if (index == arr.length - 1) return arr[index] != '0' ? 1 : 0;
            if (dp[index] != -1) return dp[index];
            int totalWays = 0;
            int num = arr[index] - '0';
            if (num != 0) totalWays = find(index + 1);
            if (index + 1 < arr.length) {
                num = num * 10 + (arr[index + 1] - '0');
                if (num <= 26 && num >= 10) totalWays = (totalWays + find(index + 2)) % MOD;
            }
            return dp[index] = totalWays;
        }
    }
}