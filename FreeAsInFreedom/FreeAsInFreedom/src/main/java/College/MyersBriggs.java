package College;
import java.util.*;
import java.io.*;
public class MyersBriggs {
	public static void main(String[]args) throws FileNotFoundException{
		System.out.println("args[0] = " + args[0]);
		Scanner input = new Scanner(System.in);
		String s = args[0];
		if(!(new File(args[0]).exists())){
			System.out.println("File does not exist");
			s = input.nextLine();
		}
		Scanner in = new Scanner(new File(s));
		while(in.hasNextLine()){
			String name = in.nextLine();
			System.out.println(name);
			String answers = in.nextLine();
			int [] a = new int[4];
			String [] b = new String[4];
			int [] c = new int[4];
			String [] d = {"I", "S", "T", "J"};
			String [] e = {"E", "N", "F", "P"};
			for(int i = 0; i < 10; i++){
				if(answers.substring(i*7, i*7+1).equals("A")){
					a[0]++;
				}
				if(answers.substring(i*7+1, i*7+2).equals("A")){
					a[1]++;
				}
				if(answers.substring(i*7+2, i*7+3).equals("A")){
					a[1]++;
				}
				if(answers.substring(i*7+3, i*7+4).equals("A")){
					a[2]++;
				}
				if(answers.substring(i*7+4, i*7+5).equals("A")){
					a[2]++;
				}
				if(answers.substring(i*7+5, i*7+6).equals("A")){
					a[3]++;
				}
				if(answers.substring(i*7+6, i*7+7).equals("A")){
					a[3]++;
				}
			}
			for(int i = 0; i < a.length; i++){
				System.out.print(a[i]+"A-"+(20-a[i])+"B ");
			}
			System.out.println();
			b[0] = (int)((double)a[0]/10*100) + "%";
			c[0] = (int)((double)a[0]/10*100);
			for(int i = 1; i < a.length; i++){
				b[i] = (int)(((double)a[i]/20)*100) + "%";
				c[i] = (int)(((double)a[i]/20)*100);
			}
			System.out.print(Arrays.toString(b) + " = ");
			for(int i = 0; i < c.length; i++){
				if(c[i] > 49){
					System.out.print(d[i]);
				}
				else{
					System.out.print(e[i]);
				}
			}
			System.out.println();
		}
	}
}
