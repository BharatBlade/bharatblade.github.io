import java.util.ArrayList;
import java.util.Arrays;

public class SynthInChInList extends Database {
	public static void main(String[]args) throws Exception{
		System.loadLibrary("openbabel_java");
		int complex = 0;
		ArrayList<ArrayList<String>> differences = new ArrayList<ArrayList<String>>();
		ArrayList<String> reactionNames = new ArrayList<String>();
		differences.add(differences(new ArrayList<String>(Arrays.asList("InChI=1S/C7H14/c1-3-5-7-6-4-2/h3H,1,4-7H2,2H3")), 
									new ArrayList<String>(Arrays.asList("InChI=1S/C7H15Br/c1-2-3-4-5-6-7-8/h2-7H2,1H3")), complex, true));
		reactionNames.add("heptene \tbromoheptane");
		differences.add(differences(new ArrayList<String>(Arrays.asList("InChI=1S/C6H12/c1-3-5-6-4-2/h3H,1,4-6H2,2H3")), 
									new ArrayList<String>(Arrays.asList("InChI=1S/C6H13Br/c1-2-3-4-5-6-7/h2-6H2,1H3")), complex, true));
		reactionNames.add("hexene \tbromohexane");
		differences.add(differences(new ArrayList<String>(Arrays.asList("InChI=1S/C5H10/c1-3-5-4-2/h3H,1,4-5H2,2H3")), 
									new ArrayList<String>(Arrays.asList("InChI=1S/C5H11Br/c1-2-3-4-5-6/h2-5H2,1H3")), complex, true));
		reactionNames.add("pentene \tbromopentane");
		differences.add(differences(new ArrayList<String>(Arrays.asList("InChI=1S/C4H8/c1-3-4-2/h3H,1,4H2,2H3")), 
									new ArrayList<String>(Arrays.asList("InChI=1S/C4H9Br/c1-2-3-4-5/h2-4H2,1H3")), complex, true));
		reactionNames.add("butene \tbromobutane");
		differences.add(differences(new ArrayList<String>(Arrays.asList("InChI=1S/C3H6/c1-3-2/h3H,1H2,2H3")), 
									new ArrayList<String>(Arrays.asList("InChI=1S/C3H7Br/c1-2-3-4/h2-3H2,1H3")), complex, true));
		reactionNames.add("propene \tbromopropane");
		differences.add(differences(new ArrayList<String>(Arrays.asList("InChI=1S/C2H4/c1-2/h1-2H2")), 
									new ArrayList<String>(Arrays.asList("InChI=1S/C2H5Br/c1-2-3/h2H2,1H3")), complex, true));
		reactionNames.add("ethene \tbromoethane");
		differences.add(differences(new ArrayList<String>(Arrays.asList("InChI=1S/C14H20/c1-2-3-4-5-6-8-11-14-12-9-7-10-13-14/h2,7,9-10,12-13H,1,3-6,8,11H2")), 
									new ArrayList<String>(Arrays.asList("InChI=1S/C14H21Br/c15-13-9-4-2-1-3-6-10-14-11-7-5-8-12-14/h5,7-8,11-12H,1-4,6,9-10,13H2")), complex, true));
		reactionNames.add("benzyl octene \t benzyl bromooctane");
		differences.add(differences(new ArrayList<String>(Arrays.asList("InChI=1S/C13H18/c1-2-3-4-5-7-10-13-11-8-6-9-12-13/h2,6,8-9,11-12H,1,3-5,7,10H2")), 
									new ArrayList<String>(Arrays.asList("InChI=1S/C13H19Br/c14-12-8-3-1-2-5-9-13-10-6-4-7-11-13/h4,6-7,10-11H,1-3,5,8-9,12H2")), complex, true));
		reactionNames.add("benzyl heptene \t benzyl bromoheptane");
		differences.add(differences(new ArrayList<String>(Arrays.asList("InChI=1S/C12H16/c1-2-3-4-6-9-12-10-7-5-8-11-12/h2,5,7-8,10-11H,1,3-4,6,9H2")), 
									new ArrayList<String>(Arrays.asList("InChI=1S/C12H17Br/c13-11-7-2-1-4-8-12-9-5-3-6-10-12/h3,5-6,9-10H,1-2,4,7-8,11H2")), complex, true));
		reactionNames.add("benzyl hexene \t benzyl bromohexane");
		differences.add(differences(new ArrayList<String>(Arrays.asList("InChI=1S/C11H14/c1-2-3-5-8-11-9-6-4-7-10-11/h2,4,6-7,9-10H,1,3,5,8H2")), 
									new ArrayList<String>(Arrays.asList("InChI=1S/C11H15Br/c12-10-6-2-5-9-11-7-3-1-4-8-11/h1,3-4,7-8H,2,5-6,9-10H2")), complex, true));
		reactionNames.add("benzyl pentene \t benzyl bromopentane");
		differences.add(differences(new ArrayList<String>(Arrays.asList("InChI=1S/C10H12/c1-2-3-7-10-8-5-4-6-9-10/h2,4-6,8-9H,1,3,7H2")), 
									new ArrayList<String>(Arrays.asList("InChI=1S/C10H13Br/c11-9-5-4-8-10-6-2-1-3-7-10/h1-3,6-7H,4-5,8-9H2")), complex, true));
		reactionNames.add("benzyl butene \t benzyl bromobutane");
		differences.add(differences(new ArrayList<String>(Arrays.asList("InChI=1S/C9H10/c1-2-6-9-7-4-3-5-8-9/h2-5,7-8H,1,6H2")), 
									new ArrayList<String>(Arrays.asList("InChI=1S/C9H11Br/c10-8-4-7-9-5-2-1-3-6-9/h1-3,5-6H,4,7-8H2")), complex, true));
		reactionNames.add("benzyl propene \t benzyl bromopropane");
		differences.add(differences(new ArrayList<String>(Arrays.asList("InChI=1S/C8H8/c1-2-8-6-4-3-5-7-8/h2-7H,1H2")), 
									new ArrayList<String>(Arrays.asList("InChI=1S/C8H9Br/c9-7-6-8-4-2-1-3-5-8/h1-5H,6-7H2")), complex, true));
		reactionNames.add("benzyl ethene \t benzyl bromoethane");
		differences.add(differences(new ArrayList<String>(Arrays.asList("InChI=1S/C11H20/c1-2-3-5-8-11-9-6-4-7-10-11/h2,11H,1,3-10H2")), 
									new ArrayList<String>(Arrays.asList("InChI=1S/C11H21Br/c12-10-6-2-5-9-11-7-3-1-4-8-11/h11H,1-10H2")), complex, true));
		reactionNames.add("cyclohexyl pentene \t cyclohexyl bromopentane");
		differences.add(differences(new ArrayList<String>(Arrays.asList("InChI=1S/C10H18/c1-2-3-7-10-8-5-4-6-9-10/h2,10H,1,3-9H2")), 
									new ArrayList<String>(Arrays.asList("InChI=1S/C10H19Br/c11-9-5-4-8-10-6-2-1-3-7-10/h10H,1-9H2")), complex, true));
		reactionNames.add("cyclohexyl butene \t cyclohexyl bromobutane");
		differences.add(differences(new ArrayList<String>(Arrays.asList("InChI=1S/C9H16/c1-2-6-9-7-4-3-5-8-9/h2,9H,1,3-8H2")), 
									new ArrayList<String>(Arrays.asList("InChI=1S/C9H17Br/c10-8-4-7-9-5-2-1-3-6-9/h9H,1-8H2")), complex, true));
		reactionNames.add("cyclohexyl propene \t cyclohexyl bromopropane");
		differences.add(differences(new ArrayList<String>(Arrays.asList("InChI=1S/C8H14/c1-2-8-6-4-3-5-7-8/h2,8H,1,3-7H2")), 
									new ArrayList<String>(Arrays.asList("InChI=1S/C8H15Br/c9-7-6-8-4-2-1-3-5-8/h8H,1-7H2")), complex, true));
		reactionNames.add("cyclohexyl ethene \t cyclohexyl bromoethane");
		differences.add(differences(new ArrayList<String>(Arrays.asList("InChI=1S/C7H12/c1-7-5-3-2-4-6-7/h1-6H2")), 
									new ArrayList<String>(Arrays.asList("InChI=1S/C7H13Br/c8-6-7-4-2-1-3-5-7/h7H,1-6H2")), complex, true));
		reactionNames.add("cyclohexyl methene \t cyclohexyl bromomethane");
		for(int i = 0; i < differences.size(); i++) {
			System.out.println(differences.get(i));
		}
		for(int i = 0; i < differences.size(); i++) {
			for(int j = 0; j < differences.size(); j++) {
				if(i != j && differences.get(i).equals(differences.get(j))) {
					differences.remove(j);
					reactionNames.remove(j);
					i--;
					break;
				}
			}
		}
		for(int i = 0; i < differences.size(); i++) {
			System.out.println(reactionNames.get(i));
		}
	}
}
