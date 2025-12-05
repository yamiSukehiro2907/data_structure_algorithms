void main() {
    for(int queens = 1 ; queens < 10 ; queens++) {
        char[][] board = new char[queens][queens];
        for (char[] ints : board) {
            Arrays.fill(ints, ' ');
        }
        for (int i = 0; i < queens; i++) {
            for (int j = 0; j < queens; j++) {
                board[i][j] = 'Q';
                generate(queens - 1, board);
                board[i][j] = ' ';
            }
        }
        found    = false;
    }
}

static boolean found = false;

void generate(int queens, char[][] board) {
    if (found) return;
    if (queens == 0) {
        for (char[] ints : board) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println();
        found = true;
        return;
    }
    for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board.length; j++) {
            if (board[i][j] != 'Q') {
                if (canBePlaced(i, j, board)) {
                    board[i][j] = 'Q';
                    generate(queens - 1, board);
                    board[i][j] = ' ';
                }
            }
        }
    }
}

boolean canBePlaced(int i, int j, char[][] board) {
    if (queenInRow(i, j, board)) return false;
    if (queenInColumn(i, j, board)) return false;
    return !queenInDiagonal(i, j, board);
}

boolean queenInRow(int i, int j, char[][] board) {
    for (int col = 0; col < board.length; col++) {
        if (col != j) {
            if (board[i][col] == 'Q') return true;
        }
    }
    return false;
}

boolean queenInColumn(int i, int j, char[][] board) {
    for (int row = 0; row < board.length; row++) {
        if (row != i) {
            if (board[row][j] == 'Q') return true;
        }
    }
    return false;
}

boolean queenInDiagonal(int i, int j, char[][] board) {
    if (inPrimaryDiagonal(i, j, board)) return true;
    return inSecondaryDiagonal(i, j, board);
}

boolean inPrimaryDiagonal(int i, int j, char[][] board) {
    int x = i - 1, y = j - 1;
    while (x >= 0 && y >= 0) {
        if (board[x][y] == 'Q') return true;
        x--;
        y--;
    }
    x = i + 1;
    y = j + 1;
    while (x < board.length && y < board.length) {
        if (board[x][y] == 'Q') return true;
        x++;
        y++;
    }
    return false;
}

boolean inSecondaryDiagonal(int i, int j, char[][] board) {
    int x = i - 1, y = j + 1;
    while (x >= 0 && y < board.length) {
        if (board[x][y] == 'Q') return true;
        x--;
        y++;
    }
    x = i + 1;
    y = j - 1;
    while (x < board.length && y >= 0) {
        if (board[x][y] == 'Q') return true;
        x++;
        y--;
    }
    return false;
}



