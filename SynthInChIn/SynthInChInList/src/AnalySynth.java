import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.openbabel.OBAtom;
import org.openbabel.OBAtomBondIter;
import org.openbabel.OBBond;
import org.openbabel.OBConversion;
import org.openbabel.OBMol;

public class AnalySynth {
	
	public static ArrayList<String> getInChI(String a) throws Exception{
		ArrayList<String> InChIs = new ArrayList<String>();
		JFrame open = new JFrame();
		FileDialog fd = new FileDialog(open, "Text file of InChI " + a, FileDialog.LOAD);
		fd.setVisible(true);
		File f = new File(fd.getDirectory() + fd.getFile());
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF8"));
		while(br.ready())
			InChIs.add(br.readLine());
		fd.dispose();
		open.dispose();
		br.close();
		return InChIs;
	}
	
	public static ArrayList<String> InchiArray(String InChI, int complex, boolean bt) throws Exception{
		OBConversion conv = new OBConversion();
		OBMol mol = new OBMol();
		conv.SetInFormat("inchi");
		conv.ReadString(mol, InChI);
		int size = (int) mol.NumAtoms();
		ArrayList<String> inchiArray = new ArrayList<String>();
		for(int i = 1; i <= size; i++) {
			OBAtom atom = mol.GetAtom(i);
			inchiArray.add(atom.GetType().replaceAll("\\d","").replace("ar", "") + atom.ImplicitHydrogenCount() + InchiArrayHelper(atom, i, complex));
		}
		Collections.sort(inchiArray);
		return inchiArray;
	}
	
	public static String InchiArrayHelper(OBAtom atom, int i, int n) {
		String line = "";
		OBAtomBondIter iter = new OBAtomBondIter(atom);
		int size = atom.CountBondsOfOrder(1) + atom.CountBondsOfOrder(2) + atom.CountBondsOfOrder(3);
		for(int j = 0; j < size; j++) {
			OBBond bond = iter.next();
			OBAtom atom2 = bond.GetBeginAtom();
			if(atom2.GetIdx() == atom.GetIdx())
				atom2 = bond.GetEndAtom();
			if(n > 1)
				line += ("[" + atom2.GetType().replaceAll("\\d","").replace("ar", "") + atom2.ImplicitHydrogenCount() + InchiArrayHelper(atom2, j, n-1) + "]");
			else if(n == 1)
				line += ("[" + atom2.GetType().replaceAll("\\d","").replace("ar", "") + atom2.ImplicitHydrogenCount() + bond.GetBondOrder() + "]");
		}
		return line;
	}
	
	public static ArrayList<String> differences(ArrayList<String> reactants, ArrayList<String> products, int complex, boolean bt) throws Exception{
		ArrayList<String> reactantTotal = new ArrayList<String>();
		ArrayList<String> productTotal = new ArrayList<String>();
		for(int i = 0; i < reactants.size(); i++){
			if(!reactants.get(i).toString().contains("/c"))
				continue;
			reactantTotal.addAll(InchiArray(reactants.get(i).toString(), complex, bt));
		}
		for(int i = 0; i < products.size(); i++){
			if(!products.get(i).toString().contains("/c"))
				continue;
			productTotal.addAll(InchiArray(products.get(i).toString(), complex, bt));
		}
		for(int i = 0; i < reactantTotal.size(); i++)
			for(int j = 0; j < productTotal.size(); j++)
				if(reactantTotal.get(i).equals(productTotal.get(j))){
					reactantTotal.remove(i);
					productTotal.remove(j);
					i--;
					break;
				}
		ArrayList<String> total = new ArrayList<String>();
		for(int i = 0; i < reactantTotal.size(); i++)
			reactantTotal.set(i, "-"+reactantTotal.get(i));
		for(int i = 0; i < reactantTotal.size(); i++)
			total.add(reactantTotal.get(i));
		for(int i = 0; i < productTotal.size(); i++)
			total.add(productTotal.get(i));
		Collections.sort(total);
		return total;
	}
	
	public static ArrayList<String> retroSynth(ArrayList<ArrayList<String>> reaction) {
		ArrayList<ArrayList<String>> temptemp = new ArrayList<ArrayList<String>>();
		for(int i = 0; i < reaction.size(); i++) {
			ArrayList<String> temp = new ArrayList<String>();
			temp.addAll(reaction.get(i));
			temptemp.add(temp);
		}
		for(int i = 0; i < temptemp.size()-1; i++)
			for(int j = 0; j < temptemp.get(i).size(); j++)
				for(int k = 0; k < temptemp.get(i+1).size(); k++)
					if(("-" + temptemp.get(i).get(j)).equals(temptemp.get(i+1).get(k)) || temptemp.get(i).get(j).equals("-" + temptemp.get(i+1).get(k))){
						temptemp.get(i+1).remove(k);
						temptemp.get(i).remove(j);
						j--;
						break;
					}
		ArrayList<String> total = new ArrayList<String>();
		for(int i = 0; i < temptemp.size(); i++)
			total.addAll(temptemp.get(i));
		Collections.sort(total);
		return total;
	}
	
	public static void rSearch(ArrayList<ArrayList<String>> list, ArrayList<String> names, ArrayList<String> reactants, ArrayList<String> givenDifferences, int n, int complex) throws Exception {
		ArrayList<String> r = new ArrayList<String>();
		for(int i = 0; i < reactants.size(); i++)
			r.addAll(InchiArray(reactants.get(i), complex, true));
		helper(list, names, new ArrayList<ArrayList<String>>(), new ArrayList<String>(), r, givenDifferences, n, complex);
	}
	
