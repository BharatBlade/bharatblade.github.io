import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import org.sikuli.script.FindFailed;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Screen;

public class OriginLabSikuli {
	public static void main(String[]args) throws IOException, FindFailed, InterruptedException{
		int row = 1;
		int count = 1;
		ArrayList<String> lines = new ArrayList<String>();
		File csv = new File("C:\\Users\\John\\Desktop\\test.csv");
		Scanner file = new Scanner(csv);
		
		String Samples = file.nextLine();
		Samples = Samples.substring(0, Samples.indexOf(","));		
		lines.add(Samples);
		
		String WaveTitle = file.nextLine();
		WaveTitle = WaveTitle.substring(0,  WaveTitle.indexOf(",")+1) + WaveTitle.replaceAll("Wavelength \\(nm\\),","");
		lines.add(WaveTitle);
		
		while(file.hasNextLine()){
			String Wavelength = file.nextLine();
			if(Wavelength.substring(0, Wavelength.indexOf(",")).equals("330")){
				row = count;
			}
			Wavelength = Wavelength.substring(0, Wavelength.indexOf(",")+1) + Wavelength.replaceAll(Wavelength.substring(0, Wavelength.indexOf(",")+1), "");
			lines.add(Wavelength);
			count++;
		}
		
		Files.write(Paths.get("C:\\Users\\John\\Desktop\\test2.csv"), lines, StandardCharsets.UTF_8);
		
		Runtime runtime = Runtime.getRuntime();
		runtime.exec("C:\\Program Files\\OriginLab\\Origin2017\\Origin94_64.exe");
		
		ImageClick("imgs\\1.png");
		ImageSend("imgs\\2.png", "C:\\Users\\John\\Desktop\\test2.csv");
		ImageClick("imgs\\3.png");
		ImageClick("imgs\\4.png");
		ImageClick("imgs\\5.png");
		ImageCtrlSend("imgs\\6.png", "g");
		ImageSend("imgs\\7.png", String.valueOf(row));
		ImageClick("imgs\\8.png");
		ImageCtrlSend("imgs\\6.png", "c");
		ImageCtrlSend("imgs\\6.png", "n");
		ImageClick("imgs\\9.png");
		ImageClick("imgs\\10.png");
		ImageCtrlSend("imgs\\11.png", "g");
		ImageSend("imgs\\7.png", "1");
		ImageClick("imgs\\8.png");
	}
	
	public static void ImageClick(String path) throws FindFailed, InterruptedException{
		new Screen().wait(path, 60000);
		Thread.sleep(3000);
		new Screen().click(path);
	}
	public static void ImageSend(String path, String data) throws FindFailed, InterruptedException{
		new Screen().wait(path, 60000);
		Thread.sleep(3000);
		new Screen().type(path, data);
	}
	public static void ImageCtrlSend(String path, String data) throws FindFailed, InterruptedException{
		new Screen().wait(path, 60000);
		Thread.sleep(3000);
		new Screen().type(path, data, KeyModifier.CTRL);
	}
}
