package dynammic_programming;

/*
Given an array of integers A representing chain of 2-D matices such that the dimensions of ith matrix is A[i-1] x A[i].
Find the most efficient way to multiply these matrices together.
The problem is not actually to perform the multiplications, but merely to decide in which order to perform the multiplications.
Return the minimum number of multiplications needed to multiply the chain.
*/
public class matrix_chain_multiplication {
    static void main() {
        int[] arr = {40, 20, 30, 10, 30};
        Solution obj = new Solution();
        System.out.println(obj.solve(arr));
    }

    static public class Solution {
        private Integer[][] dp;
        private int[] arr;

        public int solve(int[] arr) {
            this.dp = new Integer[arr.length][arr.length];
            this.arr = arr;
            return find(0, arr.length - 1);
        }

        private int find(int start, int end) {
            if (end - start <= 1) return 0;
            if (dp[start][end] != null) return dp[start][end];
            int minChain = Integer.MAX_VALUE;
            for (int i = start + 1; i < end; i++) {
                int value = this.arr[start] * this.arr[i] * this.arr[end];
                value += find(start, i);
                value += find(i, end);
                if (minChain > value) minChain = value;
            }
            return dp[start][end] = minChain;
        }
    }
}

