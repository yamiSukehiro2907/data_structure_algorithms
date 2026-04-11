package dynammic_programming;

import java.util.Arrays;

/*
Given a matrix of integers A of size N x 2 describing dimensions of N envelopes,
where A[i][0] denotes the height of the ith envelope and A[i][1] denotes the width of the ith envelope.
One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.
Find the maximum number of envelopes you can put one inside other.
*/
public class russian_envelope {

    static void main() {
        int[][] mat = {{5, 4},
                {6, 4},
                {6, 7},
                {2, 3}};
        Solution sol = new Solution();
        System.out.println(sol.solve(mat));
    }

    static public class Solution {
        private int[][] mat;
        private Integer[][] dp;

        public int solve(int[][] mat) {
            // sort first by height if equal height then in descending order of their width
            Arrays.sort(mat, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
            this.dp = new Integer[mat.length][mat.length + 1];
            this.mat = mat;
            return find(0, -1);
        }

        private int find(int index, int prevIndex) {
            if (index == mat.length) return 0;
            if (dp[index][prevIndex + 1] != null) return dp[index][prevIndex + 1];
            int skip = find(index + 1, prevIndex);
            int take = 0;
            if (prevIndex == -1 || (mat[index][1] > mat[prevIndex][1] && mat[index][0] > mat[prevIndex][0])) take = 1 + find(index + 1, index);
            return dp[index][prevIndex + 1] = Math.max(take, skip);
        }
    }
}

