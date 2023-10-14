package College;
import java.util.*;
import java.io.*;

public class NgramMain {
    public static final int LINE_BREAK = 65;

    public static void main(String[] args) throws FileNotFoundException {
        // introduce program
        System.out.println("Welcome to the Ngram Solver.  You specify");
        System.out.println("the sample file, the ngram size, and the");
        System.out.println("desired number of characters of output.");
        System.out.println();

        // open the sample text file
        Scanner console = new Scanner(System.in);
        System.out.print("What is the name of the text file? ");
        Scanner input = new Scanner(new File(console.nextLine()));
        String text = "";
        while (input.hasNext())
            text +=   input.next() + " ";

        // get n and construct NgramSolver
        System.out.print("What value of n do you want to use? ");
        int n = console.nextInt();
        NgramSolver solver = new NgramSolver(n, text.toString());

        // get output size (in characters) and produce output
        System.out.print("How many characters of output do you want? ");
        int length = console.nextInt();
        System.out.println();
        generateText(solver, length);
    }

    // Parameters: solver  - an NgramSolver
    //             length  - length of text to generate (in characters)
    // Postcondition: 'length' characters of text has been printed at
    //                 the console using the NgramSolver formatted to
    //                 >= LINE_BREAK characters per line
    public static void generateText(NgramSolver solver, int length) {
        // at the beginning of generating text, we are ready to print
        // in column 0, we have printed 0 characters total, and the current
        // the is not current ngram
        int column = 0;
        int total = 0;
        String current = "";

        // continue until we break on a termination condition        
        while (true) {
            // if solver contains the current start
            if (solver.contains(current)) {
                // get a character to add it ontoit
                char next = solver.randomChar(current);
                // print the new character
                // if the character to add is a ' ' and we are at the
                // line break, change lines rather than printing it
                if (next == ' ' && column >= LINE_BREAK) {
                    System.out.println();
                    column = 0;
                }  // otherwise print the next char
                else {
                    System.out.print(next);
                    column++;
                }
                // update current and total characters printed
		current = (current + next).substring(1);
                total++;
                // quit if we have printed enough characters and we just
                // printed a period
                if (total >= length && next == '.') 
		    break;
                // otherwise, go to the next loop iteration
                else 
		    continue;
	    }

            // else: if we are  here in this loop iteration, 
            // solver did not contain the current start, so
            // get a new start to be 'current' and print it to the output
            current = solver.randomStart();
            System.out.print(current);
            // update column and total with the length of the new start
            column += current.length();
            total += current.length();
            // quit if we have printed enough characters and the new
            // start we just printed ended in a period
            if (total >= length && current.endsWith("."))
		break;
        }
        System.out.println();
    }
}