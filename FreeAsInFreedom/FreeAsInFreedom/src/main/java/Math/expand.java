package Math;

import java.util.ArrayList;
import java.util.Scanner;
public class expand {
	public static void main(String[]args){
		Scanner in = new Scanner(System.in);
		String response = in.nextLine();
		while(response.contains("-")){
			response = response.substring(0, response.indexOf("-")) + "+_" + response.substring(response.indexOf("-")+1, response.length());
		}
		for(int i = 0; i < response.length(); i++){
			if(response.substring(i, i+1).equals("_")){
				response = response.substring(0, i) + "-" + response.substring(i+1, response.length());
			}
		}
		System.out.println(response);
		ArrayList<ArrayList<String>> vn = new ArrayList<ArrayList<String>>();
		String temp = "";
		for(int i = 0; i < response.length(); i++){
			if(response.substring(i, i+1).equals("(")){
				ArrayList<String> terms = new ArrayList<String>();
				System.out.println(i);
				for(int ii = 1; ii < response.substring(i).indexOf(")"); ii++){
					if(response.substring(ii, ii+1).equals("+")){
						System.out.println(ii);
						terms.add(temp);
						temp = "";
					}
					else if(ii == response.substring(i).indexOf(")") - 1){
						System.out.println(ii);
						temp += response.substring(ii, ii+1);
						terms.add(temp);
						temp = "";
					}
					else{
						temp += response.substring(ii, ii+1);						
					}
					
				}
				vn.add(terms);
			}
		}
		for(int i = 0; i < vn.size(); i++){
			System.out.println(vn.get(i));
		}
	}
}