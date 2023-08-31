package File;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


// ______ _ _        __  __                                                   _   
//|  ____(_) |      |  \/  |                                                 | |  
//| |__   _| | ___  | \  / | __ _ _ __   __ _  __ _  ___ _ __ ___   ___ _ __ | |_ 
//|  __| | | |/ _ \ | |\/| |/ _` | '_ \ / _` |/ _` |/ _ \ '_ ` _ \ / _ \ '_ \| __|
//| |    | | |  __/ | |  | | (_| | | | | (_| | (_| |  __/ | | | | |  __/ | | | |_ 
//|_|    |_|_|\___| |_|  |_|\__,_|_| |_|\__,_|\__, |\___|_| |_| |_|\___|_| |_|\__|
//                                             __/ |                              
//                                            |___/                               
public class FileExtended {
	
	public static ArrayList<FileProps> fps = new ArrayList<FileProps>();
	public static ArrayList<FileProps> fps2 = new ArrayList<FileProps>();
	public static double counter = 0;
	
	public ArrayList<File> folder = new ArrayList<File>();
	public ArrayList<BufferedInputStream> BI = new ArrayList<BufferedInputStream>();
	public ArrayList<byte[]> BY = new ArrayList<byte[]>();

	
	public FileExtended() {
		
	}
    public File chooseFile() {
		JFileChooser chooser = new JFileChooser("");
		chooser.showOpenDialog(null);	
		return new File(chooser.getSelectedFile().getPath());
	}
    public File chooseFolder() {
    	JFileChooser chooser = new JFileChooser();
    	chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
    	chooser.showOpenDialog(null);
	    return chooser.getSelectedFile();
    }
    public void showMessage(String message, String title) {
    	JOptionPane.showMessageDialog(null,message,title,1);
    }
    
    public void DuplicateFileRemover() {
		File folder = new File("J:\\Research\\");
		int equalFuzzy = 0;
		long totalSize = 0;
		recursiveSearch(folder);
		int total = fps.size();
		System.out.println(fps.size());
		for(int i = 0; i < fps.size(); i++) {
			for(int j = 0; j < fps.size(); j++) {
				if(i != j) {
//					if(fps.get(i).equals(fps.get(j))) {
//						totalSize+=fps.get(j).size;
//						System.out.println(fps.get(j));
//						File temp = new File(fps.get(j).path);
//						temp.delete();
//						fps.remove(j);
//						j--;
//						equalFuzzy++;
//					}
					if(fps.get(i).equals(fps.get(j))) {
						System.out.println();
						System.out.println(fps.get(i).path + fps.get(i).size);
						System.out.println(fps.get(j).path + fps.get(j).size);
						totalSize+=fps.get(i).size;
						System.out.println();
						fps2.add(fps.get(i));
						fps2.add(fps.get(j));
						fps.remove(j);
						j--;
						equalFuzzy++;
					}
				}
			}
		}
		System.out.println(total + "\t" + equalFuzzy + "\t" + totalSize);
		sortArrayListFileProps(fps2);
		for(int i = 1; i < 20; i++)
			System.out.println(fps2.get(fps2.size()-i));
	}
	
	public static void recursiveSearch(File folder) {
		File [] subfolder = folder.listFiles();
		if(subfolder != null) {
			for(int i = 0; i < subfolder.length; i++) {
				if(subfolder[i].isFile()) {
					try { fps.add(new FileProps(subfolder[i])); } catch (Exception e) {}
				}
				else if(subfolder[i].isDirectory()) {
					recursiveSearch(subfolder[i]);
				}
			}
		}
		counter++;
		if(counter % 10000 == 0) {
			System.out.println(counter);
		}
	}
	
	public void sortArrayListFileProps(ArrayList<FileProps> list) { 
		list.sort((o1, o2) -> Long.compare(o1.size, o2.size)); 
    } 
    
	public void DuplicateFileRemover2() throws Exception {
		long time = System.currentTimeMillis();
		folder.addAll(Arrays.asList(new File("/run/media/mm/Easystore//jpg/").listFiles()));
		folder = (new ArraySorting()).sort(folder);
		int cot = 0;
		for(int i = 0; i < folder.size(); i++) {
			BI.add(new BufferedInputStream(new FileInputStream(folder.get(i))));
			byte [] newArray = new byte [BI.get(i).available()];
			BI.get(i).read(newArray);
			BY.add(newArray);
			Arrays.sort(BY.get(i));
			cot++;
			if(cot % 1000 == 0) {
				System.out.println(System.currentTimeMillis() - time);
			}
		}
		int count = 0;
		long size = 0;
		
		System.out.println(folder.size());
		for(int i = 0; i < folder.size(); i++) {
			for(int j = i; j < folder.size(); j++) {
//				if(folder.get(i).length() < folder.get(j).length()) {
//					break;
//				}
				try {
					if(i != j && fuzzyHelper(i,j) < 0.2) {
						count++;
						size += folder.get(j).length()/1000;
						System.out.println(count + ",\t" + folder.get(i).getName() + "," + folder.get(j).getName() + ",\t" + size+"kb");
						BI.get(j).close();
						//folder.get(j).delete();
						Files.copy(folder.get(i).toPath(), (new File("/run/media/mm/Easystore/Personal/test/" + count + "a.jpg")).toPath(), StandardCopyOption.REPLACE_EXISTING);
						Files.copy(folder.get(j).toPath(), (new File("/run/media/mm/Easystore/Personal/test/" + count + "b.jpg")).toPath(), StandardCopyOption.REPLACE_EXISTING);
						BI.remove(j); folder.remove(j);
						j--;
					}
				} catch(Exception e) {}
			}
			System.out.println(System.currentTimeMillis() - time);
		}
	}
	
	public double fuzzyHelper(int i, int j) throws Exception {
		Arrays.sort(BY.get(i));Arrays.sort(BY.get(j));
		ArrayList<Byte> aT = new ArrayList<Byte>();
		ArrayList<Byte> bT = new ArrayList<Byte>();
		for(int k = 0; k < BY.get(i).length; k++) {
			aT.add(BY.get(i)[k]);
		}
		for(int k = 0; k < BY.get(j).length; k++) {
			bT.add(BY.get(j)[k]);
		}
		double size = (double)(aT.size() + bT.size());
		for(int k = 0; k < aT.size(); k++) {
			if(bT.contains(aT.get(k))) {
				bT.remove(aT.get(k));
				aT.remove(aT.get(k));
				k--;
			}
		}
		
		double diff = (double)(aT.size() + bT.size())/size ;
		return diff;
	}

	
}
