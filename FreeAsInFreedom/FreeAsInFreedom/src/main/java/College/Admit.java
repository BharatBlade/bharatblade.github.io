package College;

import java.util.Scanner;
public class Admit {
	
	public static Scanner in = new Scanner(System.in);
	
	//Program that compares 2 applicants and determines the more
	//qualified applicant
	public static void main(String[]args){
		System.out.println("This program compares two applicants to" +
							" determine which one is the stronger"+
							" candidate. For each candidate please"+
							" provide either SAT or ACT scores, plus"+
							" a weighted GPA.");
		double first = applicant();
		double second = applicant();
		System.out.println("First applicant overall score = "+first);
		System.out.println("Second applicant overall score = "+second);
		if(first == second){
			System.out.println("Both applicants are equally qualified");
		}
		else if (first > second){
			System.out.println("The first applicant is more qualified");			
		}
		else{
			System.out.println("The second applicant is more qualified");
		}
	}
	
	//calculates the score of one applicant
	public static double applicant(){
		System.out.print("Information for the first applicant: \n " +
				"      do you have 1) SAT scores or 2) ACT scores?");
		double sum = 0.0;
		int choice = in.nextInt();
		if(choice == 1){
			sum += SAT();
		}
		else if(choice == 2){
			sum += ACT();
		}
		else{
			exit();
		}
		sum += GPA();
		return sum;
	}
	
	//calculates the SAT score
	public static double SAT(){
		double sum = 0.0;
		System.out.print("SAT Verbal?");
		sum += check(800) - 200;
		System.out.print("SAT Math?");
		sum += check(800) - 200;
		sum /= 12;
		return sum;
	}
	
	//calculates the ACT score
	public static double ACT(){
		double sum = 0.0;
		System.out.print("ACT English?");
		sum += check(36) - 1;
		System.out.print("ACT Math?");
		sum += check(36) - 1;
		System.out.print("ACT Reading?");
		sum += check(36) - 1;
		System.out.print("ACT Science?");
		sum += check(36) - 1;
		sum /= 1.4;
		return sum;
	}

	//calculates the GPA
	public static double GPA(){
		double sum = 0.0;
		System.out.print("Overall GPA?");
		sum += in.nextDouble();
		System.out.print("Maximum GPA?");
		sum = check((int)sum, in.nextDouble())*100;
		return sum;
	}

	//checks if score is greater than maximum possible points
	public static double check(double max){
		double score = in.nextDouble();
		if(score > max){
			exit();
		}
		return score;
	}

	//checks if score is greater than maximum possible points
	//and returns score divided by maximum possible points
	public static double check(double score, double max){
		if(score > max){
			exit();
		}
		return score/max;
	}
	
	//Tells the user they're a cheater and exits the program
	public static void exit(){
		System.out.println("YOU'RE A CHEATER");
		System.exit(0);

	}
}