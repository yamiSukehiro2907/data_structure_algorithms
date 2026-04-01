void main() {
    int[][] mat = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
    };

    int[][] mat2 = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
    System.out.println(countPaths(mat2));
}

int countPaths(int[][] mat) {
    if (mat[0][0] == 1) return 0;
    int rows = mat.length, cols = mat[0].length;
    int[][] dp = new int[rows][cols];
    dp[0][0] = 1;
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            if (i == 0 && j == 0) continue;
            if (mat[i][j] == 1) continue;
            int totalPath = 0;
            if (i > 0) totalPath += dp[i - 1][j];
            if (j > 0) totalPath += dp[i][j - 1];
            dp[i][j] = totalPath;
        }
    }
    System.out.println("DP: ");
    for (int[] row : dp) System.out.println(Arrays.toString(row));
    return dp[rows - 1][cols - 1];
}

