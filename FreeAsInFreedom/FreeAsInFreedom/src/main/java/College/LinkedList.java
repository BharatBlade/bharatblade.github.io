package College;
/* Name: John Paliakkara
 * Course: CIS 203
 * Date: 5/12/17 
 * Assignment: 10
 * Username: paliakjj199
 */

// Class LinkedList<E> can be used to store a list of values of type E.

import java.util.*;

public class LinkedList<E> implements List<E> {
    private ListNode<E> front;  // first value in the list
    private ListNode<E> back;   // last value in the list
    private int size;           // current number of elements

    // post: constructs an empty list
    public LinkedList() {
        front = new ListNode<E>(null);
        back = new ListNode<E>(null);
        clear();
    }
    
	public ListIterator listIterator() {
		return new LinkedListIterator();
	}
    
    // post: returns the current number of elements in the list
    public int size() {
        return size;
    }

    // pre : 0 <= index < size() (throws IndexOutOfBoundsException if not)
    // post: returns the value at the given index in the list
    public E get(int index) {
        checkIndex(index);
        ListNode<E> current = nodeAt(index);
        return current.data;
    }

    // post: creates a comma-separated, bracketed version of the list
    public String toString() {
        if (size == 0) {
            return "[]";
        } else {
            String result = "[" + front.next.data;
            ListNode<E> current = front.next.next;
            while (current != back) {
                result += ", " + current.data;
                current = current.next;
            }
            result += "]";
            return result;
        }
    }

    // post : returns the position of the first occurrence of the given
    //        value (-1 if not found)
    public int indexOf(E value) {
        int index = 0;
        ListNode<E> current = front.next;
        while (current !=  back) {
            if (current.data.equals(value)) {
                return index;
            }
            index++;
            current = current.next;
        }
        return -1;
    }

    // post: returns true if list is empty, false otherwise
    public boolean isEmpty() {
        return size == 0;
    }

    // post: returns true if the given value is contained in the list,
    //       false otherwise
    public boolean contains(E value) {
        return indexOf(value) >= 0;
    }

    // post: appends the given value to the end of the list
    public void add(E value) {
        add(size, value);
    }

    // pre: 0 <= index <= size() (throws IndexOutOfBoundsException if not)
    // post: inserts the given value at the given index, shifting subsequent
    //       values right
    public void add(int index, E value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
        ListNode<E> current = nodeAt(index - 1);
        ListNode<E> newNode = new ListNode<E>(value, current.next, current);
        current.next = newNode;
        newNode.next.prev = newNode;
        size++;
    }

    // post: appends all values in the given list to the end of this list
    public void addAll(List<E> other) {
        for (E value: other) {
            add(value);
        }
    }

    // pre : 0 <= index < size() (throws IndexOutOfBoundsException if not)
    // post: removes value at the given index, shifting subsequent values left
    public void remove(int index) {
        checkIndex(index);
        ListNode<E> current = nodeAt(index - 1);
        current.next = current.next.next;
        current.next.prev = current;
        size--;
    }

    // pre : 0 <= index < size() (throws IndexOutOfBoundsException if not)
    // post: replaces the value at the given index with the given value
    public void set(int index, E value) {
        checkIndex(index);
        ListNode<E> current = nodeAt(index);
        current.data = value;
    }

    // post: list is empty
    public void clear() {
        front.next = back;
        back.prev = front;
        size = 0;
    }

    // post: returns an iterator for this list
    public Iterator<E> iterator() {
        return new LinkedIterator();
    }

    // pre : 0 <= index < size()
    // post: returns the node at a specific index.  Uses the fact that the list
    //       is doubly-linked to start from the front or the back, whichever
    //       is closer.
    private ListNode<E> nodeAt(int index) {
        ListNode<E> current;
        if (index < size / 2) {
            current = front;
            for (int i = 0; i < index + 1; i++) {
                current = current.next;
            }
        } else {
            current = back;
            for (int i = size; i >= index + 1; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    // post: throws an IndexOutOfBoundsException if the given index is
    //       not a legal index of the current list
    private void checkIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
    }

    private static class ListNode<E> {
        public E data;         // data stored in this node
        public ListNode<E> next;  // link to next node in the list
        public ListNode<E> prev;  // link to previous node in the list

        // post: constructs a node with given data and null links
        public ListNode(E data) {
            this(data, null, null);
        }

        // post: constructs a node with given data and given links
        public ListNode(E data, ListNode<E> next, ListNode<E> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    private class LinkedIterator implements Iterator<E> {
        protected ListNode<E> current;  // location of next value to return
        protected boolean removeOK;  // whether it's okay to remove now

        // post: constructs an iterator for the given list
        public LinkedIterator() {
            current = front.next;
            removeOK = false;
        }

        // post: returns true if there are more elements left, false otherwise
        public boolean hasNext() {
            return current != back;
        }

        // pre : hasNext()
        // post: returns the next element in the iteration
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E result = current.data;
            current = current.next;
            removeOK = true;
            return result;
        }

        // pre : next() has been called without a call on remove (i.e., at most
        //       one call per call on next)
        // post: removes the last element returned by the iterator
        public void remove() {
            if (!removeOK) {
                throw new IllegalStateException();
            }
            ListNode<E> prev2 = current.prev.prev;
            prev2.next = current;
            current.prev = prev2;
            size--;
            removeOK = false;
        }
    }
    
    private class LinkedListIterator extends LinkedIterator implements ListIterator<E> {
    	
    	//Constructor
    	public LinkedListIterator(){
    		super();
    	}

    	
    	//Checks if there is a node with data
    	//after the current node
		public boolean hasNext() {
			if(size == 0){
				return false;
			}
			return current.next != null;
		}
		
		//changes the position of current to the next node
		//returns the data of that node
		public E next() {
			current = current.next;
			return current.data;
		}

		//checks if there is a node with data
		//before the current node
		public boolean hasPrevious() {
			return current.prev != null;
		}
		
		//changes the position of the current to the previous node
		//returns the data of that node
		public E previous() {
			current = current.prev;
			return current.data;
		}
		
		//returns the "index" of the ListNode
		//aftert the current ListNode
		public int nextIndex() {
			int count = 0;
			ListNode<E> temp = current;
			while(temp.prev != null){
				temp = temp.prev;
				count++;
			}
			return count+1;
		}

		//returns the "index" of the ListNode
		//previous the current ListNode
		public int previousIndex() {
			int count = 0;
			ListNode<E> temp = current;
			while(temp.prev != null){
				temp = temp.prev;
				count++;
			}
			return count-1;		
		}

		//removes the current ListNode
		public void remove() {
			if(current.prev == null){
				front = front.next;
			}
			else if(current.next == null){
				back = back.prev;
			}
			else{
				current.prev.next = current.prev.next.next;
			}
		}
		
		//sets the current ListNode to the value given
		public void set(E e) {
			current.data = e;
		}
		
		//adds the given element immediately after
		//the current ListNode
		public void add(E e) {
			current.next = new ListNode(e, current.next, current.prev);	
		}	
    }
}
