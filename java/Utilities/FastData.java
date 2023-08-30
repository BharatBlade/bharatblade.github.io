package Utilities;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.TreeMap;
import java.util.TreeSet;

public class FastData {

	public Constants c = new Constants();
	
	public FastData() {
		initializeMaps(c.d, c.dV, c.dK);
		initializeMaps(c.h, c.hV, c.hK);
		initializeMaps(c.aL, c.aLV, c.aLK);
		initializeMaps(c.aU, c.aUV, c.aUK);
		initializeMaps(c.s, c.sV, c.sK);
		initializeMaps(c.e, c.eV, c.eK);
		initializeMaps(c.d, c.aNSV, c.aNSK);
		initializeMaps(c.aU, c.aNSV, c.aNSK, c.aNSV.size());
		initializeMaps(c.aL, c.aNSV, c.aNSK, c.aNSV.size());
		initializeMaps(c.s, c.aNSV, c.aNSK, c.aNSV.size());
		initializeMaps(c.e, c.aNSV, c.aNSK, c.aNSV.size());
	}
	
	public long decode(StringBuilder sb, TreeMap<Character, Integer> decodeDict) {
		long total = 0L;
		for(int i = sb.length() - 1; i > -1; i--) {
			try {
				total += decodeDict.get(sb.charAt(i)) * (Math.pow(decodeDict.size(), ((sb.length()-1)-i)) );				
			}
			catch(Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		c.decodedValue = total;
		return total;
	}
	
	public StringBuilder encode(long a, TreeMap<Character, Integer> encodeDictV, TreeMap<Integer, Character> encodeDictK) {
		int count = 1;
		StringBuilder sb = new StringBuilder();
		while(a > 0) {
			int code = (int)(a %   (Math.pow(encodeDictV.size(), count)) / (Math.pow(encodeDictV.size(), count-1)));
			sb.append(encodeDictK.get(code));
			a -= a %   (Math.pow(encodeDictV.size(), count));
			count++;
		}
		sb = sb.reverse();
		c.encodedValue.setLength(0);
		c.encodedValue.append(sb);
		return sb;
	}
	
	public StringBuilder convert(StringBuilder sb, TreeMap<Character, Integer> decodeDict, TreeMap<Character, Integer> encodeDictV, TreeMap<Integer, Character> encodeDictK) {
		return encode(decode(sb, decodeDict), encodeDictV, encodeDictK);
	}
	
	public StringBuilder convertLarge(StringBuilder sb, TreeMap<Character, Integer> decodeDict, TreeMap<Character, Integer> encodeDictV, TreeMap<Integer, Character> encodeDictK) {
		String temp = sb.toString();
		StringBuilder tempSB = new StringBuilder();
		for(int i = 0; i < 5; i++) {
			long a = decode(new StringBuilder().append(temp.substring(i*8, (i+1)*8)), decodeDict);
			tempSB.append(encode(a, encodeDictV, encodeDictK));
		}
		return tempSB;
	}
	
	//even numbered string lengths...
	public byte [] hexToByteArray(String hex) {
		byte [] bytes = new byte[hex.length()/2];
		for(int i = 0; i < hex.length()-1; i+=2) {
			bytes[i/2] = (byte) (c.hV.get(hex.charAt(i))*16 + c.hV.get(hex.charAt(i+1)) - 128);
		}
		return bytes;
	}
	public StringBuilder byteArrayToHex(byte [] b) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < b.length; i++) {
			int temp = b[i]+128;
			char d = c.hK.get(temp % 16);
			temp -= temp % 16;
			char e = c.hK.get(temp / 16);
			sb.append(e);
			sb.append(d);
		}
		return sb;
	}
	public StringBuilder byteToHex(byte b) {
		StringBuilder sb = new StringBuilder();
		int temp = b+128;
		char d = c.hK.get(temp % 16);
		temp -= temp % 16;
		char e = c.hK.get(temp / 16);
		sb.append(e);
		sb.append(d);
		return sb;
	}
	
	public void initializeMaps(char [] d, TreeMap<Character, Integer> dV, TreeMap<Integer, Character> dK) {
		for(int i = 0; i < d.length; i++) {
			dV.put(d[i], i);
			dK.put(i, d[i]);
		}
	}
	
	public void initializeMaps(char [] d, TreeMap<Character, Integer> dV, TreeMap<Integer, Character> dK, int start) {
		for(int i = 0; i < d.length; i++) {
			dV.put(d[i], i+start);
			dK.put(i+start, d[i]);
		}
	}
	
	public void initializeCustomMap(char [] a) {
		initializeMaps(a, c.cV, c.cK, c.cV.size());
	}
	
	public char [] getUniqueChars(File f) {
		TreeSet<Character> tree = new TreeSet<Character>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			int c = br.read();
			while(c > -1) {
				tree.add((char)c);
				c = br.read();
			}
			br.close();
		} catch (Exception e) {}
		char [] c = new char[tree.size()];
		for(int i = 0; i < c.length; i++) {
			c[i] = tree.first();
			tree.remove(tree.first());
		}
		return c;
	}
	
	public void compressFile(File f) {
		char [] cc = getUniqueChars(f);
		initializeCustomMap(cc);
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("test3.txt")));
			int a = br.read();
			StringBuilder sb = new StringBuilder();
			while(a > -1) {
				sb.append((char)a);
				if(sb.length() == 8) {
					bw.write(convert(sb, c.cV, c.aNSV, c.aNSK).toString());
					sb.setLength(0);
				}
				a = br.read();
			}
			br.close();
			bw.flush();
			bw.close();
		} catch (Exception e) {}
		
	}
	
}