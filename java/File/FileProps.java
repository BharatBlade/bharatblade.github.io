package File;
import java.awt.Dimension;
import java.io.File;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;

public class FileProps {
	String path = "";
	String name = "";
	long size = 0;
	String dim = "";

	public FileProps(File f) throws Exception {
		path = f.getAbsolutePath();
		name = f.getName();
		size = f.length();
		dim = getImageDimension(f);
	}
	
	public int compare( File a, File b ) {
        long aSize = a.length();
        long bSize = b.length();
        if ( aSize < bSize ) {
            return -1;
        }
        else if (aSize > bSize) {
            return 1;
        }
        else {
            return 0;
        }
    }
	
	public boolean equals(FileProps fp) {
		if (name.equals(fp.name) && size == fp.size) {
			return true;
		}
		return false;
	}

	public boolean equalsFuzzy(FileProps fp) {
		if (size == (fp.size)) {
			return true;
		}
		return false;
	}

	public String toString() {
		return path + "\t" + name + "\t" + size;
	}

	public String getImageDimension(File imgFile) throws Exception {
		int pos = imgFile.getName().lastIndexOf(".");
		if (pos == -1) {
			System.out.println("No extension for file: " + imgFile.getAbsolutePath());
			return String.valueOf(Math.random() * 1000000000);
		}
		String suffix = imgFile.getName().substring(pos + 1);
		Iterator<ImageReader> iter = ImageIO.getImageReadersBySuffix(suffix);
		while (iter.hasNext()) {
			ImageReader reader = iter.next();
			try {
				ImageInputStream stream = new FileImageInputStream(imgFile);
				reader.setInput(stream);
				int width = reader.getWidth(reader.getMinIndex());
				int height = reader.getHeight(reader.getMinIndex());
				return new Dimension(width, height).toString();
			} catch (Exception e) {

			} finally {
				reader.dispose();
			}
		}
		return String.valueOf(Math.random() * 1000000000);
	}
}
