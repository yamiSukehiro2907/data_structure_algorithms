void main() {
    int[] arr = {5 , 2 , 7};
    generate(0 , arr , new ArrayList<>() , 0 , 7);
    for(List<Integer> subset : subsets) System.out.println(subset);
}

static List<List<Integer>> subsets = new ArrayList<>();

void generate(int index , int[] arr , List<Integer> temp , int currSum , int k){
    if(currSum > k) return;
    if(currSum == k){
        subsets.add(new ArrayList<>(temp));
        return;
    }
    if(index >= arr.length) return;
    for(int i = index; i < arr.length ; i++){
        temp.add(arr[i]);
        generate(i + 1, arr , temp ,  currSum + arr[i] , k);
        temp.removeLast();
    }
}
