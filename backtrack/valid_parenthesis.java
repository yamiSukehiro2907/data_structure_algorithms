void main() {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    if (n % 2 == 1) {
        System.out.println("Invalid Input");
        return;
    }
    find(new StringBuilder(), n / 2, n / 2);
    System.out.println(list);
}

static List<String> list = new ArrayList<>();

static void find(StringBuilder sb, int close, int open) {
    if (close < 0 || open < 0) return;
    if (close == open) {
        if (close == 0) {
            list.add(sb.toString());
            return;
        }
        sb.append('(');
        find(sb, close, open - 1);
        sb.deleteCharAt(sb.length() - 1);
    } else if (close > open) {
        sb.append(')');
        find(sb, close - 1, open);
        sb.deleteCharAt(sb.length() - 1);
        sb.append('(');
        find(sb, close, open - 1);
        sb.deleteCharAt(sb.length() - 1);
    }
}

