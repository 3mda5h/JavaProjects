/*
 * Binary Translator: converts user's number to decimal or binary, depending on their original number
 * Author: Emily MacPherson
 * Date: 9/23/20
 * Not exactly sure how I'm supposed to cite, but I used this link to figure out how to check
 * for certain characters in a string:
 * https://www.tutorialspoint.com/searching-characters-and-substring-in-a-string-in-java
 */


import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class BinaryTranslator 
{
	String input = "cats";
	boolean running = true;
	Scanner scanner = new Scanner(System.in);
	String numberInput = "";
	
	public static void main(String[] args) 
	{
		new BinaryTranslator();
	}
	
	public BinaryTranslator()
	{
		//everything's in a while loop so it can start over
		while(running == true)
		{	
			System.out.println("Please enter \"file\" to enter a file or \"input\" to use the console.");
			input = scanner.nextLine();
			boolean retry = true;
			while(retry == true)
			{
				if(input.equals("file")) //input from a file
				{
					boolean trying = true;
					while(trying == true)	
					{	
						System.out.println("Enter your file name.");
						input = scanner.nextLine();
						try 
						{
							Scanner fileScanner = new Scanner(new File(input));
							numberInput = fileScanner.nextLine();
							trying = false;
						}
						catch(IOException ex)
						{
							System.out.println("File not found.");
						}
					}
					retry = false;
				}
				else if(input.equals("input")) //input from the console
				{
					System.out.println("Enter your number.");
					numberInput = scanner.nextLine();
					retry = false;
				}
				else //when the person is stinky and doesn't give an actual answer
				{
					System.out.println("That's not an answer dude.");
					input = scanner.nextLine();
				}
			}
			
			if(numberInput.contains("2") == true || numberInput.contains("3") == true || numberInput.contains("4") == true
					|| numberInput.contains("5") == true || numberInput.contains("6") == true || numberInput.contains("7") == true
					|| numberInput.contains("8") == true || numberInput.contains("9") == true)
				//there is definitely a better way to do that but whatever
			{
				System.out.println("Looks like your number is a decimal");
				DecimaltoBinary();
			}
			else
			{
				boolean retry2 = true;
				while (retry2 == true)
				{
					System.out.println("If translating from decimal to binary type \"dtb\"");
					System.out.println("if translating from binary to decimal type \"btd\"");
					input = scanner.nextLine();
					if(input.equals("dtb")) //decimal to binary
					{
						DecimaltoBinary();
						retry2 = false;
					}
					else if(input.equals("btd")) //binary to decimal
					{
						int answer = 0;
						int power = numberInput.length() - 1;
						for(int a = 0; a < numberInput.length(); a++)
						{
							if(numberInput.charAt(a) == '1')
							{
								answer = answer + (int)(Math.pow(2, power));
							}
							power --;
						}
						System.out.println("Decimal answer is: " + answer);
						System.out.println("Would you like to convert another number?(yes/no)");
						input = scanner.nextLine();
						if(input.equals("no"))
						{
							System.out.println("Okay, adiós");
							scanner.close();
							System.exit(1);
						}
						retry2 = false;
					}
					else //when person doesn't give good answer
					{
						System.out.println("Pardon?");
					}
				}
			}
		}
	}
	//converts decimal to binary (duh), I made a sperate method cause I use the code twice
	void DecimaltoBinary()
	{
		String answer = "";
		int number = Integer.parseInt(numberInput);
		while(number > 0)
		{
			if(number % 2 == 1)
			{
				answer = "1" + answer;
			}
			else 
			{
				answer = "0" + answer;
			}
			number = number / 2;
		}
		System.out.println("The binary conversion is: " + answer);
		System.out.println("Would you like to convert another number?(yes/no)");
		input = scanner.nextLine();
		if(input.equals("no"))
		{
			System.out.println("Okay, adiós");
			scanner.close();
			System.exit(1);
		}
	}
}


