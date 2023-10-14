package College;
/* Name: John Paliakkara
 * Course: CIS 203
 * Date: 5/3/17 
 * Assignment: 9B
 * Username: paliakjj199
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class GrammarSolver {
	
	//Map of tags and a double ArrayList of strings for each set of possible words for each tag
	//Also String sentence for adding the randomly selected words to form a sentence
	private TreeMap<String, ArrayList<ArrayList<String>>> grammarMap = new TreeMap<String, ArrayList<ArrayList<String>>>();
	private String sentence = "";
	
	//Creates adds the tags of the grammar as the keys
	//and sets the values to double ArrayLists to hold the sets of possibile words
	public GrammarSolver(List<String> grammar){
		for(int i = 0; i < grammar.size(); i++){
			ArrayList<ArrayList<String>> values = new ArrayList<ArrayList<String>>();
			ArrayList<String> temp = new ArrayList<String>();
			String key = grammar.get(i).substring(0, grammar.get(i).indexOf("=")-2);
			String t = grammar.get(i).substring(grammar.get(i).indexOf("=")+1);
			if(t.contains("<"))
				while(t.contains("<")){
					if(t.substring(0, 1).equals("|")){
						values.add(temp);
						temp = new ArrayList<String>();
						t.substring(1);
					}
					temp.add(t.substring(t.indexOf("<"), t.indexOf(">")+1));
					t = t.substring(t.indexOf(">")+1);
					if(t.indexOf("<") == -1)
						values.add(temp);
				}
			else{
				while(t.contains("|"))
					t = t.substring(0, t.indexOf("|")) + " " + t.substring(t.indexOf("|")+1);
				Scanner in = new Scanner(t);
				while(in.hasNext())
					temp.add(in.next());
				values.add(temp);
			}
			grammarMap.put(key, values);
		}
	}
	
	//Selects a random word or tag(s) based on the "symbol" given
	//and recursively adds the chosen words to the private field sentence
	//which then is added to an array of strings
	public String[] generate(String symbol, int times){
		String [] test = new String [times];
		for(int j = 0; j < times; j++){
			int random = (int)(Math.random()*grammarMap.get(symbol).size());
			if(grammarMap.get(symbol).toString().contains("<"))	
				for(int i = 0; i < grammarMap.get(symbol).get(random).size(); i++)
					generate(grammarMap.get(symbol).get(random).get(i), 1);
			else{
				int rand = (int)(Math.random()*grammarMap.get(symbol).get(0).size());
				sentence += grammarMap.get(symbol).get(0).get(rand) + " ";
			}
			test[j] = sentence;
		}
		for(int i = test.length-1; i > 0; i--)
			test[i] = test[i].substring(test[i-1].length(), test[i].length()-1);
		test[0] = test[0].substring(0, test[0].length()-1);
		return test;
	}
	
	//returns if the grammarSolver object (which is basically a map)
	//contains the given "symbol"
	public boolean grammarContains(String symbol){
		sentence = "";
		return grammarMap.get(symbol).toString().contains("<");
	}
	
	//returns the keyset of the map, which is just all the tag(s)
	//that the grammar file has
	public String getSymbols(){
		return grammarMap.keySet().toString();
	}
}