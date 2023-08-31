package Reading;

import File.FastGzip;

public class ReadingGzip {
	public static void main(String[]args) {
		//FastGzip reads the file
		FastGzip gzip = new FastGzip("test.csv.gz");
		
		//To temporarily decompress and read a gz file and print the contents to the console
		String line = gzip.nextLine();
		while(line != null) {
			System.out.println(line);
			
			line = gzip.nextLine();
		}
		gzip.close();
		
		//To read those files
		//Split each line into an array based on a delimiter you define
		//Print the first and last fields only
		gzip = new FastGzip("test.csv.gz");
		String [] lineArray = gzip.reader.nextLineToArray(",", false);
		while(lineArray.length > 0) {
			System.out.println(lineArray[0] + "\t" + lineArray[lineArray.length-1]);
			
			lineArray = gzip.nextLineToArray(",", false);
		}
		gzip.close();
		
	}
}
