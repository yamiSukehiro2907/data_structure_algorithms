package dynammic_programming;

/*
Given a strictly increasing array A of positive integers forming a sequence.
A sequence X1, X2, X3, ..., XN is fibonacci like if

N > =3
Xi + Xi+1 = Xi+2 for all i+2 <= N
Find and return the length of the longest Fibonacci-like subsequence of A.
If one does not exist, return 0.
NOTE: A subsequence is derived from another sequence A by deleting any number of elements (including none) from A, without changing the order of the remaining elements.


*/

import java.util.HashMap;
import java.util.Map;

public class length_of_longest_fibonacci_subsequence {
    static void main() {
        int[] arr = {5, 12, 17, 25, 35, 44, 50, 54};
        Solution sol = new Solution();
        System.out.println(sol.solve(arr));
    }

    static public class Solution {
        private Integer[][] dp;
        private int[] arr;
        private Map<Integer, Integer> map;

        public int solve(int[] arr) {
            this.arr = arr;
            this.dp = new Integer[arr.length][arr.length];
            this.map = new HashMap<>();
            for (int i = 0; i < arr.length; i++) map.put(arr[i], i);
            int result = 0;
            for (int i = 0; i < arr.length - 1; i++)
                for (int j = i + 1; j < arr.length; j++)
                    result = Math.max(result, find(i, j));
            return result >= 3 ? result : 0;
        }

        private int find(int secondLastIndex, int lastIndex) {
            if (dp[secondLastIndex][lastIndex] != null) return dp[secondLastIndex][lastIndex];
            int need = arr[lastIndex] - arr[secondLastIndex];
            if (need >= arr[secondLastIndex] || !map.containsKey(need)) return dp[secondLastIndex][lastIndex] = 2;
            int k = map.get(need);
            return dp[secondLastIndex][lastIndex] = 1 + find(k, secondLastIndex);
        }
    }
}

