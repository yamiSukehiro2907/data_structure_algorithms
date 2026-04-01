package graph;

import java.util.SortedMap;
import java.util.TreeMap;

/// [...](https://www.geeksforgeeks.org/problems/minimum-swaps/1?page=1&category=Graph&status=unsolved&sortBy=submissions)

public class min_swap_to_sort {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {1, 4, 3, 2, 5};
        System.out.println(solution.minSwaps(arr));
    }

    private static class Solution {
        public int minSwaps(int[] arr) {
            SortedMap<Integer , Integer> map = new TreeMap<>((a , b) -> b - a);
            for(int i = 0 ; i < arr.length ; i++) map.put(arr[i] , i);
            int index = 0 , swap = 0;
            while(!map.isEmpty()){
                int key = map.lastKey();
                int i = map.get(key);
                map.remove(key);
                if(arr[index] == key) index++;
                else {
                    arr[i] = arr[index];
                    arr[index] = key;
                    index++;
                    swap++;
                    map.put(arr[i] , i);
                }
            }
            return swap;
        }
    }
}
