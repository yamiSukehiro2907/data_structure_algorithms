package array;

public class longest_common_prefix {
    public static void main(String[] args) {

    }

    private static class Solution {
        public String longestCommonPrefix(String[] strs) {
            int minlength = Integer.MAX_VALUE;
            for (String s : strs) {
                minlength = Math.min(minlength, s.length());
            }
            StringBuilder sb = new StringBuilder();
            int k = 0;
            while (k < minlength) {
                char ch = strs[0].charAt(k);
                boolean present = true;
                for (String str : strs) {
                    if (str.charAt(k) != ch) {
                        present = false;
                        break;
                    }
                }
                if (present) sb.append(ch);
                k++;
            }
            return sb.toString();
        }
    }
}