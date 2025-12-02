void main() {
    ///  matrix with start point , end point and cheeses
    /// start point will have 1 , end point will have 2 , cheese will have 3 and walkable will have 0 and hurdle will have -1
    matrix = new int[][]{
            {1, 0, 3},
            {0, 0, -1},
            {0, 3, 2}
    };
    int totalCheese = 0;
    int[] start = null;
    for(int i = 0 ; i < matrix.length ; i++) {
        for(int j = 0 ; j < matrix[i].length ; j++) {
            if(matrix[i][j] == 3) totalCheese++;
            else if(matrix[i][j] == 1) start = new int[]{i , j};
        }
    }
    pathCount = 0;
    if(start == null){
        System.out.println("No solution");
        return;
    }
    matrix[start[0]][start[1]] = -1;
    find(start[0] , start[1] , totalCheese , 0);
    System.out.println(pathCount);
}

static int[][] matrix;

static int pathCount;

final static int[][] directions = {{0 , 1} , {0 , -1} , {1 , 0} , {-1 , 0}};

static void find(int x , int y , int totalCheese , int collectedCheese){
    for(int[] dir : directions){
        int newX = x + dir[0];
        int newY = y + dir[1];
        if(newX >= 0 && newX < matrix.length && newY >= 0 && newY <matrix[0].length){
            if(matrix[newX][newY] == 3){
                matrix[newX][newY] = -1;
                find(newX , newY , totalCheese, collectedCheese + 1);
                matrix[newX][newY] = 3;
            }
            else if(matrix[newX][newY] == 2){
                if(collectedCheese == totalCheese){
                    pathCount++;
                }
            }else if(matrix[newX][newY] == 0){
                matrix[newX][newY] = -1;
                find(newX , newY , totalCheese, collectedCheese);
                matrix[newX][newY] = 0;
            }
        }
    }
}