void main() {
    int n = 8;
    System.out.println(find(n));
    optimized(n);
}

int find(int n) {
    return findPaths(n, 0);
}

int findPaths(int n, int step) {
    if (step == n) return 1;
    if (step > n) return 0;
    int count = 0;
    count += findPaths(n, step + 1);
    count += findPaths(n, step + 2);
    return count;
}

void optimized(int n) {
    int[] arr = new int[n + 1];
    arr[1] = 1;
    for (int i = 2; i <= n; i++) {
        arr[i] = arr[i - 1] + arr[i - 2];
    }
    System.out.println(Arrays.toString(arr));
}
