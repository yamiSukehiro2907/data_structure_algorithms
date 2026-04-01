void main() {
    String s1 = "abcde";
    String s2 = "ace";
    System.out.println(lcs(s1, s2));
}

int[][] dp;

int lcs(String s1, String s2) {
    dp = new int[s1.length()][s2.length()];
    for(int[] temp : dp) Arrays.fill(temp, -1);
    for (int i = 0; i < s2.length(); i++) if (s2.charAt(i) == s1.charAt(0)) dp[0][i] = 1;
    for (int i = 0; i < s1.length(); i++) if (s1.charAt(i) == s2.charAt(0)) dp[i][0] = 1;
    int ans = find(s1.length() - 1, s2.length() - 1, s1, s2);
    for(int[] temp : dp) System.out.println(Arrays.toString(temp));
    return ans;
}

int find(int s1Index, int s2Index, String s1, String s2) {
    if (s1Index < 0 || s2Index < 0) return 0;
    if (dp[s1Index][s2Index] != -1) return dp[s1Index][s2Index];
    if (s1.charAt(s1Index) == s2.charAt(s2Index))
        return dp[s1Index][s2Index] = 1 + find(s1Index - 1, s2Index - 1, s1, s2);
    return dp[s1Index][s2Index] = Math.max(find(s1Index - 1, s2Index, s1, s2), find(s1Index, s2Index - 1, s1, s2));
}