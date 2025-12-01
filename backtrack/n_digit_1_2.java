void main() {
    int n = 3;
    find(n, new StringBuilder());
    add(0 , n , new ArrayList<>());
    IO.println(sb_list);
    IO.println(array_list);
}

private static final ArrayList<Integer> sb_list= new ArrayList<>();
private  static final ArrayList<ArrayList<Integer>> array_list = new ArrayList<>();

private static void find(int n, StringBuilder sb) {
    if (sb.length() == n) {
        sb_list.add(Integer.valueOf(sb.toString()));
        return;
    }
    sb.append('1');
    find(n, sb);
    sb.deleteCharAt(sb.length() - 1);
    sb.append('2');
    find(n, sb);
    sb.deleteCharAt(sb.length() - 1);
}

private void add(int index , int n , ArrayList<Integer> temp) {
    if(temp.size() == n){
        array_list.add(new ArrayList<>(temp));
        return;
    }
    temp.add(1);
    add(index + 1, n , temp);
    temp.removeLast();
    temp.add(2);
    add(index + 1, n , temp);
    temp.removeLast();
}
