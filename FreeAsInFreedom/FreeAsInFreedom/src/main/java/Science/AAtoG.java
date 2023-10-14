package Science;

public class AAtoG {
	public static void main(String[]args){
		String aA = "MKLLTGLVFCSLVLGVSSRSFFSFLGEAFDGARDMWRAYSDMREANYIGSDKYFHARGNYDAAKRGPGGVWAAEAISDARENIQRFFGHGAEDSLADQAANEWGRSGKDPNHFRPAGLPEKY";
		String g = "";
		int hCount = 0;
		int pCount = 0;
		for(int i = 0; i < aA.length(); i++){
			if(aA.substring(i, i+1).equals("G")||aA.substring(i, i+1).equals("A")||aA.substring(i, i+1).equals("V")||aA.substring(i, i+1).equals("L")||aA.substring(i, i+1).equals("I")||aA.substring(i, i+1).equals("M")){
				g += "H";
				hCount++;
			}
			else if(aA.substring(i, i+1).equals("F")||aA.substring(i, i+1).equals("W")||aA.substring(i, i+1).equals("Y")){
				g += "H";
				hCount++;
			}
			else if(aA.substring(i, i+1).equals("N")||aA.substring(i, i+1).equals("C")||aA.substring(i, i+1).equals("Q")||aA.substring(i, i+1).equals("S")||aA.substring(i, i+1).equals("T")){
				g += "0";
				pCount++;
			}
			else if(aA.substring(i, i+1).equals("D")||aA.substring(i, i+1).equals("E")){
				g += "-";
				pCount++;
			}
			else if(aA.substring(i, i+1).equals("R")||aA.substring(i, i+1).equals("H")||aA.substring(i, i+1).equals("K")){
				g += "+";
				pCount++;
			}
			else if(aA.substring(i, i+1).equals("P")){
				g += "U";
			}
		}
		System.out.println(hCount);
		System.out.println((double)hCount/aA.length()*100);
		System.out.println((double)pCount/aA.length()*100);
		System.out.println(g);
		String palindrome = "";
		for(int i = g.length()-1; i > -1; i--){
			palindrome += g.substring(i, i+1);
		}
		System.out.println(palindrome);
		int good = 0;
		int bad = 0;
		for(int i = 0; i < g.length(); i++){
			if(g.substring(i, i+1).equals(palindrome.substring(i, i+1)) && g.substring(i, i+1).equals("H")){
				good++;
			}
			else if(g.substring(i, i+1).equals("H") || palindrome.substring(i, i+1).equals("H")){
				bad++;
			}
			else if(g.substring(i, i+1).equals(palindrome.substring(i, i+1)) && g.substring(i, i+1).equals("0")){
				good++;
			}
			else if(g.substring(i, i+1).equals(palindrome.substring(i, i+1))){
				bad++;
			}
			else if((g.substring(i, i+1)+palindrome.substring(i, i+1)).contains("+")&&(g.substring(i, i+1)+palindrome.substring(i, i+1)).contains("-")){
				good++;
			}
		}
		System.out.println(good);
		System.out.println(bad);
		
		
		
		
		good = 0;
		bad = 0;
		for(int i = 0; i < g.length(); i++){
			if(g.substring(i, i+1).equals(g.substring(i, i+1)) && g.substring(i, i+1).equals("H")){
				good++;
			}
			else if(g.substring(i, i+1).equals("H") || g.substring(i, i+1).equals("H")){
				bad++;
			}
			else if(g.substring(i, i+1).equals(g.substring(i, i+1)) && g.substring(i, i+1).equals("0")){
				good++;
			}
			else if(g.substring(i, i+1).equals(g.substring(i, i+1))){
				bad++;
			}
			else if((g.substring(i, i+1)+g.substring(i, i+1)).contains("+")&&(g.substring(i, i+1)+g.substring(i, i+1)).contains("-")){
				good++;
			}
		}
		System.out.println(good);
		System.out.println(bad);
	}
}
