package dynammic_programming;

/*
Given a knapsack weight A and a set of items with certain value B[i] and weight C[i],
we need to calculate maximum amount that could fit in this quantity.
This is different from classical Knapsack problem, here we are allowed to use unlimited number of instances of an item.
*/
public class unbounded_knapsack {
    static void main() {
        int capacity = 10;
        int[] values = {6, 7};
        int[] weights = {5, 5};
        Solution solution = new Solution();
        System.out.println(solution.solve(capacity, values, weights));
    }

    static public class Solution {
        private Integer[][] dp;
        private int[] values;
        private int[] weights;

        public int solve(int capacity, int[] values, int[] weights) {
            this.dp = new Integer[values.length][capacity + 1];
            this.values = values;
            this.weights = weights;
            return find(0, capacity);
        }

        private int find(int index, int capacity) {
            if (index >= this.values.length) return 0;
            if (capacity <= 0) return 0;
            if (dp[index][capacity] != null) return dp[index][capacity];
            int maxVal = 0;
            int time = 0;
            while (true) {
                int totalWeight = this.weights[index] * time;
                if (totalWeight > capacity) break;
                int totalVal = this.values[index] * time + find(index + 1, capacity - totalWeight);
                if (totalVal > maxVal) maxVal = totalVal;
                time++;
            }
            return dp[index][capacity] = maxVal;
        }
    }
}
