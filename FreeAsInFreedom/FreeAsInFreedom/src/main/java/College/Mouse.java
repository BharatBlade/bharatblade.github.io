package College;
public class Mouse implements Critter {
	boolean choice = false;
	public char getChar(){
		return 'M';
	}
	public int getMove(CritterInfo info){
		if(choice == false){
			choice = true;
			return WEST;
		}
		else{
			choice = false;
			return NORTH;
		}
	}
}