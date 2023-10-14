package College;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Test {
	public static void main(String[] args){
	    ArrayList<String> database = new ArrayList<String>(Arrays.asList("1,","2,","3,","4,","5,","6,","7,","8,","9,","10,"));
	    ArrayList<ArrayList<Integer>> totalSum = new ArrayList<ArrayList<Integer>>();
	    ArrayList<ArrayList<Integer>> totalProduct = new ArrayList<ArrayList<Integer>>();
	    for(int i=1; i<=database.size(); i++){
	        ArrayList<String> result = getAllArrayLists(database, i);
	        for(int j=0; j<result.size(); j++){
	            String temp = result.get(j).toString();
	            temp = temp.substring(0, temp.length()-1);
	            ArrayList<Integer> list = new ArrayList<Integer>();
	            while(temp.contains(",")){
	            	list.add(Integer.parseInt(temp.substring(0, temp.indexOf(","))));
	            	temp = temp.substring(temp.indexOf(",")+1);
	            }
	            list.add(Integer.parseInt(temp));
	            int s = 0;
	            int p = 1;
	            Collections.sort(list);
	            for(int k = 0; k < list.size(); k++)
	            	s += list.get(k);
	            ArrayList<Integer> product = new ArrayList<Integer>();
	            for(int k = 1; k <= 10; k++)
	            	if(!list.contains(k)){
	            		p *= k;
	            		product.add(k);
	            	}
	            if(!totalSum.contains(list) && s == 36 && p == 360){
	            	totalSum.add(list);
	            	totalProduct.add(product);
	            }
	        }
	    }
	    for(int i = 0; i < totalSum.size(); i++)
	    	System.out.println(totalSum.get(i));
	    for(int i = 0; i < totalProduct.size(); i++)
	    	System.out.println(totalProduct.get(i));
	}
	public static ArrayList<String> getAllArrayLists(ArrayList<String> elements, int lengthOfList){
	    ArrayList<String> allLists = new ArrayList<String>();
	    if(lengthOfList == 1)
	    	return elements;
	    else {
	        ArrayList<String> allSublists = getAllArrayLists(elements, lengthOfList - 1);
	        for(int i = 0; i < elements.size(); i++)
	            for(int j = 0; j < allSublists.size(); j++)
	            	if(!allSublists.get(j).contains(elements.get(i)))
                		allLists.add(elements.get(i) + allSublists.get(j));
	        return allLists;
	    }
	}	
}