import java.util.ArrayList;

public class Atom {
	
	public ArrayList<Atom> bonds = new ArrayList<Atom>();	//bonds to other atoms
	public ArrayList<Integer> bondsNum = new ArrayList<Integer>(); //bond type of each bond
	public int hydrogens = 0; //number of hydrogens
	public String element; //element symbol
	
	//creates atom with specified number of bound hydrogens and element
	public Atom(int hydrogens, String element) {
        this.hydrogens = hydrogens;
        this.element = element;
    }
	
	//When printing each atom, 2 or 3 characters
	//will appear with the atom's symbol and the
	//number of hydrogens attached
	public String toString(){
    	return String.valueOf(element + hydrogens);
    }
	
	/*public int compareTo(Atom a){
		return this.toString().compareTo(a.toString());
	}*/
}