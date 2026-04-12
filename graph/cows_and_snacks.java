package graph;

/*
The legendary Farmer John is throwing a huge party, and animals from all over the world are hanging out at his house.
His guests are hungry, so he instructs his cow Bessie to bring out the snacks! Moo!
There are A snacks flavors, numbered with integers 1,2,…,A. Bessie has A snacks, one snack of each flavor.
There are M guests and every guest has exactly two favorite flavors. The procedure for eating snacks will go as follows:
First, Bessie will line up the guests in some way.
Each guest in their turn will eat all remaining snacks of their favorite flavor.
In case no favorite flavors are present when a guest goes up, they become very sad.
Help Bessie to minimize the number of sad guests by lining the guests in an optimal way.
*/

import java.util.HashMap;
import java.util.Map;

public class cows_and_snacks {
    static void main() {
        int totalSnacks = 5;
        int[][] preferences = {{1, 2}, {4, 3}, {1, 4}, {3, 4}};
        Solution solution = new Solution();
        System.out.println(solution.solve(totalSnacks, preferences));
    }

    static public class Solution {
        public int solve(int totalSnacks, int[][] preferences) {
            Union union = new Union(totalSnacks);
            for (int[] prefer : preferences) union.union(prefer[0] - 1, prefer[1] - 1);
            Map<Integer, Node> map = new HashMap<>();
            for (int i = 0; i < totalSnacks; i++) {
                Node parent = union.find(i);
                if (!map.containsKey(parent.index)) map.put(parent.index, parent);
            }
            int totalSad = 0;
            // suppose a component have N nodes and N - 1 edges then 0 people will be sad
            // if a component have N nodes and N edges then 1 people will be sad
            //  if a component have X edges and Y nodes then X - Y + 1 people will be sad
            for (Node parent : map.values()) totalSad += Math.max(0, parent.edgeCount - parent.nodeCount + 1);
            return totalSad;
        }

        static class Union {
            private final Node[] parent;

            public Union(int n) {
                this.parent = new Node[n];
                for (int i = 0; i < n; i++) parent[i] = new Node(i);
            }

            public Node find(int node) {
                if (parent[node].index != node) parent[node] = find(parent[node].index);
                return parent[node];
            }

            public void union(int node1, int node2) {
                Node p1 = find(node1);
                Node p2 = find(node2);
                if (p1.index != p2.index) {
                    if (p1.rank > p2.rank) {
                        parent[p2.index] = p1;
                        p1.edgeCount += p2.edgeCount + 1;
                        p1.nodeCount += p2.nodeCount;
                    } else if (p2.rank > p1.rank) {
                        parent[p1.index] = p2;
                        p2.edgeCount += p1.edgeCount + 1;
                        p2.nodeCount += p1.nodeCount;
                    } else {
                        parent[p2.index] = p1;
                        p1.rank++;
                        p1.edgeCount += p2.edgeCount + 1;
                        p1.nodeCount += p2.nodeCount;
                    }
                } else p1.edgeCount++;
            }
        }

        static class Node {
            int edgeCount;
            int index;
            int rank;
            int nodeCount;

            Node(int index) {
                this.index = index;
                this.rank = 0;
                this.edgeCount = 0;
                this.nodeCount = 1;
            }
        }
    }
}

