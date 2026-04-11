package dynammic_programming;

/*
You are given a set of coins A. In how many ways can you make sum B assuming you have infinite amount of each coin in the set.
NOTE:
1. Coins in set A will be unique. Expected space complexity of this problem is O(B).
2. The answer can overflow. So, return the answer % (106 + 7).
*/
public class coin_sum_infinite {
    static void main() {
        int[] arr = {10};
        int sum = 10;
        Solution s = new Solution();
        System.out.println(s.coinchange2(arr, sum));
    }

    static public class Solution {
        private final static int MOD = 1_000_007;

        public int coinchange2(int[] coins, int maxSum) {
            int[] dp = new int[maxSum + 1];
            dp[0] = 1;
            for (int coin : coins) {
                for (int sum = coin; sum <= maxSum; sum++) {
                    dp[sum] = (dp[sum] + dp[sum - coin]) % MOD;
                }
            }
            return dp[maxSum];
        }
    }
}
