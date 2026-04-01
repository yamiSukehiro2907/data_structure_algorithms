package prime_numbers;

import java.util.Arrays;

public class count_of_divisors {
    static void main() {
        int[] num = {6, 3, 12, 18, 4, 16, 11};
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.count(num)));
        Solution2 s2 = new Solution2();
        System.out.println(Arrays.toString(s2.count(num)));
    }

    static class Solution {
        int[] count(int[] arr) {
            int max = arr[0];
            for (int num : arr) if (num > max) max = num;
            int[] factors = new int[max + 1];
            Arrays.fill(factors, 1);
            for (int i = 2; i <= max; i++) {
                factors[i]++;
                for (int j = 2; j * i <= max; j++) factors[j * i]++;
            }
            int[] ans = new int[arr.length];
            for (int i = 0; i < arr.length; i++) ans[i] = factors[arr[i]];
            return ans;
        }
    }


    static class Solution2 {
        int[] count(int[] arr) {
            int max = arr[0];
            for (int num : arr) if (num > max) max = num;
            int[] primes = findPrimes(max);
            int[] ans = new int[arr.length];
            for (int i = 0; i < arr.length; i++) ans[i] = countFactors(arr[i], primes);
            return ans;
        }

        int[] findPrimes(int n) {
            boolean[] primes = new boolean[n + 1];
            Arrays.fill(primes, true);
            int count = 1;
            for (int i = 2; i <= n; i++) {
                if (primes[i]) {
                    count++;
                    for (int j = i * i; j <= n; j += i) primes[j] = false;
                }
            }
            int[] counts = new int[count];
            int index = 0;
            for (int i = 1; i <= n; i++) if (primes[i]) counts[index++] = i;
            return counts;
        }

        int countFactors(int n, int[] primes) {
            int totalFactors = 1 , prevNum = 1;
            while (n > 1) {
                int num = getLowestFactor(prevNum, primes) , count = 0;
                while (n % num == 0) {
                    n /= num;
                    count++;
                }
                totalFactors *= (count + 1);
                prevNum = num;
            }
            return totalFactors;
        }

        int getLowestFactor(int target, int[] primes) {
            int start = 0;
            int end = primes.length - 1;
            while (start < end) {
                int mid = start + (end - start) / 2;
                if (primes[mid] > target) end = mid;
                else start = mid + 1;
            }
            return primes[end];
        }

    }
}