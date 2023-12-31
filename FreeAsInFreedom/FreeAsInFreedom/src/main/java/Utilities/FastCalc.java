package Utilities;
import java.util.ArrayList;
import java.util.Scanner;

public class FastCalc {
	
	Constants co = new Constants();
	public FastCalc() {
		
	}
	
	
	public String decToHex(int dec) {
		String total = "";
		while (dec > 1) {total = Integer.toHexString((dec%16)) + total;dec /= 16;}
		return total;
	}
	public static String decToHex(double dec) {
		String total = "";
		while (dec > 1) {total = Integer.toHexString((int)(dec%16)) + total;dec /= 16;}
		return total;
	}
	public int pow(int a, int b) {
		double temp = a;
		if(b > 0) {
			for(int i = 0; i < b - 1; i++) {a *= temp;}
			return a;
		}
		else if(b == 0) {return 1;}
		return 0;
	}
	public static double pow(double a, double b) {
		double temp = a;
		if(b > 0) {
			for(int i = 0; i < b - 1; i++) {a *= temp;}
			return a;
		}
		else if(b == 0) {return 1;}
		return 0;
	}
	public void decToHexSmallNumbers(int j, int rem, int count, ArrayList<Integer> rems, StringBuilder sb){
		rem = j % pow(16, count);
		for(int i = 0; i < rems.size(); i++) {
			rem -= rems.get(i);
		}
		rems.add(rem);
		int k = rem/pow(16, count-1);
		count++;
		if(rem < j/2) {
			decToHexSmallNumbers(j, rem, count, rems, sb);
		}
		sb.append(co.hex[k]);
	}
	
	public int hexToDec(StringBuilder sb, int total, int count) {
		char a = sb.charAt(sb.length()-count);
		int b = -1;
		if(a < 58) 	{b = a - 48;}
		else 		{b = a - 87;}
		total += b*pow(16, count-1);
		count++;
		if(count <= sb.length()) {
			return hexToDec(sb, total, count);
		}
		return total;
	}
	
