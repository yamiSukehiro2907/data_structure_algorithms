package dynammic_programming;

/*
Rishik likes candies a lot. So, he went to a candy-shop to buy candies.
The shopkeeper showed him N packets each containg A[i] candies for cost of C[i] nibbles, each candy in that packet has a sweetness B[i].
The shopkeeper puts the condition that Rishik can buy as many complete candy-packets as he wants but he can't buy a part of the packet.
Rishik has D nibbles, can you tell him the maximum amount of sweetness he can get from candy-packets he will buy?
*/
public class Buying_Candies {
    private Candy[] candies;
    private Integer[][] dp;

    public int solve(int[] count, int[] sweet, int[] cost, int money) {
        int n = count.length;
        this.candies = new Candy[n];
        for (int i = 0; i < n; i++) this.candies[i] = new Candy(count[i], sweet[i], cost[i]);
        this.dp = new Integer[n][money + 1];
        return find(0, money);
    }

    private int find(int index, int moneyLeft) {
        if (index >= this.candies.length) return 0;
        if (dp[index][moneyLeft] != null) return dp[index][moneyLeft];
        Candy candy = candies[index];
        int maxSweet = 0;
        int onePacketSweetness = candy.sweet * candy.stock;
        for (int packetCount = 0; candy.cost * packetCount <= moneyLeft; packetCount++) {
            int candyTotalPacketCost = candy.cost * packetCount;
            int totalSweetness = onePacketSweetness * packetCount + find(index + 1, moneyLeft - candyTotalPacketCost);
            maxSweet = Math.max(maxSweet, totalSweetness);
        }
        return dp[index][moneyLeft] = maxSweet;
    }

    static class Candy {
        int stock;
        int sweet;
        int cost;

        Candy(int stock, int sweet, int cost) {
            this.stock = stock;
            this.sweet = sweet;
            this.cost = cost;
        }
    }
}