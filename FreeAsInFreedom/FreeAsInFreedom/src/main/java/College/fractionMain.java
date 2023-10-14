package College;

public class fractionMain {
	public static void main(String[]args){
		Fraction a = new Fraction(6, 12);
		System.out.println(a.toString());
		a.setNumerator(7);
		a.setDenominator(14);
		System.out.println(a.toString());
		a.reduce();
		System.out.println(a.toString());
	}
}
