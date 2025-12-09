void main() {
    String[] dictionary = {"cat", "cats", "and", "sand", "dog"};
    String word = "catsanddog";
    find(dictionary, word);
    System.out.println(validSentences);
}

static List<String> validSentences = new ArrayList<>();

static void find(String[] dictionary, String word) {
    TrieNode dict = new TrieNode();
    for (String s : dictionary) fill(dict, s, 0);
    form(dict, word, 0, -1, new ArrayList<>());
}

static void form(TrieNode dict, String word, int index, int lastIndex, List<String> temp) {
    if (lastIndex == word.length() - 1) {
        StringBuilder sb = new StringBuilder();
        for (String s : temp) sb.append(s).append(" ");
        validSentences.add(sb.toString());
        return;
    }
    if (index >= word.length()) return;
    for (int i = index; i < word.length(); i++) {
        String str = word.substring(index, i + 1);
        if (isPresent(dict, str, 0)) {
            temp.add(str);
            form(dict, word, i + 1, i, temp);
            temp.removeLast();
        }
    }
}

static class TrieNode {
    TrieNode[] children;
    boolean isWord;

    TrieNode() {
        children = new TrieNode[26];
        isWord = false;
    }
}

static boolean isPresent(TrieNode root, String word, int index) {
    if (index >= word.length()) return root.isWord;
    char ch = word.charAt(index);
    if (root.children[ch - 'a'] == null) return false;
    return isPresent(root.children[ch - 'a'], word, index + 1);
}

static void fill(TrieNode root, String word, int index) {
    if (index == word.length()) {
        root.isWord = true;
        return;
    }
    char ch = word.charAt(index);
    if (root.children[ch - 'a'] == null) root.children[ch - 'a'] = new TrieNode();
    fill(root.children[ch - 'a'], word, index + 1);
}


