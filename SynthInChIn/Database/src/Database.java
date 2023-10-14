import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

public class Database {
	public static void main(String[]args) throws Exception {
		File database1 = new File("database1.txt");
		PrintStream p = new PrintStream(database1);
		File database2 = new File("database2.txt");
		File database3 = new File("database3.txt");
		File database4 = new File("database4.txt");
		//databaseCreate(new File("C:\\Users\\johnj\\Downloads\\InChI\\1976-2013_USPTOgrants_CML\\grants"), p);
	}
	
	public static void databaseCreate(File folder, PrintStream out) throws Exception{
		ArrayList<File> array = new ArrayList<File>(Arrays.asList(folder.listFiles()));
		for(int i = 0; i < array.size(); i++){
			if(array.get(i).isDirectory())
				databaseCreate(array.get(i), out);
			else{
				BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(array.get(i)), "UTF8"));
				ArrayList<String> products = new ArrayList<String>();
				ArrayList<String> reactants = new ArrayList<String>();
				boolean reactant = false;
				boolean product = false;
				while(in.ready()){
					String line = in.readLine();
					if(line.contains("InChI")){
						if(reactant && !line.contains(".") && !line.contains(";") && line.contains("/c") && line.contains("/h"))
							reactants.add(line.substring(line.indexOf("InChI"), line.indexOf("\"/>")));
						else if(product && !line.contains(".") && !line.contains(";") && line.contains("/c") && line.contains("/h"))
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
				if(!reactants.isEmpty() && !products.isEmpty())
					out.println(array.get(i).toString().substring(array.get(i).toString().indexOf("grants\\") + 7) + " Reactants: " + reactants.toString().substring(1, reactants.toString().length()-1) + " Products: " + products.toString().substring(1, products.toString().length()-1));
				in.close();
			}
		}
	}
	public static void databaseDiff(File database1, File databaseDiff) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(database1), "UTF8"));
		
	}
}
