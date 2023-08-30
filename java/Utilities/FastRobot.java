package Utilities;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

//               _                        _   _             
//    /\        | |                      | | (_)            
//   /  \  _   _| |_ ___  _ __ ___   __ _| |_ _  ___  _ __  
//  / /\ \| | | | __/ _ \| '_ ` _ \ / _` | __| |/ _ \| '_ \ 
// / ____ \ |_| | || (_) | | | | | | (_| | |_| | (_) | | | |
///_/    \_\__,_|\__\___/|_| |_| |_|\__,_|\__|_|\___/|_| |_|
//                              
public class FastRobot {
	FastClipboard c = new FastClipboard();
	public FastRobot() {
		
	}
	public void keyPress(Robot robot, int key) {
		robot.keyPress(key);
		robot.keyRelease(key);
	}
	public void ctrlKeyPress(Robot robot, int key) {
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(key);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}
	public void rightClick(Robot robot) {
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_F10);
		robot.keyRelease(KeyEvent.VK_SHIFT);
	}
	public void paste(Robot robot, String send) {
		c.ClipPut(send);
		ctrlKeyPress(robot, KeyEvent.VK_V);
	}
	public String taskMgr() throws Exception{
	    String tasks = "";
	    Scanner in = new Scanner(Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe").getInputStream());
	    while(in.hasNextLine()){    tasks += in.nextLine() + "\n";      }
	    in.close();
	    return tasks;
	}

	public void executeBashCommand(String s) throws Exception {
	    Runtime r = Runtime.getRuntime();
	    String[] c = {"bash", "-c", s};
	    Process p = r.exec(c);
	    p.waitFor();
	    BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
	    String l = "";
	    while ((l = b.readLine()) != null) {
	        System.out.println(l);
	    }
	    b.close();
	}
	
}