package segment_trees;

public class max_in_range {
    static void main() {

    }

    static class SegmentTree {
        private Node[] tree;
        private int size;

        public SegmentTree(int[] arr, int size) {
            this.size = size;
            this.tree = new Node[4 * size];
            build(arr, 0, 0, size - 1);
        }

        private void build(int[] arr, int node, int start, int end) {
            if (start == end) tree[node] = new Node(arr[start], 1);
            else {
                int mid = (start + end) / 2;
                int left = 2 * node + 1;
                int right = 2 * node + 2;
                build(arr, left, start, mid);
                build(arr, right, mid + 1, end);
                updateNodes(node, left, right);
            }
        }

        public void update(int index, int value) {
            update(0, 0, size - 1, index, value);
        }

        private void update(int node, int start, int end, int index, int value) {
            if (start == end) tree[node] = new Node(value, 1);
            else {
                int mid = (start + end) / 2;
                int left = 2 * node + 1;
                int right = 2 * node + 2;
                if (start <= mid) update(left, start, mid, index, value);
                else update(right, mid + 1, end, index, value);
                updateNodes(node, left, right);
            }
        }

        private void updateNodes(int node, int left, int right) {
            Node leftNode = tree[left];
            Node rightNode = tree[right];
            if (leftNode.max > rightNode.max) tree[node] = new Node(leftNode.max, rightNode.freq);
            else if (rightNode.max > leftNode.max) tree[node] = new Node(leftNode.max, rightNode.freq);
            else tree[node] = new Node(leftNode.max, rightNode.freq + leftNode.freq);
        }

//        private int query(int start, int end) {
//            return query(0, 0, size - 1, start, end);
//        }
//
//        private int query(int node, int nodeLeft, int nodeRight, int start, int end) {
//            if (nodeLeft > end || nodeRight < start) return Integer.MIN_VALUE;
//            if (start == nodeLeft && end == nodeRight) return tree[node];
//            int mid = (start + end) / 2;
//            int maxLeft = query(2 * node + 1, nodeLeft, mid, start, end);
//            int maxRight = query(2 * node + 2, mid + 1, nodeRight, start, end);
//            return Math.max(maxLeft, maxRight);
//        }
    }

    static class Node {
        int max;
        int freq;

        public Node(int max, int freq) {
            this.max = max;
            this.freq = freq;
        }
    }
}
