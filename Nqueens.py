def print_solution(board):
    for row in board:
        for cell in row:
            if cell == 1:
                print("Q", end=" ")
            else:
                print(".", end=" ")
        print()
    print()

def is_safe(board, row, col, n):
    # Check this row on left
    for i in range(col):
        if board[row][i] == 1:
            return False

    # Check upper diagonal on left
    i, j = row, col
    while i >= 0 and j >= 0:
        if board[i][j] == 1:
            return False
        i -= 1
        j -= 1

    # Check lower diagonal on left
    i, j = row, col
    while i < n and j >= 0:
        if board[i][j] == 1:
            return False
        i += 1
        j -= 1

    return True

def solve_n_queens(board, col, n):
    if col >= n:
        print("Solution:")
        print_solution(board)
        return True

    for i in range(n):
        if is_safe(board, i, col, n):
            board[i][col] = 1
            if solve_n_queens(board, col + 1, n):
                return True
            board[i][col] = 0  # backtrack
    return False

# Simple menu
def main():
    print("N-Queens Problem Solver")
    n = int(input("Enter value of N: "))
    # Initialize the board with zeros
    board = [[0] * n for i in range(n)]

    # Start the recursive solving process
    if not solve_n_queens(board, 0, n):
        print("No solution exists.")


if __name__ == '__main__':
    main()

