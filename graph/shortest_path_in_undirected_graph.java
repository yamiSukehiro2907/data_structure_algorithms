package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class shortest_path_in_undirected_graph {
    public static void main(String[] args) {

    }
    private static class Solution {
        public int[] shortestPath(int V, int[][] edges, int src) {
            List<List<Integer>> adj = new ArrayList<>();
            for(int i = 0 ; i < V ; i++) adj.add(new ArrayList<>());
            for(int[] edge : edges){
                adj.get(edge[0]).add(edge[1]);
                adj.get(edge[1]).add(edge[0]);
            }
            int[] distance = new int[V];
            Arrays.fill(distance , Integer.MAX_VALUE);
            distance[src] = 0;
            PriorityQueue<int[]> queue = new PriorityQueue<>((a , b) -> a[1] - b[1]);
            queue.add(new int[]{src , 0});
            while(!queue.isEmpty()){
                int[] temp = queue.poll();
                for(int node : adj.get(temp[0])){
                    if(distance[node] > temp[1] + 1){
                        distance[node] = temp[1] + 1;
                        queue.add(new int[]{node , temp[1] + 1});
                    }
                }
            }
            for(int i = 0 ; i < V ; i++) if(distance[i] == Integer.MAX_VALUE) distance[i] = -1;
            return distance;
        }
    }

}
