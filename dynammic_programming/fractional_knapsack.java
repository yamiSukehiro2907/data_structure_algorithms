package dynammic_programming;

import java.util.PriorityQueue;

public class fractional_knapsack {
    static void main() {
        int[] values = {10, 20, 30, 40};
        int[] weights = {12, 13, 15, 19};
        int capacity = 10;
        Solution solution = new Solution();
        System.out.println(solution.solve(values, weights, capacity));
    }
    /*
Given two integer arrays A and B of size N each which represent values and weights associated with N items respectively.
Also given an integer C which represents knapsack capacity.
Find out the maximum total value that we can fit in the knapsack. If the maximum total value is ans, then return ⌊ans × 100⌋ , i.e., floor of (ans × 100).
NOTE:You can break an item for maximizing the total value of the knapsack
    */

    static public class Solution {
        public int solve(int[] values, int[] weights, int capacity) {
            PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Double.compare(b.getRatio(), a.getRatio()));
            for (int i = 0; i < values.length; i++) pq.add(new Pair(values[i], weights[i]));
            double totalVal = 0;
            while (!pq.isEmpty() && capacity > 0) {
                Pair pair = pq.poll();
                if (pair.weight <= capacity) {
                    totalVal += pair.value;
                    capacity -= pair.weight;
                } else {
                    totalVal += (pair.getRatio() * (double)capacity);
                    break;
                }
            }
            return (int) (totalVal * 100);
        }

        static class Pair {
            int value;
            int weight;

            Pair(int value, int weight) {
                this.value = value;
                this.weight = weight;
            }

            public double getRatio() {
                return (double) this.value / (double) this.weight;
            }
        }
    }
}