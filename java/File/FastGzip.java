package File;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.zip.GZIPInputStream;

public class FastGzip {
	public FileInputStream fis;
	public FastReader reader;
	public FastGzip() {
		
	}
	
	public FastGzip(String archivePath) {
		try {
			fis = new FileInputStream(archivePath);
			reader = new FastReader(fis);
		} catch (FileNotFoundException e) {

		}
	}
	
	public void close() {
		try { reader.close(); fis.close(); } catch (IOException e) {}
	}
	
	public File decompressGzipToFile(String destination) throws IOException {
        GZIPInputStream gis = new GZIPInputStream(fis);
        FileOutputStream fos = new FileOutputStream(new File(destination));
        byte[] buffer = new byte[1024*1024];
        int len;
        while ((len = gis.read(buffer)) > 0) {
            fos.write(buffer, 0, len);
        }
        fos.flush();
        fos.close();
        gis.close();
        return new File(destination);
    }
	public RandomAccessFile decompressGzipToRAM(String source) throws Exception {
        GZIPInputStream gis = new GZIPInputStream(new FileInputStream(new File(source)));
        RandomAccessFile raf = new RandomAccessFile("DIAGNOSIS_0_0_0.csv", "rw");
        byte[] buffer = new byte[1024*1024];
        int len;
        while ((len = gis.read(buffer)) > 0) {
        	raf.write(buffer, 0, len);
        }
        raf.seek(0);
        gis.close();
        return raf;        
    }
	public String nextLine() {
		try {
			return reader.nextLine();
		} catch (Exception e) {
			return null;
		}
	}
	
	public String[] nextLineToArray(String delim, boolean removeFirstAndLastCh) {
		try {
			return reader.nextLineToArray(delim, removeFirstAndLastCh);
		} catch (Exception e) {
			return null;
		}
	}

	
	public void closeRAF(RandomAccessFile raf) {
		try {
			raf.setLength(0);
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
