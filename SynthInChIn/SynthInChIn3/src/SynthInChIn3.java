import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class SynthInChIn3 extends Database{
	public static void main(String[]args) throws Exception {
		mapSet();
		ArrayList<String> reactants = getInChI("Reactants");
		ArrayList<String> products = getInChI("Products");
		File database1 = new File("database1.txt");
		File database2a = new File("database2a.txt");
		File database3a = new File("database3a.txt");
		File database4a = new File("database4a.txt");
		File database2b = new File("database2b.txt");
		File database3b = new File("database3b.txt");
		File database4b = new File("database4b.txt");
		/*databaseDiff(database1, database2a, true);
		sortedDatabase(database2a, database3a);
		splitDatabase(database3a, database4a);
		databaseDiff(database1, database2b, false);
		sortedDatabase(database2b, database3b);
		splitDatabase(database3b, database4b);*/
		search(differences(reactants, products, true), database4a, database3a);
	}
}
