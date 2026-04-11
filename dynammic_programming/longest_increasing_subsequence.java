package dynammic_programming;

/*
Find the longest increasing subsequence of a given array of integers, A.
In other words, find a subsequence of array in which the subsequence's elements are in strictly increasing order,
and in which the subsequence is as long as possible.
In this case, return the length of the longest increasing subsequence.
*/
public class longest_increasing_subsequence {
    static void main() {
        int[] arr = {1, 2, 1, 5};
        Solution sol = new Solution();
        System.out.println(sol.lis(arr));
    }

    static public class Solution {
        private int[] arr;
        private Integer[][] dp;

        public int lis(final int[] A) {
            this.arr = A;
            this.dp = new Integer[A.length][A.length + 1];
            return find(0, -1);
        }

        private int find(int index, int prevIndex) {
            if (index == arr.length) return 0;
            if (dp[index][prevIndex + 1] != null) return dp[index][prevIndex];
            int skip = find(index + 1, prevIndex);
            int take = 0;
            if (prevIndex == -1 || arr[index] > arr[prevIndex]) take = 1 + find(index + 1, index);
            return dp[index][prevIndex + 1] = Math.max(take, skip);
        }
    }
}