	public void derivative() {
		while(true){
			Scanner in = new Scanner(System.in);
			String expression = in.nextLine();
			ArrayList<Double> coefficients = new ArrayList<Double>();
			ArrayList<Double> exponents = new ArrayList<Double>();
			ArrayList<Integer> xLocation = new ArrayList<Integer>();
			ArrayList<String> letter = new ArrayList<String>();
			ArrayList<String> alphabet = new ArrayList<String>();
			ArrayList<String> numbers = new ArrayList<String>();
			for(int i = (int)'a'; i < (int)'z'+1; i++){
				alphabet.add(String.valueOf((char)i));
			}
			for(int i = (int)'0'; i < (int)'9'+1; i++){
				numbers.add(String.valueOf((char)i));
			}
			for(int i = 0; i < expression.length(); i++){
				if(alphabet.contains(expression.substring(i,i+1))){
					
				}
			}
			ArrayList<Integer> operatorLocation = new ArrayList<Integer>();
			String newExpression = "";
			for(int i = 0; i < expression.length(); i++){
				if(alphabet.contains(expression.substring(i, i+1))){
					xLocation.add(i);
					letter.add(expression.substring(i, i+1));
				}
			}
			for(int i = 0; i < expression.length(); i++){
				if(expression.substring(i, i+1).equals("+")||expression.substring(i, i+1).equals("-")){
					operatorLocation.add(i);
				}
			}
			if(expression.substring(0,xLocation.get(0)).equals("")){
				coefficients.add(1.0);
			}
			else{
				coefficients.add(Double.valueOf(expression.substring(0,xLocation.get(0))));
			}
			if(operatorLocation.size() > 0){
				for(int i = 0; i < xLocation.size() - 1; i++){
					if(xLocation.get(i+1).intValue() > operatorLocation.get(i)){
						if(expression.substring(operatorLocation.get(i)+1,xLocation.get(i+1)).equals("")){
							coefficients.add(1.0);
						}
						else{
							if(expression.substring(operatorLocation.get(i)+1,xLocation.get(i+1)).contains("+")){
								expression.substring(operatorLocation.get(i)+1,xLocation.get(i+1)).indexOf("+");
								coefficients.add(Double.valueOf(expression.substring(operatorLocation.get(i)+1,xLocation.get(i+1)).substring(expression.substring(operatorLocation.get(i)+1,xLocation.get(i+1)).indexOf("+"), expression.substring(operatorLocation.get(i)+1,xLocation.get(i+1)).length())));
							}
							else{
								coefficients.add(Double.valueOf(expression.substring(operatorLocation.get(i),xLocation.get(i+1))));
							}
						}
					}
				}
			}
			for(int i = 0; i < xLocation.size(); i++){
				if(xLocation.get(i).intValue() < expression.length()-2){
					if(operatorLocation.size() > 0 && i != operatorLocation.size()){
						exponents.add(Double.valueOf(expression.substring(xLocation.get(i)+2,operatorLocation.get(i))));
					}
					else{
						exponents.add(Double.valueOf(expression.substring(xLocation.get(i)+2)));
					}
					
				}
			}
			for(int i = 0; i < xLocation.size(); i++){
				if((coefficients.get(i) * exponents.get(i)) == 0){
					newExpression += "";
				}
				else if((coefficients.get(i) * exponents.get(i)) == 1){
					newExpression += letter.get(i)+"^"+(exponents.get(i)-1)+"d"+letter.get(i);
				}
				else if(exponents.get(i) == 1){
					if(coefficients.get(i) * exponents.get(i) < 0){
						newExpression += (coefficients.get(i) * exponents.get(i)*(-1));
					}
					else{
						newExpression += (coefficients.get(i) * exponents.get(i));
					}
				}
				else{
					if(coefficients.get(i) * exponents.get(i) < 0){
						newExpression += (coefficients.get(i) * exponents.get(i) *(-1))+letter.get(i)+"^"+(exponents.get(i)-1)+"d"+letter.get(i);
					}
					else{
						newExpression += (coefficients.get(i) * exponents.get(i))+letter.get(i)+"^"+(exponents.get(i)-1)+"d"+letter.get(i);
					}
				}
				if(i != xLocation.size() -1 && (coefficients.get(i+1) * exponents.get(i+1)) != 0){
					if(coefficients.get(i+1) * exponents.get(i+1)<0){
						newExpression += " - ";
					}
					else{
						newExpression += " + ";
					}
				}
			}
			System.out.println(newExpression);
			in.close();
		}
	}
	
	public void expand() {
		Scanner in = new Scanner(System.in);
		String expression = "3(x+2)(x-2)";
		ArrayList<ArrayList<Integer>> coefficients = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<String>> variables = new ArrayList<ArrayList<String>>();
		ArrayList<Integer> pOLocation = new ArrayList<Integer>();
		ArrayList<Integer> pCLocation = new ArrayList<Integer>();
		ArrayList<String> insides = new ArrayList<String>();
		if(!expression.substring(0,1).equals("(")){
			insides.add(expression.substring(0, expression.indexOf("(")));
		}
		for(int i = 0; i < expression.length(); i++){
			if(expression.substring(i, i+1).equals("(")){
				pOLocation.add(i);
			}
			else if(expression.substring(i, i+1).equals(")")){
				pCLocation.add(i);
			}
		}
		for(int i = 0; i < pOLocation.size(); i++){
			insides.add(expression.substring(pOLocation.get(i)+1, pCLocation.get(i)));
		}
		for(int i = 0; i < insides.size(); i++){
			System.out.println(insides.get(i));
		}
		for(int row = 0; row < insides.size(); row++){
			ArrayList<Integer> c = new ArrayList<Integer>();
			ArrayList<String> v = new ArrayList<String>();
			coefficients.add(c);
			variables.add(v);
			for(int col = 0; col < insides.get(row).length(); col++){
				if(insides.get(row).contains("x")){
					variables.get(row).add("x");
				}
			}
			
		}
		in.close();
	}
}
