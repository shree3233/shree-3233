import java.util.Scanner;

public class KnapsackForOne {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        int[] value = new int[n];
        int[] weight = new int[n];

        System.out.println("Enter value and weight of each item:");
        for (int i = 0; i < n; i++) {
            value[i] = sc.nextInt();
            weight[i] = sc.nextInt();
        }

        System.out.print("Enter capacity of knapsack: ");
        int W = sc.nextInt();

        // DP table where dp[i][w] represents max value using first i items with capacity w
        int[][] dp = new int[n + 1][W + 1];

        // Build DP table
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= W; w++) {
                if (weight[i - 1] <= w) {
                    // Option 1: Take the item
                    // Option 2: Skip the item
                    dp[i][w] = Math.max(value[i - 1] + dp[i - 1][w - weight[i - 1]], dp[i - 1][w]);
                } else {
                    // Cannot take the item
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        System.out.println("Maximum value in the knapsack = " + dp[n][W]);
        sc.close();
    }
}

