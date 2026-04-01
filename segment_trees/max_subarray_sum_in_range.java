package segment_trees;

public class max_subarray_sum_in_range {
    static void main() {

    }

    private static class SegmentTree {
        private Node[] tree;
        private int size;

        public SegmentTree(int[] arr) {
            this.size = arr.length;
            tree = new Node[size * 4];
            build(0, 0, size - 1, arr);
        }

        private void build(int node, int start, int end, int[] arr) {
            if (start == end) tree[node] = new Node(arr[start], arr[start], arr[start], arr[start]);
            else {
                int mid = (start + end) / 2;
                int left = 2 * node + 1;
                int right = 2 * node + 2;
                build(left, start, mid, arr);
                build(right, mid + 1, end, arr);
                Node leftNode = tree[left];
                Node rightNode = tree[right];
                int totalSum = leftNode.totalSum + rightNode.totalSum;
                int prefixSum = Math.max(leftNode.maxPrefixSum, leftNode.totalSum + rightNode.maxPrefixSum);
                int suffixSum = Math.max(rightNode.maxSuffixSum, rightNode.totalSum + leftNode.maxSuffixSum);
                int maxSubarraySum = Math.max(leftNode.maxSubarraySum, Math.max(rightNode.maxSubarraySum, leftNode.maxSuffixSum + rightNode.maxPrefixSum));
                tree[node] = new Node(prefixSum, suffixSum, maxSubarraySum, totalSum);
            }
        }
    }

    private static class Node {
        int maxPrefixSum;
        int maxSuffixSum;
        int maxSubarraySum;
        int totalSum;

        public Node(int maxPrefixSum, int maxSuffixSum, int maxSubarraySum, int totalSum) {
            this.maxPrefixSum = maxPrefixSum;
            this.maxSuffixSum = maxSuffixSum;
            this.maxSubarraySum = maxSubarraySum;
            this.totalSum = totalSum;
        }
    }
}
