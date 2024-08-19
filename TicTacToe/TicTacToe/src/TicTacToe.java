import java.util.Scanner;

public class TicTacToe {
    private static final char EMPTY = ' '; // Represents an empty cell
    private static final char PLAYER_X = 'X'; // Player X symbol
    private static final char PLAYER_O = 'O'; // Player O symbol
    private static char[][] board; // 2D array to represent the game board
    private static char currentPlayer; // Variable to track the current player

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner for user input
        board = new char[3][3]; // Initialize the game board
        initializeBoard(); // Set all cells to empty
        currentPlayer = PLAYER_X; // Set the first player to X

        while (true) {
            printBoard(); // Print the current state of the board
            System.out.println("Player " + currentPlayer + ", enter your position (1-9): ");
            int position = scanner.nextInt(); // Read the player's move

            // Validate the input position
            if (position < 1 || position > 9) {
                System.out.println("Invalid position! Choose between 1 and 9.");
                continue;
            }

            // Attempt to make the move
            if (!makeMove(position)) {
                System.out.println("The position is already occupied! Try again.");
                continue;
            }

            // Check if the current player has won
            if (checkWinner()) {
                printBoard(); // Print the final board
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }

            // Check for a draw
            if (isBoardFull()) {
                printBoard(); // Print the final board
                System.out.println("It's a draw!");
                break;
            }

            // Switch players
            currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
        }

        scanner.close(); // Close the scanner
    }

    // Method to initialize the board
    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY; // Set all cells to empty
            }
        }
    }

    // Method to print the current state of the board
    private static void printBoard() {
        System.out.println(" Tic Tac Toe ");
        System.out.println("  1 | 2 | 3 ");
        System.out.println(" ----------- ");
        for (int i = 0; i < 3; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + board[i][j] + " ");
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) {
                System.out.println(" ----------- ");
            }
        }
    }

    // Method to make a move
    private static boolean makeMove(int position) {
        int row = (position - 1) / 3; // Calculate the row index
        int col = (position - 1) % 3; // Calculate the column index

        // Check if the cell is empty
        if (board[row][col] == EMPTY) {
            board[row][col] = currentPlayer; // Place the current player's symbol
            return true; // Move was successful
        }
        return false; // Move failed (cell already occupied)
    }

    // Method to check if the current player has won
    private static boolean checkWinner() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) ||
                    (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true; // Win found
            }
        }
        // Check diagonals
        if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)) {
            return true; // Win found
        }
        return false; // No win found
    }

    // Method to check if the board is full (draw condition)
    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    return false; // Found an empty cell
                }
            }
        }
        return true; // No empty cells found
    }
}