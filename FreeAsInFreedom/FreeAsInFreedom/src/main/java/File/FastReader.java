package File;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
public class FastReader {
	public String fileName; public String [] fileNames;
	public File file; public File [] files;
	public FileReader fr; public BufferedReader br;
	public GZIPInputStream gzip; public InputStreamReader isr;
	public FastReader() {
		
	}
	public FastReader(String str) {
		try {
			fileName = str; file = new File(fileName);
			fr = new FileReader(file); br = new BufferedReader(fr);
		} catch (Exception e) { fr = null; br = null; }
	}
	public FastReader(FileInputStream fis) {
		try {
			gzip = new GZIPInputStream(fis);
			br = new BufferedReader(new InputStreamReader(gzip));
		} catch (Exception e) {fr = null; br = null;}
	}
	public int nextInt() {
		try { return br.read(); } catch (Exception e) { return -1; }
	}
	public String nextLine() {
		try { return br.readLine(); } catch (Exception e) { return null; }
	}
	public String [] nextLineToArray(String delim, boolean removeFirstAndLastCh) {
		try { return csvLineToArray(br.readLine(), delim, removeFirstAndLastCh); } catch (Exception e) { return null; }
	}
	public void close() {
		try { br.close(); fr.close(); } catch (Exception e) { try { isr.close(); } catch (Exception e1) {}}
	}
	public void loadFiles(String directory) {
		files = new File(directory).listFiles();
		for(int i = 0; i < files.length; i++) {
			fileNames[i] = files[i].getAbsolutePath();
		}
	}
	public void loadFiles(String directory, String filter, boolean contains) {
		File [] files = new File(directory).listFiles();
		for(int i = 0; i < files.length; i++) {
			if(files[i].getName().contains(filter) == contains) {
				fileNames[i] = files[i].getAbsolutePath();
			}
		}
	}
	public int countColumns(String line, String delimiter) {
		return ((line.length() - line.replace(delimiter, "").length())/delimiter.length() + 1);
	}
	public String [] csvLineToArray(String s, char delimiter, boolean removeFirstAndLastCh) {
		try {
			if(removeFirstAndLastCh) { s = s.substring(1,s.length()-1); }
			String [] t = new String[countColumns(s, ""+delimiter)];
			for(int i = 0; i < t.length-1; i++) {
				int p = s.indexOf(delimiter);
				t[i] = s.substring(0, p);
				s = s.substring(p+1);
			}
			t[t.length-1] = s;
			return t;
		}
		catch (Exception e) { return null; }
	}
	public String [] csvLineToArray(String s, String delimiter, boolean removeFirstAndLastCh) {
		if(delimiter.length() == 1) {
			return csvLineToArray(s, delimiter.charAt(0), removeFirstAndLastCh);
		}
		try {
			if(removeFirstAndLastCh) { s = s.substring(0, s.length()-1); }
			return s.split(delimiter);
		} catch(Exception e) {return null;}
	}
	public String header() {
		FastReader temp = new FastReader(fileName); String header = temp.nextLine(); temp.close();
		return header;	
	}
	public String header(String fileName) {
		FastReader temp = new FastReader(fileName); String header = temp.nextLine(); temp.close();
		return header;	
	}
}