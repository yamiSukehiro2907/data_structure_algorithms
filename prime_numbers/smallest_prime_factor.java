package prime_numbers;

import java.util.Arrays;

public class smallest_prime_factor {
    static void main() {
        int n = 10;
        int[] factors = new int[n + 1];
        Arrays.fill(factors, -1);
        for (int i = 2; i <= n; i++) {
            if (factors[i] == -1) {
                factors[i] = i;
                for (int k = 2; k * i <= n; k++) {
                    if (factors[k * i] == -1) {
                        factors[k * i] = i;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(factors));
    }
}
