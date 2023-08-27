

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CustomWriter {
	public File file;
	public FileWriter fw;
	public BufferedWriter bw;
	
	public CustomWriter(File f) {
		try {
			file = f;
			fw = new FileWriter(f);
			bw = new BufferedWriter(fw);
		} catch (Exception e) {
			fw = null; bw = null;
		}
		
	}
	
	public CustomWriter(String str) {
		try {
			file = new File(str);
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
		} catch (Exception e) {
			fw = null; bw = null;
		}
		
	}
	
	public void print(String str) {
		try {
			bw.write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void println(String str) {
		try {
			bw.write(str + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			bw.flush();
			fw.flush();
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
