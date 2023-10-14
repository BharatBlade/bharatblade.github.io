package College;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Assignment6 {
	public static void main(String[]args) throws FileNotFoundException, InterruptedException{
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter File Name: ");
		String file = reader.nextLine();
		Scanner in = new Scanner(new File(file));
		int m = in.nextInt()*2;
		int n = in.nextInt()*2;
		int numF = in.nextInt();
		int numD = in.nextInt();
		in.nextLine();
		String line = in.nextLine();
		Scanner sc = new Scanner(line);
		System.out.println(line);
		ArrayList<Integer> gX = new ArrayList<Integer>();
		ArrayList<Integer> gY = new ArrayList<Integer>();
		while(sc.hasNextInt()){
			gY.add(sc.nextInt()*2-1);
			gX.add(sc.nextInt()*2-1);
		}
		ArrayList<Integer> fX = new ArrayList<Integer>();
		ArrayList<Integer> fY = new ArrayList<Integer>();
		for(int i = 0; i < numF; i++){
			fY.add(in.nextInt()*2-1);
			fX.add(in.nextInt()*2-1);
		}
		ArrayList<Integer> dX = new ArrayList<Integer>();
		ArrayList<Integer> dY = new ArrayList<Integer>();
		for(int i = 0; i < numD; i++){
			dY.add(in.nextInt()*2-1);
			dX.add(in.nextInt()*2-1);
		}
		if(m % 2 == 1)
			m++;
		else
			m+=2;
		if(n % 2 == 1)
			n++;
		else
			n+=2;
		char [][] field = new char [n-1][m-1];
		for(int i = 0; i < field.length; i++)
			for(int j = 0; j < field[i].length; j++)
				if(i % 2 == 0 && j % 2 == 0)
					field[i][j] = '+';
				else if(i == 0 || i == field.length-1)
					field[i][j] = '-';
				else if(j == 0 || j == field[field.length-1].length-1)
					field[i][j] = '|';
		System.out.println(gX);
		System.out.println(gY);
		Thread.sleep(2000);
		for(int i = 0; i < gX.size(); i++)
			field[gX.get(i)][gY.get(i)] = 'G';
		field[field.length-2][1] = 'H';
		for(int i = 0; i < fX.size(); i++){
			field[fX.get(i)][fY.get(i)] = 'X';
		}
		for(int i = 0; i < dX.size(); i++){
			field[dX.get(i)][dY.get(i)] = '#';
		}
		vacuum v = new vacuum(field.length-2, 1, field);
		v.move();
		
	}
}