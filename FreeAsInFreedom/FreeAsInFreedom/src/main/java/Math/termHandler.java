import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class termHandler {
	public static void main (String[]args){
		Scanner in = new Scanner(System.in);
		ArrayList<Integer> pOLocation = new ArrayList<Integer>();
		ArrayList<Integer> pCLocation = new ArrayList<Integer>();
		ArrayList<Integer> oLocation = new ArrayList<Integer>();
		ArrayList<String> operator = new ArrayList<String>();
		ArrayList<ArrayList<Integer>> tLocation = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<String>> terms = new ArrayList<ArrayList<String>>();
		System.out.println("Type your factored polynomial");
		String response = in.nextLine();
	
		String [] op = {"+", "-", "*", "/", "^", "%"};
		boolean test = false;
		String temp2 = "";
		ArrayList<String> tempTerm = new ArrayList<String>();
		ArrayList<Integer> tempLoc = new ArrayList<Integer>();		
		for(int i = 0; i < response.length(); i++){
			String temp = response.substring(i, i+1);
			if(i < response.length()-1){
				temp2 = response.substring(i+1, i+2);
				test = true;
			}
			else{
				test = false;
			}
			if(temp.equals("(") || temp.equals(")")){
				if(temp.equals("(")){
					pOLocation.add(i);
				}
				else{
					pCLocation.add(i);
					terms.add(tempTerm);
					tLocation.add(tempLoc);
					tempTerm = new ArrayList<String>();
					tempLoc = new ArrayList<Integer>();
				}
			}
			else if(Arrays.asList(op).contains(temp)){
				oLocation.add(i);
				operator.add(temp);
			}
			else{
				if(test){
					if(temp2.equals("(") || temp2.equals(")") || Arrays.asList(op).contains(temp2)){
						tempTerm.add(temp);
						tempLoc.add(i);
					}
					else{
						temp += temp2;
					}
				}
			}
		}
		//System.out.println("pOLocation:" + pOLocation);
		//System.out.println("pCLocation:" + pCLocation);
		//System.out.println("oLocation:" + oLocation);
		//System.out.println("operator:" + operator);
		System.out.println("tLocation:" + tLocation);
		System.out.println("terms:" + terms);
	}
}