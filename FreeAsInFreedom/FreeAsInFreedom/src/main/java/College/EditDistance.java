package College;
import java.io.*;
import java.util.*;
public class EditDistance {
	//Program that tells the user what it is
	//Then asks for a dictionary file
	//Then asks for two words
	//Prints out the distance between the words
	public static void main(String[]args) throws FileNotFoundException{
		double time = System.currentTimeMillis();
		giveIntro();
		File file = getFile();
		Scanner dictionaryScanner = new Scanner(file);
		Map<String, List<String>> neighbors = buildMap(dictionaryScanner);
		List<String> a = getWords(neighbors);
		doMatches(a, neighbors);
		double time2 = System.currentTimeMillis();
		System.out.println(time2-time);
	}
	
	//Tells how this program works
	public static void giveIntro(){
		System.out.println("This program uses a dictionary to compute the edit distances between pairs of words.");
	}
	
	//Asks the user for the dictionary file
	//Checks to make sure file exists
	public static File getFile(){
		Scanner sc = new Scanner(System.in);
		System.out.print("What is the dictionary file? ");
		File f = new File(sc.nextLine());
		while(!f.exists()){
			System.out.print("File not found. What is the dictionary file? ");
			f = new File(sc.nextLine());
		}
		return f;
	}
	
	//Map of adjacent words from dictionary file
	public static Map<String, List<String>> buildMap(Scanner sc){
		Map<String, List<String>> a = new TreeMap<String, List<String>>();
		ArrayList<String> b = new ArrayList<String>();
		while(sc.hasNext()){
			b.add(sc.next());
		}
		for(int i = 0; i < b.size(); i++){
			String key = b.get(i);
			List<String> c = (List<String>) new ArrayList<String>();
			for(int j = 0; j < b.size(); j++){
				String value = b.get(j);
				if(isNeighbor(key, value)){
					c.add(value);
				}
			}
			a.put(key, c);
			if(i%1992 == 0)
				System.out.println((double)i/b.size()*100 + "% done ");
		}
		System.out.println();
		return a;
	}
	
	//Uses the editDistance method to print distance between two words
	public static void doMatches(List<String> a, Map<String, List<String>> b){
		System.out.println(editDistance(a.get(0), a.get(1), b));
	}
	
	//Determines if two words are "neighbors" (they have a one letter difference)
	public static boolean isNeighbor(String a, String b){
		if(a.length() == b.length()){
			ArrayList<String> c = new ArrayList<String>(Arrays.asList(a.split("")));
			ArrayList<String> d = new ArrayList<String>(Arrays.asList(b.split("")));
			for(int i = 0; i < c.size(); i++){
				if(c.get(i).equals(d.get(i))){
					c.remove(i);
					d.remove(i);
					i--;
				}
			}
			if(c.size() == 1){
				return true;
			}
		}
		return false;
	}
	
	//Asks the two words from the users
	//Checks if the two words are in the dictionary file
	public static List<String> getWords(Map<String, List<String>> a){
		Scanner test = new Scanner(System.in);
		System.out.print("word1: ");
		String b = test.nextLine();
		while(!a.containsKey(b)){
			if(b.length() == 0){
				System.exit(0);
			}
			System.out.print(b + " is not in the dictionary");
			b = test.nextLine();
		}
		System.out.print("word2: ");
		String c = test.nextLine();
		while(!a.containsKey(c)){
			if(c.length() == 0){
				System.exit(0);
			}
			System.out.print(c + " is not in the dictionary");
			c = test.nextLine();
		}
		return (List<String>) new ArrayList<String>(Arrays.asList(b, c));
	}
	
	//calculates the distance between two words
	public static int editDistance(String start, String target, Map<String, List<String>> map){
		ArrayList<String> path = new ArrayList<String>();
		int min = 100;
		if(isNeighbor(start, target)){
			path.add(start);
			return 1;
		}
		else{
			path.add(start);
			for(int i = 0; i < map.get(start).size(); i++){
				if(!path.contains(map.get(start).get(i))){
					path = new ArrayList<String>();
					path.add(start);
					min = Integer.min(min, editDistance(map.get(start).get(i), target, map, path, min));
				}
			}
		}
		return min - 1;
	}	
	
	//Is a helper method for the above edit distance method
	//is used by the main edit distance to do the majority of calculation
	private static int editDistance(String start, String target, Map<String, List<String>> map, ArrayList<String> path, int min){
		if(path.size() > min)
			return min+100;//////////////////////////////
		if(isNeighbor(start, target)){
			path.add(start);
			path.add(target);
			min = Integer.min(min, path.size());
		}
		else{
			path.add(start);
			ArrayList<String> temp = new ArrayList<String>();
			for(int i = 0; i < path.size(); i++){
				temp.add(path.get(i));
			}
			for(int i = 0; i < map.get(start).size(); i++){
				if(!path.contains(map.get(start).get(i))){
					path = new ArrayList<String>();
					path.addAll(temp);
					min = Integer.min(min, editDistance(map.get(start).get(i), target, map, path, min));
				}
			}
		}
		return min;
	}
	
}