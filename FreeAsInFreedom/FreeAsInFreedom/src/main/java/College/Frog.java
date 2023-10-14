package College;

public class Frog implements Critter {
	int choice = 1;
	int dir = 0;
	int [] arr = {NORTH, WEST, EAST, SOUTH};
	public char getChar(){
		return 'F';
	}
	public int getMove(CritterInfo info){
		if(choice == 1){
			choice = 3;
			dir = arr[(int)(Math.random()*arr.length)];
		}
		else{
			choice--;
		}
		return dir;
	}
}
