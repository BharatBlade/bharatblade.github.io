package Science;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class FastNMR {
	public static int [][]groupElements = 	{{4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 0, 0, 1, 1, 2, 2, 2, 2, 3, 3, 2, 2, 2, 7, 7, 6, 1, 1},	//Carbon
											{12, 3, 2, 1, 2, 2, 2, 2, 2, 2, 2, 4, 1, 1, 1, 1, 3, 2, 3, 2, 3, 2, 2, 1, 1, 3, 2, 1, 2, 3},	//Hydrogen
											{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 2, 1, 1, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},		//Oxygen
											{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},		//Fluorine
											{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},		//Nitrogen
											{0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},		//Bromine
											{0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},		//Iodine
											{0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},		//Chlorine
											{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 4, 4, 4, 1, 0},		//EOU
											{0, 1, 2, 3, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1, 2, 1, 2, 2, 3, 2, 3, 1, 1, 2, 1, 1, 1},		//Bonds
											{0, 4, 7, 10, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 4, 1, 2, 5, 1, 4, 2, 5, 6, 10, 6, 2, 5, 6, 1, 1}};	//Multiplicity
	public static String [] arrSymbol = 	{"H", "He", "Li", "Be", "B", "C", "N", "O", "F", "Ne", "Na", "Mg", "Al", "Si", "P", "S", "Cl", "Ar", "K", "Ca", "Sc", "Ti", "V", "Cr", "Mn", "Fe", "Co", "Ni", "Cu", "Zn", "Ga", "Ge", "As", "Se", "Br", "Kr", "Rb", "Sr", "Y", "Zr", "Nb", "Mo", "Tc", "Ru", "Rh", "Pd", "Ag", "Cd", "In", "Sn", "Sb", "Te", "I", "Xe", "Cs", "Ba", "La", "Ce", "Pr", "Nd", "Pm", "Sm", "Eu", "Gd", "Tb", "Dy", "Ho", "Er", "Tm", "Yb", "Lu", "Hf", "Ta", "W", "Re", "Os", "Ir", "Pt", "Au", "Hg", "Tl", "Pb", "Bi", "Po", "At", "Rn", "Fr", "Ra", "Ac", "Th", "Pa", "U", "Np", "Pu", "Am", "Cm", "Bk", "Cf", "Es", "Fm", "Md", "No", "Lr", "Rf", "Db", "Sg", "Bh", "Hs", "Mt", "Ds"};
	public static double [] arrMass =		{1.008, 4.003, 6.941, 9.012, 10.81, 12.01, 14.01, 16.00, 19.00, 20.18, 22.99, 24.31, 26.98, 28.09, 30.97, 32.07, 35.45, 39.95, 39.10, 40.08, 44.96, 47.88, 50.94, 52.00, 54.94, 55.85, 58.93, 58.69, 63.55, 65.39, 69.72, 72.61, 74.92, 78.96, 79.90, 83.80, 85.47, 87.62, 88.91, 91.22, 91.92, 95.94, 98, 101.1, 102.9, 106.4, 107.9, 112.4, 114.8, 118.7, 121.8, 127.6, 126.9, 131.3, 132.9, 137.3, 138.9, 140.1, 140.9, 144.2, 145, 150.4, 152.0, 157.3, 158.9, 162.5, 164.9, 167.3, 168.9, 173.0, 175.0, 178.5, 180.9, 183.9, 186.2, 190.2, 192.2, 195.1, 197.0, 200.6, 204.4, 207.2, 209.0, 209, 210, 222, 223, 226, 227, 232.0, 231, 238.0, 237, 242, 243, 247, 247, 251, 252, 257, 258, 259, 260, 261, 262, 266, 262, 265, 266, 269};
	public static String [] groupList = 	{" (CH3)4Si ", " CH3 ", " CH2 ", " CH ", " CH2I ", " CH2Br ", " CH2Cl ", " CH2F ", " CH2NH2 ", " CH2OH ", " CH2O ", " CH2CH2O ", " NH ", " OH ", " (CO)H ", " COOH ", " (CO)CH3 ", " (CO)CH2 ", " (CO)OCH3 ", " (CO)OCH2 ", " C=CHCH3 ", " C=CHCH2 ", " C=CH2 ", " C=CH ", " C=-CH ", " ArCH3 ", " ArCH2 ", " ArH ", " (CO)NH2 ", " OCH3 "};	
	public static double [][] groupShifts = {{-1.1, -0.2, 0.1, 0.3, 2.1, 2.3, 2.5, 3.3, 1.2, 2.3, 2.2, 0.4, -0.6, -0.6, 8.4, 8.9, 1.0, 1.1, 2.6, 3.0, 0.5, 0.7, 3.5, 3.9, 0.9, 1.1, 1.2, 5.4, 3.9, 2.2},
											 {1.1, 2.3, 2.5, 2.8, 4.4, 4.6, 4.9, 5.6, 4.0, 5.1, 5.1, 2.7, 6.1, 7.1, 11.2, 14.1, 3.4, 3.7, 5.0, 5.8, 3.0, 3.2, 6.1, 6.8, 4.1, 3.6, 3.9, 9.6, 9.1, 4.9}};
	public static String [] formulaTemplate = {"C", "H", "O", "F", "N", "Br", "I", "Cl"};
	public static ArrayList<String> elements = new ArrayList<String>();
	public static ArrayList<Integer> number = new ArrayList<Integer>();
	public static int [] formula = new int [8];
	public static double eou = 0.0;
	public static Scanner in = new Scanner(System.in);
	public static ArrayList<ArrayList<String>> possibleGroups = new ArrayList<ArrayList<String>>();
	public static ArrayList<ArrayList<Integer>> possibleGroupsID = new ArrayList<ArrayList<Integer>>();
	public static ArrayList<ArrayList<String>> possCombinations = new ArrayList<ArrayList<String>>();
	public static ArrayList<ArrayList<Integer>> possCombinationsID = new ArrayList<ArrayList<Integer>>();
	public static ArrayList<Integer> integration = new ArrayList<Integer>();
	public static void main(String[]args){
		ArrayList<ArrayList<String>> peaks = new ArrayList<ArrayList<String>>();

		ArrayList<Double> percent = new ArrayList<Double>();
		
		System.out.print("Do you know the chemical formula?: ");
		if(in.nextLine().toLowerCase().contains("n")){
			System.out.print("Type each element of your compound separated by one space only: ");
			String e = in.nextLine();
			System.out.print("Enter molar mass: ");
			double molarMass = in.nextDouble();
			String temp = "";
			for(int i = 0; i < e.length(); i++){
				if(e.substring(i, i+1).equals(" ")){
					elements.add(temp);
					temp = "";
				}
				else{
					temp += e.substring(i, i+1);
				}
				if(i == e.length() - 1 && !e.substring(i, i+1).equals(" ")){
					elements.add(temp);
					temp = "";
				}
			}
			for(int i = 0; i < elements.size(); i++){
				System.out.print("What percent of " + elements.get(i) + "? ");
				percent.add(in.nextDouble());
			}
			for(int i = 0; i < elements.size(); i++){
				for(int j = 0; j < arrSymbol.length; j++){
					if(elements.get(i).equals(arrSymbol[j])){
						number.add((int)Math.round(molarMass*0.01*percent.get(i)/arrMass[j]));
						break;
					}
				}
			}
			formEOU();
		}
		else{
			System.out.print("Type the formula: ");
			String input = in.nextLine();
			Pattern elementPattern = Pattern.compile("(\\p{Upper}\\p{Lower}?)(\\p{Digit}*)");
			Matcher elementMatcher = elementPattern.matcher(input);
			while (elementMatcher.find()) {
				int count;			
			    String element = elementMatcher.group(1);
			    if(elementMatcher.group(2).equals("")){
			    	count = 1;
			    }
			    else{
			    	count = Integer.valueOf(elementMatcher.group(2));
			    }
			    if(elements.contains(element)){
			    	number.set(elements.indexOf(element), number.get(elements.indexOf(element)) + count);
			    }
			    else{
			    	elements.add(element);
			    	number.add(count);
			    }   		    
			}
			formEOU();
		}
		System.out.println();
		System.out.println("EOU: " + eou);
		System.out.print("How many peaks in the NMR?");
		int numPeaks = in.nextInt();
		ArrayList<Double> area = new ArrayList<Double>();

		for(int i = 0; i < numPeaks; i++){
			ArrayList<String> peak = new ArrayList<String>();
			System.out.print("Enter shift of peak");
			peak.add(in.next());
			System.out.print("Enter multiplicity");
			peak.add(in.next());
			System.out.print("Enter integration of peak");
			area.add(in.nextDouble());
			peaks.add(peak);
		}
		double areaSum = 0;
		for(int i = 0; i < area.size(); i++){
			areaSum += area.get(i);
		}
		int factor = (int)Math.round(((double)formula[1])/areaSum);
		
		for(int i = 0; i < area.size(); i++){
			integration.add(i, (int) Math.round(area.get(i)*factor));
		}
		for(int i = 0; i < peaks.size(); i++){
			ArrayList<String> groups = new ArrayList<String>();
			ArrayList<Integer> groupsID = new ArrayList<Integer>();
			for(int j = 0; j < groupList.length; j++){
				if(Double.valueOf(peaks.get(i).get(0)) >= groupShifts[0][j] && Double.valueOf(peaks.get(i).get(0)) <= groupShifts[1][j] && Integer.valueOf(peaks.get(i).get(1)) <= groupElements[10][j]){
					groups.add(groupList[j]);
					groupsID.add(j);
				}
			}
			possibleGroups.add(groups);
			possibleGroupsID.add(groupsID);
		}
		boolean leave = false;
		for(int i = 0; i < possibleGroupsID.size(); i++){
			for(int j = 0; j < possibleGroupsID.get(i).size(); j++){
				for(int k = 0; k < 8; k++){
					if(k != 1 && formula[k] < groupElements[k][possibleGroupsID.get(i).get(j)]){
						possibleGroups.get(i).remove(j);
						possibleGroupsID.get(i).remove(j);
						j--;
						leave = true;
						break;
					}
				}
				if(leave){
					leave = false;
					continue;
				}
				if(eou < groupElements[8][possibleGroupsID.get(i).get(j)]){
					possibleGroups.get(i).remove(j);
					possibleGroupsID.get(i).remove(j);
					j--;
					continue;
				}
			}
		}
		System.out.println();
		int pos;
		ArrayList<Integer> badPos = new ArrayList<Integer>();
		for(int i = 0; i < possibleGroupsID.size(); i++){
			if(possibleGroupsID.get(i).size() == 1){
				badPos.add(i);
				pos = possibleGroupsID.get(i).get(0);
				formula[2] -= groupElements[2][pos];
				int j = 0;
				while (j < possibleGroupsID.size()){
					if(!badPos.contains(j)){
						for(int k = 0; k < possibleGroupsID.get(j).size(); k++){
							if(formula[2] < groupElements[2][possibleGroupsID.get(j).get(k)]){
								possibleGroups.get(j).remove(k);
								possibleGroupsID.get(j).remove(k);
								k--;
								continue;
							}
						}
					}
					j++;
				}
			}
		}
		System.out.println("Possible groups for each peak:");
		for(int i = 0; i < possibleGroups.size(); i++){
			System.out.println(possibleGroups.get(i));
		}
		System.out.println();
		int [] count = new int[possibleGroups.size()];
		ArrayList<String> combinations = new ArrayList<String>();
		ArrayList<Integer> combinationsID = new ArrayList<Integer>();

		for(int i = 0; i < possibleGroups.size(); i++){
			combinations.add(possibleGroups.get(i).get(count[i]));
			combinationsID.add(possibleGroupsID.get(i).get(count[i]));
			if(i == possibleGroups.size() - 1){
				possCombinations.add(combinations);
				possCombinationsID.add(combinationsID);
				combinations = new ArrayList<String>();
				combinationsID = new ArrayList<Integer>();
				count[i]++;
				int temptemp = formula[1];
				recursion(i, count, temptemp);
				i = -1;
			}
		}
	}
	public static void recursion(int i, int [] count, int h){
		if(count[i] == possibleGroups.get(i).size()){
			count[i] = 0;
			count[i-1]++;
			if(count[0] == possibleGroups.get(0).size()){
				ArrayList<ArrayList<String>> pCEq = new ArrayList<ArrayList<String>>();
				ArrayList<ArrayList<String>> pCGr = new ArrayList<ArrayList<String>>();
				for(int j = 0; j < possCombinations.size(); j++){
					int tempFac = integration.get(0)/groupElements[1][possCombinationsID.get(j).get(0)];
					int tempFac2 = integration.get(1)/groupElements[1][possCombinationsID.get(j).get(1)];
					int bondSum = groupElements[9][possCombinationsID.get(j).get(0)]*tempFac + groupElements[9][possCombinationsID.get(j).get(1)]*tempFac2 - (tempFac + tempFac2);
					if(possCombinations.get(j).contains(" ArH ")){
						bondSum = groupElements[9][possCombinationsID.get(j).get(0)]*tempFac + groupElements[9][possCombinationsID.get(j).get(1)]*tempFac2 - 2*(tempFac + tempFac2);
						bondSum += 6;
					}
					int sumH = 0;
					int sumMinH = 0;
					for(int k = 2; k < possCombinationsID.get(j).size(); k++){
						int factor = integration.get(k)/groupElements[9][possCombinationsID.get(j).get(k)];
						bondSum += groupElements[9][possCombinationsID.get(j).get(k)]*factor;
						bondSum -= 2*factor;
					}

					for(int k = 0; k < possCombinationsID.get(j).size(); k++){
						sumH += (integration.get(k)/groupElements[9][possCombinationsID.get(j).get(k)])*groupElements[9][possCombinationsID.get(j).get(k)];
					}
					
					for(int k = 0; k < possCombinationsID.get(j).size(); k++){
						sumMinH += groupElements[9][possCombinationsID.get(j).get(k)];
					}
					
					if(bondSum == 0 && sumH == h && sumMinH <= h){
						pCEq.add(possCombinations.get(j));
					}
					else if(bondSum > 0 && sumH <= h){
						pCGr.add(possCombinations.get(j));
					}
				}
				System.out.println("Probable combinations of groups:");
				for(int j = 0; j < pCEq.size(); j++){
					System.out.println(pCEq.get(j) + ", ");
				}
				System.out.println();
				System.out.println("Possible combinations of groups:");
				for(int j = 0; j < pCGr.size(); j++){
					System.out.print(pCGr.get(j) + ", ");
				}
				System.exit(0);
			}
			recursion(i-1, count, h);
		}
	}
	public static void formEOU(){
		System.out.print("Forumla: ");
		for(int i = 0; i < elements.size(); i++){
			for(int j = 0; j < formulaTemplate.length; j++){
				if(elements.get(i).toUpperCase().equals(formulaTemplate[j])){
					formula[j] += number.get(i);
					break;
				}
			}
			System.out.print(elements.get(i)+number.get(i));
		}
		eou = formula[0] + 1 + formula[4]*0.5 - 0.5*(formula[1] + formula[3] + formula[5] + formula[6] + formula[7]);
	}
}