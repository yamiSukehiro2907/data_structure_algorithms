package dynammic_programming;

/*
Given a number A, return number of ways you can draw A chords in a circle with 2 x A points such that no 2 chords intersect.
Two ways are different if there exists a chord which is present in one way and not in other.
Return the answer modulo 109 + 7.
*/
public class intersecting_chord_in_circles {
    static void main() {
        Solution sol = new Solution();
        System.out.println(sol.chordCnt(999));
    }

    static public class Solution {
        private Integer[] dp;
        private static final int MOD = (int) 1e9 + 7;

        public int chordCnt(int chords) {
            this.dp = new Integer[chords + 1];
            return find(chords);
        }

        private int find(int chords) {
            if (chords <= 1) return 1;
            if (dp[chords] != null) return dp[chords];
            int totalWays = 0;
            for (int i = 1; i <= chords; i++) {
                int waysToArrangeInsideChords = find(i - 1);
                int waysToArrangeOutsideChords = find(chords - i);
                totalWays = (int) (((long) totalWays + (long) waysToArrangeInsideChords * waysToArrangeOutsideChords) % MOD);
            }
            return dp[chords] = totalWays;
        }
    }
}
