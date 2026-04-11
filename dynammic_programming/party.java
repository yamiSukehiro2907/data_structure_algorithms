package dynammic_programming;

/*
In Danceland, one person can party either alone or can pair up with another person.
Can you find in how many ways they can party if there are A people in Danceland?
Note: Return your answer modulo 10003, as the answer can be large.
*/

public class party {
    static void main() {

    }

    static public class Solution {
        private final static long MOD = 10003;
        private final static int[] dp = new int[1_00_001];

        static {
            dp[1] = 1;
            dp[2] = 2;
            for (int i = 3; i < dp.length; i++) {
                long alone = dp[i - 1];
                long partner = (long) (i - 1) * (dp[i - 2]);
                dp[i] = (int) ((alone + partner) % MOD);
            }
        }

        public int solve(int people) {
            return dp[people];
        }
    }
}