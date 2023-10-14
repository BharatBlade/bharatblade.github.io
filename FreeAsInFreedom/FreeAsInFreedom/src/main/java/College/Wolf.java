package College;
public class Wolf implements Critter {	
	char s = 'W';
	public char getChar(){
		return s;
	}
	public int getMove(CritterInfo info){
		if(info.getNeighbor(NORTH) != '.'){
			return helper(info, SOUTH, WEST, EAST);
		}
		else if(info.getNeighbor(WEST) != '.'){
			return helper(info, EAST, NORTH, SOUTH);
		}
		else if(info.getNeighbor(EAST) != '.'){
			return helper(info, WEST, NORTH, SOUTH);
		}
		else if(info.getNeighbor(SOUTH) != '.'){
			return helper(info, NORTH, WEST, EAST);
		}
		s = 'W';
		return CENTER;
	}
	public int helper(CritterInfo info, int a, int b, int c){
		s = ' ';
		if(info.getNeighbor(a) != '.'){
			if(info.getNeighbor(b) != '.'){
				return c;
			}
			return b;
		}
		return a;
	}
}