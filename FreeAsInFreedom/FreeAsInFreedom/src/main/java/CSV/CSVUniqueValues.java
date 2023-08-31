package CSV;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.TreeSet;

import javax.swing.JFileChooser;
public class CSVUniqueValues {
	public static void main(String[]args) throws Exception{
		JFileChooser chooser = new JFileChooser("/run/media/mm/Easystore/Research/Original/");
		chooser.showOpenDialog(null);		
		File f = new File(chooser.getSelectedFile().getPath());
		FileReader fr = new FileReader(f);BufferedReader br = new BufferedReader(fr);
		String headers = br.readLine();
		String delim = "";
		System.out.println(headers);
		if(headers.contains("\",\"")) {delim = "\",\"";}
		else if(headers.contains(";")) {delim = ";";}
		else if(headers.contains(",")) {delim = ",";}
		String [] arr = headers.split(delim);
		TreeSet<String> [] tree = new TreeSet[arr.length];
		for(int i = 0; i < arr.length; i++) {tree[i] = new TreeSet<String>();}
		String line = br.readLine();
		while(line != null) {
			arr = line.split(delim);
			for(int i = 0; i < arr.length; i++) {tree[i].add(arr[i]);}
			line = br.readLine();
		}
		br.close();fr.close();
		for(int i = 0; i < tree.length; i++) {
			if(tree[i].toString().length() > 200) {System.out.println(tree[i].toString().substring(0, 200));}
			else{System.out.println(tree[i].toString());}
		}
	}
}