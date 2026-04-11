package dynammic_programming;

public class unique_bst {
    static void main() {
        Solution sol = new Solution();
        System.out.println(sol.numTrees(10));
    }

    static public class Solution {
        private Integer[] dp;

        public int numTrees(int nodeCount) {
            this.dp = new Integer[nodeCount + 1];
            return find(nodeCount);
        }

        private int find(int nodeCount) {
            if (nodeCount == 0) return 1;
            if (dp[nodeCount] != null) return dp[nodeCount];
            int totalWays = 0;
            for (int i = 1; i <= nodeCount; i++) {
                int waysToArrangeLeft = find(i - 1);
                int waysToArrangeRight = find(nodeCount - i);
                totalWays += (waysToArrangeLeft * waysToArrangeRight);
            }
            return dp[nodeCount] = totalWays;
        }
    }
}