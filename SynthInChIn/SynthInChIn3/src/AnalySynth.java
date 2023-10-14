import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
	public static ArrayList<Character> values = new ArrayList<Character>();
	public static ArrayList<String> keys = new ArrayList<String>();
	
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
	public static ArrayList<String> InchiArray(String Inchi, boolean complex) throws Exception{
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
		if(complex) {
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
		}
		else {
			for(int i = 0; i < atoms2.size(); i++){
				String [] temp = new String[atoms2.get(i).bonds.size()];
				for(int j = 0; j < atoms2.get(i).bonds.size(); j++){
					temp[j] = atoms2.get(i).bonds.get(j) + "=" + atoms2.get(i).bondsNum.get(j);
				}
				Arrays.sort(temp);
				inchiArray.add(atoms2.get(i).toString() + Arrays.toString(temp));
			}
		}
		Collections.sort(inchiArray);
		return inchiArray;
	}
	public static String differences(ArrayList<String> reactants, ArrayList<String> products, boolean complex) throws Exception{
		ArrayList<String> reactantTotal = new ArrayList<String>();
		ArrayList<String> productTotal = new ArrayList<String>();
		for(int i = 0; i < reactants.size(); i++){
			if(reactants.get(i).toString().contains(".") || !reactants.get(i).toString().contains("/c"))
				continue;
			reactantTotal.addAll(InchiArray(reactants.get(i).toString(), complex));
		}
		for(int i = 0; i < products.size(); i++){
			if(products.get(i).toString().contains(".") || !products.get(i).toString().contains("/c"))
				continue;
			productTotal.addAll(InchiArray(products.get(i).toString(), complex));
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
		for(int i = 0; i < keys.size(); i++) {
			difference = difference.replaceAll(keys.get(i), values.get(values.size()-i-1).toString());
		}
		difference = difference.replaceAll("=", "");
		difference = difference.replaceAll("\\[", "");
		if(complex)
			difference = difference.replaceAll("\\]\\]", " ");
		else
			difference = difference.replaceAll("\\]", " ");
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
	public static void search(String givenDifferences, File masterReactions, File sortedReactions) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(masterReactions), "UTF8"));
		System.out.println(givenDifferences);
		while(in.ready()){
			String parentLine = in.readLine();
			String reaction = parentLine.substring(0, parentLine.indexOf("|"));
			if(reaction.contains(givenDifferences)){
				System.out.println(reaction);
				String numbers = parentLine.substring(parentLine.indexOf("|")+1);
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(sortedReactions), "UTF8"));
				int count = 0;
				int min = 0;
				int max = 0;
				try {
					min = Integer.parseInt(numbers.substring(0, numbers.indexOf("-")));
					max = Integer.parseInt(numbers.substring(numbers.indexOf("-")+1));
				}
				catch(Exception e) {
					min = Integer.parseInt(numbers);
					max = Integer.parseInt(numbers);
				}
				System.out.println(numbers);
				while(count < min-1){
					br.readLine();
					count++;
				}
				while(count < max){
					String line = br.readLine();
					String rString = line.substring(line.indexOf("Reactants: ") + 11, line.indexOf(" Products:"));
					String pString = line.substring(line.indexOf("Products: ") + 10);
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
					count++;
				}
				br.close();
			}
		}
		in.close();
	}
	public static void mapSet(){
		for(int i = 33; i < 127; i++) {
			if((char)i != '-' && (char)i != ',' && (char)i != '\\' && (i < 48 || i > 57) && (char)i != '=' && (char)i != '|')
				values.add((char)i);
		}
		for(int i = 192; i < 251; i++) {
			if((char)i != '-' && (char)i != ',' && (char)i != '\\' && (i < 48 || i > 57) && (char)i != '=' && (char)i != '|')
				values.add((char)i);
		}
		ArrayList<String> elements = new ArrayList<String>(Arrays.asList("He", "Ne", "Si", "Cl", "Ar", "Ge", "As", "Se", "Br", "Kr", "Sb", "Te", "Xe", "At", "Rn", "B", "C", "N", "O", "F", "P", "S", "I"));
		for(int i = 0; i < elements.size(); i++) {
			for(int j = 0; j < 6; j++) {
				keys.add(elements.get(i)+j);
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
	public static boolean isNumber(String a) {
		if( ((int)a.charAt(0) >= 48 && (int)a.charAt(0) <= 57)) 
			return true;
		return false;
	}
}
