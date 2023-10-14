import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class SynthInChIn8 extends Database{
	public static void main(String[]args) throws Exception{
		System.loadLibrary("openbabel_java");
		File database1 = new File("database1.txt");
		File database2 = new File("database2.txt");
		File database3 = new File("database3.txt");
		File database4 = new File("database4.txt");
		/*databaseCreate(new File("C:\\Users\\johnj\\Downloads\\InChI\\1976-2013_USPTOgrants_CML\\grants\\1976"), new PrintStream(database1));
		databaseDiff(database1, database2, 2, true);
		sortedDatabase(database2, database3);
		splitDatabase(database3, database4);*/
		/*ArrayList<String> reactants = new ArrayList<String>(Arrays.asList("InChI=1S/C4H9Cl/c1-2-3-4-5/h2-4H2,1H3"));
		ArrayList<String> intermediates = new ArrayList<String>(Arrays.asList("InChI=1S/C4H8/c1-3-4-2/h3H,1,4H2,2H3"));
		ArrayList<String> products = new ArrayList<String>(Arrays.asList("InChI=1S/C4H10O/c1-2-3-4-5/h5H,2-4H2,1H3"));
		ArrayList<String> products2 = new ArrayList<String>(Arrays.asList("InChI=1S/C4H8O/c1-2-3-4-5/h4H,2-3H2,1H3"));*/
		
		
		/*ArrayList<String> reactants = new ArrayList<String>(Arrays.asList("InChI=1S/C4H9Cl/c1-2-3-4-5/h2-4H2,1H3"));
		ArrayList<String> products2 = new ArrayList<String>(Arrays.asList("InChI=1S/C4H10O/c1-2-3-4-5/h5H,2-4H2,1H3"));
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		ArrayList<String> names = new ArrayList<String>();
		File test = new File("inchi3.csv");
		Scanner in = new Scanner(database1);
		long time = System.currentTimeMillis();
		while(in.hasNextLine()) {
			ArrayList<String> r = new ArrayList<String>();
			ArrayList<String> p = new ArrayList<String>();
			String line = in.nextLine();
			String id = line.substring(0, line.indexOf("Reactants:"));
			Scanner in2 = new Scanner(line);
			boolean re = false;
			boolean pr = false;
			while(in2.hasNext()) {
				String token = in2.next();
				if(token.equals("Reactants:")) {
					re = true;
					token = in2.next();
				}
				else if(token.equals("Products:")) {
					pr = true;
					token = in2.next();
				}
				if(re && token.contains("/c") && !token.contains(".")) {
					if(token.substring(token.length()-1).equals(","))
						r.add(token.substring(0, token.length()-1));
					else {
						r.add(token);
						re = false;
					}
				}
				else if(pr && token.contains("/c") && !token.contains(".")) {
					if(token.substring(token.length()-1).equals(",")) {
						p.add(token.substring(0, token.length()-1));
					}
					else {
						p.add(token);
						pr = false;
					}
				}
			}
			in2.close();
			if(!r.isEmpty() && !p.isEmpty() && !differences(r, p, 2, true).isEmpty()) {
				list.add(differences(r, p, 2, true));
				names.add(id);
			}
		}
		in.close();
		*/
		
		long time = System.currentTimeMillis();
		ArrayList<String> reactants = getInChI("reactants");
		ArrayList<String> products2 = getInChI("products");
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		ArrayList<String> names = new ArrayList<String>();
		File test = new File("inchi3.csv");
		Scanner in = new Scanner(test);
		while(in.hasNextLine()) {
			String line = in.nextLine();
			line = line.replaceAll("\"", "");
			list.add(differences(new ArrayList<String>(Arrays.asList(line.substring(line.indexOf("%")+1, ordinalIndexOf(line, "%", 2)))), new ArrayList<String>(Arrays.asList(line.substring(ordinalIndexOf(line, "%", 2)+1))), 1, true));
			names.add(line.substring(0, line.indexOf("%")));
		}
		in.close();
		for(int i = 0; i < list.size(); i++) {
			Collections.sort(list.get(i));
		}		
		for(int i = 0; i < list.size(); i++) {
			for(int j = 0; j < list.size(); j++) {
				if(i != j && list.get(i).toString().equals(list.get(j).toString()) && names.get(i).equals(names.get(j))) {
					list.remove(j);
					names.remove(j);
					i--;
					break;
				}
			}
		}
		System.out.println(System.currentTimeMillis() - time);
		System.out.println("Chlorobutane to Butanal");
		time = System.currentTimeMillis();
		rSearch(list, names, reactants, differences(reactants, products2, 5, true), 2, 1);
		System.out.println(System.currentTimeMillis() - time);
	}
}