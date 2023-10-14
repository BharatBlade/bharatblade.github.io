import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
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

public class SynthInChIn {
	public static HashMap<String, Character> mp = new HashMap<String, Character>();
	public static void main(String[]args) throws Exception{
		//ArrayList<String> reactants = getInChI("reactants");
		//ArrayList<String> products = getInChI("products");
		mapSet();
		System.out.println(InchiArray("InChI=1S/C4H10O/c1-2-3-4-5/h5H,2-4H2,1H3"));
		System.out.println(differences(new ArrayList<String>(Arrays.asList("InChI=1S/C4H10O/c1-2-3-4-5/h5H,2-4H2,1H3")), new ArrayList<String>(Arrays.asList("InChI=1S/C4H9Cl/c1-2-3-4-5/h2-4H2,1H3"))));
		System.out.println(differences(new ArrayList<String>(Arrays.asList("InChI=1S/C3H8O/c1-2-3-4/h4H,2-3H2,1H3")), new ArrayList<String>(Arrays.asList("InChI=1S/C3H7Cl/c1-2-3-4/h2-3H2,1H3"))));
		/*File folder = new File("G:\\1976-2013_USPTOgrants_CML\\grants\\");
		File data = new File("database.txt");
		File sortedData = new File("sortedDatabase.txt");
		File splitData = new File("masterDatabase.txt");
		database(folder, new PrintStream(data));
		sortedDatabase(data, sortedData);
		splitDatabase(sortedData);
		search(differences(reactants, products), splitData, sortedData);*/
	}
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
		difference = difference.replaceAll("\\]\\]", " ");
		difference = difference.replaceAll("\\]", "");
		return difference;
	}
	public static void database(File folder, PrintStream out) throws Exception{
		ArrayList<File> array = new ArrayList<File>(Arrays.asList(folder.listFiles()));
		for(int i = 0; i < array.size(); i++){
			if(array.get(i).isDirectory())
				database(array.get(i), out);
			else{
				BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(array.get(i)), "UTF8"));
				ArrayList<String> products = new ArrayList<String>();
				ArrayList<String> reactants = new ArrayList<String>();
				boolean reactant = false;
				boolean product = false;
				while(in.ready()){
					String line = in.readLine();
					if(line.contains("InChI")){
						if(reactant)
							reactants.add(line.substring(line.indexOf("InChI"), line.indexOf("\"/>")));
						else if(product)
							products.add(line.substring(line.indexOf("InChI"), line.indexOf("\"/>")));
					}
					else if(line.contains("<productList>"))
						product = true;
					else if(line.contains("</productList>"))
						product = false;
					else if(line.contains("<reactantList>"))
						reactant = true;
					else if(line.contains("</reactantList>"))
						reactant = false;
				}
				if(!reactants.isEmpty() && !products.isEmpty()){
					String temp = differences(reactants, products) + array.get(i).toString().substring(array.get(i).toString().indexOf("grants\\") + 6) + 
											" Reactants: " + reactants.toString().substring(1, reactants.toString().length()-1) + 
											" Products: " + products.toString().substring(1, products.toString().length()-1);
					out.println(temp);
				}
				in.close();
			}
		}
	}
	public static void sortedDatabase(File unsorted, File sorted) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(unsorted), "UTF8"));
		int count = 1;
		int fCount = 0;
		ArrayList<File> files = new ArrayList<File>();
		ArrayList<String> f = new ArrayList<String>();
		while(br.ready()){
			String line = br.readLine();
			if(line.charAt(0) == '\\')
				continue;
			f.add(line);
			if(count % 100000 == 0){
				File temp = new File(fCount + ".tmp");
				PrintStream p = new PrintStream(temp);
				Collections.sort(f);
				for(int i = 0; i < f.size(); i++)
					p.println(f.get(i));
				p.close();
				files.add(temp);
				f = new ArrayList<String>();
				fCount++;
			}
			count++;
		}
		br.close();
		File temp = new File(fCount + ".tmp");
		PrintStream p = new PrintStream(temp);
		for(int i = 0; i < f.size(); i++)
			p.println(f.get(i));
		p.close();
		files.add(temp);
		f = new ArrayList<String>();
		BufferedReader [] brs = new BufferedReader[files.size()];
		PrintStream db = new PrintStream(sorted);
		String [] incomingLines = new String[files.size()];
		for(int i = 0; i < brs.length; i++){
			brs[i] = new BufferedReader(new InputStreamReader(new FileInputStream(i + ".tmp"), "UTF8"));
			incomingLines[i] = brs[i].readLine();
		}
		boolean sort = true;
		while(sort){
			sort = false;
			int min = 0;
			for(int i = 0; i < brs.length; i++)
				if(incomingLines[min] != null && incomingLines[i] != null && incomingLines[min].compareTo(incomingLines[i]) > 0)
					min = i;
			db.println(incomingLines[min]);
			incomingLines[min] = brs[min].readLine();
			for(int i = 0; i < brs.length; i++)
				if(brs[i].ready())
					sort = true;
		}
		for(int i = 0; i < incomingLines.length; i++)
			if(incomingLines[i] != null){
				db.println(incomingLines[i]);
				incomingLines[i] = brs[i].readLine();
				i--;
			}
		db.close();
		for(int i = 0; i < brs.length; i++)
			brs[i].close();
		for(int i = 0; i < files.size(); i++)
			files.get(i).delete();
	}
	public static void splitDatabase(File sorted) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(sorted), "UTF8"));
		ArrayList<String> temp = new ArrayList<String>();
		int lineCount = 0;
		int startCount = 0;
		File mData = new File("masterDatabase.txt");
		PrintStream p = new PrintStream(mData);
		temp.add(br.readLine());
		while(br.ready()){
			String newLine = br.readLine();
			String reaction = temp.get(temp.size()-1).substring(0, temp.get(temp.size()-1).indexOf("\\"));
			if(newLine.substring(0, newLine.indexOf("\\")).equals(reaction)){
				temp.add(newLine);
				lineCount++;
			}
			else{
				if(temp.size() > 1)
					p.println(reaction + "|" + startCount + "-" + lineCount);
				else
					p.println(reaction + "|" + startCount);
				temp = new ArrayList<String>();
				if(br.ready()){
					temp.add(newLine);
					lineCount++;
					startCount = lineCount;
				}
				else
					break;
			}
		}
		br.close();
		p.close();
	}
	public static void search(String givenDifferences, File masterReactions, File sortedReactions) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(masterReactions), "UTF8"));
		while(in.ready()){
			String parentLine = in.readLine();
			if(parentLine.contains(givenDifferences)){
				String numbers = parentLine.substring(parentLine.indexOf("|")+1);
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(sortedReactions), "UTF8"));
				int count = 0;
				while(count < Integer.parseInt(numbers.substring(0, numbers.indexOf("-")))){
					br.readLine();
					count++;
				}
				while(count <= Integer.parseInt(numbers.substring(numbers.indexOf("-")+1))){
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
						l.setIcon(new ImageIcon(ImageIO.read(new URL("https://cactus.nci.nih.gov/chemical/structure/"+reactants.get(j)+"/image"))));
						fReactants.add(l);
					}
					JLabel arrow = new JLabel();
					arrow.setIcon(new ImageIcon(ImageIO.read(new URL("http://www.downloadclipart.net/medium/2554-right-black-arrow-3-clip-art.png"))));
					fReactants.add(arrow);
					for(int j = 0; j < products.size(); j++){
						JLabel l = new JLabel();
						l.setIcon(new ImageIcon(ImageIO.read(new URL("https://cactus.nci.nih.gov/chemical/structure/"+products.get(j)+"/image"))));
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
		mp.put("B0", 'A');mp.put("B1", 'B');mp.put("B2", 'C');mp.put("B3", 'D');mp.put("B4", 'E');mp.put("C0", 'F');mp.put("C1", 'G');mp.put("C2", 'H');mp.put("C3", 'I');mp.put("C4", 'J');
		mp.put("N0", 'K');mp.put("N1", 'L');mp.put("N2", 'M');mp.put("N3", 'N');mp.put("N4", 'O');mp.put("O0", 'P');mp.put("O1", 'Q');mp.put("O2", 'R');mp.put("O3", 'S');mp.put("O4", 'T');
		mp.put("F0", 'U');mp.put("F1", 'V');mp.put("F2", 'W');mp.put("F3", 'X');mp.put("F4", 'Y');mp.put("P0", 'Z');mp.put("P1", 'a');mp.put("P2", 'b');mp.put("P3", 'c');mp.put("P4", 'd');
		mp.put("S0", 'f');mp.put("S1", 'g');mp.put("S2", 'h');mp.put("S3", 'i');mp.put("S4", 'j');mp.put("I0", 'k');mp.put("I1", 'l');mp.put("I2", 'm');mp.put("I3", 'n');mp.put("I4", 'o');
		mp.put("Si0", 'p');mp.put("Si1", 'q');mp.put("Si2", 'r');mp.put("Si3", 's');mp.put("Si4", 't');mp.put("Cl0", 'u');mp.put("Cl1", 'v');mp.put("Cl2", 'w');mp.put("Cl3", 'x');mp.put("Cl4", 'y');
		mp.put("As0", 'z');mp.put("As1", '!');mp.put("As2", '@');mp.put("As3", '#');mp.put("As4", '$');mp.put("Se0", '%');mp.put("Se1", '^');mp.put("Se2", '&');mp.put("Se3", '*');mp.put("Se4", '(');
		mp.put("Br0", ')');mp.put("Br1", '_');mp.put("Br2", '+');mp.put("Br3", '.');mp.put("Br4", '<');mp.put("Te0", '>');mp.put("Te1", '?');mp.put("Te2", ':');mp.put("Te3", ';');mp.put("Te4", '"');
	}
	public static String atomData(JniInchiAtom a){
		return a.getElementType() + a.getImplicitH();
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
}