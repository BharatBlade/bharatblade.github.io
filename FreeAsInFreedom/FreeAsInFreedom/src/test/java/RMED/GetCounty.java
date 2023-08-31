package RMED;

import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
public class GetCounty {
	public static void main(String[]args) throws Exception {
		RMEDDatabase db = new RMEDDatabase();
		InputStream inp = new FileInputStream(new File("G:\\H\\RMED_Research\\RMED Graduation Survey\\CONFIDENTIAL_RMED 2016_2022 UPDATED.xlsx"));
		Workbook wb = WorkbookFactory.create(inp);
		Sheet sheet = wb.getSheetAt(0);
		int numCol = 0;
		ArrayList<String> header = new ArrayList<String>();
		Row row = sheet.getRow(0);
		for(numCol = 0; numCol < 26; numCol++) { //49 fields for RMED tab
			Cell cell = row.getCell(numCol);
			header.add(cell.toString());
		}
		Cell cell = row.getCell(numCol);
		header.add(cell.toString());
		System.out.println(header);

		for(int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			String year = row.getCell(1).toString().trim().replace("\n", "");
			String firstName = row.getCell(2).toString().trim().replace("\n", "");
			String lastName = row.getCell(3).toString().trim().replace("\n", "");
			String specialtyMatch = row.getCell(16).toString().replace("\n", "");
			db.addDoctor2(db.NPPES(firstName, lastName, specialtyMatch, year));
			System.out.println(firstName + "\t" + lastName + "\t" + specialtyMatch);			
			Thread.sleep(500);
		}
		wb.close();
		
	}
	
//	public static int NPPES(String firstName, String lastName, String specialtyMatch) throws Exception {
//		String apiLink = "https://npiregistry.cms.hhs.gov/api/?";
//		String limit = "200";
//		
//		URL url = new URL(apiLink 	+ "first_name=" + firstName 
//									+ "&last_name=" + lastName 
//									+ "&limit=" + limit 
//									+ "&version=2.1");
//		
//		InputStream inputStream = url.openStream();
//		Scanner scanner = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
//		String out = scanner.next();
//		scanner.close();
//		String [] matches = out.split("\"created_epoch\"");
//		ArrayList<String> filtered = new ArrayList<String>();
//		for(int i = 1; i < matches.length; i++) {
//			if(matches[i].contains("\"desc\":")) {
//				if(isSpecialty(specialtyMatch, matches[i])) {
//					filtered.add(matches[i]);
//				}
//			}
//		}
//		if(filtered.size() == 0) {
//			for(int i = 1; i < matches.length; i++) {
//				if(matches[i].contains("\"credential\":")) {
//					if(isMD(matches[i])) {
//						filtered.add(matches[i]);
//					}
//				}
//			}
//		}
//		if(filtered.size() == 0 && matches.length == 2) {
//			filtered.add(matches[1]);
//		}
//		if(matches.length == 1) {
//			System.out.println("-------------------------------------------");
//			System.out.println(firstName + "\t" + lastName + "\t" + specialtyMatch + "\t" + filtered.size());			
////			System.out.println(filtered.size() + "\t" + matches.length);
//			System.out.println("-------------------------------------------");
//		}
//		return filtered.size();
//	}
//	
//	public static boolean isMD(String match) {
//		String md = match.substring(match.indexOf("\"credential\":"));
//		md = md.substring(14, md.indexOf(",\"")-1);
//		String lowerCasemd = md.toLowerCase();
//		if(lowerCasemd.contains("m") && lowerCasemd.contains("d")) {
//			boolean isMD = true;
//			int mPos = -1;
//			int dPos = -1;
//			
//			for(int j = 0; j < lowerCasemd.length(); j++) {
//				if(lowerCasemd.charAt(j) == 'm') {
//					if(mPos == -1) 	{ mPos = j; }
//					else 			{ isMD = false; }
//				}
//				else if(lowerCasemd.charAt(j) == 'd') {
//					if(dPos == -1) 	{ dPos = j; }
//					else 			{ isMD = false; }
//				}
//			}
//			
//			if(mPos == -1 || dPos == -1) { isMD = false; }
//			
//			if(dPos - mPos > 2 || dPos - mPos < 0) { isMD = false; }
//			
//			if( (md.charAt(mPos) == 'm' && md.charAt(dPos) == 'd')
//				||
//				(md.charAt(mPos) == 'M' && md.charAt(dPos) == 'D') ) {
//				
//			}
//			else { isMD = false; }
//			
//			return isMD;
//		}
//		return false;
//	}
//	
//	public static boolean isSpecialty(String specialtyMatch, String match) {
//		String sm = match.substring(match.indexOf("\"desc\":"));
//		try {
//			sm = sm.substring(8, sm.indexOf(",\"")-1);
//		}
//		catch(Exception e) {
//			System.out.println(sm);
//			System.exit(0);
//		}
//		ArrayList<String> specialtyMatchSplit = new ArrayList<String>(Arrays.asList(specialtyMatch.toLowerCase().split(" ")));
//		String [] smSplit = sm.toLowerCase().split(" ");
//		boolean smCheck = true;
//		for(int j = 0; j < smSplit.length; j++) {
//			if(smSplit[j].length() > 3) {
//				if(!specialtyMatchSplit.contains(smSplit[j])) {
//					smCheck = false;
//				}
//			}
//		}
//		if(!smCheck) {
//			String test = "Student in an Organized Health Care Education/Training Program";
//			specialtyMatchSplit = new ArrayList<String>(Arrays.asList(test.toLowerCase().split(" ")));
//			smSplit = sm.toLowerCase().split(" ");
//			smCheck = true;
//			for(int j = 0; j < smSplit.length; j++) {
//				if(smSplit[j].length() > 3) {
//					if(!specialtyMatchSplit.contains(smSplit[j])) {
//						smCheck = false;
//					}
//				}
//			}
//		}
//		
//		return smCheck;
//	}
	
}
