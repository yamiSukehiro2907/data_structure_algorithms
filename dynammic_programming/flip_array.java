package dynammic_programming;
/*
Given an array of positive elements, you have to flip the sign of some of its elements such that the resultant
sum of the elements of array should be minimum non-negative(as close to zero as possible).
Return the minimum number of elements whose sign needs to be flipped such that the resultant sum is minimum non-negative.
*/

public class flip_array {
    static void main() {


    }

    static public class Solution {
        private Node[][] dp;
        private int[] arr;
        private int totalSum;

        public int solve(final int[] arr) {
            this.arr = arr;
            for (int num : arr) totalSum += num;
            this.dp = new Node[arr.length][2 * totalSum + 1];
            return find(0, 0).flipCount;
        }

        private Node find(int index, int currentSum) {
            if (index >= arr.length) {
                if (currentSum < 0) return new Node(Integer.MAX_VALUE, Integer.MAX_VALUE);
                return new Node(0, currentSum);
            }
            int sumIndex = currentSum + this.totalSum;
            if (dp[index][sumIndex] != null) return dp[index][sumIndex];
            Node flipped = find(index + 1, currentSum - arr[index]);
            flipped = new Node(flipped.flipCount + 1, flipped.minPositiveSum);
            Node notFlipped = find(index + 1, currentSum + arr[index]);
            Node finalNode;
            if (flipped.minPositiveSum < notFlipped.minPositiveSum) finalNode = flipped;
            else if (flipped.minPositiveSum > notFlipped.minPositiveSum) finalNode = notFlipped;
            else {
                if (flipped.flipCount < notFlipped.flipCount) finalNode = flipped;
                else finalNode = notFlipped;
            }
            return dp[index][sumIndex] = finalNode;
        }

        private static class Node {
            int flipCount;
            int minPositiveSum;

            Node(int flipCount, int minPositiveSum) {
                this.flipCount = flipCount;
                this.minPositiveSum = minPositiveSum;
            }
        }
    }
}