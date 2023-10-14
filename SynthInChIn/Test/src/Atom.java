import java.util.ArrayList;

public class Atom {
	
	public int data;					//atom ID number from the InChI
	public ArrayList<Atom> bonds
			= new ArrayList<Atom>();	//bonds to other atoms
	public ArrayList<Integer> bondsNum 	//bond type of each bond
			= new ArrayList<Integer>();
	public int hydrogens = 0;				//number of hydrogens
	public String element;				//element symbol
	
	//creates a default atom
	public Atom(){
		data = 0;
		bonds = new ArrayList<Atom>();
		hydrogens = 0;
	}
	
	//Creates a blank atom assigned with
	//a number from an InChI
	public Atom(int data) {
	    this.data = data;
	    bonds = new ArrayList<Atom>();
    }
    
	//Creates an atom assigned with a number
	//from an InChI with its bonded atoms
	public Atom(int data, Atom next) {
        this.data = data;
        bonds.add(next);
    }
    
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
	
	public int compareTo(Atom a){
		return this.toString().compareTo(a.toString());
	}
}