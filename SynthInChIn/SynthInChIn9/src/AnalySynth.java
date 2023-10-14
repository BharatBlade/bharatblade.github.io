import java.util.ArrayList;

import org.openbabel.OBAtom;
import org.openbabel.OBAtomBondIter;
import org.openbabel.OBBond;
import org.openbabel.OBConversion;
import org.openbabel.OBMol;

public class AnalySynth {
	public static ArrayList<String> InChIArray(String InChI, int complex, boolean bt) throws Exception{
		OBConversion conv = new OBConversion();
		OBMol mol = new OBMol();
		conv.SetInFormat("inchi");
		conv.ReadString(mol, InChI);
		int size = (int) mol.NumAtoms();
		ArrayList<String> inchiArray = new ArrayList<String>();
		for(int i = 1; i <= size; i++) {
			OBAtom atom = mol.GetAtom(i);
			inchiArray.add(atom.GetType().replaceAll("\\d","") + atom.ImplicitHydrogenCount() + InChIArrayHelper(atom, i, 2));
		}
		return inchiArray;
	}
	
	public static String InChIArrayHelper(OBAtom atom, int i, int n) {
		String line = "";
		OBAtomBondIter iter = new OBAtomBondIter(atom);
		int size = atom.CountBondsOfOrder(1) + atom.CountBondsOfOrder(2) + atom.CountBondsOfOrder(3);
		for(int j = 0; j < size; j++) {
			OBBond bond = iter.next();
			OBAtom atom2 = bond.GetBeginAtom();
			if(atom2.GetIdx() == atom.GetIdx())
				atom2 = bond.GetEndAtom();
			if(n > 1)
				line += ("[" + atom2.GetType().replaceAll("\\d","") + atom2.ImplicitHydrogenCount() + InChIArrayHelper(atom2, j, n-1) + "]");
			else if(n == 1)
				line += ("[" + atom2.GetType().replaceAll("\\d","") + atom2.ImplicitHydrogenCount() + bond.GetBondOrder() + "]");
		}
		return line;
	}
}
