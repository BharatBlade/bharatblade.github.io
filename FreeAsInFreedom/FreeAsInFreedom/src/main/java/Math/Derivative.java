import java.util.ArrayList;
import java.util.Scanner;
public class Derivative {
	public static void main(String[]args){
		Scanner in = new Scanner(System.in);
		String expression;
		while(true){
		expression = in.nextLine();
		ArrayList<Double> coefficients = new ArrayList<Double>();
		ArrayList<Double> exponents = new ArrayList<Double>();
		ArrayList<Integer> xLocation = new ArrayList<Integer>();
		ArrayList<String> letter = new ArrayList<String>();
		ArrayList<String> alphabet = new ArrayList<String>();
		ArrayList<String> numbers = new ArrayList<String>();
		String plus = " + ";
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
		}
	}
}