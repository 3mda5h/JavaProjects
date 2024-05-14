/*
 * Minesweeper - a logical puzzle where the goal is to find the hidden mines given the number of mines in the 8 squares around a revealed square
 * Author: Emily MacPherson
 * Date: 12/4/20
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Minesweeper implements ActionListener, MouseListener
{
	JFrame frame = new JFrame("Minesweeper");
	JButton reset = new JButton("Reset");
	JButton[][] buttons = new JButton[20][20];
	int[][] counts = new int[20][20];
	Container grid = new Container();
	
	final int MINE = 10;
	
	public static void main(String[] args) 
	{
		new Minesweeper();
	}
	
	public Minesweeper()
	{
		frame.setSize(900, 900);
		frame.setLayout(new BorderLayout());
		frame.add(reset, BorderLayout.SOUTH);
		reset.addActionListener(this);
		
		//create grid of JButtons
		grid.setLayout((new GridLayout(20, 20)));
		for (int a = 0; a < buttons.length; a++) 
		{
			for (int b = 0; b < buttons[0].length; b++) 
			{
				buttons[a][b] = new JButton();
				buttons[a][b].addMouseListener(this);
				grid.add(buttons[a][b]);
			}
		}
		frame.add(grid, BorderLayout.CENTER);
		
		createMines();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void createMines()
	{
		//Initializes list of random pairs
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int x = 0; x < buttons.length; x++) 
		{
			for (int y = 0; y < buttons[0].length; y++) 
			{
				list.add(x * 100 + y);
			}
		}
		//reset counts, pick out 30 mines
		counts = new int[20][20];
		for (int a = 0; a < 50; a++) 
		{
			int choice = (int)(Math.random() * list.size());
			counts[list.get(choice) / 100][list.get(choice) % 100] = MINE;
			list.remove(choice);
		}
		//Initialize neighbor counts
		for (int x = 0; x < counts.length; x++) 
		{
			for (int y = 0; y < counts[0].length; y++)
			{
				if(counts[x][y] != MINE)
				{
					int neighborCount = 0;
					if(x > 0 && y > 0 && counts[x - 1][y - 1] == MINE)//up left
					{
						neighborCount++;
					}
					if(y > 0 && counts[x][y - 1] == MINE)//up middle
					{
						neighborCount++;
					}
					if(x < counts.length - 1 && y > 0 && counts[x + 1][y - 1] == MINE)//up right
					{
						neighborCount++;
					}
					if(x < counts.length - 1 && counts[x + 1][y] == MINE)//right middle
					{
						neighborCount++;
					}
					if(x < counts.length - 1 && y < counts[0].length - 1 && counts[x + 1][y + 1] == MINE)//bottom right
					{
						neighborCount++;
					}
					if(y < counts[0].length - 1 && counts[x][y + 1] == MINE)//bottom middle
					{
						neighborCount++;
					}
					if(x > 0 && y < counts[0].length - 1 && counts[x - 1][y + 1] == MINE)//bottom left
					{
						neighborCount++;
					}
					if(x > 0 && counts[x - 1][y] == MINE)//left middle 
					{
						neighborCount++;
					}
					counts[x][y] = neighborCount;
				}
			}
		}
	}
	
	//when a zero square is clicked, shows all neighbors and the neighbors of connected zeros
	public void clearZeros(ArrayList<Integer> toClear)
	{
		//if there are no more zeros to clear
		if(toClear.size() == 0)
		{
			return;
		}
		//if there are more zeros, check their neighbors for zeros
		else
		{
			int x = toClear.get(0) / 100;
			int y = toClear.get(0) % 100;
			toClear.remove(0);
			if(x > 0 && y > 0 && buttons[x - 1][y - 1].isEnabled())//up left
			{
				//if it hasn't been flagged
				if(!buttons[x - 1][y - 1].getBackground().equals(new Color(255, 153, 153)))
				{
					buttons[x - 1][y - 1].setEnabled(false);
					if(counts[x - 1][y - 1] == 0)
					{
						toClear.add((x - 1) * 100 + (y - 1));
					}
					//only show number if it's not a zero, cause it looks better that way :D
					else
					{
						buttons[x - 1][y - 1].setText(counts[x - 1][y - 1] + "");
					}
				}
			}
			if(y > 0 && buttons[x][y - 1].isEnabled())//up middle
			{
				if(!buttons[x][y - 1].getBackground().equals(new Color(255, 153, 153)))
				{
					buttons[x][y - 1].setEnabled(false);
					if(counts[x][y - 1] == 0)
					{
						toClear.add(x * 100 + (y - 1));
					}
					else
					{
						buttons[x][y - 1].setText(counts[x][y - 1] + "");
					}
				}
			}
			if(x < counts.length - 1 && y > 0 && buttons[x + 1][y - 1].isEnabled())//up right
			{
				if(!buttons[x + 1][y - 1].getBackground().equals(new Color(255, 153, 153)))
				{
					buttons[x + 1][y - 1].setEnabled(false);
					if(counts[x + 1][y - 1] == 0)
					{
						toClear.add((x + 1) * 100 + (y - 1));
					}
					else
					{
						buttons[x + 1][y - 1].setText(counts[x + 1][y - 1] + "");
					}
				}
			}
			if(x < counts.length - 1 && buttons[x + 1][y].isEnabled())//right middle
			{
				if(!buttons[x + 1][y].getBackground().equals(new Color(255, 153, 153)) && counts[x + 1][y] != MINE)
				{
					buttons[x + 1][y].setEnabled(false);
					if(counts[x + 1][y] == 0)
					{
						toClear.add((x + 1) * 100 + y);
					}
					else
					{
						buttons[x + 1][y].setText(counts[x + 1][y] + "");
					}
				}
			}
			if(x < counts.length - 1 && y < counts[0].length - 1 && buttons[x + 1][y + 1].isEnabled())//bottom right
			{
				if(!buttons[x + 1][y + 1].getBackground().equals(new Color(255, 153, 153)))
				{
					buttons[x + 1][y + 1].setEnabled(false);
					if(counts[x + 1][y + 1] == 0)
					{
						toClear.add((x + 1) * 100 + (y + 1));
					}
					else
					{
						buttons[x + 1][y + 1].setText(counts[x + 1][y + 1] + "");
					}
				}
			}
			if(y < counts[0].length - 1 && buttons[x][y + 1].isEnabled())//bottom middle
			{
				if(!buttons[x][y + 1].getBackground().equals(new Color(255, 153, 153)))
				{
					buttons[x][y + 1].setEnabled(false);
					if(counts[x][y + 1] == 0)
					{
						toClear.add(x * 100 + (y + 1));
					}
					else
					{
						buttons[x][y + 1].setText(counts[x][y + 1] + "");
					}
				}
			}
			if(x > 0 && y < counts[0].length - 1 && buttons[x - 1][y + 1].isEnabled())//bottom left
			{
				if(!buttons[x - 1][y + 1].getBackground().equals(new Color(255, 153, 153)))
				{
					buttons[x - 1][y + 1].setEnabled(false);
					if(counts[x - 1][y + 1] == 0)
					{
						toClear.add((x - 1) * 100 + (y + 1));
					}
					else
					{
						buttons[x - 1][y + 1].setText(counts[x - 1][y + 1] + "");
					}
				}
			}
			if(x > 0 && buttons[x - 1][y].isEnabled())//left middle 
			{
				if(!buttons[x - 1][y].getBackground().equals(new Color(255, 153, 153)))
				{
					buttons[x - 1][y].setEnabled(false);
					if(counts[x - 1][y] == 0)
					{
						toClear.add((x - 1) * 100 + y);
					}
					else
					{
						buttons[x - 1][y].setText(counts[x - 1][y] + "");
					}
				}
			}
			clearZeros(toClear);
		}
	}
	
	//checks if all non-mine squares are disabled
	public void checkWin()
	{
		boolean won = true;
		for (int x = 0; x < buttons.length; x++) 
		{
			for (int y = 0; y < buttons[0].length; y++) 
			{
				if(buttons[x][y].isEnabled() == true && counts[x][y] != MINE)
				{
					won = false;
				}
			}
		}
		if(won == true)
		{
			JOptionPane.showMessageDialog(frame, "You win!");
		}
	}
	
	//called if play clicks mine, shows all mines and number squares
	public void gameLost()
	{
		for (int x = 0; x < buttons.length; x++) 
		{
			for (int y = 0; y < buttons[0].length; y++) 
			{
				if(buttons[x][y].isEnabled() == true)
				{
					if(counts[x][y] != MINE && !buttons[x][y].getBackground().equals(new Color(255, 153, 153)))
					{
						buttons[x][y].setText(counts[x][y] + "");
						buttons[x][y].setEnabled(false);
					}
					else
					{
						buttons[x][y].setIcon(new ImageIcon("F:\\Emily_Eclipse\\Minesweeper\\src\\bomb.png"));
						buttons[x][y].setDisabledIcon(new ImageIcon("F:\\Emily_Eclipse\\Minesweeper\\src\\bomb.png"));
						buttons[x][y].setEnabled(false);
					}
				}
			}
		}
		JOptionPane.showMessageDialog(frame, "You lose :(");
	}
	
	//called when the reset button is pressed
	@Override
	public void actionPerformed(ActionEvent event) 
	{		
		if(event.getSource().equals(reset))
		{
			for (int x = 0; x < buttons.length; x++) 
			{
				for (int y = 0; y < buttons[0].length; y++) 
				{
					//reset everything
					buttons[x][y].setBackground((UIManager.getColor("Button.background")));
					buttons[x][y].setEnabled(true);
					buttons[x][y].setText("");
					buttons[x][y].setIcon(new ImageIcon(""));
					buttons[x][y].setDisabledIcon(new ImageIcon(""));
				}
			}
			createMines();
		}
	}
	
	//called when a square on the grid is clicked
	@Override
	public void mouseReleased(MouseEvent event) 
	{
		for (int x = 0; x < buttons.length; x++) 
		{
			for (int y = 0; y < buttons[0].length; y++) 
			{
				//if it's a left click, reveal number of square, or lose game if it's a bomb
				if(SwingUtilities.isLeftMouseButton(event) == true)
				{
					if(event.getSource().equals(buttons[x][y]) && !buttons[x][y].getBackground().equals(new Color(255, 153, 153)))
					{
						if(counts[x][y] == MINE)
						{
							buttons[x][y].setIcon(new ImageIcon("F:\\Emily_Eclipse\\Minesweeper\\src\\bomb.png"));
							buttons[x][y].setDisabledIcon(new ImageIcon("F:\\Emily_Eclipse\\Minesweeper\\src\\bomb.png"));
							buttons[x][y].setEnabled(false);
							gameLost();
						}
						else if(counts[x][y] == 0)
						{
							buttons[x][y].setEnabled(false);
							ArrayList<Integer> toClear = new ArrayList<Integer>();
							toClear.add((x * 100) + y);
							clearZeros(toClear);
							checkWin();
						}
						else if(counts[x][y] != MINE)
						{
							buttons[x][y].setText(counts[x][y] + "");
							buttons[x][y].setEnabled(false);
							checkWin();
						}
					}
				}
				//if it's a right click, toggle flag
				if(SwingUtilities.isRightMouseButton(event) == true)
				{
					if(event.getSource().equals(buttons[x][y]))
					{
						if(buttons[x][y].isEnabled() == true && !buttons[x][y].getBackground().equals(new Color(255, 153, 153)))
						{
							buttons[x][y].setBackground(new Color(255, 153, 153));
							buttons[x][y].setIcon(new ImageIcon("F:\\Emily_Eclipse\\Minesweeper\\src\\flag.png"));
						}
						else if(buttons[x][y].getBackground().equals(new Color(255, 153, 153)))
						{
							buttons[x][y].setBackground((UIManager.getColor("Button.background")));
							buttons[x][y].setIcon(new ImageIcon(""));
						}
					}
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
