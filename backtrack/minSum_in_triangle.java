void main() {
    int[][] arr = {{2}, {3, 4}, {6, 5, 7}, {4, 1, 8, 3}};
    ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    for (int[] row : arr) {
        ArrayList<Integer> temp = new ArrayList<>();
        for (int num : row) {
            temp.add(num);
        }
        list.add(temp);
    }
    Solution sol = new Solution();
    System.out.println(sol.minimumTotal(list));
}

static class Solution {
    public int minimumTotal(ArrayList<ArrayList<Integer>> list) {
        int n = list.size();
        int[] prev = new int[n];
        int[] curr = new int[n];
        Arrays.fill(prev, 2000);
        Arrays.fill(curr, 2000);
        prev[0] = list.get(0).get(0);
        for (int i = 1; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                int currNum = list.get(i).get(j);
                curr[j] = currNum + prev[j];
                if (j > 0 && currNum + prev[j - 1] < curr[j]) curr[j] = currNum + prev[j - 1];
            }
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }
        int min = prev[0];
        for (int num : prev) if (min > num) min = num;
        return min;
    }
}