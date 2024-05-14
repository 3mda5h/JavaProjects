/*
Main class that sets up GUI, keeps track of setup, and manages gameplay. See the user manual at
https://docs.google.com/document/d/1tIleK-yH5LF4GhXpyBDHvTz1Vn8fibNWJYIlslidINI/edit?usp=sharing
to learn how to use this program to play Arimaa.
Please note that when arrays refer to [x][y], they are actually reversed - the first value, x, is the row,
and the second value, y, is the column.
*/

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import java.util.Arrays;  

public class ArimaaTest implements MouseListener, ActionListener
{
  JFrame frame = new JFrame("Arimaa");
  Container center = new Container();
  Container north = new Container();
  Container east = new Container();
  Container south = new Container();
  Container west = new Container();
  Container moves = new Container();
  JButton[][] board = new JButton[8][8];
  JButton goldSetupRabbit = new JButton();
  JLabel goldSetupRabbitLBL = new JLabel("8");
  JButton goldSetupElephant = new JButton();
  JLabel goldSetupElephantLBL = new JLabel("1");
  JButton goldSetupCamel = new JButton();
  JLabel goldSetupCamelLBL = new JLabel("1");
  JButton goldSetupHorse = new JButton();
  JLabel goldSetupHorseLBL = new JLabel("2");
  JButton goldSetupDog = new JButton();
  JLabel goldSetupDogLBL = new JLabel("2");
  JButton goldSetupCat = new JButton();
  JLabel goldSetupCatLBL = new JLabel("2");
  JButton silverSetupRabbit = new JButton();
	JLabel silverSetupRabbitLBL = new JLabel("8");
	JButton silverSetupElephant = new JButton();
	JLabel silverSetupElephantLBL = new JLabel("1");
	JButton silverSetupCamel = new JButton();
	JLabel silverSetupCamelLBL = new JLabel("1");
	JButton silverSetupHorse = new JButton();
	JLabel silverSetupHorseLBL = new JLabel("2");
	JButton silverSetupDog = new JButton();
	JLabel silverSetupDogLBL = new JLabel("2");
	JButton silverSetupCat = new JButton();
	JLabel silverSetupCatLBL = new JLabel("2");

	JLabel instructions = new JLabel("", SwingConstants.CENTER);
  JButton instructionsButton = new JButton("Hide instructions");
  JLabel turnLBL = new JLabel("Gold's turn", SwingConstants.CENTER);
	int movesRemaining = 4;
  JLabel movesRemainingTextLBL = new JLabel("<html>Moves<br>remaining: <html>", SwingConstants.CENTER);
  JLabel movesRemainingLBL = new JLabel("    4", SwingConstants.CENTER);
  JLabel JOptionText = new JLabel("", SwingConstants.CENTER);
  
  JButton endTurn = new JButton("End Turn");
  JButton push = new JButton("Push");
  JButton pull = new JButton("Pull");

  String gameState = "startingGame";
  String setupPieceSelected = "";
  Piece playingPieceSelected = null;
  String playerState = "goldSetup";
	
  int goldCurrentPieces = 16;
  int silverCurrentPieces = 16;
  int goldRabbits = 8;
  int silverRabbits = 8;
  
  int previousPositionX = 0;
  int previousPositionY = 0;
	int weakX = 0;
	int weakY = 0;

	ArrayList<Piece[][]> goldPreviousPlays = new ArrayList<Piece[][]>();
	ArrayList<Piece[][]> silverPreviousPlays = new ArrayList<Piece[][]>();
	ArrayList<Integer> goldTimesPreviouslyPlayed = new ArrayList<Integer>();
	ArrayList<Integer> silverTimesPreviouslyPlayed = new ArrayList<Integer>();
	int goldLastPositionIndex = 0;	
	int silverLastPositionIndex = 0;
	//for each player, at each row (index) in these lists, the Piece[][] is a previous board
	//position, and the integer is the number of times this position has happened.

  ArrayList<String> goldAnimals = new ArrayList<String>();
	ArrayList<Integer> goldPowerLevels = new ArrayList<Integer>();
  ArrayList<Integer> silverPowerLevels = new ArrayList<Integer>();
  ArrayList<JButton> goldSetupButtons = new ArrayList<JButton>();
  ArrayList<ImageIcon> goldImages = new ArrayList<ImageIcon>();
  ArrayList<JLabel> goldSetupLBLs = new ArrayList<JLabel>();
  ArrayList<String> silverAnimals = new ArrayList<String>();
  ArrayList<JButton> silverSetupButtons = new ArrayList<JButton>();
  ArrayList<ImageIcon> silverImages = new ArrayList<ImageIcon>();
  ArrayList<JLabel> silverSetupLBLs = new ArrayList<JLabel>();
	
  ArrayList<Piece> frozenPieces = new ArrayList<Piece>();
 
  Piece[][] allPieces = new Piece[8][8];

  boolean[][] goldStartEmpty = new boolean[2][8];
  boolean[][] silverStartEmpty = new boolean[2][8];
	//these are arrays of the spots in the first two and last two rows that are still empty
	//during setup

  public static void main(String[] args)
  {
  	new ArimaaTest();
  }
  public ArimaaTest()
  {
    goldAnimals.add("rabbit");
    goldAnimals.add("cat");
    goldAnimals.add("dog");
    goldAnimals.add("horse");
    goldAnimals.add("camel");
    goldAnimals.add("elephant");

		for (int a = 1; a <= 6; a++) {
			goldPowerLevels.add(a);
			silverPowerLevels.add(a);
		}  

    goldSetupButtons.add(goldSetupRabbit);
    goldSetupButtons.add(goldSetupCat);
    goldSetupButtons.add(goldSetupDog);
    goldSetupButtons.add(goldSetupHorse);
    goldSetupButtons.add(goldSetupCamel);
    goldSetupButtons.add(goldSetupElephant);

    goldImages.add(new ImageIcon("./grabbit.png"));
    goldImages.add(new ImageIcon("./gcat.png"));
    goldImages.add(new ImageIcon("./gdog.png"));
    goldImages.add(new ImageIcon("./ghorse.png"));
    goldImages.add(new ImageIcon("./gcamel.png"));
    goldImages.add(new ImageIcon("./gelephant.png"));

    goldSetupLBLs.add(goldSetupRabbitLBL);
    goldSetupLBLs.add(goldSetupCatLBL);
    goldSetupLBLs.add(goldSetupDogLBL);
    goldSetupLBLs.add(goldSetupHorseLBL);
    goldSetupLBLs.add(goldSetupCamelLBL);
    goldSetupLBLs.add(goldSetupElephantLBL);

    silverAnimals.add("rabbit");
    silverAnimals.add("cat");
    silverAnimals.add("dog");
    silverAnimals.add("horse");
    silverAnimals.add("camel");
    silverAnimals.add("elephant");

    silverSetupButtons.add(silverSetupRabbit);
    silverSetupButtons.add(silverSetupCat);
    silverSetupButtons.add(silverSetupDog);
    silverSetupButtons.add(silverSetupHorse);
    silverSetupButtons.add(silverSetupCamel);
    silverSetupButtons.add(silverSetupElephant);

    silverImages.add(new ImageIcon("./srabbit.png"));
    silverImages.add(new ImageIcon("./scat.png"));
    silverImages.add(new ImageIcon("./sdog.png"));
    silverImages.add(new ImageIcon("./shorse.png"));
    silverImages.add(new ImageIcon("./scamel.png"));
    silverImages.add(new ImageIcon("./selephant.png"));

    silverSetupLBLs.add(silverSetupRabbitLBL);
    silverSetupLBLs.add(silverSetupCatLBL);
    silverSetupLBLs.add(silverSetupDogLBL);
    silverSetupLBLs.add(silverSetupHorseLBL);
    silverSetupLBLs.add(silverSetupCamelLBL);
    silverSetupLBLs.add(silverSetupElephantLBL);

    for (int a = 0; a < 2; a++) {
      for (int b = 0; b < 8; b++) {
        goldStartEmpty[a][b] = true;
        silverStartEmpty[a][b] = true;
      }
    }

    frame.setSize(1000, 900);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout(20, 20));
    frame.add(center, BorderLayout.CENTER);
		center.setLayout((new GridLayout(8, 8)));
		for (int a = 0; a < 8; a++)
		{
			for (int b = 0; b < 8; b++)
			{
				board[a][b] = new JButton();
				board[a][b].addMouseListener(this);
        board[a][b].addActionListener(this);
				center.add(board[a][b]);
			}
		}
    north.setLayout(new FlowLayout());
    for (int a = 0; a < 6; a++) {
			goldSetupButtons.get(a).setBackground(null); //for returning to default button color; used
			//https://stackoverflow.com/questions/1358398/how-to-get-jbutton-default-background-color
      goldSetupButtons.get(a).setIcon(goldImages.get(a));
			goldSetupButtons.get(a).setDisabledIcon(goldImages.get(a));
      goldSetupButtons.get(a).setPreferredSize(new Dimension(125, 90));
      goldSetupLBLs.get(a).setPreferredSize(new Dimension(27, 20));
      goldSetupLBLs.get(a).setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
      goldSetupButtons.get(a).addMouseListener(this);
      goldSetupButtons.get(a).addActionListener(this);
      north.add(goldSetupButtons.get(a));
      north.add(goldSetupLBLs.get(a));
    }
    enableGoldSetupButtons();
    frame.add(north, BorderLayout.NORTH);

