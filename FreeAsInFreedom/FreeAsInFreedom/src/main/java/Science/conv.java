package Science;

/*
 * James Hawkins, Karl Palmer
 * 2/2/17
 * Nucleotide and protein alignment program
 */
import java.util.*;
import java.io.*;
public class conv{
    public static void main(String [] args) throws FileNotFoundException{
        int count1 = 1;
        int count2 =1;
        //reads in the txt file
        System.out.println("Enter input nucleotide file");
        File input = getFile();
        System.out.println("Enter input protein file");
        File answer = getFile();
        Scanner sc = new Scanner(input);
        System.out.println("Enter where translation starts");
        int Start = start();
        //converts protein file into one big string
        String result= "";
        PrintStream out = new PrintStream( new FileOutputStream("output.txt", true));
        while(sc.hasNext()){

	        Scanner aSc = new Scanner(answer);
	        while(aSc.hasNext()){
	            result = result+ aSc.nextLine();
	        }
	        //removes white space from protein string
	        result = result.replaceAll("\\s","");
	        //modify input and output to output.txt
	        while(sc.hasNextLine()){
	            String line = sc.nextLine();
	            //modifies input line to fit what we need
	            line = convert(line);
	            //print stream
	            //PrintStream out = new PrintStream( new FileOutputStream("output.txt", true));
	            //prints count
	            out.print(count1);
	            //padding
	            out.print(spaces(count1));
	            count1 =count1+60;
	            //prints actual sequence
	            out.println(line);
	            //prints the count for the amino acids
	            out.print(count2);
	            //padding
	            out.print(spaces(count2));
	            count2=count2+20;
	            //prints the amino acids
	            for(int i=0;i<20;i++){
	                out.print(" " + result.charAt(i) +" ");
	            }
	            out.println();
	            result = result.substring(20);
	        }
	    System.out.println();
	
	    }
        
    }
    //gets user input for files and returns txt file to convert
    public static File getFile() throws FileNotFoundException{
        Scanner sc = new Scanner(System.in);
        System.out.print("Input file: ");
        File f = new File(sc.next());
        System.out.println();
        while(!f.exists()){
            System.out.println("File not found! Input file: ");
            f=new File(sc.next());
        }
        return f;
    }

    public static int start(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Input number: ");
        int tranStart = sc.nextInt();
        return tranStart;
    }

    //takes in line of input and divides up into arraylist
    public static String convert(String line){
        //removes white space
        line= line.replaceAll("\\s","");
        //removes numbers, we will add them back later
        for(int i= 0; i<5;i++){
            char a = line.charAt(i);
            if(a>='0'&& a<='9'){
                line = line.substring(1);
                i--;
            }
        }
        return line;

    }
    public static String spaces(int count){
        if(count<10){
            return "         ";
        }else if(count<100){
            return "        ";
        }else if(count<1000){
            return "       ";
        }else if(count<10000){
            return "      ";
        }else if(count<100000){
            return "     ";
        }else if(count<1000000){
            return "    ";
        }else{
            return "   ";
        }
    }
}
