package dynammic_programming;

public class longest_palindromic_substring {
    static void main() {

    }

    static class Solution {
        public int solve(String A) {
            StringBuilder sb = new StringBuilder("#");
            int length = A.length();
            for (int i = 0; i < length; i++) sb.append(A.charAt(i)).append('#');
            // all substrings will be odd aba -> #a#b#a#
            int n = sb.length();
            char[] arr = sb.toString().toCharArray();
            int[] palindrome = new int[n];
            int centre = 0, right = 0;
            // center of a possible substring and max right pointer
            for (int i = 0; i < n; i++) {
                if (i < right) palindrome[i] = Math.min(palindrome[2 * centre - i], right - i);
                int start = i - palindrome[i] - 1;
                int end = i + palindrome[i] + 1;
                while (start >= 0 && end < n && arr[start] == arr[end]) {
                    palindrome[i]++;
                    start--;
                    end++;
                }
                if (i + palindrome[i] > right) {
                    centre = i;
                    right = i + palindrome[i];
                }
            }
            int maxLength = 0;
            for (int num : palindrome) maxLength = Math.max(num, maxLength);
            return maxLength;
        }
    }
}

