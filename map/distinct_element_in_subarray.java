package map;

import java.util.*;

public class distinct_element_in_subarray {
    public static void main(String[] args) {
        int arr[] = { 3, 2, 1, 3, 5, 6, 2, 5, 5, 7 };
        int k = 4;
        ArrayList<Integer> list = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < k; i++) map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        list.add(map.size());
        for (int i = k; i < arr.length; i++) {
            map.put(arr[i - k], map.get(arr[i - k]) - 1);
            if (map.get(arr[i - k]) == 0) map.remove(arr[i - k]);
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
            list.add(map.size());
        }
        for (int num : list) {
            System.out.print(num + " ");
        }
    }

    public static class pair_of_anagrams {
        public static void main(String[] args) {
            String[] arr = { "ate", "tea", "tan", "ate", "nat", "bat" };
            ArrayList<ArrayList<Integer>> list = new ArrayList<>();
            HashMap<String, ArrayList<Integer>> map = new HashMap<>();
            for (int i = 0; i < arr.length; i++) {
                String s = arr[i];
                String sort = sort(s);
                if (!map.containsKey(sort))
                    map.put(sort, new ArrayList<>());
                map.get(sort).add(i + 1);
            }
            for(String key : map.keySet()){
                list.add(map.get(key));
            }
            for(int i = 0 ; i < list.size() ; i++){
                for(int j = 0 ; j < list.get(i).size() ; j++){
                    System.out.print(list.get(i).get(j) + " ");
                }
                System.out.println();
            }
        }

        private static String sort(String s) {
            StringBuilder sb = new StringBuilder();
            int freq[] = new int[26];
            for (int i = 0; i < s.length(); i++)
                freq[s.charAt(i) - 'a']++;
            int i = 0;
            while (i < 26) {
                if (freq[i] > 0) {
                    sb.append(i + 'a');
                    freq[i]--;
                } else {
                    i++;
                }
            }
            return sb.toString();
        }
    }

    public static class presence_of_subarray_sum_zero {
        public static void main(String[] args) {
            int arr[] = { 7, -3, -2, 1, -3, 4, 5, 10, -7 };
            int k = 0;
            System.out.println(solve(arr, k));
        }

        private static int solve(int arr[], int k) {
            int prefix[] = new int[arr.length + 1];
            for (int i = 1; i <= arr.length; i++) {
                prefix[i] = prefix[i - 1] + arr[i - 1];
            }
            int ans = 0;
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 1; i <= arr.length; i++) {
                int cur = 0;
                if(map.containsKey(prefix[i] + k)){

                }
            }
            return ans - 1;
        }
    }
}
