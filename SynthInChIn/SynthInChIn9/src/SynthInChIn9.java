import java.util.ArrayList;

public class SynthInChIn9 extends AnalySynth{
	public static void main(String[]args) throws Exception {
		System.loadLibrary("openbabel_java");
		String InChI = "InChI=1S/C6H8O7.Na/c7-3(8)1-6(13,5(11)12)2-4(9)10;/h13H,1-2H2,(H,7,8)(H,9,10)(H,11,12);/q;+1/p-1 ";
		ArrayList<String> atoms = new ArrayList<String>();
		String layer = InChI.substring(InChI.indexOf("/")+1, InChI.indexOf("/c"));
		String a = "";
		for(int i = 0; i < layer.length(); i++) {
			if((int)layer.charAt(i) >= 48 && (int)layer.charAt(i) <= 57) {
				if(!a.equals("")) {
					for(int j = 0; j < Integer.parseInt(layer.substring(i, i+1)); j++) {
						atoms.add(a);
					}
					a = "";
				}
			}
			else if (layer.charAt(i) != 'H' && layer.charAt(i) != '.'){
				a += layer.charAt(i);
			}
		}
		if(!a.equals("")) {
			atoms.add(a);
		}
		System.out.println(atoms);
		String num = "";
		for(int i = InChI.indexOf("/c")+2; i < InChI.indexOf("/h"); i++) {
			if((int)InChI.charAt(i) >= 48 && (int)InChI.charAt(i) <= 57) {
				num += InChI.charAt(i);
			}
			else {
				System.out.println(num);
				InChI = InChI.substring(0, i-num.length()) + atoms.get(Integer.parseInt(num)) + InChI.substring(i);
				num = "";
				i-=num.length();
			}
		}
		System.out.println(InChI);
		System.out.println(num);
	}
}