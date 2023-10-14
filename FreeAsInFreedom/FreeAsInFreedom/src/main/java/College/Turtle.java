package College;
public class Turtle implements Critter {
	private int choice = 0;
	private int loop = 0;
	public char getChar(){
		return 'T';
	}
	public int getMove(CritterInfo info){
		if(choice == 0){
			helper();
			return SOUTH;
		}
		else if(choice == 1){
			helper();
			return WEST;
		}
		else if(choice == 2){
			helper();
			return NORTH;
		}
		else{
			helper();
			return EAST;
		}
	}
	public void helper(){
		if(loop == 4){
			choice = 0;
			loop = -1;
		}
		loop++;
	}
}