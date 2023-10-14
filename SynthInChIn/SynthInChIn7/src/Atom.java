import java.util.ArrayList;

public class Atom {
	public ArrayList<Atom> bonds = new ArrayList<Atom>();
	public ArrayList<Integer> bondsNum = new ArrayList<Integer>();
	public int hydrogens = 0;
	public String element;
	
	public Atom(int hydrogens, String element) {
        this.hydrogens = hydrogens;
        this.element = element;
    }
	
	public String toString(){
    	return String.valueOf(element + hydrogens);
    }
}