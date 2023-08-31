package TriNetX;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class SplitFiles {
	public static void main(String[]args) throws Exception{
		char [] c = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
		BufferedReader br = new BufferedReader(new FileReader(new File("/run/media/mm/Easystore/Research/Original/diagnosis.csv")));
		FileWriter [][] fws = new FileWriter[c.length][c.length];
		BufferedWriter [][] bws = new BufferedWriter[c.length][c.length];
		String header = br.readLine();
		for(int i = 0; i < c.length; i++) {
			for(int j = 0; j < c.length; j++) {
				fws[i][j] = new FileWriter(new File("/run/media/mm/Easystore/Research/PatientSplit/diagnosis/" + c[i] + c[j] + ".csv"));
				bws[i][j] = new BufferedWriter(fws[i][j]);
				bws[i][j].write(header + "\n");
				bws[i][j].flush();
			}
		}
		
		String line = br.readLine();
		while(line != null) {
			char a = line.charAt(1); char b = line.charAt(2); int d = -1; int e = -1;
			if(a < 58) 	{d = a - 48;}
			else 		{d = a - 87;}
			if(b < 58) 	{e = b - 48;}
			else 		{e = b - 87;}
			bws[d][e].write(line + "\n");
			line = br.readLine();
		}
		
		br.close();
		for(int i = 0; i < c.length; i++) {
			for(int j = 0; j < c.length; j++) {
				bws[i][j].flush();
				fws[i][j].flush();
				bws[i][j].close();
				fws[i][j].close();
			}
		}
	}
}
