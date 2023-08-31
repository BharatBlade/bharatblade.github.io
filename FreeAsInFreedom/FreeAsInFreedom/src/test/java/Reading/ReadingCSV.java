package Reading;

import File.FastCSV;
import File.FastReader;

public class ReadingCSV {
	public static void main(String[]args) {
		
		//FastReader reads the file
		FastReader reader = new FastReader("test.csv");
		
		//To read a text or CSV file and print the contents to the console
		String line = reader.nextLine();
		while(line != null) {
			System.out.println(line);
			
			line = reader.nextLine();
		}
		reader.close();
		
		//To read those files
		//Split each line into an array based on a delimiter you define
		//Print the first and last fields only
		reader = new FastReader("test.csv");
		String [] lineArray = reader.nextLineToArray(",", false);
		while(lineArray.length > 0) {
			System.out.println(lineArray[0] + "\t" + lineArray[lineArray.length-1]);
			
			lineArray = reader.nextLineToArray(",", false);
		}
		reader.close();

		//FastCSV contains methods to help process the data from FastReader if needed
		FastCSV csv = new FastCSV();
		

	}
}
