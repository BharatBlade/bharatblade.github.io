package College;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;
public class Formula {
	public static void main(String[]args){
		/*
		String InChi1 = "Pentane:  1S/C5H12/c1-3-5-4-2/h3-5H2,1-2H3";
		String InChi2 = "3-Pentanol: 1S/C5H12O/c1-3-5(6)4-2/h5-6H,3-4H2,1-2H3";
		System.out.println(InChi1);
		System.out.println(InChi2);
		*/
		groupDifferences(atomNumberDifferences(atomNumbers("ethene:InChI=1S/C2H4/c1-2/h1-2H2"), atomNumbers("ethanol:InChI=1S/C2H6O/c1-2-3/h3H,2H2,1H3")), hydrogenDifferences(hydrogenNumbers("ethene:InChI=1S/C2H4/c1-2/h1-2H2"), hydrogenNumbers("ethanol:InChI=1S/C2H6O/c1-2-3/h3H,2H2,1H3")));
		groupDifferences(atomNumberDifferences(atomNumbers("propene:InChI=1S/C3H6/c1-3-2/h3H,1H2,2H3"), atomNumbers("propanol:InChI=1S/C3H8O/c1-2-3-4/h4H,2-3H2,1H3")), hydrogenDifferences(hydrogenNumbers("propene:InChI=1S/C3H6/c1-3-2/h3H,1H2,2H3"), hydrogenNumbers("propanol:InChI=1S/C3H8O/c1-2-3-4/h4H,2-3H2,1H3")));
		groupDifferences(atomNumberDifferences(atomNumbers("butene:InChI=1S/C4H8/c1-3-4-2/h3H,1,4H2,2H3"), atomNumbers("butanol:InChI=1S/C4H10O/c1-2-3-4-5/h5H,2-4H2,1H3")), hydrogenDifferences(hydrogenNumbers("butene:InChI=1S/C4H8/c1-3-4-2/h3H,1,4H2,2H3"), hydrogenNumbers("butanol:InChI=1S/C4H10O/c1-2-3-4-5/h5H,2-4H2,1H3")));
		System.out.println("Alkanes to primary alcohols: ");
		System.out.println(specificAtomHydrogenDifferences(hydrogenNumbers("Propane: 1/C3H8/c1-3-2/h3H2,1-2H3"), hydrogenNumbers("Propanol: 1S/C3H8O/c1-2-3-4/h4H,2-3H2,1H3")));
		System.out.println(specificAtomHydrogenDifferences(hydrogenNumbers("Butane: 1/C4H10/c1-3-4-2/h3-4H2,1-2H3"), hydrogenNumbers("Butanol: 1S/C4H10O/c1-2-3-4-5/h5H,2-4H2,1H3")));
		System.out.println(specificAtomHydrogenDifferences(hydrogenNumbers("Pentane: 1S/C5H12/c1-3-5-4-2/h3-5H2,1-2H3"), hydrogenNumbers("Pentanol: 1S/C5H12O/c1-2-3-4-5-6/h6H,2-5H2,1H3")));
		System.out.println("Alkanes to ketones: ");
		System.out.println(specificAtomHydrogenDifferences(hydrogenNumbers("Propane: 1/C3H8/c1-3-2/h3H2,1-2H3"), hydrogenNumbers("Propanone: 1S/C3H6O/c1-3(2)4/h1-2H3")));
		System.out.println(specificAtomHydrogenDifferences(hydrogenNumbers("Butane: 1/C4H10/c1-3-4-2/h3-4H2,1-2H3"), hydrogenNumbers("Butanone: 1S/C4H8O/c1-3-4(2)5/h3H2,1-2H3")));
		System.out.println(specificAtomHydrogenDifferences(hydrogenNumbers("Pentane: 1S/C5H12/c1-3-5-4-2/h3-5H2,1-2H3"), hydrogenNumbers("Pentanone: 1S/C5H10O/c1-3-4-5(2)6/h3-4H2,1-2H3")));
		System.out.println("Alkanes to secondary alcohols: ");
		System.out.println(specificAtomHydrogenDifferences(hydrogenNumbers("Propane: 1/C3H8/c1-3-2/h3H2,1-2H3"), hydrogenNumbers("2-Propanol: 1S/C3H8O/c1-3(2)4/h3-4H,1-2H3")));
		System.out.println(specificAtomHydrogenDifferences(hydrogenNumbers("Butane: 1/C4H10/c1-3-4-2/h3-4H2,1-2H3"), hydrogenNumbers("2-Butanol: 1S/C4H10O/c1-3-4(2)5/h4-5H,3H2,1-2H3")));
		System.out.println(specificAtomHydrogenDifferences(hydrogenNumbers("Pentane: 1S/C5H12/c1-3-5-4-2/h3-5H2,1-2H3"), hydrogenNumbers("3-Pentanol: 1S/C5H12O/c1-3-5(6)4-2/h5-6H,3-4H2,1-2H3")));
		System.out.println("Secondary alcohols to ketones: ");
		System.out.println(specificAtomHydrogenDifferences(hydrogenNumbers("2-Propanol: 1S/C3H8O/c1-3(2)4/h3-4H,1-2H3"), hydrogenNumbers("Propanone: 1S/C3H6O/c1-3(2)4/h1-2H3")));
		System.out.println(specificAtomHydrogenDifferences(hydrogenNumbers("2-Butanol: 1S/C4H10O/c1-3-4(2)5/h4-5H,3H2,1-2H3"), hydrogenNumbers("Butanone: 1S/C4H8O/c1-3-4(2)5/h3H2,1-2H3")));
		System.out.println(specificAtomHydrogenDifferences(hydrogenNumbers("3-Pentanol: 1S/C5H12O/c1-3-5(6)4-2/h5-6H,3-4H2,1-2H3"), hydrogenNumbers("Pentanone: 1S/C5H10O/c1-3-4-5(2)6/h3-4H2,1-2H3")));
		System.out.println();
		System.out.println("Alkanes to primary alcohols");
		groupDifferences(atomNumberDifferences(atomNumbers("Propane: 1/C3H8/c1-3-2/h3H2,1-2H3"), atomNumbers("Propanol: 1S/C3H8O/c1-2-3-4/h4H,2-3H2,1H3")), hydrogenDifferences(hydrogenNumbers("Propane: 1/C3H8/c1-3-2/h3H2,1-2H3"), hydrogenNumbers("Propanol: 1S/C3H8O/c1-2-3-4/h4H,2-3H2,1H3")));
		groupDifferences(atomNumberDifferences(atomNumbers("Butane: 1/C4H10/c1-3-4-2/h3-4H2,1-2H3"), atomNumbers("Butanol: 1S/C4H10O/c1-2-3-4-5/h5H,2-4H2,1H3")), hydrogenDifferences(hydrogenNumbers("Butane: 1/C4H10/c1-3-4-2/h3-4H2,1-2H3"), hydrogenNumbers("Butanol: 1S/C4H10O/c1-2-3-4-5/h5H,2-4H2,1H3")));
		groupDifferences(atomNumberDifferences(atomNumbers("Pentane: 1S/C5H12/c1-3-5-4-2/h3-5H2,1-2H3"), atomNumbers("Pentanol: 1S/C5H12O/c1-2-3-4-5-6/h6H,2-5H2,1H3")), hydrogenDifferences(hydrogenNumbers("Pentane: 1S/C5H12/c1-3-5-4-2/h3-5H2,1-2H3"), hydrogenNumbers("Pentanol: 1S/C5H12O/c1-2-3-4-5-6/h6H,2-5H2,1H3")));
		
		/*
		Propane: 1/C3H8/c1-3-2/h3H2,1-2H3
		Butane: 1/C4H10/c1-3-4-2/h3-4H2,1-2H3
		Pentane: 1S/C5H12/c1-3-5-4-2/h3-5H2,1-2H3
		
		Propanol: 1S/C3H8O/c1-2-3-4/h4H,2-3H2,1H3
		Butanol: 1S/C4H10O/c1-2-3-4-5/h5H,2-4H2,1H3
		Pentanol: 1S/C5H12O/c1-2-3-4-5-6/h6H,2-5H2,1H3
	
		Propanone: 1S/C3H6O/c1-3(2)4/h1-2H3
		Butanone: 1S/C4H8O/c1-3-4(2)5/h3H2,1-2H3
		Pentanone: 1S/C5H10O/c1-3-4-5(2)6/h3-4H2,1-2H3
		
		2-Propanol: 1S/C3H8O/c1-3(2)4/h3-4H,1-2H3
		2-Butanol: 1S/C4H10O/c1-3-4(2)5/h4-5H,3H2,1-2H3
		3-Pentanol: 1S/C5H12O/c1-3-5(6)4-2/h5-6H,3-4H2,1-2H3
		*/
		
		//InChi Atom Differences
		/*
		TreeMap<String, Integer> a = atomNumbers(InChi1);
		System.out.println(a);
		System.out.println();
		TreeMap<String, Integer> b = atomNumbers(InChi2);
		System.out.println(b);
		System.out.println();
		TreeMap<String, Integer> c = atomNumberDifferences(a, b);
		System.out.println("Atom Differences: " + c);
		System.out.println();
		ArrayList<Integer> d = hydrogenNumbers(InChi1); 
		System.out.println(d);
		System.out.println();
		ArrayList<Integer> e = hydrogenNumbers(InChi2);
		System.out.println(e);
		System.out.println();
		ArrayList<Integer> f = hydrogenDifferences(d, e);
		System.out.println("Hydrogen Differences: " + hydrogenDifferences(d, e));
		System.out.println(specificAtomHydrogenDifferences(d,e));
		System.out.println();
		groupDifferences(c, f);
		*/
		
		/*
		atomNumbers("1S/C5H12/c1-3-5-4-2/h3-5H2,1-2H3");
		atomNumbers("1S/C5H11Cl/c1-2-3-4-5-6/h2-5H2,1H3");
		atomNumbers("1S/C5H11Cl/c1-3-4-5(2)6/h5H,3-4H2,1-2H3");
		atomNumbers("1S/C5H12O/c1-2-3-4-5-6/h6H,2-5H2,1H3");
		atomNumbers("1S/C5H12O/c1-3-4-5(2)6/h5-6H,3-4H2,1-2H3");
		atomNumbers("InChI=1S/C5H10O/c1-2-3-4-5-6/h5H,2-4H2,1H3");
		atomNumbers("1S/C5H10O/c1-3-4-5(2)6/h3-4H2,1-2H3");
		*/
	}
	public static TreeMap<String, Integer> atomNumbers(String InChi){
		String formula = InChi.substring(InChi.indexOf("/")+1, InChi.indexOf("/c"));
		ArrayList<String> elements = new ArrayList<String>();
		ArrayList<Integer> positions = new ArrayList<Integer>();
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		for(int i = 0; i < formula.length(); i++){
			if((int)formula.substring(i, i+1).charAt(0) >= 65 && (int)formula.substring(i, i+1).charAt(0) <= 96){
				if(i < formula.length() - 1 && (int)formula.substring(i+1, i+2).charAt(0) >= 97 && (int)formula.substring(i, i+1).charAt(0) <= 122){
					elements.add(formula.substring(i, i+2));
					positions.add(i);
				}
				else{
					elements.add(formula.substring(i, i+1));
					positions.add(i);
				}
			}
		}
		for(int i = 0; i < positions.size()-1; i++){
			if(formula.substring(positions.get(i)+elements.get(i).length(), positions.get(i+1)).equals("")){
				numbers.add(1);
			}
			else{
				numbers.add(Integer.valueOf(formula.substring(positions.get(i)+elements.get(i).length(), positions.get(i+1))));
			}
		}
		if(formula.substring(positions.get(positions.size()-1)+elements.get(elements.size()-1).length()).equals("")){
			numbers.add(1);
		}
		else{
			numbers.add(Integer.valueOf(formula.substring(positions.get(positions.size()-1)+elements.get(elements.size()-1).length())));
		}
		//System.out.println(InChi);
		//System.out.println(formula);
		//System.out.println(elements);
		//System.out.println(numbers);
		TreeMap<String, Integer> test = new TreeMap<String, Integer>();
		for(int i = 0; i < elements.size(); i++){
			test.put(elements.get(i), numbers.get(i));
		}
		return test;
	}
	public static TreeMap<String, Integer> atomNumberDifferences(TreeMap<String, Integer> a, TreeMap<String, Integer> b){
		TreeMap<String, Integer> differences = new TreeMap<String, Integer>();
		for(Map.Entry<String,Integer> one : a.entrySet()) {
			for(Map.Entry<String,Integer> two : b.entrySet()) {
				if(one.getKey().equals(two.getKey())){
					differences.put(one.getKey(), two.getValue() - one.getValue());
				}
			}
		}
		for(Map.Entry<String,Integer> one : a.entrySet()) {
			if(!differences.containsKey(one.getKey()))
				differences.put(one.getKey(), -one.getValue());
		}
		for(Map.Entry<String,Integer> two : b.entrySet()) {
			if(!differences.containsKey(two.getKey()))
				differences.put(two.getKey(), two.getValue());
		}
		return differences;
	}
	public static ArrayList<Integer> hydrogenNumbers(String InChi){
		String pos = InChi.substring(InChi.indexOf("/c")+2, InChi.indexOf("/h"));
		String numH = InChi.substring(InChi.indexOf("/h")+2);
		ArrayList<String> tempArray = new ArrayList<String>();
		String tempString = "";
		for(int i = 0; i < numH.length(); i++){
			if(numH.substring(i, i+1).equals(",")){
				tempArray.add(tempString);
				tempString = "";
			}else
			tempString += numH.substring(i, i+1);
		}
		tempArray.add(tempString);
		for(int i = 0; i < tempArray.size(); i++){
			if(!tempArray.get(i).contains("H")){
				tempArray.set(i, tempArray.get(i)+tempArray.get(i+1).substring(tempArray.get(i+1).length()-2));
			}
		}
		numH = "";
		for(int i = 0; i < tempArray.size() - 1; i++){
			numH += tempArray.get(i)+",";
		}
		numH+=tempArray.get(tempArray.size()-1);
		ArrayList<String> positions = new ArrayList<String>();
		for(int i = 0; i < pos.length(); i++){
			if(pos.substring(i, i+1).equals("(")){
				positions.add(positions.get(positions.size()-1)+"."+pos.substring(i+1, i+1+pos.substring(i+1).indexOf(")")));
				i++;
			}
			else if(i < pos.length()-1 && (int)pos.substring(i, i+1).charAt(0) >= 48 && (int)pos.substring(i, i+1).charAt(0) <= 57 && (int)pos.substring(i+1, i+2).charAt(0) >= 48 && (int)pos.substring(i+1, i+2).charAt(0) <= 57){
				positions.add("."+pos.substring(i, i+2));
				i++;
			}
			else if((int)pos.substring(i, i+1).charAt(0) >= 48 && (int)pos.substring(i, i+1).charAt(0) <= 57){
				positions.add("."+pos.substring(i, i+1));
			}
		}
		ArrayList<String> hydrogenCount = new ArrayList<String>();
		ArrayList<String> carbonNumber = new ArrayList<String>();
		String temp = "";
		for(int i = 0; i < numH.length(); i++){
			if(numH.substring(i, i+1).equals(",")){
				carbonNumber.add(temp.substring(0, temp.indexOf("H")));
				if(temp.substring(temp.indexOf("H")+1).equals("")){
					hydrogenCount.add("1");
				}
				else{
					hydrogenCount.add(temp.substring(temp.indexOf("H")+1));
				}
				temp = "";
				i++;
			}
			temp += numH.substring(i, i+1);
		}
		carbonNumber.add(temp.substring(0, temp.indexOf("H")));
		if(temp.substring(temp.indexOf("H")+1).equals("")){
			hydrogenCount.add("1");
		}
		else{
			hydrogenCount.add(temp.substring(temp.indexOf("H")+1));
		}

		ArrayList<String> uniqueHydrogens = new ArrayList<String>();
		for(int i = 0; i < carbonNumber.size(); i++){
			if(carbonNumber.get(i).contains("-")){
				String temp2 = "";
				for(int j = Integer.valueOf(carbonNumber.get(i).substring(0, carbonNumber.get(i).indexOf("-"))); j <= Integer.valueOf(carbonNumber.get(i).substring(carbonNumber.get(i).indexOf("-")+1)); j++){
					temp2 += "," + j + ",";
				}
				carbonNumber.set(i, temp2);
			}
			else{
				carbonNumber.set(i, "," + carbonNumber.get(i)+",");
			}				
		}
		String allCH = "";
		for(int i = 0; i < carbonNumber.size(); i++){
			allCH += carbonNumber.get(i);
		}
		for(int i = 0; i < positions.size(); i++){
			for(int j = 0; j < carbonNumber.size(); j++){
				if(carbonNumber.get(j).contains("," + positions.get(i).substring(positions.get(i).lastIndexOf(".")+1)+",")){
					uniqueHydrogens.add(hydrogenCount.get(j));
				}
			}
			if(!allCH.contains(positions.get(i).substring(positions.get(i).lastIndexOf(".")+1))){
				uniqueHydrogens.add("0");
			}
		}
		ArrayList<Integer> uniqueHydrogenCount = new ArrayList<Integer>();
		for(int i = 0; i < uniqueHydrogens.size(); i++){
			uniqueHydrogenCount.add(Integer.valueOf(uniqueHydrogens.get(i)));
		}
		return uniqueHydrogenCount;
	}
	public static ArrayList<Integer> hydrogenDifferences(ArrayList<Integer> a, ArrayList<Integer> b){
		ArrayList<Integer> c = new ArrayList<Integer>();
		for(int i = 0; i < Integer.min(a.size(), b.size()); i++){
			c.add(b.get(i)-a.get(i));
		}
		if(a.size() > b.size()){
			for(int i = b.size(); i < a.size(); i++){
				c.add(-a.get(i));
			}
		}
		else if(a.size() < b.size()){
			for(int i = a.size(); i < b.size(); i++){
				c.add(b.get(i));
			}
		}
		return c;
	}
	public static ArrayList<ArrayList<Integer>> specificAtomHydrogenDifferences(ArrayList<Integer> a, ArrayList<Integer> b){
		
		for(int i = 0; i < a.size(); i++){
			for(int j = 0; j < b.size(); j++){
				if(a.get(i).equals(b.get(j))){
					a.remove(i);
					b.remove(j);
					i--;
					break;
				}
			}
		}
		return new ArrayList<ArrayList<Integer>>(Arrays.asList(a, b));
	}
	public static void groupDifferences(TreeMap<String, Integer> atom, ArrayList<Integer> hydrogen){
		Iterator<Integer> test = atom.values().iterator();
		while(test.hasNext()){
			if(test.next() == 0){
				test.remove();
			}
		}
		System.out.println(atom);
		
		for(int i = 0; i < hydrogen.size(); i++){
			if(i == 0 && hydrogen.get(i) == 0 && hydrogen.get(i+1) == 0){
				hydrogen.remove(i);
				i--;
			}
			else if(i == hydrogen.size()-1 && hydrogen.get(i) == 0 && hydrogen.get(i-1) == 0){
				hydrogen.remove(i);
				i--;
			}
		}
		System.out.println(hydrogen);
	}
}