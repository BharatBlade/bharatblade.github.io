package Web;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FastWeb {
	public FastWeb() {
		
	}
	public String removeHTMLTags(String s){
	    return s.replaceAll("\\<[^>]*>","");
	}

    public String readHTML(String site) {
		String s = null;
		try {
			URL url = new URL(site);
			try {
				Scanner sc = new Scanner(url.openStream(), "UTF-8");
			    s = sc.useDelimiter("\\A").next();
                sc.close();
			}
			catch (IOException e) {e.printStackTrace();}
		}
		catch (MalformedURLException e) {e.printStackTrace();}
	    return s;
	}
    
    public static void sendKeys(WebDriver driver, String tagName, String field, String value, String keys) throws InterruptedException{
		try{	
			driver.findElement(By.xpath("//" + tagName + "[@" + field + "='" + value + "']")).sendKeys(keys);
		}
		catch(Exception e){
			while(true){
				Thread.sleep(5000);
				driver.findElement(By.xpath("//" + tagName + "[@" + field + "='" + value + "']")).sendKeys(keys);
				Toolkit.getDefaultToolkit().beep();
			}
		}
	}
	public static void sendClick(WebDriver driver, String tagName, String field, String value) throws InterruptedException{
		try{
			driver.findElement(By.xpath("//" + tagName + "[@" + field + "='" + value + "']")).click();
		}
		catch(Exception e){
			while(true){
				Thread.sleep(5000);
				driver.findElement(By.xpath("//" + tagName + "[@" + field + "='" + value + "']")).click();
				Toolkit.getDefaultToolkit().beep();
			}
		}
	}
	public static WebElement getElement(WebDriver driver, String tagName, String field, String value) throws InterruptedException{
		try{
			return driver.findElement(By.xpath("//" + tagName + "[@" + field + "='" + value + "']"));
		}
		catch(Exception e){
			while(true){
				Thread.sleep(5000);
				driver.findElement(By.xpath("//" + tagName + "[@" + field + "='" + value + "']"));
				Toolkit.getDefaultToolkit().beep();
			}
		}
	}
	public static WebElement getSubElement(WebElement driver, String tagName, String field, String value) throws InterruptedException{
		try{
			return driver.findElement(By.xpath("//" + tagName + "[@" + field + "='" + value + "']"));
		}
		catch(Exception e){
			while(true){
				Thread.sleep(5000);
				driver.findElement(By.xpath("//" + tagName + "[@" + field + "='" + value + "']"));
				Toolkit.getDefaultToolkit().beep();
			}
		}
	}
	public static String getText(WebDriver driver, String tagName, String field, String value) throws InterruptedException{
		try{
			return driver.findElement(By.xpath("//" + tagName + "[@" + field + "='" + value + "']")).getText();
		}
		catch(Exception e){
			while(true){
				Thread.sleep(5000);
				driver.findElement(By.xpath("//" + tagName + "[@" + field + "='" + value + "']")).getText();
				Toolkit.getDefaultToolkit().beep();
			}
		}
	}
	public static String getHTML(WebDriver driver, String tagName, String field, String value) throws InterruptedException{
		try{
			return driver.findElement(By.xpath("//" + tagName + "[@" + field + "='" + value + "']")).getAttribute("outerHTML");
		}
		catch(Exception e){
			while(true){
				Thread.sleep(5000);
				driver.findElement(By.xpath("//" + tagName + "[@" + field + "='" + value + "']")).getAttribute("outerHTML");
				Toolkit.getDefaultToolkit().beep();
			}
		}
	}
}
