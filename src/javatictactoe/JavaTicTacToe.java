/*
 * JavaTicTacToe Version1.0
 * Frank Bernal
 * CIS 084 Java Programming
 * Input: User square selection
 * Output: Game display
 * 22 Mar 2022
 */

package javatictactoe;
// Import the scanner
import java.util.Scanner;
import java.lang.Math;

public class JavaTicTacToe {
    // Constants to eliminate magic numbers
    static final int ROW_TOTAL = 3;   // Max rows is 3 in the array
    static final int COL_TOTAL = 3;   // Max columns is 3 in the array
    // Declare array for board with 3 rows and 3 cols
    static char[ ][ ] board = {   // Index for positions in array
        {'1', '2', '3'},          // [0][0] [0][1] [0][2]
        {'4', '5', '6'},          // [1][0] [1][1] [1][2]
        {'7', '8', '9'}           // [2][0] [2][1] [2][2]
    };
    public static void main(String[] args) {
        // Local variables for main
        int squareCounter = 0;   // Keep track # of squares used
        char gameWinner = '-';   // Winner of hame X or O
        char inputCharacter;     // Input from user (should be 1-9)
        int choice;              // Input converted to index 0-8
        char player;             // Current player set at each turn
        
        // Create scanner object
        Scanner stdin = new Scanner(System.in);
        
        // Welcome message
        System.out.println ("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        System.out.println ("             Weclome             ");
        System.out.println ("               to                ");
        System.out.println ("           Tic Tac Toe           ");
        System.out.printf  ("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=\n\n");
        
        // Select starting player
        // Call whoGoesFirst
        player = whoGoesFirst();
        // Print who goes first
        System.out.printf ("The starting player will use %c's\n\n", player);
        
        // Print instructions
        System.out.println ("Enter a 1 - 9 to select a square");
        
        // Start loop for gameplay
        while (squareCounter < 9 && gameWinner != 'X' && gameWinner != 'O') {
            // Loop goes until squares run out or a winner is determined
            // Divider
            System.out.println ("=================================");
            // Call board
            displayTicTacToe(board);
            
            // Prompt for user input
            System.out.printf ("Player %c, enter a numer (1-9)", player);
            //Read input from keyboard
            inputCharacter = stdin.next().charAt(0);
            
            // Array index is numbered 0-8
            // Convert inputCharacter from ascii (1-9) to int (0-8)
            // To select the row and column index for the 3 by 3 array
            choice = inputCharacter - '1';   // Input is now 0-8
            // Row will be [0] for (0 1 2) [1] for (3 4 5) [2] for (6 7 8)
            int row = choice / ROW_TOTAL;
            // Col will be [0] for (0 3 6) [1] (1 4 7) [2] for (2 5 8)
            int col = choice / COL_TOTAL;
        }   // End of loop
    }   // End of PSV Main
    
    // Start whoGoesFirst
    public static char whoGoesFirst() {
        // Math.random() gives a double between 0.0 and less than 1.0
        int selectFirstPlayer = (int)(Math.random()*2);
        // If random returns a 1 X goes first, if 0 O goes first
        if (selectFirstPlayer % 2 == 1)
            return 'X';
        else
            return 'O';
    }   // End of whoGoesFirst
    
    // Start displayTicTacToe
    public static void displayTicTacToe(char[][] board) {
        // Blank line
        System.out.println();
        // Print vertical lines above 
        System.out.println("     |     |     ");
        
        // Outer loop for rows
        for (int row = 0; row < 3; row++) {
            // Inner loop for cols
            for (int col = 0; col <3; col++) {
                // First 2 cols get a vertical line
                if (col < 2)
                System.out.printf ("  %c  |", board[row][col]);
                // Last col does not
                else
                    System.out.printf ("  %c  \n", board[row][col]);
            }   // End of col inner loop
            // First two rows get a bottom line
            if (row <2)
                System.out.println ("-----|-----|-----");
            // Last one does not
            else
                System.out.println ("     |     |     ");
        }   // End of row outer loop
        // Blank line for formatting
        System.out.println();
    }   // End of displayTicTacToe
}   // End of JavaTicTacToe
