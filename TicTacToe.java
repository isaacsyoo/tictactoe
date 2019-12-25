/* 
3X3 TIC-TAC-TOE
Designed by Isaac Yoo, 12/2019. [https://www.linkedin.com/in/isaacsyoo/]
This game may be played with 1 player (user vs CP), OR 2 players (user vs user).
Please refer to README for further details.
*/

import java.util.Scanner;
import java.util.InputMismatchException;

public class TicTacToe {

	public static int x; //x-coordinate
	public static int y; //y-coordinate
	public static char identity; //X or O
	public static char[][] board = new char[3][3];

	public static void main(String[] args) { 
		System.out.println();
		System.out.println("Welcome to Tic-Tac-Toe!");
		System.out.println("Here are the simple rules to play:");
		System.out.println("(1) The upper left coordinate is (1, 1). The coordinates run along the x-y axis.");
		System.out.println("(2) Enter your coordinates with no space. [EX: (2,3) would be: 23]");
		System.out.println("(3) Only enter values from 1 to 3, as this is the range of the 3x3 tic-tac-toe board.");
		System.out.println("(4) To terminate the game at any time, enter \"exit\".");
		System.out.println("Have fun!");
		System.out.println();
		ticTacToe();
	}

	public static void ticTacToe() { //game code
		Scanner scan = new Scanner(System.in);
		int input = 0;
		String value;
		boolean victory = false;
		identity = 'X';		

		try {
			System.out.println("Please enter the number of players! (1 or 2)");
			value = scan.next();
			
			if (value.toLowerCase().equals("exit")) { //option to terminate game
				System.out.println("Game terminating.");
				System.exit(0);
			} else {
				input = Integer.parseInt(value);
			}
			
			if (input == 1) { //1 player
				while (!victory) {
					if (tie()) { //if all coordinates are filled
						System.out.println("It's a tie!");
						return;
					}

					System.out.println("Please enter your desired coordinate, Player X.");

					value = scan.next();

					if (value.toLowerCase().equals("exit")) { //option to terminate game
						System.out.println("Game terminating.");
						System.exit(0);
					} else {
						input = Integer.parseInt(value);
					}

					x = (input/10) - 1; //store x-coordinate
					y = (input%10) - 1; //store y-coordinate

					if (board[y][x] == 'X' || board[y][x] == 'O') { //if position has already been played
						System.out.println("Coordinate has already been played! Game terminating.");
						return;
					}

					board[y][x] = 'X'; //update board with player's desired position
					printBoard(board);

					if (result(y, x)) { //if a player wins, exit while loop
						System.out.println("Congratulations, Player X! You have won!");
						return;
					}

					if (randomInput()) { //selects coordinate for CP & checks if there is a victory/tie
						victory = true;
					}
				}
		
			} else if (input == 2) { //2 players
				while (!victory) {
					if (tie()) { //if all coordinates are filled
						System.out.println("It's a tie!");
						return;
					}

					System.out.println("Please enter your desired coordinate, Player " + String.valueOf(identity) + ".");
					
					value = scan.next();

					if (value.toLowerCase().equals("exit")) { //option to terminate game
						System.out.println("Game terminating.");
						System.exit(0);
					} else {
						input = Integer.parseInt(value);
					}

					x = (input/10) - 1; //store x-coordinate
					y = (input%10) - 1; //store y-coordinate

					if (board[y][x] == 'X' || board[y][x] == 'O') { //if position has already been played
						System.out.println("Coordinate has already been played! Game terminating.");
						return;
					}

					board[y][x] = identity; //update board with player's desired position
					printBoard(board);

					if (result(y, x) == true) { //if a player wins, exit while loop
						victory = true;
						System.out.println("Congratulations, Player " + String.valueOf(identity) + "! You have won!");
					}

					if (identity == 'X') {
						identity = 'O'; //if X player just played, change to O player
					} else if (identity == 'O') {
						identity = 'X'; //if O player just played, change to X player
					}
				}
			}
			
			else { //if invalid number of players
				System.out.println("Number of players must be 1 or 2! Game terminating.");
				return;
			}
		
		} catch (InputMismatchException e) { //if input is not an integer
			System.out.println("Invalid entry! Game terminating.");
		} catch (ArrayIndexOutOfBoundsException e) { //if input is not a valid coordinate
			System.out.println("Invalid entry! Game terminating.");
		} catch (NumberFormatException e) { //if input is not an integer 
			System.out.println("Invalid entry! Game terminating.");
		}
	}

