package dynammic_programming;

public class zero_one_knapsack {
    static void main() {

    }

    /*
Given two integer arrays A and B of size N each which represent values and weights associated with N items respectively.
Also given an integer C which represents knapsack capacity.
Find out the maximum value subset of A such that sum of the weights of this subset is smaller than or equal to C.
NOTE:You cannot break an item, either pick the complete item, or don’t pick it (0-1 property).
     */
    static public class Solution {
        private Integer[][] dp;
        private int[] values;
        private int[] weights;

        public int solve(int[] values, int[] weights, int capacity) {
            this.values = values;
            this.weights = weights;
            dp = new Integer[values.length + 1][capacity + 1];
            return find(0, capacity);
        }

        private int find(int index, int capacity) {
            if (index >= this.values.length || capacity <= 0) return 0;
            if (dp[index][capacity] != null) return dp[index][capacity];
            int max = 0;
            if (weights[index] <= capacity) max = values[index] + find(index + 1, capacity - weights[index]);
            max = Math.max(max, find(index + 1, capacity));
            return dp[index][capacity] = max;
        }
    }

}