import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class SynthInChIn6 extends Database{
	public static void main(String[]args) throws Exception {
		File database1 = new File("database1.txt");
		File database2a = new File("database2a.txt");
		File database3a = new File("database3a.txt");
		File database4a = new File("database4a.txt");
		ArrayList<String> reactants = new ArrayList<String>(Arrays.asList("InChI=1S/C4H9Cl/c1-2-3-4-5/h2-4H2,1H3"));
		ArrayList<String> intermediates = new ArrayList<String>(Arrays.asList("InChI=1S/C4H8/c1-3-4-2/h3H,1,4H2,2H3"));
		ArrayList<String> products = new ArrayList<String>(Arrays.asList("InChI=1S/C4H10O/c1-2-3-4-5/h5H,2-4H2,1H3"));
		ArrayList<String> products2 = new ArrayList<String>(Arrays.asList("InChI=1S/C4H8O/c1-2-3-4-5/h4H,2-3H2,1H3"));
		ArrayList<String> list = new ArrayList<String>();
		File test = new File("inchi3.csv");
		Scanner in = new Scanner(test);
		File out = new File("output.txt");
		PrintStream p = new PrintStream(out);
		while(in.hasNextLine()) {
			String line = in.nextLine();
			line = line.replaceAll("\"", "");
			list.add(differences(new ArrayList<String>(Arrays.asList(line.substring(line.indexOf("%")+1, ordinalIndexOf(line, "%", 2)))), new ArrayList<String>(Arrays.asList(line.substring(ordinalIndexOf(line, "%", 2)+1))), 2, true) + " " + line.substring(line.indexOf("%")+1, ordinalIndexOf(line, "%", 2)) + " " + line.substring(ordinalIndexOf(line, "%", 2)+1));
		}
		Collections.sort(list);
		for(int i = 0; i < list.size()-1; i++) {
			if(list.get(i).substring(0, list.get(i).indexOf(" ")).equals(list.get(i+1).substring(0, list.get(i+1).indexOf(" ")))) {
				list.remove(i+1);
				i--;
			}
		}
		Collections.sort(list);
		for(int i = 0; i < list.size(); i++) {
			p.println(list.get(i));
		}
		p.close();
		ArrayList<String> list2 = new ArrayList<String>();
		list2.add(differences(reactants, intermediates, 2, true));
		list2.add(differences(intermediates, products, 2, true));
		list2.add(differences(products, products2, 2, true));
		retroSynth(list2);
		System.out.println(differences(reactants, products2, 2, true));
		//retroSearch(differences(reactants, products, 2, true), new ArrayList<String>(), 2);
	}
}