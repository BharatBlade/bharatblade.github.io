package Utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FastDate {
	
	public String pattern = "yyyy/MM/dd_HH:mm:ss.SSS";
	public DateFormat df = new SimpleDateFormat(pattern);
	Date today = Calendar.getInstance().getTime();
	
	public FastDate() {
		
	}
	
	public String getDate() {
		return df.format(today);
	}
	
}
