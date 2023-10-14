package College;


public class Bird implements Critter {
	public char getChar(){
		return 'B';
	}
	public int getMove(CritterInfo info){
		int [] arr = {NORTH, WEST, EAST, SOUTH};
		return arr[(int)(Math.random()*arr.length)];
	}
}
