import java.io.File;

public class Temp {
	
	public Temp() {
		
	}
	
	public void recursiveSearch(File folder) throws Exception {
		File[] subfolder = folder.listFiles();
		if (subfolder != null) {
			for (int i = 0; i < subfolder.length; i++) {
				if (subfolder[i].isDirectory()) {
					recursiveSearch(subfolder[i]);
				}
				else {
					
				}
			}
		}

	}

}
