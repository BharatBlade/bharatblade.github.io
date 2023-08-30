package CSV;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class CSVStripperPerformance {
	public static String line = "";
	public static int rows = 0;
	public static String delim = "";
	
	public static void main(String[]args) throws Exception {
		JFileChooser chooser = new JFileChooser("");
		chooser.showOpenDialog(null);
		File file = new File(chooser.getSelectedFile().getPath());
		//File file = new File("/run/media/mm/Windows/Users/HP/Documents/CVD Metabolic Conditions Diagnoses - ADHD Dataset/diagnosis.csv");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		chooser.showOpenDialog(null);
		File file2 = new File(chooser.getSelectedFile().getPath());
		FileReader fr2 = new FileReader(file2);
		BufferedReader br2 = new BufferedReader(fr2);
		String sss = br2.readLine();
		ArrayList<String[]> parameters = new ArrayList<String[]>();
		ArrayList<String> fileNames = new ArrayList<String>();
		while(sss != null) {
			String list  = sss.substring(0, sss.indexOf("}")+1);
			String fileName = sss.substring(sss.indexOf("}")+1);
			list = list.substring(1,list.length()-1);
			parameters.add(list.split(","));
			fileNames.add(fileName);
			sss = br2.readLine();
		}
		FileWriter [] fws = new FileWriter[fileNames.size()];
		BufferedWriter [] bws = new BufferedWriter[fileNames.size()];
		String headers = br.readLine();
		for(int i = 0; i < fileNames.size(); i++) {
			fws[i] = new FileWriter(new File(fileNames.get(i) + ".csv"));
			bws[i] = new BufferedWriter(fws[i]);
			bws[i].write(headers + "\n");
		}
		if(headers.contains("\",\"")) {
			headers = headers.substring(1, headers.length()-1);
			delim = "\",\"";
		}
		line = br.readLine();
		String [] s = headers.split(delim);
		for(int i = 0; i < parameters.size(); i++) {
			System.out.println(fileNames.get(i));
			System.out.println(Arrays.toString(parameters.get(i)));
		}
		long time = System.currentTimeMillis(); int count2 = 0;
		while(line != null) {
			line = line.substring(1, line.length()-1);
			int ccc = s.length;
			String [] t = new String[ccc];
			String z = line;
			for(int i = 0; i < t.length-1; i++) {
				t[i] = z.substring(0, z.indexOf(delim));
				z = z.substring(z.indexOf(delim)+delim.length());
			}
			t[t.length-1] = z;
			boolean print = false;
			for(int i = 0; i < parameters.size(); i++) {
				for(int j = 0; j < parameters.get(i).length; j++) {
					String testing = parameters.get(i)[j];
					String argg = t[3]; //fix
					if(!testing.isEmpty() && testing.charAt(testing.length()-1) == '*') {
						int mark = testing.indexOf('*');
						if(mark <= argg.length() && testing.substring(0, mark).equals(argg.substring(0, mark))) {
							print = true;
							break;
						}
					}
					else if(!testing.isEmpty() && testing.charAt(0) == '*') {
						if(testing.substring(1).equals(argg.substring(argg.length() - testing.length() + 1))) {
							print = true;
							break;
						}
					}
					else if(!testing.isEmpty() && testing.contains("*") && testing.length() == argg.length()) {
						int mark = testing.indexOf("*");
						if(testing.substring(0, mark).equals(argg.substring(0, mark)) && testing.substring(mark+1).equals(argg.substring(mark+1))) {
							print = true;
							break;
						}
					}
					else if(!testing.isEmpty() && testing.contains("-")) {
						String upper = testing.substring(0, testing.indexOf("-"));
						String lower = testing.substring(testing.indexOf("-")+1);
						int ssize = 0;
						int arggL = argg.length();
						int upperL = upper.length();
						int lowerL = lower.length();
						if(arggL <= upperL && arggL <= lowerL) {ssize = arggL;}
						else if(upperL <= arggL && upperL <= lowerL) {ssize = upperL;}
						else if(lowerL <= arggL && lowerL <= upperL) {ssize = lowerL;}
						char [][] ch = new char[3][ssize];
						for(int k = 0; k < ssize; k++) {
							ch[0][k] = argg.charAt(k); ch[1][k] = upper.charAt(k); ch[2][k] = lower.charAt(k);
							boolean current01 = ch[0][k] == ch[1][k]; boolean current02 = ch[0][k] == ch[2][k];
							boolean previous01 = false; boolean previous02 = false;
							if(k > 0) {
								previous01 = ch[0][k-1] == ch[1][k-1];
								previous02 = ch[0][k-1] == ch[2][k-1];
							}
							if(ch[0][k] > ch[1][k] && ch[0][k] < ch[2][k]) {
								print = true;
								break;
							}
							else if(current01 || current02) {
								if(k == ssize - 1) {
									print = true;
									break;
								}
							}
							else if(k > 0 && k < ssize && ((previous01 && !previous02 && ch[0][k] >= ch[1][k]) || (previous02 && !previous01 && ch[0][k] <= ch[2][k]))  ) {
								print = true;
								break;
							}
//							else {
//								print = false;
//								//break;
//							}
						}
						if(print) {
							break;
						}
					}
					else {
						if(!testing.isEmpty() && testing.equals(argg)) {
							print = true;
							break;
						}
					}
				}
				if(print) {
					
					if(delim.equals("\",\"") && line.charAt(0) != '"') {line = "\"" + line + "\"";}					
					bws[i].write(line+"\n");
					if(line.length() > 150) {
						System.out.println(line + "\t" + line.length());
						Thread.sleep(200);
					}
					print = false;
					//break;
				}
			}
			count2++;
			if(count2 == 10000000) {
				count2 = 0; rows++;
				System.out.println(System.currentTimeMillis() - time + "\t" + rows);
    		}
    		line = br.readLine();
    	}
		for(int i = 0; i < bws.length; i++) {
			bws[i].flush();fws[i].flush();bws[i].close();fws[i].close();
		}
		br.close();fr.close();br2.close();fr2.close();
		JOptionPane.showMessageDialog(null, "All Done!", "All Done!", JOptionPane.INFORMATION_MESSAGE);
	}
}
