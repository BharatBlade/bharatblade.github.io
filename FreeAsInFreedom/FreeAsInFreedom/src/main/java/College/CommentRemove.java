package College;
import java.util.*;
import java.io.*;
public class CommentRemove {
	
	//Removes all comments of the code without eliminating code
	//Can handle comments with spaces or no spaces after "//" or "/*"
	//Keeps whatever indentation was created by the user such as spaces
	public static void main(String[]args) throws FileNotFoundException{
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the input file name: ");
		File file = new File(in.nextLine());
		
		//Checks if the file exists
		//If it doesn't asks file name again
		while(!file.exists()){
			System.out.println("\"" + file.getName() + "\"" + " does not exist.");
			System.out.print("Enter the input file name: ");			
			file = new File(in.nextLine());
		}
		
		//Create a "<filename>.cln" file to
		//store the uncommented code
		PrintStream out = new PrintStream(file.getName()+".cln");
		
		//Converts the contents of commented code
		//Into a string
		String temp = fileToString(file);
		
		//Prints the uncommented code into the ".cln" file
		out.println(commentRemover(temp));
	}
	
	//Converts uncommented code from a file into a string
	//with the new line escape sequences
	public static String fileToString(File file) throws FileNotFoundException{
		Scanner code = new Scanner(file);
		String temp = "";
		while(code.hasNextLine()){
			temp += code.nextLine();
			temp += "\n";
		}
		return "";
	}
	
	//Takes a string with the contents of the file
	//and removes multiline comments
	//Then the possible empty new line at the top of code
	//when the header is removed
	//Then the single line comments are removed
	public static String commentRemover(String temp){
		while(temp.indexOf("/*") > -1){
			temp = temp.substring(0, temp.indexOf("/*")) + temp.substring(temp.indexOf("*/")+2);
		}
		if(temp.substring(0,1).equals("\n")){
			temp = temp.substring(1);
		}
		while(temp.indexOf("//") > -1){
			temp = temp.substring(0, temp.indexOf("//")) + temp.substring(temp.indexOf("\n", temp.indexOf("//")));
		}
		return temp;
	}
}