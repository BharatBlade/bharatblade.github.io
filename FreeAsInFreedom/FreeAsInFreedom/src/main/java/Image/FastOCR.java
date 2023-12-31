package Image;
import java.io.File;
import javax.imageio.ImageIO;

import java.awt.image.RenderedImage;
import java.awt.image.BufferedImage;
import net.sourceforge.tess4j.Tesseract;
public class FastOCR {
	public String tessDataPath = "C:\\Users\\john\\Documents\\bharatblade.github.io\\FreeAsInFreedom\\FreeAsInFreedom\\";
	public Tesseract tesseract = new Tesseract();
	public FastImage fastImage = new FastImage();
	public FastOCR() {
		tesseract.setDatapath(tessDataPath);
	}
	
	public FastOCR(String path) {
		tesseract.setDatapath(path);
	}
	
	public String ocr() {
		try {
			ImageIO.write((RenderedImage) fastImage.readImageFromClipboard(), "jpg", new File("image2.jpg"));
			BufferedImage bufferedImage = ImageIO.read(new File("image2.jpg"));
			ImageIO.write(imageBlackWhite(bufferedImage), "jpg", new File("image3.jpg"));
			return tesseract.doOCR(new File("image2.jpg"));
		} catch (Exception e) {
			return null;
		}
	}
	
	public BufferedImage imageBlackWhite(BufferedImage bufferedImage) {
		for (int x = 0; x < bufferedImage.getWidth(); x++) {
		    for (int y = 0; y < bufferedImage.getHeight(); y++) {
		    	if(bufferedImage.getRGB(x,y) < 0 && bufferedImage.getRGB(x,y) > -7000000) {
		    		bufferedImage.setRGB(x,y,-1);
		    	}
		    }
		}
		return bufferedImage;
	}
}