package segment_trees;

public class product_sum_of_subarray {
    static void main() {

    }

    static class Node {
        int elementSum;
        int productSum;

        public Node(int elementSum, int productSum) {
            this.elementSum = elementSum;
            this.productSum = productSum;
        }
    }

    static class SegmentTree {
        private Node[] tree;
        private int size;

        public SegmentTree(int[] arr) {
            this.size = arr.length;
            tree = new Node[size * 4];
            build(0, 0, size - 1, arr, new int[0]);
        }

        private void build(int node, int start, int end, int[] arr, int[] index) {
            if (start == end) {
                tree[node] = new Node(arr[index[0]], arr[index[0]] * (index[0] + 1));
                index[0]++;
            } else {
                int mid = (start + end) / 2;
                int left = 2 * node + 1;
                int right = 2 * node + 2;
                build(left, start, mid, arr, index);
                build(right, mid + 1, end, arr, index);
                Node leftNode = tree[left];
                Node rightNode = tree[right];
                int elementSum = leftNode.elementSum + rightNode.elementSum;
                int productSum = leftNode.productSum + rightNode.productSum;
                tree[node] = new Node(elementSum, productSum);
            }
        }

        public int query(int left , int right){
            return query(0 , 0 , size - 1 , left , right);
        }

        private int query(int node , int nodeLeft , int nodeRight , int start , int end){
            return 0;
        }
    }
}
