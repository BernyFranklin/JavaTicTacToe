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
// Import math functions
import java.lang.Math;

public class JavaTicTacToe {
    // Constants to eliminate magic numbers
    static final int ROW_TOTAL = 3;   // Max rows is 3 in the array
    static final int COL_TOTAL = 3;   // Max columns is 3 in the array
    
    // Global Variables
    static int squareCounter = 0;   // Keep track # of squares used
    static char player;             // Current player set at each turn
    
    // Declare array for board with 3 rows and 3 cols
    static char[ ][ ] board = {   // Index for positions in array
        {'1', '2', '3'},          // [0][0] [0][1] [0][2]
        {'4', '5', '6'},          // [1][0] [1][1] [1][2]
        {'7', '8', '9'}           // [2][0] [2][1] [2][2]
    };
    
    // Start main
    public static void main(String[] args) {
        // Local variables for main
        char gameWinner = '-';   // Winner of hame X or O
        char inputCharacter;     // Input from user (should be 1-9)
        int choice;              // Input converted to index 0-8
        
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
            System.out.printf ("Player %c, enter a number (1-9): ", player);
            //Read input from keyboard
            inputCharacter = stdin.next().charAt(0);

            // Array index is numbered 0-8
            // Convert inputCharacter from ascii (1-9) to int (0-8)
            // To select the row and column index for the 3 by 3 array
            choice = inputCharacter - '1';   // Input is now 0-8
            
            // Row will be [0] for (0 1 2) [1] for (3 4 5) [2] for (6 7 8)
            int row = choice / ROW_TOTAL;
            
            // Col will be [0] for (0 3 6) [1] (1 4 7) [2] for (2 5 8)
            int col = choice % COL_TOTAL;

            // Checking for valid entries and updating array
            // Player is switched if valid input detected
            // X to O or O to X
            player = runInput(choice, row, col, player);

            // Check if there is a winner
            // Returns X or O if game is won
            // Returns Tie Game if no winner
            gameWinner = checkForWinningGame(board);
        }   // End of loop

        // The game is over. Either game was been one or we ran out of squares
        // Print divider
        System.out.printf ("=================================\n");
        // Display final board
        displayTicTacToe(board);
        // Blank line
        System.out.println();

        // Did anyone win?
        if (gameWinner == 'X'|| gameWinner == 'O')
            System.out.printf ("Player %c wins the game!!\n\n", gameWinner);
        // Nobody won :[
        else
            System.out.printf ("There seems to be a tie!\n\n");

        // Close the scanner
        stdin.close();
        
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
        System.out.println("             |     |             ");
        
        // Outer loop for rows
        for (int row = 0; row < 3; row++) {
            // Inner loop for cols
            // Print 8 spaces for looks
            System.out.printf("        ");
            
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
                System.out.println ("        -----|-----|-----");
            // Last row does not
            else
                System.out.println ("             |     |     ");
        }   // End of row outer loop
        
        // Blank line for formatting
        System.out.println();
        
    }   // End of displayTicTacToe
    
    // Start of runInput
    public static char runInput(int choice, int row, int col, char player) {
        // Check if input is between 0-8
        if (choice < 0 || choice > 8)
            System.out.printf ("Illegal value, please try again\n\n");
        
        // Check if square is already ocupied
        else if (board[row][col] == 'X' || board[row][col] == 'O')
            // Print the space is occupied
            System.out.printf ("This space has already been used, "
                    + "please try again\n\n");
        
        // Enter square input as player selection
        else {
            // Enter X or O depending on who's turn it is
            board[row][col] = player;
            // Update counter
            squareCounter++;
            
            // Select next player
            if (player == 'X')
                // switch to O if X
                return 'O';
            else
                // switch to X if O
                return 'X';
        }   // End of Else
        
        // If space already in use or bad input
        // Original player returned to try again
        return player;
        
    }   // End of runInput
    
    // Start of checkForWinningGame
    public static char checkForWinningGame(char[][] board) {
        // Check for all 8 possibilities for a winner
        //     [0][1][2]  [0][1][2]  [0][1][2]  [0][1][2]
        // [0]  X  X  X    *  *  *    *  *  *    X  *  * 
        // [1]  *  *  *    X  X  X    *  *  *    X  *  * 
        // [2]  *  *  *    *  *  *    X  X  X    X  *  *  
        //    ============================================
        // [0]  *  X  *    *  *  X    X  *  *    *  *  X
        // [1]  *  X  *    *  *  X    *  X  *    *  X  *
        // [2]  *  X  *    *  *  X    *  *  X    X  *  *
        
        // Who owns the square
        char player;
        
        // Check for winners
        // Top Row, Left Col, or Left Diagonal
        // Starting at [0][0]
        player = board[0][0];
        if ((board[0][1] == player && board[0][2] == player) ||     //1 Top Row
                (board[1][0] == player && board[2][0] == player) || //2 L.Col
                (board[1][1] == player && board[2][2] == player))   //3 L.Diag
            return player;
        
        // Middle Col
        // Starting at [0][1]
        player = board[0][1];
        if (board[1][1] == player && board[2][1] == player)         //4 M. Col
            return player;
        
        // Right Col and Left Diagonal
        // Starting at [0][2]
        player = board[0][2];
        if ((board[1][2] == player && board[2][2] == player) ||     //5 R. Col
                (board[1][1] == player && board[2][0] == player))   //6 R.Diag
            return player;
        
        // Middle row starting at [1][0]
        player = board[1][0];
        if (board[1][1] == player && board[1][2] == player)         //7 M. Row
            return player;
        
        // Bottom row starting at [2][0]
        player = board[2][0];
        if (board[2][1] == player && board[2][2] == player)         //8 B. Row
            return player;
        
        // No Winner
        return '-';
    }   // End of checkForWinningGame
}   // End of JavaTicTacToe
