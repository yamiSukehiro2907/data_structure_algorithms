package map;
import java.util.*;

public class freqMap {
    public static void main(String[] args) {
        int arr[] = { 1, 6, 5, 3, 2, 1, 6, 7, 6 };
        int query[] = { 6, 7, 1 };
        int max = Integer.MIN_VALUE;
        for (int num : arr) {
            max = Math.max(max, num);
        }
        int freq[] = new int[max + 1];
        for (int num : arr) {
            freq[num - 0]++;
        }
        for (int i = 0; i < query.length; i++) {
            System.out.println(freq[query[i] - 0]);
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }
        for (int q : query) {
            System.out.println(map.getOrDefault(q, 0));
        }
    }

    public static class length_of_longest_subarray_with_sum_zero {
        public static void main(String[] args) {
            int arr[] = {};
            System.out.println(solve(arr));
        }

        private static int solve(int[] arr) {
            int ans = 0;
            HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
            int prefix[] = new int[arr.length + 1];
            for(int i = 1 ; i <= arr.length ; i++){
                prefix[i] = prefix[i - 1] + arr[i - 1];
            }
            for(int i = 1 ; i <= arr.length ; i++){
                if(!map.containsKey(prefix[i])){
                    map.put(prefix[i], new ArrayList<>());
                }
                map.get(prefix[i]).add(i);
            }
            return ans - 1;
        }
    }

    public static class subarray_with_sum_divisible_by_k {
        public static void main(String[] args) {
            int arr[] = {};
            int k = 1;
            System.out.println(solve(arr, k));
        }

        private static int solve(int[] arr, int k) {
            int prefix[] = new int[arr.length + 1];
            for (int i = 1; i <= arr.length; i++) {
                prefix[i] = prefix[i - 1] + arr[i - 1];
                prefix[i] = prefix[i] % k;
            }
            int ans = 0;
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 1; i <= arr.length; i++) {
                map.put(prefix[i], map.getOrDefault(prefix[i], 0) + 1);
                ans = Math.max(ans, map.get(prefix[i]));
            }
            return ans - 1;
        }
    }
}
