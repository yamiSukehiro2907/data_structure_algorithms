package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
You are given N towns (1 to N). All towns are connected via unique directed path as mentioned in the input.
Given 2 towns find whether you can reach the first town from the second without repeating any edge.
B C : query to find whether B is reachable from C.
Input contains an integer array A of size N and 2 integers B and C ( 1 <= B, C <= N ).
There exist a directed edge from A[i] to i+1 for every 1 <= i < N. Also, it's guaranteed that A[i] <= i for every 1 <= i < N.
NOTE: Array A is 0-indexed. A[0] = 1 which you can ignore as it doesn't represent any edge.
*/
public class first_depth_first_search {
    static void main() {
        int[] arr = {1, 1, 2};
        Solution s = new Solution();
        System.out.println(s.solve(arr, 1, 2));
    }

    static public class Solution {
        private List<List<Integer>> graph;
        private Set<String> visitedPaths;

        public int solve(int[] A, final int target, final int src) {
            this.graph = new ArrayList<>();
            for (int i = 0; i < A.length; i++) this.graph.add(new ArrayList<>());
            for (int i = 1; i < A.length; i++) this.graph.get(A[i] - 1).add(i);
            this.visitedPaths = new HashSet<>();
            return canReach(src - 1, target - 1) ? 1 : 0;
        }

        private boolean canReach(int src, int target) {
            if (src == target) return true;
            System.out.println("src: " + src + " target: " + target);
            for (int next : this.graph.get(src)) {
                String path = src + "," + next;
                if (visitedPaths.contains(path)) continue;
                System.out.println(path);
                visitedPaths.add(path);
                if (canReach(next, target)) return true;
                visitedPaths.remove(path);
            }
            return false;
        }
    }
}

