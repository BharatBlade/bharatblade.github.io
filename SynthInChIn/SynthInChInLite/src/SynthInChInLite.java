import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import net.sf.jniinchi.*;

public class SynthInChInLite {
	public static HashMap<String, Character> mp = new HashMap<String, Character>();
	public static void main(String[]args) throws Exception{
		//ArrayList<String> reactants = getInChI("reactants");
		//ArrayList<String> products = getInChI("products");
		mapSet();
		System.out.println(differences(new ArrayList<String>(Arrays.asList("InChI=1S/C6H5NO2/c8-7(9)6-4-2-1-3-5-6/h1-5H")), new ArrayList<String>(Arrays.asList("InChI=1S/C6H7N/c7-6-4-2-1-3-5-6/h1-5H,7H2"))));

		File database3 = new File("database3.txt");
		File database4 = new File("database4.txt");
		
		
		//search(differences(reactants, products), database4, database3);
		
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
		difference = difference.replaceAll("\\]\\]\\]", " ");
		difference = difference.replaceAll("\\]", " ");
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
	public static void search(String givenDifferences, File masterReactions, File sortedReactions) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(masterReactions), "UTF8"));
		while(in.ready()){
			String parentLine = in.readLine();
			if(parentLine.contains(givenDifferences)){
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
				while(count < max+1){
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
	public static void reactionCompare(ArrayList<String> reactions){
		ArrayList<String> reactionDifference = new ArrayList<String>();
		for(int i = 0; i < reactions.size(); i++){
			Scanner in = new Scanner(reactions.get(i));
			while(in.hasNext()) {
				String temp = in.next();
				if(reactionDifference.isEmpty()) {
					reactionDifference.add(temp);
				}
				else {
					for(int j = 0; j < reactionDifference.size(); j++) {
						if(reactionDifference.get(j).substring(0, 1).equals("-") && !temp.substring(0, 1).equals("-")){
							if(reactionDifference.get(j).substring(1).equals(temp)) {
								reactionDifference.remove(j);
								break;
							}
							else if(j == reactionDifference.size()-1) {
								reactionDifference.add(temp);
								break;
							}
						}
						else if(!reactionDifference.get(j).substring(0, 1).equals("-") && temp.substring(0, 1).equals("-")){
							if(temp.substring(1).equals(reactionDifference.get(j))){
								reactionDifference.remove(j);
								break;
							}
							else if(j == reactionDifference.size()-1) {
								reactionDifference.add(temp);
								break;
							}
						}
						else if(j == reactionDifference.size()-1) {
							reactionDifference.add(temp);
							break;
						}
					}
				}
			}
			in.close();
		}
		Collections.sort(reactionDifference);
		System.out.println(reactionDifference);
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
}