void main() {
    int[] arr = {2, 7, 9, 3, 1};
    System.out.println(find(arr, 0));
    optimized(arr);
}

int find(int[] arr, int index) {
    if (index >= arr.length) return 0;
    int take = arr[index] + find(arr, index + 2);
    int skip = find(arr, index + 1);
    return Math.max(take, skip);
}

void optimized(int[] arr) {
    int[] maxSumTillNow = new int[arr.length];
    Arrays.fill(maxSumTillNow, -1);
    find2(maxSumTillNow, arr, arr.length - 1);
    System.out.println(Arrays.toString(maxSumTillNow));
}

int find2(int[] maxSumTillNow, int[] arr, int index) {
    if (index < 0) return 0;
    if (maxSumTillNow[index] != -1) return maxSumTillNow[index];
    int take = arr[index] + find2(maxSumTillNow, arr, index - 2);
    int skip = find2(maxSumTillNow, arr, index - 1);
    return maxSumTillNow[index] = Math.max(take, skip);
}