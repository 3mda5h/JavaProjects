/*
 * GUITicTacToe - Tic-Tac-Toe but with GUI (obviously)
 * Author: Emily MacPherson
 * Date: 10/7/20
 * I used https://stackoverflow.com/questions/20462167/increasing-font-size-in-a-jbutton &
 * https://stackoverflow.com/questions/15393385/how-to-change-text-color-of-a-jbutton to figure out how
 * to change font size and color of JButton text
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUITicTacToe implements ActionListener
{	
	JFrame frame = new JFrame();
	JButton[][] button = new JButton[3][3];
	int[][] board = new int[3][3];
	final int BLANK = 0;
	final int X_MOVE = 1;
	final int O_MOVE = 2;
	final int X_TURN = 0;
	final int O_TURN = 1;
	int turn = X_TURN;
	Container center = new Container();
	JLabel xLabel = new JLabel("X wins: 0");
	JLabel oLabel = new JLabel("O wins: 0");
	JButton xChangeName = new JButton("Change X's Name.");
	JButton oChangeName = new JButton("Change O's Name.");
	JTextField xChangeField = new JTextField();
	JTextField oChangeField = new JTextField();
	Container north = new Container();
	String xPlayerName = "X";
	String oPlayerName = "O";
	int xWins = 0;
	int oWins = 0;
	
	public static void main(String[] args) 
	{
		new GUITicTacToe();
	}
	
	GUITicTacToe()
	{
		frame.setSize(400, 400);
		turn = X_TURN;
		//Center grid container
		frame.setLayout(new BorderLayout());
		center.setLayout(new GridLayout(3,3));
		for (int row = 0; row < button.length; row++) 
		{
			for (int column = 0; column < button[0].length; column++) 
			{
				button[column][row] = new JButton("");
				button[column][row].setFont(new Font("Arial", Font.PLAIN, 40));
				center.add(button[column][row]);
				button[column][row].addActionListener(this);
			}
		}
		frame.add(center, BorderLayout.CENTER);
		
		//North grid container
		north.setLayout(new GridLayout(3,2));
		north.add(xLabel);
		north.add(oLabel);
		north.add(xChangeName);
		xChangeName.addActionListener(this);
		north.add(oChangeName);
		oChangeName.addActionListener(this);
		north.add(xChangeField);
		north.add(oChangeField);
		frame.add(north, BorderLayout.NORTH);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		//highlights the name of the player whose turn it is
		while(true)
		{
			if(turn == X_TURN)
			{
				xLabel.setForeground(Color.BLUE);
				oLabel.setForeground(Color.BLACK);
			}
			else
			{
				oLabel.setForeground(Color.BLUE);
				xLabel.setForeground(Color.BLACK);
			}
		}
	}
	
	//called when there's an action (mouse click)
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		JButton current;
		boolean gridButton = false;
		for (int row = 0; row < button.length; row++) 
		{
			for (int column = 0; column < button[0].length; column++) 
			{
				if(event.getSource().equals(button[column][row]))
				{
					gridButton = true;
					current = button[column][row];
					if(board[column][row] == BLANK)
					{
						if(turn == X_TURN)
						{
							current.setText("X");
							current.setEnabled(false);
							board[column][row] = X_MOVE;
							turn = O_TURN;
						}
						else
						{
							current.setText("O");
							current.setEnabled(false);
							board[column][row] = O_MOVE;
							turn = X_TURN;
						}
						//check for wins and ties
						if(checkWin(X_MOVE) == true)
						{
							//x wins
							xWins ++;
							xLabel.setText(xPlayerName + " wins: " + xWins);
							clearBoard();
						}
						else if(checkWin(O_MOVE) == true)
						{
							//o wins
							oWins ++;
							oLabel.setText(oPlayerName + " wins: " + oWins);
							clearBoard();
						}
						else if(checkTie() == true)
						{
							//tie
							clearBoard();
						}
					}	
				}
			}
		}
		//if player clicked change name button
		if(gridButton == false)
		{
			if(event.getSource().equals(xChangeName) == true)
			{
				if(!xChangeField.getText().equals(""))
				{
					xPlayerName = xChangeField.getText();
					xLabel.setText(xPlayerName + " wins: " + xWins);
				}
			}
			else if(event.getSource().equals(oChangeName) == true)
			{
				if(!oChangeField.getText().equals(""))
				{
					oPlayerName = oChangeField.getText();
					oLabel.setText(oPlayerName + " wins: " + oWins);
				}
			}
		}
	}
	
	//checks all the win combinations 
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
	
	//checks if any spaces are empty, if not and there are no wins, there is a tie
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
		return true;
	}
	
	//clears all Xs and Os and re-enables all buttons
	public void clearBoard()
	{
		for (int row = 0; row < board.length; row++) 
		{
			for (int column = 0; column < board.length; column++) 
			{
				board[row][column] = BLANK;
				button[row][column].setText("");
				button[row][column].setEnabled(true);
			}
		}
		turn = X_TURN;
	}
}
