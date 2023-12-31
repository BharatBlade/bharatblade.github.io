package Image;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;

public class FastImage {
	
	public int [] a = {256, 0, 0};
	public int [] b = {1,0,2,1,0,2};
	public int step = 0;
	public int count = 0;
	
	public FastImage() {
		
	}
	
	public int [][] readImage(File f, int w, int h) throws Exception {
		BufferedImage image = ImageIO.read(f);
		int [][] graph = new int[w][h];
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
		        int clr = image.getRGB(i, j)/1;
		        if(clr < 0 && clr >= -5000000) {
		        	clr = -1;
		        }
		        graph[i][j] = clr;
			}
		}
		return graph;
	}
	
	public void writeImage(int [][] color) {
	    String path = "PNGLevel3.png";
	    BufferedImage image = new BufferedImage(color.length, color[0].length, BufferedImage.TYPE_INT_RGB);
	    for (int x = 0; x < color.length; x++) {
	        for (int y = 0; y < color[0].length; y++) {
	            image.setRGB(x, y, color[x][y]);
	        }
	    }

	    File ImageFile = new File(path);
	    try {
	        ImageIO.write(image, "png", ImageFile);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public int compressPixelColor(int a) {
	    int compressionLevel = 5; //less is more
	    int [] arr = new int[compressionLevel];
	    for(int i = 0; i < arr.length; i++) {		arr[i] = (255/arr.length)*(i+1);		}
	    int b = 999;		int c = a;
	    for(int i = 0; i < arr.length; i++) {	
	    	int d = Math.abs(arr[i] - a);
	    	if(d < b) {	b = d;	c = arr[i];		}
	    }
	    return c;
	}
	
	public void colorSplit(int div) {
		int s = 1;
		colorSplitHelper(div);
		step++;
		for(int i =0; i < 6; i++) {
			for(int j = 0; j < div; j++) {
				a[b[i]] += s*(256/div);
				colorSplitHelper(div);
				step++;
			}
			s *= -1;
		}
	}
	
	public void colorSplitHelper(int div) {
		if(step == 0) {
			System.out.print("from");
		}
		else if(step == div*6) {
			System.out.print("to");
		}
		else {
			System.out.print((int)(step*(100/(div*6))) + "%");
		}

		int [] e = {a[0], a[1], a[2]};
		for(int i = 0; i< e.length; i++) {
			if(e[i] == 256) {
				e[i] = 255;
			}
		}
		Color your_color = new Color(e[0],e[1],e[2]);

		String hex = "#"+Integer.toHexString(your_color.getRGB()).substring(2);
		System.out.println(" {color: " + hex.toUpperCase() + "}");
	}
	
	public void ResolutionFilter(File dirPath, String writePath, String ext) throws Exception {
		File filesList[] = dirPath.listFiles();
		for(File file : filesList) {
			if(file.isFile()) {
				Dimension d = getImageDimensions(file);
				double ar = Math.round((double)d.height/d.width*10.0)/10.0;
				if(d.width > 599 && d.height > 959 && ar > 1.1 && ar < 2.1) {
					Files.copy(file.toPath(), (new File(writePath + count + "." + ext)).toPath(), StandardCopyOption.REPLACE_EXISTING); count++;
					if(count % 1000 == 0) {
						System.out.print(count + "\t");
					}
				}
			} else { ResolutionFilter(file, writePath, ext); }
		}
	}
	
	public Dimension getImageDimensions(File imgFile) throws Exception {
		int pos = imgFile.getName().lastIndexOf(".");
		if (pos == -1)
		throw new Exception("No extension for file: " + imgFile.getAbsolutePath());
		String suffix = imgFile.getName().substring(pos + 1);
		Iterator<ImageReader> iter = ImageIO.getImageReadersBySuffix(suffix);
		while(iter.hasNext()) {
			ImageReader reader = iter.next();
			try {
				ImageInputStream stream = new FileImageInputStream(imgFile); reader.setInput(stream);
				int width = reader.getWidth(reader.getMinIndex()); int height = reader.getHeight(reader.getMinIndex());
				return new Dimension(width, height);
			} catch (Exception e) {}	finally {reader.dispose();}
		}
		return new Dimension (0,0);
	}
	
	public BufferedImage rotateImage(BufferedImage im) {
		int w = im.getWidth();
	    int h = im.getHeight();
		BufferedImage dest = new BufferedImage(h, w, im.getType());
	    for (int y = 0; y < h; y++) 
	        for (int x = 0; x < w; x++) 
	            dest.setRGB(y, w - x - 1, im.getRGB(x, y));
	    return dest;
	}
}
