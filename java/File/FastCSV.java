package File;
import java.io.File;
import java.util.TreeSet;

import Utilities.FastDate;

public class FastCSV {
	public FastCSV() {
		
	}
	
	public void searchCSV(String file, String subSearch, boolean saveToFile) throws Exception {
		FastDate date = new FastDate();
		FastReader reader = new FastReader(file);
		FastWriter writer = new FastWriter("CSVSearch_" + date.getDate() + ".csv");
		System.out.println(reader.nextLine());
		String line = reader.nextLine();
		while(line != null) {
			if(line.contains(subSearch)) {
				if(saveToFile) { writer.println(line); }
				else { System.out.println(line);  }
			}
			line = reader.nextLine();
		}
		reader.close();
		writer.close();
	}
	
	public void searchMultipleCSV(String directory, String subSearch, boolean saveToFile) throws Exception {
		File [] files = new File(directory).listFiles();
		FastDate date = new FastDate();
		FastWriter writer = new FastWriter("CSVSearch_" + date.getDate() + ".csv");
		for(int i = 0; i < files.length; i++) {
			FastReader reader = new FastReader(files[i].getAbsolutePath());
			System.out.println(reader.nextLine());
			String line = reader.nextLine();
			while(line != null) {
				if(line.contains(subSearch)) {
					if(saveToFile) { writer.println(line); }
					else { System.out.println(line);  }
				}
				line = reader.nextLine();
			}
			reader.close();			
		}
		writer.close();
	}
	
	public String uniqueValues(String readPathCSV, String delim, int numOfColInCSV, boolean removeFirstAndLastCh ) throws Exception {
		FastReader fe = new FastReader(readPathCSV);
		System.out.println(fe.nextLine());
		String line = fe.nextLine();
		@SuppressWarnings("unchecked")
		TreeSet<String> [] trees = (TreeSet<String>[]) new TreeSet [numOfColInCSV];
		for(int i = 0; i < trees.length; i++) {
			trees[i] = new TreeSet<String>();
		}
		
		FastReader reader = new FastReader();
		while(line != null) {
			String [] fields = reader.csvLineToArray(line, delim, removeFirstAndLastCh);
			for(int i = 0; i < fields.length; i++) {
				trees[i].add(fields[i]);
			}
			line = fe.nextLine();
		}
		reader.close();
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < trees.length - 1; i++) {
			sb.append(trees[i] + "\n");
		}
		sb.append(trees[trees.length-1]);
		fe.close();
		return sb.toString();
	}
}
