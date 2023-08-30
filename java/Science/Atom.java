package Science;
import java.util.ArrayList;

public class Atom {
	public String element;
	public ArrayList<Atom> bonds;
	public int lonePairs;
	public double x;
	public double y;
	public double z;
	
	public Atom(){
		element = "";
		bonds = new ArrayList<Atom>();
		x = 0;
		y = 0;
		z = 0;
	}
	
	public Atom(String element) {
	    this.element = element;
	    bonds = new ArrayList<Atom>();
		x = 0;
		y = 0;
		z = 0;
    }
	
    public Atom(String element, Atom next) {
        this.element = element;
        bonds.add(next);
		x = 0;
		y = 0;
		z = 0;
    }
    
    public String toString(){
    	return element;
    }
}