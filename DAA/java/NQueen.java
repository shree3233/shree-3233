import java.util.*;

public class NQueen {

    // Function to print the board
    static void printBoard(int[][] board, int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }
    }

    // Function to check if placing a queen at (row, col) is safe
    static boolean isSafe(int[][] board, int row, int col, int N) {
        // Check same column
        for (int i = 0; i < row; i++)
            if (board[i][col] == 1)
                return false;

        // Check upper-left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        // Check upper-right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < N; i--, j++)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    // Backtracking function to place queens
    static boolean solveNQueens(int[][] board, int row, int N) {
        if (row >= N)
            return true; // All queens placed

        // Skip row if queen already placed
        boolean alreadyPlaced = false;
        for (int j = 0; j < N; j++) {
            if (board[row][j] == 1) {
                alreadyPlaced = true;
                break;
            }
        }
        if (alreadyPlaced)
            return solveNQueens(board, row + 1, N);

        for (int col = 0; col < N; col++) {
            if (board[row][col] == 0 && isSafe(board, row, col, N)) {
                board[row][col] = 1; // Place queen
                if (solveNQueens(board, row + 1, N))
                    return true;
                board[row][col] = 0; // Backtrack
            }
        }
        return false; // No valid position
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size of board (N): ");
        int N = sc.nextInt();

        int[][] board = new int[N][N];

        System.out.print("Enter position of first queen (row and column, 0-based index): ");
        int firstRow = sc.nextInt();
        int firstCol = sc.nextInt();

        if (firstRow >= N || firstCol >= N || firstRow < 0 || firstCol < 0) {
            System.out.println("Invalid position!");
            sc.close();
            return;
        }

        board[firstRow][firstCol] = 1; // Place first queen

        // Solve remaining queens
        if (solveNQueens(board, 0, N)) {
            System.out.println("\nN-Queens solution:");
            printBoard(board, N);
        } else {
            System.out.println("No solution exists with the first queen at the given position.");
        }

        sc.close();
    }
}
