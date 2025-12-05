void main() {
    Solution sol = new Solution();
    List<List<String>> ans = sol.solveNQueens(9);
    for (List<String> list : ans) {
        System.out.println(list);
    }
}

static class Solution {
    private List<List<String>> ans;
    private int cols;
    private int primaryDiagonal;
    private int secondaryDiagonal;
    private int totalQueens;
    private int[] queens;

    public List<List<String>> solveNQueens(int n) {
        this.totalQueens = n;
        if (totalQueens == 1) {
            List<List<String>> ans = new ArrayList<>();
            ans.add(List.of("Q"));
            return ans;
        }
        if (totalQueens == 2 || totalQueens == 3) return new ArrayList<>();
        this.ans = new ArrayList<>();
        this.cols = 0;
        this.primaryDiagonal = 0;
        this.secondaryDiagonal = 0;
        this.queens = new int[n];
        find(0);
        return ans;
    }

    private void place(int row, int col) {
        cols |= (1 << col);
        primaryDiagonal |= (1 << (row - col + totalQueens - 1));
        secondaryDiagonal |= (1 << (row + col));
        queens[row] = col;
    }

    private void remove(int row, int col) {
        cols ^= (1 << col);
        primaryDiagonal ^= (1 << (row - col + totalQueens - 1));
        secondaryDiagonal ^= (1 << (row + col));
    }

    private void find(int row) {
        if (row == totalQueens) {
            add();
            return;
        }
        for (int col = 0; col < totalQueens; col++) {
            if (queenInColumn(col)) continue;
            if (queenInDiagonal(row, col)) continue;
            place(row, col);
            find(row + 1);
            remove(row, col);
        }
    }

    private void add() {
        List<String> list = new ArrayList<>();
        for (int row = 0; row < totalQueens; row++) {
            StringBuilder sb = new StringBuilder();
            for (int col = 0; col < totalQueens; col++) {
                if (queens[row] == col) {
                    sb.append('Q');
                } else {
                    sb.append('.');
                }
            }
            list.add(sb.toString());
        }
        ans.add(list);
    }

    private boolean queenInColumn(int col) {
        return ((cols >> col) & 1) == 1;
    }

    private boolean queenInDiagonal(int row, int col) {
        if (inPrimaryDiagonal(row, col)) return true;
        return inSecondaryDiagonal(row, col);
    }

    private boolean inPrimaryDiagonal(int row, int col) {
        return ((primaryDiagonal >> (row - col + totalQueens - 1)) & 1) == 1;
    }

    private boolean inSecondaryDiagonal(int row, int col) {
        return ((secondaryDiagonal >> (row + col)) & 1) == 1;
    }
}