package array;

import java.util.Arrays;
import java.util.Scanner;

public class matrix_range_sum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] arr = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        int[][] prefix = new int[m][n];
        for (int i = 0; i < m; i++) {
            prefix[i][0] = arr[i][0];
            for (int j = 1; j < n; j++) {
                prefix[i][j] = prefix[i][j - 1] + arr[i][j];
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(prefix[i][j] + " ");
            }
        }
        int q = sc.nextInt();
        int[] ans = new int[q];
        for (int i = 0; i < q; i++) {
            int startRow = sc.nextInt();
            int startCol = sc.nextInt();
            int endRow = sc.nextInt();
            int endCol = sc.nextInt();
            int sum = 0;
            for (int j = startRow; j <= endRow; j++) {
                sum += prefix[j][endCol];
                if (startCol > 0)
                    sum -= prefix[j][startCol - 1];
            }
            ans[i] = sum;
        }
        System.out.println(Arrays.toString(ans));
    }
}
