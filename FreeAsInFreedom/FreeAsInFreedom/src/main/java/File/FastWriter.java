package File;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class FastWriter {
	public String fileName; 
	public File file;
	public FileWriter fw; public BufferedWriter bw;
	public FastWriter(String str) {
		try {
			fileName = str; file = new File(fileName);
			fw = new FileWriter(file); bw = new BufferedWriter(fw);
		} catch (Exception e) { fw = null; bw = null; }
	}
	public void print(String str) {
		try { bw.write(str); 		} catch (IOException e) {}
	}
	public void println(String str) {
		try { bw.write(str + "\n"); } catch (IOException e) {}
	}
	public void close() {
		try { bw.flush(); fw.flush(); bw.close(); fw.close(); } catch (IOException e) {}
	}
}