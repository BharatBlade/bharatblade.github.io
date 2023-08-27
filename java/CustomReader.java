

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CustomReader {
	File file;
	FileReader fr;
	BufferedReader br;
	
	public CustomReader(File f) {
		try {
			file = f;
			fr = new FileReader(f);
			br = new BufferedReader(fr);
		} catch (Exception e) {
			fr = null; br = null;
		}
	}
	
	public CustomReader(String str) {
		try {
			file = new File(str);
			fr = new FileReader(file);
			br = new BufferedReader(fr);
		} catch (Exception e) {
			fr = null; br = null;
		}
	}
	
	public int nextChar() {
		try {
			return br.read();
		} catch (Exception e) {
			return -1;
		}
	}
	
	public String nextLine() {
		try {
			return br.readLine();
		} catch (Exception e) {
			return null;
		}
	}
	
	public void close() {
		try {
			br.close();
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
