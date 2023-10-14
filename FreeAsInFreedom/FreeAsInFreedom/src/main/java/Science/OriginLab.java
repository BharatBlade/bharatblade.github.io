package Science;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class OriginLab {
	public static void main(String[]args) throws IOException, InterruptedException, AWTException{
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

		Robot robot = new Robot();
		Runtime runtime = Runtime.getRuntime();
		Process origin = runtime.exec("C:\\Program Files\\OriginLab\\Origin2017\\Origin94_64.exe");
		while(true){
			boolean escape = false;
			Scanner in = new Scanner(Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe").getInputStream());
			while(in.hasNextLine()){	
				String line = in.nextLine();
				if(line.toLowerCase().contains("origin")){
					Scanner sc = new Scanner(line);
					sc.next();sc.next();sc.next();sc.next();
					String m = sc.next();
					int memory = Integer.parseInt(m.substring(0, m.indexOf(",")) + m.substring(m.indexOf(",")+1));
					if(memory > 100000)
						escape = true;
					break;
				}
			}
			if(escape)
				break;			
		}
		
		System.out.println("HELLO");
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_K);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	    StringSelection selection = new StringSelection("C:\\Users\\John\\Desktop\\test2.csv");
	    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	    clipboard.setContents(selection, selection);
	    Thread.sleep(2000);
	    robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
	    robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(2000);
	    robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_F10);
		robot.keyRelease(KeyEvent.VK_SHIFT);
	    robot.keyPress(KeyEvent.VK_DOWN);
	    Thread.sleep(500);
	    robot.keyPress(KeyEvent.VK_RIGHT);
	    Thread.sleep(500);
	    robot.keyPress(KeyEvent.VK_RIGHT);
	    Thread.sleep(500);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	    Thread.sleep(1000);
	    robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_F6);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(2000);
	    robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_G);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(2000);
		selection = new StringSelection(String.valueOf(row));
	    clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	    clipboard.setContents(selection, selection);
	    robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(500);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(500);
	    robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(500);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_N);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(500);
		robot.keyPress(KeyEvent.VK_DOWN);
		Thread.sleep(500);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
	    robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_G);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_1);
		Thread.sleep(500);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(500);
	    robot.keyPress(KeyEvent.VK_RIGHT);
		Thread.sleep(2000);
	    robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_F10);
		robot.keyRelease(KeyEvent.VK_SHIFT);
		for(int i = 0; i < 4; i++){
			Thread.sleep(500);
			robot.keyPress(KeyEvent.VK_DOWN);
		}
		Thread.sleep(500);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_F10);
		robot.keyRelease(KeyEvent.VK_SHIFT);
		for(int i = 0; i < 7; i++){
			Thread.sleep(500);
			robot.keyPress(KeyEvent.VK_DOWN);
		}
		Thread.sleep(500);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(500);
		robot.keyPress(KeyEvent.VK_LEFT);
		robot.keyPress(KeyEvent.VK_UP);
		selection = new StringSelection("(i-1)*0.18");
	    clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	    clipboard.setContents(selection, selection);
	    Thread.sleep(1000);
	    robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	    Thread.sleep(500);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(500);
	    robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(500);
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_F10);
		robot.keyRelease(KeyEvent.VK_SHIFT);
		Thread.sleep(500);
		robot.keyPress(KeyEvent.VK_DOWN);
		Thread.sleep(500);
		robot.keyPress(KeyEvent.VK_RIGHT);
		Thread.sleep(500);
		robot.keyPress(KeyEvent.VK_DOWN);
		Thread.sleep(500);
		robot.keyPress(KeyEvent.VK_RIGHT);
	    Thread.sleep(500);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
}