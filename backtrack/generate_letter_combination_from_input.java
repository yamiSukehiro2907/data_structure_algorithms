void main() {
    String input = "56758";
    find(input);
}

static final String[] map = {"0" , "1" , "abc", "def", "ghe", "jkl", "mno", "pqrs", "tuv", "wxyz"};

static List<String> ans = new ArrayList<>();

static void find(String input) {
    fill(0, input, new StringBuilder());
    System.out.println(ans);
}

static void fill(int index, String input, StringBuilder sb) {
    if (index == input.length()) {
        ans.add(sb.toString());
        return;
    }
    char ch = input.charAt(index);
    for (char t : map[ch - '0'].toCharArray()) {
        sb.append(t);
        fill(index + 1, input, sb);
        sb.deleteCharAt(sb.length() - 1);
    }
}

