package College;
/* Name: John Paliakkara
 * Course: CIS 203
 * Date: 4/28/17 
 * Assignment: 9
 * Username: paliakjj199
 */
public class CombinationLock{
	
	/*Creates the start of the list of dashes
	Creates a boolean to determine if the turn method was used
	Creates an array of values to keep track and change values
	when the lock is turned
    */
    public ListNode dial;
    public boolean original = true;
    public int [] arr = {0, 10, 20, 30};
    
    //Constructor to make the lock
    public CombinationLock(){
		dial = new ListNode('-');
		initDial( dial ); 
	}
    
    //adds 40 dashes to the LinkedList
    public void initDial(ListNode temp){
    	for(int i = 1; i < 40; i++){
    		temp.next = new ListNode('-');
    		temp = temp.next;
    	}
    }
    
    /*main code, takes the dashes and prints them in
    right order so that when the minuses are changed
    into pluses, they appear to be moving around the lock*/
    public String toString(){
    	ListNode tempDial = dial;
    	String temp = "            " + arr[0] + "\n" + "            " + tempDial.data + "\n";
    	tempDial = tempDial.next;
    	for(int i = 0; i < 19; i++){
    		temp+="  ";
    		for(int j = 0; j < (9-i) || j < i-9; j++)
    			temp+=" ";
    		if(i == 9)
    			temp = temp.substring(0, temp.length()-2) + arr[3];
    		temp+=tempDial.data;
    		ListNode temp2Dial = dial;
    		for(int j = 0; (j < i*2+1 && i < 10) || (j < (18-i)*2+1 && i >= 10); j++)
    			temp+=" ";
    		for(int j = 0; j < 40-(i+1); j++)
    			temp2Dial = temp2Dial.next;
    		if(i == 9)
    			temp+=temp2Dial.data + "" + arr[1] + "\n";
    		else
        		temp+=temp2Dial.data + "\n";    			
    		tempDial = tempDial.next;
    	}
    	temp+="            " + tempDial.data + "\n" + "            " + arr[2];
    	return temp;
    }
    
    /*converts the right number of minuses into pluses
    based on the number it's turned to
    if false, then pluses are added to left side
    if true, pluses are added to right side*/
    public void turn(boolean direction, int n){
    	ListNode temp = dial;
    	if(original){
    		if(direction){
    			for(int i = 0; i < n; i++){				
    				ListNode temp2 = temp;
    				for(int j = 0; j < 39-i; j++)
            			temp2 = temp2.next;
    				temp2.data = '+';
       			}
    		}
    		else{
    			for(int i = 0; i < n; i++){
    				temp.data = '+';
    				temp = temp.next;
    			}
    		}
    	}
    	else{
    		while(temp != null){
				if(temp.data == '-')
					temp.data = '+';
				else
					temp.data = '-';
				temp = temp.next;
			}
    	}
    	for(int i = 0; i < arr.length; i++)
    		arr[i]+=n;
    	System.out.println(toString());
    	for(int i = 0; i < arr.length; i++)
    		arr[i]=i*10;
    	original = false;
    }
    
//////////////////////////////////////
    private class ListNode {
        char data;
        ListNode next;
        
        public ListNode(char data) {
            this.data = data;
            this.next = null;
        }

        public ListNode(char data, ListNode next) {
            this.data = data;
            this.next = next;
        }
    }
}