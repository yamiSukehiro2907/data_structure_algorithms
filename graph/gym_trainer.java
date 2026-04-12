package graph;

/*
You are the trainer of a gym and there are A people who come to your gym.
Some of them are friends because they walk together, and some of them are friends because they talk together.
But people become possessive about each other, so a person cannot walk with one friend and talk with another.
Although he can walk with two or more people or talk with two or more people.
You being the trainer, decided to suggest each one of the 2 possible diets.
But friends, being friends will always have the same diet as all the other friends are having.
Find and return the number of ways you can suggest each of them a diet.
As the number of ways can be huge, return the answer modulo 109+7.
NOTE: If there is any person who walks with one person and talks with the another person, then you may return 0.
*/

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class gym_trainer {
    static void main() {

    }


    static public class Solution {
        private static final long MOD = (long) 1e9 + 7;

        public int solve(int totalPeople, int[][] walkBuddies, int[][] talkBuddies) {
            Union union = new Union(totalPeople);
            for (int[] pair : walkBuddies) if (!union.union(pair[0] - 1, pair[1] - 1, "WALK")) return 0;
            for (int[] pair : talkBuddies) if (!union.union(pair[0] - 1, pair[1] - 1, "TALK")) return 0;
            Map<Integer, Integer> walkMap = new HashMap<>();
            Map<Integer, Integer> talkMap = new HashMap<>();
            int totalGroups = 0;
            for (int i = 0; i < totalPeople; i++) {
                Node parent = union.find(i);
                if (parent.type.equals("NONE")) totalGroups++;
                else if (parent.type.equals("WALK")) {
                    if (!walkMap.containsKey(parent.index)) walkMap.put(parent.index, 0);
                    walkMap.put(parent.index, walkMap.get(parent.index) + 1);
                } else {
                    if (!talkMap.containsKey(parent.index)) talkMap.put(parent.index, 0);
                    talkMap.put(parent.index, talkMap.get(parent.index) + 1);
                }
            }
            totalGroups += walkMap.size();
            totalGroups += talkMap.size();
            long ans = 1;
            while (totalGroups-- > 0) ans = (ans * 2) % MOD;
            return (int) ans;
        }

        static class Union {
            private final Node[] parent;
            private final int[] rank;

            public Union(int size) {
                parent = new Node[size];
                rank = new int[size];
                for (int i = 0; i < size; i++) {
                    parent[i] = new Node(i);
                    rank[i] = 1;
                }
            }

            public Node find(int node) {
                if (parent[node].index != node) parent[node] = find(parent[node].index);
                return parent[node];
            }

            public boolean union(int node1, int node2, String type) {
                Node p1 = find(node1);
                Node p2 = find(node2);
                if (!Objects.equals(p1.type, "NONE") && !Objects.equals(p1.type, type)) return false;
                if (!Objects.equals(p2.type, "NONE") && !Objects.equals(p2.type, type)) return false;
                if (p1.index != p2.index) {
                    if (rank[p1.index] > rank[p2.index]) {
                        parent[p2.index] = p1;
                        p1.type = type;
                    } else if (rank[p2.index] > rank[p1.index]) {
                        parent[p1.index] = p2;
                        p2.type = type;
                    } else {
                        parent[p1.index] = p2;
                        rank[p2.index]++;
                        p2.type = type;
                    }
                }
                return true;
            }
        }

        static class Node {
            int index;
            String type;

            Node(int index) {
                this.index = index;
                this.type = "NONE";
            }
        }
    }
}