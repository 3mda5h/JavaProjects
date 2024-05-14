/*
 * Cryptography - user can choose from 3 different ciphers to encode or decode a message of their choice.
 * Author: Emily MacPherson
 * Date: 11/29/20
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Cryptography implements ActionListener, FocusListener 
{
	//UI stuff
	JFrame frame = new JFrame();
	
	Container container = new Container();
	Container cipherContainer = new Container();
	Container keyContainer = new Container();
	Container encodeContainer = new Container();
	Container spacesContainer = new Container();
	Container nonLettersContainer = new Container();
	
	ButtonGroup cipherGroup = new ButtonGroup();
	ButtonGroup spacesGroup = new ButtonGroup();
	ButtonGroup nonLettersGroup = new ButtonGroup();
	
	JLabel selectCipher = new JLabel("Select cipher");
	JRadioButton scytale = new JRadioButton("Scytale", true);
	JRadioButton caesar = new JRadioButton("Caesar");
	JRadioButton vigenere = new JRadioButton("Vigenère");
	JLabel inputLabel = new JLabel("Input your message");
	JTextField inputField = new JTextField("Type here");
	JLabel keyLabel = new JLabel("Key:");
	JTextField keyField = new JTextField("Type here");
	JButton encode = new JButton("Encode");
	JButton decode = new JButton("Decode");
	JLabel outputLabel = new JLabel("Output:");
	JTextField outputField = new JTextField("");
	JLabel error = new JLabel("");
	JLabel settings = new JLabel("Settings");
	JLabel spacesLabel = new JLabel("Delete spaces when encoding:");
	JRadioButton noSpaces = new JRadioButton("Yes");
	JRadioButton yesSpaces = new JRadioButton("No", true);
	JLabel nonLettersLabel = new JLabel("Delete non-letters when encoding:");
	JRadioButton noNonLetters = new JRadioButton("Yes");
	JRadioButton yesNonLetters = new JRadioButton("No", true);
	
	
	public static void main(String[] args)
	{
		new Cryptography(); 
	}
	
	//creates window & user interface 
	public Cryptography()
	{
		frame.add(container, BorderLayout.PAGE_START);
		container.setLayout(new GridLayout(12,1));
		
		selectCipher.setHorizontalAlignment(JLabel.CENTER);
		selectCipher.setFont(new Font("Courier", Font.BOLD, 17));
		selectCipher.setOpaque(true);
		selectCipher.setBackground(new Color(205, 205, 205));
		
		container.add(selectCipher);
		
		container.add(cipherContainer);
		cipherContainer.setLayout(new GridLayout(1,3));
		
		cipherGroup.add(scytale);
		cipherGroup.add(caesar);
		cipherGroup.add(vigenere);
		scytale.setFont(new Font("Courier", Font.PLAIN, 16));
		cipherContainer.add(scytale);
		caesar.setFont(new Font("Courier", Font.PLAIN, 16));
		cipherContainer.add(caesar);
		vigenere.setFont(new Font("Courier", Font.PLAIN, 16));
		cipherContainer.add(vigenere);
		
		inputLabel.setHorizontalAlignment(JLabel.CENTER);
		inputLabel.setFont(new Font("Courier", Font.BOLD, 17));
		inputLabel.setOpaque(true);
		inputLabel.setBackground(new Color(205, 205, 205));
		container.add(inputLabel);
		
		inputField.setFont(new Font("Courier", Font.PLAIN, 15));
		inputField.setForeground(new Color(150, 150, 150));
		inputField.addFocusListener(this);
		container.add(inputField);
		
		container.add(keyContainer);
		keyContainer.setLayout(new FlowLayout());
		keyLabel.setPreferredSize(new Dimension(100, 30));
		keyLabel.setFont(new Font("Courier", Font.PLAIN, 16));
		keyLabel.setOpaque(true);
		keyLabel.setBackground(new Color(205, 205, 205));
		keyLabel.setHorizontalAlignment(JLabel.CENTER);
		keyContainer.add(keyLabel);
		keyField.setPreferredSize(new Dimension(280, 30));
		keyField.setForeground(new Color(150, 150, 150));
		keyField.setFont(new Font("Courier", Font.PLAIN, 15));
		keyField.addFocusListener(this);
		keyContainer.add(keyField);
		
		container.add(encodeContainer);
		encodeContainer.setLayout(new GridLayout(1,2));
		encode.setFont(new Font("Courier", Font.BOLD, 17));
		encode.setBackground(new Color(255, 186, 186));
		encode.addActionListener(this);
		encodeContainer.add(encode);
		decode.setFont(new Font("Courier", Font.BOLD, 17));
		decode.setBackground(new Color(186, 255, 186));
		decode.addActionListener(this);
		encodeContainer.add(decode);
		
		outputLabel.setHorizontalAlignment(JLabel.CENTER);
		outputLabel.setFont(new Font("Courier", Font.BOLD, 17));
		outputLabel.setOpaque(true);
		outputLabel.setBackground(new Color(205, 205, 205));
		container.add(outputLabel);
		
		outputField.setFont(new Font("Courier", Font.PLAIN, 15));
		container.add(outputField);
		
		error.setFont(new Font("Courier", Font.PLAIN, 15));
		error.setForeground(new Color(145, 0, 0));
		error.setVisible(false);
		container.add(error);
		
		settings.setHorizontalAlignment(JLabel.CENTER);
		settings.setFont(new Font("Courier", Font.BOLD, 17));
		settings.setOpaque(true);
		settings.setBackground(new Color(205, 205, 205));
		container.add(settings);
		
		container.add(spacesContainer);
		spacesContainer.setLayout(new FlowLayout());
		spacesLabel.setFont(new Font("Courier", Font.PLAIN, 15));
		spacesContainer.add(spacesLabel);
		
		spacesGroup.add(noSpaces);
		spacesGroup.add(yesSpaces);
		noSpaces.setFont(new Font("Courier", Font.PLAIN, 13));
		spacesContainer.add(noSpaces);
		yesSpaces.setFont(new Font("Courier", Font.PLAIN, 13));
		spacesContainer.add(yesSpaces);
		
		container.add(nonLettersContainer);
		nonLettersContainer.setLayout(new FlowLayout());
		nonLettersLabel.setFont(new Font("Courier", Font.PLAIN, 15));
		nonLettersContainer.add(nonLettersLabel);
		
		nonLettersGroup.add(noNonLetters);
		nonLettersGroup.add(yesNonLetters);
		noNonLetters.setFont(new Font("Courier", Font.PLAIN, 13));
		nonLettersContainer.add(noNonLetters);
		yesNonLetters.setFont(new Font("Courier", Font.PLAIN, 13));
		nonLettersContainer.add(yesNonLetters);
		
		frame.setSize(460, 530);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	//called when a button is clicked
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		boolean nonNumber = false;
		boolean nonLetter = false;
		//if the user has entered something in both the message and key text boxes
		//text is black when user has entered something
		if(inputField.getForeground().equals(new Color(0, 0, 0)) && keyField.getForeground().equals(new Color(0, 0, 0)))
		{
			error.setVisible(false);
			//if the key contains any non-numbers
			for (int i = 0; i < keyField.getText().length(); i++) 
			{
				if((int)keyField.getText().charAt(i) < 48 || (int)keyField.getText().charAt(i) > 57)
				{
					nonNumber = true;
				}
			}
			//if key contains any non-letters
			for (int i = 0; i < keyField.getText().length(); i++) 
			{
				if((int)keyField.getText().charAt(i) < 65 || (int)keyField.getText().charAt(i) > 122 || (int)keyField.getText().charAt(i) > 90
						&& (int)keyField.getText().charAt(i) < 97)
				{
					nonLetter = true;
				}
			}
			//if encode button is pressed
			if(event.getSource().equals(encode))
			{
				if(scytale.isSelected() == true)
				{
					if(nonNumber == true || keyField.getText().equals("0"))
					{
						error.setText("A scytale key must be a number greater than 0");
						error.setVisible(true);
					}
					else
					{
						if(keyField.getText().equals("1"))
						{
							error.setText("A key of 1 is not remcommended for scytale");
							error.setVisible(true);			
						}
						outputField.setText(scytaleEncoder(inputField.getText(), Integer.parseInt(keyField.getText())));
					}	
				}
				if(caesar.isSelected() == true)
				{
					if(nonNumber == true || Integer.parseInt(keyField.getText()) > 26)
					{
						error.setText("A caesar key must be a number less than 26");
						error.setVisible(true);
					}
					else
					{
						if(keyField.getText().equals("0") || keyField.getText().equals("26"))
						{
							error.setText("A key of 0 or 26 is not reccomended for caesar");
							error.setVisible(true);			
						}
						outputField.setText(caesar("encode", inputField.getText(), Integer.parseInt(keyField.getText())));
					}
				}
				if(vigenere.isSelected() == true)
				{
					if(nonLetter == true)
					{
						error.setText("A vigenere key must be letters only & no spaces");
						error.setVisible(true);
					}
					else
					{
						outputField.setText(vigenere("encode", inputField.getText(), keyField.getText()));
					}
				}
				
			}
			//if decode button is pressed
			if(event.getSource().equals(decode))
			{
				if(scytale.isSelected() == true)
				{
					if(nonNumber == true || keyField.getText().equals("0"))
					{
						error.setText("A scytale key must be a number greater than 0");
						error.setVisible(true);
					}
					else
					{
						if(keyField.getText().equals("1"))
						{
							error.setText("A key of 1 is not remcommended for scytale");
							error.setVisible(true);			
						}
						outputField.setText(scytaleDecoder(inputField.getText(), Integer.parseInt(keyField.getText())));
					}				
				}
				if(caesar.isSelected() == true)
				{
					if(nonNumber == true || Integer.parseInt(keyField.getText()) > 26)
					{
						error.setText("A caesar key must be a number less than 26");
						error.setVisible(true);
					}
					else
					{
						if(keyField.getText().equals("0") || keyField.getText().equals("26"))
						{
							error.setText("A key of 0 or 26 is not reccomended for caesar");
							error.setVisible(true);			
						}
						outputField.setText(caesar("decode", inputField.getText(), Integer.parseInt(keyField.getText())));				
					}
				}
				if(vigenere.isSelected() == true)
				{
					if(nonLetter == true)
					{
						error.setText("A vigenere key must be letters only & no spaces");
						error.setVisible(true);
					}
					else
					{
						outputField.setText(vigenere("decode", inputField.getText(), keyField.getText()));
					}
				}
			}	
		}
		//if there's no text in the input or key fields. 
		else
		{
			error.setText("error: you're missing a message or a key");
			error.setVisible(true);
		}
	}
	
	//detects when user clicks text field and removes "type here" 
	@Override
	public void focusGained(FocusEvent event) 
	{
		if(event.getSource().equals(inputField) && inputField.getForeground().equals(new Color(150, 150, 150)))
		{
			inputField.setText(null);
			inputField.setForeground(new Color(0, 0, 0));
		}
		if(event.getSource().equals(keyField) && keyField.getForeground().equals(new Color(150, 150, 150)))
		{
			keyField.setText(null);
			keyField.setForeground(new Color(0, 0, 0));
		}
	}
	
	//detects when user clicks away and re-adds "type here" if there's still no text
	@Override
	public void focusLost(FocusEvent event) 
	{
		if(event.getSource().equals(inputField) && inputField.getText().equals(""))
		{
			inputField.setText("Type here");
			inputField.setForeground(new Color(150, 150, 150));
		}
		if(event.getSource().equals(keyField) && keyField.getText().equals(""))
		{
			keyField.setText("Type here");
			keyField.setForeground(new Color(150, 150, 150));
		}	
	}

	//encodes with scytale cipher 
	public String scytaleEncoder(String message, int numOfRows)
	{
		//will delete spaces or special characters if user wants
		message = modifyMessage(message);
		String output = "";
		int numOfColumns = message.length() / numOfRows;
		int remainder = message.length() % numOfRows;
		//if there is a remainder, we need an extra column for the extra letters and @ signs
		if(remainder > 0)
		{
			numOfColumns++;
		}
		char[][] encoder = new char[numOfRows][numOfColumns];
		int charInString = 0;
		for (int row = 0; row < numOfRows; row++) 
		{
			for (int column = 0; column < numOfColumns - 1; column++) 
			{
				encoder[row][column] = message.charAt(charInString);
				output += encoder[row][column] + " ";
				charInString++;
			}
			//the @ sign will be used if we have ran out of letters but still need to fill the column
			//the number of rows filled with letters in the last column = the remainder
			//so if we are in a row greater than (or equal to cause the row count starts at 0) the remainder we are out of letters and must use @
			if(row >= remainder && remainder != 0)
			{
				encoder[row][numOfColumns - 1] = '@';
			}
			else
			{
				encoder[row][numOfColumns - 1] = message.charAt(charInString);
				charInString++;
			}
			output += encoder[row][numOfColumns - 1] + " ";
			//prints grid for debugging. Don't really need it anymore but I'll leave it cause it's nice to see
			System.out.println(output);
			System.out.println();
			output = "";
		}
		
		//go through the array in reverse (down columns then across rows) to get the encoded message
		for (int column = 0; column < numOfColumns; column++)
		{
			for (int row = 0; row < numOfRows; row++)
			{
				output += encoder[row][column];
			}
		}
		return output;
	}
	
	//decodes scytale cipher
	public String scytaleDecoder(String message, int numOfRows)
	{
		String output = "";
		int numOfColumns = message.length() / numOfRows;
		int remainder = message.length() % numOfRows;
		if(remainder > 0)
		{
			numOfColumns++;
		}
		char[][] decoder = new char[numOfRows][numOfColumns];
		int charInString = 0;
		//put the encoded message in the array going down columns then across rows
		for (int column = 0; column < numOfColumns; column++) 
		{
			for (int row = 0; row < numOfRows; row++) 
			{
				decoder[row][column] = message.charAt(charInString);
				charInString++;
			}
		}
		//read the array going across rows then down columns to get the decoded message
		for (int row = 0; row < numOfRows; row++)
		{
			for (int column = 0; column < numOfColumns; column++)
			{
				if(decoder[row][column] != '@')
				{
					output += decoder[row][column];
				}
			}
		}
		return output;
	}
	
	//encodes or decodes with caesar cipher
	public String caesar(String type, String message, int key)
	{
		if(type.equals("encode"))
		{
			message = modifyMessage(message);
		}
		String output = "";
		for (int i = 0; i < message.length(); i++) 
		{
			if(type.equals("encode"))
			{
				output += letterShiftEncode((int)message.charAt(i), key);
			}
			else if(type.equals("decode"))
			{
				output += letterShiftDecode((int)message.charAt(i), key);
			}
		}
		return output;
	}
	
	//encodes or decodes with vigenere cipher
	public String vigenere(String type, String message, String keyWord)
	{
		if(type.equals("encode"))
		{
			message = modifyMessage(message);
		}
		String output = ""; 
		String keyWordRepeated = "";
		int keyWordIndex = 0;
		for (int i = 0; i < message.length() ; i++) 
		{
			if(keyWordIndex < keyWord.length())//make sure index is still within range
			{
				if((int)message.charAt(i) > 64 && (int)message.charAt(i) < 91 || (int)message.charAt(i) > 96 && (int)message.charAt(i) < 123)//if it's a letter
				{
					keyWordRepeated += keyWord.charAt(keyWordIndex);
					keyWordIndex++;
				}
				else
				{
					keyWordRepeated += ' ';
				}
			}
			//if it's not in range, set it back to 0
			else
			{
				keyWordIndex = 0;
				i --;//keep i the same
			}
		}
		//prints message with keywords lined up, for debugging 
		System.out.println(message);
		System.out.println(keyWordRepeated);
		int intKey = 69;
		for (int i = 0; i < message.length(); i++) 
		{
			//capital letter
			if((int)keyWordRepeated.charAt(i) > 64 && (int)keyWordRepeated.charAt(i) < 91)
			{
				intKey = (int)keyWordRepeated.charAt(i) - 65;
			}
			//lower case letter
			else
			{
				intKey = (int)keyWordRepeated.charAt(i) - 97;
			}
			if(type.equals("encode"))
			{
				output += letterShiftEncode((int)message.charAt(i), intKey);
			}
			else
			{
				output += letterShiftDecode((int)message.charAt(i), intKey);
			}
			
		}
		return output;
	}
	
	//shifts the letter to the right by a certain amount
	public char letterShiftEncode(int realAscii, int key)
	{
		int newAscii = realAscii + key;
		//uhh so this might be a really dumb way of doing the looping thing, but it's the only one I can think of soo
		//if the letter is uppercase:
		if(realAscii > 64 && realAscii < 91)
		{
			//if the new ASCII value will be higher than Z's
			if(newAscii > 90)
			{
				//calculate how much of the key it takes to get to Z, then add what's left of the key to 64, one
				//lower than A's ASCII value. This is to account of the fact that the jump from A to Z counts as 1
				newAscii = (key - (90 - realAscii)) + 64; 
			}
		}
		//if the letter is lowercase
		else if(realAscii > 96 && realAscii < 123)
		{
			//if the new ASCII value is higher than z's
			if(newAscii > 122)
			{
				//calculate how much of the key it takes to get to z, then add what's left of the key to 96, one
				//before a's ASCII value, same reason as last time
				newAscii = (key - (122 - realAscii)) + 96; 
			}
		}
		//if character is non letter, like ? or . or a space, keep it the same.
		else
		{
			newAscii = realAscii;
		}
		char output = (char)newAscii;
		return output; 
	}
	
	//shifts the letter to the left by a certain amount
	public char letterShiftDecode(int realAscii, int key)
	{
		int newAscii = realAscii - key;
		//if the letter is uppercase
		if(realAscii > 64 && realAscii < 91)
		{
			//if the new ASCII value will be lower than A's
			if(newAscii < 65)
			{
				//calculate how much of the key it takes to get to A, then minus what's left of the key from 91, Z's ASCII value plus one
				newAscii = 91 - (key - (realAscii - 65)); 
			}
		}
		//if the letter is lowercase
		else if(realAscii > 96 && realAscii <123)
		{
			//if the new ASCII value is higher lower than a's
			if(newAscii < 97)
			{
				//calculate how much of the key it takes to get to A, then minus what's left of the key from 123, z's ASCII value plus one 
				newAscii = 123 - (key - (realAscii - 97)); 
			}
		}
		else if(realAscii == 32)
		{
			newAscii = 32;
		}
		else
		{
			newAscii = realAscii;
		}
		char output = (char)newAscii;
		return output; 
	}
	
	//if the user wants, this method goes through and deletes spaces and/or non letter characters
	public String modifyMessage(String message)
	{
		String newMessage = "";
		if(noSpaces.isSelected())
		{
			//only adds non-space characters to the new message, then makes the new message the message
			for(int i = 0; i < message.length(); i++) 
			{
				if(message.charAt(i) != ' ')
				{
					newMessage += message.charAt(i);
				}
			}
			message = newMessage;
			newMessage = "";
		}
		if(noNonLetters.isSelected() == true)
		{
			//only adds letters and spaces to new message, then makes the new message the message
			for(int i = 0; i < message.length(); i++) 
			{
				if((int)message.charAt(i) > 64 && (int)message.charAt(i) < 191 || (int)message.charAt(i) < 123
				&& (int)message.charAt(i) > 96 || message.charAt(i) == ' ')
				{
					newMessage += message.charAt(i);
				}
			}
			message = newMessage;
		}
		return message;
	}
}
