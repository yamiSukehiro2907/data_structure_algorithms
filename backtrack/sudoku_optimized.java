void main() {
    char[][] matrix = {{'5', '3', '.', '.', '7', '.', '.', '.', '.' }, {'6', '.', '.', '1', '9', '5', '.', '.', '.' }, {'.', '9', '8', '.', '.', '.', '.', '6', '.' }, {'8', '.', '.', '.', '6', '.', '.', '.', '3' }, {'4', '.', '.', '8', '.', '3', '.', '.', '1' }, {'7', '.', '.', '.', '2', '.', '.', '.', '6' }, {'.', '6', '.', '.', '.', '.', '2', '8', '.' }, {'.', '.', '.', '4', '1', '9', '.', '.', '5' }, {'.', '.', '.', '.', '8', '.', '.', '7', '9' }};
    Solution sol = new Solution();
    sol.solveSudoku(matrix);
    for (char[] temp : matrix) {
        System.out.println(Arrays.toString(temp));
    }
}

static boolean found = false;

static class Solution {
    private int[] rows;
    private int[] cols;
    private int[] blocks;
    private char[][] matrix;

    public void solveSudoku(char[][] matrix) {
        this.rows = new int[9];
        this.cols = new int[9];
        this.blocks = new int[9];
        this.matrix = matrix;
        for (int i = 0; i < matrix.length; i++) {
            rows[i] = 0;
            cols[i] = 0;
            blocks[i] = 0;
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (matrix[i][j] != '.') {
                    int num = matrix[i][j] - '0';
                    rows[i] |= (1 << num);
                    cols[j] |= (1 << num);
                    blocks[findBlockNumber(i, j)] |= (1 << num);
                }
            }
        }
        fill(0, 0);
    }

    private boolean couldPlace(int row, int col, int num) {
        if (inRow(num, row)) return false;
        if (inColumn(num, col)) return false;
        return !inBlock(num, row, col);
    }

    private void fill(int i, int j) {
        if (i >= matrix.length) {
            found = true;
        } else if (j == matrix[0].length) {
            fill(i + 1, 0);
        } else if (matrix[i][j] == '.') {
            for (int num = 1; num <= 9; num++) {
                if (couldPlace(i, j, num)) {
                    change(i, j, num);
                    fill(i, j + 1);
                    if (!found) {
                        remove(i, j, num);
                    }
                }
            }
        } else {
            fill(i, j + 1);
        }
    }

    private int findBlockNumber(int i, int j) {
        return (i / 3) * 3 + (j / 3);
    }

    private boolean inRow(int num, int row) {
        return ((rows[row] >> num) & 1) == 1;
    }

    private boolean inColumn(int num, int col) {
        return ((cols[col] >> num) & 1) == 1;
    }

    private boolean inBlock(int num, int row, int col) {
        int blockNumber = findBlockNumber(row, col);
        return ((blocks[blockNumber] >> num) & 1) == 1;
    }

    private void change(int row, int col, int num) {
        rows[row] |= (1 << num);
        cols[col] |= (1 << num);
        int blockNumber = findBlockNumber(row, col);
        blocks[blockNumber] |= (1 << num);
        matrix[row][col] = (char) ('0' + num);
    }

    private void remove(int row, int col, int num) {
        rows[row] ^= (1 << num);
        cols[col] ^= (1 << num);
        int blockNumber = findBlockNumber(row, col);
        blocks[blockNumber] ^= (1 << num);
        matrix[row][col] = '.';
    }
}
