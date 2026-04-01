package bit_manipulation;

import java.util.ArrayList;

public class setZeroes {
    public static void main(String[] args) {
        int[][] matrix = {{0, 1}, {1, 1}};
        Solution.setZeroes(matrix);
    }

    static class Solution {
        public static void setZeroes(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            int row[] = new int[m];
            int column[] = new int[n];
            System.out.println("Before Matrix: ");
            printGrid(matrix);
            for (int i = 0; i < m; i++) {
                int sum = 0;
                for (int j = 0; j < n; j++) {
                    sum += matrix[i][j];
                }
                row[i] = sum;
            }
            for (int j = 0; j < n; j++) {
                int sum = 0;
                for (int i = 0; i < m; i++) {
                    sum += matrix[i][j];
                }
                column[j] = sum;
            }
            System.out.println("Row: ");
            print(row);
            System.out.println("Column: ");
            print(column);
            System.out.println("After matrix: ");
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (row[i] == m && column[j] == n) {
                        matrix[i][j] = 1;
                    } else {
                        matrix[i][j] = 0;
                    }
                }
            }
            printGrid(matrix);
        }

        private static void print(int[] arr) {
            for (int j : arr) {
                System.out.print(j + " ");
            }
            System.out.println();
        }

        private static void printGrid(int[][] matrix) {
            for (int[] ints : matrix) {
                for (int j = 0; j < matrix[0].length; j++) {
                    System.out.print(ints[j] + " ");
                }
                System.out.println();
            }
        }
    }

    static
    class Solution2 {
        public void setZeroes(ArrayList<ArrayList<Integer>> list) {
            int m = list.size(), n = list.getFirst().size();
            boolean firstRowZero = false, firstColZero = false;
            for (int i = 0; i < m; i++) {
                if (list.getFirst().get(i) == 0) {
                    firstRowZero = true;
                    break;
                }
            }
            for (int j = 0; j < n; j++) {
                if (list.get(j).getFirst() == 0) {
                    firstColZero = true;
                    break;
                }
            }
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    if (list.get(i).get(j) == 0) {
                        list.get(i).set(0, 0);
                        list.getFirst().set(j, 0);
                    }
                }
            }
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    if (list.get(i).getFirst() == 0 || list.getFirst().get(i) == 0) {
                        list.get(i).set(j, 0);
                    }
                }
            }
            if (firstRowZero) {
                for (int i = 0; i < n; i++) {
                    list.getFirst().set(i, 0);
                }
            }
            if (firstColZero) {
                for (int i = 0; i < m; i++) {
                    list.get(i).set(0, 0);
                }
            }
        }
    }

}
