void main() {
    String s = "(a)())()";
    Solution sol = new Solution();
    List<String> ans = sol.removeInvalidParentheses(s);
    System.out.println(ans);
}

static class Solution {
    private int minRemoved;
    private int[] temp;
    private Set<String> ans;
    private int totalOpen;
    private int totalClose;

    public List<String> removeInvalidParentheses(String s) {
        this.ans = new HashSet<>();
        this.totalOpen = 0;
        this.totalClose = 0;
        this.minRemoved = s.length();
        this.temp = new int[s.length()];
        fill(s);
        find(totalOpen, totalClose, 0, 0);
        return new ArrayList<>(ans);
    }

    private void fill(String s) {
        for (int i = 0; i < s.length(); i++) {
            temp[i] = s.charAt(i);
            if (s.charAt(i) == '(') totalOpen++;
            else if (s.charAt(i) == ')') totalClose++;
        }
    }

    private boolean valid() {
        int o = 0, c = 0;
        for (int num : temp) {
            if (num == -1) continue;
            char ch = (char) (num);
            if (ch == '(') o++;
            if (ch == ')') c++;
            if (c > o) return false;
        }
        return o == c;
    }


    private void add(int removed) {
        if (minRemoved < removed) return;
        StringBuilder sb = new StringBuilder();
        for (int num : temp) {
            if (num == -1) continue;
            sb.append((char) (num));
        }
        if (minRemoved > removed) {
            ans.clear();
            minRemoved = removed;
        }
        ans.add(sb.toString());
    }

    private void find(int open, int close, int removed, int start) {
        if (open < 0 || close < 0) {
            return;
        }
        if (removed > minRemoved) return;
        if (open == close && valid()) {
            add(removed);
            return;
        }
        for (int i = start; i < temp.length; i++) {
            if (temp[i] == -1) continue;
            char ch = (char) (temp[i]);
            if ((ch == '(') && (i == 0 || ((char) (temp[i - 1]) != '(') || temp[i - 1] == -1)) {
                temp[i] = -1;
                find(open - 1, close, removed + 1, i);
                temp[i] = ch;
            } else if ((ch == ')') && (i == 0 || ((char) (temp[i - 1]) != ')') || temp[i - 1] == -1)) {
                temp[i] = -1;
                find(open, close - 1, removed + 1, i);
                temp[i] = ch;
            }
        }
    }
}
//