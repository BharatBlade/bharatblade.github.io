import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import net.sf.jniinchi.INCHI_BOND_TYPE;
import net.sf.jniinchi.JniInchiAtom;
import net.sf.jniinchi.JniInchiBond;
import net.sf.jniinchi.JniInchiInputInchi;
import net.sf.jniinchi.JniInchiOutputStructure;
import net.sf.jniinchi.JniInchiWrapper;

public class SynthInChIn2 {
	public static HashMap<String, Character> mp = new HashMap<String, Character>();
	public static void main(String[]args) throws Exception {
		mapSet();
		ArrayList<String> reactants1 = new ArrayList<String>(Arrays.asList("InChI=1S/C3H8O/c1-2-3-4/h4H,2-3H2,1H3"));
		ArrayList<String> products1 = new ArrayList<String>(Arrays.asList("InChI=1S/C3H7Cl/c1-2-3-4/h2-3H2,1H3"));
		System.out.println(differences(reactants1, products1));
		System.out.println(simplifyDifferences(differences(reactants1, products1), 0));
		System.out.println(simplifyDifferences(simplifyDifferences(differences(reactants1, products1), 0), 1));
	}
	public static ArrayList<String> InchiArray(String Inchi) throws Exception{
		JniInchiOutputStructure structure = JniInchiWrapper.getStructureFromInchi(new JniInchiInputInchi(Inchi));
		ArrayList<String> inchiArray = new ArrayList<String>();
		ArrayList<JniInchiAtom> atoms = new ArrayList<JniInchiAtom>();
		ArrayList<Atom> atoms2 = new ArrayList<Atom>();
		for(int i = 0; i < structure.getNumAtoms() && !structure.getAtom(i).getElementType().equals("H"); i++){
			JniInchiAtom atom = structure.getAtom(i);
			atoms.add(atom);
			atoms2.add(new Atom(atom.getImplicitH(), atom.getElementType()));
		}
		for(int i = 0; i < structure.getNumBonds() && !structure.getBond(i).getOriginAtom().getElementType().equals("H") && !structure.getBond(i).getTargetAtom().getElementType().equals("H"); i++){
			JniInchiBond bond = structure.getBond(i);
			int o = 0;
			for(int j = 0; j < atoms.size(); j++){
				if(bond.getOriginAtom().equals(atoms.get(j))){
					o = j;
					break;
				}
			}
			for(int j = 0; j < atoms.size(); j++){
				if(bond.getTargetAtom().equals(atoms.get(j))){
					atoms2.get(o).bonds.add(atoms2.get(j));
					atoms2.get(j).bonds.add(atoms2.get(o));
					atoms2.get(o).bondsNum.add(bondType(bond.getBondType()));
					atoms2.get(j).bondsNum.add(bondType(bond.getBondType()));
					break;
				}
			}
		}
		for(int i = 0; i < atoms2.size(); i++) {
			String [] temp = new String[atoms2.get(i).bonds.size()];
			for(int j = 0; j < atoms2.get(i).bonds.size(); j++) {
				String [] temp2 = new String[atoms2.get(i).bonds.get(j).bonds.size()];
				for(int k = 0; k < atoms2.get(i).bonds.get(j).bonds.size(); k++) {
					String [] bondAtoms = new String[atoms2.get(i).bonds.get(j).bonds.get(k).bonds.size()];
					for(int l = 0; l < atoms2.get(i).bonds.get(j).bonds.get(k).bonds.size(); l++) {
						bondAtoms[l] = atoms2.get(i).bonds.get(j).bonds.get(k).bonds.get(l) + "=" + atoms2.get(i).bonds.get(j).bonds.get(k).bondsNum.get(l);
					}
					Arrays.sort(bondAtoms);
					temp2[k] = atoms2.get(i).bonds.get(j).bonds.get(k) + "" + Arrays.toString(bondAtoms);
				}
				Arrays.sort(temp2);
				temp[j] = atoms2.get(i).bonds.get(j) + "" + Arrays.toString(temp2);
			}
			Arrays.sort(temp);
			inchiArray.add(atoms2.get(i).toString() + Arrays.toString(temp));
		}
		Collections.sort(inchiArray);
		return inchiArray;
	}
	public static String differences(ArrayList<String> reactants, ArrayList<String> products) throws Exception{
		ArrayList<String> reactantTotal = new ArrayList<String>();
		ArrayList<String> productTotal = new ArrayList<String>();
		for(int i = 0; i < reactants.size(); i++){
			if(reactants.get(i).toString().contains(".") || !reactants.get(i).toString().contains("/c"))
				continue;
			reactantTotal.addAll(InchiArray(reactants.get(i).toString()));
		}
		for(int i = 0; i < products.size(); i++){
			if(products.get(i).toString().contains(".") || !products.get(i).toString().contains("/c"))
				continue;
			productTotal.addAll(InchiArray(products.get(i).toString()));
		}
		for(int i = 0; i < reactantTotal.size(); i++)
			for(int j = 0; j < productTotal.size(); j++)
				if(reactantTotal.get(i).equals(productTotal.get(j))){
					reactantTotal.remove(i);
					productTotal.remove(j);
					i--;
					break;
				}
		String [] total = new String[reactantTotal.size() + productTotal.size()];
		for(int i = 0; i < reactantTotal.size(); i++)
			reactantTotal.set(i, "-"+reactantTotal.get(i));
		for(int i = 0; i < reactantTotal.size(); i++)
			total[i] = reactantTotal.get(i);
		for(int i = 0; i < productTotal.size(); i++)
			total[i + reactantTotal.size()] = productTotal.get(i);
		Arrays.sort(total);
		String difference = Arrays.toString(total);
		Iterator it = mp.entrySet().iterator();
		difference = difference.replaceAll(", ", "");
		while(it.hasNext()){
			Map.Entry pair = (Map.Entry)it.next();
			difference = difference.replaceAll(pair.getKey().toString(), pair.getValue().toString());
		}
		difference = difference.replaceAll("=", "");
		difference = difference.replaceAll("\\[", "");
		difference = difference.replaceAll("\\]\\]\\]", "\\]\\]\\] ");
		difference = difference.replaceAll("\\]", "");
		Scanner in = new Scanner(difference);
		ArrayList<String> diff = new ArrayList<String>();
		while(in.hasNext()) {
			diff.add(in.next());
		}
		in.close();
		Collections.sort(diff);
		difference = diff.toString();
		difference = difference.replaceAll(",", "");
		difference = difference.replaceAll("\\[", "");
		difference = difference.replaceAll("\\]", "");
		return difference;
	}
	public static String simplifyDifferences(String givenDifference, int layer) {
		ArrayList<String> total = new ArrayList<String>();
		ArrayList<String> diff = new ArrayList<String>();
		Scanner in = new Scanner(givenDifference);
		while(in.hasNext()) {
			diff.add(in.next());
		}
		for(int k = 0; k < diff.size(); k++) {
			String start = "";
			if(diff.get(k).substring(0, 1).equals("-")) {
				givenDifference = diff.get(k).substring(2-layer);
				start = diff.get(k).substring(0, 2-layer);
			}
			else {
				givenDifference = diff.get(k).substring(1-layer);
				start = diff.get(k).substring(0, 1-layer);
			}
			String parent = "";
			String child = "";
			String temp = "";
			ArrayList<String> parents = new ArrayList<String>();
			ArrayList<String> parentChild = new ArrayList<String>();
			ArrayList<String> temps = new ArrayList<String>();
			for(int i = 0; i < givenDifference.length(); i++) {
				if(!isNumber(givenDifference.substring(i, i+1))) {
					if(!isNumber(givenDifference.substring(i+1, i+2))) {
						if(!isNumber(givenDifference.substring(i+2, i+3))) {
							parent = givenDifference.substring(i, i+1);
							child = givenDifference.substring(i+1, i+2);
							i++;
							parents.add(parent);
							parentChild.add(parent + child);
							temps.add(temp);
							temp = "";
						}
						else {
							child = givenDifference.substring(i, i+1);
							parents.add(parent);
							parentChild.add(child);
							temps.add(temp);
							temp = "";
						}
					}
					else {
						temp += givenDifference.substring(i, i+1);
					}
				}
				else {
					temp += givenDifference.substring(i, i+1);
				}
			}
			temps.add(temp);
			temps.remove(0);
			for(int i = 0; i < temps.size(); i++) {
				for(int j = 0; j < temps.get(i).length(); j++) {
					if(temps.get(i).substring(j, j+1).equals(parents.get(i))) {
						start += parentChild.get(i) + temps.get(i).substring(j+1, j+2);
						temps.set(i, temps.get(i).substring(j+1, j+2));
						break;
					}
				}
			}
			total.add(start);
		}
		Collections.sort(total);
		for(int i = 0; i < total.size(); i++) {
			for(int j = 0; j < total.size() && i != j; j++) {
				if(total.get(i).substring(0, 1).equals("-")) {
					if(total.get(i).equals("-" + total.get(j))) {
						if(i > j) {
							total.remove(i);
							total.remove(j);
						}
						else {
							total.remove(j);
							total.remove(i);
						}
						i--;
						break;
					}
				}
				else if(total.get(j).equals("-" + total.get(i))) {
					if(i > j) {
						total.remove(i);
						total.remove(j);
					}
					else {
						total.remove(j);
						total.remove(i);
					}
					i--;
					break;
				}
			}
		}
		return total.toString().substring(1, total.toString().length()-1).replaceAll(",", "");
	}
	public static void mapSet(){
		mp.put("B0", 'A');mp.put("B1", 'B');mp.put("B2", 'C');mp.put("B3", 'D');mp.put("B4", 'E');mp.put("C0", 'F');mp.put("C1", 'G');mp.put("C2", 'H');mp.put("C3", 'I');mp.put("C4", 'J');
		mp.put("N0", 'K');mp.put("N1", 'L');mp.put("N2", 'M');mp.put("N3", 'N');mp.put("N4", 'O');mp.put("O0", 'P');mp.put("O1", 'Q');mp.put("O2", 'R');mp.put("O3", 'S');mp.put("O4", 'T');
		mp.put("F0", 'U');mp.put("F1", 'V');mp.put("F2", 'W');mp.put("F3", 'X');mp.put("F4", 'Y');mp.put("P0", 'Z');mp.put("P1", 'a');mp.put("P2", 'b');mp.put("P3", 'c');mp.put("P4", 'd');
		mp.put("S0", 'f');mp.put("S1", 'g');mp.put("S2", 'h');mp.put("S3", 'i');mp.put("S4", 'j');mp.put("I0", 'k');mp.put("I1", 'l');mp.put("I2", 'm');mp.put("I3", 'n');mp.put("I4", 'o');
		mp.put("Si0", 'p');mp.put("Si1", 'q');mp.put("Si2", 'r');mp.put("Si3", 's');mp.put("Si4", 't');mp.put("Cl0", 'u');mp.put("Cl1", 'v');mp.put("Cl2", 'w');mp.put("Cl3", 'x');mp.put("Cl4", 'y');
		mp.put("As0", 'z');mp.put("As1", '!');mp.put("As2", '@');mp.put("As3", '#');mp.put("As4", '$');mp.put("Se0", '%');mp.put("Se1", '^');mp.put("Se2", '&');mp.put("Se3", '*');mp.put("Se4", '(');
		mp.put("Br0", ')');mp.put("Br1", '_');mp.put("Br2", '+');mp.put("Br3", '.');mp.put("Br4", '<');mp.put("Te0", '>');mp.put("Te1", '?');mp.put("Te2", ':');mp.put("Te3", ';');mp.put("Te4", '"');
	}
	public static int bondType(INCHI_BOND_TYPE a){
		if(a == INCHI_BOND_TYPE.SINGLE)
			return 1;
		else if(a == INCHI_BOND_TYPE.DOUBLE)
			return 2;
		else if(a == INCHI_BOND_TYPE.TRIPLE)
			return 3;
		return 0;
	}
	public static boolean isNumber(String a) {
		if( ((int)a.charAt(0) >= 48 && (int)a.charAt(0) <= 57)) 
			return true;
		return false;
	}
}
