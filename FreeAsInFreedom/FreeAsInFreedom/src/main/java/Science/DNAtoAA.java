package Science;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class DNAtoAA {
	public static void main(String[]args) throws FileNotFoundException{
		Scanner DNA = new Scanner(new File("C:\\Users\\John\\Downloads\\Pf3D7_11_v3_1_2038340.gff3"));
		String DNASequence = "";
		String AASequence = "";
		long time = System.currentTimeMillis();
		System.out.println("STEP1");
		int count = 0;
		while(DNA.hasNext()){
			count++;
			DNASequence += DNA.next();
		}
		System.out.println("STEP2");
		for(int i = 0; i < DNASequence.length(); i+=3){
			try{
				String codon = DNASequence.substring(i, i+3);
				String AA = "";
				if(codon.equals("TTT")||codon.equals("TTC")){
					AA = "F";
				}
				else if(codon.equals("CTT")||codon.equals("CTC")||codon.equals("CTA")||codon.equals("CTG")||codon.equals("TTA")||codon.equals("TTG")){
					AA = "L";
				}
				else if(codon.equals("ATT")||codon.equals("ATC")||codon.equals("ATA")){
					AA = "I";
				}
				else if(codon.equals("ATG")){
					AA = "M";
				}
				else if(codon.equals("GTT")||codon.equals("GTC")||codon.equals("GTA")||codon.equals("GTG")){
					AA = "V";
				}
				else if(codon.equals("TCT")||codon.equals("TCC")||codon.equals("TCA")||codon.equals("TCG")||codon.equals("AGT")||codon.equals("AGC")){
					AA = "S";
				}
				else if(codon.equals("CCT")||codon.equals("CCC")||codon.equals("CCA")||codon.equals("CCG")){
					AA = "P";
				}
				else if(codon.equals("ACT")||codon.equals("ACC")||codon.equals("ACA")||codon.equals("ACG")){
					AA = "T";
				}
				else if(codon.equals("GCT")||codon.equals("GCC")||codon.equals("GCA")||codon.equals("GCG")){
					AA = "A";
				}
				else if(codon.equals("TAT")||codon.equals("TAC")){
					AA = "Y";
				}
				else if(codon.equals("TAA")||codon.equals("TAG")||codon.equals("TGA")){
					AA = "U";
				}
				else if(codon.equals("CAT")||codon.equals("CAC")){
					AA = "H";
				}
				else if(codon.equals("CAA")||codon.equals("CAG")){
					AA = "Q";
				}
				else if(codon.equals("AAT")||codon.equals("AAC")){
					AA = "N";
				}
				else if(codon.equals("AAA")||codon.equals("AAG")){
					AA = "K";
				}
				else if(codon.equals("GAT")||codon.equals("GAC")){
					AA = "D";
				}
				else if(codon.equals("GAA")||codon.equals("GAG")){
					AA = "E";
				}
				else if(codon.equals("TGT")||codon.equals("TGC")){
					AA = "C";
				}
				else if(codon.equals("TGG")){
					AA = "W";
				}
				else if(codon.equals("CGT")||codon.equals("CGC")||codon.equals("CGA")||codon.equals("CGG")||codon.equals("AGA")||codon.equals("AGG")){
					AA = "R";
				}
				else if(codon.equals("GGT")||codon.equals("GGC")||codon.equals("GGA")||codon.equals("GGG")){
					AA = "G";
				}
				AASequence += AA;
			}
			catch(Exception e){
				
			}
		}
		PrintStream out = new PrintStream(new File("AA.txt"));
		for(int i = 0; i < AASequence.length(); i+=100){
			try{
				out.println(AASequence.substring(i, i+100));
			}
			catch(Exception e){
				out.println(AASequence.substring(i));
			}
		}
		System.out.println(System.currentTimeMillis() - time);
	}
}
