import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class CSVStripper {
	
	public static JFrame f= new JFrame("CSVStripper");
	public static JButton submit = new JButton("Submit");
	public static String line = "";
	public static long cc1, cc2, cc3, cc4 = 0;
	public static int rows = 0;
	public static String delim = "";
	
	public static void main(String[]args) throws Exception {
		JFileChooser chooser = new JFileChooser("");
		chooser.showOpenDialog(null);
		FileReader fr = new FileReader(new File(chooser.getSelectedFile().getPath()));
		BufferedReader br = new BufferedReader(fr);
		FileWriter fw = new FileWriter(new File("output.csv"));BufferedWriter bw = new BufferedWriter(fw);
		String headers = br.readLine();bw.write(headers + "\n");
		if(headers.contains("\",\"")) {
			headers = headers.substring(1, headers.length()-1);
			delim = "\",\"";
		}
		line = br.readLine();
		if(line.contains("\",\"")) {
			line = line.substring(1, line.length()-1);
			delim = "\",\"";
		}
		else if(line.contains(";")) {delim = ";";}
		else if(line.contains(",")) {delim = ",";}
		String [] s = headers.split(delim);String [] fl = line.split(delim);
		JLabel [] l = new JLabel[s.length];JTextField [] tf = new JTextField[s.length];
		ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
		f.setSize(1000,800);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Font font = new Font("Serif", Font.PLAIN, 18);
	    for(int i = 0; i < s.length; i++) {
		    l[i]=new JLabel(s[i] + " (ex. " + fl[i] + ")");
		    l[i].setBounds(20,20+((int)f.getSize().getHeight()/(s.length+2))*i, 500,40);
		    l[i].setFont(font); l[i].setHorizontalAlignment(SwingConstants.RIGHT);
		    tf[i]=new JTextField("");
		    tf[i].setBounds(550,20+((int)f.getSize().getHeight()/(s.length+2))*i, 400,40);
		    tf[i].setFont(font);
		    f.add(l[i]);f.add(tf[i]);
	    }
	    submit.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
				ArrayList<Integer> pos = new ArrayList<Integer>();
				for(int i = 0; i < tf.length; i++) {
					tf[i].setText(tf[i].getText().replace(" ", ""));
					arr.add(new ArrayList<String>(Arrays.asList(tf[i].getText().split(","))));
				}
				for(int i = 0; i < arr.size(); i++) {if(!arr.get(i).isEmpty()) {pos.add(i);}}
				for(int i = 0; i < arr.size(); i++) {System.out.print(arr.get(i) + "\t");}
				System.out.println();System.out.print(arr.get(3) + "\t");System.out.println(arr.get(3).get(0));
				f.dispose();
				long time = System.currentTimeMillis(); int count2 = 0;
				while(line != null) {
					int ccc = arr.size(); long time2 = System.currentTimeMillis();
					String [] t = new String[ccc];
					String z = line;
					for(int i = 0; i < t.length-1; i++) {
						t[i] = z.substring(0, z.indexOf(delim));
						z = z.substring(z.indexOf(delim)+delim.length());
					}
					t[t.length-1] = z;
					boolean print = false;
					cc1 += System.currentTimeMillis() - time2; time2 = System.currentTimeMillis();
					for(int i = 0; i < pos.size(); i++) {
						for(int j = 0; j < arr.get(pos.get(i)).size(); j++) {
							String testing = arr.get(pos.get(i)).get(j);String argg = t[pos.get(i)];
							if(!testing.isEmpty() && testing.charAt(testing.length()-1) == '*') {
								int mark = testing.indexOf('*');
								if(mark <= argg.length() && testing.substring(0, mark).equals(argg.substring(0, mark))) {
									print = true;break;
								}
							}
							else if(!testing.isEmpty() && testing.charAt(0) == '*') {
								if(testing.substring(1).equals(argg.substring(argg.length() - testing.length() + 1))) {
									print = true;break;
								}
							}
							else if(!testing.isEmpty() && testing.contains("*") && testing.length() == argg.length()) {
								int mark = testing.indexOf("*");
								if(testing.substring(0, mark).equals(argg.substring(0, mark)) && testing.substring(mark+1).equals(argg.substring(mark+1))) {
									print = true;break;
								}
							}
							else if(!testing.isEmpty() && testing.contains("-")) {
								String upper = testing.substring(0, testing.indexOf("-"));
								String lower = testing.substring(testing.indexOf("-")+1);
								int ssize = 0;
								int arggL = argg.length();
								int upperL = upper.length();
								int lowerL = lower.length();
								if(arggL <= upperL && arggL <= lowerL) {ssize = arggL;}
								else if(upperL <= arggL && upperL <= lowerL) {ssize = upperL;}
								else if(lowerL <= arggL && lowerL <= upperL) {ssize = lowerL;}
								char [][] ch = new char[3][ssize];
								for(int k = 0; k < ssize; k++) {
									ch[0][k] = argg.charAt(k); ch[1][k] = upper.charAt(k); ch[2][k] = lower.charAt(k);
									boolean current01 = ch[0][k] == ch[1][k]; boolean current02 = ch[0][k] == ch[2][k];
									boolean previous01 = false; boolean previous02 = false;
									if(k > 0) {
										previous01 = ch[0][k-1] == ch[1][k-1];
										previous02 = ch[0][k-1] == ch[2][k-1];
									}
									if(ch[0][k] > ch[1][k] && ch[0][k] < ch[2][k]) {print = true;break;}
									else if(current01 || current02) {if(k == ssize - 1) {print = true;break;}}
									else if(k > 0 && k < ssize && ((previous01 && !previous02 && ch[0][k] >= ch[1][k]) || (previous02 && !previous01 && ch[0][k] <= ch[2][k]))  ) {
										print = true;
										break;
									}
									else {print = false;break;}
								}
								if(print) {break;}
							}
							else {if(!testing.isEmpty() && testing.equals(argg)) {print = true; break;}}
						}
					}
					cc2 += System.currentTimeMillis() - time2; time2 = System.currentTimeMillis();
					if(print) {
						if(delim.equals("\",\"")) {line = "\"" + line + "\"";}
						try {bw.write(line+"\n"); bw.flush();}
						catch (Exception e1) {}
					}
					count2++;
					if(count2 == 10000000) {
						count2 = 0; rows++;
						System.out.println(System.currentTimeMillis() - time + "\t" + cc1 + "\t" + cc2 + "\t" + cc3 + "\t" + cc4 + "\t" + rows);
						time = System.currentTimeMillis(); cc1 = 0;cc2 = 0;cc3 = 0; cc4 = 0;
		    		}
					cc3 += System.currentTimeMillis() - time2; time2 = System.currentTimeMillis();
		    		try {line = br.readLine();}catch (Exception e1) {}
		    		if(line != null && delim.equals("\",\"")) {line = line.substring(1, line.length()-1);}
		    		cc4 += System.currentTimeMillis() - time2;
		    	}
				try {bw.flush();fw.flush();bw.close();fw.close();br.close();fr.close();}
				catch (IOException e1) {}
				f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
				JOptionPane.showMessageDialog(null, "All Done!", "All Done!", JOptionPane.INFORMATION_MESSAGE);
	    	}
	    } );
	    submit.setBounds((int)f.getSize().getWidth()-150, (int)f.getSize().getHeight()-100, 100, 40);
	    f.add(submit);f.setLayout(null);f.setVisible(true);
	    
	}
}