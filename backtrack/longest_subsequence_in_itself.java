void main() {

}

static class Solution {
    public int[] maxLCS(String A) {
        int n = A.length();
        if (n == 1) return new int[]{0, 0};
        if (n == 2) {
            if (A.charAt(0) == A.charAt(1)) return new int[]{0, 1};
            return new int[]{0, 0};
        }
        int[] ans = new int[]{-1, -1};
        for (int i = 0; i < n - 1; i++) {
            String s1 = A.substring(0, i + 1);
            String s2 = findRev(A, i + 1, n - 1);
            int temp = find(s1, s2);
            if (temp > ans[1]) {
                ans[1] = temp;
                ans[0] = i + 1;
            }
        }
        return ans;
    }

    private String findRev(String s, int left, int right) {
        StringBuilder sb = new StringBuilder();
        while (right >= left) sb.append(s.charAt(right--));
        return sb.toString();
    }

    private int find(String s1, String s2) {
        int s1Length = s1.length();
        int s2Length = s2.length();
        if (s1Length == 0 || s2Length == 0) return 0;
        int[] prev = new int[s1Length];
        int[] curr = new int[s1Length];
        for (int i = 0; i < s1Length; i++) {
            if (s1.charAt(i) == s2.charAt(i)) prev[i] = 1;
            else if (i > 0 && prev[i - 1] == 1) prev[i] = 1;
        }
        for (int i = 1; i < s2Length; i++) {
            for (int j = 0; j < s1Length; j++) {
                if (s1.charAt(j) == s2.charAt(i)) {
                    curr[j] = 1;
                    if (j > 0) curr[j] += prev[j - 1];
                } else {
                    curr[j] = prev[j];
                    if (j > 0 && curr[j - 1] > curr[j]) curr[j] = curr[j - 1];
                }
            }
            int[] temp = curr;
            curr = prev;
            prev = temp;
        }
        return prev[s1Length - 1];
    }
}
