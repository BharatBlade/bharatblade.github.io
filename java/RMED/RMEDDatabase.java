package RMED;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class RMEDDatabase {
	public ArrayList<RMEDDoctor> doctors = new ArrayList<RMEDDoctor>();
	
	public RMEDDatabase() {
		
	}
	
	public void addFile(String file, int numColEnd, int startSheet, int endSheet) throws Exception{
		InputStream inp = new FileInputStream(file);
		Workbook wb = WorkbookFactory.create(inp);
		for(int qq = startSheet; qq <= endSheet; qq++) {
			Sheet sheet = wb.getSheetAt(qq);
			int numCol = 0;
			ArrayList<String> header = new ArrayList<String>();
			Row row = sheet.getRow(0);
			for(numCol = 0; numCol < numColEnd; numCol++) { //49 fields for RMED tab
				Cell cell = row.getCell(numCol);
				header.add(cellValue(cell));
			}
			Cell cell = row.getCell(numCol);
			header.add(cellValue(cell));
			for(int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				RMEDDoctor doctor = new RMEDDoctor();
				for(int j = 0; j < numCol; j++) {
					cell = row.getCell(j);
					doctor.addProperty(header.get(j), cellValue(cell));
				}
				addDoctor(doctor);
			}
			System.out.println(doctors.size());
		}
		wb.close();
	}
	
	public void addCSV(String file) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(new File(file)));
		String [] header = br.readLine().split("\",\"");
		String line = br.readLine();
		while(line != null) {
			RMEDDoctor doctor = new RMEDDoctor();
			String [] arr = line.split("\",\"");
			for(int i = 0; i < header.length; i++) {
				if(arr[i] != null) doctor.addProperty(header[i], arr[i]);
			}
			addDoctor(doctor);
			
			line = br.readLine();
		}
		System.out.println(doctors.size());
	}
	
	public void addDoctor(RMEDDoctor doctor) {
		for(int i = 0; i < doctors.size(); i++) {
			if(doctors.get(i).equals(doctor)) {
				doctors.get(i).updateDoctor(doctor);
				break;
			}
		}
		doctors.add(doctor);
	}
	
	public void addDoctor2(RMEDDoctor doctor) {
		for(int i = 0; i < doctors.size(); i++) {
			if(doctors.get(i).equals(doctor)) {
				doctors.get(i).updateDoctor2(doctor);
				break;
			}
		}
		doctors.add(doctor);
	}
		
	public String cellValue(Cell cell) {
		try {
			try {					return cell.getStringCellValue();}
			catch (Exception e) {	return String.valueOf(cell.getNumericCellValue());}
		}
		catch(Exception e) { return ""; }
	}
	
	public String [] csvLineToArray(String s, String delim, int numOfColInCSV, boolean removeFirstAndLastCh) {
//		try {
			if(removeFirstAndLastCh) { 
				s = s.substring(1,s.length()-1);
			}
			String [] t = new String[numOfColInCSV];
			for(int i = 0; i < t.length-1; i++) {
				int p = s.indexOf(delim);
				t[i] = s.substring(0, p);
				s = s.substring(p+delim.length());
			}
			t[t.length-1] = s;
			return t;
//		}
//		catch (Exception e) {
//			return null;
//		}
	}
	
	
	public RMEDDoctor NPPES(String firstName, String lastName, String specialtyMatch, String year) throws Exception {
		RMEDDoctor doctor = new RMEDDoctor();
		String apiLink = "https://npiregistry.cms.hhs.gov/api/?";
		String limit = "200";
		
		URL url = new URL(apiLink 	+ "first_name=" + firstName 
									+ "&last_name=" + lastName 
									+ "&limit=" + limit 
									+ "&version=2.1");
		
		InputStream inputStream = url.openStream();
		Scanner scanner = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
		String out = scanner.next();
		scanner.close();
		inputStream.close();
		String [] matches = out.split("\"created_epoch\"");
		ArrayList<String> filtered = new ArrayList<String>();
		for(int i = 1; i < matches.length; i++) {
			if(matches[i].contains("\"desc\":") && matches[i].contains("\"credential\":")) {
				if(isSpecialty(specialtyMatch, matches[i]) && isMD(matches[i]) && isYear(year, matches[i]) ) {
					filtered.add(matches[i]);
				}
			}
		}
		if(filtered.size() == 0) {
			for(int i = 1; i < matches.length; i++) {
				if(matches[i].contains("\"credential\":")) {
					if(isMD(matches[i])  && isYear(year, matches[i]) ) {
						filtered.add(matches[i]);
					}
				}
			}
		}
		if(filtered.size() == 0) {
			for(int i = 1; i < matches.length; i++) {
				if(matches[i].contains("\"desc\":")) {
					if(isSpecialty(specialtyMatch, matches[i]) && isYear(year, matches[i]) ) {
						filtered.add(matches[i]);
					}
				}
			}			
		}
		if(filtered.size() == 0) {
			for(int i = 1; i < matches.length; i++) {
				if(isYear(year, matches[i]) ) {
					filtered.add(matches[i]);
				}
			}
		}
		if(filtered.size() == 0 && matches.length == 2) {
			filtered.add(matches[1]);
		}
		if(matches.length == 1) {

		}
		System.out.println(filtered.size());
//		if(filtered.size() > 1) {
//			for(int aa = 0; aa < filtered.size(); aa++) {
//				System.out.println(filtered.get(aa));
//			}
//		}
		if(filtered.size() == 1) {
			String record = filtered.get(0);
			ArrayList<String> fields = new ArrayList<String>();
			String [] fieldNames = {"number",
									"address_1","city","state","postal_code",
									"telephone_number","fax_number",
									"gender",
									"enumeration_date",
									"last_updated"};
			String [] properties = {"NPI",
									"Prac_Add1","Prac_City","Prac_State","Prac_Zip",
									"Provider Business Practice Location Address Telephone Number","Provider Business Practice Location Address Fax Number",
									"Gen",
									"Provider Enumeration Date",
									"Last Update Date"};
//			System.out.println(record);
			for(int i = 0; i < fieldNames.length; i++) {
				int e = record.indexOf("\"" + fieldNames[i] + "\":");
				if(e == -1) {
					String field = null;
//					System.out.println(field);
					doctor.addProperty(properties[i], field);					
					continue;
				}
				String field = record.substring(e + fieldNames[i].length()+4);
//				System.out.println(field); Thread.sleep(500);
				int a = field.indexOf("\",\"");
				int b = field.indexOf("null");
				int c = field.indexOf("\"},{\"");
				int cTemp = field.indexOf("\"}],\"");
				if(c > -1 && cTemp > -1) {
					c = Math.min(c, cTemp);
				}
				else {
					c = Math.max(c, cTemp);
				}
				if(a > -1 && b > -1 && c > -1) {
					int d = Math.min(a, c);
					field = field.substring(0, Math.min(d, b));
//					System.out.println("HERE1" + "\t" + d + "\t" + b);
				}
				else if(a > -1 && b > -1) {
					field = field.substring(0, Math.min(a, b));					
//					System.out.println("HERE2");
				}
				else if(a > -1 && c > -1) {
					field = field.substring(0, Math.min(a, c));
//					System.out.println("HERE3");
				}
//				else if(b > -1 && c > -1) {
//					field = field.substring(0, Math.min(b, c));
//				}
				else {
					field = field.substring(0, Math.max(a, b));
//					System.out.println("HERE4");
				}
//				System.out.println(field);
				doctor.addProperty(properties[i], field);
			}
			doctor.addProperty("F_Name", firstName);
			doctor.addProperty("L_Name", lastName);
			doctor.addProperty("SpecialtyMatch", specialtyMatch);
//			System.out.println(doctor);
//			addDoctor2(doctor);
		}
		return doctor;
	}
	
	public boolean isMD(String match) {
		String md = match.substring(match.indexOf("\"credential\":"));
		md = md.substring(14, md.indexOf(",\"")-1);
		String lowerCasemd = md.toLowerCase();
		if(lowerCasemd.contains("m") && lowerCasemd.contains("d")) {
			boolean isMD = true;
			int mPos = -1;
			int dPos = -1;
			
			for(int j = 0; j < lowerCasemd.length(); j++) {
				if(lowerCasemd.charAt(j) == 'm') {
					if(mPos == -1) 	{ mPos = j; }
					else 			{ isMD = false; }
				}
				else if(lowerCasemd.charAt(j) == 'd') {
					if(dPos == -1) 	{ dPos = j; }
					else 			{ isMD = false; }
				}
			}
			
			if(mPos == -1 || dPos == -1) { isMD = false; }
			
			if(dPos - mPos > 2 || dPos - mPos < 0) { isMD = false; }
			
			if( (md.charAt(mPos) == 'm' && md.charAt(dPos) == 'd')
				||
				(md.charAt(mPos) == 'M' && md.charAt(dPos) == 'D') ) {
				
			}
			else { isMD = false; }
			
			return isMD;
		}
		return false;
	}
	
	public boolean isSpecialty(String specialtyMatch, String match) {
		String sm = match.substring(match.indexOf("\"desc\":"));
//		try {
			sm = sm.substring(8, sm.indexOf(",\"")-1);
//		}
//		catch(Exception e) {
//			System.out.println(sm);
//			System.exit(0);
//		}
		ArrayList<String> specialtyMatchSplit = new ArrayList<String>(Arrays.asList(specialtyMatch.toLowerCase().split(" ")));
		String [] smSplit = sm.toLowerCase().split(" ");
		boolean smCheck = true;
		for(int j = 0; j < smSplit.length; j++) {
			if(smSplit[j].length() > 3) {
				if(!specialtyMatchSplit.contains(smSplit[j])) {
					smCheck = false;
				}
			}
		}
		if(!smCheck) {
			String test = "Student in an Organized Health Care Education/Training Program";
			specialtyMatchSplit = new ArrayList<String>(Arrays.asList(test.toLowerCase().split(" ")));
			smSplit = sm.toLowerCase().split(" ");
			smCheck = true;
			for(int j = 0; j < smSplit.length; j++) {
				if(smSplit[j].length() > 3) {
					if(!specialtyMatchSplit.contains(smSplit[j])) {
						smCheck = false;
					}
				}
			}
		}
		
		return smCheck;
	}
	
	public boolean isYear(String year, String match) {
		String enumerationYear = match.substring(match.indexOf("\"enumeration_date\":"));
		enumerationYear = enumerationYear.substring(20, enumerationYear.indexOf("\","));
		int a = Integer.parseInt(year.substring(0, 4));
		int b = Integer.parseInt(enumerationYear.substring(0, 4));
//		System.out.println(year + "\t" + match + "\t" + a + "\t" + b);
		if(a - b > 2) {
			return false;
		}
		return true;
	}
}
