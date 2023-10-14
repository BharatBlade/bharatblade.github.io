package College;
import java.util.Scanner;
public class RockPaperScissors {
	public static void main(String[]args){
		introduction();
		Scanner console = new Scanner(System.in);
		int duels = getDuels(console);
		System.out.println("Number of duels entered: " + duels);
		playManyGames(console, duels);
	}
	public static void introduction(){
		System.out.println("This�program�plays�duels�of�Rock�Paper�Scissors\n"+
							"against�the�computer.��You'll�type�in�your�guess\n"+
							"of�(R)ock,�(P)aper,�or�(S)cissors�and�try�to\n"+
							"beat�the�computer�as�many�times�as�you�can.\n");
	}
	public static int getDuels(Scanner console){
		System.out.print("Best�out�of�how�many�duels�(must�be�odd)?");
		int a = 0;
		while(a % 2 == 0){
			while(!console.hasNextInt()){
				System.out.println("Invalid�number�of�duels.��Type�a�positive�odd�number!");
				System.out.print("Best�out�of�how�many�duels�(must�be�odd)?");
				console.next();
			}
			a = console.nextInt();
			if(a % 2 == 0){
				System.out.println("Invalid�number�of�duels.��Type�a�positive�odd�number!");
				System.out.print("Best�out�of�how�many�duels�(must�be�odd)?");
			}
		}
		return a;
	}
	public static void playManyGames(Scanner in, int duels){
		int totalDuels = 0;
		int wins = 0;
		int losses = 0;
		do{
			for(int i = 1; i <= duels; i++){
				System.out.println("Duel " + i + ": ");
				int winner = playDuel(in);
				if(winner == 1){
					wins++;
				}
				else if(winner == 2){
					losses++;
				}
				totalDuels++;
			}
		}while(playAgain(in));
		reportStats(totalDuels, wins, losses);
	}
	public static boolean playAgain(Scanner in){
		System.out.println("Do�you�want�to�play�again?");
		if(in.next().toLowerCase().contains("y")){
			return true;
		}
		return false;
	}
	public static int playDuel(Scanner in){
		String u = getUserWeapon(in);
		String c = getRandomWeapon();
		if(u.equals(c)){
			System.out.println("Tie!");
			return 3;
		}
		else if((u.equals("R")&&c.equals("S")) || (u.equals("P")&&c.equals("R")) || (u.equals("S")&&c.equals("P"))){
			System.out.println("You win!");
			return 1;
		}
		System.out.println("You lose!");
		return 2;
	}
	public static String getUserWeapon(Scanner in){
		String s = "";
		System.out.print("Choose your weapon?");
		s = in.next();
		while(!"RSPrsp".contains(s)){
			System.out.print("Choose your weapon?");
			s = in.next();
		}
		return s.toUpperCase();
	}
	public static String getRandomWeapon(){
		int a = (int)(Math.random()*3);
		if(a == 0){
			System.out.println("I chose the weapon: R");
			return "R";
		}
		else if(a == 1){
			System.out.println("I chose the weapon: S");
			return "S";
		}
		System.out.println("I chose the weapon: P");
		return "P";
	}
	public static void reportStats(int totalDuels, int wins, int losses){
		System.out.println("Overall results:");
		System.out.println("total duels = " + totalDuels);
		System.out.println("wins = " + wins);
		System.out.println("losses = " + losses);
		System.out.println("ties = " + (totalDuels - losses - wins));
		System.out.println("win % = " + (double)((int)((double)wins/totalDuels*10000))/100);
	}
}