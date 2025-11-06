import java.util.Scanner;

public class Knapsack {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        double[] value = new double[n];
        double[] weight = new double[n];
        double[] ratio = new double[n];

        System.out.println("Enter value and weight of each item:");
        for (int i = 0; i < n; i++) {
            System.out.println("Value of "+(i+1)+"th item: ");
            value[i] = sc.nextDouble();
            System.out.println("Weight of "+(i+1)+"th item: ");
            weight[i] = sc.nextDouble();
            ratio[i] = value[i] / weight[i];  // value-to-weight ratio
        }

        System.out.print("Enter capacity of knapsack: ");
        double W = sc.nextDouble();

        // Sort items by ratio in descending order (simple bubble sort)
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (ratio[j] < ratio[j + 1]) {
                    // swap ratio
                    double temp = ratio[j];
                    ratio[j] = ratio[j + 1];
                    ratio[j + 1] = temp;

                    // swap value
                    double tempVal = value[j];
                    value[j] = value[j + 1];
                    value[j + 1] = tempVal;

                    // swap weight
                    double tempWt = weight[j];
                    weight[j] = weight[j + 1];
                    weight[j + 1] = tempWt;
                }
            }
        }

        double totalValue = 0.0;

        // Add items to knapsack
        for (int i = 0; i < n; i++) {
            if (weight[i] <= W) {
                // Take whole item
                W -= weight[i];
                totalValue += value[i];
            } else {
                // Take fractional part
                totalValue += value[i] * (W / weight[i]);
                break;
            }
        }

        System.out.println("Maximum value in the knapsack = " + totalValue);
        sc.close();
    }
}

