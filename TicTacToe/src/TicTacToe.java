/*
 * TicTacToe - players choose their coordinates to place their piece on the board, first to get 3 in a row wins
 * Author: Emily MacPherson
 * Date:10/1/20
 */

import java.util.Scanner;

public class TicTacToe 
{
	int[][] board = new int[3][3];
	final int BLANK = 0;
	final int X_MOVE = 1;
	final int O_MOVE = 2;
	final int X_TURN = 0;
	final int O_TURN = 1;
	int turn = X_TURN;
	int xWins = 0;
	int oWins = 0;
	int ties = 0;
	
	Scanner scanner;
	String input = "";
	
	public static void main(String[] args) 
	{
		new TicTacToe();
	}
	
	//does basic game functions
	public TicTacToe()
	{
		scanner = new Scanner(System.in);
		boolean stillPlaying = true;
		while(stillPlaying == true)
		{
			while(checkWin(X_MOVE) == false && checkWin(O_MOVE) == false && checkTie() == false)
			{
				printBoard();
				//checks if player coordinates are valid
				if(turn == X_TURN)
				{
					System.out.println("Player X, enter your coordinates.");
				}
				else
				{
					System.out.println("Player O, enter your coordinates.");
				}
				input = scanner.nextLine();
				if(input.length() != 2)
				{
					System.out.println("Enter a letter followed by a number");
				}
				else if(input.charAt(0) != 'a' &&
						input.charAt(0) != 'b' && 
						input.charAt(0) != 'c')
				{
					System.out.println("Row must be an a, b, or c.");
				}
				else if(input.charAt(1) != '1' &&
						input.charAt(1) != '2' && 
						input.charAt(1) != '3')
				{
					System.out.println("Column must be an 1, 2, or 3.");
				}
				else
				{
					//places X or O on board
					int row = input.charAt(0) - 'a';
					int column = input.charAt(1) - '1';
					if(board[row][column] == BLANK)
					{
						if(turn == X_TURN)
						{
							board[row][column] = X_MOVE;
							turn = O_TURN;
						}
						else
						{
							board[row][column] = O_MOVE;
							turn = X_TURN;
						}
					}
					else
					{
						System.out.println("There's already a piece there, fool");
					}
				}
			}
			//checks for win and restarts if the player wants
			if(checkWin(X_MOVE) == true)
			{
				System.out.println("X wins!");
				xWins += 1;
				printBoard();
			}
			else if(checkWin(O_MOVE) == true)
			{
				System.out.println("O wins!");
				oWins += 1;
				printBoard();
			}
			System.out.println("X has won " + xWins + " times, O has won " + oWins + " times, and there have been " +
			ties + " ties.");
			System.out.println("Would you like to play again?(yes/no)");
			input = scanner.nextLine();
			if(input.equals("no"))
			{
				System.out.println("Fine, be like that. Have a good day.");
				stillPlaying = false;
			}
			else
			{
				for (int row = 0; row < board.length; row++) 
				{
					for (int column = 0; column < board[0].length; column++) 
					{
						board[row][column] = BLANK;
						turn = X_TURN;
					}
				}
			}
		}
	}
	
	//prints the game board
	public void printBoard()
	{
		System.out.println(" \t1\t2\t3");
		for (int row = 0; row < board.length; row++) 
		{
			String output = (char)('a' + row) + "\t";
			for (int column = 0; column < board[0].length; column++) 
			{
				if(board[row][column] == BLANK)
				{
					output += " \t";
				}
				else if(board[row][column] == X_MOVE)
				{
					output += "X\t";
				}
				else if(board[row][column] == O_MOVE)
				{
					output += "O\t";
				}
			}
			System.out.println(output);
		}
	}
	
	//checks if a play has gotten 3 in a row of their piece
	public boolean checkWin(int player)
	{
		if(board[0][0] == player && board[0][1] == player && board[0][2] == player)
		{
			return true;
		}
		if(board[1][0] == player && board[1][1] == player && board[1][2] == player)
		{
			return true;
		}
		if(board[2][0] == player && board[2][1] == player && board[2][2] == player)
		{
			return true;
		}
		if(board[0][0] == player && board[1][0] == player && board[2][0] == player)
		{
			return true;
		}
		if(board[0][1] == player && board[1][1] == player && board[2][1] == player)
		{
			return true;
		}
		if(board[0][2] == player && board[1][2] == player && board[2][2] == player)
		{
			return true;
		}
		if(board[0][0] == player && board[1][1] == player && board[2][2] == player)
		{
			return true;
		}
		if(board[0][2] == player && board[1][1] == player && board[2][0] == player)
		{
			return true;
		}
		return false;
	}
	
	//checks if any of the board spaces are empty. If none are empty and there hasn't been a win, it's a tie.
	public boolean checkTie()
	{
		for (int row = 0; row < board.length; row++) 
		{
			for(int column = 0; column < board[0].length; column++)
			{
				if(board[row][column] == BLANK)
				{
					return false;
				}
			}
		}
		ties += 1;
		printBoard();
		System.out.println("You have tied.");
		return true;
	}
}
