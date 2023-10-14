package Math;

import java.util.ArrayList;
import java.util.Scanner;
public class Calculator {
	public static void main(String[]args){
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		System.out.println(sin(abs(approxFrac(input))));
	}
	public static String emdas(String input){
		ArrayList<Double> num = new ArrayList<Double>();
		ArrayList<String> operator = new ArrayList<String>();
		String temp = "";
		for(int i = 0; i < input.length(); i++){
			if(input.substring(i, i+1).equals("+") || input.substring(i, i+1).equals("-") || input.substring(i, i+1).equals("*") || input.substring(i, i+1).equals("/") || input.substring(i, i+1).equals("^")){
				num.add(Double.valueOf(temp));
				operator.add(input.substring(i, i+1));
				temp = "";
			}
			else if(i == input.length() - 1){
				temp += input.substring(i, i+1);
				num.add(Double.valueOf(temp));
			}
			else{
				temp += input.substring(i, i+1);
			}
		}
		
		for(int i = 0; i < operator.size(); i++){
			if(operator.get(i).equals("^")){
				num.set(i, Math.pow(num.get(i), num.get(i+1)));
				num.remove(i+1);
				operator.remove(i);
				i--;
			}
		}
		for(int i = 0; i < operator.size(); i++){
			if(operator.get(i).equals("*")){
				num.set(i, num.get(i)*num.get(i+1));
				num.remove(i+1);
				operator.remove(i);
				i--;
			}
			else if(operator.get(i).equals("/")){
				num.set(i, num.get(i)/num.get(i+1));
				num.remove(i+1);
				operator.remove(i);
				i--;
			}
		}
		double total = num.get(0);
		for(int i = 0; i < operator.size(); i++){
			if(operator.get(i).equals("+")){
				total += num.get(i+1);
			}
			if(operator.get(i).equals("-")){
				total -= num.get(i+1);
			}
		}
		return Double.toString(total);
	}
	public static String pemdas(String input){
		while(input.contains("(")){
		int oP = 0;
		int cP = 0;
		for(int i = 0; i < input.length(); i++){
			if(input.substring(i, i+1).equals("(")){
				oP = i;
			}
			else if(input.substring(i, i+1).equals(")")){
				cP = i;
			}
			if(oP < cP){
				input = input.substring(0, oP) + emdas(input.substring(oP+1, cP)) + input.substring(cP+1, input.length());
				oP = 0;
				cP = 0;
				i = 0;
			}
		}
		}
			return emdas(input);
	}
	public static String abs(String input){
		int a = 0;
		int cP = 0;
		boolean check = false;
		if(input.contains("abs")){
			for(int i = 0; i < input.length(); i++){
				if(input.substring(i, i+3).equals("abs")){
					a = i;
					for(int ii = a; ii < input.length(); ii++){
						if(input.substring(ii, ii+1).equals(")")){
							cP = ii;
							check = true;
							break;
						}
					}
				}
				if(check){
					break;
				}
			}
			input = input.substring(0, a) + String.valueOf(Math.abs(Double.valueOf(pemdas(input.substring(a+4, cP))))) + input.substring(cP+1, input.length());
		}
		return input;
	}
	public static String approxFrac(String input){
		int a = 0;
		int cP = 0;
		int d = 0;
		double c = 0;
		boolean check = false;
		if(input.contains("approxFrac")){
			for(int i = 0; i < input.length(); i++){
				if(input.substring(i, i+10).equals("approxFrac")){
					a = i;
					for(int ii = a; ii < input.length(); ii++){
						if(input.substring(ii, ii+1).equals(")")){
							cP = ii;
							check = true;
							break;
						}
					}
				}
				if(check){
					break;
				}
			}
			String ans = String.valueOf(Double.valueOf(pemdas(input.substring(a+11, cP))));
			for(int i = 0; i < ans.length(); i++){
				if(ans.substring(i, i+1).equals(".")){
					d = ans.length() - i;
				}
			}
			double numerator = Double.valueOf(ans)*Math.pow(10, d);
			double denominator = Math.pow(10, d);
			int cof = GCF((int)numerator, (int)denominator);
			numerator = numerator / cof;
			denominator = denominator / cof;
			input = input.substring(0, a) + (int)numerator + "/" + (int)denominator + input.substring(cP+1, input.length());
		}
		return input;
	}
	public static String sin(String input){
		int a = 0;
		int cP = 0;
		boolean check = false;
		if(input.contains("sin")){
			for(int i = 0; i < input.length(); i++){
				if(input.substring(i, i+3).equals("sin")){
					a = i;
					for(int ii = a; ii < input.length(); ii++){
						if(input.substring(ii, ii+1).equals(")")){
							cP = ii;
							check = true;
							break;
						}
					}
				}
				if(check){
					break;
				}
			}
			input = input.substring(0, a) + String.valueOf(Math.sin((Double.valueOf(pemdas(input.substring(a+4, cP)))))) + input.substring(cP+1, input.length());
		}
		return input;
	}
	public static String cos(String input){
		int a = 0;
		int cP = 0;
		boolean check = false;
		if(input.contains("cos")){
			for(int i = 0; i < input.length(); i++){
				if(input.substring(i, i+3).equals("cos")){
					a = i;
					for(int ii = a; ii < input.length(); ii++){
						if(input.substring(ii, ii+1).equals(")")){
							cP = ii;
							check = true;
							break;
						}
					}
				}
				if(check){
					break;
				}
			}
			input = input.substring(0, a) + String.valueOf(Math.cos((Double.valueOf(pemdas(input.substring(a+4, cP)))))) + input.substring(cP+1, input.length());
		}
		return input;
	}
	public static String tan(String input){
		int a = 0;
		int cP = 0;
		boolean check = false;
		if(input.contains("tan")){
			for(int i = 0; i < input.length(); i++){
				if(input.substring(i, i+3).equals("tan")){
					a = i;
					for(int ii = a; ii < input.length(); ii++){
						if(input.substring(ii, ii+1).equals(")")){
							cP = ii;
							check = true;
							break;
						}
					}
				}
				if(check){
					break;
				}
			}
			input = input.substring(0, a) + String.valueOf(Math.tan((Double.valueOf(pemdas(input.substring(a+4, cP)))))) + input.substring(cP+1, input.length());
		}
		return input;
	}
	public static String arcsin(String input){
		int a = 0;
		int cP = 0;
		boolean check = false;
		if(input.contains("arcsin")){
			for(int i = 0; i < input.length(); i++){
				if(input.substring(i, i+6).equals("arcsin")){
					a = i;
					for(int ii = a; ii < input.length(); ii++){
						if(input.substring(ii, ii+1).equals(")")){
							cP = ii;
							check = true;
							break;
						}
					}
				}
				if(check){
					break;
				}
			}
			input = input.substring(0, a) + String.valueOf(Math.asin((Double.valueOf(pemdas(input.substring(a+4, cP)))))) + input.substring(cP+1, input.length());
		}
		return input;
	}
	public static String arccos(String input){
		int a = 0;
		int cP = 0;
		boolean check = false;
		if(input.contains("arccos")){
			for(int i = 0; i < input.length(); i++){
				if(input.substring(i, i+6).equals("arccos")){
					a = i;
					for(int ii = a; ii < input.length(); ii++){
						if(input.substring(ii, ii+1).equals(")")){
							cP = ii;
							check = true;
							break;
						}
					}
				}
				if(check){
					break;
				}
			}
			input = input.substring(0, a) + String.valueOf(Math.acos((Double.valueOf(pemdas(input.substring(a+4, cP)))))) + input.substring(cP+1, input.length());
		}
		return input;
	}
	public static String arctan(String input){
		int a = 0;
		int cP = 0;
		boolean check = false;
		if(input.contains("arctan")){
			for(int i = 0; i < input.length(); i++){
				if(input.substring(i, i+6).equals("arctan")){
					a = i;
					for(int ii = a; ii < input.length(); ii++){
						if(input.substring(ii, ii+1).equals(")")){
							cP = ii;
							check = true;
							break;
						}
					}
				}
				if(check){
					break;
				}
			}
			input = input.substring(0, a) + String.valueOf(Math.atan((Double.valueOf(pemdas(input.substring(a+4, cP)))))) + input.substring(cP+1, input.length());
		}
		return input;
	}
	public static String Base(String input){
		int a = 0;
		int cP = 0;
		int com = 0;
		boolean check = false;
		if(input.contains("base")){
			for(int i = 0; i < input.length(); i++){
				if(input.substring(i, i+4).equals("base")){
					a = i;
					for(int iii = a; iii < input.length(); iii++){
						if(input.substring(iii, iii+1).equals(",")){
							com = iii;
							for(int ii = iii; ii < input.length(); ii++){
								if(input.substring(ii, ii+1).equals(")")){
									cP = ii;
									check = true;
									break;
								}
							}
						}
					}
				}
				if(check){
					break;
				}
			}
			input = input.substring(0, a) + String.valueOf(Integer.toString(Integer.valueOf(input.substring(a+5, com)), Integer.valueOf(input.substring(com+1, cP)))) + input.substring(cP+1, input.length());
		}
		return input;
	}
	public static int GCF(int a, int b) {
	    if (b == 0) return a;
	    else return (GCF (b, a % b));
	}
	public static double nSolve(String input){
		int a = 0;
		int cP = 0;
		int eq = 0;
		int vP = 0;
		double vf = 0;
		double nf = 0;
		for(int i = 0; i < input.length(); i++){
			if(input.substring(i, i+1).equals("(")){
				a = i;
				for(int ii = i; ii < input.length(); ii++){
					if(input.substring(ii, ii+1).equals(")")){
						cP = ii;
					}
				}
			}
		}
		for(int i = a+1; i < input.length()-1; i++){
			if(input.substring(i, i+1).equals("=")){
				eq = i;
			}
			else if((int)input.substring(i, i+1).charAt(0) > 64 && (int)input.substring(i, i+1).charAt(0) < 123){
				vP = i;
			}
		}
		if(eq > vP){
			nf = Double.valueOf(pemdas(input.substring(eq+1, cP)));
			input = remdas(input.substring(a+1, eq), nf);
		}
		else{
			nf = Double.valueOf(pemdas(input.substring(a+1, eq)));
		}
		
		return Double.valueOf(input);
	}
	public static String remdas(String input, double nf){
		ArrayList<Double> num = new ArrayList<Double>();
		ArrayList<String> operator = new ArrayList<String>();
		String temp = "";
		for(int i = 0; i < input.length(); i++){
			if(input.substring(i, i+1).equals("+") || input.substring(i, i+1).equals("-") || input.substring(i, i+1).equals("*") || input.substring(i, i+1).equals("/") || input.substring(i, i+1).equals("^")){
				num.add(Double.valueOf(temp));
				operator.add(input.substring(i, i+1));
				temp = "";
			}
			else if(i == input.length() - 1){
				temp += input.substring(i, i+1);
				num.add(Double.valueOf(temp));
			}
			else{
				temp += input.substring(i, i+1);
			}
		}
		for(int i = 0; i < operator.size(); i++){
			if(operator.get(i).equals("^")){
				num.set(i, Math.pow(num.get(i), num.get(i+1)));
				num.remove(i+1);
				operator.remove(i);
				i--;
			}
		}
		for(int i = 0; i < operator.size(); i++){
			if(operator.get(i).equals("*")){
				num.set(i, num.get(i)*num.get(i+1));
				num.remove(i+1);
				operator.remove(i);
				i--;
			}
			else if(operator.get(i).equals("/")){
				num.set(i, num.get(i)/num.get(i+1));
				num.remove(i+1);
				operator.remove(i);
				i--;
			}
		}
		double total = nf;
		for(int i = 0; i < operator.size(); i++){
			if(operator.get(i).equals("+")){
				total -= num.get(i+1);
			}
			if(operator.get(i).equals("-")){
				total += num.get(i+1);
			}
		}
		return Double.toString(total);
	}
	public static String rpemdas(String input){
		while(input.contains("(")){
		int oP = 0;
		int cP = 0;
		for(int i = 0; i < input.length(); i++){
			if(input.substring(i, i+1).equals("(")){
				oP = i;
			}
			else if(input.substring(i, i+1).equals(")")){
				cP = i;
			}
			if(oP < cP){
				input = input.substring(0, oP) + emdas(input.substring(oP+1, cP)) + input.substring(cP+1, input.length());
				oP = 0;
				cP = 0;
				i = 0;
			}
		}
		}
			return emdas(input);
	}
}