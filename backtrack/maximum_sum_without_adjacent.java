void main() {
    int[][] arr = {
            {16, 5, 54, 55, 36, 82, 61, 77, 66, 61},
            {31, 30, 36, 70, 9, 37, 1, 11, 68, 14}
    };
    Solution sol = new Solution();
    System.out.println(sol.adjacent(arr));
}

static class Solution {
    public int adjacent(int[][] A) {
        if (A[0].length == 0) return 0;
        int n = A[0].length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = Math.max(A[0][0], A[1][0]);
        for (int i = 2; i <= n; i++) {
            int currentMax = Math.max(A[0][i - 1], A[1][i - 1]);
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + currentMax);
        }
        return dp[n];
    }
}