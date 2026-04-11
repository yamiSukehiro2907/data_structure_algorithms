package dynammic_programming;

public class ways_to_send_signal {
    static void main() {
        for (int i = 1; i <= 5; i++) {
            Solution solution = new Solution();
            System.out.println(solution.solve(i));
        }
    }

    static public class Solution {
        private static final long[] dp = new long[1_00_001];
        private static final long MOD = 1_000_000_007L;

        static {
            dp[0] = 1;
            dp[1] = 2;
            for (int i = 2; i <= 100000; i++) {
                long on = dp[i - 2];
                long off = dp[i - 1];
                dp[i] = (on + off + MOD) % MOD;
            }
        }

        public int solve(int A) {
            return (int) (dp[A] % MOD);
        }
    }

}
