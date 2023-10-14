package Science;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class conv2 {
	public static void main(String[]args) throws FileNotFoundException{
		Scanner reader = new Scanner(System.in);
		System.out.print("Enter Nucleotide File: ");
		Scanner nucleotideScanner = new Scanner(new File(reader.nextLine()));
		System.out.print("Enter Amino Acid File: ");
		Scanner aminoAcidScanner = new Scanner(new File(reader.nextLine()));
		System.out.print("Enter desired length of line: ");
		int length = reader.nextInt();
		System.out.print("Enter starting nucleotide of starting amino acid: ");
		int position = reader.nextInt();
		PrintStream out = new PrintStream(new File("output.txt"));
		String nucleotideSequence = "";
		String aminoAcidSequence = "";
		ArrayList<String> nS = new ArrayList<String>();
		ArrayList<String> aAS = new ArrayList<String>();
		while(nucleotideScanner.hasNextLine()){
			String line = nucleotideScanner.nextLine();
			for(int i = 0; i < line.length(); i++)
				if((int)line.substring(i, i+1).charAt(0) >= 97 && (int)line.substring(i, i+1).charAt(0) <= 122)
					nucleotideSequence += line.substring(i, i+1);
		}
		while(aminoAcidScanner.hasNextLine()){
			String line = aminoAcidScanner.nextLine();
			for(int i = 0; i < line.length(); i++)
				if((int)line.substring(i, i+1).charAt(0) >= 65 && (int)line.substring(i, i+1).charAt(0) <= 90)
					aminoAcidSequence += line.substring(i, i+1);
		}
		String temp = nucleotideSequence.substring(0, position-1);
		nucleotideSequence = nucleotideSequence.substring(position-1);
		String aminoAcidSequence2 = "";
		for(int i = 0 ; i < temp.length(); i++)
			aminoAcidSequence2 += " ";
		for(int i = 0; i < aminoAcidSequence.length(); i++)
			aminoAcidSequence2 += " " + aminoAcidSequence.substring(i, i+1) + " ";
		int tt = 0;
		for(int i = 0; i < aminoAcidSequence2.length(); i+=length){
			try{
				aAS.add(aminoAcidSequence2.substring(i, i+length));
				tt = i;
			}
			catch(Exception e){}
		}
		tt += length;
		aAS.add(aminoAcidSequence2.substring(tt));
		String nTemp = "";
		for(int i = 0; i < temp.length() + nucleotideSequence.length(); i++){
			if(i < temp.length())
				nTemp += temp.substring(i, i+1);
			else
				nTemp += nucleotideSequence.substring(i-position+1, i-position+2);
			if((i+1) % length == 0){
				nS.add(nTemp);
				nTemp = "";
			}
			if(i == temp.length() + nucleotideSequence.length() - 1)
				nS.add(nTemp);
		}
		int aACount = 0;
		for(int i = 0; i < nS.size(); i++){
			String space = "";
			for(int j = 0; j < 8-String.valueOf((i*length+1)).length(); j++)
				space += " ";
			out.println((i*length+1) + space + nS.get(i));
			try{
				String space2 = "";
				for(int j = 0; j < 8-String.valueOf(aACount+1+aAS.get(i-1).replaceAll(" ", "").length()).length(); j++)
					space2 += " ";
				out.println(aACount+1+aAS.get(i-1).replaceAll(" ", "").length() + space2 + aAS.get(i));
				aACount += aAS.get(i-1).replaceAll(" ", "").length();
			}
			catch (Exception e){out.println();}
		}
		reader.close();
		nucleotideScanner.close();
		aminoAcidScanner.close();
	}
}