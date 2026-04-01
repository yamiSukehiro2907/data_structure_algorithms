package segment_trees;

public class SegmentTree {
    private int[] tree;
    private int size;

    public SegmentTree(int[] arr) {
        size = arr.length;
        tree = new int[4 * size];
        build(arr, 0, 0, size - 1);
    }

    private void build(int[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            int left = 2 * node + 1;
            int right = 2 * node + 2;
            build(arr, left, start, mid);
            build(arr, right, mid + 1, end);
            tree[node] = tree[left] + tree[right];
        }
    }

    public int query(int start, int end) {
        return query(0, 0, size - 1, start, end);
    }

    private int query(int node, int nodeLeft, int nodeRight, int start, int end) {
        if (end < nodeLeft || start < nodeRight) return 0;
        if (start == nodeLeft && end == nodeRight) return tree[node];
        int mid = (start + end) / 2;
        int left = query(node * 2 + 1, nodeLeft, mid, start, end);
        int right = query(node * 2 + 1, mid + 1, end, start, end);
        return left + right;
    }

    public void update(int index, int value) {
        update(0, 0, size - 1, index, value);
    }

    private void update(int node, int start, int end, int index, int value) {
        if (start == end) {
            tree[node] = value;
        } else {
            int mid = (start + end) / 2;
            if (index <= mid) {
                update(2 * node + 1, start, mid, index, value);
            } else {
                update(2 * node + 2, mid + 1, end, index, value);
            }
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }
}