	public static void helper(ArrayList<ArrayList<String>> list, ArrayList<String> names, ArrayList<ArrayList<String>> current, ArrayList<String> ids, ArrayList<String> reactants, ArrayList<String> givenDifferences, int n, int complex) throws Exception {
		for(int i = 0; i < list.size() && n >= 0; i++) {
			ArrayList<String> temp = new ArrayList<String>();
			temp.addAll(list.get(i));
			current.add(temp);
			ids.add(names.get(i));
			if(!helper2(current, reactants)) {
				current.remove(current.size()-1);
				ids.remove(ids.size()-1);
				continue;
			}
			if(retroSynth(current).toString().equals(givenDifferences.toString()))
				JOptionPane.showMessageDialog(null, ids, "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
			helper(list, names, current, ids, reactants, givenDifferences, n-1, complex);
			current.remove(current.size()-1);
			ids.remove(ids.size()-1);
		}
	}
	
	public static boolean helper2 (ArrayList<ArrayList<String>> reaction, ArrayList<String> reactants) throws Exception {
		ArrayList<ArrayList<String>> temptemp = new ArrayList<ArrayList<String>>();
		for(int i = 0; i < reaction.size(); i++) {
			ArrayList<String> temp = new ArrayList<String>();
			temp.addAll(reaction.get(i));
			temptemp.add(temp);
		}
		ArrayList<String> temp = new ArrayList<String>();
		temp.addAll(reactants);
		for(int i = 0; i < temptemp.size(); i++)
			for(int j = 0; j < temptemp.get(i).size(); j++)
				if(temptemp.get(i).get(j).charAt(0) == '-' && !temp.contains(temptemp.get(i).get(j).substring(1)))
					return false;
				else if(temptemp.get(i).get(j).charAt(0) == '-') {
					for(int k = 0; k < temp.size(); k++)
						if(temptemp.get(i).get(j).substring(1).equals(temp.get(k))) {
							temptemp.get(i).remove(j);
							temp.remove(k);
							j--;
							break;
						}
				}
				else
					temp.add(temptemp.get(i).get(j));
		return true;
	}
	
	public static ArrayList<String> mim(ArrayList<String> test){
		ArrayList<String> total = new ArrayList<String>();
		for(int n = 0; n < test.size(); n++) {
			if(test.get(n).contains("*") || test.get(n).contains(".")) {
				String inchi = test.get(n);
				if(!inchi.substring(inchi.length()-1).equals("/"))
					inchi += "/";
				inchi = inchi.replaceAll(";/", ";:/");
				inchi = inchi.replaceAll(";;", ";:;");
				inchi = inchi.replaceAll(";;", ";:;");
				inchi = inchi.replaceAll("c;", "c:;");
				inchi = inchi.replaceAll("h;", "h:;");
				ArrayList<ArrayList<String>> a = new ArrayList<ArrayList<String>>();
				
				if(inchi.contains("/c") && inchi.contains("/h"))
					inchi = inchi.substring(0, ordinalIndexOf(inchi, "/", 4));
				else if(inchi.contains("/c") || inchi.contains("/h"))
					inchi = inchi.substring(0, ordinalIndexOf(inchi, "/", 3));
				else
					inchi = inchi.substring(0, ordinalIndexOf(inchi, "/", 2));
				inchi = inchi.replace("/", " ");
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
					sc.close();
					for(int i = 0; i < b.size(); i++)
						if(b.get(i).equals(";"))
							b.set(i, "");
					a.add(b);
				}
				in.close();
				for(int i = 0; i < a.get(1).size(); i++)
					if(a.get(1).get(i).charAt(0) > 48 && a.get(1).get(i).charAt(0) < 59) {
						int c = Integer.parseInt(a.get(1).get(i).substring(0, 1));
						String temp = a.get(1).get(i).substring(1);
						if(a.get(1).get(i).charAt(1) > 48 && a.get(1).get(i).charAt(1) < 59) {
							c = Integer.parseInt(a.get(1).get(i).substring(0, 2));
							temp = a.get(1).get(i).substring(2);
						}
						a.get(1).remove(i);
						i--;
						for(int j = 0; j < c; j++)
							a.get(1).add(i+1, temp);
					}
				for(int i = 2; i < a.size(); i++) {
					for(int j = 0; j < a.get(i).size(); j++) {
						if(a.get(i).get(j).length() > 2 && a.get(i).get(j).charAt(0) > 48 && a.get(i).get(j).charAt(0) < 59 && (a.get(i).get(j).substring(1, 2).equals("*") || a.get(i).get(j).substring(2, 3).equals("*"))) {
							int c = Integer.parseInt(a.get(i).get(j).substring(0, 1));
							String temp = a.get(i).get(j).substring(2);
							if(a.get(i).get(j).substring(2, 3).equals("*")) {
								c = Integer.parseInt(a.get(i).get(j).substring(0, 2));
								temp = a.get(i).get(j).substring(3);
							}
							a.get(i).remove(j);
							j--;
							for(int k = 0; k < c; k++)
								a.get(i).add(j+1, temp);
						}
					}
				}
				String [] starts = {"/", "/c", "/h"};
				for(int i = 0; i < a.get(1).size(); i++) {
					inchi = a.get(0).get(0);
					for(int j = 1; j < a.size(); j++) {
						if(!a.get(j).get(i).equals(""))
							inchi += starts[j-1] + a.get(j).get(i);
					}
					total.add(inchi);
				}
			}
			else {
				total.add(test.get(n));
			}
		}
		return total;
	}
	
	public static int ordinalIndexOf(String s, String sub, int n) {
	    int p=s.indexOf(sub);
	    while(--n > 0 && p != -1)
	        p=s.indexOf(sub, p+1);
	    return p;
	}
}