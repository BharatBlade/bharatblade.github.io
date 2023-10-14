import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class SynthInChIn4 extends Database{
	public static void main(String[]args) throws Exception {
		//mapSet();
		File database1 = new File("database1.txt");
		File database2a = new File("database2a.txt");
		File database3a = new File("database3a.txt");
		File database4a = new File("database4a.txt");
		File database2b = new File("database2b.txt");
		File database3b = new File("database3b.txt");
		File database4b = new File("database4b.txt");
		//databaseCreate(new File("C:\\Users\\johnj\\Downloads\\InChI\\1976-2013_USPTOgrants_CML\\grants\\"), new PrintStream(database1));
		//databaseDiff(database1, database2a, true);
		//sortedDatabase(database2a, database3a);
		//splitDatabase(database3a, database4a);
		//databaseDiff(database1, database2b, true);
		//sortedDatabase(database2b, database3b);
		//splitDatabase(database3b, database4b);
		//ArrayList<String> reactants = getInChI("Reactants");
		//ArrayList<String> products = getInChI("Reactants");
		System.out.println(differences(new ArrayList<String>(Arrays.asList("InChI=1S/C6H4ClNO2/c7-5-3-1-2-4-6(5)8(9)10/h1-4H")), new ArrayList<String>(Arrays.asList("InChI=1S/C6H6ClN/c7-5-3-1-2-4-6(5)8/h1-4H,8H2")), true));
		System.out.println(differences(new ArrayList<String>(Arrays.asList("InChI=1S/C6H4ClNO2/c7-5-3-1-2-4-6(5)8(9)10/h1-4H")), new ArrayList<String>(Arrays.asList("InChI=1S/C6H6ClN/c7-5-3-1-2-4-6(5)8/h1-4H,8H2")), false));
		System.out.println(differences(new ArrayList<String>(Arrays.asList("InChI=1S/C6H4ClNO2/c7-5-2-1-3-6(4-5)8(9)10/h1-4H")), new ArrayList<String>(Arrays.asList("InChI=1S/C6H6ClN/c7-5-2-1-3-6(8)4-5/h1-4H,8H2")), true));
		System.out.println(differences(new ArrayList<String>(Arrays.asList("InChI=1S/C6H4ClNO2/c7-5-2-1-3-6(4-5)8(9)10/h1-4H")), new ArrayList<String>(Arrays.asList("InChI=1S/C6H6ClN/c7-5-2-1-3-6(8)4-5/h1-4H,8H2")), false));
		System.out.println(differences(new ArrayList<String>(Arrays.asList("InChI=1S/C6H4ClNO2/c7-5-1-3-6(4-2-5)8(9)10/h1-4H")), new ArrayList<String>(Arrays.asList("InChI=1S/C6H6ClN/c7-5-1-3-6(8)4-2-5/h1-4H,8H2")), true));
		System.out.println(differences(new ArrayList<String>(Arrays.asList("InChI=1S/C6H4ClNO2/c7-5-1-3-6(4-2-5)8(9)10/h1-4H")), new ArrayList<String>(Arrays.asList("InChI=1S/C6H6ClN/c7-5-1-3-6(8)4-2-5/h1-4H,8H2")), false));
	}
}