void main() {
    this.matrix = new int[][]{
            {1, 0, 0, 0, 0, 1},
            {0, 1, 0, 0, 1, 0},
            {1, 0, 0, 1, 1, 0},
            {1, 0, 0, 0, 1, 1},
            {1, 1, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 1}
    };
    int[] cheese = {5 , 4};
    int[] start = {0 , 1};
    this.dest = cheese;
    System.out.println(pathExist(start[0] , start[1]));
}

int[] dest;

final static int[][] directions = {{0 , 1} , {0 , -1} , {1 , 0} , {-1, 0}};

int[][] matrix;

boolean pathExist(int x , int y){
    if(x == dest[0] && y == dest[1]) return true;
    for(int[] dir : directions){
        int nx = x + dir[0];
        int ny = y + dir[1];
        if(nx >= 0 && nx < matrix.length && ny >= 0 && ny < matrix[0].length && matrix[nx][ny] == 0){
            matrix[nx][ny] = 1;
            if(pathExist(nx , ny)) return true;
            matrix[nx][ny] = 0;
        }
    }
    return false;
}