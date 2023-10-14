package College;
/*Name: John Paliakkara
Date: 2/19/2017
Course: CIS 203
Assn: 4 Ngram Solver*/
import java.util.*;
public class NgramSolver {

	//Map field is to record each sequence of characters, and their predicted sequential character(s)
	//Starts field is to record the character sequences at the beginning of each sentence
	private Map<String, String> m1 = new TreeMap<String, String>();
	private ArrayList<String> starts = new ArrayList<String>();
	
	//Constructor that takes integer n for length of each character sequence saved
	//and the string test to read character sequences from
	public NgramSolver(int n, String test){
		starts.add(test.substring(0, n));
		for(int i = 0; i < test.length() - n; i++){
			String key = test.substring(i, i+n);
			String value = test.substring(i+n, i+n+1);
			if(m1.containsKey(key)){
				m1.replace(key, m1.get(key).toString()+value);
			}
			else{
				m1.put(key, value);				
			}
			String end = test.substring(i, i+2);
			if(i != test.length()-1 && (end.equals(". ") || end.equals("? ") || end.equals("! "))){
				starts.add(test.substring(i+2, i+2+n));
			}
		}
	}
	
	//returns true if the map contains the given ngram
	public boolean contains(String ngram){
		return m1.containsKey(ngram);
	}
	
	//returns a random char from the value of the ngram's key
	//e.g. if map key = "is " and value = "iaf"
	//method returns either "i", "a", or "f" at random
	public char randomChar(String ngram){
		String temp = m1.get(ngram).toString();
		return temp.charAt((int)(Math.random()*temp.length()));
	}
	
	//returns a random start character sequence from the start arraylist 
	//that recorded the beginning character sequence of each sentence
	public String randomStart(){
		return starts.get((int)(Math.random()*starts.size()));
	}
}