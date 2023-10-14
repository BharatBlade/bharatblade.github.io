import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import net.sf.jniinchi.INCHI_BOND_TYPE;
import net.sf.jniinchi.JniInchiAtom;
import net.sf.jniinchi.JniInchiBond;
import net.sf.jniinchi.JniInchiInputInchi;
import net.sf.jniinchi.JniInchiOutputStructure;
import net.sf.jniinchi.JniInchiWrapper;

public class Test {
	public static HashMap<String, Character> mp = new HashMap<String, Character>();
	public static void main(String[]args) throws Exception {
		ArrayList<String> test = new ArrayList<String>(Arrays.asList("InChI=1/CH5N3.CH2O3/c2*2-1(3)4/h(H5,2,3,4);(H2,2,3,4)/p-2", "InChI=1/Al.Li.4H/q+3;+1;4*-1"));
		for(int n = 0; n < test.size() && (test.get(n).contains("*") || test.get(n).contains(".")); n++) {
			String inchi = test.get(n);
			test.remove(n);
			n--;
			System.out.println(inchi);
			inchi = inchi.replaceAll(";/", ";:/");
			inchi = inchi.replaceAll(";;", ";:;");
			inchi = inchi.replaceAll(";;", ";:;");
			inchi = inchi.replaceAll("c;", "c:;");
			inchi = inchi.replaceAll("h;", "h:;");
			System.out.println(inchi);
			ArrayList<ArrayList<String>> a = new ArrayList<ArrayList<String>>();
			if(inchi.contains("/c") && inchi.contains("/h"))
				inchi = inchi.substring(0, ordinalIndexOf(inchi, "/", 4));
			else if(inchi.contains("/c") || inchi.contains("/h"))
				inchi = inchi.substring(0, ordinalIndexOf(inchi, "/", 3));
			else
				inchi = inchi.substring(0, ordinalIndexOf(inchi, "/", 2));
			inchi = inchi.replace("/", " ");
			System.out.println(inchi);
			Scanner in = new Scanner(inchi);
			while(in.hasNext()) {
				String next = in.next();
				next = next.replaceAll("\\.", " ");
				next = next.replaceAll(";", " ");
				next = next.replaceAll(":", ";");
				next = next.replaceAll(";", " ; ");
				if(next.charAt(0) == 'c' || next.charAt(0) == 'h')
					next = next.substring(1);
				ArrayList<String> b = new ArrayList<String>();
				Scanner sc = new Scanner(next);
				while(sc.hasNext())
					b.add(sc.next());
				for(int i = 0; i < b.size(); i++)
					if(b.get(i).equals(";"))
						b.set(i, "");
				a.add(b);
			}
			for(int i = 0; i < a.get(1).size(); i++)
				if(a.get(1).get(i).charAt(0) > 48 && a.get(1).get(i).charAt(0) < 59) {
					int c = Integer.parseInt(a.get(1).get(i).substring(0, 1));
					String temp = a.get(1).get(i).substring(1);
					a.get(1).remove(i);
					i--;
					for(int j = 0; j < c; j++)
						a.get(1).add(i+1, temp);
				}
			for(int i = 2; i < a.size(); i++) {
				for(int j = 0; j < a.get(i).size(); j++) {
					if(!a.get(i).get(j).equals("") && a.get(i).get(j).charAt(0) > 48 && a.get(i).get(j).charAt(0) < 59 && a.get(i).get(j).substring(1, 2).equals("*")) {
						int c = Integer.parseInt(a.get(i).get(j).substring(0, 1));
						String temp = a.get(i).get(j).substring(2);
						a.get(i).remove(j);
						j--;
						for(int k = 0; k < c; k++)
							a.get(i).add(j+1, temp);
					}
				}
			}
			for(int i = 0; i < a.size(); i++)
				System.out.println(a.get(i));
			String [] starts = {"/", "/c", "/h"};
			for(int i = 0; i < a.get(1).size(); i++) {
				inchi = a.get(0).get(0);
				for(int j = 1; j < a.size(); j++) {
					if(!a.get(j).get(i).equals(""))
						inchi += starts[j-1] + a.get(j).get(i);
				}
				test.add(inchi);
			}
		}
		System.out.println(test);
		//System.out.println(a.get(2));
		//System.out.println(metalIon(new ArrayList<String>(Arrays.asList("InChI=1/C2H4O2.Ag/c1-2(3)4;/h1H3,(H,3,4);/q;+1/p-1", "InChI=1/2K.H2O3S/c;;1-4(2)3/h;;(H2,1,2,3)/q2*+1;/p-2", "InChI=1/C2H4O2.Ag/c1-2(3)4;/h1H3,(H,3,4);/q;+1/p-1", "InChI=1/2K.H2O3S2/c;;1-5(2,3)4/h;;(H2,1,2,3,4)/q2*+1;/p-2", "InChI=1/C2H4O2.Ag/c1-2(3)4;/h1H3,(H,3,4);/q;+1/p-1"))));
				//TreeMap<String, Integer> map = new TreeMap<String, Integer>();
				/*Set<String> set = new HashSet<String>();
				PrintStream keyCode = new PrintStream(new File("keyCode.txt"));
				int count = 0;
				int counter = 0;
				Scanner in = new Scanner(database4b);
				while(in.hasNextLine()) {
					String line = in.nextLine();
					line = line.substring(0, line.indexOf("|"));
					Scanner sc = new Scanner(line);
					while(sc.hasNext()) {
						String next = sc.next();
						set.add(next);
					}
					counter++;
					System.out.println(counter);
					sc.close();
				}
				Iterator<String> iterator = set.iterator();
				while (iterator.hasNext()) {
					keyCode.println(iterator.next() + " " + count);
					count++;
			    }
				in.close();
				keyCode.close();
				Scanner in = new Scanner(new File("keyCode.txt"));
				while(in.hasNext()){
					map.put(in.next(), in.nextInt());
				}
				in.close();
				PrintStream database5b = new PrintStream(new File("database5b.txt"));
				in = new Scanner(database4b);
				
				int counter = 0;
				while(in.hasNextLine()) {
					String line = in.nextLine();
					String id = line.substring(line.indexOf("|"));
					line = line.substring(0, line.indexOf("|"));
					Scanner sc = new Scanner(line);
					String temp = "";
					while(sc.hasNext()) {
						String next = sc.next();
						Iterator<Entry<String, Integer>> it = map.entrySet().iterator();
						while(it.hasNext()){
							Map.Entry<String, Integer> pair = (Map.Entry<String, Integer>)it.next();
							if(next.equals(pair.getKey().toString())) {
								temp += pair.getValue().toString() + " ";
							}
						}
					}
					sc.close();
					database5b.println(temp.substring(0, temp.length()-1) + id);
					counter++;
					System.out.println(counter);
				}
				in.close();*/
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
		
		for(int i = 0; i < atoms2.size(); i++){
			String [] temp = new String[atoms2.get(i).bonds.size()];
			for(int j = 0; j < atoms2.get(i).bonds.size(); j++){
				String [] bondAtoms = new String[atoms2.get(i).bonds.get(j).bonds.size()];
				for(int k = 0; k < atoms2.get(i).bonds.get(j).bonds.size(); k++)
					bondAtoms[k] = atoms2.get(i).bonds.get(j).bonds.get(k) + "=" + atoms2.get(i).bonds.get(j).bondsNum.get(k);
				Arrays.sort(bondAtoms);
				temp[j] = atoms2.get(i).bonds.get(j) + "" + Arrays.toString(bondAtoms);
			}
			Arrays.sort(temp);
			inchiArray.add(atoms2.get(i).toString() + Arrays.toString(temp));
		}
		/*for(int i = 0; i < atoms2.size(); i++) {
			String [] temp = new String[atoms2.get(i).bonds.size()];
			for(int j = 0; j < atoms2.get(i).bonds.size(); j++) {
				temp[j] = atoms2.get(i).bonds.get(j) + "=" + atoms2.get(i).bondsNum.get(j);
			}
			Arrays.sort(temp);
			inchiArray.add(atoms2.get(i).toString() + Arrays.toString(temp));
		}
		Collections.sort(inchiArray);*/
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
		Iterator<?> it = mp.entrySet().iterator();
		difference = difference.replaceAll(", ", "");
		while(it.hasNext()){
			Map.Entry pair = (Map.Entry)it.next();
			difference = difference.replaceAll(pair.getKey().toString(), pair.getValue().toString());
		}
		difference = difference.replaceAll("=", "");
		difference = difference.replaceAll("\\[", "");
		difference = difference.replaceAll("\\]\\]", " ");
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
	public static int bondType(INCHI_BOND_TYPE a){
		if(a == INCHI_BOND_TYPE.SINGLE)
			return 1;
		else if(a == INCHI_BOND_TYPE.DOUBLE)
			return 2;
		else if(a == INCHI_BOND_TYPE.TRIPLE)
			return 3;
		return 0;
	}
	public static void mapSet(){
		ArrayList<Character> characters = new ArrayList<Character>();
		for(int i = 33; i < 127; i++) {
			if((char)i != '-' && (char)i != ',' && (char)i != '\\' && (i < 48 || i > 57) && (char)i != '=' && (char)i != '|')
				characters.add((char)i);
		}
		for(int i = 192; i < 251; i++) {
			if((char)i != '-' && (char)i != ',' && (char)i != '\\' && (i < 48 || i > 57) && (char)i != '=' && (char)i != '|')
				characters.add((char)i);
		}
		ArrayList<String> elements = new ArrayList<String>(Arrays.asList("He", "Ne", "Si", "Cl", "Ar", "Ge", "As", "Se", "Br", "Kr", "Sb", "Te", "Xe", "At", "Rn", "B", "C", "N", "O", "F", "P", "S", "I"));
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0; i < elements.size(); i++) {
			for(int j = 0; j < 6; j++) {
				list.add(elements.get(i)+j);
			}
		}
		for(int i = 0; i < list.size(); i++) {
			mp.put(list.get(i), characters.get(i));
		}
	}
	public static ArrayList<ArrayList<String>> comb(ArrayList<String> array) {
		ArrayList<ArrayList<String>> a = new ArrayList<ArrayList<String>>();
	    for (short i = (short) (array.size() - 1); i > -1; i--) {
	    	a.add(new ArrayList<String>(Arrays.asList(array.get(i))));
	    	System.out.println(1 << (array.size()-i-1));
	        for (int j = 1; j < 1 << (array.size()-i-1); j++) {
	        	ArrayList<String> b = new ArrayList<String>();
	        	b.add(array.get(i));
	        	b.addAll(a.get(j-1));
	        	Collections.sort(b);
	        	a.add(b);
	        }
	    }
	    return a;
	}
	public static int ordinalIndexOf(String s, String sub, int n) {
	    int p=s.indexOf(sub);
	    while(--n > 0 && p != -1)
	        p=s.indexOf(sub, p+1);
	    return p;
	}
}