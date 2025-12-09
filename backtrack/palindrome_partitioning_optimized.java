void main() {
    Solution sol = new Solution();
    String s = "abba";
    ArrayList<ArrayList<String>> ans = sol.partition(s);
    for (ArrayList<String> list : ans) {
        System.out.println(list);
    }
}


public class Solution {
    private ArrayList<ArrayList<String>> palindromes;
    private boolean[][] isPalindrome;

    public ArrayList<ArrayList<String>> partition(String word) {
        int n = word.length();
        isPalindrome = new boolean[n][n];
        findPalindromes(word);
        this.palindromes = new ArrayList<>();
        formPartitions(word, 0, new ArrayList<>());
        Collections.sort(palindromes, (listA, listB) -> {
            for (int i = 0; i < Math.min(listA.size(), listB.size()); i++) {
                String s1 = listA.get(i);
                String s2 = listB.get(i);
                if (s1.length() != s2.length()) {
                    return s1.length() - s2.length();
                }
            }
            return listA.size() - listB.size();
        });
        return palindromes;
    }

    private void formPartitions(String word, int index, ArrayList<String> list) {
        if (index == word.length()) {
            palindromes.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < word.length(); i++) {
            if (isPalindrome[index][i]) {
                list.add(word.substring(index, i + 1));
                formPartitions(word, i + 1, list);
                list.remove(list.size() - 1);
            }
        }
    }

    private void findPalindromes(String word) {
        for (int i = 0; i < word.length(); i++) {
            addOddPalindrome(word, i);
            addEvenPalindrome(word, i);
        }
    }

    private void addOddPalindrome(String word, int index) {
        isPalindrome[index][index] = true;
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
                isPalindrome[left][right] = true;
                left--;
                right++;
            } else break;
        }
    }
}
