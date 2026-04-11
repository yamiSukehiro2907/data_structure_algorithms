package dynammic_programming;

/*
You are given an integer A, which represents the length of a string containing only opening and closing parentheses.
Your task is to find the number of valid parentheses expressions of length A.
A string of parentheses is considered valid if it meets the following conditions:
It is an empty string, or
It can be written as AB, where A and B are valid strings of parentheses, or
It can be written as (A), where A is a valid string of parentheses.
For example, "()", "(())", and "()()" are all valid strings of parentheses, but ")(", "()(", and "(()" are not.
Your task is to write a function that takes an integer A as input and returns the number of valid parentheses expressions of length A.
*/

public class count_valid_parenthesis {
    static void main() {
        Solution sol = new Solution();
        System.out.println(sol.solve(6));
    }

    static public class Solution {
        private int[][] dp;
        private int n;

        public int solve(int A) {
            if (A % 2 != 0) return 0;
            this.n = A;
            this.dp = new int[A + 1][A / 2 + 1];
            for (int[] row : dp) java.util.Arrays.fill(row, -1);
            return find(0, 0);
        }

        private int find(int pos, int openCount) {
            if (pos == n) return openCount == 0 ? 1 : 0;
            int remaining = n - pos;
            if (openCount > remaining) return 0;
            if (dp[pos][openCount] != -1) return dp[pos][openCount];
            int total = 0;
            // we used openChar here so increase the position and openCount
            total += find(pos + 1, openCount + 1);
            // we used closeChar so position increase by 1 and one of the openChar is being paired
            if (openCount > 0) total += find(pos + 1, openCount - 1);
            return dp[pos][openCount] = total;
        }
    }
}