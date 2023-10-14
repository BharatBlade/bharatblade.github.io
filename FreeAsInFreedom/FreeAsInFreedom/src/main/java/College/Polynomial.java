package College;
public class Polynomial {
	
	//fields for degree, and coefficients array
	private int deg = 0;
	private double [] coef;
	
	public Polynomial(int degree, double [] coefficients){
		deg = degree;
		coef = coefficients.clone();
	}

	//converts Polnomial to readable string format
	public String toString(){
		double [] coefTemp = coef.clone();
		String a = "";
		for(int i = deg; i > 0; i--){
			String o = " + ";
			if(coefTemp[i-1] < 0){
				coefTemp[i-1] = Math.abs(coefTemp[i-1]);
				o = " - ";
			}
			String temp = coefTemp[i] + "x^" + (i) + o;
			if(i == 1){
				temp = coefTemp[i] + "x" + o;
			}
			if(coefTemp[i] == 1){
				a += temp.substring(temp.indexOf("x"), temp.length());
			}
			else if(coefTemp[i] != 0){
				a += temp;
			}
		}
		if(coefTemp[0] != 0){
			a += coefTemp[0];
		}
		else{
			a = a.substring(0, a.length()-3);
		}
		return a;
	}
	
	//Evaluates polynomial when x = given number
	public double evaluate(double x){
		double total = 0.0;
		for(int i = 0; i <= deg; i++){
			total += coef[i]*Math.pow(x, i);
		}
		return total;
	}
	
	//Checks if 2 polynomials have the same values
	public boolean equals(Polynomial a){
		return this.toString().equals(a.toString());
	}
}
