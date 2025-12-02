void main() {
    int[] arr = {1 , 2 , 3};
    generate(0 , arr , new ArrayList<>());
    for(List<Integer> subset : subsets)
    {
        System.out.println(subset);
    }
}

static List<List<Integer>> subsets = new ArrayList<>();

void generate(int index , int[] arr , List<Integer> temp){
    subsets.add(new ArrayList<>(temp));
    if(index >= arr.length) return;
    for(int i = index; i < arr.length ; i++){
        temp.add(arr[i]);
        generate(i + 1, arr , temp);
        temp.removeLast();
    }
}
