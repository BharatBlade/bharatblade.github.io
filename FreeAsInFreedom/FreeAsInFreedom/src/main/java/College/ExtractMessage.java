package College;
import java.util.*;
import java.io.*;
public class ExtractMessage {
	//decodes the message from a file given
	public static void main(String[]args) throws FileNotFoundException{
		//asks user for file until given an
		//existing file
		Scanner in = fileCheck();
		
		//determines the number of elements needed for the array
		//based on number of integers taken from input file
		//and placed in a temp file
		int size = retrieveNumbers(in);
		
		//takes the integers from the temp file
		//and subtracts the offset from each
		//and puts them into an array with a length
		//of the number of integers in input file
		decodeMessage(new int[size], new Scanner(new File("temp.txt")));
	}
	
	//asks user for file until given an existing file
	//returns a Scanner given the correct file
	public static Scanner fileCheck() throws FileNotFoundException{
		Scanner input = new Scanner(System.in);
		System.out.print("Enter file: ");
		String s = input.nextLine();
		if(!new File(s).exists()){
			System.out.println("File does not exist, enter file: ");
			s = input.nextLine();
		}
		input.close();
		return new Scanner(new File(s));
	}
	
	//Uses the Scanner given to take every integer from input file
	//Integers taken are put into a "temp" file
	//The number of integers taken is returned
	//This number determines the size of the array of integers
	public static int retrieveNumbers(Scanner in) throws FileNotFoundException{
		PrintStream out = new PrintStream(new File("temp.txt"));
		int size = 0;
		while(in.hasNext()){
			if(in.hasNextInt()){
				out.print(in.nextInt() + " ");
				size++;
			}
			else{
				in.next();
			}	
		}
		in.close();
		out.close();
		return size;
	}
	
	//Scans each integer from the "temp" file
	//and put that number minus the offset into the array
	//Then prints out the char equivalent for each integer
	public static void decodeMessage(int [] a, Scanner in){
		for(int i = 0; i < a.length; i++){
			a[i] = in.nextInt() - a.length;
		}
		for(int i = 0; i < a.length; i++){
			System.out.print((char)(a[i]));
		}
		in.close();
		new File("temp.txt").delete();
	}
}