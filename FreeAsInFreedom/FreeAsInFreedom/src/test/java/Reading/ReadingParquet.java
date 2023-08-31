package Reading;

import File.FastParquet;
import File.FastReader;

public class ReadingParquet {
	public static void main(String[]args) {
		//FastParquet reads the file
		FastParquet reader = new FastParquet();
		//loads the parquet file to the reader
		reader.parquetReader("test.parquet");
		//print parquet to console with 500ms interval between each record
		reader.printToConsole(500);
		//print parquet to text file
		reader.printToTxt("test.txt");
		
		//To read those files
		//Split each line into an array based on a delimiter you define
		//Print the first and last fields only
		reader = new FastParquet();
		reader.parquetReader("test.parquet");
		String [] lineArray = reader.nextLineToArray(",", false);
		while(lineArray.length > 0) {
			System.out.println(lineArray[0] + "\t" + lineArray[lineArray.length-1]);
			
			lineArray = reader.nextLineToArray(",", false);
		}
		
	}
}
