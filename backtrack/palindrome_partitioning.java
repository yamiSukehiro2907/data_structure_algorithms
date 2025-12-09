void main() {
    Solution sol = new Solution();
    String s = "a";
    ArrayList<ArrayList<String>> ans = sol.partition(s);
    for (ArrayList<String> list : ans) {
        System.out.println(list);
    }
}

static class Solution {
    private ArrayList<Pair> palindromes;
    private ArrayList<ArrayList<String>> partitions;
    private Map<Integer, List<Pair>> startPalindromesMap;

    public ArrayList<ArrayList<String>> partition(String word) {
        this.palindromes = new ArrayList<>();
        this.partitions = new ArrayList<>();
        this.startPalindromesMap = new HashMap<>();
        fill(word);
        fillMap();
        form(word, 0, -1, new ArrayList<>());
        Collections.sort(partitions, new Comparator<ArrayList<String>>() {
            @Override
            public int compare(ArrayList<String> a, ArrayList<String> b) {
                int minLen = Math.min(a.size(), b.size());
                for (int i = 0; i < minLen; i++) {
                    int lenA = a.get(i).length();
                    int lenB = b.get(i).length();
                    if (lenA != lenB) {
                        return lenA - lenB;
                    }
                }
                return a.size() - b.size();
            }
        });
        return partitions;
    }

    private void form(String word, int index, int lastIndex, ArrayList<String> list) {
        if (lastIndex == word.length() - 1) {
            partitions.add(new ArrayList<>(list));
            return;
        }
        if (index >= word.length()) return;
        List<Pair> pairs = startPalindromesMap.get(index);
        if (pairs == null) return;
        for (Pair pair : pairs) {
            list.add(word.substring(pair.startIndex, pair.endIndex + 1));
            form(word, pair.endIndex + 1, pair.endIndex, list);
            list.removeLast();
        }
    }

    private void fillMap() {
        for (Pair pair : palindromes) {
            if (!startPalindromesMap.containsKey(pair.startIndex)) {
                startPalindromesMap.put(pair.startIndex, new ArrayList<>());
            }
            startPalindromesMap.get(pair.startIndex).add(pair);
        }
    }

    private static class Pair {
        private final int startIndex;
        private final int endIndex;

        public Pair(int startIndex, int endIndex) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }
    }

    private void fill(String word) {
        for (int i = 0; i < word.length(); i++) {
            addOddPalindrome(word, i);
            addEvenPalindrome(word, i);
        }
    }

    private void addOddPalindrome(String word, int index) {
        palindromes.add(new Pair(index, index));
        int left = index - 1, right = index + 1;
        findPalindrome(word, left, right);
    }

    private void addEvenPalindrome(String word, int right) {
        int left = right - 1;
        findPalindrome(word, left, right);
    }

    private void findPalindrome(String word, int left, int right) {
        while (left >= 0 && right < word.length()) {
            if (word.charAt(left) == word.charAt(right)) {
                palindromes.add(new Solution.Pair(left, right));
                left--;
                right++;
            } else break;
        }
    }

}
