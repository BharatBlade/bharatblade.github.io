package Science;

import java.util.ArrayList;
import java.util.Arrays;

public class Molecule3 {
	
	public static void main(String[]args) throws Exception {
		long time = System.currentTimeMillis();
		int atomNum = 4;
		
		int rB = 3;		
		ArrayList<Atom> atoms = new ArrayList<Atom>();
		Atom [] current = new Atom[atomNum];
		for(int i = -rB; i <= rB; i+=1)
			for(int j = -rB; j <= rB; j+=1) {
				double z = Math.sqrt(Math.pow(rB, 2) - Math.pow(i, 2) - Math.pow(j, 2));
				if(Double.isNaN(z)) {
					continue;
				}
				atoms.add(new Atom(i, j, -z));
				atoms.add(new Atom(i, j, z));
			}
		for(int i = 0; i < current.length-1; i++) {
			current[i] = new Atom(0,0,0);
		}
		current[current.length-1] = new Atom(0,0,rB);
		double total = 0;
		for(int i = 0; i < atoms.size(); i++) {
			for(int j = 0; j < atoms.size(); j++) {
				for(int k = 0; k < atoms.size(); k++) {
					if(i != j && i != k && j != k) {
						double temp = totalDistance(current);
						if(temp > total) {
							total = temp;
							current[0] = atoms.get(i);
							current[1] = atoms.get(j);
							current[2] = atoms.get(k);
						}
					}
				}
			}
		}
		System.out.println(Arrays.toString(current));
		System.out.println(System.currentTimeMillis() - time);
		Atom [] current2 = new Atom[current.length];
		for(int i = 0; i < current.length; i++) {
			current2[i] = current[i];
		}
		
		for(int a = 0; a < 3; a++) {
			rB *= 10;
			ArrayList<Atom> atoms2 = createAtoms(current2[0], rB, a);
			ArrayList<Atom> atoms3 = createAtoms(current2[1], rB, a);
			ArrayList<Atom> atoms4 = createAtoms(current2[2], rB, a);
			current2 = new Atom[current.length];
			for(int i = 0; i < current2.length-1; i++) {
				current2[i] = new Atom(0,0,0);
			}
			current2[current2.length-1] = new Atom(0,0,rB);
			total = 0;
			System.out.println("SIZE: " + atoms2.size());
			for(int i = 0; i < atoms2.size(); i++) {
				System.out.print(i + ", ");
				for(int j = 0; j < atoms3.size(); j++) {
					for(int k = 0; k < atoms4.size(); k++) {
						double temp = totalDistance(current2);
						if(temp > total) {
							total = temp;
							current2[0] = atoms2.get(i);
							current2[1] = atoms3.get(j);
							current2[2] = atoms4.get(k);
						}
					}
				}
			}
			System.out.println();
			System.out.println(Arrays.toString(current2));
			System.out.println(System.currentTimeMillis() - time);
		}
	}
	public static double distance(Atom a, Atom b) {
		return Math.sqrt(Math.pow((b.x-a.x), 2) + Math.pow((b.y-a.y), 2) + Math.pow((b.z-a.z), 2));
	}
	public static double totalDistance(Atom [] test) {
		double d = 0.0;
		for(int i = 0; i < test.length; i++)
			for(int j = i+1; j < test.length; j++)
				d += distance(test[i], test[j]);
		return d;
	}
	
	public static ArrayList<Atom> createAtoms(Atom a, int rB, int s){
		ArrayList<Atom> atoms = new ArrayList<Atom>();
		double minX = a.x*10-(int)(Math.pow(10, s+1));
		double minY = a.y*10-(int)(Math.pow(10, s+1));
		double maxX = a.x*10+(int)(Math.pow(10, s+1));
		double maxY = a.y*10+(int)(Math.pow(10, s+1));
		if(minX < -rB) {
			minX = -rB;
		}
		if(minY < -rB) {
			minY = -rB;
		}
		if(maxX > rB) {
			maxX = rB;
		}
		if(maxY > rB) {
			maxY = rB;
		}
		for(double i = minX; i <= maxX; i+=2)
			for(double j = minY; j <= maxY; j+=2) {
				double z = Math.sqrt(Math.pow(rB, 2) - Math.pow(i, 2) - Math.pow(j, 2));
				if(Double.isNaN(z)) {
					continue;
				}
				atoms.add(new Atom(i, j, -z));
				atoms.add(new Atom(i, j, z));
			}
		return atoms;
	}
	
	
}