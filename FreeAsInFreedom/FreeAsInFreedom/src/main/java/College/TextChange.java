package College;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TextChange {
	public static void main(String[]args) throws FileNotFoundException{
		ArrayList<String> a = new ArrayList<String>();
		ArrayList<String> b = new ArrayList<String>();
		ArrayList<String> a1 = new ArrayList<String>();
		ArrayList<String> b1 = new ArrayList<String>();
		File f1 = new File("original.txt");
		Scanner in = new Scanner(f1);
		while(in.hasNext()){
			a.add(in.next());
		}
		File f2 = new File("edited.txt");
		in = new Scanner(f2);
		while(in.hasNext()){
			b.add(in.next());
		}
		a1.addAll(a);
		b1.addAll(b);
		for(int i = 0; i < a.size(); i++){
			for(int j = 0; j < b.size(); j++){
				if(a.get(i).equals(b.get(j))){
					a.remove(i);
					i--;
					b.remove(j);
					j--;
					break;
				}
			}
		}
		System.out.println(a);
		System.out.println(b);
	}
}
