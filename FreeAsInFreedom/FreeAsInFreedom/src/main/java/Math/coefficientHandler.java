import java.util.ArrayList;
import java.util.Scanner;
public class coefficientHandler {
	public static void main(String[]args){
		Scanner in = new Scanner(System.in);
		ArrayList<Integer> pOLocation = new ArrayList<Integer>();
		ArrayList<Integer> pCLocation = new ArrayList<Integer>();
		String response = in.nextLine();
		for(int i = 0; i < response.length(); i++){
			String temp = response.substring(i, i+1);
			if(temp.equals("(")){
				pOLocation.add(i);
			}
			else if(temp.equals(")")){
				pCLocation.add(i);
			}
		}
	}
}
