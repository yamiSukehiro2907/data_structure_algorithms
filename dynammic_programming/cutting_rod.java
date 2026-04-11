package dynammic_programming;

/*
Given a rod of length N units and an array A of size N denotes prices that contains prices of all pieces of size 1 to N.
Find and return the maximum value that can be obtained by cutting up the rod and selling the pieces.
*/
public class cutting_rod {
    static void main() {
        int[] arr = {1, 5, 2, 5, 6};
        Solution obj = new Solution();
        System.out.println(obj.solve(arr));
    }

    static public class Solution {
        private int[] prices;
        private Integer[] dp;

        public int solve(int[] arr) {
            this.prices = arr;
            this.dp = new Integer[arr.length + 1];
            return find(arr.length);
        }

        private int find(int length) {
            if (length <= 0) return 0;
            if (dp[length] != null) return dp[length];
            int maxSellingPrice = 0;
            for (int cut = 1; cut <= length; cut++) {
                int sp = this.prices[cut - 1] + find(length - cut);
                if (sp > maxSellingPrice) maxSellingPrice = sp;
            }
            return dp[length] = maxSellingPrice;
        }
    }
}
