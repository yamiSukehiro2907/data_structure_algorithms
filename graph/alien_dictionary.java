package graph;

import java.util.*;

public class alien_dictionary {
    public static void main(String[] args) {
        String[] words = {"dddc", "a", "ad", "ab", "b", "be", "cd", "cded" };
        Solution solution = new Solution();
        System.out.println(solution.findOrder(words));
    }

    private static class Solution {
        public String findOrder(String[] words) {
            List<Set<Integer>> adjList = new ArrayList<>();
            for (int i = 0; i < 26; i++) adjList.add(new HashSet<>());
            boolean[] addInRules = new boolean[26];
            for (String word : words) {
                for (char c : word.toCharArray()) {
                    addInRules[c - 'a'] = true;
                }
            }
            for (int i = 0; i < words.length - 1; i++) {
                String word1 = words[i], word2 = words[i + 1];
                int index = 0, length = Math.min(word1.length(), word2.length());
                while (index < length && word1.charAt(index) == word2.charAt(index)) index++;
                if (index == length && word1.length() > word2.length()) return "";
                if (index < length) adjList.get(word1.charAt(index) - 'a').add(word2.charAt(index) - 'a');
            }
            if (!isDAG(adjList)) return "";
            Stack<Integer> stack = new Stack<>();
            boolean[] visited = new boolean[26];
            for (int i = 0; i < 26; i++) if (!visited[i]) dfs(i, visited, stack, adjList);
            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                int num = stack.pop();
                if (addInRules[num]) sb.append((char) (num + 'a'));
            }
            return sb.toString();
        }

        private boolean isDAG(List<Set<Integer>> list) {
            boolean[] visited = new boolean[26];
            for (int i = 0; i < 26; i++)
                if (!visited[i]) {
                    visited[i] = true;
                    if (isCycle(visited, list, i)) return false;
                    visited[i] = false;
                }
            return true;
        }

        private boolean isCycle(boolean[] visited, List<Set<Integer>> list, int src) {
            for (int node : list.get(src)) {
                if (visited[node]) return true;
                visited[node] = true;
                if (isCycle(visited, list, node)) return true;
                visited[node] = false;
            }
            return false;
        }

        private void dfs(int src, boolean[] visited, Stack<Integer> stack, List<Set<Integer>> adjList) {
            visited[src] = true;
            for (int node : adjList.get(src)) if (!visited[node]) dfs(node, visited, stack, adjList);
            stack.push(src);
        }
    }
}