import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Database extends AnalySynth{
	public static int counter = 0;
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
				out.println(array.get(i).toString().substring(array.get(i).toString().indexOf("grants\\") + 7) + " Reactants: " + reactants.toString().substring(1, reactants.toString().length()-1) + " Products: " + products.toString().substring(1, products.toString().length()-1) + "|" + counter);
				counter++;
				in.close();
			}
		}
	}
	public static void databaseDiff(File database1, File database2, boolean complex) throws Exception {
		PrintStream print = new PrintStream(database2);
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(database1), "UTF8"));
		while(in.ready()) {
			String line = in.readLine();
			String c = line.substring(line.indexOf("|"));
			String reactants = line.substring(line.indexOf("Reactants")+11, line.indexOf("Products")-1);
			reactants = reactants.replace(", ", " ");
			String products = line.substring(line.indexOf("Products")+10);
			products = products.replaceAll(", ", " ");
			ArrayList<String> Reactants = new ArrayList<String>();
			ArrayList<String> Products = new ArrayList<String>();
			Scanner r = new Scanner(reactants);
			Scanner p = new Scanner(products);
			while(r.hasNext()) {
				String rTemp = r.next();
				if(!rTemp.contains(".") && !rTemp.contains(";") && rTemp.contains("/c") && rTemp.contains("/h"))
					Reactants.add(rTemp);
			}
			while(p.hasNext()) {
				String pTemp = p.next();
				if(!pTemp.contains(".") && !pTemp.contains(";") && pTemp.contains("/c") && pTemp.contains("/h"))
					Products.add(pTemp);
			}
			
			/*if(!Reactants.isEmpty() && !Products.isEmpty()) {
				print.println(differences(Reactants, Products, complex) + c);
				for(int i = 0; i < Reactants.size(); i++) {
					for(int j = 0; j < Products.size(); j++) {
						print.println(differences(new ArrayList<String>(Arrays.asList(Reactants.get(i))), new ArrayList<String>(Arrays.asList(Products.get(j))), complex) + c);
					}
				}
			}*/
			if(!Reactants.isEmpty() && !Products.isEmpty() && Reactants.size() < 11 && Products.size() < 11) {
				if(Products.get(Products.size()-1).contains("|"));{
					try {
						String temp = Products.get(Products.size()-1);
						Products.set(Products.size()-1, temp.substring(0, temp.indexOf("|")));
					}
					catch(Exception e) {
						
					}
				}
				ArrayList<ArrayList<String>> rPerm = comb(Reactants);
				ArrayList<ArrayList<String>> pPerm = comb(Products);
				System.out.println(rPerm);
				System.out.println(pPerm);
				for(int i = 0; i < rPerm.size(); i++) {
					for(int j = 0; j < pPerm.size(); j++) {
						print.println(differences(rPerm.get(i), pPerm.get(j), complex) + c);
					}
				}
			}
			r.close();
			p.close();
		}
		in.close();
		print.close();
	}
	public static void sortedDatabase(File database2, File database3) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(database2)));
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
				File temp = new File(fCount + ".txt");
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
		File temp = new File(fCount + ".txt");
		PrintStream p = new PrintStream(temp);
		for(int i = 0; i < f.size(); i++)
			p.println(f.get(i));
		p.close();
		files.add(temp);
		f = new ArrayList<String>();
		BufferedReader [] brs = new BufferedReader[files.size()];
		PrintStream db = new PrintStream(database3);
		String [] incomingLines = new String[files.size()];
		for(int i = 0; i < brs.length; i++){
			brs[i] = new BufferedReader(new InputStreamReader(new FileInputStream(i + ".txt")));
			incomingLines[i] = brs[i].readLine();
		}
		boolean sort = true;
		String printed = "";
		while(sort){
			sort = false;
			int min = 0;
			for(int i = 0; i < brs.length; i++) {
				if(incomingLines[min] != null && incomingLines[i] != null && incomingLines[min].compareTo(incomingLines[i]) > 0)
					min = i;
				else if(incomingLines[min] == null && incomingLines[i] != null)
					min = i;
			}
			if(!incomingLines[min].equals(printed)) {
				db.println(incomingLines[min]);
				printed = incomingLines[min];
			}
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
	public static void splitDatabase(File database3, File database4) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(database3), "UTF8"));
		ArrayList<String> temp = new ArrayList<String>();
		int lineCount = 0;
		int startCount = 0;
		PrintStream p = new PrintStream(database4);
		temp.add(br.readLine());
		ArrayList<Integer> ids = new ArrayList<Integer>();
		while(br.ready()){
			String newLine = br.readLine();
			String reaction = temp.get(temp.size()-1).substring(0, temp.get(temp.size()-1).indexOf("|"));
			if(newLine.substring(0, newLine.indexOf("|")).equals(reaction)){
				temp.add(newLine);
				ids.add(Integer.parseInt(newLine.substring(newLine.indexOf("|")+1)));
				lineCount++;
			}
			else{
				if(temp.size() > 1)
					p.println(reaction + "|" + ids.toString().substring(1, ids.toString().length()-1).replaceAll(", ", " "));
				else
					p.println(reaction + "|" + ids.toString().substring(1, ids.toString().length()-1).replaceAll(", ", " "));
				temp = new ArrayList<String>();
				ids = new ArrayList<Integer>();
				if(br.ready()){
					temp.add(newLine);
					ids.add(Integer.parseInt(newLine.substring(newLine.indexOf("|")+1)));
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
	public static ArrayList<ArrayList<String>> comb(ArrayList<String> array) {
		ArrayList<ArrayList<String>> a = new ArrayList<ArrayList<String>>();
	    for (int i = array.size() - 1; i > -1; i--) {
	    	a.add(new ArrayList<String>(Arrays.asList(array.get(i))));
	        for (int j = 1; j < (int)Math.pow(2, (array.size()-i)-1); j++) {
	        	ArrayList<String> b = new ArrayList<String>();
	        	b.add(array.get(i));
	        	b.addAll(a.get(j-1));
	        	Collections.sort(b);
	        	a.add(b);
	        }
	    }
	    return a;
	}
}