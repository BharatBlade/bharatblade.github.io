package College;
/* Name: John Paliakkara
 * Course: CIS 203
 * Date: 5/12/17 
 * Assignment: 10
 * Username: paliakjj199
 */

import java.util.*;

public class Client {

    public static void main (String [] args) {
        int arg = Integer.parseInt("4");
       if (arg == 1)
           testHasPreviousPrevious();
       else if (arg == 2)
           testNextIndex();
       else if (arg == 3)
           testPreviousIndex();
       else if (arg == 4)
           testAdd();
       else if (arg == 5)
           testRemove();
       else if (arg == 6)
           testSet();
        else {
	    System.out.println("1 - hasPrevious() and previous()");
	    System.out.println("2 - nextIndex()");
	    System.out.println("3 - previousIndex()");
	    System.out.println("4 - add()");
	    System.out.println("5 - remove()");
            System.out.println("6 - set()");
	}
    }

    public static void testSet() {
        
        System.out.println("\nTesting set()...");
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int i = 1; i <= 7; i++)
            list.add(i);
        System.out.println("list = " + list);
        ListIterator<Integer> i = list.listIterator();
        System.out.print("Try to set() without calling next() or previous()... ");
        try {
	    i.set(999);
	} catch(IllegalStateException e) {
	    System.out.println(e + " caught");
	}
        System.out.println("i.next() = " + i.next());
        System.out.println("setting what was returned to 999... ");
	i.set(999);
        System.out.println("list = " + list);
        System.out.println("going to end of list...");
	while (i.hasNext())
	    i.next();
        System.out.println("i.previous() = " + i.previous());
        System.out.println("setting what was returned to -999... ");
	i.set(-999);
        System.out.println("list = " + list);
        System.out.println("going back three...");
        System.out.println("i.previous() = " + i.previous());
        System.out.println("i.previous() = " + i.previous());
        System.out.println("i.previous() = " + i.previous());
        System.out.println("setting what was returned to 1000... ");
	i.set(1000);
        System.out.println("list = " + list);

    }

    public static void testRemove() {
        System.out.println("\nTesting remove()...");
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int i = 1; i <= 7; i++)
            list.add(i);
        System.out.println("list = " + list);
        ListIterator<Integer> i = list.listIterator();
        System.out.print("Try to remove() without calling next() or previous()... ");
        try {
	    i.remove();
	} catch(IllegalStateException e) {
	    System.out.println(e + " caught");
	}
        System.out.println("i.next() = " + i.next());
        System.out.println("removing what was returned... ");
        i.remove();
        System.out.println("list = " + list);
        System.out.println("Going to end of list and removing...");
        while (i.hasNext())
	    i.next();
        i.remove();
        System.out.println("list = " + list);
        System.out.print("Try to remove() without calling next () or previous()... ");
        try {
	    i.remove();
	} catch(IllegalStateException e) {
	    System.out.println(e + " caught");
	}
        System.out.println("i.previous() = " + i.previous());
        System.out.println("removing what was returned... ");
	i.remove();
        System.out.println("list = " + list);
        System.out.print("Try to remove() without calling previous() or next()... ");
        try {
	    i.remove();
	} catch(IllegalStateException e) {
	    System.out.println(e + " caught");
	}
        System.out.println("There should be no next element: " + !i.hasNext());
        System.out.println("i.previous() = " + i.previous());
        System.out.println("removing what was returned... ");
	i.remove();
        System.out.println("list = " + list);
        System.out.println("Going to the beginning of the list");
        while (i.hasPrevious())
	    i.previous();
        System.out.println("and doing a removal...");
        i.remove();
        System.out.println("list = " + list);
        System.out.println("There should be no previous element: " + !i.hasPrevious());
    }

    public static void testAdd() {
        System.out.println("\nTesting add()...");
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int i = 1; i <= 7; i++)
            list.add(i);
        System.out.println("list = " + list);
        ListIterator<Integer> i = list.listIterator();
        System.out.println("Advancing 2... ");
        System.out.println("i.next() = " + i.next());
        System.out.println("i.next() = " + i.next());
        System.out.println("Adding 999 ");
        i.add(999);
        System.out.println("list = " + list);
        System.out.print("Going to end of list... ");
        while (i.hasNext())
	    i.next();
        System.out.println("Adding 1000 ");
        i.add(1000);
        System.out.println("list = " + list);
        System.out.print("Going to beginning of list... ");        
        while (i.hasPrevious())
	    i.previous();
        System.out.println("Adding 2000 ");
        i.add(2000);
        System.out.println("list = " + list);
        System.out.println("Advancing three times...");
        System.out.println("i.next() = " + i.next());
        System.out.println("i.next() = " + i.next());
        System.out.println("i.next() = " + i.next());
        System.out.println("and back once...");
        System.out.println("i.previous() = " + i.previous());
        System.out.println("Adding 3000 ");
	i.add(3000);
        System.out.println("list = " + list);
    }

    public static void testPreviousIndex() {
        System.out.println("\nTesting previousIndex()...");
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int i = 1; i <= 7; i++)
            list.add(i);
        System.out.println("list = " + list);
        ListIterator<Integer> i = list.listIterator();
        System.out.println("On new ListIterator i, i.previousIndex() = " +
			   i.previousIndex());
	System.out.println("Advancing twice...");
        System.out.println("i.next() = " + i.next());
        System.out.println("i.next() = " + i.next());
        System.out.print(" and now i.nextIndex() = " + i.nextIndex());
        System.out.println(" and i.previousIndex() = " + i.previousIndex());
	while (i.hasNext())
	    i.next();
        System.out.print("Running out this iterator,");
        System.out.print(" and now i.nextIndex() = " + i.nextIndex());
        System.out.println(" and i.previousIndex() = " + i.previousIndex());
        System.out.println("Clearing list...");
        list.clear();
        System.out.println("list = " + list);
        i = list.listIterator();        
        System.out.print("and now i.nextIndex() = " + i.nextIndex());
        System.out.println(" and  i.previousIndex() = " + i.previousIndex());
    }


    public static void testNextIndex() {
        System.out.println("\nTesting nextIndex()...");
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int i = 1; i <= 7; i++)
            list.add(i);
        System.out.println("list = " + list);
        ListIterator<Integer> i = list.listIterator();
        System.out.println("On new ListIterator i, i.nextIndex() = " +
			   i.nextIndex());
	System.out.println("Advancing twice...");
	System.out.println("i.next() = " + i.next());
	System.out.println("i.next() = " + i.next());
        System.out.println("and now i.nextIndex() = " + i.nextIndex());
	while (i.hasNext())
	    i.next();
        System.out.println("Running out this iterator... ");
        System.out.println("and now i.nextIndex() = " + i.nextIndex());
        System.out.println("Clearing list...");
        list.clear();
        System.out.println("list = " + list);
        i = list.listIterator();        
        System.out.println("and now i.nextIndex() = " + i.nextIndex());
    }

    public static void testHasPreviousPrevious() {
        System.out.println("Testing hasPrevious() and previous()...");
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int i = 1; i <= 7; i++)
            list.add(i);
        System.out.println("list = " + list);
        ListIterator<Integer> i = list.listIterator();
        System.out.println("Is there a previous right now?...");
	try {
	    i.previous();
	} catch (NoSuchElementException e) {
	    System.out.println(e + " caught");
	}
	System.out.println("Going to end of list...");
        while (i.hasNext())
	    i.next();
	System.out.println("and traverse it backwards");
	while (i.hasPrevious())
	    System.out.print(i.previous() + " ");
	System.out.println();
	System.out.println("Clearing list..");
        list.clear();
        System.out.println("list = " + list);
	i = list.listIterator();
	System.out.println("Going to end of list...");
        while (i.hasNext())
	    i.next();
	System.out.println("Going to beginning of list...");
	while (i.hasPrevious())
	    System.out.print(i.previous() + " ");
	System.out.println("Making new nonempty list...");
        list = new LinkedList<Integer>();
        for (int j = 1; j <= 7; j++)
            list.add(j);
        System.out.println("list = " + list);
        System.out.println("Advancing  two elements...");
        i = list.listIterator(); 
	System.out.println("i.next() = " + i.next());
	System.out.println("i.next() = " + i.next());
        System.out.println("Interleaving use of next() and previous() " +
                           "5 times we get...");      
        for (int j = 0; j < 5; j++) 
            System.out.print(i.next() + " " + i.previous() + " ");
	System.out.println();
    }
}
