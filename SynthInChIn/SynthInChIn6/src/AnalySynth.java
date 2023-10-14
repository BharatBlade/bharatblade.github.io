import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import net.sf.jniinchi.INCHI_BOND_TYPE;
import net.sf.jniinchi.JniInchiAtom;
import net.sf.jniinchi.JniInchiBond;
import net.sf.jniinchi.JniInchiInputInchi;
import net.sf.jniinchi.JniInchiOutputStructure;
import net.sf.jniinchi.JniInchiWrapper;

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
	public static ArrayList<String> InchiArray(String Inchi, int complex, boolean bt) throws Exception{
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
			for(int j = 0; j < atoms.size(); j++)
				if(bond.getOriginAtom().equals(atoms.get(j))){
					o = j;
					break;
				}
			for(int j = 0; j < atoms.size(); j++)
				if(bond.getTargetAtom().equals(atoms.get(j))){
					atoms2.get(o).bonds.add(atoms2.get(j));
					atoms2.get(j).bonds.add(atoms2.get(o));
					atoms2.get(o).bondsNum.add(bondType(bond.getBondType()));
					atoms2.get(j).bondsNum.add(bondType(bond.getBondType()));
					break;
				}
		}
		if(complex == 2)
			for(int i = 0; i < atoms2.size(); i++){
				String [] temp = new String[atoms2.get(i).bonds.size()];
				for(int j = 0; j < atoms2.get(i).bonds.size(); j++){
					String [] bondAtoms = new String[atoms2.get(i).bonds.get(j).bonds.size()];
					for(int k = 0; k < atoms2.get(i).bonds.get(j).bonds.size(); k++) {
						if(bt)
							bondAtoms[k] = atoms2.get(i).bonds.get(j).bonds.get(k).toString() + "=" + atoms2.get(i).bonds.get(j).bondsNum.get(k);
						else
							bondAtoms[k] = atoms2.get(i).bonds.get(j).bonds.get(k).toString();
					}
					Arrays.sort(bondAtoms);
					temp[j] = atoms2.get(i).bonds.get(j) + "" + Arrays.toString(bondAtoms);
				}
				Arrays.sort(temp);
				inchiArray.add(atoms2.get(i).toString() + Arrays.toString(temp));
			}
		else if(complex == 1)
			for(int i = 0; i < atoms2.size(); i++){
				String [] temp = new String[atoms2.get(i).bonds.size()];
				for(int j = 0; j < atoms2.get(i).bonds.size(); j++) {
					if(bt)
						temp[j] = atoms2.get(i).bonds.get(j).toString() + "=" + atoms2.get(i).bondsNum.get(j);
					else
						temp[j] = atoms2.get(i).bonds.get(j).toString();
				}
				Arrays.sort(temp);
				inchiArray.add(atoms2.get(i).toString() + Arrays.toString(temp));
			}
		else
		 	for(int i = 0; i < atoms2.size(); i++){
		  		inchiArray.add(atoms2.get(i).toString());
		  	}
		Collections.sort(inchiArray);
		return inchiArray;
	}
	public static String differences(ArrayList<String> reactants, ArrayList<String> products, int complex, boolean bt) throws Exception{
		reactants = mim(reactants);
		products = mim(products);
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
		String [] total = new String[reactantTotal.size() + productTotal.size()];
		for(int i = 0; i < reactantTotal.size(); i++)
			reactantTotal.set(i, "-"+reactantTotal.get(i));
		for(int i = 0; i < reactantTotal.size(); i++)
			total[i] = reactantTotal.get(i);
		for(int i = 0; i < productTotal.size(); i++)
			total[i + reactantTotal.size()] = productTotal.get(i);
		Arrays.sort(total);
		String difference = Arrays.toString(total);
		difference = difference.replaceAll(", ", "");
		return difference;
	}
	
	public static String retroSynth(ArrayList<String> list) {
		for(int i = 0; i < list.size(); i++) {
			list.set(i, list.get(i).substring(1, list.get(i).length()-1));
		}
		ArrayList<ArrayList<String>> rxns = new ArrayList<ArrayList<String>>();
		ArrayList<String> difference = new ArrayList<String>();
		for(int i = 0; i < list.size(); i++) {
			ArrayList<String> rxn = new ArrayList<String>();
			String r = list.get(i);
			while(r.contains("]]")) {
				rxn.add(r.substring(0, r.indexOf("]]")+2));
				r = r.substring(r.indexOf("]]")+2);
			}
			rxns.add(rxn);
		}
		for(int i = 0; i < rxns.size()-1; i++) {
			for(int j = 0; j < rxns.get(i).size(); j++) {
				for(int k = 0; k < rxns.get(i+1).size(); k++) {
					if(("-" + rxns.get(i).get(j)).equals(rxns.get(i+1).get(k)) || rxns.get(i).get(j).equals("-" + rxns.get(i+1).get(k))){
						rxns.get(i+1).remove(k);
						rxns.get(i).remove(j);
						j--;
						break;
					}
				}
			}
		}
		for(int i = 0; i < rxns.size(); i++) {
			difference.addAll(rxns.get(i));
		}
		Collections.sort(difference);
		System.out.println(difference.toString().replaceAll(", ", ""));
		return difference.toString().replaceAll(", ", "");
	}
	
	public static void search(String givenDifferences, File masterReactions, File firstDatabase) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(masterReactions), "UTF8"));
		while(in.ready()){
			String parentLine = in.readLine();
			String reaction = parentLine.substring(0, parentLine.indexOf("|"));
			if(reaction.contains(givenDifferences)){
				String numbers = parentLine.substring(parentLine.indexOf("|")+1);
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(firstDatabase), "UTF8"));
				Scanner num = new Scanner(numbers);
				ArrayList<String> nums = new ArrayList<String>();
				while(num.hasNextLine())
					nums.add(num.next());
				num.close();
				while(br.ready()) {
					String line = br.readLine();
					if(nums.contains(line.substring(line.indexOf("|")+1))) {
						String rString = line.substring(line.indexOf("Reactants: ") + 11, line.indexOf(" Products:"));
						String pString = line.substring(line.indexOf("Products: ") + 10, line.indexOf("|"));
						ArrayList<String> reactants = new ArrayList<String>();
						ArrayList<String> products = new ArrayList<String>();
						Scanner rScanner = new Scanner(rString.substring(0, rString.length()));
						Scanner pScanner = new Scanner(pString.substring(0, pString.length()));
						while(rScanner.hasNext()){
							String temp = rScanner.next();
							if(temp.substring(temp.length()-1).equals(","))
								temp = temp.substring(0, temp.length()-1);
							reactants.add(temp);
						}
						while(pScanner.hasNext()){
							String temp = pScanner.next();
							if(temp.substring(temp.length()-1).equals(","))
								temp = temp.substring(0, temp.length()-1);
							products.add(temp);
						}
						JFrame fReactants = new JFrame();
						fReactants.setTitle(line.substring(line.indexOf("\\")+1, line.indexOf(" Reactants")));
						fReactants.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						fReactants.setLayout(new FlowLayout());
					    fReactants.setSize(1200,600);
						for(int j = 0; j < reactants.size(); j++){
							JLabel l = new JLabel();	
							l.setIcon(new ImageIcon(ImageIO.read(new URL("https://cactus.nci.nih.gov/chemical/structure/"+reactants.get(j)+"/image?format=gif"))));
							fReactants.add(l);
						}
						JLabel arrow = new JLabel();
						arrow.setIcon(new ImageIcon(ImageIO.read(new URL("http://www.downloadclipart.net/medium/2554-right-black-arrow-3-clip-art.png"))));
						fReactants.add(arrow);
						for(int j = 0; j < products.size(); j++){
							JLabel l = new JLabel();
							l.setIcon(new ImageIcon(ImageIO.read(new URL("https://cactus.nci.nih.gov/chemical/structure/"+products.get(j)+"/image?format=gif"))));
							fReactants.add(l);
						}
						fReactants.setVisible(true);
						rScanner.close();
						pScanner.close();
					}
				}
				br.close();
			}
		}
		in.close();
	}
	
	public static void retroSearch(String givenDifferences, ArrayList<String> list, int n) throws Exception {
		Scanner in = new Scanner(new File("output.txt"));
		while(in.hasNextLine()) {
			String line = in.nextLine();
			String r1 = line.substring(0, line.indexOf(" "));
			list.add(r1);
			if(list.size() > 1 && retroSynth(list).equals(givenDifferences)) {
				System.out.println(list);
				System.exit(0);
			}
			else if(n < 1) {
				in.close();
				return;
			}
			else {
				retroSearch(givenDifferences, list, n-1);
			}
		}
		in.close();
	}
	public static void rretroSearch(String givenDifferences, ArrayList<String> list, int n) throws Exception {
		Scanner in = new Scanner(new File("output.txt"));
		while(in.hasNextLine()) {
			String line = in.nextLine();
			String r1 = line.substring(0, line.indexOf(" "));
			list.add(r1);
			
			if(n > 0) {
				rretroSearch(givenDifferences, list, n-1);
			}
			else {
				
			}
		}
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
					for(int i = 0; i < b.size(); i++)
						if(b.get(i).equals(";"))
							b.set(i, "");
					a.add(b);
				}
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