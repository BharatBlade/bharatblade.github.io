import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class SynthInChIn7 extends Database{
	public static void main(String[]args) throws Exception {
		/*File database1 = new File("database1.txt");
		File database2 = new File("database2.txt");
		File database3 = new File("database3.txt");
		File database4 = new File("database4.txt");
		databaseCreate(new File("C:\\Users\\johnj\\Downloads\\InChI\\1976-2013_USPTOgrants_CML\\grants\\1976\\pftaps19760106_wk01.zip"), new PrintStream(database1));
		databaseDiff(database1, database2, 2, true);
		sortedDatabase(database2, database3);
		splitDatabase(database3, database4);*/
		
		ArrayList<String> reactants = new ArrayList<String>(Arrays.asList("InChI=1S/C4H9Cl/c1-2-3-4-5/h2-4H2,1H3"));
		ArrayList<String> products2 = new ArrayList<String>(Arrays.asList("InChI=1S/C4H8O/c1-2-3-4-5/h4H,2-3H2,1H3"));
		ArrayList<String> list = new ArrayList<String>();
		File test = new File("inchi3.csv");
		Scanner in = new Scanner(test);
		while(in.hasNextLine()) {
			String line = in.nextLine();
			line = line.replaceAll("\"", "");
			list.add(differences(new ArrayList<String>(Arrays.asList(line.substring(line.indexOf("%")+1, ordinalIndexOf(line, "%", 2)))), new ArrayList<String>(Arrays.asList(line.substring(ordinalIndexOf(line, "%", 2)+1))), 2, true) + " " + line.substring(0, line.indexOf("%")));
			//list.add(line.substring(0, line.indexOf("|")) + " " + line.substring(line.indexOf("|")+1));
		}
		in.close();
		Collections.sort(list);
		for(int i = 0; i < list.size()-1; i++)
			if(list.get(i).substring(0,  list.get(i).indexOf(" ")).equals(list.get(i+1).substring(0,  list.get(i+1).indexOf(" ")))) {
				list.remove(i+1);
				i--;
			}
		Collections.sort(list);
		System.out.println("Chlorobutane to Butanal");
		long time = System.currentTimeMillis();
		rSearch(list, differences(reactants, products2, 2, true), reactants, 3);
		System.out.println(System.currentTimeMillis() - time);
	}
}