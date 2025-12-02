void main() {
    int[] arr = {-5 , 3};
    int target = -2;
    System.out.println((new Solution()).SubsetSum(arr, target));
}
static class Solution {
    public int SubsetSum(int[] A, int B) {
        return find(A, B, 0) ? 1 : 0;
    }
    private boolean find(int[] arr , int target , int index){
        if(target == 0) return true;
        if(index >= arr.length) return false;
        for(int i = index ; i < arr.length ; i++) {
            if (find(arr, target - arr[i], i + 1)) return true;
        }
        return false;
    }
}
