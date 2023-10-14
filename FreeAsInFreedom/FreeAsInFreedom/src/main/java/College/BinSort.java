package College;

import java.io.*;
import java.util.*;
public class BinSort {
	
	/*Sorts numbers of an unsorted list into "bins" by
	comparing the ones, tens... digit of each number and putting
	them into a bin based on the value of that digit. Then repeating
	until there are no more digits to compare in the largest number*/
	public static void main(String[]args) throws FileNotFoundException{
		
		/*Takes the input file, and the stores
		the most digits in a number in a variable
		and the number of values to be sorted*/
		Scanner in = new Scanner(new File(args[0]));
		int maxDigits = in.nextInt();
		int size = in.nextInt();
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		
		extractValues(in, numbers);
		numbers = binSort(maxDigits, size, numbers);
		
		//Prints the sorted array
		System.out.println(numbers);
	}
	
	//Puts the numbers of the unsorted list into an ArrayList
	//and prints the array
	public static void extractValues(Scanner in, ArrayList<Integer> numbers){
		while(in.hasNextInt())
			numbers.add(in.nextInt());
		in.close();
		System.out.println(numbers);
	}
	
	/*creates an array of 10 ArrayLists or "bins" for numbers 0-9
	adds each number to its corresponding bin based on the digit
	compared e.g. when comparing the ones digit, a number like 
	90 will be placed in the first "0" bin
	then the numbers sorted into these bins are put into a new
	ArrayList. These numbers are then sorted again with 
	comparisons done in the tens place. Continues to do this 
	for the maximum number of digits in the largest number
	Returns the sorted array at the end*/
	public static ArrayList<Integer> binSort(int maxDigits, int size, ArrayList<Integer> numbers){
		for(int i = 0; i <= maxDigits; i++){
			ArrayList<Integer> [] bins = (ArrayList<Integer>[]) new ArrayList [10];
			for(int j = 0; j < bins.length; j++)
				bins[j] = new ArrayList<Integer>();
			for(int j = 0; j < size; j++)
				bins[(int)(numbers.get(j)/Math.pow(10, i))%10].add(numbers.get(j));
			numbers = new ArrayList<Integer>();
			for(int j = 0; j < bins.length; j++)
				for(int k = 0; k < bins[j].size(); k++)
					numbers.add(bins[j].get(k));
		}
		return numbers;
	}
}