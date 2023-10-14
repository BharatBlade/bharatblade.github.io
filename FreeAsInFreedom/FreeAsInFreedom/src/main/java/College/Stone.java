package College;

public class Stone implements Critter {
	public char getChar(){
		return 'S';
	}
	public int getMove(CritterInfo info){
		return CENTER;
	}
}
