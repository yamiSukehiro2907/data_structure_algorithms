package prime_numbers;

import java.util.ArrayList;
import java.util.List;

public class prime_numbers {
    static void main() {
        int n = 20;
        List<Integer> list = findPrimeNumbers(n);
        System.out.println(list);
    }

    static List<Integer> findPrimeNumbers(int n) {
        List<Integer> list = new ArrayList<>();
        boolean[] isPrime = new boolean[n + 1];
        list.add(1);
        isPrime[1] = true;
        for (int i = 2; i <= n; i++) {
            if (!isPrime[i]) {
                list.add(i);
                for (int j = 2; i * j <= n; j++) {
                    isPrime[i * j] = true;
                }
            }
        }
        return list;
    }
}
