package dynammic_programming;

/*
Given two strings A and B, count the number of unique ways in string A to form a subsequence that is identical to string B.
A subsequence of a string is a new string formed from the original string by deleting some (can be none) of the characters
without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
Since the answer can be very large, return the result modulo 109 + 7.
*/

public class distinct_subsequences {
    static void main() {
        String from = "rabbbit";
        String target = "rabbit";
        Solution s = new Solution();
        System.out.println(s.numDistinct(from, target));
    }

    static public class Solution {
        private Integer[][] dp;
        private String from, target;
        private static final int MOD = (int) 1e9 + 7;

        public int numDistinct(String from, String target) {
            int fLength = from.length();
            int tLength = target.length();
            this.dp = new Integer[fLength][tLength];
            this.from = from;
            this.target = target;
            return count(from.length() - 1, target.length() - 1);
        }

        private int count(int fIndex, int tIndex) {
            if (tIndex < 0) return 1;
            if (fIndex < 0) return 0;
            if (fIndex < tIndex) return 0;
            if (dp[fIndex][tIndex] != null) return dp[fIndex][tIndex];
            int totalWays = 0;
            if (from.charAt(fIndex) == target.charAt(tIndex))
                totalWays = (totalWays + count(fIndex - 1, tIndex - 1)) % MOD;
            totalWays = (totalWays + count(fIndex - 1, tIndex)) % MOD;
            return dp[fIndex][tIndex] = totalWays;
        }
    }
}

