/*
 * Karl Palmer
 * CIS 203
 * Assignment 6
 */
package College;


import java.util.*;
import java.io.*;

public class BinSort2{
    public static void main(String args[]){
        //gets file name from args
        
        File file = new File("10.txt");
        try{
        	
            //makes scanner on the file
            Scanner sc = new Scanner(file);
            //goes through and sorts each line in the file using binsort
            int max = sc.nextInt();
            sc.nextInt();
                //gets the array representation of the line
                ArrayList<Integer>line = getLine(sc);
                System.out.println(line);
                //gets the maximum digits in the line
                //sorts the line
                sortBin(line, max);
                
            
            System.out.println(line);
        }catch(Exception ex){
            //System.out.println(ex);
        }
    }

    //getLine takes an string of ints and returns a arraylist
    private static ArrayList<Integer> getLine(Scanner sc){
        //makes arraylist to put values in
        ArrayList<Integer> result = new ArrayList<Integer>();
        //makes a scanner on the line
        //goes through line and adds the ints to the array
        while(sc.hasNextInt()){
            //converts string to int and adds to the array
            result.add(sc.nextInt());
        }

        //returns the array of ints for the line
        return result;
    }



    //takes an array list and a max int of digits and sorts
    private static void sortBin(ArrayList<Integer>list, int max){
        //makes bin of arraylists
        int pow =1;
        //initialize bin
        for(int i=0; i<=max;i++){
            ArrayList<Integer>[] bin = (ArrayList<Integer>[]) new ArrayList[10];
            for (int j = 0; j < bin.length; j++) {
                bin[j]=new ArrayList<Integer>();
            }

            for(int j=0; j<list.size(); j++){
                bin[(list.get(j)/pow)%10].add(list.get(j));
                }

                list.clear();

                for (int j = 0; j < bin.length; j++) {
                    list.addAll(bin[j]);
                }

            //move to the next digit to sort
            pow*=10;
        }
    }
}
