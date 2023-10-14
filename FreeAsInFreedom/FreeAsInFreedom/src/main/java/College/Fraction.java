package College;
public class Fraction {
	private int num;
	private int den;
	public Fraction(int n, int d){
		num = n;
		den = d;
	}
	public int getNumerator() {
		return num;
	}
	public void setNumerator(int n) {
		num = n;
	}
	public int getDenominator() {
		return den;
	}
	public void setDenominator(int d) {
		den = d;
	}
	public void reduce(){
		int a = Math.min(num, den);
		for(int i = 1; i <= Math.min(num, den); i++){
			if(num % i == 0 && den % i == 0){
				a = i;
			}
		}
		num /= a;
		den /= a;
	}
	public String toString() {
		return "fraction = " + num + "/" + den;
	}
}