package College;
import java.util.*;
import java.io.*;

public class PolyTest {
    public static void main (String [] args) throws FileNotFoundException {
        int choice = menu();
	Scanner sc;
	if (choice == 1) {
            sc = new Scanner (new File ("test1.dat"));
            testConstructorToString(sc);
	} else if (choice == 2) {
 	    sc = new Scanner (new File ("test1.dat"));
            testEvaluate(sc);
	} else {
	   sc = new Scanner (new File ("test2.dat"));
           testEquals(sc);
	}
    }

    public static int menu() {
        System.out.println("1 - test constructor and toString()");
        System.out.println("2 - test evaluate()");
        System.out.println("3 - test equals()");
	System.out.print("> ");
	Scanner sc = new Scanner(System.in);
	return sc.nextInt();
    }

    public static void testConstructorToString(Scanner sc) {
	System.out.println("\nTesting constructor and toString()");
	double [] c;
	Polynomial p;
	while (sc.hasNextLine()) {
            c = getNextPoly(sc.nextLine());
	    p = new Polynomial(c.length-1, c);
	    System.out.println(p);
	}
	c = new double[3];
        c[0] = 3; c[1] = 12.4; c[2] = 5;
	p = new Polynomial(2, c);
	System.out.println(p);
        c[0] = 5;
	System.out.println("Should still be " + p);
	System.out.println();
        
    }

    public static void testEvaluate(Scanner sc) {
	System.out.println("\nTesting evaluate()");
	while (sc.hasNextLine()) {
            double [] c = getNextPoly(sc.nextLine());
	    Polynomial p = new Polynomial(c.length-1, c);
	    System.out.println(p + " evaluated at -4 = " + p.evaluate(-4));
	}
	System.out.println();
        
    }

    public static void testEquals(Scanner sc) {
	System.out.println("\nTesting equals()");
	while (sc.hasNextLine()) {
            double [] c1 = getNextPoly(sc.nextLine());
	    Polynomial p1 = new Polynomial(c1.length-1, c1);
            double [] c2 = getNextPoly(sc.nextLine());
	    Polynomial p2 = new Polynomial(c2.length-1, c2);
            System.out.println("-------------");
	    System.out.println("p1 = " + p1);
	    System.out.println("p2 = " + p2);
	    System.out.println("p1.equals(p2)? " + p1.equals(p2));
	    System.out.println("p2.equals(p1)? " + p2.equals(p1));
	}
    }

    public static double[] getNextPoly(String input) {
	Scanner sc = new Scanner (input);
        double coef = sc.nextDouble();
        int degree;

	if (sc.hasNextInt())
	    degree = sc.nextInt();
	else
	    degree = 0;
        double [] coefs = new double[degree+1];
	coefs[degree] = coef;
	while (sc.hasNextDouble()) {
	   coef = sc.nextDouble();
	   if (sc.hasNextInt()) {
	       degree = sc.nextInt();
	       coefs[degree] = coef;
	   } else
	       coefs[0] = coef;
	}
	return coefs;
    }

}



