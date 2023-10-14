package Science;
public class Molecule4 {
	public static void main(String[]args) {
		double radii = 3;
		Atom [] atoms = new Atom[4];
		for(int i = 0; i < atoms.length; i++) {
			double x = Math.random()*radii;
			double y = Math.random()*(radii-x);
			double z = Math.sqrt(Math.pow(radii, 2) - Math.pow(x, 2) - Math.pow(y, 2));
			atoms[i] = new Atom(x, y, z);
		}
		for(int i = 0; i < atoms.length; i++) {
			System.out.println(atoms[i]);
		}
		System.out.println();
		for(int k = 0; k < 1; k++) {
			for(int i = 0; i < atoms.length; i++) {
				double tempX = 0.0;
				double tempY = 0.0;
				double tempZ = 0.0;
				for(int j = 0; j < atoms.length; j++) {
					if(i == j) {

					}
					else {
						tempX += atoms[j].x;
						tempY += atoms[j].y;
						tempZ += atoms[j].z;
					}
				}
				atoms[i].x = tempX/(double)(atoms.length - 1);
				atoms[i].y = tempY/(double)(atoms.length - 1);
				atoms[i].z = tempZ/(double)(atoms.length - 1);
				System.out.println("CENTROID: " + atoms[i]);
				double distance = Math.sqrt(Math.pow(atoms[i].x, 2) + Math.pow(atoms[i].y, 2) + Math.pow(atoms[i].z, 2));
				atoms[i].x *= -radii/distance;
				atoms[i].y *= -radii/distance;
				atoms[i].z *= -radii/distance;
				for(int j = 0; j < atoms.length; j++) {
					System.out.println(atoms[j]);
				}
				System.out.println();
			}	
		}
		for(int i = 0; i < atoms.length; i++) {
			System.out.println(atoms[i]);
		}
		System.out.println(atoms.length);
		Molecule m = new Molecule();
		System.out.println(m.angle(atoms[0], atoms[1], atoms[3]));
	}
}