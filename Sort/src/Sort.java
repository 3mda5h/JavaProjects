/*
 * Sort - sorts an input file of numbers into ascending numerical order. user can choose between 4 sorting methods and compare sorting times
 * Author - Emily MacPherson
 * Date - 10/30/20 
 * if it matters, I used this video https://youtu.be/Fiot5yuwPAg to understand how quicksort works (didn't really use the code from it, just watched the animation)
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Sort 
{
	Scanner scanner = new Scanner(System.in);
	String input;
	Scanner fileInput;
	int[] inputArray;
	long startTime;
	
	public static void main(String[] args) 
	{
		new Sort();
	}
	
	//constructor
	public Sort()
	{
		System.out.println("Enter a number for the input file.");
		System.out.println("1: input1.txt, 2: input2.txt, 3: input3.txt, 4: input3.txt");
		input = scanner.nextLine();
		
		//checks if input is valid, if not, keeps asking
		if(input.length() != 1 || input.charAt(0) != '1' && input.charAt(0) != '2'
				 && input.charAt(0) != '3'  && input.charAt(0) != '4')
		{
			System.out.println("Enter a 1, 2, 3, or 4");
			while(input.length() != 1 || input.charAt(0) != '1' && input.charAt(0) != '2'
					 && input.charAt(0) != '3'  && input.charAt(0) != '4')
			{
				input = scanner.nextLine();
			}
		}
		
		try 
		{
			fileInput = new Scanner(new File("input" + input.charAt(0) + ".txt"));
		} 
		catch (FileNotFoundException ex) 
		{
			ex.printStackTrace();
			System.out.println("File no found");
			System.exit(0);
		}
		String inFile = fileInput.nextLine();
		String[] inputStringArray = inFile.split(",");
		inputArray = new int[inputStringArray.length];
		//makes an array of ints from the array of strings
		for (int i = 0; i < inputStringArray.length; i++) 
		{
			inputArray[i] = Integer.parseInt(inputStringArray[i]);
		}
		System.out.println("Enter a number for the sorting method.");
		System.out.println("1: Bubble, 2: Selection, 3: Table, 4: Quicksort");
		input = scanner.nextLine();
		//checks if input is valid
		if(input.length() != 1 || input.charAt(0) != '1' && input.charAt(0) != '2'
				 && input.charAt(0) != '3' && input.charAt(0) != '4')
		{
			System.out.println("Enter a 1, 2, 3 or 4");
			while(input.length() != 1 || input.charAt(0) != '1' && input.charAt(0) != '2'
					 && input.charAt(0) != '3' && input.charAt(0) != '4')
			{
				input = scanner.nextLine();
			}
		}
		System.out.println("Sorting...");
		startTime = System.currentTimeMillis();
		if(input.equals("1"))
		{
			inputArray = bubbleSort(inputArray);
		}
		if(input.equals("2"))
		{
			inputArray = selectionSort(inputArray);
		}
		if(input.equals("3"))
		{
			inputArray = tableSort(inputArray);
		}
		if(input.equals("4"))
		{
			inputArray = quickSort(inputArray, 0, inputArray.length - 1);
		}
		long totalTime = System.currentTimeMillis() - startTime;
		System.out.println("Total Sort Time: " + totalTime + " miliseconds");
		System.out.println("Writing file...");
		PrintWriter pw;
		//writes out put file
		try 
		{
			pw = new PrintWriter(new FileWriter(new File("output.txt")));
			String output = "";
	 		for (int i = 0; i < inputArray.length; i++) 
			{
				output += inputArray[i] + ", ";
			}
	 		output += "\nTotal Sort Time: " + totalTime + " miliseconds";
	 		pw.write(output);
	 		pw.close();
		} 
		catch (IOException ex) 
		{
			ex.printStackTrace();
			System.exit(0);
		}
		System.out.println("File writen");
		System.exit(0);
		
		
	}
	
	//compare pares of numbers, higher one goes right
	int[] bubbleSort(int[] array)
	{
		int currentEnd = array.length;
		for (int j = 0; j < array.length; j++) 
		{
			for (int i = 0; i < currentEnd - 1; i++) 
			{
				//if left is higher
				if(array[i] > array[i + 1])
				{
					//swap
					int temp = array[i];
					array[i] = array[i + 1];
					array[i + 1] = temp;
					
				}
			}
			currentEnd --; //current end is the end of the array minus the highest ones we've already sorted
		}
		return array;
	}
	
	//find smallest number, move it to the front
	int[] selectionSort(int[] array)
	{
		for (int j = 0; j < array.length; j++) 
		{
			int smallestNumber = array[j];
			int smallestIndex = j;
			for (int i = j; i < array.length; i++) 
			{
				if(array[i] < smallestNumber)
				{
					smallestNumber = array[i];
					smallestIndex = i;
				}
			}
			int temp = array[smallestIndex];
			array[smallestIndex] = array[j];
			array[j] = temp;
		}
		return array;
	}
	
	//tally how often you see a number, print out that number of times
	int[] tableSort(int[] array)
	{
		int[] tally = new int[1001];
		for (int i = 0; i < array.length; i++) 
		{
			tally[array[i]]++;
		}
		int count = 0;
		//i keeps track of the actual number
		for (int i = 0; i < tally.length; i++) 
		{
			//j keeps track of how many times we've seen that number
			for (int j = 0; j < tally[i]; j++) 
			{
				array[count] = i;
				count++; 
			}
		}
		return array;
	}
	
	
	//a recursive function that keeps calling itself until there are no more values to sort
	int[] quickSort(int[] array, int start, int end)
	{
		//we only need to sort if there is more than one item left
		if(end - start >= 1) 
		{
			int partitionIndex = partition(array, start, end);
			//Recursively calls quickSort, once for the left half of the pivot location, and again for the right half
			quickSort(array, start, partitionIndex - 1); //left half
			quickSort(array, partitionIndex + 1, end); //right half
		}
		return array;
	}
	
	private int partition(int[] array, int start, int end)
	{
		//pivot will always be the last number in the partition, cause why not (it can be anything) 
		int pivot = array[end];
		int lower = start;
		int upper = end;
		//Iterate until upper and lower indexes meet
		while(lower < upper)
		{
			if(array[lower] >= pivot)
			{
				//we want to swap, but we need to find something to swap with that is less than the pivot value
				//if we never find anything to swap, then upper and lower will eventually be equal and we need to stop
				while(upper != lower)
				{
					//if it's smaller than the pivot value, we can swap
					if(array[upper - 1] < pivot)
					{
						int temp = array[lower];
						array[lower] = array[upper - 1];
						array[upper - 1] = temp;
						break;
					}
					//if not, keep looking left
					else
					{
						upper --;
					}
				}
				
			}
			lower ++;
		}
		//in the end, you put the pivot value in the pivot location (the place where the upper and lower indexes meet)
		int temp = array[end];
		array[end] = array[upper];
		array[upper] = temp;
		return upper; //returns pivot location
	}
	
}
