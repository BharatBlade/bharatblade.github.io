package College;
import java.util.Scanner;

public class DayOfWeek {	

	public static Scanner in = new Scanner(System.in);
	
	//Program asks for the month, day, and year
	//Prints out the day of the week that corresponds to that date
	public static void main(String[]args){	
		System.out.print("What is the month (1 to 12)");
		int month = in.nextInt();
		System.out.print("What is the day (1 to 31)");
		int day = in.nextInt();
		System.out.print("What is the year (e.g., 2007)");
		int year = in.nextInt();
		System.out.println(daysToDate(totalDays(month, day, year)));
	}
	
	//Calculates number of days from all the months of the year given
	//except for the month given
	public static int monthsToDays(int months, int years){
		int sum = 0;
		for(int i = 1; i < months; i++){
			if(i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12){
				sum += 31;
			}
			else if(i == 2){
				sum += 28;
			}
			else{
				sum += 30;
			}
		}
		if(years % 4 == 0 && months < 3){
			sum -= 1;
		}
		return sum;
	}
	
	//calculates the total number of days since 1/1/1
	public static int totalDays(int month, int day, int year){
		return monthsToDays(month, year) + day + 365*year + year/4 - year/100 + year/400 - 1;
	}
	
	//takes number of days and determines day of the week
	public static String daysToDate(int totalDays){
		if(totalDays % 7 == 0){
			return "SUNDAY";			
		}
		else if(totalDays % 7 == 1){
			return "MONDAY";
		}
		else if(totalDays % 7 == 2){
			return "TUESDAY";
		}
		else if(totalDays % 7 == 3){
			return "WEDNESDAY";
		}
		else if(totalDays % 7 == 4){
			return "THURSDAY";
		}
		else if(totalDays % 7 == 5){
			return "FRIDAY";
		}
		else{
			return "SATURDAY";
		}
	}
}