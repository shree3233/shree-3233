import java.util.Scanner;

public class fibo {

    // Recursive method to calculate Fibonacci number
    static int fibonacciRecursive(int n) {
        if (n <= 1)
            return n; // Base cases: F(0) = 0, F(1) = 1
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    // Iterative method to print Fibonacci series
    static void fibonacciIterative(int n) {
        int a = 0, b = 1, c;
        System.out.println("\nFibonacci Series using Iteration:");
        for (int i = 0; i < n; i++) {
            System.out.print(a + " ");
            c = a + b;
            a = b;
            b = c;
        }
    }

    // Recursive method to print Fibonacci series
    static void printFibonacciRecursive(int n) {
        System.out.println("\nFibonacci Series using Recursion:");
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacciRecursive(i) + " ");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of terms: ");
        int n = sc.nextInt();

        // Handle negative input
        if (n < 0) {
            System.out.println("Please enter a non-negative number!");
        } else if (n == 0) {
            System.out.println("No terms to display!");
        } else {
            fibonacciIterative(n);
            printFibonacciRecursive(n);
        }

        sc.close();
    }
}
