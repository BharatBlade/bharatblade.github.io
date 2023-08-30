package Utilities;

public class FastList {
	
	public char [] name = null;
	public FastList previous = null;
	public FastList next = null;
	public int size = 1;
	
	public FastList() {
		
	}
	
	public FastList(char [] n) {
		name = n;
	}

	public char [] getName() {
		return name;
	}

	public void setName(char [] name) {
		this.name = name;
	}

	public FastList getPrevious() {
		return previous;
	}

	public void setPrevious(FastList previous) {
		this.previous = previous;
	}

	public FastList getNext() {
		return next;
	}

	public void setNext(FastList next) {
		this.next = next;
	}
	
	
	public void add(FastList s) throws Exception {
		
		int a = this.compare(s);
		
		if(a > 0) {
			if(this.previous == null) {
				this.previous = s;
				s.next = this;
			}
			else {
				FastList temp = this;
				while(a > 0 && temp.previous != null) {
					temp = temp.previous;
					a = temp.compare(s);
				}
				if(temp.previous == null) {
					temp.previous = s;
					s.next = temp;
				}
				else {
					s.previous = temp;
					s.next = temp.next;
					temp.next.previous = s;
					temp.next = s;
				}
			}
			size++;
		}
		else if(a < 0) {
			if(this.next == null) {
				this.next = s;
				s.previous = this;
			}
			else {
				FastList temp = this;
				while(a < 0 && temp.next != null) {
					temp = temp.next;
					a = temp.compare(s);
				}
				if(temp.next == null) {
					temp.next = s;
					s.previous = temp;
				}
				else {
					s.next = temp;
					s.previous = temp.previous;
					temp.previous.next = s;
					temp.previous = s;
				}
			}
			size++;
		}
		
	}
	
	public String toString() {
		return String.valueOf(name);
	}
	
	public int compare(FastList s) throws Exception {
		for(int i = 0; i < 40; i++) {
			if(this.name[i] > s.name[i]) {return 1;}
			else if(this.name[i] < s.name[i]) {return -1;}
		}
		return 0;
	}
	
	public int size() {
		return size;	
	}
}
