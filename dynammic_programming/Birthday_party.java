package dynammic_programming;

/*
As it is Tushar's Birthday on March 1st, he decided to throw a party to all his friends at TGI Fridays in Pune.
Given are the eating capacity of each friend, filling capacity of each dish and cost of each dish.
A friend is satisfied if the sum of the filling capacity of dishes he ate is equal to his capacity.
Find the minimum cost such that all of Tushar's friends are satisfied (reached their eating capacity).
NOTE:
1. Each dish is supposed to be eaten by only one person. Sharing is not allowed.
2. Each friend can take any dish unlimited number of times.
3. There always exists a dish with filling capacity 1 so that a solution always exists.
*/
public class Birthday_party {
    private Integer[][] dp;
    private int[] dishCapacity;
    private int[] dishCost;

    public int solve(final int[] personCapacity, final int[] dishCapacity, final int[] dishCost) {
        this.dishCapacity = dishCapacity;
        this.dishCost = dishCost;
        int minCost = 0;
        int maxCap = personCapacity[0];
        for (int cap : personCapacity) maxCap = Math.max(cap, maxCap);
        int totalDish = dishCapacity.length;
        this.dp = new Integer[totalDish][maxCap + 1];
        for (int friendCapacity : personCapacity) {
            int friendCost = find(0, friendCapacity);
            minCost += friendCost;
        }
        return minCost;
    }

    private int find(int index, int capacity) {
        if (index >= dishCapacity.length) return capacity == 0 ? 0 : Integer.MAX_VALUE;
        if (capacity == 0) return 0;
        if (dp[index][capacity] != null) return dp[index][capacity];
        int minCost = Integer.MAX_VALUE, dishPrice = dishCost[index], dishCap = dishCapacity[index], times = 0;
        while (true) {
            int dishCost = times * dishPrice;
            int dishFillCap = times * dishCap;
            if (capacity - dishFillCap < 0) break;
            int remCost = find(index + 1, capacity - dishFillCap);
            int totalCost = (remCost == Integer.MAX_VALUE) ? Integer.MAX_VALUE : remCost + dishCost;
            minCost = Math.min(minCost, totalCost);
            if (capacity - dishFillCap <= 0) break;
            times++;
        }
        return dp[index][capacity] = minCost;
    }

    static void main() {
        Birthday_party b = new Birthday_party();
        int[] personCapacity = {2, 3, 1, 5, 4};
        int[] dishCapacity = {3, 2, 4, 1};
        int[] dishCost = {1, 2, 5, 10};
        System.out.println(b.solve(personCapacity, dishCapacity, dishCost));
    }
}
