package Science;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.imageio.ImageIO;

public class EKG {
	public static void main(String[]args) throws Exception {
		TreeSet<Integer> colors = new TreeSet<Integer>();
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		File f = new File("/home/mm/Pictures/EKGPURE.png");
		BufferedImage image = ImageIO.read(f);
		int w = 1503; int h = 791;
		int [][] graph = new int[w][h];
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
		        int clr = image.getRGB(i, j)/1;
		        if(clr < 0 && clr >= -5000000) {
		        	clr = -1;
		        }
		        graph[i][j] = clr;
		        if(!map.containsKey(clr)) {
		        	map.put(clr, 1);
		        }
		        else {
		        	map.put(clr, map.get(clr)+1);
		        }
			}
		}
		for(int a = 0; a < 2; a++) {
			for(int i = 0+10; i < w-10; i++) {
				for(int j = 0+10; j < h-10; j++) {
					int total = 0;
					if(graph[i+1][j] == -1) {
						total++;
					}
					if(graph[i-1][j] == -1) {
						total++;
					}
					if(graph[i][j+1] == -1) {
						total++;
					}
					if(graph[i][j-1] == -1) {
						total++;
					}
					if(graph[i+1][j+1] == -1) {
						total++;
					}
					if(graph[i+1][j-1] == -1) {
						total++;
					}
					if(graph[i-1][j+1] == -1) {
						total++;
					}
					if(graph[i-1][j-1] == -1) {
						total++;
					}
					if(graph[i][j] != -1 && total > 7) {
						graph[i][j] = -1;
						i = 10;
						j = 10;
					}
				}
			}
		}
		for(int a = 0; a < 3; a++) {
			for(int i = 35+(a*350); i < 66+(a*350); i++) {
				for(int b = 0; b < 3; b++) {
					for(int j = 100+(b*210); j < 120+(b*210); j++) {graph[i][j] = 0;}
				}
			}
		}
		
		for(int i = 0; i < 10; i++) {
			int key = Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();
			System.out.print(key + "\t\t");
			System.out.println(map.get(key));
			int red =   (key & 0x00ff0000) >> 16;
	        int green = (key & 0x0000ff00) >> 8;
	        int blue =   key & 0x000000ff;
	        System.out.print("Red Color value = " + red);
	        System.out.print("Green Color value = " + green);
	        System.out.println("Blue Color value = " + blue);
			map.remove(key);
		}
		writeImage(graph);
	}
	public static void writeImage(int [][] color) {
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
}