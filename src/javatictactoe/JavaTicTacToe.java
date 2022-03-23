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
        
        // Select starting player
        // Call whoGoesFirst
        player = whoGoesFirst();
        System.out.printf ("The starting player will use %c's\n", player);
    }   // End of PSV Main
    
    // Start whoGoesFirst
    public static char whoGoesFirst() {
        int selectFirstPlayer = (int)(Math.random()*2);
        if (selectFirstPlayer % 2 == 1)
            return 'X';
        else
            return 'O';
    }   // End of whoGoesFirst
}   // End of JavaTicTacToe
