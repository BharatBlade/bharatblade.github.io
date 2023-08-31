package Science;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

public class Molecule {
	public static void main(String[]args){
		ArrayList<Atom> molecule = new ArrayList<Atom>();
		TreeMap<String, Integer> covalentRadii = new TreeMap<String, Integer>() {{put("H", 31);put("He", 28);put("Li", 128);put("Be", 96);put("B", 84);put("C", 76);put("N", 71);put("O", 66);put("F", 57);put("Ne", 58);put("Na", 166);put("Mg", 141);put("Al", 121);put("Si", 111);put("P", 107);put("S", 105);put("Cl", 102);put("Ar", 106);put("K", 203);put("Ca", 176);put("Sc", 170);put("Ti", 160);put("v", 153);put("Cr", 139);put("Mn", 150);put("Fe", 142);put("Co", 138);put("Ni", 124);put("Cu", 132);put("Zn", 122);put("Ga", 122);put("Ge", 120);put("As", 119);put("Se", 120);put("Br", 120);put("Kr", 116);put("Rb", 220);put("Sr", 195);put("Y", 190);put("Zr", 175);put("Nb", 164);put("Mo", 154);put("Tc", 147);put("Ru", 146);put("Rh", 142);put("Pd", 139);put("Ag", 145);put("Cd", 144);put("In", 142);put("Sn", 139);put("Sb", 139);put("Te", 138);put("I", 139);put("Xe", 140);put("Cs", 244);put("Ba", 215);put("La", 207);put("Ce", 204);put("Pr", 203);put("Nd", 201);put("Pm", 199);put("Sm", 198);put("Eu", 198);put("Gd", 196);put("Tb", 194);put("Dy", 192);put("Ho", 192);put("Er", 189);put("Tm", 190);put("Yb", 187);put("Lu", 187);put("Hf", 175);put("Ta", 170);put("W", 162);put("Re", 151);put("Os", 144);put("Ir", 141);put("Pt", 136);put("Au", 136);put("Hg", 132);put("Tl", 145);put("Pb", 146);put("Bi", 148);put("Po", 140);put("At", 150);put("Rn", 150);put("Fr", 260);put("Ra", 221);put("Ac", 215);put("Th", 206);put("Pa", 200);put("U", 196);put("Np", 190);put("Pu", 187);put("Am", 180);put("Cm", 169);}};
		Atom c1 = new Atom("C");
		Atom h1 = new Atom("H");
		Atom h2 = new Atom("H");
		Atom h3 = new Atom("H");
		Atom h4 = new Atom("H");
		molecule.add(c1);molecule.add(h1);molecule.add(h2);molecule.add(h3);molecule.add(h4);
		c1.bonds.add(h1);
		c1.bonds.add(h2);
		c1.bonds.add(h3);
		c1.bonds.add(h4);
		for(int i = 0; i < molecule.size(); i++){
			for(int j = 0; j < molecule.get(i).bonds.size(); j++){
				molecule.get(i).bonds.get(j).x += covalentRadii.get(molecule.get(i).bonds.get(j).element);
				
			}
		}
	}
	public static double distance(Atom a, Atom b){
		return Math.sqrt(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2) + Math.pow(b.z - a.z, 2));
	}
	public static double angle(Atom parent, Atom a, Atom b){
		return Math.acos(( (a.x-parent.x)*(b.x-parent.x) + (a.y-parent.y)*(b.y-parent.y) + (a.z-parent.z)*(b.z-parent.z) )/(distance(parent, a)*distance(parent, b)))*(180/Math.PI);
	}
}