    south.setLayout(new FlowLayout());
    for (int a = 0; a < 6; a++) {
			silverSetupButtons.get(a).setBackground(null);
      silverSetupButtons.get(a).setIcon(silverImages.get(a));
			silverSetupButtons.get(a).setDisabledIcon(silverImages.get(a));
      silverSetupButtons.get(a).setPreferredSize(new Dimension(125, 90));
      silverSetupLBLs.get(a).setPreferredSize(new Dimension(27, 20));
      silverSetupLBLs.get(a).setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
      silverSetupButtons.get(a).addMouseListener(this);
      silverSetupButtons.get(a).addActionListener(this);
      south.add(silverSetupButtons.get(a));
      south.add(silverSetupLBLs.get(a));
    }
    disableSilverSetupButtons();
    frame.add(south, BorderLayout.SOUTH);
		
		east.setLayout(new FlowLayout());
    frame.add(east, BorderLayout.EAST);
    frame.add(west, BorderLayout.WEST);
    frame.setVisible(true);

    JOptionText.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15)); //for chaning font of JOptionPane

    //startSetup();
    quickSetUp(); //this is for setting up pieces during testing/debugging
  }

  //skips the manual set up process (for testing)
  public void quickSetUp()
  {
		for (int a = 0; a < goldSetupButtons.size(); a++) {
			north.remove(goldSetupButtons.get(a));
			north.remove(goldSetupLBLs.get(a));
			south.remove(silverSetupButtons.get(a));
			south.remove(silverSetupLBLs.get(a));
		}
    for(int y = 0; y < 6; y++)
    {
			allPieces[0][y] = new Piece(goldImages.get(y), goldPowerLevels.get(y), playerState); 
			board[0][y].setIcon(goldImages.get(y)); //upper row
      board[0][y].setDisabledIcon(goldImages.get(y));
    }
		for (int y = 0; y < 4; y++) {
			allPieces[1][y] = new Piece(goldImages.get(y), goldPowerLevels.get(y), playerState); 
			board[1][y].setIcon(goldImages.get(y)); //lower row
      board[1][y].setDisabledIcon(goldImages.get(y));
		}
		for (int y = 6; y < 8; y++) {
			allPieces[0][y] = new Piece(goldImages.get(0), goldPowerLevels.get(0), playerState); 
			board[0][y].setIcon(goldImages.get(0)); //remaining rabbits upper row
      board[0][y].setDisabledIcon(goldImages.get(0));
		}
		for (int y = 4; y < 8; y++) {
			allPieces[1][y] = new Piece(goldImages.get(0), goldPowerLevels.get(0), playerState); 
			board[1][y].setIcon(goldImages.get(0)); //remaining rabbits lower row
      board[1][y].setDisabledIcon(goldImages.get(0));
		}

		playerState = "silverSetup";
		for(int y = 0; y < 6; y++)
    {
			allPieces[6][y] = new Piece(silverImages.get(y), silverPowerLevels.get(y), playerState); 
			board[6][y].setIcon(silverImages.get(y)); //upper row
      board[6][y].setDisabledIcon(silverImages.get(y));
    }
		for (int y = 0; y < 4; y++) {
			allPieces[7][y] = new Piece(silverImages.get(y), silverPowerLevels.get(y), playerState); 
			board[7][y].setIcon(silverImages.get(y)); //lower row
      board[7][y].setDisabledIcon(silverImages.get(y)); 
		}
		for (int y = 6; y < 8; y++) {
			allPieces[6][y] = new Piece(silverImages.get(0), silverPowerLevels.get(0), playerState); 
			board[6][y].setIcon(silverImages.get(0)); //remaining rabbits upper row
      board[6][y].setDisabledIcon(silverImages.get(0));
		}
		for (int y = 4; y < 8; y++) {
			allPieces[7][y] = new Piece(silverImages.get(0), silverPowerLevels.get(0), playerState); 
			board[7][y].setIcon(silverImages.get(0)); //remaining rabbits lower row
      board[7][y].setDisabledIcon(silverImages.get(0));
		}
		
		startGame();
  }
	
  //starts the setup of the pieces
  public void startSetup()
  {
    disableWholeBoard();
    disableSilverSetupButtons();
    enableGoldSetupButtons();
    JOptionText.setText("<html>Set up your pieces. Choose the type of piece you want to place then<br>click where you want it on the board. Right click to deselect and<br>place a different type of piece. Gold will set up first, then silver.<html>");
    JOptionPane.showMessageDialog(frame, JOptionText);
  }

  //called whenever a button is clicked
  public void actionPerformed(ActionEvent event)
  {
		//when pull is clicked, change instructions, enable pullable pieces, change the background of the pull
		//button, and change the player state
		if(event.getSource().equals(pull)) 
    {
			instructions.setText("Select what piece you want to pull. Right click pull button to cancel");
      endTurn.setEnabled(false);
			pull.setEnabled(false);
			push.setEnabled(false);
      enablePushableOrPullablePieces(playingPieceSelected, previousPositionX, previousPositionY, "pull");
			if (playingPieceSelected.getColor().equals("gold")) {
				pull.setBackground(new Color(224, 214, 173));
				playerState = "goldPullPiece";
			}
			else {
				pull.setBackground(new Color(214, 214, 214));
				playerState = "silverPullPiece";
			}
		}

		//depending on what the current text on the button is, hide or show instructions and change button text
    if(event.getSource().equals(instructionsButton))
    {
			if (instructionsButton.getText().equals("Hide instructions")) {
				instructions.setVisible(false);
      	instructionsButton.setText("Show instructions"); //for hiding/showing a JLabel: 
				//https://stackoverflow.com/questions/13859348/how-to-hide-or-remove-a-jlabel
			}
      else {
				instructions.setVisible(true);
      	instructionsButton.setText("Hide instructions");
			}
		}

		//change instructions, enable pushable pieces, and change player state when push is selected
		if (event.getSource().equals(push)) 
    {
			instructions.setText("Select what piece you want to push. Right click push button to cancel");
      endTurn.setEnabled(false);
			pull.setEnabled(false);
			push.setEnabled(false);
			enablePushableOrPullablePieces(playingPieceSelected, previousPositionX, previousPositionY, "push");
			if (playingPieceSelected.getColor().equals("gold")) {
				push.setBackground(new Color(224, 214, 173));
				playerState = "goldPushPiece";
			}
			else {
				push.setBackground(new Color(214, 214, 214));
				playerState = "silverPushPiece";
			}
		}

		//when end turn button is clicked, check to make sure the last move was valid (not third time
		//position has occurred and there was a net change); if it was, change the player state
		if (event.getSource().equals(endTurn))
    {    
			unColorLastSpot(previousPositionX, previousPositionY);
      if(netChangeInPosition() == false)// there must be a net change in position 
      {
				JOptionText.setText("<html>At least one piece must have a net change in its position.<br>You get your moves back.<html>");
        JOptionPane.showMessageDialog(frame, JOptionText);
        movesRemaining += 4 - movesRemaining; //set movesRemaining to the amount of moves you used
        movesRemainingLBL.setText("    " + movesRemaining);
      }
      else if(previouslyPlayed() == true) //cannot be played more than twice
      {
        JOptionText.setText("<html>This play has already existed more than twice.<br>Move a different direction or a different piece to change it.<html>");
		    JOptionPane.showMessageDialog(frame, JOptionText);
				revertToLastBoardPosition();
		    movesRemaining = 4;
        movesRemainingLBL.setText("    " + movesRemaining);
        enableMovablePieces("gold");
        playerState = "goldPlay";
      }
      else
      {
        instructions.setText("Select a piece to interact with. Right click to de-select.");
        if (playerState.equals("goldPlay"))
        { 
          enableMovablePieces("silver");
          playerState = "silverPlay";
          turnLBL.setText("Silver's turn");
        }
        else
        {
          enableMovablePieces("gold");
          playerState = "goldPlay";
          turnLBL.setText("Gold's turn");
			  }
				push.setEnabled(false);
				pull.setEnabled(false);
				movesRemaining = 4;
				movesRemainingLBL.setText("    " + movesRemaining);
				endTurn.setEnabled(false);
				//piecesMovedThisTurn.removeAll(piecesMovedThisTurn);
      }
		}

    //if the source is a gold setup button
    for (int a = 0; a < goldAnimals.size(); a++) {
      if(event.getSource().equals(goldSetupButtons.get(a)))
      {
        setupPieceSelected = goldAnimals.get(a);
        disableGoldSetupButtons();
				goldSetupButtons.get(a).setBackground(new Color(224, 214, 173));
        enableGoldPlace();
      }
    }
    //if the source is a silver setup button
    for (int a = 0; a < silverAnimals.size(); a++) {
      if(event.getSource().equals(silverSetupButtons.get(a)))
      {
        setupPieceSelected = silverAnimals.get(a);
        disableSilverSetupButtons();
				silverSetupButtons.get(a).setBackground(new Color(214, 214, 214));
        enableSilverPlace();
      }
    }
		
    //if the source is one of the buttons on the board
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (event.getSource().equals(board[x][y]))
				{
					if (playerState.equals("goldSetup"))
					{
						for (int a = 0; a < goldAnimals.size(); a++) {
							if (setupPieceSelected.equals(goldAnimals.get(a))) {
								allPieces[x][y] = new Piece(goldImages.get(a), goldPowerLevels.get(a), playerState); 
								board[x][y].setIcon(goldImages.get(a));
								board[x][y].setDisabledIcon(goldImages.get(a));                     
								goldStartEmpty[x][y] = false;                                   
								goldSetupLBLs.get(a).setText(Integer.toString(Integer.parseInt(goldSetupLBLs.get(a).getText()) - 1));
								disableWholeBoard();
								enableGoldSetupButtons();
								checkRemoveSetup(a, north);
							}
						}
					}
					else if (playerState.equals("silverSetup"))
					{
						for (int a = 0; a < silverAnimals.size(); a++) {
							if (setupPieceSelected.equals(silverAnimals.get(a))) {
								allPieces[x][y] = new Piece(silverImages.get(a), silverPowerLevels.get(a), playerState);
								board[x][y].setIcon(silverImages.get(a));
								board[x][y].setDisabledIcon(silverImages.get(a));
								silverStartEmpty[x-6][y] = false;
								silverSetupLBLs.get(a).setText(Integer.toString(Integer.parseInt(silverSetupLBLs.get(a).getText()) - 1));
								disableWholeBoard();
								enableSilverSetupButtons();
								checkRemoveSetup(a, south);
								
							}
						}
					}
          //the play state is when a piece is selected by the player to interact with
					else if (playerState.equals("goldPlay")) 
          {
						instructions.setText("Select a square to move to. If possible, press push or pull to push/pull another piece.");
            playingPieceSelected = allPieces[x][y];
						//if the slected piece can push any pieces
            if (playingPieceSelected.getPowerLevel() != 1 && movesRemaining > 1 &&
						pushableOrPullablePiecesExist(playingPieceSelected, x, y, "push") == true)
						{
							push.setEnabled(true);
						}
            //if the selected piece can pull any pieces
            if (playingPieceSelected.getPowerLevel() != 1 && movesRemaining > 1 &&
						pushableOrPullablePiecesExist(playingPieceSelected, x, y, "pull") == true)
						{
							pull.setEnabled(true);
						}
						//enable squares the selected piece can move to
            enableSquaresMovableTo(allPieces[x][y], x, y);
						board[x][y].setBackground(new Color(224, 214, 173));
            previousPositionX = x;
            previousPositionY = y;
						playerState = "goldMove";
					}
					else if (playerState.equals("silverPlay")) 
          {
						instructions.setText("Select a square to move to. If possible, press push or pull to push/pull another piece.");
            playingPieceSelected = allPieces[x][y];
						if (playingPieceSelected.getPowerLevel() != 1 && movesRemaining > 1 &&
						pushableOrPullablePiecesExist(playingPieceSelected, x, y, "push") == true)
						{
							push.setEnabled(true);
						}
            if (playingPieceSelected.getPowerLevel() != 1 && movesRemaining > 1 &&
						pushableOrPullablePiecesExist(playingPieceSelected, x, y, "pull") == true)
						{
							pull.setEnabled(true);
						}
            enableSquaresMovableTo(allPieces[x][y], x, y);
            board[x][y].setBackground(new Color(214, 214, 214));
						previousPositionX = x;
            previousPositionY = y;
						playerState = "silverMove";
					}
          //the move state is when a square that the slected piece will move to is clicked
					else if (playerState.equals("goldMove")) {
            push.setEnabled(false);
						pull.setEnabled(false);
						//if the player is about to move themselves into a capture square, give them a warning
            if((x == 2 && y == 2) || (x == 2 && y == 5) || (x == 5 && y == 2) || (x == 5 && y == 5))
            {
              JOptionText.setText("<html>Are you sure you want to move your piece here?<br>If there are no friendly neighbors, this piece will be captured.<html>");
              int input = JOptionPane.showConfirmDialog(frame, JOptionText, "Trap Square Warning", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
              if(input == 0)
              {
                movesRemaining -= 1;
                movesRemainingLBL.setText("    " + movesRemaining);
                //move the piece to this spot, delete it from the old spot
                movePiece(playingPieceSelected, previousPositionX, previousPositionY, x, y);
                //update previous position in case we move again
                previousPositionX = x;
                previousPositionY = y;
                disableWholeBoard();
                goldMoveEnded();
              }
              else
              {
                playerState = "goldPlay";
								disableWholeBoard();
                enableMovablePieces("gold");
              }
            }
            else
            {
              movesRemaining -= 1;
              movesRemainingLBL.setText("    " + movesRemaining);
              //move the piece to this spot, delete it from the old spot
              movePiece(playingPieceSelected, previousPositionX, previousPositionY, x, y);
              //update previous position in case we move again
              previousPositionX = x;
              previousPositionY = y;
              disableWholeBoard();
              goldMoveEnded(); 
            }
					}
					else if (playerState.equals("silverMove")) {
						push.setEnabled(false);
						pull.setEnabled(false);
						//if the player is about to move themselves into a capture suare, give them a warning
            if((x == 2 && y == 2) || (x == 2 && y == 5) || (x == 5 && y == 2) || (x == 5 && y == 5))
            {
              JOptionText.setText("<html>Are you sure you want to move your piece here?<br>If there are no friendly neighbors, this piece will be captured.<html>");
              int input = JOptionPane.showConfirmDialog(frame, JOptionText, "Trap Square Warning", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
              if(input == 0)
              {
                movesRemaining -= 1;
                movesRemainingLBL.setText("    " + movesRemaining);
                //move the piece to this spot, delete it from the old spot
                movePiece(playingPieceSelected, previousPositionX, previousPositionY, x, y);
                //update previous position in case we move again
                previousPositionX = x;
                previousPositionY = y;
                disableWholeBoard();
                silverMoveEnded();
              }
              else
              {
                playerState = "silverPlay";
								disableWholeBoard();
                enableMovablePieces("silver");
              }
            }
            else
            {
              movesRemaining -= 1;
              movesRemainingLBL.setText("    " + movesRemaining);
              //move the piece to this spot, delete it from the old spot
              movePiece(playingPieceSelected, previousPositionX, previousPositionY, x, y);
              //update previous position in case we move again
              previousPositionX = x;
              previousPositionY = y;
              disableWholeBoard();
              silverMoveEnded(); 
            }
					}
          //the push piece state is when a piece that the selected piece will push is clicked 
					else if (playerState.equals("goldPushPiece"))
					{
						instructions.setText("Select where to push this piece to.");
            weakX = x;
						weakY = y;
						enableSquaresPushableTo(x, y);
						playerState = "goldPushPlace";
					}
					else if (playerState.equals("silverPushPiece"))
					{
						instructions.setText("Select where to push this piece to.");
            weakX = x;
						weakY = y;
            enableSquaresPushableTo(x, y);
						playerState = "silverPushPlace";
					}
          //the push place state is when the place that the weak piece will be pushed to is selected
					else if (playerState.equals("goldPushPlace"))
					{
						movePiece(allPieces[weakX][weakY],
						weakX, weakY, x, y);
						//move the strong piece to where the weak piece was
						movePiece(allPieces[previousPositionX][previousPositionY], previousPositionX, previousPositionY, weakX, weakY);
						push.setBackground(null);
						movesRemaining = movesRemaining - 2;
						movesRemainingLBL.setText("    " + movesRemaining);
						goldMoveEnded();
					}
					else if (playerState.equals("silverPushPlace"))
					{
						movePiece(allPieces[weakX][weakY],
						weakX, weakY, x, y);
						//move the strong piece to where the weak piece was
						movePiece(allPieces[previousPositionX][previousPositionY],
						previousPositionX, previousPositionY, weakX, weakY);
						push.setBackground(null);
						movesRemaining = movesRemaining - 2;
						movesRemainingLBL.setText("    " + movesRemaining);
						silverMoveEnded();
					}
          //the pull piece state is when a piece that the selected piece will pull is clicked 
					else if (playerState.equals("goldPullPiece"))
					{
						instructions.setText("Select where to pull this piece to.");
            weakX = x;
						weakY = y;
						enableSquaresMovableTo(allPieces[previousPositionX][previousPositionY], previousPositionX, previousPositionY);
            pull.setBackground(null);
						playerState = "goldPullPlace";
					}
					else if (playerState.equals("silverPullPiece"))
					{
            instructions.setText("Select where to pull this piece to.");
            weakX = x;
						weakY = y;
						enableSquaresMovableTo(allPieces[previousPositionX][previousPositionY], previousPositionX, previousPositionY);
            pull.setBackground(null);
						playerState = "silverPullPlace";
					}
          //the pull place state is when the place that the strong piece will move to in order to pull the weak peice is clicked
					else if (playerState.equals("goldPullPlace"))
					{
						//piecesMovedThisTurn.remove(allPieces[previousPositionX][previousPositionY]);
            //move the strong piece to the new position
            movePiece(allPieces[previousPositionX][previousPositionY], previousPositionX, previousPositionY, x, y);
            //move the weak piece to where the strong piece used to be
            movePiece(allPieces[weakX][weakY], weakX, weakY, previousPositionX, previousPositionY);
            movesRemaining = movesRemaining - 2;
						movesRemainingLBL.setText("    " + movesRemaining); /*bug bookmark*/
						goldMoveEnded();
					}
					else if (playerState.equals("silverPullPlace"))
					{
						//piecesMovedThisTurn.remove(allPieces[previousPositionX][previousPositionY]);
            //move the strong piece to the new position
            movePiece(allPieces[previousPositionX][previousPositionY], previousPositionX, previousPositionY, x, y);
            //move the weak piece to where the strong piece used to be
            movePiece(allPieces[weakX][weakY], weakX, weakY, previousPositionX, previousPositionY);
            movesRemaining = movesRemaining - 2;
						movesRemainingLBL.setText("    " + movesRemaining);
						silverMoveEnded();
					}
				}
			}
		}
  }

  public void mouseClicked(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
  
  //called whenever the mouse click something, whether it is disabled or not
  public void mouseReleased(MouseEvent event)
  {
    //we are only using this for right clicks 
    if(SwingUtilities.isRightMouseButton(event) == true)
    {
      if(playerState.equals("goldSetup") && goldSetupButtons.contains(event.getSource()))
      {
        for (int a = 0; a < goldAnimals.size(); a++)
        {
          //if the background is colored that means it is slected so it can be de-selected.
          if(event.getSource().equals(goldSetupButtons.get(a)) && goldSetupButtons.get(a).getBackground().equals(new Color(224, 214, 173)))
          {
            goldSetupButtons.get(a).setBackground(null);
            setupPieceSelected = "";
            enableGoldSetupButtons();
            disableWholeBoard();
          }
        }
      }
      else if(playerState.equals("silverSetup") && silverSetupButtons.contains(event.getSource()))
      {
        for (int a = 0; a < silverAnimals.size(); a++)
        {
          if(event.getSource().equals(silverSetupButtons.get(a)) && silverSetupButtons.get(a).getBackground().equals(new Color(214, 214, 214)))
          {
            silverSetupButtons.get(a).setBackground(null);
            setupPieceSelected = "";
            enableSilverSetupButtons();
            disableWholeBoard();
          }
        }
			}
      //cancel a pull, go back to being able to move the selected piece 
			else if(event.getSource().equals(pull)) 
      {
        instructions.setText("Select a square to move to. If possible, press push or pull to push/pull another piece.");
        if(pull.getBackground().equals(new Color(224, 214, 173)))
        {
          enableSquaresMovableTo(playingPieceSelected, previousPositionX, previousPositionY);
          board[previousPositionX][previousPositionY].setBackground(new Color(224, 214, 173));
          pull.setEnabled(true);
          if(pushableOrPullablePiecesExist(playingPieceSelected, previousPositionX, previousPositionY, "push") == true)
          {
            push.setEnabled(true);
          }
          pull.setBackground(null);
          playerState = "goldMove";
        }
        else if(pull.getBackground().equals(new Color(214, 214, 214)))
        {
          enableSquaresMovableTo(playingPieceSelected, previousPositionX, previousPositionY);
          board[previousPositionX][previousPositionY].setBackground(new Color(214, 214, 214));
          pull.setEnabled(true);
          if(pushableOrPullablePiecesExist(playingPieceSelected, previousPositionX, previousPositionY, "push") == true)
          {
            push.setEnabled(true);
          }
          pull.setBackground(null);
          playerState = "silverMove"; 
        }
			}
      //cancel a push, go back to being able to move the selected piece 
			else if (event.getSource().equals(push)) 
      {
				instructions.setText("Select a square to move to. If possible, press push or pull to push/pull another piece.");
        if(push.getBackground().equals(new Color(224, 214, 173)))
        {
          enableSquaresMovableTo(playingPieceSelected, previousPositionX, previousPositionY);
          board[previousPositionX][previousPositionY].setBackground(new Color(224, 214, 173));
          push.setEnabled(true);
          if(pushableOrPullablePiecesExist(playingPieceSelected, previousPositionX, previousPositionY, "pull") == true)
          {
            pull.setEnabled(true);
          }
          push.setBackground(null);
          playerState = "goldMove"; 
        }
        else if(push.getBackground().equals(new Color(214, 214, 214)))
        {
          enableSquaresMovableTo(playingPieceSelected, previousPositionX, previousPositionY);
          board[previousPositionX][previousPositionY].setBackground(new Color(214, 214, 214));
          push.setEnabled(true);
          if(pushableOrPullablePiecesExist(playingPieceSelected, previousPositionX, previousPositionY, "pull") == true)
          {
            pull.setEnabled(true);
          }
          push.setBackground(null);
          playerState = "silverMove"; 
        }
			}
      //to deselect one of the pieces on the board and move a different piece
      for(int x = 0; x < 8; x++)
      {
        for(int y = 0; y < 8; y++)
        {
          if(event.getSource().equals(board[x][y]) && (board[x][y].getBackground().equals(new Color(224, 214, 173)) || board[x][y].getBackground().equals(new Color(214, 214, 214))))
          {
            push.setEnabled(false);
            pull.setEnabled(false);
            if(playerState.equals("goldMove"))
            {
              instructions.setText("Select a piece to interact with. Right click to de-select.");
							unColorLastSpot(x, y);
              playingPieceSelected = null;
							playerState = "goldPlay";
              enableMovablePieces("gold");
            }
            if(playerState.equals("silverMove"))
            {
              instructions.setText("Select a piece to interact with. Right click to de-select.");
              unColorLastSpot(x, y);
              playingPieceSelected = null;
							playerState = "silverPlay";
              enableMovablePieces("silver");
            }
          }
        }
      }
		}
  }

  //enables all the buttons on the board
  public void enableWholeBoard() {
		for (int a = 0; a < 8; a++)
		{
			for (int b = 0; b < 8; b++)
			{
				board[a][b].setEnabled(true);
			}
		}
	}

  //disables all the buttons on the board
	public void disableWholeBoard() {
		for (int a = 0; a < 8; a++)
		{
			for (int b = 0; b < 8; b++)
			{
				unColorLastSpot(a, b);
        board[a][b].setEnabled(false);
			}
		}
	}

  //enables the first two rows of gold's side (for setup)
  public void enableGoldPlace() {
    for (int a = 0; a < 2; a++) {
      for (int b = 0; b < 8; b++) {
        if (goldStartEmpty[a][b] == true) {
          board[a][b].setEnabled(true);
        }
      }
    }
  }

  //enables the first two rows of silver's side (for setup)
  public void enableSilverPlace() {
    for (int a = 6; a < 8; a++) {
      for (int b = 0; b < 8; b++) {
        if (silverStartEmpty[a - 6][b] == true) {
          board[a][b].setEnabled(true);
        }
      }
    }
  }

  //checks if a set up button should be removed (it's number of pieces has reached 0)
  public void checkRemoveSetup(int index, Container container) {
    if (container.equals(north)) {
  		if (goldSetupLBLs.get(index).getText().equals("0")) {
  			goldAnimals.remove(index);
				goldPowerLevels.remove(index);
  			goldImages.remove(index);
  			container.remove(goldSetupButtons.get(index));
  			goldSetupButtons.remove(index);
  			container.remove(goldSetupLBLs.get(index));
  			goldSetupLBLs.remove(index);
  			frame.repaint();
        if (goldAnimals.isEmpty() == true) {
          playerState = "silverSetup";
          disableWholeBoard();
          enableSilverSetupButtons();
        }
      }
    }
    else if (container.equals(south)) {
  		if (silverSetupLBLs.get(index).getText().equals("0")) {
  			silverAnimals.remove(index);
				silverPowerLevels.remove(index);
  			silverImages.remove(index);
  			container.remove(silverSetupButtons.get(index));
  			silverSetupButtons.remove(index);
  			container.remove(silverSetupLBLs.get(index));
  			silverSetupLBLs.remove(index);
  			frame.repaint();
        if (silverAnimals.isEmpty() == true) {
          startGame();
        }
      }
    }
	}

  //disables all gold setup buttons
  public void disableGoldSetupButtons() {
    for (int a = 0; a < goldSetupButtons.size(); a++) 
    {
      goldSetupButtons.get(a).setEnabled(false);
    }
  }

  //disables all silver setup buttons
  public void disableSilverSetupButtons() {
    for (int a = 0; a < silverSetupButtons.size(); a++) {
      silverSetupButtons.get(a).setEnabled(false);
    }
  }

  //enables all gold setup buttons
  public void enableGoldSetupButtons() {
	  for (int a = 0; a < goldSetupButtons.size(); a++) {
			goldSetupButtons.get(a).setBackground(null);
      goldSetupButtons.get(a).setEnabled(true);
    }
  }

  //enables all silver setup buttons
  public void enableSilverSetupButtons() {
    for (int a = 0; a < silverSetupButtons.size(); a++) {
			silverSetupButtons.get(a).setBackground(null);
      silverSetupButtons.get(a).setEnabled(true);
    }
  }

  //starts the game: adds the new UI on the sides and allows gold to move their pieces
  public void startGame()
  {
		instructions.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
    instructions.setPreferredSize(new Dimension(650, 30));
    north.add(instructions);
    instructionsButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
    instructionsButton.setPreferredSize(new Dimension(180, 40));
    instructionsButton.addActionListener(this);
    north.add(instructionsButton);
    west.setLayout(new GridLayout(4, 1));
		west.setPreferredSize(new Dimension(115, 100));
    turnLBL.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		west.add(turnLBL);
    moves.setLayout(new BoxLayout(moves, BoxLayout.Y_AXIS));
    movesRemainingTextLBL.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		moves.add(movesRemainingTextLBL);
		movesRemainingLBL.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		moves.add(movesRemainingLBL);
    west.add(moves);
		endTurn.addActionListener(this);
    endTurn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		endTurn.setEnabled(false);
		west.add(endTurn);
		east.setLayout(new GridLayout(2, 1));
		push.addActionListener(this);
    push.addMouseListener(this);
    push.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		push.setEnabled(false);
		east.add(push);
		pull.addActionListener(this);
    pull.addMouseListener(this);
    pull.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		pull.setEnabled(false);
		east.add(pull);
		east.setPreferredSize(new Dimension(115, 100));
		frame.repaint();
		addDummyBoardPosition();
		playerState = "goldPlay";
		disableWholeBoard();
    enableMovablePieces("gold");
    instructions.setText("Select a piece to interact with. Right click to de-select.");
  }

  //disables all gold/silver pieces that cannot move, either because a piece is frozen or all its sides are blocked off by pieces it cannot push
  public void enableMovablePieces(String color)
  {
    //go through all the pieces of that color, check their neighbors
    for (int x = 0; x < 8; x++) 
    {
			for (int y = 0; y < 8; y++) 
      {
        if(allPieces[x][y] != null && allPieces[x][y].getColor().equals(color))
        {
          checkNeighbors(allPieces[x][y], x, y); //add the piece's neighbors to its arraylist of neighbors
        }
      }
    }

    //go through the board and enable all non-frozen piece squares & disable all that are frozen, belong to
		//the other player, or cannot move because they are surrounded by non-pushable pieces in all directions.
    for (int x = 0; x < 8; x++) 
    {
			for (int y = 0; y < 8; y++) 
      {
        //checking if it is correct color and not frozen
        if(allPieces[x][y] != null && allPieces[x][y].getColor().equals(color) && isFrozen(allPieces[x][y]) == false)
        {
          //check if any of the pieces around the current piece are pushable. If so, the current piece cannot be blocked off
          if(pushableOrPullablePiecesExist(allPieces[x][y], x, y, "push") == true)
          {
            board[x][y].setEnabled(true);
          }
          //check if all its sides are not blocked off 
          else if(((x < 7 && x > 0) && (y > 0 && y < 7) && allPieces[x][y].getNeighbors().size() < 4) || ((x == 7 ^ x == 0 ^ y == 0 ^ y == 7) && allPieces[x][y].getNeighbors().size() < 3) || (((x == 0 && y == 0) || (x == 7 && y == 0) || (x == 7 && y == 7) || (x == 0 && y == 7)) && allPieces[x][y].getNeighbors().size() < 2)) //'^' is xor, which means only one of the conditions may be true for the section to be true
          {
            board[x][y].setEnabled(true);
          }
        }
        else
        {
          board[x][y].setEnabled(false);
        }
      }
    }  
  }

  //check the 4 cardinal directions of a given piece for other pieces, adds those pieces to an arraylist of neighbors
  public void checkNeighbors(Piece currentPiece, int x, int y)
  {
    currentPiece.resetNeighbors();
    if(x > 0 && allPieces[x - 1][y] != null) //top
    {
      currentPiece.addNeighbor(allPieces[x-1][y]);
    }
    if(y < 7 && allPieces[x][y + 1] != null) //right
    {
      currentPiece.addNeighbor(allPieces[x][y + 1]);
    }
    if(x < 7 && allPieces[x + 1][y] != null) //bottom
    {
      currentPiece.addNeighbor(allPieces[x + 1][y]);
    }
    if(y > 0 && allPieces[x][y - 1] != null) //left
    {
      currentPiece.addNeighbor(allPieces[x][y - 1]);
    }
  }

  //returns if a piece is frozen (if it has an adjacent more powerful enemy and does not have an adjacent friendly piece)
  public boolean isFrozen(Piece currentPiece)
  {
    boolean morePowerfulEnemy = false;
    boolean friendlyPiece = false;
    for(int a = 0; a < currentPiece.getNeighbors().size(); a++)
    {
		if(currentPiece.getNeighbors().get(a) != null)
		{
			Piece neighborPiece = currentPiece.getNeighbors().get(a);
			
		  //if there is a more powerful opponent neighbor
		  if(neighborPiece.getPowerLevel() > currentPiece.getPowerLevel() && !neighborPiece.getColor().equals(currentPiece.getColor()))
		  {
		    morePowerfulEnemy = true;
		  }
		  //if there is a friendly neighbor
		  else if(neighborPiece.getColor().equals(currentPiece.getColor()))
		  {
		    friendlyPiece = true;
		  }
		}
    	
    }
    if(morePowerfulEnemy == true && friendlyPiece == false)
    {
      if(!frozenPieces.contains(currentPiece))
      {
    	  frozenPieces.add(currentPiece);
      }
      return true;
    }
    else
    {
      return false;
    }
  }

  //remove pieces that are no longer frozen from the frozenPieces ArrayList
  public void removeNonFrozenPieces()
  {
    for (int x = 0; x < 8; x++)
    {
      for (int y = 0; y < 8; y++) 
      {
        if(allPieces[x][y] != null)
        {
          checkNeighbors(allPieces[x][y], x, y);
        }
      }
	  }
    for(int a = 0; a < frozenPieces.size(); a++)
    {
      if(frozenPieces.get(a) != null)
      {
        if(isFrozen(frozenPieces.get(a)) == false) 
        {
          frozenPieces.set(a, null);
          //cannot remove from frozenPieces because then size will change 
        }
      }
    }
  }

  //called after a player selects a piece to move, enables all squares player can move the piece to 
  public void enableSquaresMovableTo(Piece currentPiece, int x, int y)
  {
    disableWholeBoard();
    if(x > 0 && allPieces[x - 1][y] == null) //top
    {
      board[x - 1][y].setEnabled(true);
    }
    if(y < 7 && allPieces[x][y + 1] == null) //right
    {
      board[x][y + 1].setEnabled(true);
    }
    if(x < 7 && allPieces[x + 1][y] == null) //bottom
    {
      board[x + 1][y].setEnabled(true);
    }
    if(y > 0 && allPieces[x][y - 1] == null) //left
    {
      board[x][y - 1].setEnabled(true);
    }
    if(x > 0 && board[x - 1][y] != null && currentPiece.getPowerLevel() == 1
		&& currentPiece.getColor().equals("gold"))
    {
      board[x - 1][y].setEnabled(false);
    }
		if(x < 7 && board[x + 1][y] != null && currentPiece.getPowerLevel() == 1
		&& currentPiece.getColor().equals("silver"))
    {
      board[x + 1][y].setEnabled(false);
    }
  }

	//checks to see if the current piece can push any other pieces
  public boolean pushableOrPullablePiecesExist(Piece currentPiece, int x, int y, String pushOrPull)
  {
    //a piece can only pull another piece if it has empty spaces around it to pull it to
    if((pushOrPull.equals("pull") && hasEmptyNeighbor(x, y) == true) || (pushOrPull.equals("push")))
    {
      if(x > 0 && allPieces[x-1][y] != null &&
      allPieces[x - 1][y].getPowerLevel() < currentPiece.getPowerLevel()
      && allPieces[x - 1][y].getColor() != currentPiece.getColor()) //top
      {
        if(pushOrPull.equals("pull"))
        {
          return true;
        }
        if(pushOrPull.equals("push") && hasEmptyNeighbor(x - 1, y) == true)
        {
          return true;
        }
      }
      if(y < 7 && allPieces[x][y + 1] != null &&
      allPieces[x][y + 1].getPowerLevel() < currentPiece.getPowerLevel()
      && allPieces[x][y + 1].getColor() != currentPiece.getColor()) //right
      {
        if(pushOrPull.equals("pull"))
        {
          return true;
        }
        if(pushOrPull.equals("push") && hasEmptyNeighbor(x, y + 1) == true)
        {
          return true;
        }
      }
      if(x < 7 && allPieces[x + 1][y] != null &&
      allPieces[x + 1][y].getPowerLevel() < currentPiece.getPowerLevel()
      && allPieces[x + 1][y].getColor() != currentPiece.getColor()) //bottom
      {
        if(pushOrPull.equals("pull"))
        {
          return true;
        }
        if(pushOrPull.equals("push") && hasEmptyNeighbor(x + 1, y) == true)
        {
          return true;
        }
      }
      if(y > 0 && allPieces[x][y - 1] != null &&
      allPieces[x][y - 1].getPowerLevel() < currentPiece.getPowerLevel()
      && allPieces[x][y - 1].getColor() != currentPiece.getColor()) //left
      {
        if(pushOrPull.equals("pull"))
        {
          return true;
        }
        if(pushOrPull.equals("push") && hasEmptyNeighbor(x, y - 1) == true)
        {
          return true;
        }
      }
    }
		return false;
  }

	//called after a player selects one of their own pieces and clicks the push or pull button, enables the pieces the stronger piece can push/pull
  public void enablePushableOrPullablePieces(Piece currentPiece, int x, int y, String pushOrPull)
  {
    disableWholeBoard();
    if(x > 0 && allPieces[x - 1][y] != null &&
    allPieces[x - 1][y].getPowerLevel() < currentPiece.getPowerLevel()
    && allPieces[x - 1][y].getColor() != currentPiece.getColor()) //top
    {
      if(pushOrPull.equals("pull"))
      {
        board[x - 1][y].setEnabled(true);
      }
      if(pushOrPull.equals("push") && hasEmptyNeighbor(x - 1, y) == true)
      {
        board[x - 1][y].setEnabled(true);
      }
    }
    if(y < 7 && allPieces[x][y + 1] != null &&
    allPieces[x][y + 1].getPowerLevel() < currentPiece.getPowerLevel()
    && allPieces[x][y + 1].getColor() != currentPiece.getColor()) //right
    {
      if(pushOrPull.equals("pull"))
      {
        board[x][y + 1].setEnabled(true);
      }
      if(pushOrPull.equals("push") && hasEmptyNeighbor(x, y + 1) == true)
      {
        board[x][y + 1].setEnabled(true);
      }
    }
    if(x < 7 && allPieces[x + 1][y] != null &&
    allPieces[x + 1][y].getPowerLevel() < currentPiece.getPowerLevel()
    && allPieces[x + 1][y].getColor() != currentPiece.getColor()) //bottom
    {
      if(pushOrPull.equals("pull"))
      {
        board[x + 1][y].setEnabled(true);
      }
      if(pushOrPull.equals("push") && hasEmptyNeighbor(x + 1, y) == true)
      {
        board[x + 1][y].setEnabled(true);
      }
    }
    if(y > 0 && allPieces[x][y - 1] != null &&
    allPieces[x][y - 1].getPowerLevel() < currentPiece.getPowerLevel()
    && allPieces[x][y - 1].getColor() != currentPiece.getColor()) //left
    {
      if(pushOrPull.equals("pull"))
      {
        board[x][y - 1].setEnabled(true);
      }
      if(pushOrPull.equals("push") && hasEmptyNeighbor(x, y - 1) == true)
      {
        board[x][y - 1].setEnabled(true);
      }
    } 
  }

  //enables the squares that a weaker piece can be pushed to
  public void enableSquaresPushableTo(int x, int y) 
  {
    disableWholeBoard();
    if(x > 0 && allPieces[x - 1][y] == null) //top
    {
      board[x - 1][y].setEnabled(true);
    }
    if(y < 7 && allPieces[x][y + 1] == null) //right
    {
      board[x][y + 1].setEnabled(true);
    }
    if(x < 7 && allPieces[x + 1][y] == null) //bottom
    {
      board[x + 1][y].setEnabled(true);
    }
    if(y > 0 && allPieces[x][y - 1] == null) //left
    {
      board[x][y - 1].setEnabled(true);
    }
  }

  //returns if a piece has an empty square adjacent to it (if it can pull something)
  public boolean hasEmptyNeighbor(int x, int y)
  {
    if(x > 0 && allPieces[x - 1][y] == null) //top
    {
      return true;
    }
    if(y < 7 && allPieces[x][y + 1] == null) //right
    {
      return true;
    }
    if(x < 7 && allPieces[x + 1][y] == null) //bottom
    {
      return true;
    }
    if(y > 0 && allPieces[x][y - 1] == null) //left
    {
      return true;
    }
    return false;
  }

  //check for a win: if a rabbit has reached the home rank of the enemy, if all of one player's rabbits are
	//gone, or if all of a player's pieces are frozen. 
  public void checkWin()
  {
    for (int x = 0; x < 8; x++) 
    {
      for (int y = 0; y < 8; y++) 
      {
        if(allPieces[x][y] != null)
        {
          checkNeighbors(allPieces[x][y], x, y);
          isFrozen(allPieces[x][y]);
        }
      }
    }
    for(int a = 0; a < 8; a++)
    {
      if(allPieces[0][a] != null && allPieces[0][a].getPowerLevel() == 1
			&& allPieces[0][a].getColor().equals("silver")) 
      {
				JOptionText.setText("A silver rabbit has reached gold's home rank. Silver wins!");
        JOptionPane.showMessageDialog(frame, JOptionText);
        endGame();
			}
    }
    for(int a = 0; a < 8; a++)
    {
      if(allPieces[7][a] != null && allPieces[7][a].getPowerLevel() == 1
			&& allPieces[7][a].getColor().equals("gold"))
			{
				JOptionText.setText("A gold rabbit has reached silver's home rank. Gold wins!");
        JOptionPane.showMessageDialog(frame, JOptionText);
        endGame();
			}
    }
    if(goldRabbits == 0)
    {
      JOptionText.setText("All of gold's rabbits have been captured. Silver wins!");
      JOptionPane.showMessageDialog(frame, JOptionText);
      endGame();
    } 
    if(silverRabbits == 0)
    {
      JOptionText.setText("All of silver's rabbits have been captured. Gold wins!");
      JOptionPane.showMessageDialog(frame, JOptionText);
      endGame();
    }
    int goldFrozenPieces = 0;
    int silverFrozenPieces = 0;
    for(int a = 0; a < frozenPieces.size(); a++)
    {
      if(frozenPieces.get(a) != null && frozenPieces.get(a).getColor().equals("gold"))
      {
        goldFrozenPieces++;
      }
      if(frozenPieces.get(a) != null && frozenPieces.get(a).getColor().equals("silver"))
      {
        silverFrozenPieces++;
      }
    }
    if(goldFrozenPieces == goldCurrentPieces)
    {
      JOptionText.setText("All of gold's pieces are frozen. Silver wins!");
      JOptionPane.showMessageDialog(frame, JOptionText);
      endGame();
    }
    if(silverFrozenPieces == silverCurrentPieces)
    {
      JOptionText.setText("All of silver's pieces are frozen. Gold wins!");
      JOptionPane.showMessageDialog(frame, JOptionText);
      endGame();
    }
  }
	
  //called when a player wins
  public void endGame()
  {
    frame.dispose(); //will close window
  }
  
  //moves a piece from one spot to another
	public void movePiece (Piece piece, int oldX, int oldY, int newX, int newY)
	{
		board[newX][newY].setIcon(piece.getAnimal());
		board[newX][newY].setDisabledIcon(piece.getAnimal());
		allPieces[newX][newY] = piece;
		allPieces[oldX][oldY] = null;
		board[oldX][oldY].setIcon(null);
		board[oldX][oldY].setDisabledIcon(null);
		unColorLastSpot(oldX, oldY);
	}
  
  //returns if there has been a net change in position of the pieces that turn
  public boolean netChangeInPosition()
  {
		if (playerState.equals("goldMove") || playerState.equals("goldPushPlace")
		|| playerState.equals("goldPullPlace"))
		{
			if(arraysEqual(goldPreviousPlays.get(goldLastPositionIndex), allPieces) == false)
			{
				System.out.println("net change occurred");
				return true;
			}
			else
			{
				System.out.println("no net change");
				return false;
			}
		}
		else {
			if(arraysEqual(silverPreviousPlays.get(silverLastPositionIndex), allPieces) == false)
			{
				System.out.println("net change occurred");
				return true;
			}
			else
			{
				System.out.println("no net change");
				return false;
			}
		}
    
  }

  //called whenever gold completes a move (a movement of a piece, a push, or a pull)
	public void goldMoveEnded ()
	{
    checkPieceCaptured();
    removeNonFrozenPieces();
    checkWin();
    //if gold's turn is over with
    if (movesRemaining != 0)
		{
			instructions.setText("Select a piece to interact with. Right click to de-select.");
			enableMovablePieces("gold");
			playerState = "goldPlay";
			endTurn.setEnabled(true);
		}
    //if it's not over with
		else {
			instructions.setText("Select another piece to interact with. This can be the same piece.");
			//first we make sure there's net change in position
      if(netChangeInPosition() == false) 
      {
				//System.out.println("previouslyPlayed is true");
				JOptionText.setText("<html>At least one piece must have a net change in its position.<br>You get your moves back.<html>");
		    JOptionPane.showMessageDialog(frame, JOptionText);
		    movesRemaining += 4 - movesRemaining; //set movesRemaining to the amount of moves used 
        movesRemainingLBL.setText("    " + movesRemaining);
        enableMovablePieces("gold");
        playerState = "goldPlay"; //choose another piece to move
      }
      //then we make sure this possition of the board hasen't happened more than twice 
      else if(previouslyPlayed() == true) 
      {
        JOptionText.setText("<html>This play has already existed more than twice.<br>Move a different direction or a different piece to change it.<html>");
		    JOptionPane.showMessageDialog(frame, JOptionText);
				revertToLastBoardPosition();
		    movesRemaining = 4;
        movesRemainingLBL.setText("    " + movesRemaining);
        enableMovablePieces("gold");
        playerState = "goldPlay";
      }
      else //gold's turn over
      {
				System.out.println("previouslyPlayed is false (gold's turn over)");
        instructions.setText("Select a piece to interact with. Right click to de-select.");
				disableWholeBoard();
        enableMovablePieces("silver");
        playerState = "silverPlay";
        turnLBL.setText("Silver's turn"); 
        movesRemaining = 4;
        movesRemainingLBL.setText("    " + movesRemaining);
        endTurn.setEnabled(false);
        //piecesMovedThisTurn.removeAll(piecesMovedThisTurn);
      }
		}
	}

  //called whenever silver completes a move (a movement of a piece, a push, or a pull)
	public void silverMoveEnded ()
	{
		instructions.setText("Select another piece to interact with. This can be the same piece.");
    checkPieceCaptured();
    removeNonFrozenPieces();
    checkWin();
     //if silver's turn is over with
    if (movesRemaining != 0)
		{
			enableMovablePieces("silver");
			playerState = "silverPlay";
			endTurn.setEnabled(true);
		}
    //if it's not over with
		else
		{
      if(netChangeInPosition() == false) //there must be a net change in position
      {
				//System.out.println("previouslyPlayed is true");
				JOptionText.setText("<html>At least one piece must have a net change in its position.<br>You get your moves back.<html>");
		    JOptionPane.showMessageDialog(frame, JOptionText);
		    movesRemaining += 4 - movesRemaining; //set movesRemaining to the amount of moves you used 
        movesRemainingLBL.setText("    " + movesRemaining);
        enableMovablePieces("silver");
        playerState = "silverPlay";
      }
      else if(previouslyPlayed() == true)  //this can't be the third time reaching this position with the same piece
      {
        JOptionText.setText("<html>This play has already existed more than twice.<br>Move a different direction or a different piece to change it.<html>");
		    JOptionPane.showMessageDialog(frame, JOptionText);
				revertToLastBoardPosition();
		    movesRemaining = 4;
        movesRemainingLBL.setText("    " + movesRemaining);
        enableMovablePieces("gold");
        playerState = "goldPlay";
      }
      else
      {
        instructions.setText("Select a piece to interact with. Right click to de-select.");
				disableWholeBoard();
        enableMovablePieces("gold");
        playerState = "goldPlay";
        turnLBL.setText("Gold's turn"); 
        movesRemaining = 4;
        movesRemainingLBL.setText("    " + movesRemaining);
        endTurn.setEnabled(false);
      }
		}
	}

  //check if a piece is captured (in one of the red squares and does not have a friendly neighbor)
  public void checkPieceCaptured()
  {
    for(int x = 0; x < 8; x++)
    {
      for(int y = 0; y < 8; y++)
      {
        if(board[x][y].getBackground().equals(new Color(255, 128, 128)) && allPieces[x][y] != null && hasFriendlyNeighbors(allPieces[x][y], x, y) == false)
        {
          if(allPieces[x][y].getColor().equals("gold"))
          {
            goldCurrentPieces--;
            if(allPieces[x][y].getPowerLevel() == 1)
            {
              goldRabbits--;
            }
          }
          else if(allPieces[x][y].getColor().equals("silver"))
          {
            silverCurrentPieces--;
            if(allPieces[x][y].getPowerLevel() == 1)
            {
              silverRabbits--;
            }
          }
          allPieces[x][y] = null;
          board[x][y].setIcon(null);
          board[x][y].setDisabledIcon(null);
        }
      }
    }
  }

	public boolean hasFriendlyNeighbors(Piece piece, int x, int y)
	{
		checkNeighbors(piece, x, y);
    if (piece.getNeighbors().size() > 0)
		{ 
			for (int a = 0; a < piece.getNeighbors().size(); a++)
			{
				if (piece.getNeighbors().get(a).getColor().equals(piece.getColor()))
				{
					//System.out.println("Has Friendly Neighbors");
					return true;
				}
			}
			//System.out.println("No Friendly Neighbors");
			return false;
		}
		else
		{
			//System.out.println("No Neighbors");
			return false;
		}
	}

	//Look through previous ending positions to see if the current one has occurred before; if it has,
	//check to see if it's the third time.
	public boolean previouslyPlayed ()
	{
		if (playerState.equals("goldMove") || playerState.equals("goldPushPlace")
		|| playerState.equals("goldPullPlace"))
		{
			for (int a = 0; a < goldPreviousPlays.size(); a++)
			{
				if (arraysEqual(goldPreviousPlays.get(a), allPieces) == true)
				{
					System.out.println("played before");
					if (goldTimesPreviouslyPlayed.get(a) > 2)
					{
						//this is the third time this board position has occurred
						System.out.println("3rd time (gold)");
						return true;
					}
					else {
						goldLastPositionIndex = a; /*major bookmark*/
						System.out.println("time still ok (gold)");
						goldTimesPreviouslyPlayed.set(a, (goldTimesPreviouslyPlayed.get(a) + 1));
						return false;
					}
				}
			}
			System.out.println("not played before (gold)");
			addNewBoardPosition();
			return false;
		}
		else {
			for (int a = 0; a < silverPreviousPlays.size(); a++)
			{
				if (arraysEqual(silverPreviousPlays.get(a), allPieces) == true)
				{
					System.out.println("played before");
					if (silverTimesPreviouslyPlayed.get(a) > 2)
					{
						//this is the third time this board position has occurred
						System.out.println("3rd time (silver)");
						return true;
					}
					else {
						silverLastPositionIndex = a;
						System.out.println("time still ok (silver)");
						silverTimesPreviouslyPlayed.set(a, (silverTimesPreviouslyPlayed.get(a) + 1));
						return false;
					}
				}
			}
			System.out.println("not played before (silver)");
			addNewBoardPosition();
			return false;
		}
		
	}

	public void unColorLastSpot (int x, int y)
	{
		if ((x == 2 && y == 2) || (x == 5 && y == 2) || (x == 2 && y == 5) ||
			(x == 5 && y == 5)) //trap squares
		{
			board[x][y].setBackground(new Color(255, 128, 128));
		}
		else
		{
			board[x][y].setBackground(null);
		}
	}

  //checks the piece at each position in two arrays; if all of them are equal, return that the two arrays
	//are equal (since the board stays the same size, it's assumed the arrays compared are the same size)
	public boolean arraysEqual (Piece[][] oldPosition, Piece[][] currentPosition) {
		for (int a = 0; a < oldPosition.length; a++) {
			for (int b = 0; b < oldPosition[0].length; b++)
			{
				//if one is null and the other isn't, they're not equal.
        if((oldPosition[a][b] != null && currentPosition[a][b] == null) || (oldPosition[a][b] == null && currentPosition[a][b] != null))
        {
					System.out.println("one null, one not");
          return false;
        }
        //get and compare color and power level since .equals doesn't work
        else if (oldPosition[a][b] != null && currentPosition[a][b] != null && !oldPosition[a][b].getColor().equals(null) && !currentPosition[a][b].getColor().equals(null) && ((!oldPosition[a][b].getColor().equals(currentPosition[a][b].getColor())) || (oldPosition[a][b].getPowerLevel() != currentPosition[a][b].getPowerLevel())))
				{
					System.out.println("different pieces");
					return false;
				}
			}
		}
		return true;
	}

	//add the current ending board position to the ArrayList of past board positions for the current player
  public void addNewBoardPosition()
  {
	  Piece[][] copy = new Piece[8][8];
	  for (int a = 0; a < 8; a++) 
		{
			for (int b = 0; b < 8; b++)
			{
				if(allPieces[a][b] != null)
				{
					if(allPieces[a][b].getColor().equals("gold"))
          {
            copy[a][b] = new Piece(allPieces[a][b].getAnimal(), allPieces[a][b].getPowerLevel(), "goldSetup");
          }
          else
          {
            copy[a][b] = new Piece(allPieces[a][b].getAnimal(), allPieces[a][b].getPowerLevel(), "silverSetup");
          }
				}
			}
		}
		if (playerState.equals("goldMove") || playerState.equals("goldPushPlace")
		|| playerState.equals("goldPullPlace"))
		{
			goldPreviousPlays.add(copy);
			System.out.println("new position added");
			goldTimesPreviouslyPlayed.add(1);
			goldLastPositionIndex = goldPreviousPlays.size() - 1;
			System.out.println("Last Position Index (gold): " + goldLastPositionIndex);
		}
		else
		{
			silverPreviousPlays.add(copy);
			System.out.println("new position added");
			silverTimesPreviouslyPlayed.add(1);
			silverLastPositionIndex = silverPreviousPlays.size() - 1;	
			System.out.println("Last Position Index (silver): " + silverLastPositionIndex);
		}
  }

	//called at the start so the board position ArrayLists are not empty; adds a Piece[][] with no pieces
	//in any of the spots
	public void addDummyBoardPosition()
  {
	  Piece[][] dummy = new Piece[8][8];
	  for (int a = 0; a < 8; a++) 
		{
			for (int b = 0; b < 8; b++)
			{
        dummy[a][b] = null;
			}
		}
		goldPreviousPlays.add(dummy);
		silverPreviousPlays.add(dummy); 
  }

	public void revertToLastBoardPosition()
	{
		Piece[][] lastBoardPosition = new Piece[8][8];
		if (playerState.equals("goldMove") || playerState.equals("goldPushPlace")
		|| playerState.equals("goldPullPlace"))
		{
			lastBoardPosition = goldPreviousPlays.get(goldLastPositionIndex);
			for (int a = 0; a < allPieces.length; a++) {
				for (int b = 0; b < allPieces[0].length; b++) {
					allPieces[a][b] = lastBoardPosition[a][b];
				}
			}
		}
		else {
			lastBoardPosition = silverPreviousPlays.get(silverLastPositionIndex);
			for (int a = 0; a < allPieces.length; a++) {
				for (int b = 0; b < allPieces[0].length; b++) {
					allPieces[a][b] = lastBoardPosition[a][b];
				}
			}
		}
	}
}