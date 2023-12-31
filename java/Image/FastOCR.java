package Image;
import java.io.File;
import javax.imageio.ImageIO;

import Utilities.FastClipboard;

import java.awt.geom.AffineTransform;
import java.awt.image.*;
import net.sourceforge.tess4j.Tesseract;
public class FastOCR {
	String tessDataPath = "C:\\Users\\johnj\\PortableApps\\EclipsePortable\\Data\\FreeAsInFreedom\\FreeAsInFreedom\\";
	FastClipboard c = new FastClipboard();
	
	public FastOCR() {

	}
	
	public void ocrNBME() {
		String text = ocr();
		text = text.replace("-\n", "");
		text = text.replace("\n", " ");
		text = text.replace("  A)", "\nA)");
		text = text.replace("  B)", "\nB)");
		text = text.replace("  C)", "\nC)");
		text = text.replace("  D)", "\nD)");
		text = text.replace("  E)", "\nE)");
		text = text.replace("  F)", "\nF)");
		text = text.replace("  G)", "\nG)");
		text = text.replace("  H)", "\nH)");
		text = text.replace("  I)", "\nI)");
		text = text.replace("  J)", "\nJ)");
		text = text.replace("  0)", "\nC)");
		//text = text.replace(" ", "-");
		//text = text.replace(" ", "-");
		text = text.replace("- ", "-");
		text = text.replace(" -", "-");
		text = text.replace("  Incorrect Answer", "<br><br>Incorrect Answer");
		text = text.replace(" Incorrect Answer", "<br><br>Incorrect Answer");
		text = text.replace("  lncorrectAnswer", "<br><br>Incorrect Answer");
		text = text.replace(" lncorrectAnswer", "<br><br>Incorrect Answer");
		text = text.replace("lncorrectAnswer", "<br><br>Incorrect Answer");
		text = text.replace(" IncorrectAnswer", "<br><br>Incorrect Answer");
		text = text.replace("IncorrectAnswer", "<br><br>Incorrect Answer");
		text = text.replace("Educational Objective", "<br><br>Educational Objective");
		text = text.replace("  Educational Objective", "<br><br>Educational Objective");
		text = text.replace(" Educational Objective", "<br><br>Educational Objective");
		System.out.println(text);
		c.ClipPut(text);
	}
	
	@SuppressWarnings("deprecation")
	public String ocr() {
		Tesseract tesseract = new Tesseract();
		tesseract.setTessVariable("user_defined_dpi", "96");
		try {
			tesseract.setDatapath(tessDataPath); //"..\\Tess4J-3.4.8-src\\Tess4J\\tessdata"
			ImageIO.write((RenderedImage) c.getImageFromClipboard(), "jpg", new File("image2.jpg"));
			BufferedImage im = ImageIO.read(new File("image2.jpg"));
			for (int x = 0; x < im.getWidth(); x++) {
			    for (int y = 0; y < im.getHeight(); y++) {
			    	if(im.getRGB(x,y) < 0 && im.getRGB(x,y) > -7000000) {
			    		im.setRGB(x,y,-1);
			    	}
			    }
			}
		    FastImage fi = new FastImage();
		    //im = fi.rotateImage(im);
			ImageIO.write(im, "jpg", new File("image3.jpg"));
			System.out.println("STEP 4");
			return tesseract.doOCR(new File("image3.jpg"));
		} catch (Exception e) {return null;}
	}
}
