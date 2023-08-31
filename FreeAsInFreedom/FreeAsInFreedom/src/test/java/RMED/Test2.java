package RMED;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Test2 {
	public static void main(String[]args) throws Exception {
		URL url = new URL("https://www.bing.com/search?q=151+WOODGATE+DR+JOHNSTOWN+null+OH+430311198");		
		InputStream inputStream = url.openStream();
		Scanner scanner = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
		String out = scanner.next();
		scanner.close();
		inputStream.close();
		System.out.println(out);
	}
}