	public static void printBoard(char[][] board) { //prints board
		for (int i = 0; i < 3; i++) { //loop through rows
			System.out.print("| ");
			for (int j = 0; j < 3; j++) { //loop through columns
				if (board[i][j] == 'X' || board[i][j] == 'O') { //if coordinate is played
					System.out.print(board[i][j] + " | "); //place player's positions in coordinate
				} else { //if coordinate is not played yet
					System.out.print("_ | ");
				}
			}
			System.out.println(); //skip line to next row
		}
	}

	public static boolean result(int row, int column) { //determines if game is over or not
		if (board[0][column] == board[1][column] && board[1][column] == board[2][column]) { //up and down case
			return true;
		}

		if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) { //left and right case
			return true;
		}

		if (row == column) { //diagonal case: (0,0) (1,1) (2,2)
			if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
				return true;
			}
		}

		if ((column == 2 && row == 0) || (column == 1 && row == 1) || (column == 0 && row == 2)) { //diagonal case: (2,0), (1,1), (0,2)
			if (board[2][0] == board[1][1] && board[1][1] == board[0][2]) {
				return true;
			}
		}
		return false;
	}

	public static boolean tie() { //determines if all coordinates are filled
		for (int q = 0; q < 3; q++) {
			for (int w = 0; w < 3; w++) {
				if (board[q][w] == '\0') { //if there is a null coordinate, then board is not filled
					return false;
				}
			}
		}
		return true;
	}

	public static boolean randomInput() { //computer (CP) plays against 1 player. method selects coordinate for CP and checks if there is a victory
		boolean empty = true;
		while (empty) {
			int a = (int)(Math.random() * 3); //random number between 0 (inclusive) and 2 (inclusive)
			int b = (int)(Math.random() * 3); //random number between 0 (inclusive) and 2 (inclusive)

			if (board[b][a] != 'X' && board[b][a] != 'O') {
				board[b][a] = 'O';
				System.out.println("CP's turn! Coordinates: (" + (b+1) + ", " + (a+1) + ")");
				printBoard(board);
				empty = false;
				if (result(y, x)) {
					System.out.println("CP has won! Better luck next time.");
					return true;
				} else if (tie()) {
					System.out.println("It's a tie!");
					return true;
				}
			}
		}
		return false;
	}

	public static void testCases() { //test all possible cases to ensure that result() method is implemented correctly
		printBoard(board);

		//UP AND DOWN: TRUE
		board[0][0] = 'X'; board[1][0] = 'X'; board[2][0] = 'X'; x = 0; y = 2; printBoard(board); System.out.println(result(y, x)); board = new char[3][3];

		board[0][1] = 'X'; board[1][1] = 'X'; board[2][1] = 'X'; x = 1; y = 2; printBoard(board); System.out.println(result(y, x)); board = new char[3][3];

		board[0][2] = 'X'; board[1][2] = 'X'; board[2][2] = 'X'; x = 2; y = 2; printBoard(board); System.out.println(result(y, x)); board = new char[3][3];

		//LEFT AND RIGHT: TRUE
		board[0][0] = 'X'; board[0][1] = 'X'; board[0][2] = 'X'; x = 2; y = 0; printBoard(board); System.out.println(result(y, x)); board = new char[3][3];

		board[1][0] = 'X'; board[1][1] = 'X'; board[1][2] = 'X'; x = 2; y = 1; printBoard(board); System.out.println(result(y, x)); board = new char[3][3];

		board[2][0] = 'X'; board[2][1] = 'X'; board[2][2] = 'X'; x = 2; y = 2; printBoard(board); System.out.println(result(y, x)); board = new char[3][3];

		//DIAGONALS: TRUE
		board[0][0] = 'X'; board[1][1] = 'X'; board[2][2] = 'X'; x = 2; y = 2; printBoard(board); System.out.println(result(y, x)); board = new char[3][3];

		board[0][2] = 'X'; board[1][1] = 'X'; board[2][0] = 'X'; x = 0; y = 2; printBoard(board); System.out.println(result(y, x)); board = new char[3][3];

		//SOME "FALSE" TEST CASES
		board[0][0] = 'X'; board[1][1] = 'X'; board[2][1] = 'X'; x = 1; y = 2; printBoard(board); System.out.println(result(y, x)); board = new char[3][3];

		board[1][0] = 'X'; board[1][1] = 'X'; board[2][0] = 'X'; x = 0; y = 2; printBoard(board); System.out.println(result(y, x)); board = new char[3][3];
	}
}